/*
 * @MS 2022-12-13
 * Copyright (c) 2001-2023 萌森
 * 保留所有权利
 * 本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 * Copyright (c) 2001-2023 Meng Sen
 * All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.security.google.authenticator.factory;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 该类实现了RFC 6238（TOTP：基于时间的一次性密码算法）中描述的功能
 * 并在其Google Authenticator应用程序中再次测试了Google对该算法的实现。
 * <p>
 * 该类允许用户创建一个新的16位base32编码的密钥
 * 该密钥具有在｛@code time=0｝（UNIX纪元）计算的验证码和Google提供的QR条形码的URL，以便用户将生成的信息加载到Google Authenticator中。
 * <p>
 * 此类使用的随机数生成器使用默认算法和提供程序。
 * 用户可以通过将以下系统属性设置为自己选择的算法和提供程序名称来覆盖它们：
 * <ul>
 * <li>{@link #RNG_ALGORITHM}.</li>
 * <li>{@link #RNG_ALGORITHM_PROVIDER}.</li>
 * </ul>
 * <p>
 * 此类不会以任何方式存储生成的密钥或授权过程中传递的密钥。
 *
 * @version 1.1.4
 * @see <a href="http://thegreyblog.blogspot.com/2011/12/google-authenticator-using-it-in-your.html"></a>
 * @see <a href="http://code.google.com/p/google-authenticator"></a>
 * @see <a href="http://tools.ietf.org/id/draft-mraihi-totp-timebased-06.txt"></a>
 * @since 0.3.0
 */
public final class GoogleAuthenticator implements IGoogleAuthenticator {

    /**
     * The system property to specify the random number generator algorithm to use.
     *
     * @since 0.5.0
     */
    public static final String RNG_ALGORITHM = "com.ms.google.auth.algorithm";

    /**
     * The system property to specify the random number generator provider to use.
     *
     * @since 0.5.0
     */
    public static final String RNG_ALGORITHM_PROVIDER = "com.ms.google.auth.algorithmProvider";

    /**
     * The logger for this class.
     */
    private static final Logger log = Logger.getLogger(GoogleAuthenticator.class.getName());

    /**
     * Number of digits of a scratch code represented as a decimal integer.
     */
    private static final int SCRATCH_CODE_LENGTH = 8;

    /**
     * Modulus used to truncate the scratch code.
     */
    public static final int SCRATCH_CODE_MODULUS = (int) Math.pow(10, SCRATCH_CODE_LENGTH);

    /**
     * Magic number representing an invalid scratch code.
     */
    private static final int SCRATCH_CODE_INVALID = -1;

    /**
     * Length in bytes of each scratch code. We're using Google's default of
     * using 4 bytes per scratch code.
     */
    private static final int BYTES_PER_SCRATCH_CODE = 4;

    /**
     * The default SecureRandom algorithm to use if none is specified.
     *
     * @see java.security.SecureRandom#getInstance(String)
     * @since 0.5.0
     */
    private static final String DEFAULT_RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

    /**
     * The default random number algorithm provider to use if none is specified.
     *
     * @see java.security.SecureRandom#getInstance(String)
     * @since 0.5.0
     */
    private static final String DEFAULT_RANDOM_NUMBER_ALGORITHM_PROVIDER = "SUN";

    /**
     * The configuration used by the current instance.
     */
    private final GoogleAuthenticatorConfig config;

    /**
     * The internal SecureRandom instance used by this class.  Since Java 7
     * {@link Random} instances are required to be thread-safe, no synchronisation is
     * required in the methods of this class using this instance.  Thread-safety
     * of this class was a de-facto standard in previous versions of Java so
     * that it is expected to work correctly in previous versions of the Java
     * platform as well.
     */
    private ReseedingSecureRandom secureRandom;

    private ICredentialRepository credentialRepository;
    private boolean credentialRepositorySearched;

