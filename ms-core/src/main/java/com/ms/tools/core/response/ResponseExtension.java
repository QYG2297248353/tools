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

import java.io.Serializable;

/**
 * JSON 统一响应格式 - 扩展版本
 *
 * @author ms2297248353
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseExtension<T> {
    /**
     * response code
     * 默认值
     * OK 200
     * Fail 400
     */
    private String status;

    /**
     * response message
     * 默认值
     * OK success
     * Fail failed
     */
    private String message;

    /**
     * response data
     * null by default
     */
    private T data;

    /**
     * response timestamp
     * default value is System.currentTimeMillis()
     */
    @Builder.Default
    private long timestamp = System.currentTimeMillis();

    /**
     * response version 1.0.0
     */
    @Builder.Default
    private String version = SystemConfiguration.getSystemVersion();

    /**
     * response encoding format utf-8
     */
    @Builder.Default
    private String charset = SystemConfiguration.getSystemEncoding();

    /**
     * response language zh_CN
     */
    @Builder.Default
    private String language = SystemConfiguration.getSystemLanguage();

    /**
     * response timezone GMT+8
     */
    @Builder.Default
    private String timezone = SystemConfiguration.getSystemTimezone();


    /**
     * response country CN
     */
    @Builder.Default
    private String country = SystemConfiguration.getSystemCountry();


    /**
     * response success
     *
     * @return ResponseResult
     */
    public static ResponseExtension success() {
        return success(ResponseHttpStatus.SUCCESS.getDescription());
    }

    /**
     * response success
     *
     * @param message message
     * @return ResponseResult
     */
    public static ResponseExtension success(String message) {
        return success(ResponseHttpStatus.SUCCESS.getCode(), message);
    }

    /**
     * response success
     *
     * @param code    code
     * @param message message
     * @return ResponseResult
     */
    public static ResponseExtension success(String code, String message) {
        return ResponseExtension.builder()
                .status(code)
                .message(message)
                .build();
    }

    /**
     * response success
     *
     * @param data data
     * @param <T>  响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> ResponseExtension<T> success(T data) {
        return success(ResponseHttpStatus.SUCCESS.getDescription(), data);
    }

    /**
     * response success
     *
     * @param data data
     * @param <T>  响应类
     * @return ResponseResult
     */
    public static <T> ResponseExtension<T> success(T data) {
        return success(ResponseHttpStatus.SUCCESS.getDescription(), data);
    }

    /**
     * response success
     *
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> ResponseExtension<T> success(String message, T data) {
        return success(ResponseHttpStatus.SUCCESS.getCode(), message, data);
    }

    /**
     * response success
     *
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T> ResponseExtension<T> success(String message, T data) {
        return success(ResponseHttpStatus.SUCCESS.getCode(), message, data);
    }

    /**
     * response success
     *
     * @param code    code
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> ResponseExtension<T> success(String code, String message, T data) {
        return ResponseExtension.<T>builder()
                .status(code)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * response success
     *
     * @param code    code
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T> ResponseExtension<T> success(String code, String message, T data) {
        return ResponseExtension.<T>builder()
                .status(code)
                .message(message)
                .data(data)
                .build();
    }


    /**
     * response fail
     *
     * @return ResponseResult
     */
    public static ResponseExtension fail() {
        return fail(ResponseHttpStatus.FAIL.getDescription());
    }

    /**
     * response fail
     *
     * @param message message
     * @return ResponseResult
     */
    public static ResponseExtension fail(String message) {
        return ResponseExtension.builder()
                .status(ResponseHttpStatus.FAIL.getCode())
                .message(message)
                .build();
    }

    /**
     * response fail
     *
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> ResponseExtension<T> fail(String message, T data) {
        return fail(ResponseHttpStatus.FAIL.getCode(), message, data);
    }

    /**
     * response fail
     *
     * @param code code
     * @return ResponseResult
     */
    public static ResponseExtension fail(Integer code) {
        return fail(code, ResponseHttpStatus.FAIL.getDescription());
    }

    /**
     * response fail
     *
     * @param code    code
     * @param message message
     * @return ResponseResult
     */
    public static ResponseExtension fail(Integer code, String message) {
        String status = String.valueOf(code);
        return fail(status, message);
    }

    /**
     * response fail
     *
     * @param code    code
     * @param message message
     * @return ResponseResult
     */
    public static ResponseExtension fail(String code, String message) {
        return ResponseExtension.builder()
                .status(code)
                .message(message)
                .build();
    }

    /**
     * response fail
     *
     * @param code    code
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> ResponseExtension<T> fail(String code, String message, T data) {
        return ResponseExtension.<T>builder()
                .status(code)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * response fail
     *
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T> ResponseExtension<T> fail(String message, T data) {
        return fail(ResponseHttpStatus.FAIL.getCode(), message, data);
    }

    /**
     * response fail
     *
     * @param code    code
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T> ResponseExtension<T> fail(String code, String message, T data) {
        return ResponseExtension.<T>builder()
                .status(code)
                .message(message)
                .data(data)
                .build();
    }
}
