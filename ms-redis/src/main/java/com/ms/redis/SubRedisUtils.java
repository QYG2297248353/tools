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

package com.ms.redis;

import com.ms.redis.utils.MsReceiverListener;
import com.ms.redis.utils.RedisReceiverService;
import com.ms.redis.utils.SubReceiver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis 订阅客户端
 * 订阅者（Subscriber）可以根据自己的需要，订阅一个或多个频道（Channel）。
 *
 * @author ms2297248353
 */
@Component
public class SubRedisUtils {

    @Resource
    private RedisReceiverService redisReceiverService;

    /**
     * 配置订阅消息监听器
     *
     * @param listener 订阅消息监听器
     * @return 订阅成功返回true，否则返回false
     */
    public <T extends SubReceiver> boolean configureMessageListener(T listener) {
        return redisReceiverService.configureListener(listener);
    }

    /**
     * 配置订阅消息监听器
     *
     * @param listener 订阅消息监听器
     * @param <T>      对象
     * @return 订阅成功返回true，否则返回false
     */
    public <T extends MsReceiverListener> boolean configureMessageListener(T listener) {
        return redisReceiverService.configureListener(listener);
    }

    /**
     * 配置自定义订阅消息监听器
     *
     * @param listener   订阅消息监听器
     * @param methodName 方法名
     * @param <T>        对象
     * @return 订阅成功返回true，否则返回false
     */
    public <T extends SubReceiver> boolean configureMessageListener(T listener, String methodName) {
        return redisReceiverService.configureListener(listener, methodName);
    }


    /**
     * 订阅消息
     *
     * @param channel 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribe(String channel) {
        return redisReceiverService.subscribe(channel);
    }

    /**
     * 批量订阅消息
     *
     * @param channels 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribe(String... channels) {
        return redisReceiverService.subscribe(channels);
    }

    /**
     * 取消订阅消息
     *
     * @param channel 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribe(String channel) {
        return redisReceiverService.unsubscribe(channel);
    }

    /**
     * 批量取消订阅消息
     *
     * @param channels 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribe(String... channels) {
        return redisReceiverService.unsubscribe(channels);
    }

}
