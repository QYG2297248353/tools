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

package com.ms.network.factory;

import com.alibaba.fastjson2.JSON;
import com.ms.core.base.basic.Strings;
import com.ms.core.base.enums.request.MediaTypeEnum;
import com.ms.core.base.enums.request.RequestBodyTypeEnum;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 请求构建
 *
 * @author ms2297248353
 */
public class BuildFactory {
    private static final Logger LOGGER = Logger.getLogger(BuildFactory.class.getName());

    private static final String BASE_HEADER_KEY = "User-Agent";
    private static final String BASE_HEADER_VALUE = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    private static MultipartBody.Builder multipartBuilder;
    private static FormBody.Builder formBuilder;
    private Map<String, String> headers;
    private Map<String, String> urlParams;
    private Map<String, Object> jsonBody;
    private String jsonBodyStr;
    private String url;
    private Request.Builder requestBuilder;

    private RequestBodyTypeEnum requestBodyType;

    public BuildFactory() {
        headers = new HashMap<>();
        jsonBody = new HashMap<>();
        urlParams = new HashMap<>();
        url = Strings.EMPTY;
        multipartBuilder = new MultipartBody.Builder();
        formBuilder = new FormBody.Builder();
        requestBuilder = new Request.Builder();
        requestBodyType = RequestBodyTypeEnum.NONE;
    }

    /**
     * 自定义请求
     *
     * @param mediaType 类型
     * @param str       文本
     * @return 请求体
     */
    private static RequestBody textRequest(MediaType mediaType, String str) {
        return RequestBody.create(str, mediaType);
    }

