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
