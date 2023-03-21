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
