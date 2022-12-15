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

    public static Client getClient() {
        return Client.getClient();
    }

    public static OkHttpClient getOkClient() {
        return getClient().getOkClient();
    }

    public static Client getClient(String host, Integer port, Proxy.Type proxyType) {
        return Client.getClient(host, port, proxyType);
    }

    public static OkHttpClient getOkClient(String host, Integer port, Proxy.Type proxyType) {
        return getClient(host, port, proxyType).getOkClient();
    }

    public OkHttp3Client client() {
        OkHttp3Factory factory = new OkHttp3Factory();
        return factory.getClient();
    }

    public OkHttp3Client client(String host, Integer port, Proxy.Type proxyType) {
        OkHttp3Factory factory = new OkHttp3Factory(host, port, proxyType);
        return factory.getClient();
    }

}
