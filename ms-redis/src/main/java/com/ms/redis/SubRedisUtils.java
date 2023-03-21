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
