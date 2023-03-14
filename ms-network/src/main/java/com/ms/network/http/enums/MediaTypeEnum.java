package com.ms.network.http.enums;

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

    private String value;

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
