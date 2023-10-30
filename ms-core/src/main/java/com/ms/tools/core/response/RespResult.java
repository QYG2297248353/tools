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

import java.io.Serializable;

/**
 * JSON 统一响应格式
 * code: 200
 * msg: success
 *
 * @author ms2297248353
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RespResult<T> {
    /**
     * response code
     * 默认值
     * OK 200
     * Fail 400
     */
    private String code;

    /**
     * response message
     * 默认值
     * OK success
     * Fail failed
     */
    private String msg;

    /**
     * response data
     * null by default
     */
    private T result;

    /**
     * response timestamp
     * default value is System.currentTimeMillis()
     */
    @Builder.Default
    private long timestamp = System.currentTimeMillis();

    /**
     * response success
     *
     * @return ResponseResult
     */
    public static RespResult success() {
        return success(ResponseHttpStatus.SUCCESS.getDescription());
    }

    /**
     * response success
     *
     * @param message message
     * @return ResponseResult
     */
    public static RespResult success(String message) {
        return success(ResponseHttpStatus.SUCCESS.getCode(), message);
    }

    /**
     * response success
     *
     * @param code    code
     * @param message message
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> RespResult<T> success(String code, String message) {
        return RespResult.<T>builder()
                .code(code)
                .msg(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * response success
     *
     * @param data data
     * @param <T>  响应类
     * @return ResponseResult
     */
    public static <T extends Serializable> RespResult<T> success(T data) {
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
    public static <T extends Serializable> RespResult<T> success(String message, T data) {
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
    public static <T extends Serializable> RespResult<T> success(Integer code, String message, T data) {
        String status = String.valueOf(code);
        return success(status, message, data);
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
    public static <T extends Serializable> RespResult<T> success(String code, String message, T data) {
        return RespResult.<T>builder()
                .code(code)
                .msg(message)
                .result(data)
                .build();
    }

    /**
     * response success
     *
     * @param message message
     * @param data    data
     * @param <T>     响应类
     * @return ResponseResult
     */
    public static <T> RespResult<T> success(String message, T data) {
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
    public static <T> RespResult<T> success(String code, String message, T data) {
        return RespResult.<T>builder().result(data)
                .msg(message)
                .code(code)
                .build();
    }

    /**
     * response fail
     *
     * @return ResponseResult
     */
    public static RespResult fail() {
        return fail(ResponseHttpStatus.FAIL.getDescription());
    }

    /**
     * response fail
     *
     * @param message message
     * @return ResponseResult
     */
    public static RespResult fail(String message) {
        return RespResult.builder()
                .code(ResponseHttpStatus.FAIL.getCode())
                .msg(message)
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
    public static <T extends Serializable> RespResult<T> fail(String message, T data) {
        return fail(ResponseHttpStatus.FAIL.getCode(), message, data);
    }

    /**
     * response fail
     *
     * @param code code
     * @return ResponseResult
     */
    public static RespResult fail(Integer code) {
        return fail(code, ResponseHttpStatus.FAIL.getDescription());
    }

    /**
     * response fail
     *
     * @param code    code
     * @param message message
     * @return ResponseResult
     */
    public static RespResult fail(Integer code, String message) {
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
    public static RespResult fail(String code, String message) {
        return RespResult.builder()
                .code(code)
                .msg(message)
                .timestamp(System.currentTimeMillis())
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
    public static <T extends Serializable> RespResult<T> fail(String code, String message, T data) {
        return RespResult.<T>builder()
                .code(code)
                .msg(message)
                .result(data)
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
    public static <T> RespResult<T> fail(String message, T data) {
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
    public static <T> RespResult<T> fail(String code, String message, T data) {
        return RespResult.<T>builder()
                .code(code)
                .msg(message)
                .result(data)
                .build();
    }

}
