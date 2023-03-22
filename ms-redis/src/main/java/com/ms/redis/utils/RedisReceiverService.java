package com.ms.redis.utils;

import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * Redis 订阅客户端
 *
 * @author ms2297248353
 */
@Component
public class RedisReceiverService {
    private static final Logger logger = Logger.getLogger(RedisReceiverService.class.getName());

    @Resource
    private RedisMessageListenerContainer container;
    @Resource
    private MessageListenerAdapter listenerAdapter;

    /**
     * 配置监听器
     *
     * @param listener 监听器
     * @param <T>      对象
     * @return 配置成功返回true，否则返回false
     */
    public <T extends SubReceiver> boolean configureListener(T listener) {
        try {
            logger.info("监听器发生更换,监听器：" + listenerAdapter.getDelegate().getClass().getName() + "结束任务");
            listenerAdapter.setDelegate(listener);
            listenerAdapter.setDefaultListenerMethod("receiveMessage");
            return true;
        } catch (Exception e) {
            logger.warning("配置监听器失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 配置监听器
     *
     * @param listener 监听器
     * @param <T>      对象
     * @return 配置成功返回true，否则返回false
     */
    public <T extends MsReceiverListener> boolean configureListener(T listener) {
        try {
            logger.info("监听器发生更换,监听器：" + listenerAdapter.getDelegate().getClass().getName() + "结束任务");
            listenerAdapter.setDelegate(listener);
            listenerAdapter.setDefaultListenerMethod("onMessage");
            return true;
        } catch (Exception e) {
            logger.warning("配置监听器失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 配置监听器
     *
     * @param listener   监听器
     * @param methodName 监听器方法名
     * @param <T>        对象
     * @return 配置成功返回true，否则返回false
     */
    public <T> boolean configureListener(T listener, String methodName) {
        try {
            logger.info("监听器发生更换,监听器：" + listenerAdapter.getDelegate().getClass().getName() + "结束任务");
            listenerAdapter.setDelegate(listener);
            listenerAdapter.setDefaultListenerMethod(methodName);
            logger.info("新监听器：" + listenerAdapter.getDelegate().getClass().getName() + "开始任务,执行方法：" + methodName);
            return true;
        } catch (Exception e) {
            logger.warning("配置监听器失败：" + e.getMessage());
            return false;
        }
    }


    /**
     * 订阅消息
     *
     * @param channel 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribe(String channel) {
        try {
            container.addMessageListener(listenerAdapter, new PatternTopic(channel));
            return true;
        } catch (Exception e) {
            logger.warning("订阅消息失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 批量订阅消息
     *
     * @param channels 频道
     * @return 订阅成功返回true，否则返回false
     */
    public boolean subscribe(String... channels) {
        try {
            for (String channel : channels) {
                container.addMessageListener(listenerAdapter, new PatternTopic(channel));
            }
            return true;
        } catch (Exception e) {
            logger.warning("批量订阅消息失败：" + e.getMessage());
            return false;
        }
    }


    /**
     * 取消订阅消息
     *
     * @param channel 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribe(String channel) {
        try {
            container.removeMessageListener(listenerAdapter, new PatternTopic(channel));
            return true;
        } catch (Exception e) {
            logger.warning("取消订阅消息失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 批量取消订阅消息
     *
     * @param channels 频道
     * @return 取消订阅成功返回true，否则返回false
     */
    public boolean unsubscribe(String... channels) {
        try {
            for (String channel : channels) {
                container.removeMessageListener(listenerAdapter, new PatternTopic(channel));
            }
            return true;
        } catch (Exception e) {
            logger.warning("批量取消订阅消息失败：" + e.getMessage());
            return false;
        }
    }


}
