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

package com.ms.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate 工具类
 *
 * @author ms2297248353
 */
@Component
public class RedisTemplateUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;


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

    // 基本对象操作

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public Object setCacheObject(Object key, Object value) {
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
    public Object setCacheObject(Object key, Object value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
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
    public Object setCacheObject(Object key, Object value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        return value;
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
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Object getCacheObject(Object key) {
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
    public Object getCacheObject(Object key, Integer timeout, TimeUnit timeUnit) {
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
    public Object getAndSet(Object key, Object value, Integer timeout, TimeUnit timeUnit) {
        Object result = redisTemplate.opsForValue().getAndSet(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return result;
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
    public Object getAndSet(Object key, Object value, Long timeout, TimeUnit timeUnit) {
        Object result = redisTemplate.opsForValue().getAndSet(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return result;
    }

    /**
     * 删除单个对象
     *
     * @param key 缓存的键值
     */
    public void deleteObject(Object key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个对象
     *
     * @param keys 缓存的键值
     */
    public void deleteObject(Object... keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 删除多个对象
     *
     * @param keys 缓存的键值
     */
    public void deleteObject(Collection<Object> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 判断是否存在
     *
     * @param key 缓存的键值
     * @return true=存在；false=不存在
     */
    public boolean hasKey(Object key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 获取过期时间
     *
     * @param key 缓存的键值
     * @return 过期时间 单位 s 秒 ；-1=永久有效；-2=不存在
     */
    public Long getExpire(Object key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取过期时间
     *
     * @param key      缓存的键值
     * @param timeUnit 时间颗粒度
     * @return 过期时间 单位 timeUnit 指定单位 ；-1=永久有效；-2=不存在
     */
    public Long getExpire(Object key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }


    // 集合操作

    /**
     * 缓存List
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     * @return 缓存的对象
     */
    public List<Object> setCacheList(Object key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
        return value;
    }

    /**
     * 缓存List
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public List<Object> setCacheList(Object key, List<Object> value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForList().rightPushAll(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return value;
    }

    /**
     * 缓存List
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public List<Object> setCacheList(Object key, List<Object> value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForList().rightPushAll(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return value;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public List<Object> getCacheList(Object key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 获得缓存的list对象
     *
     * @param key   缓存的键值
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return 缓存键值对应的数据
     */
    public List<Object> getCacheList(Object key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * list缓存的长度
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public Long getCacheListSize(Object key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   缓存的键值
     * @param index 索引
     * @param value 值
     */
    public void updateCacheList(Object key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   缓存的键值
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long removeCacheList(Object key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 缓存Set
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     * @return 缓存数据的对象
     */
    public Set<Object> setCacheSet(Object key, Set<Object> value) {
        redisTemplate.opsForSet().add(key, value.toArray());
        return value;
    }

    /**
     * 缓存Set
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存数据的对象
     */
    public Set<Object> setCacheSet(Object key, Set<Object> value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForSet().add(key, value.toArray());
        redisTemplate.expire(key, timeout, timeUnit);
        return value;
    }

    /**
     * 缓存Set
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存数据的对象
     */
    public Set<Object> setCacheSet(Object key, Set<Object> value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForSet().add(key, value.toArray());
        redisTemplate.expire(key, timeout, timeUnit);
        return value;
    }

    /**
     * 获得缓存的set
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Set<Object> getCacheSet(Object key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     * @return 缓存的对象
     */
    public Map<Object, Object> setCacheMap(Object key, Map<Object, Object> value) {
        redisTemplate.opsForHash().putAll(key, value);
        return value;
    }

    /**
     * 缓存Map
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public Map<Object, Object> setCacheMap(Object key, Map<Object, Object> value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().putAll(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return value;
    }

    /**
     * 缓存Map
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public Map<Object, Object> setCacheMap(Object key, Map<Object, Object> value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().putAll(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return value;
    }

    /**
     * 获得缓存的Map
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Map<Object, Object> getCacheMap(Object key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key     缓存键值
     * @param hashKey 项
     * @param value   值
     */
    public void setCacheMapValue(Object key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key      缓存键值
     * @param hashKey  项
     * @param value    值
     * @param timeout  时间(秒)
     * @param timeUnit 时间颗粒度
     */
    public void setCacheMapValue(Object key, Object hashKey, Object value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key      缓存键值
     * @param hashKey  项
     * @param value    值
     * @param timeout  时间(秒)
     * @param timeUnit 时间颗粒度
     */
    public void setCacheMapValue(Object key, Object hashKey, Object value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 删除hash表中的值
     *
     * @param key      缓存键值
     * @param hashKeys 项 可以使多个 不能使null
     */
    public void deleteCacheMap(Object key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 缓存键值
     * @return true 存在 false不存在
     */
    public boolean containsKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 缓存键值
     */
    public void deleteCache(Object key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除缓存
     *
     * @param keys 缓存键值
     */
    public void deleteCache(Collection<Object> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(Object pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(Collection<Object> pattern) {
        for (Object object : pattern) {
            Set<Object> keys = redisTemplate.keys(object);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
            }
        }
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(Object... pattern) {
        for (Object object : pattern) {
            Set<Object> keys = redisTemplate.keys(object);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
            }
        }
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(String... pattern) {
        for (String object : pattern) {
            Set<Object> keys = redisTemplate.keys(object);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
            }
        }
    }

}
