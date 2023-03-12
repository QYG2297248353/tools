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

package core.exception.web;

public class MsSendRequestException extends Exception {
    private static final long serialVersionUID = 4177930991742488041L;

    public MsSendRequestException() {
        super();
    }

    public MsSendRequestException(String message) {
        super(message);
    }

    public MsSendRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MsSendRequestException(Throwable cause) {
        super(cause);
    }

    protected MsSendRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
