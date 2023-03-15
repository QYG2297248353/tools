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
     * 是否开启缓存
     * 默认开启
     */
    private Boolean cacheEnable = true;
    /**
     * 缓存大小
     * 默认10M
     */
    private Long cacheSize = 10L;
    /**
     * 缓存路径
     */
    private String cachePath = "okhttp/cache";

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

    public Proxy getProxy() {
        if (proxyHost == null || proxyPort == null) {
            throw new IllegalArgumentException("proxyHost or proxyPort is null");
        }
        return new Proxy(Proxy.Type.valueOf(proxyType), new java.net.InetSocketAddress(proxyHost, proxyPort));
    }

    public Authenticator getProxyAuthenticator() {
        return (route, response) -> {
            String credential = Credentials.basic(proxyUsername, proxyPassword);
            return response.request().newBuilder()
                    .header("Authorization", credential)
                    .build();
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
}
