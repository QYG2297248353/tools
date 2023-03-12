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
 *
 * @author ms2297248353
 */
public class SnowFlakeCreator {
    /**
     * 创建SnowFlake 雪花Id
     * 本对象单例维护
     *
     * @return id
     */
    public static Long snowflake() {
        return SnowFlakeFactory.getSnowFlake().nextId();
    }

    /**
     * 创建SnowFlake 雪花Id
     * 本对象单例维护
     *
     * @param workerId     工作机器ID(0~31)
     * @param dataCenterId 数据中心ID(0~31)
     * @return id
     */
    public static Long snowflake(Integer workerId, Integer dataCenterId) {
        return SnowFlakeFactory.getSnowFlake(workerId, dataCenterId).nextId();
    }

    /**
     * 创建SnowFlake 雪花Id
     * 本对象单例维护
     *
     * @return id
     */
    public static String snowflakeString() {
        return String.valueOf(SnowFlakeFactory.getSnowFlake().nextId());
    }

    /**
     * 创建SnowFlake 雪花Id
     * 非单例模式，请自己维护
     *
     * @param workerId     工作机器ID(0~31)
     * @param dataCenterId 数据中心ID(0~31)
     * @return SnowFlake
     */
    public static SnowFlakeFactory snowflakeBuild(Integer workerId, Integer dataCenterId) {
        return new SnowFlakeFactory(workerId, dataCenterId);
    }
}
