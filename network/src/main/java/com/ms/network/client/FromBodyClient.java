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

public class FromBodyClient extends SendClient {
    protected FromBodyClient(OkHttpClient client, BuildFactory buildFactory) {
        super(client, buildFactory);
    }

    /**
     * 表单请求
     * <p>
     * application/x-www-form-urlencoded 请求
     *
     * @param key   参数名
     * @param value 参数值
     * @return 构建
     */
    public FromBodyClient addFromBody(String key, String value) {
        buildFactory.addFromBody(key, value);
        return this;
    }
}
