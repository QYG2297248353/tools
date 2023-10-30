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

package com.ms.tools.core.config;

/**
 * 系统配置
 *
 * @author ms2297248353
 */
public class SystemConfiguration {

    /**
     * 系统编码
     */
    public static final String SYSTEM_ENCODING = "UTF-8";
    /**
     * 系统默认语言
     */
    public static final String SYSTEM_LANGUAGE = "zh_CN";
    /**
     * 系统默认时区
     */
    public static final String SYSTEM_TIMEZONE = "GMT+8";
    /**
     * 系统默认国家
     */
    public static final String SYSTEM_COUNTRY = "CN";
    /**
     * 系统默认版本号
     */
    public static final String SYSTEM_VERSION = "1.0.0";
    /**
     * 成功状态码
     */
    public static final String SYSTEM_SUCCESS_CODE = "200";
    /**
     * 失败状态码
     */
    public static final String SYSTEM_FAIL_CODE = "400";
    /**
     * 成功状态码 msg
     */
    public static final String SYSTEM_SUCCESS_MSG = "success";
    /**
     * 失败状态码 msg
     */
    public static final String SYSTEM_FAIL_MSG = "failed";
    /**
     * 系统默认时间格式
     */
    public static final String SYSTEM_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    /**
     * 作者
     */
    public static final String TOOLS_AUTHOR = "萌森";
    /**
     * 团队
     */
    public static final String TOOLS_TEAM = "萌森工作室";
    /**
     * 技术支持-网站
     */
    public static final String TOOLS_SUPPORT_URL = "https://qyg2297248353.top";
    /**
     * 技术支持-邮箱
     */
    public static final String TOOLS_SUPPORT_EMAIL = "qyg2297248353@163.com";
    /**
     * 系统编码
     */
    private static final String SYSTEM_KEY_ENCODING = "MS_PROJECT_ENCODING";
    /**
     * 系统默认语言
     */
    private static final String SYSTEM_KEY_LANGUAGE = "MS_PROJECT_LANGUAGE";
    /**
     * 系统默认时区
     */
    private static final String SYSTEM_KEY_TIMEZONE = "MS_PROJECT_TIMEZONE";
    /**
     * 系统默认国家
     */
    private static final String SYSTEM_KEY_COUNTRY = "MS_PROJECT_COUNTRY";
    /**
     * 系统默认版本号
     */
    private static final String SYSTEM_KEY_VERSION = "MS_PROJECT_VERSION";
    /**
     * 成功状态码
     */
    private static final String SYSTEM_KEY_SUCCESS_CODE = "MS_PROJECT_SUCCESS_CODE";
    /**
     * 失败状态码
     */
    private static final String SYSTEM_KEY_FAIL_CODE = "MS_PROJECT_FAIL_CODE";
    /**
     * 成功状态码 msg
     */
    private static final String SYSTEM_KEY_SUCCESS_MSG = "MS_PROJECT_SUCCESS_MSG";
    /**
     * 失败状态码 msg
     */
    private static final String SYSTEM_KEY_FAIL_MSG = "MS_PROJECT_FAIL_MSG";
    /**
     * 系统默认时间格式
     */
    private static final String SYSTEM_KEY_DATE_FORMAT = "MS_PROJECT_DATE_FORMAT";

    /**
     * 获取系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return System.getenv(SYSTEM_KEY_VERSION) == null ? System.getProperty(SYSTEM_KEY_VERSION, SYSTEM_VERSION) : System.getenv(SYSTEM_KEY_VERSION);
    }

    /**
     * 获取系统编码
     *
     * @return 系统编码
     */
    public static String getSystemEncoding() {
        return System.getenv(SYSTEM_KEY_ENCODING) == null ? System.getProperty(SYSTEM_KEY_ENCODING, SYSTEM_ENCODING) : System.getenv(SYSTEM_KEY_ENCODING);
    }

    /**
     * 获取系统默认语言
     *
     * @return 系统默认语言
     */
    public static String getSystemLanguage() {
        return System.getenv(SYSTEM_KEY_LANGUAGE) == null ? System.getProperty(SYSTEM_KEY_LANGUAGE, SYSTEM_LANGUAGE) : System.getenv(SYSTEM_KEY_LANGUAGE);
    }

    /**
     * 获取系统默认时区
     *
     * @return 系统默认时区
     */
    public static String getSystemTimezone() {
        final String timezone = "user.timezone";
        return System.getenv(SYSTEM_KEY_TIMEZONE) == null ? System.getProperty(SYSTEM_KEY_TIMEZONE, System.getProperty(timezone, SYSTEM_TIMEZONE)) : System.getenv(SYSTEM_KEY_TIMEZONE);
    }

    /**
     * 获取系统默认国家
     *
     * @return 系统默认国家
     */
    public static String getSystemCountry() {
        return System.getenv(SYSTEM_KEY_COUNTRY) == null ? System.getProperty(SYSTEM_KEY_COUNTRY, SYSTEM_COUNTRY) : System.getenv(SYSTEM_KEY_COUNTRY);
    }

    /**
     * 获取系统默认成功状态码
     *
     * @return 系统默认成功状态码
     */
    public static String getSystemSuccessCode() {
        return System.getenv(SYSTEM_KEY_SUCCESS_CODE) == null ? System.getProperty(SYSTEM_KEY_SUCCESS_CODE, SYSTEM_SUCCESS_CODE) : System.getenv(SYSTEM_KEY_SUCCESS_CODE);
    }

    /**
     * 获取系统默认失败状态码
     *
     * @return 系统默认失败状态码
     */
    public static String getSystemFailCode() {
        return System.getenv(SYSTEM_KEY_FAIL_CODE) == null ? System.getProperty(SYSTEM_KEY_FAIL_CODE, SYSTEM_FAIL_CODE) : System.getenv(SYSTEM_KEY_FAIL_CODE);
    }

    /**
     * 获取系统默认成功状态码 msg
     *
     * @return 系统默认成功状态码 msg
     */
    public static String getSystemSuccessMsg() {
        return System.getenv(SYSTEM_KEY_SUCCESS_MSG) == null ? System.getProperty(SYSTEM_KEY_SUCCESS_MSG, SYSTEM_SUCCESS_MSG) : System.getenv(SYSTEM_KEY_SUCCESS_MSG);
    }

    /**
     * 获取系统默认失败状态码 msg
     *
     * @return 系统默认失败状态码 msg
     */
    public static String getSystemFailMsg() {
        return System.getenv(SYSTEM_KEY_FAIL_MSG) == null ? System.getProperty(SYSTEM_KEY_FAIL_MSG, SYSTEM_FAIL_MSG) : System.getenv(SYSTEM_KEY_FAIL_MSG);
    }

    /**
     * 获取系统默认时间格式
     *
     * @return 系统默认时间格式
     */
    public static String getSystemDateFormat() {
        return System.getenv(SYSTEM_KEY_DATE_FORMAT) == null ? System.getProperty(SYSTEM_KEY_DATE_FORMAT, SYSTEM_DATE_FORMAT) : System.getenv(SYSTEM_KEY_DATE_FORMAT);
    }
}
