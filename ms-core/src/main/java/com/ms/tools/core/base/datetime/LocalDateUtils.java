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
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ms
 */
public class LocalDateUtils {

    /**
     * 创建当前时间
     *
     * @return 当前时间
     */
    public LocalDate create() {
        return LocalDate.now();
    }

    /**
     * 创建指定时间
     *
     * @param date 日期
     * @return 指定时间
     */
    public LocalDate create(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 创建指定时间
     *
     * @param localDateTime 日期
     * @return 指定时间
     */
    public LocalDate create(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalDate create(ZoneId zoneId) {
        return LocalDate.now(zoneId);
    }

    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalDate create(ZoneIdEnum zoneId) {
        return LocalDate.now(zoneId.toZoneId());
    }

    /**
     * 创建指定时间
     *
     * @param year  年
     * @param month 月 1/12
     * @param day   日 1/31
     * @return 指定时间
     */
    public LocalDate create(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    /**
     * 创建指定时间
     *
     * @param year  年
     * @param month 月
     * @param day   日 1/31
     * @return 指定时间
     */
    public LocalDate create(int year, Month month, int day) {
        return LocalDate.of(year, month, day);
    }


    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月 1/12
     * @param day    日 1/31
     * @param zoneId 时区
     * @return 指定时间
     */
    public LocalDate create(int year, int month, int day, ZoneId zoneId) {
        return LocalDate.of(year, month, day).atStartOfDay(zoneId).toLocalDate();
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月
     * @param day    日 1/31
     * @param zoneId 时区
     * @return 指定时间
     */
    public LocalDate create(int year, Month month, int day, ZoneId zoneId) {
        return LocalDate.of(year, month, day).atStartOfDay(zoneId).toLocalDate();
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月 1/12
     * @param day    日 1/31
     * @param zoneId 时区
     * @return 指定时间
     */
    public LocalDate create(int year, int month, int day, ZoneIdEnum zoneId) {
        return LocalDate.of(year, month, day).atStartOfDay(zoneId.toZoneId()).toLocalDate();
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月
     * @param day    日 1/31
     * @param zoneId 时区
     * @return 指定时间
     */
    public LocalDate create(int year, Month month, int day, ZoneIdEnum zoneId) {
        return LocalDate.of(year, month, day).atStartOfDay(zoneId.toZoneId()).toLocalDate();
    }

}
