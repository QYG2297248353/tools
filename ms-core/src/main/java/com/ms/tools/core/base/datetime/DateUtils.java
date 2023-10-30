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

import com.ms.core.base.basic.StringUtils;
import com.ms.core.base.datetime.enums.CalendarFieldEnum;
import com.ms.core.base.datetime.enums.ZoneIdEnum;
import com.ms.core.config.SystemConfiguration;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * 时间工厂
 *
 * @author ms
 */
public class DateUtils {

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return create();
    }

    /**
     * 创建当前时间
     *
     * @return 当前时间
     */
    public static Date create() {
        return new Date();
    }

    /**
     * 创建指定时间
     *
     * @param time 指定时间
     * @return 指定时间
     */
    public static Date create(long time) {
        return new Date(time);
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
    @Deprecated
    public static Date create(int year, int month, int day, int hour, int minute, int second) {
        return new Date(year, month, day, hour, minute, second);
    }

    /**
     * 创建指定时间
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 指定时间
     */
    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @return 指定时间
     */
    public static Date createDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar.getTime();
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
    public static Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        return calendar.getTime();
    }

    /**
     * 创建指定时间
     *
     * @param year        年
     * @param month       月
     * @param day         日
     * @param hour        时
     * @param minute      分
     * @param second      秒
     * @param millisecond 毫秒
     * @return 指定时间
     */
    public static Date createDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 创建日历时间
     *
     * @return 日历时间
     */
    public static Calendar createCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 创建日历时间
     *
     * @param timeZone 时区
     * @return 日历时间
     */
    public static Calendar createCalendar(TimeZone timeZone) {
        return Calendar.getInstance(timeZone);
    }

    /**
     * 创建日历时间
     *
     * @param zoneId 时区
     * @return 日历时间
     */
    public static Calendar createCalendar(ZoneId zoneId) {
        return Calendar.getInstance(TimeZone.getTimeZone(zoneId));
    }

    /**
     * 创建日历时间
     *
     * @param zoneId 时区
     * @return 日历时间
     */
    public static Calendar createCalendar(ZoneIdEnum zoneId) {
        return Calendar.getInstance(zoneId.toTimeZone());
    }

    /**
     * 移动时间
     * <p>
     * Calendar.YEAR 年
     * Calendar.MONTH 月
     * Calendar.DAY_OF_MONTH 日
     * Calendar.HOUR 时
     * Calendar.HOUR_OF_DAY 时
     * Calendar.MINUTE 分
     * Calendar.SECOND 秒
     * Calendar.MILLISECOND 毫秒
     * Calendar.WEEK_OF_YEAR 周
     * Calendar.DAY_OF_YEAR 天
     * Calendar.DAY_OF_WEEK 星期
     * Calendar.DAY_OF_WEEK_IN_MONTH 星期
     * Calendar.AM_PM 上午/下午
     * Calendar.ZONE_OFFSET 时区
     * Calendar.DST_OFFSET 夏令时
     * Calendar.ERA 时代
     *
     * @param date   时间
     * @param field  字段
     * @param amount 数量
     * @return 移动后的时间
     */
    public static Date move(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 移动时间
     * <p>
     * Calendar.YEAR 年
     * Calendar.MONTH 月
     * Calendar.DAY_OF_MONTH 日
     * Calendar.HOUR 时
     * Calendar.HOUR_OF_DAY 时
     * Calendar.MINUTE 分
     * Calendar.SECOND 秒
     * Calendar.MILLISECOND 毫秒
     * Calendar.WEEK_OF_YEAR 周
     * Calendar.DAY_OF_YEAR 天
     * Calendar.DAY_OF_WEEK 星期
     * Calendar.DAY_OF_WEEK_IN_MONTH 星期
     * Calendar.AM_PM 上午/下午
     * Calendar.ZONE_OFFSET 时区
     * Calendar.DST_OFFSET 夏令时
     * Calendar.ERA 时代
     *
     * @param date   时间
     * @param field  字段
     * @param amount 数量
     * @return 移动后的时间
     */
    public static Date move(Date date, CalendarFieldEnum field, int amount) {
        return move(date, field.getField(), amount);
    }

    /**
     * 比较两个日期
     * <p>
     * 0：相等
     * 1：date1大于date2
     * -1：date1小于date2
     * 0：date1或date2为空
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 比较结果
     */
    public static int compare(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        return date1.compareTo(date2);
    }

    /**
     * 比较两个日期
     * <p>
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否相等
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        return isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 前一个日期是否在后一个日期之前
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否在前
     */
    public static boolean isBefore(Date date1, Date date2) {
        return compare(date1, date2) == -1;
    }

    /**
     * 前一个日期是否在后一个日期之后
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否在后
     */
    public static boolean isAfter(Date date1, Date date2) {
        return compare(date1, date2) == 1;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 天数
     */
    public static long betweenDay(Date date1, Date date2) {
        return between(date1, date2, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取两个日期之间的小时数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 小时数
     */
    public static long betweenHour(Date date1, Date date2) {
        return between(date1, date2, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取两个日期之间的分钟数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 分钟数
     */
    public static long betweenMinute(Date date1, Date date2) {
        return between(date1, date2, Calendar.MINUTE);
    }

    /**
     * 获取两个日期之间的秒数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 秒数
     */
    public static long betweenSecond(Date date1, Date date2) {
        return between(date1, date2, Calendar.SECOND);
    }

    /**
     * 获取两个日期之间的毫秒数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 毫秒数
     */
    public static long betweenMilliSecond(Date date1, Date date2) {
        return between(date1, date2, Calendar.MILLISECOND);
    }

    /**
     * 获取两个日期之间的时间差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @param field 字段
     * @return 时间差
     */
    public static long between(Date date1, Date date2, CalendarFieldEnum field) {
        return between(date1, date2, field.getField());
    }

    /**
     * 获取两个日期之间的时间差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @param field 字段
     * @return 时间差
     */
    public static long between(Date date1, Date date2, int field) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        return Math.abs(time1 - time2) / getMillis(field);
    }

    /**
     * 获取时间差
     *
     * @param field 字段
     * @return 时间差
     */
    private static long getMillis(int field) {
        switch (field) {
            case Calendar.MILLISECOND:
                return 1;
            case Calendar.SECOND:
                return 1000;
            case Calendar.MINUTE:
                return 1000 * 60;
            case Calendar.HOUR_OF_DAY:
                return 1000 * 60 * 60;
            case Calendar.DAY_OF_YEAR:
            default:
                return 1000 * 60 * 60 * 24;
        }
    }

    /**
     * 获取年份
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return get(date, Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return get(date, Calendar.MONTH);
    }

    /**
     * 获取日期
     *
     * @param date 日期
     * @return 日期
     */
    public static int getDay(Date date) {
        return get(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取小时
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return get(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return get(date, Calendar.MINUTE);
    }

    /**
     * 获取秒
     *
     * @param date 日期
     * @return 秒
     */
    public static int getSecond(Date date) {
        return get(date, Calendar.SECOND);
    }

    /**
     * 获取毫秒
     *
     * @param date 日期
     * @return 毫秒
     */
    public static int getMilliSecond(Date date) {
        return get(date, Calendar.MILLISECOND);
    }

    /**
     * 获取日期
     *
     * @param date  日期
     * @param field 字段
     * @return 日期
     */
    public static int get(Date date, CalendarFieldEnum field) {
        return get(date, field.getField());
    }

    /**
     * 获取日期
     *
     * @param date  日期
     * @param field 字段
     * @return 日期
     */
    public static int get(Date date, int field) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获取当前年份
     *
     * @return 当前年份
     */
    public static int getYear() {
        return getYear(now());
    }

    /**
     * 获取月份
     *
     * @return 月份
     */
    public static int getMonth() {
        return getMonth(now());
    }

    /**
     * 获取日期
     *
     * @return 日期
     */
    public static int getDay() {
        return getDay(now());
    }

    /**
     * 获取小时
     *
     * @return 小时
     */
    public static int getHour() {
        return getHour(now());
    }

    /**
     * 获取分钟
     *
     * @return 分钟
     */
    public static int getMinute() {
        return getMinute(now());
    }

    /**
     * 获取秒
     *
     * @return 秒
     */
    public static int getSecond() {
        return getSecond(now());
    }

    /**
     * 获取毫秒
     *
     * @return 毫秒
     */
    public static int getMilliSecond() {
        return getMilliSecond(now());
    }

    /**
     * 获取当前日期路径
     *
     * @return 当前日期路径
     */
    public static String getNowPath() {
        return getNowPath(null);
    }

    /**
     * 获取当前日期路径
     *
     * @param format 格式
     * @return 当前日期路径
     */
    public static String getNowPath(String format) {
        if (StringUtils.isBlank(format)) {
            format = "yyyy/MM/dd";
        }
        return format(now(), format);
    }

    /**
     * 获取当前日期路径
     *
     * @param format 格式
     * @param suffix 后缀
     * @return 当前日期路径
     */
    public static String getNowPath(String format, String suffix) {
        return getNowPath(format) + suffix;
    }

    /**
     * 时间戳 转为 日期
     * <p>
     * 时间戳为10位时,自动转为13位
     *
     * @param timestamp 时间戳
     * @return 日期
     */
    public static Date timestampToDate(long timestamp) {
        if (String.valueOf(timestamp).length() == 10) {
            timestamp *= 1000;
        }
        return new Date(timestamp);
    }

    /**
     * 日期 转为 时间戳
     *
     * @param date 日期
     * @return 时间戳
     */
    public static long dateToTimestamp(Date date) {
        return date.getTime();
    }

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long timestamp() {
        return dateToTimestamp(now());
    }

    /**
     * 获取当前时间戳
     * 10位
     *
     * @return 时间戳
     */
    public static int timestamp10() {
        return (int) (timestamp() / 1000);
    }

    /**
     * 获取当前时间戳
     * 10位
     *
     * @return 时间戳
     */
    public static long timestampTen() {
        return timestamp10();
    }


    /**
     * 获取当前时间戳
     * 13位
     *
     * @return 时间戳
     */
    public static long timestamp13() {
        return timestamp();
    }


    /**
     * 获取当前格式化日期
     *
     * @return 当前格式化日期
     */
    public static String getNowFormat() {
        return getNowFormat(null);
    }

    /**
     * 获取当前格式化日期
     *
     * @param format 格式
     * @return 当前格式化日期
     */
    public static String getNowFormat(String format) {
        return format(now(), format);
    }

    /**
     * 格式化日期
     *
     * @param date 日期
     * @return 格式化后的日期
     */
    public static String format(Date date) {
        return format(date, null);
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param format 格式
     * @return 格式化后的日期
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = SystemConfiguration.getSystemDateFormat();
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param format 格式
     * @param locale 本地化
     * @return 格式化后的日期
     */
    public static String format(Date date, String format, Locale locale) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = SystemConfiguration.getSystemDateFormat();
        }
        return new SimpleDateFormat(format, locale).format(date);
    }

    /**
     * 格式化日期
     *
     * @param date     日期
     * @param format   格式
     * @param locale   本地化
     * @param timeZone 时区
     * @return 格式化后的日期
     */
    public static String format(Date date, String format, Locale locale, TimeZone timeZone) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = SystemConfiguration.getSystemDateFormat();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }
}
