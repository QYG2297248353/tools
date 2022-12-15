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

package com.ms.network.core;

import com.ms.network.client.Client;
import com.ms.network.client.OkHttp3Client;
import com.ms.network.factory.OkHttp3Factory;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.net.Proxy;

/**
 * OkHttp3 请求客户端
 *
 * @author ms2297248353
 */
@Component
public class OkHttp3Utils {
    /**
     * 获取简单客户端
     * <p>
     * 单例对象-如先创建代理客户端此时返回代理简单客户端
     *
     * @return 客户端
     */
    public static Client getClient() {
        return Client.getClient();
    }

    /**
     * 获取请求客户端
     * <p>
     * 单例对象-如先创建代理客户端此时返回代理简单客户端
     * 获取单例请求客户端
     *
     * @return 客户端
     */
    public static OkHttpClient getOkClient() {
        return getClient().getOkClient();
    }

    /**
     * 获取简单代理客户端
     * <p>
     * 单例对象-如先创建简单客户端此时返回简单客户端
     *
     * @param host      主机IP
     * @param port      代理端口
     * @param proxyType 连接类型
     * @return 代理客户端
     */
    public static Client getClient(String host, Integer port, Proxy.Type proxyType) {
        return Client.getClient(host, port, proxyType);
    }

    /**
     * 获取代理请求客户端
     * <p>
     * 单例对象-如先创建简单客户端此时返回简单客户端
     * 获取单例请求客户端
     *
     * @param host      主机IP
     * @param port      代理端口
     * @param proxyType 连接类型
     * @return 代理客户端
     */
    public static OkHttpClient getOkClient(String host, Integer port, Proxy.Type proxyType) {
        return getClient(host, port, proxyType).getOkClient();
    }

    /**
     * 获取简单代理客户端
     * <p>
     * 单例对象-如先创建简单客户端此时返回简单客户端
     * 获取单例请求客户端
     *
     * @param host      主机IP
     * @param port      代理端口
     * @param proxyType 连接类型
     * @param username  用户名
     * @param password  密码
     * @return 代理客户端
     */
    public static Client getClient(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        return Client.getClient(host, port, proxyType, username, password);
    }

    /**
     * 获取代理请求客户端
     * <p>
     * 单例对象-如先创建简单客户端此时返回简单客户端
     * 获取单例请求客户端
     *
     * @param host      主机IP
     * @param port      代理端口
     * @param proxyType 连接类型
     * @param username  用户名
     * @param password  密码
     * @return 代理客户端
     */
    public static OkHttpClient getOkClient(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        return getClient(host, port, proxyType, username, password).getOkClient();
    }


    /**
     * 获取请求客户端
     * <p>
     * 非单例对象,请自行维护
     *
     * @return 客户端
     */
    public static OkHttp3Client client() {
        OkHttp3Factory factory = new OkHttp3Factory();
        return factory.getClient();
    }

    /**
     * 获取代理请求客户端
     * <p>
     * 非单例对象,请自行维护
     *
     * @param host      主机IP
     * @param port      代理端口
     * @param proxyType 连接类型
     * @return 代理客户端
     */
    public static OkHttp3Client client(String host, Integer port, Proxy.Type proxyType) {
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType);
        return factory.getClient();
    }

    /**
     * 获取代理请求客户端
     * <p>
     * 非单例对象,请自行维护
     *
     * @param host      主机IP
     * @param port      代理端口
     * @param proxyType 连接类型
     * @param username  用户名
     * @param password  密码
     * @return 代理客户端
     */
    public static OkHttp3Client client(String host, Integer port, Proxy.Type proxyType, String username, String password) {
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType, username, password);
        return factory.getClient();
    }
}
