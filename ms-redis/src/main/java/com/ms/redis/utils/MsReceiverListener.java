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

/**
 * 用于接收消息
 * <p>
 * 不推荐使用
 * 如果需要使用 请配置 CountDownLatch 计数器
 * 保障线程安全
 *
 * @author ms2297248353
 */
public abstract class MsReceiverListener {

    /**
     * 收到通道的消息之后执行的方法
     *
     * @param message 消息
     */
    public abstract void onMessage(String message);
}
