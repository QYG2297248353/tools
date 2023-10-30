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

package com.ms.tools.core.base.format;

import com.ms.tools.core.enums.datetime.DateFormatEnum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具类
 *
 * @author 萌森 Ms
 */
public class DateTimeFormatUtils {

    private DateTimeFormatUtils() {
    }


    /**
     * 日期格式化
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 格式化结果
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DateFormatEnum.DEFAULT.getPattern()).format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式 yyyy-MM-dd HH:mm:ss
     * @return 格式化结果
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    时间戳
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(long date, String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(date));
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(Date date, DateFormatEnum pattern) {
        return new SimpleDateFormat(pattern.getPattern()).format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(long date, DateFormatEnum pattern) {
        return new SimpleDateFormat(pattern.getPattern()).format(new Date(date));
    }

    /**
     * 日期字符串 转 日期
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 日期
     */
    public static Date parse(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 任意格式字符串时间转为时间
     * 无需指定格式
     *
     * @param dateTime 时间
     * @return 时间
     */
    public static Date parseTime(String dateTime) {
        return parseTime(dateTime, null);
    }

    /**
     * 格式字符串时间转为时间
     *
     * @param dateTime 时间
     * @param pattern  格式 识别概率低请尽可能使用指定格式
     * @return 时间
     */
    public static Date parseTime(String dateTime, String pattern) {
        if (pattern == null) {
            // DateFormatEnum 支持的格式 解析
            for (DateFormatEnum value : DateFormatEnum.values()) {
                Date parseTime = parseTime(dateTime, value.getPattern());
                if (parseTime != null) {
                    return parseTime;
                }
            }
            // 日期
            pattern = dateTime.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
            if (dateTime.equals(pattern)) {
                pattern = pattern.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
            }
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
            // 时间
            pattern = pattern.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        }
        try {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.parse(dateTime);
        } catch (Exception e) {
            return null;
        }
    }
}
