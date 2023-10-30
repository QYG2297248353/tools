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

package com.ms.tools.core.response;

import com.ms.core.config.SystemConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ms2297248353
 */

public enum ResponseHttpStatus {
    /**
     * 状态码：全局正常
     */
    SUCCESS("200", "success"),
    /**
     * 状态码：全局失败
     */
    FAIL("400", "failed"),

    /**
     * 状态码：正常
     */
    HTTP_STATUS_200("200", "ok"),
    /**
     * 状态码：失败
     */
    HTTP_STATUS_400("400", "request error"),
    /**
     * 状态码：未登录
     */
    HTTP_STATUS_401("401", "no authentication"),
    /**
     * 状态码：未授权
     */
    HTTP_STATUS_403("403", "no authorities"),
    /**
     * 状态码：服务器异常
     */
    HTTP_STATUS_500("500", "server error");

    public static final List<ResponseHttpStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500
            ));

    /**
     * response code
     */
    private final String code;

    /**
     * description.
     */
    private final String description;

    ResponseHttpStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 获取HTTP响应状态码
     *
     * @return 状态码
     */
    public String getCode() {
        if (this == SUCCESS) {
            return SystemConfiguration.getSystemSuccessCode();
        }
        if (this == FAIL) {
            return SystemConfiguration.getSystemFailCode();
        }
        return code;
    }

    /**
     * 获取HTTP响应消息
     *
     * @return 消息
     */
    public String getDescription() {
        if (this == SUCCESS) {
            return SystemConfiguration.getSystemSuccessMsg();
        }
        if (this == FAIL) {
            return SystemConfiguration.getSystemFailMsg();
        }
        return description;
    }
}
