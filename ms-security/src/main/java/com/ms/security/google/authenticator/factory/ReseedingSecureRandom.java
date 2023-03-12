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

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

class ReseedingSecureRandom {
    private static final int MAX_OPERATIONS = 1_000_000;
    private final String provider;
    private final String algorithm;
    private final AtomicInteger count = new AtomicInteger(0);
    private volatile SecureRandom secureRandom;

    ReseedingSecureRandom() {
        this.algorithm = null;
        this.provider = null;

        buildSecureRandom();
    }

    ReseedingSecureRandom(String algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm cannot be null.");
        }

        this.algorithm = algorithm;
        this.provider = null;

        buildSecureRandom();
    }

    ReseedingSecureRandom(String algorithm, String provider) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm cannot be null.");
        }

        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null.");
        }

        this.algorithm = algorithm;
        this.provider = provider;

        buildSecureRandom();
    }

    private void buildSecureRandom() {
        try {
            if (this.algorithm == null && this.provider == null) {
                this.secureRandom = new SecureRandom();
            } else if (this.provider == null) {
                this.secureRandom = SecureRandom.getInstance(this.algorithm);
            } else {
                this.secureRandom = SecureRandom.getInstance(this.algorithm, this.provider);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new GoogleAuthenticatorException(
                    String.format(
                            "Could not initialise SecureRandom with the specified algorithm: %s. " +
                                    "Another provider can be chosen setting the %s system property.",
                            this.algorithm,
                            GoogleAuthenticator.RNG_ALGORITHM
                    ), e
            );
        } catch (NoSuchProviderException e) {
            throw new GoogleAuthenticatorException(
                    String.format(
                            "Could not initialise SecureRandom with the specified provider: %s. " +
                                    "Another provider can be chosen setting the %s system property.",
                            this.provider,
                            GoogleAuthenticator.RNG_ALGORITHM_PROVIDER
                    ), e
            );
        }
    }

    void nextBytes(byte[] bytes) {
        if (count.incrementAndGet() > MAX_OPERATIONS) {
            synchronized (this) {
                if (count.get() > MAX_OPERATIONS) {
                    buildSecureRandom();
                    count.set(0);
                }
            }
        }

        this.secureRandom.nextBytes(bytes);
    }
}
