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

package com.ms.network.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Okhttp3 configuration
 *
 * @author ms2297248353
 */
@Getter
@Setter
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ms.okhttp")
public class OkHttpConfig {
    /**
     * 超时配置-连接时长
     * 默认值 15s
     */
    @Value("${ms.okhttp.timeout.connect:30}")
    private Integer connectTimeout;
    /**
     * 超时配置-写入时长
     * 默认值 20s
     */
    @Value("${ms.okhttp.timeout.write:60}")
    private Integer writeTimeout;
    /**
     * 超时配置-读取时长
     * 默认值 20s
     */
    @Value("${ms.okhttp.timeout.read:60}")
    private Integer readTimeout;
    /**
     * 超时配置-完整流程时长
     * 默认值 30s
     */
    @Value("${ms.okhttp.timeout.call:120}")
    private Integer callTimeout;
    /**
     * 缓存配置-是否开启缓存
     */
    @Value("${ms.okhttp.cache.enable:false}")
    private Boolean cacheEnable;
    /**
     * 缓存配置-缓存大小
     */
    @Value("${ms.okhttp.cache.size:10485760}")
    private Long cacheSize;
    /**
     * 缓存配置-缓存目录
     * 默认关闭缓存
     */
    @Value("${ms.okhttp.cache.directory:null")
    private String cacheDirectory;

    /**
     * 代理配置-是否开启代理
     */
    @Value("${ms.okhttp.proxy.enable:false}")
    private Boolean proxyEnable;
    /**
     * 代理配置-ip
     * 本机
     */
    @Value("${ms.okhttp.proxy.host:127.0.0.1}")
    private String proxyHost;

    /**
     * 代理配置-ip
     * 本机
     */
    @Value("${ms.okhttp.proxy.port:7890}")
    private Integer proxyPort;

    /**
     * 代理配置-类型
     * HTTP 默认
     * SOCKS
     * DIRECT 直连
     */
    @Value("${ms.okhttp.proxy.type:http}")
    private String proxyType;

    /**
     * 代理配置-用户名
     */
    @Value("${ms.okhttp.proxy.username:null}")
    private String proxyUsername;

    /**
     * 代理配置-鉴权密码
     */
    @Value("${ms.okhttp.proxy.password:null}")
    private String proxyPassword;


    public OkHttpConfig() {
        init();
    }

    /**
     * 非注入式-配置初始化
     *
     * @return
     */
    public OkHttpConfig init() {
        connectTimeout = 30;
        writeTimeout = 60;
        readTimeout = 60;
        callTimeout = 120;
        cacheEnable = false;
        cacheSize = 10L * 1024 * 1024;
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

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public InetSocketAddress getProxyHostPort() {
        // todo: 常用类型效验确实 IP以及端口效验
        return new InetSocketAddress(proxyHost, proxyPort);
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Integer writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Integer getCallTimeout() {
        return callTimeout;
    }

    public void setCallTimeout(Integer callTimeout) {
        this.callTimeout = callTimeout;
    }

    public Boolean getCacheEnable() {
        return cacheEnable;
    }

    public void setCacheEnable(Boolean cacheEnable) {
        this.cacheEnable = cacheEnable;
    }

    public Long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public String getCacheDirectory() {
        return cacheDirectory;
    }

    public void setCacheDirectory(String cacheDirectory) {
        this.cacheDirectory = cacheDirectory;
    }

    public Boolean getProxyEnable() {
        return proxyEnable;
    }

    public void setProxyEnable(Boolean proxyEnable) {
        this.proxyEnable = proxyEnable;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
}
