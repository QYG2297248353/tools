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
import com.ms.network.factory.OkHttp3Factory;
import okhttp3.OkHttpClient;

import java.net.Proxy;
import java.util.Map;

/**
 * 自定义客户端
 *
 * @author ms2297248353
 */
public class OkHttp3Client {
    private final BuildFactory buildFactory;
    private final OkHttpClient okHttpClient;

    public OkHttp3Client(OkHttpClient okHttpClient) {
        this.buildFactory = new BuildFactory();
        this.okHttpClient = okHttpClient;
    }

    public OkHttp3Client() {
        this.buildFactory = new BuildFactory();
        OkHttp3Factory factory = new OkHttp3Factory();
        okHttpClient = factory.getClient().getOkHttpClient();
    }

    public OkHttp3Client(String host, Integer port, Proxy.Type proxyType) {
        this.buildFactory = new BuildFactory();
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType);
        okHttpClient = factory.getClient().getOkHttpClient();
    }

    public OkHttp3Client(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        this.buildFactory = new BuildFactory();
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType, username, password);
        okHttpClient = factory.getClient().getOkHttpClient();
    }

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    protected BuildFactory getBuildFactory() {
        return this.buildFactory;
    }

    /**
     * 请求域名
     *
     * @param url 域名
     * @return 构建
     */
    public BodyClient uri(String url) {
        this.buildFactory.setUrl(url);
        return new BodyClient(this.okHttpClient, this.buildFactory);
    }

    /**
     * 添加请求头
     *
     * @param key   参数名
     * @param value 参数值
     * @return 构建
     */
    public OkHttp3Client addHeader(String key, String value) {
        this.buildFactory.addHeader(key, value);
        return this;
    }

    /**
     * 添加请求头
     *
     * @param headers 请求头队列
     * @return 构建
     */
    public OkHttp3Client addHeaders(Map<String, String> headers) {
        this.buildFactory.addHeaders(headers);
        return this;
    }
}
