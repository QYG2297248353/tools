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

package com.ms.core.base.datetime;


import com.ms.core.base.datetime.enums.ZoneIdEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工厂
 *
 * @author ms
 */
public class LocalTimeUtils {
    /**
     * 创建当前时间
     *
     * @return 当前时间
     */
    public LocalTime create() {
        return LocalTime.now();
    }

    /**
     * 创建指定时间
     *
     * @param date 日期
     * @return 指定时间
     */
    public LocalTime create(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND) * 1000000);
    }

    /**
     * 创建指定时间
     *
     * @param localDateTime 日期时间
     * @return 指定时间
     */
    public LocalTime create(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }


    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalTime create(ZoneId zoneId) {
        return LocalTime.now(zoneId);
    }

    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalTime create(ZoneIdEnum zoneId) {
        return LocalTime.now(zoneId.toZoneId());
    }

    /**
     * 创建指定时间
     *
     * @param hour   时
     * @param minute 分
     * @return 指定时间
     */
    public LocalTime create(int hour, int minute) {
        return LocalTime.of(hour, minute);
    }

    /**
     * 创建指定时间
     *
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return 指定时间
     */
    public LocalTime create(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    /**
     * 创建指定时间
     *
     * @param hour         时
     * @param minute       分
     * @param second       秒
     * @param nanoOfSecond 纳秒
     * @return 指定时间
     */
    public LocalTime create(int hour, int minute, int second, int nanoOfSecond) {
        return LocalTime.of(hour, minute, second, nanoOfSecond);
    }

}