package com.ms.core.base.datetime.enums;

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
