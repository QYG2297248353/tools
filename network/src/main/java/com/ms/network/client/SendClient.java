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
import okhttp3.OkHttpClient;

/**
 * @author ms2297248353
 */
public class SendClient {
    protected BuildFactory buildFactory;
    protected OkHttpClient client;

    public SendClient(OkHttpClient client, BuildFactory buildFactory) {
        this.client = client;
        this.buildFactory = buildFactory;
    }

    /**
     * 请求方式-GET
     *
     * @return 构建完成 sync()发送请求
     */
    public GetClient get() {
        buildFactory.get();
        return new GetClient(client, buildFactory);
    }

    /**
     * 请求方式-POST
     *
     * @return 构建完成 sync()发送请求
     */
    public PostClient post() {
        buildFactory.post();
        return new PostClient(client, buildFactory);
    }

    /**
     * 请求方式-PUT
     *
     * @return 构建完成 sync()发送请求
     */
    public PutClient put() {
        buildFactory.put();
        return new PutClient(client, buildFactory);
    }

    /**
     * 请求方式-DELETE
     *
     * @return 构建完成 sync()发送请求
     */
    public DeleteClient delete() {
        buildFactory.delete();
        return new DeleteClient(client, buildFactory);
    }
}
