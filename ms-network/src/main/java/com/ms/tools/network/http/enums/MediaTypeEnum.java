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

package com.ms.tools.network.http.enums;

public enum MediaTypeEnum {
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_XHTML("application/xhtml+xml"),
    APPLICATION_ATOM("application/atom+xml"),
    APPLICATION_FORM("application/x-www-form-urlencoded"),
    APPLICATION_OCTET("application/octet-stream"),
    APPLICATION_SVG("application/svg+xml"),
    APPLICATION_XHTML_XML("application/xhtml+xml"),
    APPLICATION_XHTML_XUL("application/vnd.mozilla.xul+xml");

    private final String value;

    MediaTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public MediaTypeEnum valueTo(String value) {
        for (MediaTypeEnum mediaTypeEnum : MediaTypeEnum.values()) {
            if (mediaTypeEnum.getValue().equals(value)) {
                return mediaTypeEnum;
            }
        }
        return null;
    }
}
