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

package com.ms.redis.service;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * Redis 订阅客户端
 *
 * @author ms2297248353
 */
@Service
public class RedisReceiverService {
    private static final Logger log = Logger.getLogger(RedisReceiverService.class.getName());

    @Resource
    private RedisMessageListenerContainer container;

    /**
     * 批量订阅-频道消息
     *
     * @param topics 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribeChannel(MessageListenerAdapter listenerAdapter, List<ChannelTopic> topics) {
        try {
            for (ChannelTopic topic : topics) {
                container.addMessageListener(listenerAdapter, topic);
            }
            return true;
        } catch (Exception e) {
            log.warning("批量订阅消息失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 批量订阅-模式消息
     *
     * @param topics 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribePattern(MessageListenerAdapter listenerAdapter, List<PatternTopic> topics) {
        try {
            for (PatternTopic topic : topics) {
                container.addMessageListener(listenerAdapter, topic);
            }
            return true;
        } catch (Exception e) {
            log.warning("批量订阅消息失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 批量取消订阅-模式消息
     *
     * @param topics 模式
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribeChannel(MessageListenerAdapter listenerAdapter, List<ChannelTopic> topics) {
        try {
            for (ChannelTopic topic : topics) {
                container.removeMessageListener(listenerAdapter, topic);
            }
            return true;
        } catch (Exception e) {
            log.warning("批量取消订阅消息失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 批量取消订阅-模式消息
     *
     * @param topics 模式
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribePattern(MessageListenerAdapter listenerAdapter, List<PatternTopic> topics) {
        try {
            for (PatternTopic topic : topics) {
                container.removeMessageListener(listenerAdapter, topic);
            }
            return true;
        } catch (Exception e) {
            log.warning("批量取消订阅消息失败：" + e.getMessage());
            return false;
        }
    }


}
