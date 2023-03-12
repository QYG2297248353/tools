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

import com.ms.core.base.datetime.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author qyg2297248353
 */
public class DateTimeFormatFactory {

    public static String format(Date date, String format) {
        return DateTimeUtils.toLocalDateTime(date).format(DateTimeFormatter.ofPattern(format));
    }

    public static String format(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String format(String format) {
        return DateTimeUtils.getLocalDateTime().format(DateTimeFormatter.ofPattern(format));
    }

    public synchronized String formatDate(Date date, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(date);
    }

}
