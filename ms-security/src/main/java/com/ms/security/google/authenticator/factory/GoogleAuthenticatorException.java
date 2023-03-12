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

/**
 * Date: 12/02/14
 * Time: 13:36
 */
public class GoogleAuthenticatorException extends RuntimeException {

    /**
     * Builds an exception with the provided error message.
     *
     * @param message the error message.
     */
    public GoogleAuthenticatorException(String message) {
        super(message);
    }

    /**
     * Builds an exception with the provided error mesasge and
     * the provided cuase.
     *
     * @param message the error message.
     * @param cause   the cause.
     */
    public GoogleAuthenticatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