    /**
     * The constructor that uses the default config, random number algorithm, and random number algorithm provider.
     */
    public GoogleAuthenticator() {
        config = new GoogleAuthenticatorConfig();

        secureRandom = new ReseedingSecureRandom(
                getRandomNumberAlgorithm(),
                getRandomNumberAlgorithmProvider());
    }

    /**
     * The constructor that allows a user to specify the config and uses the default randomNumberAlgorithm and randomNumberAlgorithmProvider.
     *
     * @param config The configuration used by the current instance.
     */
    public GoogleAuthenticator(GoogleAuthenticatorConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Configuration cannot be null.");
        }

        this.config = config;

        secureRandom = new ReseedingSecureRandom(
                getRandomNumberAlgorithm(),
                getRandomNumberAlgorithmProvider()
        );
    }

    /**
     * The constructor that allows a user the randomNumberAlgorithm, the randomNumberAlgorithmProvider, and uses the default config.
     *
     * @param randomNumberAlgorithm         The random number algorithm to define the secure random number generator. If this is null the randomNumberAlgorithmProvider must be null.
     * @param randomNumberAlgorithmProvider The random number algorithm provider to define the secure random number generator. This value may be null.
     */
    public GoogleAuthenticator(String randomNumberAlgorithm, String randomNumberAlgorithmProvider) {
        this(new GoogleAuthenticatorConfig(), randomNumberAlgorithm, randomNumberAlgorithmProvider);

    }

    /**
     * The constructor that allows a user to specify the config, the randomNumberAlgorithm, and the randomNumberAlgorithmProvider.
     *
     * @param config                        The configuration used by the current instance.
     * @param randomNumberAlgorithm         The random number algorithm to define the secure random number generator. If this is null the randomNumberAlgorithmProvider must be null.
     * @param randomNumberAlgorithmProvider The random number algorithm provider to define the secure random number generator. This value may be null.
     */
    public GoogleAuthenticator(GoogleAuthenticatorConfig config, String randomNumberAlgorithm, String randomNumberAlgorithmProvider) {
        if (config == null) {
            throw new IllegalArgumentException("Configuration cannot be null.");
        }

        this.config = config;

        if (randomNumberAlgorithm == null && randomNumberAlgorithmProvider == null) {
            secureRandom = new ReseedingSecureRandom();
        } else if (randomNumberAlgorithm == null) {
            throw new IllegalArgumentException("RandomNumberAlgorithm must not be null. If the RandomNumberAlgorithm is null, the RandomNumberAlgorithmProvider must also be null.");
        } else if (randomNumberAlgorithmProvider == null) {
            secureRandom = new ReseedingSecureRandom(randomNumberAlgorithm);
        }
    }

    /**
     * @return the default random number generator algorithm.
     * @since 0.5.0
     */
    private String getRandomNumberAlgorithm() {
        return System.getProperty(
                RNG_ALGORITHM,
                DEFAULT_RANDOM_NUMBER_ALGORITHM);
    }

    /**
     * @return the default random number generator algorithm provider.
     * @since 0.5.0
     */
    private String getRandomNumberAlgorithmProvider() {
        return System.getProperty(
                RNG_ALGORITHM_PROVIDER,
                DEFAULT_RANDOM_NUMBER_ALGORITHM_PROVIDER);
    }

    /**
     * 使用RFC 6238中指定的算法计算指定时刻提供的密钥的验证码。
     *
     * @param key 二进制格式的密钥。
     * @param tm  时间
     * @return 指定时刻提供的密钥的验证代码
     */
    int calculateCode(byte[] key, long tm) {
        // Allocating an array of bytes to represent the specified instant
        // of time.
        byte[] data = new byte[8];
        long value = tm;

        // Converting the instant of time from the long representation to a
        // big-endian array of bytes (RFC4226, 5.2. Description).
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }

        // Building the secret key specification for the HmacSHA1 algorithm.
        SecretKeySpec signKey = new SecretKeySpec(key, config.getHmacHashFunction().toString());

        try {
            // Getting an HmacSHA1/HmacSHA256 algorithm implementation from the JCE.
            Mac mac = Mac.getInstance(config.getHmacHashFunction().toString());

            // Initializing the MAC algorithm.
            mac.init(signKey);

            // Processing the instant of time and getting the encrypted data.
            byte[] hash = mac.doFinal(data);

            // Building the validation code performing dynamic truncation
            // (RFC4226, 5.3. Generating an HOTP value)
            int offset = hash[hash.length - 1] & 0xF;

            // We are using a long because Java hasn't got an unsigned integer type
            // and we need 32 unsigned bits).
            long truncatedHash = 0;

            for (int i = 0; i < 4; ++i) {
                truncatedHash <<= 8;

                // Java bytes are signed but we need an unsigned integer:
                // cleaning off all but the LSB.
                truncatedHash |= (hash[offset + i] & 0xFF);
            }

            // Clean bits higher than the 32nd (inclusive) and calculate the
            // module with the maximum validation code value.
            truncatedHash &= 0x7FFFFFFF;
            truncatedHash %= config.getKeyModulus();

            // Returning the validation code to the caller.
            return (int) truncatedHash;
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            // Logging the exception.
            log.log(Level.SEVERE, ex.getMessage(), ex);

            // We're not disclosing internal error details to our clients.
            throw new GoogleAuthenticatorException("The operation cannot be performed now.");
        }
    }

    private long getTimeWindowFromTime(long time) {
        return time / config.getTimeStepSizeInMillis();
    }

    /**
     * This method implements the algorithm specified in RFC 6238 to check if a
     * validation code is valid in a given instant of time for the given secret
     * key.
     *
     * @param secret    the Base32 encoded secret key.
     * @param code      the code to validate.
     * @param timestamp the instant of time to use during the validation process.
     * @param window    the window size to use during the validation process.
     * @return <code>true</code> if the validation code is valid,
     * <code>false</code> otherwise.
     */
    private boolean checkCode(
            String secret,
            long code,
            long timestamp,
            int window) {
        byte[] decodedKey = decodeSecret(secret);

        // convert unix time into a 30 second "window" as specified by the
        // TOTP specification. Using Google's default interval of 30 seconds.
        long timeWindow = getTimeWindowFromTime(timestamp);

        // Calculating the verification code of the given key in each of the
        // time intervals and returning true if the provided code is equal to
        // one of them.
        for (int i = -((window - 1) / 2); i <= window / 2; ++i) {
            // Calculating the verification code for the current time interval.
            long hash = calculateCode(decodedKey, timeWindow + i);

            // Checking if the provided code is equal to the calculated one.
            if (hash == code) {
                // The verification code is valid.
                return true;
            }
        }

        // The verification code is invalid.
        return false;
    }

    private byte[] decodeSecret(String secret) {
        // Decoding the secret key to get its raw byte representation.
        switch (config.getKeyRepresentation()) {
            case BASE32:
                Base32 codec32 = new Base32();
                // See: https://issues.apache.org/jira/browse/CODEC-234
                // Commons Codec Base32::decode does not support lowercase letters.
                return codec32.decode(secret.toUpperCase());
            case BASE64:
                Base64 codec64 = new Base64();
                return codec64.decode(secret);
            default:
                throw new IllegalArgumentException("Unknown key representation type.");
        }
    }

    @Override
    public GoogleAuthenticatorKey createCredentials() {
        // 分配足够大的缓冲区以容纳密钥所需的字节。
        int bufferSize = config.getSecretBits() / 8;
        byte[] buffer = new byte[bufferSize];

        secureRandom.nextBytes(buffer);

        // 提取组成密钥的字节。
        byte[] secretKey = Arrays.copyOf(buffer, bufferSize);
        String generatedKey = calculateSecretKey(secretKey);

        // 在时间 = 0时生成验证码。
        int validationCode = calculateValidationCode(secretKey);

        // 计算暂存代码
        List<Integer> scratchCodes = calculateScratchCodes();

        return
                new GoogleAuthenticatorKey
                        .Builder(generatedKey)
                        .setConfig(config)
                        .setVerificationCode(validationCode)
                        .setScratchCodes(scratchCodes)
                        .build();
    }

    @Override
    public GoogleAuthenticatorKey createCredentials(String userName) {
        // Further validation will be performed by the configured provider.
        if (userName == null) {
            throw new IllegalArgumentException("User name cannot be null.");
        }

        GoogleAuthenticatorKey key = createCredentials();

        ICredentialRepository repository = getValidCredentialRepository();
        repository.saveUserCredentials(
                userName,
                key.getKey(),
                key.getVerificationCode(),
                key.getScratchCodes());

        return key;
    }

    private List<Integer> calculateScratchCodes() {
        List<Integer> scratchCodes = new ArrayList<>();

        for (int i = 0; i < config.getNumberOfScratchCodes(); ++i) {
            scratchCodes.add(generateScratchCode());
        }

        return scratchCodes;
    }

    /**
     * This method calculates a scratch code from a random byte buffer of
     * suitable size <code>#BYTES_PER_SCRATCH_CODE</code>.
     *
     * @param scratchCodeBuffer a random byte buffer whose minimum size is
     *                          <code>#BYTES_PER_SCRATCH_CODE</code>.
     * @return the scratch code.
     */
    private int calculateScratchCode(byte[] scratchCodeBuffer) {
        if (scratchCodeBuffer.length < BYTES_PER_SCRATCH_CODE) {
            throw new IllegalArgumentException(
                    String.format(
                            "The provided random byte buffer is too small: %d.",
                            scratchCodeBuffer.length));
        }

        int scratchCode = 0;

        for (int i = 0; i < BYTES_PER_SCRATCH_CODE; ++i) {
            scratchCode = (scratchCode << 8) + (scratchCodeBuffer[i] & 0xff);
        }

        scratchCode = (scratchCode & 0x7FFFFFFF) % SCRATCH_CODE_MODULUS;

        // Accept the scratch code only if it has exactly
        // SCRATCH_CODE_LENGTH digits.
        if (validateScratchCode(scratchCode)) {
            return scratchCode;
        } else {
            return SCRATCH_CODE_INVALID;
        }
    }

    /* package */ boolean validateScratchCode(int scratchCode) {
        return (scratchCode >= SCRATCH_CODE_MODULUS / 10);
    }

    /**
     * This method creates a new random byte buffer from which a new scratch
     * code is generated. This function is invoked if a scratch code generated
     * from the main buffer is invalid because it does not satisfy the scratch
     * code restrictions.
     *
     * @return A valid scratch code.
     */
    private int generateScratchCode() {
        while (true) {
            byte[] scratchCodeBuffer = new byte[BYTES_PER_SCRATCH_CODE];
            secureRandom.nextBytes(scratchCodeBuffer);

            int scratchCode = calculateScratchCode(scratchCodeBuffer);

            if (scratchCode != SCRATCH_CODE_INVALID) {
                return scratchCode;
            }
        }
    }

    /**
     * This method calculates the validation code at time 0.
     *
     * @param secretKey The secret key to use.
     * @return the validation code at time 0.
     */
    private int calculateValidationCode(byte[] secretKey) {
        return calculateCode(secretKey, 0);
    }


    @Override
    public int getTotpPassword(String secret) {
        return getTotpPassword(secret, System.currentTimeMillis());
    }

    @Override
    public int getTotpPassword(String secret, long time) {
        return calculateCode(decodeSecret(secret), getTimeWindowFromTime(time));
    }

    @Override
    public int getTotpPasswordOfUser(String userName) {
        return getTotpPasswordOfUser(userName, System.currentTimeMillis());
    }

    @Override
    public int getTotpPasswordOfUser(String userName, long time) {
        ICredentialRepository repository = getValidCredentialRepository();

        return calculateCode(
                decodeSecret(repository.getSecretKey(userName)),
                getTimeWindowFromTime(time));
    }

    /**
     * This method calculates the secret key given a random byte buffer.
     *
     * @param secretKey a random byte buffer.
     * @return the secret key.
     */
    private String calculateSecretKey(byte[] secretKey) {
        switch (config.getKeyRepresentation()) {
            case BASE32:
                return new Base32().encodeToString(secretKey);
            case BASE64:
                return new Base64().encodeToString(secretKey);
            default:
                throw new IllegalArgumentException("Unknown key representation type.");
        }
    }

    @Override
    public boolean authorize(String secret, int verificationCode) {
        return authorize(secret, verificationCode, System.currentTimeMillis());
    }

    @Override
    public boolean authorize(String secret, int verificationCode, long time) {
        // Checking user input and failing if the secret key was not provided.
        if (secret == null) {
            throw new IllegalArgumentException("Secret cannot be null.");
        }

        // Checking if the verification code is between the legal bounds.
        if (verificationCode <= 0 || verificationCode >= config.getKeyModulus()) {
            return false;
        }

        // Checking the validation code using the current UNIX time.
        return checkCode(
                secret,
                verificationCode,
                time,
                config.getWindowSize());
    }

    @Override
    public boolean authorizeUser(String userName, int verificationCode) {
        return authorizeUser(userName, verificationCode, System.currentTimeMillis());
    }

    @Override
    public boolean authorizeUser(String userName, int verificationCode, long time) {
        ICredentialRepository repository = getValidCredentialRepository();

        return authorize(repository.getSecretKey(userName), verificationCode, time);
    }

    /**
     * 此方法加载使用Java服务加载器API注册的第一个可用且有效的ICredentialRepository。
     *
     * @return the first registered ICredentialRepository.
     * @throws UnsupportedOperationException if no valid service is
     *                                       found.
     */
    private ICredentialRepository getValidCredentialRepository() {
        ICredentialRepository repository = getCredentialRepository();

        if (repository == null) {
            throw new UnsupportedOperationException(
                    String.format("An instance of the %s service must be " +
                                    "configured in order to use this feature.",
                            ICredentialRepository.class.getName()
                    )
            );
        }

        return repository;
    }

    /**
     * 此方法加载使用Java服务加载器API注册的第一个可用ICredentialRepository
     *
     * @return the first registered ICredentialRepository or <code>null</code>
     * if none is found.
     */
    @Override
    public ICredentialRepository getCredentialRepository() {
        if (credentialRepositorySearched) {
            return credentialRepository;
        }

        credentialRepositorySearched = true;

        ServiceLoader<ICredentialRepository> loader =
                ServiceLoader.load(ICredentialRepository.class);

        for (ICredentialRepository repository : loader) {
            credentialRepository = repository;
            break;
        }

        return credentialRepository;
    }

    /**
     * 手动装载存储库
     *
     * @param repository The credential repository to use, or {@code null} to
     *                   disable this feature.
     */
    @Override
    public void setCredentialRepository(ICredentialRepository repository) {
        credentialRepository = repository;
        credentialRepositorySearched = true;
    }

    /**
     * Int to String 补位
     *
     * @param code 代码
     * @return 完整验证码
     */
    public String parseTotpCode(int code) {
        String format = String.format("%06d", code);
        return format;
    }

    public GoogleAuthenticatorKey parseGoogleAuthenticatorKey(String secret) {
        return new GoogleAuthenticatorKey
                .Builder(secret)
                .setConfig(new GoogleAuthenticatorConfig())
                .setVerificationCode(calculateCode(secret.getBytes(), 0))
                .build();
    }
}
