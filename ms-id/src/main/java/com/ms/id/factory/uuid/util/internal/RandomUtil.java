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

package com.ms.id.factory.uuid.util.internal;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Utility class that wraps a shared {@link SecureRandom} and provides new
 * instances of {@link SecureRandom}.
 */
public final class RandomUtil {

    protected static final SecureRandom SHARED_RANDOM = newSecureRandom();

    private RandomUtil() {
    }

    public static int nextInt() {
        return SHARED_RANDOM.nextInt();
    }

    public static long nextLong() {
        return SHARED_RANDOM.nextLong();
    }

    /**
     * Returns a new instance of {@link SecureRandom}.
     * <p>
     * It tries to create an instance with the algorithm name specified in the
     * system property `uuidcreator.securerandom` or in the environment variable
     * `UUIDCREATOR_SECURERANDOM`. If the algorithm name is not supported by the
     * runtime, it returns an instance with the default algorithm.
     * <p>
     * It can be useful to make use of SHA1PRNG or DRBG as a non-blocking source of
     * random bytes. The SHA1PRNG algorithm is default on operating systems that
     * don't have '/dev/random', e.g., on Windows. The DRBG algorithm is available
     * in JDK 9+.
     * <p>
     * To control the algorithm used by this method, use the system property
     * `uuidcreator.securerandom` or the environment variable
     * `UUIDCREATOR_SECURERANDOM` as in examples below.
     * <p>
     * System property:
     *
     * <pre>{@code
     * # Use the the algorithm SHA1PRNG for SecureRandom
     * -Duuidcreator.securerandom="SHA1PRNG"
     *
     * # Use the the algorithm DRBG for SecureRandom (JDK9+)
     * -Duuidcreator.securerandom="DRBG"
     * }</pre>
     *
     * <p>
     * Environment variable:
     *
     * <pre>{@code
     * # Use the the algorithm SHA1PRNG for SecureRandom
     * export UUIDCREATOR_SECURERANDOM="SHA1PRNG"
     *
     * # Use the the algorithm DRBG for SecureRandom (JDK9+)
     * export UUIDCREATOR_SECURERANDOM="DRBG"
     * }</pre>
     *
     * @return a new {@link SecureRandom}.
     */
    public static SecureRandom newSecureRandom() {
        String algorithm = SettingsUtil.getSecureRandom();
        if (algorithm != null) {
            try {
                return SecureRandom.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                return new SecureRandom();
            }
        }
        return new SecureRandom();
    }
}
