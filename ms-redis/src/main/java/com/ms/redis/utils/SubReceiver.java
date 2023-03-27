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

package com.ms.redis.utils;

import java.util.concurrent.CountDownLatch;

public abstract class SubReceiver {
    /**
     * 用于接收消息的计数器
     */
    private CountDownLatch latch;

    /**
     * 构造方法
     *
     * @param latch 计数器
     */
    protected SubReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 消息处理接口
     *
     * @param message 消息
     */
    public abstract void onMessage(String message);

    /**
     * 收到通道的消息之后执行的方法
     *
     * @param message 消息
     */
    protected void receiveMessage(String message) {
        onMessage(message);
        latch.countDown();
    }
}
