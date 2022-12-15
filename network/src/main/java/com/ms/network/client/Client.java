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

package com.ms.network.client;


import com.ms.network.factory.BuildFactory;
import com.ms.network.factory.OkHttp3Factory;
import okhttp3.OkHttpClient;

import java.net.Proxy;
import java.util.Map;

/**
 * 单例维护 OkHttpClient
 *
 * @author ms2297248353
 */
public class Client {
    private static volatile Client client = null;

    private static OkHttpClient okClient = null;
    private final BuildFactory buildFactory;

    private Client() {
        this.buildFactory = new BuildFactory();
        OkHttp3Factory factory = new OkHttp3Factory();
        okClient = factory.getClient().getOkHttpClient();
    }

    private Client(String host, Integer port, Proxy.Type proxyType) {
        this.buildFactory = new BuildFactory();
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType);
        okClient = factory.getClient().getOkHttpClient();
    }

    private Client(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        this.buildFactory = new BuildFactory();
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType, username, password);
        okClient = factory.getClient().getOkHttpClient();
    }

    /**
     * 构建单例对象
     *
     * @return 单例对象
     */
    public static Client getClient() {
        if (client == null) {
            synchronized (Client.class) {
                if (client == null) {
                    client = new Client();
                }
            }
        }
        return client;
    }

    /**
     * 构建单例对象
     * <strong>Note: 不推荐使用，SpringBoot因采用配置文件注入配置</strong>
     *
     * @param host      IP
     * @param port      端口
     * @param proxyType 代理类型
     * @return 单例对象
     */
    public static Client getClient(String host, Integer port, Proxy.Type proxyType) {
        if (client == null) {
            synchronized (Client.class) {
                if (client == null) {
                    client = new Client(host, port, proxyType);
                }
            }
        }
        return client;
    }

    /**
     * 构建单例对象
     * <strong>Note: 不推荐使用，SpringBoot因采用配置文件注入配置</strong>
     *
     * @param host      IP
     * @param port      端口
     * @param proxyType 代理类型
     * @param username  鉴权账户
     * @param password  鉴权密码
     * @return 单例对象
     */
    public static Client getClient(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        if (client == null) {
            synchronized (Client.class) {
                if (client == null) {
                    client = new Client(host, port, proxyType, username, password);
                }
            }
        }
        return client;
    }

    /**
     * 获取自定义客户端
     *
     * @return 客户端
     */
    public OkHttpClient getOkClient() {
        if (okClient == null) {
            getClient();
        }
        return okClient;
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
        return new BodyClient(this);
    }

    /**
     * 添加请求头
     *
     * @param key   参数名
     * @param value 参数值
     * @return 构建
     */
    public Client addHeader(String key, String value) {
        this.buildFactory.addHeader(key, value);
        return this;
    }

    /**
     * 添加请求头
     *
     * @param headers 请求头队列
     * @return 构建
     */
    public Client addHeaders(Map<String, String> headers) {
        this.buildFactory.addHeaders(headers);
        return this;
    }
}
