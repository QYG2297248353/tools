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

package com.ms.tools.core.enums.datetime;

import java.util.Calendar;

public enum CalendarFieldEnum {
    /**
     * 年
     */
    YEAR(Calendar.YEAR),
    /**
     * 月
     */
    MONTH(Calendar.MONTH),
    /**
     * 日
     */
    DAY(Calendar.DAY_OF_MONTH),
    /**
     * 时
     */
    HOUR(Calendar.HOUR_OF_DAY),
    /**
     * 分
     */
    MINUTE(Calendar.MINUTE),
    /**
     * 秒
     */
    SECOND(Calendar.SECOND),
    /**
     * 毫秒
     */
    MILLISECOND(Calendar.MILLISECOND),
    /**
     * 星期
     */
    WEEK(Calendar.DAY_OF_WEEK),
    /**
     * 一年中的第几周
     */
    WEEK_OF_YEAR(Calendar.WEEK_OF_YEAR),
    /**
     * 一月中的第几周
     */
    WEEK_OF_MONTH(Calendar.WEEK_OF_MONTH),
    /**
     * 一年中的第几天
     */
    DAY_OF_YEAR(Calendar.DAY_OF_YEAR),
    /**
     * 一月中的第几天
     */
    DAY_OF_MONTH(Calendar.DAY_OF_MONTH),
    /**
     * 一周中的第几天
     */
    DAY_OF_WEEK(Calendar.DAY_OF_WEEK),
    /**
     * 一月中的第几个星期几
     */
    DAY_OF_WEEK_IN_MONTH(Calendar.DAY_OF_WEEK_IN_MONTH),
    /**
     * 一天中的第几个小时
     */
    HOUR_OF_DAY(Calendar.HOUR_OF_DAY);

    private int field;

    CalendarFieldEnum(int field) {
        this.field = field;
    }

    public static CalendarFieldEnum valueOf(int field) {
        for (CalendarFieldEnum calendarFieldEnum : CalendarFieldEnum.values()) {
            if (calendarFieldEnum.getField() == field) {
                return calendarFieldEnum;
            }
        }
        return null;
    }

    public int getField() {
        return field;
    }
}
