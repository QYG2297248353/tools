package com.ms.core.base.datetime.factory;

import com.ms.core.base.basic.StringUtils;
import com.ms.core.base.datetime.enums.CalendarFieldEnum;
import com.ms.core.base.datetime.enums.ZoneIdEnum;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.ms.core.base.basic.FormatUtils.format;


/**
 * 时间工厂
 *
 * @author ms
 */
public class DateFactory {

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return new DateFactory().create();
    }

    /**
     * 创建当前时间
     *
     * @return 当前时间
     */
    public Date create() {
        return new Date();
    }

    /**
     * 创建指定时间
     *
     * @param time 指定时间
     * @return 指定时间
     */
    public Date create(long time) {
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
    public Date create(int year, int month, int day, int hour, int minute, int second) {
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
    public Date createDate(int year, int month, int day) {
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
    public Date createDate(int year, int month, int day, int hour, int minute) {
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
    public Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
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
    public Date createDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
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
    public Calendar createCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 创建日历时间
     *
     * @param timeZone 时区
     * @return 日历时间
     */
    public Calendar createCalendar(TimeZone timeZone) {
        return Calendar.getInstance(timeZone);
    }

    /**
     * 创建日历时间
     *
     * @param zoneId 时区
     * @return 日历时间
     */
    public Calendar createCalendar(ZoneId zoneId) {
        return Calendar.getInstance(TimeZone.getTimeZone(zoneId));
    }

    /**
     * 创建日历时间
     *
     * @param zoneId 时区
     * @return 日历时间
     */
    public Calendar createCalendar(ZoneIdEnum zoneId) {
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
    public Date move(Date date, int field, int amount) {
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
    public Date move(Date date, CalendarFieldEnum field, int amount) {
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
    public int compare(Date date1, Date date2) {
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
    public boolean isSameDay(Date date1, Date date2) {
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
     */
    public boolean isBefore(Date date1, Date date2) {
        return compare(date1, date2) == -1;
    }

    /**
     * 前一个日期是否在后一个日期之后
     */
    public boolean isAfter(Date date1, Date date2) {
        return compare(date1, date2) == 1;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 天数
     */
    public long betweenDay(Date date1, Date date2) {
        return between(date1, date2, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取两个日期之间的小时数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 小时数
     */
    public long betweenHour(Date date1, Date date2) {
        return between(date1, date2, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取两个日期之间的分钟数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 分钟数
     */
    public long betweenMinute(Date date1, Date date2) {
        return between(date1, date2, Calendar.MINUTE);
    }

    /**
     * 获取两个日期之间的秒数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 秒数
     */
    public long betweenSecond(Date date1, Date date2) {
        return between(date1, date2, Calendar.SECOND);
    }

    /**
     * 获取两个日期之间的毫秒数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 毫秒数
     */
    public long betweenMilliSecond(Date date1, Date date2) {
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
    public long between(Date date1, Date date2, CalendarFieldEnum field) {
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
    public long between(Date date1, Date date2, int field) {
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
    private long getMillis(int field) {
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
                return 1000 * 60 * 60 * 24;
            default:
                return 1000 * 60 * 60 * 24;
        }
    }

    /**
     * 获取年份
     */
    public int getYear(Date date) {
        return get(date, Calendar.YEAR);
    }

    /**
     * 获取月份
     */
    public int getMonth(Date date) {
        return get(date, Calendar.MONTH);
    }

    /**
     * 获取日期
     */
    public int getDay(Date date) {
        return get(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取小时
     */
    public int getHour(Date date) {
        return get(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     */
    public int getMinute(Date date) {
        return get(date, Calendar.MINUTE);
    }

    /**
     * 获取秒
     */
    public int getSecond(Date date) {
        return get(date, Calendar.SECOND);
    }

    /**
     * 获取毫秒
     */
    public int getMilliSecond(Date date) {
        return get(date, Calendar.MILLISECOND);
    }

    /**
     * 获取日期
     */
    public int get(Date date, CalendarFieldEnum field) {
        return get(date, field.getField());
    }

    /**
     * 获取日期
     */
    public int get(Date date, int field) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获取当前年份
     */
    public int getYear() {
        return getYear(now());
    }

    /**
     * 获取月份
     */
    public int getMonth() {
        return getMonth(now());
    }

    /**
     * 获取日期
     */
    public int getDay() {
        return getDay(now());
    }

    /**
     * 获取小时
     */
    public int getHour() {
        return getHour(now());
    }

    /**
     * 获取分钟
     */
    public int getMinute() {
        return getMinute(now());
    }

    /**
     * 获取秒
     */
    public int getSecond() {
        return getSecond(now());
    }

    /**
     * 获取毫秒
     */
    public int getMilliSecond() {
        return getMilliSecond(now());
    }

    /**
     * 获取当前日期路径
     */
    public String getNowPath() {
        return getNowPath(null);
    }

    /**
     * 获取当前日期路径
     */
    public String getNowPath(String format) {
        if (StringUtils.isBlank(format)) {
            format = "yyyy/MM/dd";
        }
        return format(now(), format);
    }

    /**
     * 获取当前日期路径
     */
    public String getNowPath(String format, String suffix) {
        if (StringUtils.isBlank(format)) {
            format = "yyyy/MM/dd";
        }
        return format(now(), format) + suffix;
    }
}
