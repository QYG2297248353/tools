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

package com.ms.network.okhttp.properties;

import com.ms.core.base.basic.Lists;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Dns;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ms2297248353
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ms.network.okhttp")
public class OkHttpProperties {
    /**
     * 连接超时时间
     * 默认10秒
     */
    private Integer connectTimeout = 10;
    /**
     * 读取超时时间
     * 默认30秒
     */
    private Integer readTimeout = 30;
    /**
     * 写入超时时间
     * 默认30秒
     */
    private Integer writeTimeout = 30;

    /**
     * 是否自动重定向
     * 默认开启
     */
    private Boolean followRedirects = true;
    /**
     * 是否开启重试
     * 默认开启
     */
    private Boolean retryOnConnectionFailure = true;


    /**
     * 是否开启连接池
     */
    private Boolean connectionPoolEnable = true;
    /**
     * 最大空闲连接数
     * 连接池大小
     * 默认30个
     */
    private Integer maxIdleConnections = 30;
    /**
     * 空闲连接存活时间
     * 默认5分钟
     */
    private Long keepAliveDuration = 5L;

    /**
     * 空闲连接存活时间单位
     * 默认分钟
     */
    private String keepAliveDurationTimeUnit = "MINUTES";

    /**
     * 是否开启缓存
     * 默认开启
     */
    private Boolean cacheEnable = true;
    /**
     * 缓存大小 单位 MB
     * 默认1G
     */
    private Long cacheSize = 1024L;
    /**
     * 缓存路径
     */
    private String cachePath = "./okhttp/cache";

    /**
     * 是否开启代理
     * 默认关闭
     */
    private Boolean proxyEnable = false;
    /**
     * 代理类型
     * 默认HTTP
     * <p>
     * HTTP,HTTPS,SOCKS
     * HTTP:HTTP代理
     * HTTPS:HTTPS代理
     * SOCKS:SOCKS代理
     */
    private String proxyType = "HTTP";
    /**
     * 代理地址
     */
    private String proxyHost;
    /**
     * 代理端口
     */
    private Integer proxyPort;
    /**
     * 代理用户名
     */
    private String proxyUsername;
    /**
     * 代理密码
     */
    private String proxyPassword;

    /**
     * 是否使用自定义DNS
     * 默认关闭
     */
    private Boolean dnsEnable = false;

    /**
     * 自定义DNS
     */
    private String[] dns = new String[]{"8.8.8.8", "114.114.114.114"};


    /**
     * 是否开启日志
     * 默认开启
     */
    private Boolean logEnable = true;
    /**
     * 日志级别
     * 默认BASIC
     * <p>
     * NONE,BASIC,HEADERS,BODY 4种
     * NONE:不打印日志
     * BASIC:请求/响应行
     * HEADERS:请求/响应行 + 头
     * BODY:请求/响应行 + 头 + 体
     */
    private String logLevel = "BASIC";

    public OkHttpProperties() {
    }

    /**
     * 初始化
     *
     * @param connectTimeout           连接超时时间 s秒
     * @param readTimeout              读取超时时间 s秒
     * @param writeTimeout             写入超时时间 s秒
     * @param followRedirects          是否自动重定向
     * @param retryOnConnectionFailure 是否开启重试
     */
    public OkHttpProperties(Integer connectTimeout, Integer readTimeout, Integer writeTimeout, Boolean followRedirects, Boolean retryOnConnectionFailure) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        this.followRedirects = followRedirects;
        this.retryOnConnectionFailure = retryOnConnectionFailure;
    }

    /**
     * 关闭日志
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties closeLog() {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.logEnable = false;
        return okHttpProperties;
    }

    /**
     * 关闭缓存
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties closeCache() {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.cacheEnable = false;
        return okHttpProperties;
    }

    /**
     * 关闭连接池
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties closeConnectionPool() {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.connectionPoolEnable = false;
        return okHttpProperties;
    }

    /**
     * 开启代理
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties openProxy(String host, int port) {
        return openProxy(Proxy.Type.HTTP, host, port);
    }

    /**
     * 开启代理
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties openProxy(Proxy.Type type, String host, int port) {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.proxyEnable = true;
        okHttpProperties.proxyType = type.name();
        okHttpProperties.proxyHost = host;
        okHttpProperties.proxyPort = port;
        return okHttpProperties;
    }

    /**
     * 开启dns
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties openDns() {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.dnsEnable = true;
        return okHttpProperties;
    }

    /**
     * 开启dns
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties openDns(String... dns) {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.dnsEnable = true;
        okHttpProperties.dns = dns;
        return okHttpProperties;
    }

    /**
     * 开启线程池
     *
     * @return OkHttpProperties
     */
    public static OkHttpProperties openThreadPool(int maxIdleConnections, long keepAliveDuration, TimeUnit timeUnit) {
        OkHttpProperties okHttpProperties = new OkHttpProperties();
        okHttpProperties.connectionPoolEnable = true;
        okHttpProperties.maxIdleConnections = maxIdleConnections;
        okHttpProperties.keepAliveDuration = keepAliveDuration;
        okHttpProperties.keepAliveDurationTimeUnit = timeUnit.name();
        return okHttpProperties;
    }

    public Proxy getProxy() {
        if (proxyHost == null || proxyPort == null) {
            throw new IllegalArgumentException("proxyHost or proxyPort is null");
        }
        return new Proxy(Proxy.Type.valueOf(proxyType), new java.net.InetSocketAddress(proxyHost, proxyPort));
    }

    public Authenticator getProxyAuthenticator() {
        return (route, response) -> {
            String credential = Credentials.basic(proxyUsername, proxyPassword);
            return response.request().newBuilder().header("Authorization", credential).build();
        };
    }

    public Dns getDns() {
        if (Lists.isEmpty(dns)) {
            throw new IllegalArgumentException("dns is null");
        }
        return s -> {
            List inetAddresses = Lists.EMPTY_LIST;
            for (String d : dns) {
                inetAddresses = Arrays.asList(InetAddress.getAllByName(d));
                if (!inetAddresses.isEmpty()) {
                    break;
                }
            }
            return inetAddresses;
        };
    }

    public OkHttpProperties setProxy(String host, int port) {
        proxyEnable = true;
        proxyHost = host;
        proxyPort = port;
        return this;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public Boolean getFollowRedirects() {
        return followRedirects;
    }

    public Boolean getRetryOnConnectionFailure() {
        return retryOnConnectionFailure;
    }

    public Boolean getConnectionPoolEnable() {
        return connectionPoolEnable;
    }

    public Integer getMaxIdleConnections() {
        return maxIdleConnections;
    }

    public Long getKeepAliveDuration() {
        return keepAliveDuration;
    }

    public Boolean getCacheEnable() {
        return cacheEnable;
    }

    public Long getCacheSize() {
        return cacheSize;
    }

    public String getCachePath() {
        return cachePath;
    }

    public Boolean getProxyEnable() {
        return proxyEnable;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(Proxy.Type proxyType) {
        this.proxyType = proxyType.name();
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public Boolean getDnsEnable() {
        return dnsEnable;
    }

    public Boolean getLogEnable() {
        return logEnable;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public TimeUnit getKeepAliveDurationTimeUnit() {
        return TimeUnit.valueOf(keepAliveDurationTimeUnit.toUpperCase());
    }
}
