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

package com.ms.network.okhttp.enums;

/**
 * 请求类型
 *
 * @author ms2297248353
 */

public enum ContentTypeEnum {
    /**
     * application/json
     * json 请求
     */
    APPLICATION_JSON("application/json"),
    /**
     * application/x-www-form-urlencoded
     * 表单请求
     */
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    /**
     * multipart/form-data
     * 文件上传
     */
    MULTIPART_FORM_DATA("multipart/form-data"),
    /**
     * text/plain
     * raw 请求
     */
    TEXT_PLAIN("text/plain"),
    /**
     * text/xml
     * xml 请求
     */
    TEXT_XML("text/xml"),
    /**
     * text/html
     * html 请求
     */
    TEXT_HTML("text/html"),
    /**
     * application/octet-stream
     * 二进制流
     */
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    /**
     * application/xml
     * xml 请求
     */
    APPLICATION_XML("application/xml"),
    /**
     * application/zip
     * zip 请求
     */
    APPLICATION_ZIP("application/zip"),
    /**
     * application/pdf
     * pdf 请求
     */
    APPLICATION_PDF("application/pdf"),
    /**
     * application/msword
     * word 请求
     */
    APPLICATION_MSWORD("application/msword");

    private String value;

    ContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public ContentTypeEnum valueOfType(String value) {
        for (ContentTypeEnum contentTypeEnum : ContentTypeEnum.values()) {
            if (contentTypeEnum.getValue().equals(value)) {
                return contentTypeEnum;
            }
        }
        return ContentTypeEnum.APPLICATION_JSON;
    }


}
