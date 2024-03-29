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

package com.ms.tools.security.authenticator.factory;

import java.util.concurrent.TimeUnit;

public class GoogleAuthenticatorConfig {
    private long timeStepSizeInMillis = TimeUnit.SECONDS.toMillis(30);
    private int windowSize = 3;
    private int codeDigits = 6;
    private int numberOfScratchCodes = 5;
    private int keyModulus = (int) Math.pow(10, codeDigits);
    private int secretBits = 160;
    private KeyRepresentation keyRepresentation = KeyRepresentation.BASE32;
    private HmacHashFunction hmacHashFunction = HmacHashFunction.HmacSHA1;

    /**
     * Returns the key module.
     *
     * @return the key module.
     */
    public int getKeyModulus() {
        return keyModulus;
    }

    /**
     * Returns the key representation.
     *
     * @return the key representation.
     */
    public KeyRepresentation getKeyRepresentation() {
        return keyRepresentation;
    }

    /**
     * Returns the number of digits in the generated code.
     *
     * @return the number of digits in the generated code.
     */
    @SuppressWarnings("UnusedDeclaration")
    public int getCodeDigits() {
        return codeDigits;
    }

    /**
     * Returns the number of scratch codes to generate.  We are using Google's default of providing 5 scratch codes.
     *
     * @return the number of scratch codes to generate.
     */
    public int getNumberOfScratchCodes() {
        return numberOfScratchCodes;
    }

    /**
     * Returns the time step size, in milliseconds, as specified by RFC 6238.
     * The default value is 30.000.
     *
     * @return the time step size in milliseconds.
     */
    public long getTimeStepSizeInMillis() {
        return timeStepSizeInMillis;
    }

    /**
     * Returns an integer value representing the number of windows of size
     * timeStepSizeInMillis that are checked during the validation process,
     * to account for differences between the server and the client clocks.
     * The bigger the window, the more tolerant the library code is about
     * clock skews.
     * <p>
     * We are using Google's default behaviour of using a window size equal
     * to 3.  The limit on the maximum window size, present in older
     * versions of this library, has been removed.
     *
     * @return the window size.
     * @see #timeStepSizeInMillis
     */
    public int getWindowSize() {
        return windowSize;
    }

    /**
     * Returns the number of bits of the secret keys to generate.  The length
     * should always be a multiple of 8.  The default value is 160 bits, and
     * a value smaller than 128 is disallowed, as recommended by RFC 4226 §4.
     *
     * @return the secret size in bits.
     */
    public int getSecretBits() {
        return secretBits;
    }

    /**
     * Returns the cryptographic hash function used to calculate the HMAC (Hash-based
     * Message Authentication Code). This implementation uses the SHA1 hash
     * function by default.
     * <p>
     *
     * @return the HMAC hash function.
     */
    public HmacHashFunction getHmacHashFunction() {
        return hmacHashFunction;
    }

    public static class GoogleAuthenticatorConfigBuilder {
        private GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();

        public GoogleAuthenticatorConfig build() {
            return config;
        }

        public GoogleAuthenticatorConfigBuilder setCodeDigits(int codeDigits) {
            if (codeDigits <= 0) {
                throw new IllegalArgumentException("Code digits must be positive.");
            }

            if (codeDigits < 6) {
                throw new IllegalArgumentException("The minimum number of digits is 6.");
            }

            if (codeDigits > 8) {
                throw new IllegalArgumentException("The maximum number of digits is 8.");
            }

            config.codeDigits = codeDigits;
            config.keyModulus = (int) Math.pow(10, codeDigits);
            return this;
        }

        public GoogleAuthenticatorConfigBuilder setNumberOfScratchCodes(int numberOfScratchCodes) {
            if (numberOfScratchCodes < 0) {
                throw new IllegalArgumentException("The number of scratch codes must not be negative");
            }

            if (numberOfScratchCodes > 1_000) {
                throw new IllegalArgumentException("The maximum number of scratch codes is 1000");
            }

            config.numberOfScratchCodes = numberOfScratchCodes;
            return this;
        }

        public GoogleAuthenticatorConfigBuilder setTimeStepSizeInMillis(long timeStepSizeInMillis) {
            if (timeStepSizeInMillis <= 0) {
                throw new IllegalArgumentException("Time step size must be positive.");
            }

            config.timeStepSizeInMillis = timeStepSizeInMillis;
            return this;
        }

        public GoogleAuthenticatorConfigBuilder setWindowSize(int windowSize) {
            if (windowSize <= 0) {
                throw new IllegalArgumentException("Window number must be positive.");
            }

            config.windowSize = windowSize;
            return this;
        }

        public GoogleAuthenticatorConfigBuilder setSecretBits(int secretBits) {
            if (secretBits < 128) {
                throw new IllegalArgumentException("Secret bits must be greater than or equal to 128.");
            }

            if (secretBits % 8 != 0) {
                throw new IllegalArgumentException("Secret bits must be a multiple of 8.");
            }

            config.secretBits = secretBits;
            return this;
        }

        public GoogleAuthenticatorConfigBuilder setKeyRepresentation(KeyRepresentation keyRepresentation) {
            if (keyRepresentation == null) {
                throw new IllegalArgumentException("Key representation cannot be null.");
            }

            config.keyRepresentation = keyRepresentation;
            return this;
        }

        public GoogleAuthenticatorConfigBuilder setHmacHashFunction(HmacHashFunction hmacHashFunction) {
            if (hmacHashFunction == null) {
                throw new IllegalArgumentException("HMAC Hash Function cannot be null.");
            }

            config.hmacHashFunction = hmacHashFunction;
            return this;
        }
    }
}
