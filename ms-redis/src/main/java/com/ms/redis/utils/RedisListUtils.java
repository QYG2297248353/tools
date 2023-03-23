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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ms2297248353
 */
@Component
public class RedisListUtils extends RedisUtils {
    /**
     * 构造方法
     * 注入 RedisTemplate
     *
     * @param redisTemplate RedisTemplate
     */
    public RedisListUtils(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }


    /**
     * 缓存List集合
     * 存储在 key 的列表的头部
     *
     * @param key    缓存的键值
     * @param values 缓存的值
     * @return 缓存的对象
     */
    public Object setList(Object key, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的头部
     *
     * @param key     缓存的键值
     * @param timeout 时间
     * @param values  缓存的值
     * @return 缓存的对象
     */
    public Object setList(Object key, long timeout, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的头部
     *
     * @param key      缓存的键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @param values   缓存的值
     * @return 缓存的对象
     */
    public Object setList(Object key, long timeout, TimeUnit timeUnit, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        redisTemplate.expire(key, timeout, timeUnit);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的头部
     *
     * @param key    缓存的键值
     * @param values 缓存的值
     * @return 缓存的对象
     */
    public Object setList(Object key, Collection<Object> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的头部
     *
     * @param key     缓存的键值
     * @param timeout 时间
     * @param values  缓存的值
     * @return 缓存的对象
     */
    public Object setList(Object key, long timeout, Collection<Object> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的头部
     *
     * @param key      缓存的键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @param values   缓存的值
     * @return 缓存的对象
     */
    public Object setList(Object key, long timeout, TimeUnit timeUnit, Collection<Object> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        redisTemplate.expire(key, timeout, timeUnit);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的尾部
     *
     * @param key    缓存的键值
     * @param values 缓存的值
     * @return 缓存的对象
     */
    public Object setListRight(Object key, Object... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的尾部
     *
     * @param key     缓存的键值
     * @param timeout 时间
     * @param values  缓存的值
     * @return 缓存的对象
     */
    public Object setListRight(Object key, long timeout, Object... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的尾部
     *
     * @param key      缓存的键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @param values   缓存的值
     * @return 缓存的对象
     */
    public Object setListRight(Object key, long timeout, TimeUnit timeUnit, Object... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, timeout, timeUnit);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的尾部
     *
     * @param key    缓存的键值
     * @param values 缓存的值
     * @return 缓存的对象
     */
    public Object setListRight(Object key, Collection<Object> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的尾部
     *
     * @param key     缓存的键值
     * @param timeout 时间
     * @param values  缓存的值
     * @return 缓存的对象
     */
    public Object setListRight(Object key, long timeout, Collection<Object> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return values;
    }

    /**
     * 缓存List集合
     * 存储在 key 的列表的尾部
     *
     * @param key      缓存的键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @param values   缓存的值
     * @return 缓存的对象
     */
    public Object setListRight(Object key, long timeout, TimeUnit timeUnit, Collection<Object> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, timeout, timeUnit);
        return values;
    }


    /**
     * 获得缓存的list集合
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public List<Object> getList(Object key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 获得缓存的list集合
     *
     * @param key   缓存的键值
     * @param start 开始位置
     * @param end   结束位置
     * @return 缓存键值对应的数据
     */
    public List<Object> getList(Object key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获得缓存的list集合大小
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据大小
     */
    public Long getListSize(Object key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   缓存的键值
     * @param index 索引
     * @return 缓存键值对应的数据
     */
    public Object getListIndex(Object key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 通过索引 设置list中的值
     *
     * @param key   缓存的键值
     * @param index 索引
     * @param value 值
     */
    public void setListIndex(Object key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 通过索引 移除list中的值
     *
     * @param key   缓存的键值
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long removeList(Object key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 通过索引 移除list中的值
     *
     * @param key   缓存的键值
     * @param value 值
     * @return 移除的个数
     */
    public Long removeList(Object key, Object value) {
        return redisTemplate.opsForList().remove(key, 0, value);
    }

    /**
     * 通过索引 移除list中的值
     *
     * @param key   缓存的键值
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long removeList(Object key, long count, Object... value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 通过索引 移除list中的值
     *
     * @param key   缓存的键值
     * @param value 值
     * @return 移除的个数
     */
    public Long removeList(Object key, Object... value) {
        return redisTemplate.opsForList().remove(key, 0, value);
    }

    /**
     * 通过索引 移除list中的值
     *
     * @param key   缓存的键值
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long removeList(Object key, long count, Collection<Object> value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 通过索引 移除list中的值
     *
     * @param key   缓存的键值
     * @param value 值
     * @return 移除的个数
     */
    public Long removeList(Object key, Collection<Object> value) {
        return redisTemplate.opsForList().remove(key, 0, value);
    }

    /**
     * 移除范围之外的数据
     * <p>
     * start = 0, end = -1  start 大于 end代表移除所有数据
     * list length = 5
     * start = 3, end = 8 end 大于 list length 代表移除到最后
     *
     * @param key   缓存的键值
     * @param start 开始位置
     * @param end   结束位置
     */
    public void trimList(Object key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 移除顶部的数据
     *
     * @param key 缓存的键值
     * @return 移除的数据
     */
    public Object leftPop(Object key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除底部的数据
     *
     * @param key 缓存的键值
     * @return 移除的数据
     */
    public Object rightPop(Object key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除顶部的数据并重置时间
     *
     * @param key     缓存的键值
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 移除的数据
     */
    public Object leftPop(Object key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除底部的数据并重置时间
     *
     * @param key     缓存的键值
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 移除的数据
     */
    public Object rightPop(Object key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

}
