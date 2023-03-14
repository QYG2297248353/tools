package com.ms.network.okhttp.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "ms.network.okhttp")
public class OkHttpProperties {
    /**
     * 连接超时时间
     * 默认10秒
     */
    private int connectTimeout = 10;
    /**
     * 读取超时时间
     * 默认10秒
     */
    private int readTimeout = 10;
    /**
     * 写入超时时间
     * 默认10秒
     */
    private int writeTimeout = 10;
    /**
     * 最大空闲连接数
     * 默认5个
     */
    private int maxIdleConnections = 5;
    /**
     * 空闲连接存活时间
     * 默认5秒
     */
    private int keepAliveDuration = 5;
    /**
     * 是否开启重试
     * 默认开启
     */
    private boolean retryOnConnectionFailure = true;
    /**
     * 是否开启日志
     * 默认开启
     */
    private boolean logEnable = true;
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
}
