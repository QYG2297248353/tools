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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
@Component
public class RedisHashMapUtils extends RedisUtils {
    private static final Logger LOGGER = Logger.getLogger(RedisHashMapUtils.class.getName());

    /**
     * 构造方法
     * 注入 RedisTemplate
     *
     * @param redisTemplate RedisTemplate
     */
    public RedisHashMapUtils(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 缓存Hash结构
     *
     * @param key   缓存的键值
     * @param field 缓存的键值 K
     * @param value 缓存的值 V
     * @param <T>   缓存的值类型
     * @return true=设置成功；false=设置失败
     */
    public <T> boolean setCacheHash(String key, String field, T value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            return true;
        } catch (Exception e) {
            LOGGER.warning("缓存Hash结构失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 缓存Hash结构
     *
     * @param key      缓存的键值
     * @param field    缓存的键值
     * @param value    缓存的值
     * @param timeout  超时时间
     * @param timeUnit 超时时间单位
     * @param <T>      缓存的值类型
     * @return true=设置成功；false=设置失败
     */
    public <T> boolean setCacheHash(String key, String field, T value, long timeout, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            expire(key, timeout, timeUnit);
            return true;
        } catch (Exception e) {
            LOGGER.warning("缓存Hash结构失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 获取Hash结构
     *
     * @param key   缓存的键值
     * @param field 缓存的键值  K
     * @param <T>   缓存的值类型
     * @return 缓存的值  V
     */
    public <T> T getCacheHash(String key, String field) {
        return (T) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 删除Hash结构
     *
     * @param key   缓存的键值
     * @param field 缓存的键值 K
     * @return true=删除成功；false=删除失败
     */
    public boolean deleteCacheHash(String key, String field) {
        try {
            redisTemplate.opsForHash().delete(key, field);
            return true;
        } catch (Exception e) {
            LOGGER.warning("删除Hash结构失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 获取Hash结构的大小
     *
     * @param key 缓存的键值
     * @return Hash结构的大小
     */
    public Long getCacheHashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 判断Hash结构是否存在
     *
     * @param key   缓存的键值
     * @param field 缓存的键值 K
     * @return true=存在；false=不存在
     */
    public Boolean hasKeyCacheHash(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 获取Hash结构的所有键值
     *
     * @param key 缓存的键值
     * @return Hash结构的所有键值
     */
    public Set<Object> getCacheHashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取Hash结构的所有值
     *
     * @param key 缓存的键值
     * @return Hash结构的所有值
     */
    public List<Object> getCacheHashValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 获取Hash结构的所有键值对
     *
     * @param key 缓存的键值
     * @return Hash结构的所有键值对
     */
    public Map<Object, Object> getCacheHashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}
