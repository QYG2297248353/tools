package core.base.datetime.factory;

import core.base.datetime.enums.ZoneIdEnum;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工厂
 *
 * @author ms
 */
public class DateFactory {

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


}
