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

package com.ms.network.ip;

import com.ms.core.base.basic.StringUtils;
import com.ms.core.base.enums.regular.RegexpEnum;
import com.ms.network.ip.core.IPAddressFactory;
import com.ms.network.ip.core.IPLocation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpUtils {
    /**
     * 获取IP地址信息
     * 数据来源
     * <a href="https://cz88.net/">纯真数据库</a>
     *
     * @param ip       ip地址
     * @param dataPath 数据文件路径
     * @return IP地址信息
     */
    public static IPLocation getIPLocation(String ip, String dataPath) {
        IPAddressFactory factory = new IPAddressFactory(dataPath);
        return factory.getIPLocation(ip);
    }

    /**
     * 获取IP地址
     *
     * @param ip ip地址
     * @return ip地址
     */
    public static String getIpAddr(String ip) {
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return "";
        }
        String[] ips = ip.split(",");
        for (String strIp : ips) {
            if (!"unknown".equalsIgnoreCase(strIp)) {
                ip = strIp;
                break;
            }
        }
        return ip;
    }

    /**
     * 获取IP地址
     *
     * @param ip ip地址
     * @return ip地址
     */
    public static String getIpAddr(String[] ip) {
        if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip[0])) {
            return "";
        }
        for (String strIp : ip) {
            if (!"unknown".equalsIgnoreCase(strIp)) {
                ip[0] = strIp;
                break;
            }
        }
        return ip[0];
    }

    /**
     * 判断是否是IP地址
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isIp(String str) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_IP_ADD.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否是IPv4地址
     * 排除局域网
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isIpv4(String str) {
        str = StringUtils.replaceBlank(str);
        if (isIp(str)) {
            Pattern r = Pattern.compile(RegexpEnum.REGEX_LAN_IP_ADD.regex());
            Matcher m = r.matcher(str);
            return !m.matches();
        }
        return false;
    }

}