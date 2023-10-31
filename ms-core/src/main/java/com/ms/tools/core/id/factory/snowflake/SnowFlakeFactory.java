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

package com.ms.tools.core.id.factory.snowflake;

import com.ms.tools.core.exception.base.MsToolsRuntimeException;

/**
 * SnowFlake 雪花Id 构建器
 *
 * @author ms2297248353
 */
public class SnowFlakeFactory {
    /**
     * 开始时间截
     */
    private static final long TW_EPOCH = 979574400000L;
    /**
     * 单例对象
     */
    private volatile static SnowFlakeFactory idWorker = null;
    /**
     * 机器ID所占位置
     */
    private final long workerIdBits = 5L;
    /**
     * 数据标识所占位数
     */
    private final long dataCenterIdBits = 5L;
    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;
    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构建对象
     * 请注意多次同时调用本对象会出现重复,请做好单例维护
     *
     * @param workerId     工作机器ID(0~31)
     * @param dataCenterId 数据中心ID(0~31)
     */
    public SnowFlakeFactory(long workerId, long dataCenterId) {
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获取单例对象
     * 非集群状态下(使用) 设备id与数据中心id均为1构建
     *
     * @return 单例对象
     */
    public static SnowFlakeFactory getSnowFlake() {
        return getSnowFlake(1, 1);
    }

    /**
     * 获取单例对象
     *
     * @param workerId     工作机器ID(0~31)
     * @param dataCenterId 数据中心ID(0~31)
     * @return 单例对象
     */
    public static SnowFlakeFactory getSnowFlake(long workerId, long dataCenterId) {
        if (null == idWorker) {
            synchronized (SnowFlakeFactory.class) {
                idWorker = new SnowFlakeFactory(workerId, dataCenterId);
            }
        }
        return idWorker;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return Id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new MsToolsRuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - TW_EPOCH) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 时间戳
     * @return 新时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 时间戳
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
