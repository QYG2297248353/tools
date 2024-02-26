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

package com.ms.tools.minio.exception;

import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * 异常整合
 *
 * @author ms
 */
public class MsMinioException extends Exception {

    private static final long serialVersionUID = 555427034214631148L;
    static Logger log = Logger.getLogger(MsMinioException.class.getName());

    public MsMinioException() {
        super();
    }

    public MsMinioException(String message) {
        super(message);
    }

    public MsMinioException(String message, Throwable cause) {
        super(message, cause);
    }

    public MsMinioException(Throwable cause) {
        /**
         * ErrorResponseException 错误响应异常
         *
         * Thrown to indicate S3 service returned an error response.
         * 抛出该异常表示 S3 服务返回了错误响应。
         *
         * IllegalArgumentException 非法参数异常
         *
         * Throws to indicate invalid argument passed.
         * 抛出异常以指示传递的参数无效。
         *
         * InsufficientDataException
         * 数据不足异常
         *
         * Thrown to indicate not enough data available in InputStream.
         * 抛出该异常表示 InputStream 中没有足够的可用数据。
         *
         * InternalException 内部异常
         *
         * Thrown to indicate internal library error.
         * 抛出该异常表示内部库错误。
         *
         * InvalidKeyException 无效密钥异常
         *
         * Thrown to indicate missing of HMAC SHA-256 library.
         * 抛出该异常表示缺少 HMAC SHA-256 库。
         *
         * InvalidResponseException 无效响应异常
         *
         * Thrown to indicate S3 service returned invalid or no error response.
         * 抛出该异常表示 S3 服务返回无效或无错误响应。
         *
         * IOException IO异常
         *
         * Thrown to indicate I/O error on S3 operation.
         * 抛出该异常表示 S3 操作上发生 I/O 错误。
         *
         * NoSuchAlgorithmException 没有这样的算法异常
         *
         * Thrown to indicate missing of MD5 or SHA-256 digest library.
         * 抛出该异常表示缺少 MD5 或 SHA-256 摘要库。
         *
         * ServerException 服务器异常
         *
         * Thrown to indicate HTTP server error.
         * 抛出该异常表示 HTTP 服务器错误。
         *
         * XmlParserException
         *
         * Thrown to indicate XML parsing error.
         * 抛出该异常表示 XML 解析错误。
         */
        if (cause instanceof ErrorResponseException) {
            ErrorResponseException e = (ErrorResponseException) cause;
            log.severe("[错误响应] Error response: " + e.errorResponse().toString());
        } else if (cause instanceof InsufficientDataException) {
            log.severe("[非法参数] illegal parameter error: " + cause);
        } else if (cause instanceof IllegalArgumentException) {
            log.severe("[数据不足] Insufficient data error: " + cause);
        } else if (cause instanceof InternalException) {
            log.severe("[内部异常] Internal anomaly: " + cause);
        } else if (cause instanceof InvalidKeyException) {
            log.severe("[无效密钥] Invalid key error: " + cause);
        } else if (cause instanceof InvalidResponseException) {
            log.severe("[无效响应] Invalid response error: " + cause);
        } else if (cause instanceof IOException) {
            log.severe("[读写异常] I/O error: " + cause);
        } else if (cause instanceof NoSuchAlgorithmException) {
            log.severe("[未知算法] Unknown algorithm error: " + cause);
        } else if (cause instanceof ServerException) {
            log.severe("[服务器异常] server error: " + cause);
        } else if (cause instanceof XmlParserException) {
            log.severe("[XML解析异常] XML parser error: " + cause);
        } else {
            log.severe("[Minio未知异常] Unknown error: " + cause);
        }
    }
}
