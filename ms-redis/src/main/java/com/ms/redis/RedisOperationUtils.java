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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate 工具类
 *
 * @author ms2297248353
 */
@Component
public class RedisOperationUtils<T, S> {

    @Resource
    private RedisTemplate<T, S> redisTemplate;


    /**
     * 获取 RedisTemplate
     *
     * @return RedisTemplate
     */
    public RedisTemplate<T, S> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 设置 RedisTemplate
     *
     * @param redisTemplate RedisTemplate
     */
    public void setRedisTemplate(RedisTemplate<T, S> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 基本对象操作

    /**
     * 缓存/覆盖 k, v
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public void set(T key, S value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存/覆盖 k, v
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public void set(T key, S value, Integer timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 缓存/覆盖 k, v
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public void set(T key, S value, Long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置过期时间
     *
     * @param key     k
     * @param timeout 超时时间 单位 s 秒
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(T key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     *
     * @param key      k
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(T key, int timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, timeUnit));
    }

    /**
     * 设置过期时间
     *
     * @param key      k
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(T key, long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, timeUnit));
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public S get(T key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存的基本对象并设置新过期时间
     *
     * @param key      缓存键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存键值对应的数据
     */
    public S get(T key, int timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        S value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            expire(key, timeout, timeUnit);
        }
        return value;
    }

