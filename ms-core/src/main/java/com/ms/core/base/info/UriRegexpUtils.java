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

package com.ms.core.base.info;

import com.ms.core.base.basic.StringUtils;
import com.ms.core.base.enums.regular.RegexpEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qyg2297248353
 */
public class UriRegexpUtils {
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String HOST = "www";
    private static final String END = "/";
    private static final String LEVEL = ".";
    private static final String START = "://";

    /**
     * 获取链接域名
     * 无法解析返回源链接
     * 中文域名无法解析
     * 无https(s)头部信息
     *
     * @param str 解析链接
     * @return 链接 cjms.cf
     */
    public static String getHost(String str) {
        String group = getWebsite(str);
        int index = 0;
        String s = group.toLowerCase();
        if (s.startsWith(HTTP)) {
            int i = s.indexOf(HTTP);
            index = i + 7;
        }
        if (s.startsWith(HTTPS)) {
            int i = s.indexOf(HTTPS);
            index = i + 8;
        }
        int end = group.length();
        if (group.endsWith(END)) {
            end -= 1;
        }

        String substring = group.substring(index, end);
        if (substring.startsWith(HOST)) {
            int i = substring.indexOf(HOST);
            index = i + 4;
            substring = substring.substring(index);
        }
        return substring;
    }

    /**
     * 获取链接域名
     * 无法解析返回源链接
     * 中文域名无法解析
     *
     * @param str 解析链接
     * @return 域名链接 http(s)://www.cjms.cf/
     */
    public static String getWebsite(String str) {
        str = StringUtils.replaceBlank(str);
        if (isUrl(str)) {
            Pattern r = Pattern.compile(RegexpEnum.REGEX_URL.regex());
            Matcher m = r.matcher(str);
            if (m.find()) {
                String group = m.group();
                int index = 0;
                int end = group.length();
                boolean hasEnd = group.lastIndexOf(END) == org.apache.commons.lang3.StringUtils.ordinalIndexOf(group, END, 2);
                if (!hasEnd) {
                    end = org.apache.commons.lang3.StringUtils.ordinalIndexOf(group, END, 3) + 1;
                }
                String substring = group.substring(index, end);
                if (substring.startsWith(HOST)) {
                    int i = substring.indexOf(HOST);
                    index = i + 4;
                    substring = substring.substring(index);
                }
                if (hasEnd) {
                    substring += '/';
                }
                return substring;
            }
        }
        return str;
    }


    /**
     * 获取链接一级域名
     * 无法解析返回源链接
     * 中文域名无法解析
     * 无https(s)头部信息
     * 注意:例如香港等部分域名 .com.hk 截取将会存在问题，使用getHost获取即可
     *
     * @param str 解析链接
     * @return 链接 cjms.cf
     */
    public static String getMainHost(String str) {
        String substring = getHost(str);
        int firstIndex = substring.indexOf(LEVEL);
        if (firstIndex != substring.lastIndexOf(LEVEL)) {
            substring = substring.substring(firstIndex + 1);
        }
        return substring;
    }

    /**
     * 获取链接一级域名
     * 无法解析返回源链接
     * 中文域名无法解析
     *
     * @param str 解析链接
     * @return 域名链接 http(s)://cjms.cf/
     */
    public static String getMainWebsite(String str) {
        str = getWebsite(str);
        String http = str.substring(0, (str.indexOf(START) + 3));
        String host = getMainHost(str);
        if (!host.endsWith(END)) {
            host += '/';
        }
        return http + host;
    }


    /**
     * 是否为域名链接
     *
     * @param str 效验字符串
     * @return 是否为链接
     */
    public static boolean isUrl(String str) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_URL.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 是否为链接
     * 含Ftp
     *
     * @param str 效验字符串
     * @return 是否为链接
     */
    public static boolean isUrlFtp(String str) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_URL_FTP.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 是否为图片链接
     * gif|png|jpg|jpeg|webp|svg|psd|bmp|tif
     *
     * @param str 效验字符串
     * @return 是否为链接
     */
    public static boolean isImgUrl(String str) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_IMG_URL.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 是否为图片链接
     * 含Ftp
     * gif|png|jpg|jpeg|webp|svg|psd|bmp|tif
     *
     * @param str 效验字符串
     * @return 是否为链接
     */
    public static boolean isImgUrlFtp(String str) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_IMG_URL_FTP.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
