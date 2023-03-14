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

package com.ms.network.factory;

import com.ms.network.client.OkHttp3Client;
import com.ms.network.config.OkHttpConfig;
import okhttp3.*;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Http(s) 请求客户端构建
 *
 * @author Ms
 */
public class OkHttp3Factory {
    private OkHttp3Client client;

    @Resource
    private OkHttpConfig config;

    /**
     * 初始化 Client
     * 开启 https
     */
    public OkHttp3Factory() {
        client = createClient();
    }


    /**
     * 初始化 Client
     * 开启 https
     * 无密码代理
     */
    public OkHttp3Factory(String host, Integer port, Proxy.Type proxyType) {
        if (proxyType != null && proxyType != Proxy.Type.DIRECT) {
            client = createClient(host, port, proxyType, null, null);
        } else {
            client = createClient();
        }
    }

    /**
     * 初始化 Client
     * 开启 https
     * 开启 代理
     */
    public OkHttp3Factory(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        if (proxyType != null && proxyType != Proxy.Type.DIRECT) {
            client = createClient(host, port, proxyType, username, password);
        } else {
            client = createClient();
        }
    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return 工厂
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    private static OkHttpClient.Builder createBuilder(OkHttpConfig httpConfig) {
        TrustManager[] trustManagers = buildTrustManagers();
        ConnectionPool pool = new ConnectionPool(5, 5, TimeUnit.MINUTES);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(httpConfig.getReadTimeout(), TimeUnit.SECONDS)
                .callTimeout(httpConfig.getCallTimeout(), TimeUnit.SECONDS)
                .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
                .hostnameVerifier((hostName, session) -> true)
                .retryOnConnectionFailure(true)
                .connectionPool(pool)
                .protocols(Arrays.asList(Protocol.HTTP_1_1, Protocol.HTTP_2))
                .retryOnConnectionFailure(true);
        if (Boolean.TRUE.equals(httpConfig.getCacheEnable())) {
            Cache cache = new Cache(new File(httpConfig.getCacheDirectory()), httpConfig.getCacheSize());
            builder.cache(cache);
        }
        if (Boolean.TRUE.equals(httpConfig.getProxyEnable())) {
            Proxy proxy = new Proxy(httpConfig.getProxyType(), httpConfig.getProxyHostPort());
            builder.proxy(proxy);
            String proxyUsername = httpConfig.getProxyUsername();
            String proxyPassword = httpConfig.getProxyPassword();
            if (proxyUsername != null && proxyPassword != null) {
                builder.proxyAuthenticator((route, response) -> {
                    String credential = Credentials.basic(proxyUsername, proxyPassword, StandardCharsets.UTF_8);
                    return response.request().newBuilder().header("Proxy-Authenticator", credential).build();
                });
            }
        }
        return builder;
    }

    public OkHttp3Client getClient() {
        if (client == null) {
            client = new OkHttp3Factory().getClient();
        }
        return client;
    }

    private OkHttp3Client createClient() {
        return createClient(null, null, null, null, null);
    }

    private OkHttp3Client createClient(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        synchronized (OkHttp3Factory.class) {
            if (client == null) {
                OkHttpConfig httpConfig = getConfig();
                httpConfig.update(host, port, proxyType, username, password);
                OkHttpClient.Builder builder = createBuilder(httpConfig);
                OkHttpClient httpClient = builder.build();
                client = new OkHttp3Client(httpClient);
            }
        }
        return client;
    }

    private OkHttpConfig getConfig() {
        if (config == null) {
            config = new OkHttpConfig().init();
        }
        return config;
    }
}