    /**
     * 获取缓存的基本对象并设置新过期时间
     *
     * @param key      缓存键值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存键值对应的数据
     */
    public S get(T key, long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        S value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            expire(key, timeout, timeUnit);
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
    public S getAndSet(T key, S value) {
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
    public S getAndSet(T key, S value, int timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        S result = redisTemplate.opsForValue().getAndSet(key, value);
        if (result != null) {
            expire(key, timeout, timeUnit);
        }
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
    public S getAndSet(T key, S value, long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        S result = redisTemplate.opsForValue().getAndSet(key, value);
        if (result != null) {
            expire(key, timeout, timeUnit);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param key 缓存的键值
     */
    public boolean delete(T key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除多个
     *
     * @param keys 缓存的键值
     */
    public long delete(T... keys) {
        List<T> list = Arrays.asList(keys);
        Long delete = redisTemplate.delete(list);
        delete = delete == null ? 0 : delete;
        return delete;
    }

    /**
     * 删除多个
     *
     * @param keys 缓存的键值
     */
    public long delete(Collection<T> keys) {
        Long delete = redisTemplate.delete(keys);
        delete = delete == null ? 0 : delete;
        return delete;
    }

    /**
     * 判断是否存在
     *
     * @param key 缓存的键值
     * @return true=存在；false=不存在
     */
    public boolean exist(T key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 获取过期时间
     *
     * @param key 缓存的键值
     * @return 过期时间 单位 s 秒 ；-1=永久有效；-2=不存在
     */
    public long getExpire(T key) {
        Long expire = redisTemplate.getExpire(key);
        expire = expire == null ? -2 : expire;
        return expire;
    }

    /**
     * 获取过期时间
     *
     * @param key      缓存的键值
     * @param timeUnit 时间颗粒度
     * @return 过期时间 单位 timeUnit 指定单位 ；-1=永久有效；-2=不存在
     */
    public long getExpire(T key, TimeUnit timeUnit) {
        Long expire = redisTemplate.getExpire(key, timeUnit);
        expire = expire == null ? -2 : expire;
        return expire;
    }


    // 集合操作

    /**
     * 缓存List
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public void setList(T key, List<S> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 缓存List
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public void setList(T key, List<S> value, int timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        setList(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 缓存List
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public void setList(T key, List<S> value, long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        setList(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 插入List 头部插入
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public void insertList(T key, S value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 插入List 插入对象
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public void insertListPush(T key, S value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 插入List 头部插入
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public void insertListPush(T key, List<S> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 插入List 头部插入
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public void insertList(T key, List<S> value) {
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 插入List 头部插入-存在时
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public void insertListIfPresent(T key, S value) {
        redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public List<S> getList(T key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 获得缓存的list 索引位置对象对象
     *
     * @param key   缓存的键值
     * @param index 索引
     * @return 缓存键值对应的数据
     */
    public S getListIndex(T key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获得缓存的list 对象索引位置
     *
     * @param key   缓存的键值
     * @param value 对象
     * @return 缓存键值对应的数据 -1 代表没有找到
     */
    public long getListIndex(T key, S value) {
        Long index = redisTemplate.opsForList().indexOf(key, value);
        index = index == null ? -1 : index;
        return index;
    }

    /**
     * 获得缓存的list 对象最后出现索引位置
     *
     * @param key   缓存的键值
     * @param value 对象
     * @return 缓存键值对应的数据 -1 代表没有找到
     */
    public long getListLastIndex(T key, S value) {
        Long index = redisTemplate.opsForList().lastIndexOf(key, value);
        index = index == null ? -1 : index;
        return index;
    }


    /**
     * 获得缓存的list对象
     *
     * @param key   缓存的键值
     * @param start 开始
     * @param end   结束 -1 代表所有值
     * @return 缓存键值对应的数据
     */
    public List<S> getList(T key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获得缓存的list对象-前指定数量
     *
     * @param key 缓存的键值
     * @param num 数量
     * @return 缓存键值对应的数据
     */
    public List<S> getList(T key, long num) {
        return getList(key, 0, num - 1);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   缓存的键值
     * @param index 索引
     * @param value 值
     */
    public void updateListIndex(T key, long index, S value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 裁剪(删除), 删除除了[start,end]以外的所有元素
     *
     * @param key   缓存的键值
     * @param start 开始
     * @param end   结束
     */
    public void trimList(T key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * list缓存的长度
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public long getListSize(T key) {
        Long size = redisTemplate.opsForList().size(key);
        size = size == null ? 0 : size;
        return size;
    }


    /**
     * 移除N个值为value
     *
     * @param key   缓存的键值
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long removeCacheList(T key, long count, S value) {
        Long remove = redisTemplate.opsForList().remove(key, count, value);
        remove = remove == null ? 0 : remove;
        return remove;
    }

    /**
     * 缓存Set
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     * @return 缓存数据的对象
     */
    public long setSet(T key, Set<S> value) {
        long i = 0;
        for (S s : value) {
            Long add = redisTemplate.opsForSet().add(key, s);
            add = add == null ? 0 : add;
            i += add;
        }
        return i;
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
    public long setSet(T key, Set<S> value, Integer timeout, TimeUnit timeUnit) {
        long set = setSet(key, value);
        expire(key, timeout, timeUnit);
        return set;
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
    public long setCacheSet(T key, Set<S> value, Long timeout, TimeUnit timeUnit) {
        long set = setSet(key, value);
        expire(key, timeout, timeUnit);
        return set;
    }

    /**
     * 获得缓存的set
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Set<S> getSet(T key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * set缓存长度
     *
     * @param key 缓存键值
     * @return set长度
     */
    public long getSetSize(T key) {
        Long size = redisTemplate.opsForSet().size(key);
        size = size == null ? 0 : size;
        return size;
    }

    /**
     * 移除值为value的 一个元素
     *
     * @param key   缓存键值
     * @param value 值
     */
    public long removeCacheSet(T key, S value) {
        Long remove = redisTemplate.opsForSet().remove(key, value);
        remove = remove == null ? 0 : remove;
        return remove;
    }

    /**
     * 缓存Map
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     */
    public <TT, SS> void setHashMap(T key, Map<TT, SS> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    /**
     * 缓存Map
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <TT, SS> void setHashMap(T key, Map<TT, SS> value, int timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        setHashMap(key, value);
        expire(key, timeout, timeUnit);
    }

    /**
     * 缓存Map
     *
     * @param key      缓存键值
     * @param value    缓存的数据
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <TT, SS> void setHashMap(T key, Map<Object, Object> value, long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        setHashMap(key, value);
        expire(key, timeout, timeUnit);
    }

    /**
     * 获得缓存的Map
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <TT, SS> Map<TT, SS> getHashMap(T key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return (Map<TT, SS>) map;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key     缓存键值
     * @param hashKey 项
     * @param value   值
     */
    public <TT, SS> void addHashMapValue(T key, TT hashKey, SS value) {
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
    public <TT, SS> void addHashMapValue(T key, TT hashKey, SS value, int timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        addHashMapValue(key, hashKey, value);
        expire(key, timeout, timeUnit);
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
    public <TT, SS> void setCacheMapValue(T key, TT hashKey, SS value, long timeout, TimeUnit timeUnit) {
        timeUnit = timeUnit == null ? TimeUnit.SECONDS : timeUnit;
        addHashMapValue(key, hashKey, value);
        expire(key, timeout, timeUnit);
    }

    /**
     * 获取map缓存中的某个key对应的值
     *
     * @param key     缓存键值
     * @param hashKey 项
     * @return 值
     */
    public <TT, SS> SS getCacheMapValue(T key, TT hashKey) {
        Object val = redisTemplate.opsForHash().get(key, hashKey);
        return val == null ? null : (SS) val;
    }

    /**
     * 获取map缓存中keys对应的值
     *
     * @param key     缓存键值
     * @param hashKey 项
     * @return 值
     */
    public <TT, SS> List<SS> getCacheMapValues(T key, TT... hashKey) {
        List<Object> multiGet = redisTemplate.opsForHash().multiGet(key, Arrays.asList(hashKey));
        return (List<SS>) multiGet;
    }

    /**
     * 删除hash表中的值
     *
     * @param key      缓存键值
     * @param hashKeys 项 可以使多个 不能使null
     */
    public <TT> long deleteCacheMap(T key, TT... hashKeys) {
        Long delete = redisTemplate.opsForHash().delete(key, hashKeys);
        delete = delete == null ? 0 : delete;
        return delete;
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key     缓存键值
     * @param hashKey 项
     * @return true 存在 false不存在
     */
    public <TT> boolean existHash(T key, TT hashKey) {
        return Boolean.TRUE.equals(redisTemplate.opsForHash().hasKey(key, hashKey));
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值前缀
     */
    public void deletePattern(T pattern) {
        Set<T> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }


    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(Collection<T> pattern) {
        for (T object : pattern) {
            deleteCacheByPattern(object);
        }
    }

    /**
     * 模糊删除缓存
     *
     * @param pattern 缓存键值
     */
    public void deleteCacheByPattern(T... pattern) {
        for (T object : pattern) {
            deleteCacheByPattern(object);
        }
    }

}