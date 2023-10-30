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


import com.ms.core.base.datetime.enums.ZoneIdEnum;

import java.time.*;
import java.util.Date;

public class ZonedDateTimeUtils {
    /**
     * 创建
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime create() {
        return ZonedDateTime.now();
    }

    /**
     * 创建
     *
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(String zoneId) {
        return ZonedDateTime.now(ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }

    /**
     * 创建
     *
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(ZoneIdEnum zoneId) {
        return ZonedDateTime.now(zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return ZonedDateTime
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneIdEnum.DEFAULT.toZoneId());
    }

    /**
     * 创建
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, String zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, ZoneId zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, zoneId);
    }

    /**
     * 创建
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, ZoneIdEnum zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param year         年
     * @param month        月
     * @param day          日
     * @param hour         时
     * @param minute       分
     * @param second       秒
     * @param nanoOfSecond 纳秒
     * @param zoneId       时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, int nanoOfSecond, String zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, nanoOfSecond, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param year         年
     * @param month        月
     * @param day          日
     * @param hour         时
     * @param minute       分
     * @param second       秒
     * @param nanoOfSecond 纳秒
     * @param zoneId       时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, int nanoOfSecond, ZoneId zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, nanoOfSecond, zoneId);
    }

    /**
     * 创建
     *
     * @param epochMilli 毫秒
     * @param zoneId     时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(long epochMilli, String zoneId) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param epochMilli 毫秒
     * @param zoneId     时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(long epochMilli, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), zoneId);
    }

    /**
     * 创建
     *
     * @param date   日期
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(Date date, String zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param date   日期
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(Date date, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), zoneId);
    }

    /**
     * 创建
     *
     * @param date   日期
     * @param zoneId 时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(Date date, ZoneIdEnum zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param localDateTime 日期时间
     * @param zoneId        时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDateTime localDateTime, String zoneId) {
        return ZonedDateTime.of(localDateTime, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localDateTime 日期时间
     * @param zoneId        时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDateTime localDateTime, ZoneId zoneId) {
        return ZonedDateTime.of(localDateTime, zoneId);
    }

    /**
     * 创建
     *
     * @param localDateTime 日期时间
     * @param zoneId        时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDateTime localDateTime, ZoneIdEnum zoneId) {
        return ZonedDateTime.of(localDateTime, zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param localDate 日期
     * @param localTime 时分秒
     * @param zoneId    时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDate localDate, LocalTime localTime, String zoneId) {
        return ZonedDateTime.of(localDate, localTime, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localDate 日期
     * @param localTime 时分秒
     * @param zoneId    时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDate localDate, LocalTime localTime, ZoneId zoneId) {
        return ZonedDateTime.of(localDate, localTime, zoneId);
    }

    /**
     * 创建
     *
     * @param localDate 日期
     * @param zoneId    时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDate localDate, String zoneId) {
        return ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localDate 日期
     * @param zoneId    时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalDate localDate, ZoneId zoneId) {
        return ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, zoneId);
    }

    /**
     * 创建
     *
     * @param localTime 时分秒
     * @param zoneId    时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalTime localTime, String zoneId) {
        return ZonedDateTime.of(LocalDate.now(), localTime, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localTime 时分秒
     * @param zoneId    时区
     * @return ZonedDateTime
     */
    public ZonedDateTime create(LocalTime localTime, ZoneId zoneId) {
        return ZonedDateTime.of(LocalDate.now(), localTime, zoneId);
    }
}
