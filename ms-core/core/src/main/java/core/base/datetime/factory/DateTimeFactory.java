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

package core.base.datetime.factory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ms2297248353
 */
public class DateTimeFactory {

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

    public static boolean isLeapYear(int year) {
        return LocalDate.of(year, 1, 1).isLeapYear();
    }

    public static boolean compare(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isAfter(dateTime2);
    }

    public static boolean compare(Date date1, Date date2) {
        return date1.after(date2);
    }

}
