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

import com.ms.core.exception.base.MsToolsRuntimeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具
 * 规范标准 ISO 8601 格式
 * 1970年1月1日零点（格林威治时区／GMT+00:00）
 *
 * @author ms2297248353
 */
public class DateTimeUtils {

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
     * @param pattern  格式
     * @return 时间
     */
    public static Date parseTime(String dateTime, String pattern) {
        String str = dateTime;
        if (pattern == null) {
            // 日期
            pattern = dateTime.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
            if (str.equals(pattern)) {
                pattern = pattern.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
            }
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
            // 时间
            pattern = pattern.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
            pattern = pattern.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        }
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateTime);
        } catch (ParseException e) {
            throw new MsToolsRuntimeException(e);
        }
    }
}
