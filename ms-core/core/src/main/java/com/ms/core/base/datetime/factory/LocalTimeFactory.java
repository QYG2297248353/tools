package com.ms.core.base.datetime.factory;


import com.ms.core.base.datetime.enums.ZoneIdEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工厂
 *
 * @author ms
 */
public class LocalTimeFactory {
    /**
     * 创建当前时间
     *
     * @return 当前时间
     */
    public LocalTime create() {
        return LocalTime.now();
    }

    /**
     * 创建指定时间
     *
     * @param date 日期
     * @return 指定时间
     */
    public LocalTime create(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND) * 1000000);
    }

    /**
     * 创建指定时间
     *
     * @param localDateTime 日期时间
     * @return 指定时间
     */
    public LocalTime create(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }


    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalTime create(ZoneId zoneId) {
        return LocalTime.now(zoneId);
    }

    /**
     * 创建指定时区当前时间
     *
     * @param zoneId 时区
     * @return 当前时间
     */
    public LocalTime create(ZoneIdEnum zoneId) {
        return LocalTime.now(zoneId.toZoneId());
    }

    /**
     * 创建指定时间
     *
     * @param hour   时
     * @param minute 分
     * @return 指定时间
     */
    public LocalTime create(int hour, int minute) {
        return LocalTime.of(hour, minute);
    }

    /**
     * 创建指定时间
     *
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return
     */
    public LocalTime create(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    /**
     * 创建指定时间
     *
     * @param hour         时
     * @param minute       分
     * @param second       秒
     * @param nanoOfSecond 纳秒
     * @return 指定时间
     */
    public LocalTime create(int hour, int minute, int second, int nanoOfSecond) {
        return LocalTime.of(hour, minute, second, nanoOfSecond);
    }

}
