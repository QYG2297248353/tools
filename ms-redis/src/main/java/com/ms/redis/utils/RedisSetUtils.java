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

package com.ms.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ms2297248353
 */
@Component
public class RedisSetUtils extends RedisUtils {
    /**
     * 构造方法
     * 注入 RedisTemplate
     *
     * @param redisTemplate RedisTemplate
     */
    public RedisSetUtils(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 缓存Set集合
     *
     * @param key    缓存的键值
     * @param values 缓存的值
     * @return 缓存的对象
     */
    public Object set(Object key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
        return values;
    }

    /**
     * 缓存Set集合
     *
     * @param key     缓存的键值
     * @param timeout 时间
     * @param values  缓存的值
     * @return 缓存的对象
     */
    public Object set(Object key, long timeout, Object... values) {
        redisTemplate.opsForSet().add(key, values);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return values;
    }

    /**
     * 缓存Set集合
     *
     * @param key      缓存的键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @param values   缓存的值
     * @return 缓存的对象
     */
    public Object set(Object key, long timeout, TimeUnit timeUnit, Object... values) {
        redisTemplate.opsForSet().add(key, values);
        redisTemplate.expire(key, timeout, timeUnit);
        return values;
    }

    /**
     * 获得缓存的set集合
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Set<Object> get(Object key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获得缓存的set集合并重置过期时间
     *
     * @param key      缓存键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存键值对应的数据
     */
    public Set<Object> get(Object key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获得缓存的set集合并重置过期时间
     *
     * @param key     缓存键值
     * @param timeout 时间
     * @return 缓存键值对应的数据
     */
    public Set<Object> get(Object key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 移除缓存的set集合
     *
     * @param key    缓存键值
     * @param values 缓存的值
     * @return 移除的数量
     */
    public Long remove(Object key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断缓存的set集合是否存在
     *
     * @param key   缓存键值
     * @param value 缓存的值
     * @return 是否存在
     */
    public Boolean isMember(Object key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获得缓存的set集合大小
     *
     * @param key 缓存键值
     * @return 缓存的set集合大小
     */
    public Long size(Object key) {
        return redisTemplate.opsForSet().size(key);
    }

}
