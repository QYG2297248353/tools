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

import com.ms.exception.web.MsSendRequestException;
import com.ms.network.factory.BuildFactory;
import com.ms.network.interfaces.RequestCallBack;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public class RequestClient {
    private static final Logger LOGGER = Logger.getLogger(RequestClient.class.getName());

    private final OkHttpClient client;

    private final BuildFactory buildFactory;

    protected RequestClient(OkHttpClient client, BuildFactory buildFactory) {
        this.client = client;
        this.buildFactory = buildFactory;
    }

    /**
     * 发送请求-同步
     *
     * @return 响应对象
     * @throws MsSendRequestException 请求发送失败
     */
    public Response sync() throws MsSendRequestException {
        try {
            return client.newCall(buildFactory.build()).execute();
        } catch (IOException e) {
            throw new MsSendRequestException("请求发送异常,请检查您的网络", e);
        }
    }

    /**
     * 发送请求-异步
     * 忽略响应结果
     */
    public void async() {
        client.newCall(buildFactory.build()).enqueue(new RequestCallBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                RequestClient.LOGGER.warning("Failed to execute request, 忽略执行响应");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RequestClient.LOGGER.warning("Success to execute request, 忽略执行响应");
            }
        });
    }

    /**
     * 发送请求-异步
     *
     * @param callback 回调函数
     */
    public void async(RequestCallBack callback) {
        client.newCall(buildFactory.build()).enqueue(callback);
    }
}
