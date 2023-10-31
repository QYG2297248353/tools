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

package com.ms.tools.redis.listener;

import java.util.concurrent.CountDownLatch;

/**
 * @author ms2297248353
 */
public abstract class AbstractSubReceiver {
    /**
     * 用于接收消息的计数器
     */
    private final CountDownLatch latch;

    protected AbstractSubReceiver() {
        latch = new CountDownLatch(1);
    }

    /**
     * 消息处理接口
     *
     * @param message 消息
     */
    public abstract void onMessage(String message);
}
