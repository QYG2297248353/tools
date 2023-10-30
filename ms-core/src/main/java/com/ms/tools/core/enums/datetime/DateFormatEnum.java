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

/**
 * 日期格式枚举
 *
 * @author ms
 */
public enum DateFormatEnum {
    /**
     * yyyy-MM-dd
     */
    DATE_PATTERN("yyyy-MM-dd"),
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    DATE_TIME_PATTERN("yyyy-MM-dd HH:mm:ss"),
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    DATE_TIME_MS_PATTERN("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * yyyyMMdd
     */
    DATE_PATTERN_NO_SPLIT("yyyyMMdd"),
    /**
     * yyyyMMddHHmmss
     */
    DATE_TIME_PATTERN_NO_SPLIT("yyyyMMddHHmmss"),
    /**
     * yyyyMMddHHmmssSSS
     */
    DATE_TIME_MS_PATTERN_NO_SPLIT("yyyyMMddHHmmssSSS"),

    /**
     * yyyy-MM-dd HH:mm
     */
    DATE_TIME_MINUTE_PATTERN("yyyy-MM-dd HH:mm"),
    /**
     * yyyyMMddHHmm
     */
    DATE_TIME_MINUTE_PATTERN_NO_SPLIT("yyyyMMddHHmm"),

    /**
     * HH:mm:ss
     */
    TIME_PATTERN("HH:mm:ss"),
    /**
     * HHmmss
     */
    TIME_PATTERN_NO_SPLIT("HHmmss"),

    /**
     * HH:mm
     */
    TIME_MINUTE_PATTERN("HH:mm"),
    /**
     * HHmm
     */
    TIME_MINUTE_PATTERN_NO_SPLIT("HHmm"),

    /**
     * yyyy-MM
     */
    MONTH_PATTERN("yyyy-MM"),
    /**
     * yyyyMM
     */
    MONTH_PATTERN_NO_SPLIT("yyyyMM"),

    /**
     * yyyy
     */
    YEAR_PATTERN("yyyy"),

    /**
     * yyyy年MM月dd日
     */
    DATE_PATTERN_CHINESE("yyyy年MM月dd日"),
    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    DATE_TIME_PATTERN_CHINESE("yyyy年MM月dd日 HH时mm分ss秒"),
    /**
     * yyyy年MM月dd日 HH时mm分
     */
    DATE_TIME_MINUTE_PATTERN_CHINESE("yyyy年MM月dd日 HH时mm分"),
    /**
     * MM月dd日
     */
    DATE_PATTERN_CHINESE_NO_YEAR("MM月dd日"),
    /**
     * MM月dd日 HH时mm分ss秒
     */
    DATE_TIME_PATTERN_CHINESE_NO_YEAR("MM月dd日 HH时mm分ss秒"),
    /**
     * MM月dd日 HH时mm分
     */
    DATE_TIME_MINUTE_PATTERN_CHINESE_NO_YEAR("MM月dd日 HH时mm分"),
    /**
     * yyyy年MM月
     */
    MONTH_PATTERN_CHINESE("yyyy年MM月"),
    /**
     * yyyy年
     */
    YEAR_PATTERN_CHINESE("yyyy年"),
    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    DATE_TIME_PATTERN_UTC("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

    /**
     * 默认格式
     * yyyy-MM-dd HH:mm:ss
     */
    DEFAULT(DATE_TIME_PATTERN.getPattern());

    private final String pattern;

    DateFormatEnum(String pattern) {
        this.pattern = pattern;
    }

    /**
     * 根据pattern获取枚举
     *
     * @param pattern 日期格式
     * @return DatePatternEnum
     */
    public static DateFormatEnum getEnum(String pattern) {
        for (DateFormatEnum datePatternEnum : DateFormatEnum.values()) {
            if (datePatternEnum.getPattern().equals(pattern)) {
                return datePatternEnum;
            }
        }
        return DateFormatEnum.DEFAULT;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public String toString() {
        return pattern;
    }
}
