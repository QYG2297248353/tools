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

package com.ms.id.factory.snowflake;

/**
 * SnowFlake 雪花Id
 * 工具类维护单例对象
 * 仅供工具类使用 避免其他项目直接调用
 *
 * @author ms2297248353
 */
public class SnowFlakeCore {
    /**
     * 单例维护
     */
    private volatile static SnowFlakeCore snowFlakeCore = null;

    /**
     * 工作机器ID
     * 随机生成
     */
    private final long workerId;
    /**
     * 数据中心ID
     * 随机生成
     */
    private final long dataCenterId;
    /**
     * 雪花函数
     */
    private final SnowFlakeFactory snowFlake;

    /**
     * 私有化构造器
     */
    private SnowFlakeCore() {
        workerId = (long) (Math.random() * 31);
        dataCenterId = (long) (Math.random() * 31);
        snowFlake = new SnowFlakeFactory(workerId, dataCenterId);
    }

    /**
     * 获取单例对象
     */
    public static SnowFlakeCore getInstance() {
        if (snowFlakeCore == null) {
            synchronized (SnowFlakeCore.class) {
                if (snowFlakeCore == null) {
                    snowFlakeCore = new SnowFlakeCore();
                }
            }
        }
        return snowFlakeCore;
    }

    /**
     * 获取雪花Id
     *
     * @return 雪花Id
     */
    public long getSnowFlakeId() {
        return snowFlake.nextId();
    }


}
