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

package com.ms.api.tencent.sms.response;

import com.alibaba.fastjson2.annotation.JSONField;

public class ErrorRB {
    @JSONField(name = "Code")
    private String code;
    @JSONField(name = "Message")
    private String message;

    public ErrorRB() {
        code = "404";
        message = "请求失败";
    }

    public ErrorRB(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorRB error() {
        return new ErrorRB();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