    /**
     * 二进制请求流
     * 快速构建
     *
     * @param mediaType 类型
     * @param stream    流
     * @return 请求体
     */
    private static RequestBody streamingRequest(MediaType mediaType, InputStream stream) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                try (Source source = Okio.source(stream)) {
                    bufferedSink.writeAll(source);
                }
            }
        };
    }

    /**
     * 文件请求流
     * 快速构建
     *
     * @param mediaType 类型
     * @param stream    文件
     * @return 请求体
     */
    private static RequestBody streamingRequest(MediaType mediaType, File stream) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                try (Source source = Okio.source(stream)) {
                    bufferedSink.writeAll(source);
                }
            }
        };
    }

    /**
     * Socket请求流
     * 快速构建
     *
     * @param mediaType 类型
     * @param stream    Socket
     * @return 请求体
     */
    private static RequestBody streamingRequest(MediaType mediaType, Socket stream) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                try (Source source = Okio.source(stream)) {
                    bufferedSink.writeAll(source);
                }
            }
        };
    }

    /**
     * 文件请求
     * 快速构建
     *
     * @param mediaType 类型
     * @param file      文件
     * @return 请求体
     */
    private static RequestBody fileRequest(MediaType mediaType, File file) {
        return RequestBody.create(file, mediaType);
    }

    /**
     * 构建From请求
     *
     * @param key   参数名
     * @param value 参数值
     * @return 请求体
     */
    private FormBody.Builder formBody(String key, String value) {
        requestBodyType = RequestBodyTypeEnum.X_WWW_FORM_URLENCODED;
        formBuilder.add(key, value);
        return formBuilder;
    }

    /**
     * 构建multipartFrom 文件请求
     *
     * @param key   参数名
     * @param value 文件名 允许为null
     * @param file  文件
     * @return 请求体
     */
    private MultipartBody.Builder multipartFromFile(MediaType mediaType, String key, String value, File file) {
        requestBodyType = RequestBodyTypeEnum.FORM_DATA;
        multipartBuilder.setType(MultipartBody.FORM);
        if (value == null) {
            value = file.getName();
        }
        multipartBuilder.addFormDataPart(key, value, RequestBody.create(file, mediaType));
        return multipartBuilder;
    }

    /**
     * 构建multipartFrom 请求
     *
     * @param key   参数名
     * @param value 文件名
     * @return 请求体
     */
    private MultipartBody.Builder multipartFrom(String key, String value) {
        requestBodyType = RequestBodyTypeEnum.FORM_DATA;
        multipartBuilder.setType(MultipartBody.FORM);
        multipartBuilder.addFormDataPart(key, value);
        return multipartBuilder;
    }

    /**
     * Uri参数处理
     */
    private void urlParams() {
        StringBuilder urlBuilder = new StringBuilder(url);
        if (!urlParams.isEmpty()) {
            urlBuilder.append(Strings.QUESTION_MARK);
            try {
                for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), Strings.UTF_8)).append(Strings.EQUALS).append(URLEncoder.encode(entry.getValue(), Strings.UTF_8)).append(Strings.AND);
                }
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
                return;
            }
            try {
                urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            } catch (StringIndexOutOfBoundsException e) {
                LOGGER.warning("参数异常,抛弃参数");
                return;
            }
            url = urlBuilder.toString();
        }
    }

    private void buildRequest() {
        requestBuilder.url(url).header(BASE_HEADER_KEY, BASE_HEADER_VALUE);
        if (!headers.isEmpty()) {
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        }
    }

    public void setUrl(String url) {
        this.url = url.trim();
        if (!url.toLowerCase().startsWith(Strings.HTTP)) {
            this.url = Strings.HTTP_HEAD + url;
        }
    }

    public BuildFactory addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public BuildFactory addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public BuildFactory addUrlParam(String key, Integer value) {
        String param = String.valueOf(value);
        addUrlParam(key, param);
        return this;
    }

    public BuildFactory addUrlParam(String key, String value) {
        urlParams.put(key, value);
        return this;
    }

    public BuildFactory addUrlParam(String key, Boolean value) {
        String param = String.valueOf(value);
        addUrlParam(key, param);
        return this;
    }

    public BuildFactory addJsonBody(String key, Object value) {
        requestBodyType = RequestBodyTypeEnum.JSON;
        jsonBody.put(key, value);
        return this;
    }

    public BuildFactory addJsonBody(Object obj) {
        requestBodyType = RequestBodyTypeEnum.JSON;
        jsonBodyStr = JSON.toJSONString(obj);
        return this;
    }

    public BuildFactory addJsonBody(String obj) {
        if (JSON.isValid(obj)) {
            jsonBodyStr = obj;
            requestBodyType = RequestBodyTypeEnum.JSON;
        }
        return this;
    }

    public BuildFactory addJsonBody(Map<String, Object> maps) {
        requestBodyType = RequestBodyTypeEnum.JSON;
        jsonBody.putAll(maps);
        return this;
    }

    public BuildFactory addFromBody(String key, String value) {
        formBody(key, value);
        return this;
    }

    public FormBody fromBodyBuild() {
        return formBuilder.build();
    }

    public BuildFactory addMultipart(String key, String value) {
        multipartFrom(key, value);
        return this;
    }

    public BuildFactory addMultipart(String mediaType, String key, File value) {
        MediaType parse = MediaType.parse(mediaType);
        multipartFromFile(parse, key, null, value);
        return this;
    }

    public BuildFactory addMultipart(String mediaType, String key, String fileName, File value) {
        MediaType parse = MediaType.parse(mediaType);
        multipartFromFile(parse, key, fileName, value);
        return this;
    }

    public BuildFactory addMultipart(MediaTypeEnum mediaType, String key, String fileName, File value) {
        MediaType parse = MediaType.parse(mediaType.getType());
        multipartFromFile(parse, key, fileName, value);
        return this;
    }

    public MultipartBody multipartBuild() {
        return multipartBuilder.build();
    }

    private void init() {
        jsonBody.clear();
        headers.clear();
    }


    public BuildFactory get() {
        urlParams();
        buildRequest();
        requestBuilder.get();
        return this;
    }


    public BuildFactory post() {
        urlParams();
        buildRequest();
        RequestBody requestBody = buildRequestBody();
        assert requestBody != null;
        requestBuilder.post(requestBody);
        return this;
    }

    public BuildFactory put() {
        urlParams();
        buildRequest();
        RequestBody requestBody = buildRequestBody();
        assert requestBody != null;
        requestBuilder.put(requestBody);
        return this;
    }

    public BuildFactory delete() {
        urlParams();
        buildRequest();
        if (requestBodyType == RequestBodyTypeEnum.NONE) {
            requestBuilder.delete();
        } else {
            RequestBody requestBody = buildRequestBody();
            requestBuilder.delete(requestBody);
        }
        return this;
    }

    private RequestBody buildRequestBody() {
        switch (requestBodyType) {
            case JSON:
                if (jsonBodyStr == null && !jsonBody.isEmpty()) {
                    jsonBodyStr = JSON.toJSONString(jsonBody);
                }
                MediaType parse = MediaType.parse(requestBodyType.getType().getType());
                return RequestBody.create(jsonBodyStr, parse);
            case FORM_DATA:
                return multipartBuild();
            case X_WWW_FORM_URLENCODED:
                return fromBodyBuild();
            case NONE:
            default:
                return null;
        }
    }

    public Request build() {
        return requestBuilder.build();
    }
}
