package com.ms.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis 发布客户端
 * 发布订阅模式是一种消息通信模式，它的特点是：发布者（Publisher）不会将消息直接发送给特定的订阅者（Subscriber），
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
