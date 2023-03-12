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

package com.ms.core.base.datetime;

import com.ms.core.base.datetime.factory.DateTimeFormatFactory;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qyg2297248353
 */
public class DateTimeFormatUtils extends DateFormatUtils {

    public static synchronized String formatDate(Date date, String format) {
        return new DateTimeFormatFactory().formatDate(date, format);
    }

    public static String format(Date date, String format) {
        return DateTimeFormatFactory.format(date, format);
    }

    public static String format(LocalDateTime localDateTime, String format) {
        return DateTimeFormatFactory.format(localDateTime, format);
    }

    public static String format(String format) {
        return DateTimeFormatFactory.format(format);
    }

}
