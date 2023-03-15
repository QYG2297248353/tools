package com.ms.network.okhttp.factory;


import com.ms.network.okhttp.properties.OkHttpProperties;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

import java.io.File;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author ms2297248353
 */
public class OkHttpFactory {

    private OkHttpClient okHttpClient;

    private OkHttpClient.Builder builder;

    private OkHttpFactory() {
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

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
            ConnectionPool pool = new ConnectionPool(properties.getMaxIdleConnections(), properties.getKeepAliveDuration(), TimeUnit.MINUTES);
            builder.connectionPool(pool);
        }
        if (properties.getCacheEnable() != null && properties.getCacheEnable()) {
            File directory = new File(properties.getCachePath());
            long maxSize = properties.getCacheSize() * 1024 * 1024;
            Cache cache = new Cache(directory, maxSize);
            builder.cache(cache);
        }
        if (properties.getProxyEnable() != null && properties.getProxyEnable()) {
            builder.proxy(properties.getProxy());
            builder.proxyAuthenticator(properties.getProxyAuthenticator());
        } else {
            builder.proxy(Proxy.NO_PROXY);
        }
        if (properties.getDnsEnable() != null && properties.getDnsEnable()) {
            builder.dns(properties.getDns());
        }
        builder.setCookieJar$okhttp(CookieJar.NO_COOKIES);
        okHttpClient = builder.build();
        return okHttpClient;
    }

}
