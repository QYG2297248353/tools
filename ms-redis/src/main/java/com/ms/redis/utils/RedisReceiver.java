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
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public class RedisReceiver extends SubReceiver {
    private static final Logger logger = Logger.getLogger(RedisReceiver.class.getName());

    public RedisReceiver(CountDownLatch latch) {
        super(latch);
    }

    /**
     * 消息处理
     *
     * @param message 消息
     */
    @Override
    void onMessage(String message) {
        logger.warning("请重新配置监听器或重新实现方法");
    }

}
