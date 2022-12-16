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

package com.ms.base.utils.factory;

import com.ms.exception.base.MsToolsRuntimeException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public class DateTimeFactory {
    private static final Logger LOGGER = Logger.getLogger(DateTimeFactory.class.getName());
    private static final String CHINA_ZONED = "Asia/Shanghai";

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalTime getLocalTime() {
        return LocalDateTime.now().toLocalTime();
    }

    public static LocalDate getLocalDate() {
        return LocalDateTime.now().toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(LocalDate localDate, LocalTime localTime) {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.of(localDate, localTime);
        } catch (DateTimeException e) {
            throw new MsToolsRuntimeException(e);
        }
        return dateTime;
    }

    public static LocalTime toLocalTime(int hour, int minute, int second, int millisecond) {
        LocalTime localTime;
        try {
            localTime = LocalTime.of(hour, minute, second, millisecond);
        } catch (DateTimeException e) {
            throw new MsToolsRuntimeException(e);
        }
        return localTime;
    }

    public static LocalTime toLocalTime(int hour, int minute, int second) {
        LocalTime localTime;
        try {
            localTime = LocalTime.of(hour, minute, second);
        } catch (DateTimeException e) {
            throw new MsToolsRuntimeException(e);
        }
        return localTime;
    }

    public static LocalTime toLocalTime(int hour, int minute) {
        LocalTime localTime;
        try {
            localTime = LocalTime.of(hour, minute);
        } catch (DateTimeException e) {
            throw new MsToolsRuntimeException(e);
        }
        return localTime;
    }

    public static LocalDate toLocalDate(int year, int month, int dayOfMonth) {
        LocalDate localDate;
        try {
            localDate = LocalDate.of(year, month, dayOfMonth);
        } catch (DateTimeException e) {
            throw new MsToolsRuntimeException(e);
        }
        return localDate;
    }

    /**
     * 日期和时间
     * <pre>
     *     yyyy-MM-dd'T'HH:mm:ss
     * </pre>
     * 带毫秒的日期和时间
     * <pre>
     *     yyyy-MM-dd'T'HH:mm:ss.SSS
     * </pre>
     *
     * @param strTime 字符串时间
     * @return 格式化时间
     * @throws MsToolsRuntimeException 运行异常-字符串格式异常
     */
    public static LocalDateTime parseLocalDateTimeEx(String strTime) throws MsToolsRuntimeException {
        LocalDateTime parse;
        try {
            parse = LocalDateTime.parse(strTime);
        } catch (DateTimeParseException e) {
            throw new MsToolsRuntimeException("格式异常", e);
        }
        return parse;
    }

    /**
     * 日期和时间
     * <pre>
     *     yyyy-MM-dd'T'HH:mm:ss
     * </pre>
     * 带毫秒的日期和时间
     * <pre>
     *     yyyy-MM-dd'T'HH:mm:ss.SSS
     * </pre>
     *
     * @param strTime 字符串时间
     * @return 格式化时间
     */
    public static LocalDateTime parseLocalDateTime(String strTime) {
        LocalDateTime parse;
        try {
            parse = parseLocalDateTimeEx(strTime);
        } catch (MsToolsRuntimeException e) {
            LOGGER.warning("时间格式异常：" + strTime + "使用当前时间");
            parse = getLocalDateTime();
        }
        return parse;
    }

    /**
     * 日期：yyyy-MM-dd
     *
     * @param strTime 字符串日期
     * @return 本地日期
     * @throws MsToolsRuntimeException 运行异常-字符串格式异常
     */
    public static LocalDate parseLocalDateEx(String strTime) throws MsToolsRuntimeException {
        LocalDate parse;
        try {
            parse = LocalDate.parse(strTime);
        } catch (Exception e) {
            throw new MsToolsRuntimeException("格式异常", e);
        }
        return parse;
    }

    /**
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     *
     * @param strTime 字符串时间
     * @return 本地时间
     * @throws MsToolsRuntimeException 运行异常-字符串格式异常
     */
    public static LocalTime parseLocalTimeEx(String strTime) throws MsToolsRuntimeException {
        LocalTime parse;
        try {
            parse = LocalTime.parse(strTime);
        } catch (Exception e) {
            throw new MsToolsRuntimeException("格式异常", e);
        }
        return parse;
    }


    /**
     * 日期：yyyy-MM-dd
     *
     * @param strTime 字符串日期
     * @return 本地日期
     */
    public static LocalDate parseLocalDate(String strTime) {
        LocalDate localDate;
        try {
            localDate = parseLocalDateEx(strTime);
        } catch (MsToolsRuntimeException e) {
            localDate = getLocalDate();
        }
        return localDate;
    }

    /**
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     *
     * @param strTime 字符串时间
     * @return 本地时间
     */
    public static LocalTime parseLocalTime(String strTime) {
        LocalTime parse;
        try {
            parse = parseLocalTimeEx(strTime);
        } catch (MsToolsRuntimeException e) {
            parse = getLocalTime();
        }
        return parse;
    }


    public static ZonedDateTime getZonedDateTime() {
        return getZonedDateTime(CHINA_ZONED);
    }

    public static ZonedDateTime getZonedDateTime(String zone) {
        ZoneId zoneId = ZoneId.of(zone);
        return ZonedDateTime.now(zoneId);
    }

    public static LocalTime getZonedTime() {
        return ZonedDateTime.now().toLocalTime();
    }

    public static LocalDate getZonedDate() {
        return ZonedDateTime.now().toLocalDate();
    }


    public static ZonedDateTime conversionZoneDateTime(ZonedDateTime dateTime, String zone) {
        ZoneId zoneId = ZoneId.of(zone);
        return dateTime.withZoneSameInstant(zoneId);
    }

    public static ZonedDateTime localDateTimeAddZone(LocalDateTime dateTime, String zone) {
        ZoneId zoneId = ZoneId.of(zone);
        return dateTime.atZone(zoneId);
    }

    public static ZonedDateTime localDateTimeAddChinaZone(LocalDateTime dateTime) {
        return dateTime.atZone(getChinaZone());
    }

    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = localDateTimeAddChinaZone(dateTime);
        return zonedDateTimeToDate(zonedDateTime);
    }

    public static Date zonedDateTimeToDate(ZonedDateTime dateTime) {
        return Date.from(dateTime.toInstant());
    }

    public static ZoneId getChinaZone() {
        return ZoneId.of(CHINA_ZONED);
    }

    public static ZonedDateTime dateToZonedDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), getChinaZone());
    }

    public static ZonedDateTime dateToZonedDateTime(LocalDateTime date) {
        return localDateTimeAddChinaZone(date);
    }

    public static String formatterDateTime(LocalDateTime localDate, String dtf) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dtf);
        return format.format(localDate);
    }

    public static LocalDateTime parseDateTimeToLocalDateTime(String dateTime, String dtf) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dtf);
        return LocalDateTime.parse(dateTime, format);
    }

    /**
     * 操作指定时间
     *
     * @param dateTime    日期时间
     * @param year        移动年份
     * @param month       移动月份
     * @param day         移动日
     * @param hour        移动小时
     * @param minute      移动分钟
     * @param second      移动秒钟
     * @param millisecond 移动毫秒
     * @return 移动后的时间
     */
    public static LocalDateTime moveSet(LocalDateTime dateTime, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second, Integer millisecond) {
        if (year != null && year >= 0) {
            dateTime = dateTime.withYear(year);
        } else {
            int moveDate = year == null ? 0 : Math.abs(year);
            dateTime = dateTime.minusYears(moveDate);
        }

        if (month != null && month >= 0) {
            dateTime = dateTime.withMonth(month);
        } else {
            int moveDate = month == null ? 0 : Math.abs(month);
            dateTime = dateTime.minusMonths(moveDate);
        }

        if (day != null && day >= 0) {
            dateTime = dateTime.withDayOfMonth(day);
        } else {
            int moveDate = day == null ? 0 : Math.abs(day);
            dateTime = dateTime.minusDays(moveDate);
        }


        if (hour != null && hour >= 0) {
            dateTime = dateTime.withHour(hour);
        } else {
            int moveDate = hour == null ? 0 : Math.abs(hour);
            dateTime = dateTime.minusHours(moveDate);
        }


        if (minute != null && minute >= 0) {
            dateTime = dateTime.withMinute(minute);
        } else {
            int moveDate = minute == null ? 0 : Math.abs(minute);
            dateTime = dateTime.minusMinutes(moveDate);
        }


        if (second != null && second >= 0) {
            dateTime = dateTime.withSecond(second);
        } else {
            int moveDate = second == null ? 0 : Math.abs(second);
            dateTime = dateTime.minusSeconds(moveDate);
        }


        if (millisecond != null && millisecond >= 0) {
            dateTime = dateTime.withNano(millisecond);
        } else {
            int moveDate = millisecond == null ? 0 : Math.abs(millisecond);
            dateTime = dateTime.minusNanos(moveDate);
        }
        return dateTime;
    }


    /**
     * 操作指定时间
     *
     * @param dateTime    日期时间
     * @param year        移动年份
     * @param month       移动月份
     * @param day         移动日
     * @param hour        移动小时
     * @param minute      移动分钟
     * @param second      移动秒钟
     * @param millisecond 移动毫秒
     * @return 移动后的时间
     */
    public static LocalDateTime move(LocalDateTime dateTime, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second, Integer millisecond) {
        if (year != null && year >= 0) {
            dateTime = dateTime.plusYears(year);
        } else {
            int moveDate = year == null ? 0 : Math.abs(year);
            dateTime = dateTime.minusYears(moveDate);
        }

        if (month != null && month >= 0) {
            dateTime = dateTime.plusMonths(month);
        } else {
            int moveDate = month == null ? 0 : Math.abs(month);
            dateTime = dateTime.minusMonths(moveDate);
        }

        if (day != null && day >= 0) {
            dateTime = dateTime.plusDays(day);
        } else {
            int moveDate = day == null ? 0 : Math.abs(day);
            dateTime = dateTime.minusDays(moveDate);
        }


        if (hour != null && hour >= 0) {
            dateTime = dateTime.plusHours(hour);
        } else {
            int moveDate = hour == null ? 0 : Math.abs(hour);
            dateTime = dateTime.minusHours(moveDate);
        }


        if (minute != null && minute >= 0) {
            dateTime = dateTime.plusMinutes(minute);
        } else {
            int moveDate = minute == null ? 0 : Math.abs(minute);
            dateTime = dateTime.minusMinutes(moveDate);
        }


        if (second != null && second >= 0) {
            dateTime = dateTime.plusSeconds(second);
        } else {
            int moveDate = second == null ? 0 : Math.abs(second);
            dateTime = dateTime.minusSeconds(moveDate);
        }


        if (millisecond != null && millisecond >= 0) {
            dateTime = dateTime.plusNanos(millisecond);
        } else {
            int moveDate = millisecond == null ? 0 : Math.abs(millisecond);
            dateTime = dateTime.minusNanos(moveDate);
        }
        return dateTime;
    }


    public static LocalDateTime moveSetDate(Integer year, Integer month, Integer day) {
        return moveSetDate(getLocalDateTime(), year, month, day);
    }

    public static LocalDateTime moveSetTime(Integer hour, Integer minute, Integer second) {
        return moveSetTime(getLocalDateTime(), hour, minute, second, null);
    }

    public static LocalDateTime moveSetTime(Integer hour, Integer minute, Integer second, Integer millisecond) {
        return moveSetTime(getLocalDateTime(), hour, minute, second, millisecond);
    }


    public static LocalDateTime moveSetDate(LocalDateTime dateTime, Integer year, Integer month, Integer day) {
        return moveSet(dateTime, year, month, day, null, null, null, null);
    }

    public static LocalDateTime moveSetTime(LocalDateTime dateTime, Integer hour, Integer minute, Integer second) {
        return moveSetTime(dateTime, hour, minute, second, null);
    }

    public static LocalDateTime moveSetTime(LocalDateTime dateTime, Integer hour, Integer minute, Integer second, Integer millisecond) {
        return moveSet(dateTime, null, null, null, hour, minute, second, millisecond);
    }


    public static LocalDateTime moveDate(Integer year, Integer month, Integer day) {
        return moveDate(getLocalDateTime(), year, month, day);
    }

    public static LocalDateTime moveTime(Integer hour, Integer minute, Integer second) {
        return moveTime(getLocalDateTime(), hour, minute, second, null);
    }

    public static LocalDateTime moveTime(Integer hour, Integer minute, Integer second, Integer millisecond) {
        return moveTime(getLocalDateTime(), hour, minute, second, millisecond);
    }

    public static LocalDateTime moveDateTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        return moveDateTime(getLocalDateTime(), year, month, day, hour, minute, second);
    }


    public static LocalDateTime moveDate(LocalDateTime dateTime, Integer year, Integer month, Integer day) {
        return move(dateTime, year, month, day, null, null, null, null);
    }

    public static LocalDateTime moveTime(LocalDateTime dateTime, Integer hour, Integer minute, Integer second) {
        return moveTime(dateTime, hour, minute, second, null);
    }

    public static LocalDateTime moveTime(LocalDateTime dateTime, Integer hour, Integer minute, Integer second, Integer millisecond) {
        return move(dateTime, null, null, null, hour, minute, second, millisecond);
    }

    public static LocalDateTime moveDateTime(LocalDateTime dateTime, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        return move(dateTime, year, month, day, hour, minute, second, second);
    }


}
