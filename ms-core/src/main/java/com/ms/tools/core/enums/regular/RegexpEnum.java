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

package com.ms.tools.core.enums.regular;

/**
 * @author qyg2297248353
 */
public enum RegexpEnum {
    /**
     * 邮箱
     */
    REGEX_EMAIL(RegexpConstant.REGEX_EMAIL, "正则表达式：验证邮箱", "是否为邮箱效验"),
    REGEX_PHONE(RegexpConstant.REGEX_PHONE, "正则表达式：验证手机号", "是否为手机号效验"),
    REGEX_UCC(RegexpConstant.REGEX_UCC, "正则表达式：验证统一信用代码", "是否为统一信用代码效验"),
    REGEX_IP_ADD(RegexpConstant.REGEX_IP_ADD, "正则表达式：验证IPV4地址(含局域网)", "是否为IPV4地址(含局域网)效验"),
    REGEX_IP_ADD_PORT(RegexpConstant.REGEX_IP_ADD_PORT, "正则表达式：验证IPV4地址(含局域网含端口号)", "是否为IPV4地址(含局域网含端口号)效验"),
    REGEX_LAN_IP_ADD(RegexpConstant.REGEX_LAN_IP_ADD, "正则表达式：验证IPV4地址", "是否为IPV4地址效验"),
    REGEX_IPV6_ADD(RegexpConstant.REGEX_IPV6_ADD, "正则表达式：验证IPV6地址", "是否为IPV6地址效验"),
    REGEX_STRING(RegexpConstant.REGEX_STRING, "正则表达式：匹配空格、回车、换行符、制表符", "是否为空格、回车、换行符、制表符效验"),
    REGEX_URL(RegexpConstant.REGEX_URL, "正则表达式：链接匹配", "是否url链接,ip,与本地链接均为false"),
    REGEX_URL_FTP(RegexpConstant.REGEX_URL_FTP, "正则表达式：链接(FTP)匹配", "是否url链接"),
    REGEX_IMG_URL(RegexpConstant.REGEX_IMG_URL, "正则表达式：图片链接匹配", "是否url图片链接"),
    REGEX_IMG_URL_FTP(RegexpConstant.REGEX_IMG_URL_FTP, "正则表达式：图片(FTP)链接匹配", "是否url图片链接"),
    REGEX_HTML_LABEL(RegexpConstant.REGEX_HTML_LABEL, "正则表达式：HTML标签匹配", "是否HTML标签"),
    REGEX_STYLE_LABEL(RegexpConstant.REGEX_STYLE_LABEL, "正则表达式：HTML标签样式匹配", "是否HTML标签"),
    REGEX_SCRIPT_LABEL(RegexpConstant.REGEX_SCRIPT_LABEL, "正则表达式：HTML标签JS匹配", "是否uHTML标签");

    private String regex;
    private String explain;
    private String description;

    RegexpEnum(String regex, String explain, String description) {
        this.regex = regex;
        this.explain = explain;
        this.description = description;
    }

    public String regex() {
        return regex;
    }

    public String explain() {
        return explain;
    }

    public String description() {
        return description;
    }

    static class RegexpConstant {
        /**
         * 正则表达式：验证邮箱
         */
        public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        /**
         * 正则表达式：验证手机号
         */
        public static final String REGEX_PHONE = "^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$";
        /**
         * 正则表达式：验证统一信用代码
         */
        public static final String REGEX_UCC = "^([1-9|A|N|Y])([1-9])([0-9]{6})[A-Z0-9]{10}$";
        /**
         * 正则表达式：验证IPV4地址(含局域网)
         */
        public static final String REGEX_IP_ADD = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        /**
         * 正则表达式：验证IPV4地址(含局域网含端口号)
         * 端口号可选
         */
        public static final String REGEX_IP_ADD_PORT = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?):\\d{1,5}";
        /**
         * 正则表达式：验证IPV4地址
         */
        public static final String REGEX_LAN_IP_ADD = "(?:(?:10(?:(?:\\.1[0-9][0-9])|(?:\\.2[0-4][0-9])|(?:\\.25[0-5])|(?:\\.[1-9][0-9])|(?:\\.[0-9])))|(?:172(?:\\.(?:1[6-9])|(?:2[0-9])|(?:3[0-1])))|(?:192\\.168))(?:(?:\\.1[0-9][0-9])|(?:\\.2[0-4][0-9])|(?:\\.25[0-5])|(?:\\.[1-9][0-9])|(?:\\.[0-9])){2}";
        /**
         * 正则表达式：验证IPV6地址
         */
        public static final String REGEX_IPV6_ADD = "([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}";
        /**
         * 正则表达式：HTML标签
         */
        public static final String REGEX_HTML_LABEL = "<[^>]+>";
        /**
         * 正则表达式：HTML-style标签
         */
        public static final String REGEX_STYLE_LABEL = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        /**
         * 正则表达式：HTML-script标签
         */
        public static final String REGEX_SCRIPT_LABEL = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        /**
         * 正则表达式：匹配空格、回车、换行符、制表符
         */
        public static String REGEX_STRING = "\\s*|\t|\r|\n";
        /**
         * 正则表达式：链接匹配
         */
        public static String REGEX_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)";
        /**
         * 正则表达式：链接+Ftp匹配
         */
        public static String REGEX_URL_FTP = "(http|https|ftp):\\/\\/([\\w.]+\\/?)\\S*";
        /**
         * 正则表达式：Url图片链接匹配
         */
        public static String REGEX_IMG_URL = "(http|https):\\/\\/([\\w.]+\\/?)\\S*(\\.+(gif|png|jpg|jpeg|webp|svg|psd|bmp|tif))$";
        /**
         * 正则表达式：Url图片链接匹配(含ftp)
         */
        public static String REGEX_IMG_URL_FTP = "(http|https|ftp):\\/\\/([\\w.]+\\/?)\\S*(\\.+(gif|png|jpg|jpeg|webp|svg|psd|bmp|tif))$";
    }
}
