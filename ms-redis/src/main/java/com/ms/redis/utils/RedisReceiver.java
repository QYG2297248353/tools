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
