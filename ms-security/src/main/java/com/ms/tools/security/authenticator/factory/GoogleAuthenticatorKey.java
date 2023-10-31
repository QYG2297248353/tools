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

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a JavaBean used by the GoogleAuthenticator library to represent
 * a secret key.
 * <p>
 * This class is immutable.
 * <p>
 * Instance of this class should only be constructed by the GoogleAuthenticator
 * library.
 *
 * @version 1.1.4
 * @see GoogleAuthenticator
 * @since 1.0.0
 */
public final class GoogleAuthenticatorKey {
    /**
     * The configuration of this key.
     */
    private final GoogleAuthenticatorConfig config;

    /**
     * The secret key in Base32 encoding.
     */
    private final String key;

    /**
     * The verification code at time = 0 (the UNIX epoch).
     */
    private final int verificationCode;

    /**
     * The list of scratch codes.
     */
    private final List<Integer> scratchCodes;

    /**
     * The private constructor of this class.
     *
     * @param config           the configuration of the TOTP algorithm.
     * @param key              the secret key in Base32 encoding.
     * @param verificationCode the verification code at time = 0 (the UNIX epoch).
     * @param scratchCodes     the list of scratch codes.
     */
    private GoogleAuthenticatorKey(GoogleAuthenticatorConfig config,
                                   String key,
                                   int verificationCode,
                                   List<Integer> scratchCodes) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (config == null) {
            throw new IllegalArgumentException("Configuration cannot be null");
        }

        if (scratchCodes == null) {
            throw new IllegalArgumentException("Scratch codes cannot be null");
        }

        this.config = config;
        this.key = key;
        this.verificationCode = verificationCode;
        this.scratchCodes = new ArrayList<>(scratchCodes);
    }

    /**
     * Get the list of scratch codes.
     *
     * @return the list of scratch codes.
     */
    public List<Integer> getScratchCodes() {
        return scratchCodes;
    }

    /**
     * Get the config of this key.
     *
     * @return the config of this key.
     */
    public GoogleAuthenticatorConfig getConfig() {
        return config;
    }

    /**
     * Returns the secret key in Base32 encoding.
     *
     * @return the secret key in Base32 encoding.
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the verification code at time = 0 (the UNIX epoch).
     *
     * @return the verificationCode at time = 0 (the UNIX epoch).
     */
    public int getVerificationCode() {
        return verificationCode;
    }

    /**
     * This class is a builder to create instances of the {@link GoogleAuthenticatorKey} class.
     */
    public static class Builder {
        private GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();
        private String key;
        private int verificationCode;
        private List<Integer> scratchCodes = new ArrayList<>();

        /**
         * Creates an instance of the builder.
         *
         * @param key the secret key in Base32 encoding.
         * @see GoogleAuthenticatorKey#GoogleAuthenticatorKey(GoogleAuthenticatorConfig, String, int, List)
         */
        public Builder(String key) {
            this.key = key;
        }

        /**
         * Creates an instance of the {@link GoogleAuthenticatorKey} class.
         *
         * @return an instance of the {@link GoogleAuthenticatorKey} class initialized with the properties set in this builder.
         * @see GoogleAuthenticatorKey#GoogleAuthenticatorKey(GoogleAuthenticatorConfig, String, int, List)
         */
        public GoogleAuthenticatorKey build() {
            return new GoogleAuthenticatorKey(config, key, verificationCode, scratchCodes);
        }

        /**
         * Sets the config of the TOTP algorithm for this key.
         *
         * @param config the config of the TOTP algorithm for this key.
         * @return the builder.
         * @see GoogleAuthenticatorKey#GoogleAuthenticatorKey(GoogleAuthenticatorConfig, String, int, List)
         */
        public Builder setConfig(GoogleAuthenticatorConfig config) {
            this.config = config;
            return this;
        }

        /**
         * Sets the secret key.
         *
         * @param key the secret key.
         * @return the builder.
         * @see GoogleAuthenticatorKey#GoogleAuthenticatorKey(GoogleAuthenticatorConfig, String, int, List)
         */
        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        /**
         * Sets the verification code.
         *
         * @param verificationCode the verification code.
         * @return the builder.
         * @see GoogleAuthenticatorKey#GoogleAuthenticatorKey(GoogleAuthenticatorConfig, String, int, List)
         */
        public Builder setVerificationCode(int verificationCode) {
            this.verificationCode = verificationCode;
            return this;
        }

        /**
         * Sets the scratch codes.
         *
         * @param scratchCodes the scratch codes.
         * @return the builder.
         * @see GoogleAuthenticatorKey#GoogleAuthenticatorKey(GoogleAuthenticatorConfig, String, int, List)
         */
        public Builder setScratchCodes(List<Integer> scratchCodes) {
            this.scratchCodes = scratchCodes;
            return this;
        }
    }
}
