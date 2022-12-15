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

/**
 * @author ms2297248353
 */
public class MultipartClient extends SendClient {

    protected MultipartClient(OkHttpClient client, BuildFactory buildFactory) {
        super(client, buildFactory);
    }

    /**
     * 表单请求
     * <p>
     * multipart/form-data 请求
     *
     * @param key
     * @param value
     * @return
     */
    public MultipartClient addMultipart(String key, String value) {
        buildFactory.addMultipart(key, value);
        return this;
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
        buildFactory.addMultipart(mediaType, key, file);
        return this;
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
        buildFactory.addMultipart(mediaType, key, fileName, file);
        return this;
    }
}
