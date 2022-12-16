/*
 * @MS 2022-12-13
 *  Copyright (c) 2001-2023 萌森
 *  保留所有权利
 *  本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 *  Copyright (c) 2001-2023 Meng Sen
 *  All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.base.utils;

import com.ms.base.utils.factory.DateTimeFactory;
import com.ms.exception.base.MsToolsRuntimeException;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 日期时间工具
 * 规范标准 ISO 8601 格式
 * 1970年1月1日零点（格林威治时区／GMT+00:00）
 *
 * @author ms2297248353
 */
public class DateTimeUtils extends DateUtils {
    /**
     * 获取本地日期时间(无时区)
     * <pre>
     *     获取本地日期
     *     getLocalDateTime().toLocalDate();
     *     获取本地时间
     *     getLocalDateTime().getLocalTime();
     * </pre>
     * Month的范围用1~12表示1月到12月；
     * Week的范围用1~7表示周一到周日
     *
     * @return 本地日期时间
     */
    public static LocalDateTime getLocalDateTime() {
        return DateTimeFactory.getLocalDateTime();
    }

    /**
     * 获取日期(无时区)
     *
     * @return 本地日期
     */
    public static LocalDate getLocalDate() {
        return DateTimeFactory.getLocalDate();
    }

    /**
     * 获取时间(无时区)
     *
     * @return 本地时间
     */
    public static LocalTime getLocalTime() {
        return DateTimeFactory.getLocalTime();
    }

    public static String formatter(String dtf) {
        return DateTimeFactory.formatterDateTime(getLocalDateTime(), dtf);
    }

    public static String formatter(LocalDateTime dateTime, String dtf) {
        return DateTimeFactory.formatterDateTime(dateTime, dtf);
    }

