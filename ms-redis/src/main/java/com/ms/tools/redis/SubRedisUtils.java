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

import com.ms.tools.redis.service.RedisReceiverService;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Redis 订阅客户端
 * 订阅者（Subscriber）可以根据自己的需要，订阅一个或多个频道（Channel）或者模式（Pattern）。
 *
 * @author ms2297248353
 */
@Component
public class SubRedisUtils {

    private final RedisReceiverService redisReceiverService;
    private final MessageListenerAdapter listenerAdapter;

    SubRedisUtils(MessageListenerAdapter listenerAdapter, RedisReceiverService redisReceiverService) {
        this.listenerAdapter = listenerAdapter;
        this.redisReceiverService = redisReceiverService;
    }

    public static MessageListenerAdapter createMessageListenerAdapter(Object bean, String method) {
        return new MessageListenerAdapter(bean, method);
    }

    /**
     * 订阅-频道消息
     *
     * @param channels 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribeChannel(String... channels) {
        List<ChannelTopic> topics = Arrays.stream(channels).map(ChannelTopic::new).collect(Collectors.toList());
        return subscribeChannel(topics);
    }

    /**
     * 取消订阅-频道消息
     *
     * @param channels 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribeChannel(String... channels) {
        List<ChannelTopic> topics = Arrays.stream(channels).map(ChannelTopic::new).collect(Collectors.toList());
        return unsubscribeChannel(topics);
    }

    /**
     * 订阅-模式消息
     *
     * @param pattern 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribePattern(String... pattern) {
        List<PatternTopic> topics = Arrays.stream(pattern).map(PatternTopic::new).collect(Collectors.toList());
        return subscribePattern(topics);
    }

    /**
     * 取消订阅-模式消息
     *
     * @param pattern 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribePattern(String... pattern) {
        List<PatternTopic> topics = Arrays.stream(pattern).map(PatternTopic::new).collect(Collectors.toList());
        return unsubscribePattern(topics);
    }

    /**
     * 订阅-频道消息
     *
     * @param channels 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribeChannel(List<ChannelTopic> channels) {
        return redisReceiverService.subscribeChannel(listenerAdapter, channels);
    }

    /**
     * 取消订阅-频道消息
     *
     * @param channels 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribeChannel(List<ChannelTopic> channels) {
        return redisReceiverService.unsubscribeChannel(listenerAdapter, channels);
    }

    /**
     * 订阅-模式消息
     *
     * @param pattern 模式
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribePattern(List<PatternTopic> pattern) {
        return redisReceiverService.subscribePattern(listenerAdapter, pattern);
    }

    /**
     * 取消订阅-模式消息
     *
     * @param pattern 模式
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribePattern(List<PatternTopic> pattern) {
        return redisReceiverService.unsubscribePattern(listenerAdapter, pattern);
    }

    /**
     * 自定义订阅-频道消息
     *
     * @param channels 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribeChannel(MessageListenerAdapter listenerAdapter, List<ChannelTopic> channels) {
        return redisReceiverService.subscribeChannel(listenerAdapter, channels);
    }

    /**
     * 取消自定义订阅-频道消息
     *
     * @param channels 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribeChannel(MessageListenerAdapter listenerAdapter, List<ChannelTopic> channels) {
        return redisReceiverService.unsubscribeChannel(listenerAdapter, channels);
    }

    /**
     * 自定义订阅-模式消息
     *
     * @param pattern 模式
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribePattern(MessageListenerAdapter listenerAdapter, List<PatternTopic> pattern) {
        return redisReceiverService.subscribePattern(listenerAdapter, pattern);
    }

    /**
     * 取消自定义订阅-模式消息
     *
     * @param pattern 模式
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribePattern(MessageListenerAdapter listenerAdapter, List<PatternTopic> pattern) {
        return redisReceiverService.unsubscribePattern(listenerAdapter, pattern);
    }

}
