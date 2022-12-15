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

package com.ms.network.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Okhttp3 configuration
 *
 * @author ms2297248353
 */
@Getter
@Setter
@Component
public class OkHttpConfig {
    /**
     * 超时配置-连接时长
     * 默认值 15s
     */
    @Value("${ms.okhttp.timeout.connect:15}")
    private Integer connectTimeout;
    /**
     * 超时配置-写入时长
     * 默认值 20s
     */
    @Value("${ms.okhttp.timeout.write:20}")
    private Integer writeTimeout;
    /**
     * 超时配置-读取时长
     * 默认值 20s
     */
    @Value("${ms.okhttp.timeout.read:20}")
    private Integer readTimeout;
    /**
     * 超时配置-完整流程时长
     * 默认值 30s
     */
    @Value("${ms.okhttp.timeout.call:30}")
    private Integer callTimeout;
    /**
     * 缓存配置-是否开启缓存
     */
    @Value("${ms.okhttp.timeout.cache.enable:false}")
    private Boolean cacheEnable;
    /**
     * 缓存配置-缓存大小
     */
    @Value("${ms.okhttp.timeout.cache.size:10 * 1024 * 1024}")
    private Integer cacheSize;
    /**
     * 缓存配置-缓存目录
     * 默认根目录 /cache
     */
    @Value("${ms.okhttp.timeout.cache.directory:/cache")
    private String cacheDirectory;

    /**
     * 代理配置-是否开启代理
     */
    @Value("${ms.okhttp.timeout.proxy.enable:false}")
    private Boolean proxyEnable;
    /**
     * 代理配置-ip
     * 本机
     */
    @Value("${ms.okhttp.timeout.proxy.host:127.0.0.1")
    private String proxyHost;

    /**
     * 代理配置-ip
     * 本机
     */
    @Value("${ms.okhttp.timeout.proxy.port:7890")
    private Integer proxyPort;

    /**
     * 代理配置-类型
     * HTTP 默认
     * SOCKS
     * DIRECT 直连
     */
    @Value("${ms.okhttp.timeout.proxy.type:http")
    private String proxyType;

    /**
     * 代理配置-用户名
     */
    @Value("${ms.okhttp.timeout.proxy.username")
    private String proxyUsername;

    /**
     * 代理配置-鉴权密码
     */
    @Value("${ms.okhttp.timeout.proxy.password")
    private String proxyPassword;


    /**
     * 非注入式-配置初始化
     *
     * @return
     */
    public OkHttpConfig init() {
        connectTimeout = 15;
        writeTimeout = 20;
        readTimeout = 20;
        callTimeout = 20;
        cacheEnable = false;
        cacheSize = 10 * 1024 * 1024;
        cacheDirectory = "/cache";
        proxyEnable = false;
        proxyHost = "127.0.0.1";
        proxyPort = 7890;
        proxyType = "http";
        return this;
    }

    public void update(String host, Integer port, Proxy.Type type, String username, String password) {
        if (host != null && port != null && type != null) {
            proxyHost = host;
            proxyPort = port;
            proxyType = type.name();
        }
    }

    public Proxy.Type getProxyType() {
        proxyType = proxyType.toUpperCase();
        switch (proxyType) {
            case "SOCKS":
                return Proxy.Type.SOCKS;
            case "DIRECT":
                return Proxy.Type.DIRECT;
            case "HTTP":
            default:
                return Proxy.Type.HTTP;
        }
    }

    public InetSocketAddress getProxyHostPort() {
        // todo: 常用类型效验确实 IP以及端口效验
        return new InetSocketAddress(proxyHost, proxyPort);
    }
}