    public static LocalDateTime parseToLocalDateTime(String dateTime, String dtf) {
        return DateTimeFactory.parseDateTimeToLocalDateTime(dateTime, dtf);
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param year        年份
     * @param month       月份-1-12
     * @param dayOfMonth  日-1-31
     * @param hour        小时 0-23
     * @param minute      分钟 0-59
     * @param second      秒钟 0-59
     * @param millisecond 毫秒 0-999999999
     * @return 日期时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalDateTime toLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second, int millisecond) {
        return LocalDateTime.of(toLocalDate(year, month, dayOfMonth), toLocalTime(hour, minute, second, millisecond));
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param year       年份
     * @param month      月份-1-12
     * @param dayOfMonth 日-1-31
     * @param hour       小时 0-23
     * @param minute     分钟 0-59
     * @param second     秒钟 0-59
     * @return 日期时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalDateTime toLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return LocalDateTime.of(toLocalDate(year, month, dayOfMonth), toLocalTime(hour, minute, second));
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param year       年份
     * @param month      月份-1-12
     * @param dayOfMonth 日-1-31
     * @param hour       小时 0-23
     * @param minute     分钟 0-59
     * @return 日期时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalDateTime toLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(toLocalDate(year, month, dayOfMonth), toLocalTime(hour, minute));
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param localDate 本地日期
     * @param localTime 本地时间
     * @return 日期时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param hour        小时 0-23
     * @param minute      分钟 0-59
     * @param second      秒钟 0-59
     * @param millisecond 毫秒 0-999999999
     * @return 本地时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalTime toLocalTime(int hour, int minute, int second, int millisecond) {
        return DateTimeFactory.toLocalTime(hour, minute, second, millisecond);
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param hour   小时 0-23
     * @param minute 分钟 0-59
     * @param second 秒钟 0-59
     * @return 本地时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalTime toLocalTime(int hour, int minute, int second) {
        return DateTimeFactory.toLocalTime(hour, minute, second);
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param hour   小时 0-23
     * @param minute 分钟 0-59
     * @return 本地时间
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalTime toLocalTime(int hour, int minute) {
        return DateTimeFactory.toLocalTime(hour, minute);
    }

    /**
     * 日期时间解析(无时区)
     *
     * @param year       年份
     * @param month      月份-1-12
     * @param dayOfMonth 日-1-31
     * @return 本地日期
     * @throws MsToolsRuntimeException 超出指定范围
     */
    public static LocalDate toLocalDate(int year, int month, int dayOfMonth) {
        return DateTimeFactory.toLocalDate(year, month, dayOfMonth);
    }

    /**
     * 解析本地字符串日期(无时区)
     * yyyy-MM-dd
     *
     * @param date 字符串日期
     * @return 本地日期
     * @throws MsToolsRuntimeException 运行异常-字符串格式异常
     */
    public static LocalDate parseToLocalDate(String date) {
        return DateTimeFactory.parseLocalDateEx(date);
    }

    /**
     * 解析本地字符串时间(无时区)
     * HH:mm:ss
     * 带毫秒的时间
     * HH:mm:ss.SSS
     *
     * @param time 字符串时间
     * @return 本地时间
     * @throws MsToolsRuntimeException 运行异常-字符串格式异常
     */
    public static LocalTime parseToLocalTime(String time) {
        return DateTimeFactory.parseLocalTimeEx(time);
    }

    /**
     * 解析本地字符串日期时间(无时区)
     * yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间
     * yyyy-MM-dd'T'HH:mm:ss.SSS
     *
     * @param dateTime 字符串时间
     * @return 格式化时间
     * @throws MsToolsRuntimeException 运行异常-字符串格式异常
     */
    public static LocalDateTime parseToLocalDateTime(String dateTime) {
        return DateTimeFactory.parseLocalDateTimeEx(dateTime);
    }


    /**
     * 获取日期时间(中国时区)
     * <pre>
     *     获取日期
     *     getZonedDateTime().toLocalDate();
     *     获取时间
     *     getZonedDateTime().getLocalTime();
     *     丢弃时区
     *     getZonedDateTime().toLocalDateTime();
     * </pre>
     *
     * @return 日期时间中国区
     */
    public static ZonedDateTime getZonedDateTime() {
        return DateTimeFactory.getZonedDateTime();
    }

    /**
     * 获取日期时间(指定时区)
     * zone 参数类型
     * "Asia/Shanghai" 地区
     * "+8" 偏移量
     * "UTC+8" 时区偏移量
     * "Z" 时区
     *
     * <pre>
     *     获取日期
     *     getZonedDateTime().toLocalDate();
     *     获取时间
     *     getZonedDateTime().getLocalTime();
     * </pre>
     *
     * @return 日期时间中国区
     */
    public static ZonedDateTime getZonedDateTime(String zone) {
        return DateTimeFactory.getZonedDateTime(zone);
    }

    /**
     * 时区转换
     *
     * @param dateTime 转换时间
     * @param zone     时区
     * @return 转换后时间(时间已根据时区有所偏移)
     */
    public static ZonedDateTime conversionZoneDateTime(ZonedDateTime dateTime, String zone) {
        return DateTimeFactory.conversionZoneDateTime(dateTime, zone);
    }

    /**
     * 添加时区
     *
     * @param dateTime 时间
     * @param zone     时区
     * @return 时间
     */
    public static ZonedDateTime addZone(LocalDateTime dateTime, String zone) {
        return DateTimeFactory.localDateTimeAddZone(dateTime, zone);
    }

    /**
     * LocalDateTime转换为Date
     * 注意：当前不存咋时区，会自动添加中国时区
     *
     * @param dateTime 日期时间
     * @return 转换时间
     */
    public static Date toDate(LocalDateTime dateTime) {
        return DateTimeFactory.localDateTimeToDate(dateTime);
    }

    /**
     * ZonedDateTime转换为Date
     *
     * @param dateTime 日期时间
     * @return 转换时间
     */
    public static Date toDate(ZonedDateTime dateTime) {
        return DateTimeFactory.zonedDateTimeToDate(dateTime);
    }

    /**
     * 转换为ZonedDateTime
     * 转换会缺失精度，默认使用中国时区
     *
     * @param dateTime 日期时间
     * @return 转换时间
     */
    public static ZonedDateTime toZonedDateTime(Date dateTime) {
        return DateTimeFactory.dateToZonedDateTime(dateTime);
    }

    /**
     * 转换为ZonedDateTime
     * 默认使用中国时区
     *
     * @param dateTime 日期时间
     * @return 转换时间
     */
    public static ZonedDateTime toZonedDateTime(LocalDateTime dateTime) {
        return DateTimeFactory.dateToZonedDateTime(dateTime);
    }


    /**
     * 添加中国时区
     *
     * @param dateTime 本地时间
     * @return 中国时区时间
     */
    public static ZonedDateTime addChinaZoneLocalDateTime(LocalDateTime dateTime) {
        return DateTimeFactory.localDateTimeAddChinaZone(dateTime);
    }

    /**
     * 获取日期
     *
     * @return 本地日期
     */
    public static LocalDate getZonedDate() {
        return DateTimeFactory.getZonedDate();
    }

    /**
     * 获取时间
     *
     * @return 本地时间
     */
    public static LocalTime getZonedTime() {
        return DateTimeFactory.getZonedTime();
    }

    /**
     * 获取年
     *
     * @return 年
     */
    public static int getYear() {
        return getLocalDateTime().getYear();
    }

    /**
     * 获取月
     * from 1 to 12
     *
     * @return 月
     */
    public static int getMonth() {
        return getLocalDateTime().getMonthValue();
    }

    /**
     * 获取日
     * 月为单位
     * from 1 to 31
     *
     * @return 日
     */
    public static int getDayOfMonth() {
        return getLocalDateTime().getDayOfMonth();
    }

    /**
     * 获取日
     * 星期为单位
     * from 1 (Monday) to 7 (Sunday)
     *
     * @return 日
     */
    public static int getDayOfWeek() {
        return getLocalDateTime().getDayOfWeek().getValue();
    }

    /**
     * 获取日
     * 年为单位
     * from 1 to 365, or 366 in a leap year
     *
     * @return 日
     */
    public static int getDayOfYear() {
        return getLocalDateTime().getDayOfYear();
    }

    /**
     * 获取小时
     * from 0 to 23
     *
     * @return 小时
     */
    public static int getHour() {
        return getLocalDateTime().getHour();
    }

    /**
     * 获取分钟
     * from 0 to 59
     *
     * @return 分钟
     */
    public static int getMinute() {
        return getLocalDateTime().getMinute();
    }

    /**
     * 获取秒
     * from 0 to 59
     *
     * @return 秒
     */
    public static int getSecond() {
        return getLocalDateTime().getSecond();
    }

    /**
     * 获取毫秒
     * from 0 to 999,999,999
     *
     * @return 毫秒
     */
    public static int getNano() {
        return getLocalDateTime().getNano();
    }

    /**
     * 拨动当前时间轴为指定时间
     * 不建议使用负值拨动
     * 如果使用负数将会将时间向前拨动
     * -5 hour 会将时间向前拨动5小时，但是 处于临界值会出现异常问题
     * 推荐直接使用 5小时前的时间点
     *
     * @param hour   小时 0-23
     * @param minute 分钟 0-59
     * @param second 秒钟 0-59
     * @return 拨动后时间
     */
    public static LocalDateTime moveSetTime(int hour, int minute, int second) {
        return DateTimeFactory.moveSetTime(hour, minute, second);
    }

    /**
     * 拨动当前时间轴为指定时间
     * 不建议使用负值拨动
     * 如果使用负数将会将时间向前拨动
     * -5 hour 会将时间向前拨动5小时，但是 处于临界值会出现异常问题
     * 推荐直接使用 5小时前的时间点
     *
     * @param year  年
     * @param month 月 1-12
     * @param day   日 1-31
     * @return 拨动后时间
     */
    public static LocalDateTime moveSetDate(int year, int month, int day) {
        return DateTimeFactory.moveSetDate(year, month, day);
    }


    /**
     * 拨动当前时间轴
     * 正值添加时间，负值减少时间
     *
     * @param hour   拨动小时
     * @param minute 拨动分钟
     * @param second 拨动秒钟
     * @return 拨动后时间
     */
    public static LocalDateTime moveTime(Integer hour, Integer minute, Integer second) {
        return DateTimeFactory.moveTime(hour, minute, second);
    }

    /**
     * 拨动当前时间轴
     * 正值添加时间，负值减少时间 null不拨动
     *
     * @param year  拨动年份
     * @param month 拨动月
     * @param day   拨动日
     * @return 拨动后时间
     */
    public static LocalDateTime moveDate(Integer year, Integer month, Integer day) {
        return DateTimeFactory.moveDate(year, month, day);
    }

    /**
     * 拨动当前时间轴
     * 正值添加时间，负值减少时间 null不拨动
     *
     * @param year   拨动年份
     * @param month  拨动月
     * @param day    拨动日
     * @param hour   拨动小时
     * @param minute 拨动分钟
     * @param second 拨动秒钟
     * @return 拨动后时间
     */
    public static LocalDateTime moveDateTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        return DateTimeFactory.moveDateTime(year, month, day, hour, minute, second);
    }
}
