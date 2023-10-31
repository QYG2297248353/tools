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

package com.ms.tools.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis 发布客户端
 * 发布订阅模式是一种消息通信模式，它的特点是：发布者（Publisher）不会将消息直接发送给特定的订阅者（Subscriber），
 * 这里不限定频道（Channel）或者模式（Pattern）的数量，发布者只需要将消息发送给频道，而订阅者可以根据自己的需要，订阅一个或多个频道（Channel）或者模式（Pattern）。
 *
 * @author ms2297248353
 */
@Component
public class PubRedisUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 发布消息
     *
     * @param channel 频道
     * @param message 消息
     */
    public void publish(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    /**
     * 发布消息
     *
     * @param channel 频道
     * @param message 消息
     * @param <T>     消息类型
     * @return 消息
     */
    public <T> T publish(String channel, T message) {
        redisTemplate.convertAndSend(channel, message);
        return message;
    }
}
