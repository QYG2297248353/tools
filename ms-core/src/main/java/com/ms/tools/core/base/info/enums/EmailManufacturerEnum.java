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

package com.ms.tools.core.base.info.enums;


import com.ms.core.base.basic.Strings;

/**
 * 常见邮箱
 * 邮箱基本信息
 * 邮箱官网
 *
 * @author ms2297248353
 */

public enum EmailManufacturerEnum {
    /**
     * gmail邮箱
     */
    EMAIL_GMAIL("gmail", "@gmail.com", "谷歌", "https://mail.google.com/"),
    /**
     * outlook邮箱
     */
    EMAIL_OUTLOOK("outlook", "@outlook.com", "微软", "https://outlook.live.com/"),

    /**
     * icloud邮箱
     */
    EMAIL_ICLOUD("icloud", "@icloud.com", "苹果", "https://www.icloud.com/"),
    /**
     * yahoo邮箱
     */
    EMAIL_YAHOO("yahoo", "@yahoo.com", "雅虎", "https://mail.yahoo.com/"),
    /**
     * aol邮箱
     */
    EMAIL_AOL("aol", "@aol.com", "雅虎", "https://mail.aol.com/"),
    /**
     * 163邮箱
     */
    EMAIL_163("163", "@163.com", "网易", "https://mail.163.com/"),
    /**
     * 126邮箱
     */
    EMAIL_126("126", "@126.com", "网易", "https://mail.126.com/"),
    /**
     * qq邮箱
     */
    EMAIL_QQ("qq", "@qq.com", "腾讯", "https://mail.qq.com/"),
    /**
     * sina邮箱
     */
    EMAIL_SINA("sina", "@sina.com", "新浪", "https://mail.sina.com.cn/"),
    /**
     * 189邮箱
     */
    EMAIL_189("189", "@189.cn", "天翼", "https://mail.189.cn/"),
    /**
     * 139邮箱
     */
    EMAIL_139("139", "@139.com", "中国移动", "https://mail.10086.cn/"),
    /**
     * 21cn邮箱
     */
    EMAIL_21CN("21cn", "@21cn.com", "天翼", "https://mail.21cn.com/"),
    /**
     * 263邮箱
     */
    EMAIL_263("263", "@263.com", "236云通信", "https://mail.263.net/"),
    /**
     * 58邮箱
     */
    EMAIL_58("58", "@58.com", "58集团", "https://mail.58.com/"),
    /**
     * 188邮箱
     */
    EMAIL_188("188", "@188.com", "网易", "https://mail.188.com/");

    /**
     * 邮箱特征符号
     */
    private final String alias;

    /**
     * 邮箱地址后缀
     */
    private final String suffix;

    /**
     * 邮箱厂商
     */
    private final String manufacturer;

    /**
     * 邮箱官网
     */
    private final String website;

    EmailManufacturerEnum(String alias, String suffix, String manufacturer, String website) {
        this.alias = alias;
        this.suffix = suffix;
        this.manufacturer = manufacturer;
        this.website = website;
    }

    /**
     * 根据邮箱地址后缀获取邮箱厂商
     *
     * @param suffix 邮箱地址后缀
     * @return 邮箱厂商
     */
    public static EmailManufacturerEnum getEmailManufacturer(String suffix) {
        if (!suffix.startsWith(Strings.AT)) {
            suffix = Strings.AT + suffix;
        }
        for (EmailManufacturerEnum emailManufacturerEnum : EmailManufacturerEnum.values()) {
            if (emailManufacturerEnum.getSuffix().equals(suffix)) {
                return emailManufacturerEnum;
            }
        }
        return null;
    }

    public String getAlias() {
        return alias;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getWebsite() {
        return website;
    }


}
