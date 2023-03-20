package com.ms.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author ms2297248353
 */
public abstract class RedisUtils {
    protected RedisTemplate<Object, Object> redisTemplate;

    /**
     * 构造方法
     *
     * @param redisTemplate RedisTemplate
     */
    RedisUtils(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取 RedisTemplate
     *
     * @return RedisTemplate
     */
    public RedisTemplate<Object, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 设置 RedisTemplate
     *
     * @param redisTemplate RedisTemplate
     */
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间 单位 s 秒
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(Object key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(Object key, long timeout, TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 删除缓存
     *
     * @param key Redis键
     * @return true=删除成功；false=删除失败
     */
    public boolean delete(Object key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除缓存
     *
     * @param keys Redis键
     * @return true=删除成功；false=删除失败
     */
    public boolean delete(Collection<Object> keys) {
        return Boolean.TRUE.equals(redisTemplate.delete(keys));
    }

    /**
     * 删除缓存
     *
     * @param keys Redis键
     * @return true=删除成功；false=删除失败
     */
    public boolean delete(Object... keys) {
        return Boolean.TRUE.equals(redisTemplate.delete(keys));
    }


}
