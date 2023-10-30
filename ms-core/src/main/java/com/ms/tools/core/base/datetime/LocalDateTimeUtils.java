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

package com.ms.tools.core.base.datetime;


import com.ms.tools.core.enums.datetime.ZoneIdEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工厂
 *
 * @author ms
 */
public class LocalDateTimeUtils {

    /**
     * 创建当前时间
     *
     * @return 当前时间
     */
    public LocalDateTime create() {
        return LocalDateTime.now();
    }

    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalDateTime create(ZoneIdEnum zoneId) {
        return LocalDateTime.now(zoneId.toZoneId());
    }

    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalDateTime create(ZoneId zoneId) {
        return LocalDateTime.now(zoneId);
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return 指定时间
     */
    public LocalDateTime create(int year, int month, int day, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @param nano   纳秒
     * @return 指定时间
     */
    public LocalDateTime create(int year, int month, int day, int hour, int minute, int second, int nano) {
        return LocalDateTime.of(year, month, day, hour, minute, second, nano);
    }

    /**
     * 创建指定时间
     *
     * @param date 日期
     * @param time 时间
     * @return 指定时间
     */
    public LocalDateTime create(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    /**
     * 创建指定时间
     *
     * @param date 日期
     * @return 指定时间
     */
    public LocalDateTime create(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 转为LocalDate
     *
     * @param localDateTime 时间
     * @return 日期
     */
    public LocalDate convertToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 转为LocalTime
     *
     * @param localDateTime 时间
     * @return 时间
     */
    public LocalTime convertToLocalTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }
}
