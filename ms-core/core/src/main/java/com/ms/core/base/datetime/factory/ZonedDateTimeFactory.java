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

package com.ms.core.base.datetime.factory;


import com.ms.core.base.datetime.enums.ZoneIdEnum;

import java.time.*;
import java.util.Date;

public class ZonedDateTimeFactory {
    /**
     * 创建
     *
     * @return
     */
    public ZonedDateTime create() {
        return ZonedDateTime.now();
    }

    /**
     * 创建
     *
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(String zoneId) {
        return ZonedDateTime.now(ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }

    /**
     * 创建
     *
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(ZoneIdEnum zoneId) {
        return ZonedDateTime.now(zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneIdEnum.DEFAULT.toZoneId());
    }

    /**
     * 创建
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, String zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, ZoneId zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, zoneId);
    }

    /**
     * 创建
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, ZoneIdEnum zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param nanoOfSecond
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, int nanoOfSecond, String zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, nanoOfSecond, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param nanoOfSecond
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(int year, int month, int day, int hour, int minute, int second, int nanoOfSecond, ZoneId zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, second, nanoOfSecond, zoneId);
    }

    /**
     * 创建
     *
     * @param epochMilli
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(long epochMilli, String zoneId) {
        return ZonedDateTime.ofInstant(java.time.Instant.ofEpochMilli(epochMilli), ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param epochMilli
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(long epochMilli, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(java.time.Instant.ofEpochMilli(epochMilli), zoneId);
    }

    /**
     * 创建
     *
     * @param date
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(Date date, String zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param date
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(Date date, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), zoneId);
    }

    /**
     * 创建
     *
     * @param date
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(Date date, ZoneIdEnum zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param localDateTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDateTime localDateTime, String zoneId) {
        return ZonedDateTime.of(localDateTime, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localDateTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDateTime localDateTime, ZoneId zoneId) {
        return ZonedDateTime.of(localDateTime, zoneId);
    }

    /**
     * 创建
     *
     * @param localDateTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDateTime localDateTime, ZoneIdEnum zoneId) {
        return ZonedDateTime.of(localDateTime, zoneId.toZoneId());
    }

    /**
     * 创建
     *
     * @param localDate
     * @param localTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDate localDate, LocalTime localTime, String zoneId) {
        return ZonedDateTime.of(localDate, localTime, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localDate
     * @param localTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDate localDate, LocalTime localTime, ZoneId zoneId) {
        return ZonedDateTime.of(localDate, localTime, zoneId);
    }

    /**
     * 创建
     *
     * @param localDate
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDate localDate, String zoneId) {
        return ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localDate
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalDate localDate, ZoneId zoneId) {
        return ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, zoneId);
    }

    /**
     * 创建
     *
     * @param localTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalTime localTime, String zoneId) {
        return ZonedDateTime.of(LocalDate.now(), localTime, ZoneId.of(zoneId));
    }

    /**
     * 创建
     *
     * @param localTime
     * @param zoneId
     * @return
     */
    public ZonedDateTime create(LocalTime localTime, ZoneId zoneId) {
        return ZonedDateTime.of(LocalDate.now(), localTime, zoneId);
    }
}
