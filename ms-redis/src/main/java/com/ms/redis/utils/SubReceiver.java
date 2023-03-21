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
    abstract void onMessage(String message);

    /**
     * 收到通道的消息之后执行的方法
     *
     * @param message 消息
     */
    public void receiveMessage(String message) {
        onMessage(message);
        latch.countDown();
    }
}
