package com.ms.network.okhttp.enums;

public enum RequestMethodEnum {
    /**
     * GET
     */
    GET,
    /**
     * POST
     */
    POST,
    /**
     * PUT
     */
    PUT,
    /**
     * DELETE
     */
    DELETE,
    /**
     * HEAD
     */
    HEAD,
    /**
     * PATCH
     */
    PATCH,
    /**
     * OPTIONS
     */
    OPTIONS,
    /**
     * TRACE
     */
    TRACE;

    public static RequestMethodEnum fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }

}
