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

package com.ms.tools.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ms2297248353
 */
@Component
public class RedisObjectUtils extends RedisUtils {
    /**
     * 构造方法
     * 注入 RedisTemplate
     *
     * @param redisTemplate RedisTemplate
     */
    public RedisObjectUtils(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public Object set(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public Object set(Object key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        return value;
    }


    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Object get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存的基本对象并重置过期时间
     *
     * @param key      缓存键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存键值对应的数据
     */
    public Object get(Object key, long timeout, TimeUnit timeUnit) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            redisTemplate.expire(key, timeout, timeUnit);
        }
        return value;
    }

    /**
     * 获得缓存的基本对象并设置新值
     *
     * @param key   缓存键值
     * @param value 新数据
     * @return 旧数据
     */
    public Object getAndSet(Object key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }


    /**
     * 获得缓存的基本对象并设置新值
     *
     * @param key      缓存键值
     * @param value    新数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 旧数据
     */
    public Object getAndSet(Object key, Object value, long timeout, TimeUnit timeUnit) {
        Object result = redisTemplate.opsForValue().getAndSet(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return result;
    }

    /**
     * 将 key 中储存的数字值增一
     *
     * @param key 缓存键值
     * @return 增一后的值
     */
    public Long increment(Object key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 将 key 中储存的数字值增加指定值
     *
     * @param key   缓存键值
     * @param delta 增加值
     * @return 增加后的值
     */
    public Long increment(Object key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 将 key 中储存的数字值减一
     *
     * @param key 缓存键值
     * @return 减一后的值
     */
    public Long decrement(Object key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 将 key 中储存的数字值减去指定值
     *
     * @param key   缓存键值
     * @param delta 减去值
     * @return 减去后的值
     */
    public Long decrement(Object key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * 将 key 中储存的浮点数字值增加或减少指定值
     *
     * @param key   缓存键值
     * @param delta 增值 正数为增加 负数为减少
     * @return 增加后的值
     */
    public Double increment(Object key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
