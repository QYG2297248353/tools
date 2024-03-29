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

package com.ms.tools.network.okhttp.factory;


import com.ms.tools.network.NetUtils;
import com.ms.tools.network.okhttp.properties.OkHttpProperties;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public class OkHttpFactory {

    /**
     * 网络缓存时间为7天
     */
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     */
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
     */
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";
    private final Interceptor cacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (NetUtils.isNetworkConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        } else {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetUtils.isNetworkConnected()) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", CACHE_CONTROL_NETWORK)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                    .removeHeader("Pragma")
                    .build();
        }
    };
    private OkHttpClient okHttpClient;
    private OkHttpClient.Builder builder;

    /**
     * 获取构建对象
     *
     * @return 构建对象
     */
    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

    /**
     * 创建客户端
     *
     * @return 客户端
     */
    public OkHttpClient create() {
        builder = new OkHttpClient.Builder();
        okHttpClient = builder.build();
        return okHttpClient;
    }

    /**
     * 创建客户端
     *
     * @param properties 配置文件
     * @return 客户端
     */
    public OkHttpClient create(OkHttpProperties properties) {
        builder = new OkHttpClient.Builder();
        if (properties.getConnectTimeout() != null) {
            builder.connectTimeout(properties.getConnectTimeout(), TimeUnit.SECONDS);
        }
        if (properties.getReadTimeout() != null) {
            builder.readTimeout(properties.getReadTimeout(), TimeUnit.SECONDS);
        }
        if (properties.getWriteTimeout() != null) {
            builder.writeTimeout(properties.getWriteTimeout(), TimeUnit.SECONDS);
        }
        if (properties.getFollowRedirects() != null) {
            builder.followRedirects(properties.getFollowRedirects());
        }
        if (properties.getRetryOnConnectionFailure() != null) {
            builder.retryOnConnectionFailure(properties.getRetryOnConnectionFailure());
        }
        if (properties.getConnectionPoolEnable() != null && properties.getConnectionPoolEnable()) {
            ConnectionPool pool = new ConnectionPool(properties.getMaxIdleConnections(), properties.getKeepAliveDuration(), properties.getKeepAliveDurationTimeUnit());
            builder.connectionPool(pool);
        }
        if (properties.getCacheEnable() != null && properties.getCacheEnable()) {
            File directory = new File(properties.getCachePath());
            long maxSize = properties.getCacheSize() * 1024 * 1024;
            Cache cache = new Cache(directory, maxSize);
            builder.cache(cache);
            builder.addInterceptor(cacheControlInterceptor);
        }
        if (properties.getProxyEnable() != null && properties.getProxyEnable()) {
            builder.proxy(properties.getProxy());
            if (properties.getProxyUsername() != null) {
                Authenticator proxyAuthenticator = properties.getProxyAuthenticator();
                builder.proxyAuthenticator(proxyAuthenticator);
            }
        } else {
            builder.proxy(Proxy.NO_PROXY);
        }
        if (properties.getDnsEnable() != null && properties.getDnsEnable()) {
            builder.dns(properties.getDns());
        }
        if (properties.getLogEnable() != null && properties.getLogEnable()) {
            builder.addInterceptor(new OkHttpLogInterceptor(properties));
        }
        okHttpClient = builder.build();
        return okHttpClient;
    }

    private class OkHttpLogInterceptor implements Interceptor {
        private final Logger logger = Logger.getLogger("OkHttpLogInterceptor");
        private final OkHttpProperties properties;

        public OkHttpLogInterceptor(OkHttpProperties properties) {
            this.properties = properties;
        }

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();
            if (properties.getLogEnable()) {
                String uri = String.format("请求地址：%s", request.url());
                switch (properties.getLogLevel()) {
                    case "BASIC":
                        logger.info(uri);
                        break;
                    case "HEADERS":
                        logger.info(uri);
                        logger.info(String.format("请求头：%s", request.headers()));
                        break;
                    case "BODY":
                        logger.info(uri);
                        logger.info(String.format("请求头：%s", request.headers()));
                        logger.info(String.format("请求体：%s", request.body()));
                        break;
                    default:
                        break;
                }
            }
            Response response = chain.proceed(request);
            if (properties.getLogEnable()) {
                String status = String.format("响应码：%s", response.code());
                switch (properties.getLogLevel()) {
                    case "BASIC":
                        logger.info(status);
                        break;
                    case "HEADERS":
                        logger.info(status);
                        logger.info(String.format("响应头：%s", response.headers()));
                        break;
                    case "BODY":
                        logger.info(status);
                        logger.info(String.format("响应头：%s", response.headers()));
                        logger.info(String.format("响应体：%s", response.body()));
                        break;
                    default:
                        break;
                }
            }
            return response;
        }
    }
}
