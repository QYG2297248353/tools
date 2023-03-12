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

package com.ms.id.factory.uuid.exception;

import java.util.Arrays;

/**
 * Runtime exception to be used when an invalid UUID is received as argument.
 */
public final class InvalidUuidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUuidException(String message) {
        super(message);
    }

    public InvalidUuidException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Factory method for creating a runtime exception.
     *
     * @param obj an object that can, for example, a string of a char array.
     * @return a runtime exception
     */
    public static InvalidUuidException newInstance(Object obj) {

        String string;
        if (obj == null) {
            string = null;
        } else if (obj instanceof char[]) {
            string = String.valueOf((char[]) obj);
        } else if (obj.getClass().isArray()) {
            string = Arrays.toString((byte[]) obj);
        } else {
            string = String.valueOf(obj);
        }

        if (string != null) {
            string = "\"" + string + "\"";
        }

        return new InvalidUuidException("Invalid UUID: " + string);
    }
}
