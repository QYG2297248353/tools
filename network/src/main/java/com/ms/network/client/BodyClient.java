/*
 * @MS 2022-12-13
 *  Copyright (c) 2001-2023 萌森
 *  保留所有权利
 *  本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 *  Copyright (c) 2001-2023 Meng Sen
 *  All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.network.client;

import com.ms.network.factory.BuildFactory;
import okhttp3.OkHttpClient;

import java.io.File;
import java.util.Map;

/**
 * @author ms2297248353
 */
public class BodyClient extends SendClient {

    public BodyClient(OkHttpClient client, BuildFactory buildFactory) {
        super(client, buildFactory);
    }

    public BodyClient(Client client) {
        super(client.getOkClient(), client.getBuildFactory());
    }

    /**
     * Json请求
     * <p>
     * application/json 请求
     *
     * @param key   参数名
     * @param value 参数值
     * @return 构建
     */
    public JsonClient addJsonBody(String key, Object value) {
        this.buildFactory.addJsonBody(key, value);
        return new JsonClient(this.client, this.buildFactory);
    }

    /**
     * Json请求
     * <p>
     * application/json 请求
     *
     * @param value 参数值
     * @return 构建
     */
    public JsonClient addJsonBody(Map<String, Object> value) {
        this.buildFactory.addJsonBody(value);
        return new JsonClient(this.client, this.buildFactory);
    }

    /**
     * Json请求
     * <p>
     * application/json 请求
     *
     * @param value 参数值
     * @return 构建
     */
    public JsonClient addJsonBody(Object value) {
        this.buildFactory.addJsonBody(value);
        return new JsonClient(this.client, this.buildFactory);
    }

    /**
     * 表单请求
     * <p>
     * application/x-www-form-urlencoded 请求
     *
     * @param key   参数名
     * @param value 参数值
     * @return 构建
     */
    public FromBodyClient addFromBody(String key, String value) {
        this.buildFactory.addFromBody(key, value);
        return new FromBodyClient(this.client, this.buildFactory);
    }

    /**
     * 表单请求
     * <p>
     * multipart/form-data 请求
     *
     * @param key   参数名
     * @param value 参数值
     * @return 构建
     */
    public MultipartClient addMultipart(String key, String value) {
        this.buildFactory.addMultipart(key, value);
        return new MultipartClient(this.client, this.buildFactory);
    }

    /**
     * 表单请求
     * <p>
     * multipart/form-data 请求
     *
     * @param mediaType 文件类型
     * @param key       参数名
     * @param file      文件
     * @return 构建
     */
    public MultipartClient addMultipartFile(String mediaType, String key, File file) {
        this.buildFactory.addMultipart(mediaType, key, file);
        return new MultipartClient(this.client, this.buildFactory);
    }

    /**
     * 表单请求
     * <p>
     * multipart/form-data 请求
     *
     * @param mediaType 文件类型
     * @param key       参数名
     * @param fileName  文件名称
     * @param file      文件
     * @return 构建
     */
    public MultipartClient addMultipartFile(String mediaType, String key, String fileName, File file) {
        this.buildFactory.addMultipart(mediaType, key, fileName, file);
        return new MultipartClient(this.client, this.buildFactory);
    }


}
