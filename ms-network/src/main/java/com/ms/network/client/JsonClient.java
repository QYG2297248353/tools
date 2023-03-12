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

package com.ms.network.client;

import com.ms.network.factory.BuildFactory;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * @author ms2297248353
 */
public class JsonClient extends SendClient {

    protected JsonClient(OkHttpClient client, BuildFactory buildFactory) {
        super(client, buildFactory);
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
        buildFactory.addJsonBody(key, value);
        return this;
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
        buildFactory.addJsonBody(value);
        return this;
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
        buildFactory.addJsonBody(value);
        return this;
    }


}
