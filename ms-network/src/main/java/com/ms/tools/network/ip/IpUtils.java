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

package com.ms.tools.network.ip;

import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.enums.regular.RegexpEnum;
import com.ms.tools.network.ip.core.IPAddressFactory;
import com.ms.tools.network.ip.core.IPLocation;

import javax.servlet.http.HttpServletRequest;
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
        IPAddressFactory factory = IPAddressFactory.getInstance(dataPath);
        return factory.getIPLocation(ip);
    }

    /**
     * 获取IP地址
     * 请求头
     *
     * @param request 请求
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (Strings.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (Strings.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Strings.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (Strings.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (Strings.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断是否是IP地址
     * 含局域网
     *
     * @param ip the str
     * @return the boolean
     */
    public static boolean isIp(String ip) {
        ip = Strings.replaceBlank(ip);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_IP_ADD.regex());
        Matcher m = r.matcher(ip);
        return m.matches();
    }

    /**
     * 判断是否是IP地址
     * 含局域网
     * 含端口号
     *
     * @param ip the str
     * @return the boolean
     */
    public static boolean isIpPort(String ip) {
        ip = Strings.replaceBlank(ip);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_IP_ADD_PORT.regex());
        Matcher m = r.matcher(ip);
        return m.matches();
    }


    /**
     * 判断是否是IPv4地址
     * 排除局域网
     *
     * @param ip the str
     * @return the boolean
     */
    public static boolean isIpv4(String ip) {
        ip = Strings.replaceBlank(ip);
        if (isIp(ip)) {
            Pattern r = Pattern.compile(RegexpEnum.REGEX_LAN_IP_ADD.regex());
            Matcher m = r.matcher(ip);
            return !m.matches();
        }
        return false;
    }

    /**
     * 判断是否是IPv6地址
     *
     * @param ip the str
     * @return the boolean
     */
    public static boolean isIpv6(String ip) {
        ip = Strings.replaceBlank(ip);
        if (isIp(ip)) {
            Pattern r = Pattern.compile(RegexpEnum.REGEX_IPV6_ADD.regex());
            Matcher m = r.matcher(ip);
            return m.matches();
        }
        return false;
    }

    /**
     * 判断是否是内网IP
     *
     * @param ip ip
     * @return 是否是内网IP
     */
    public static boolean isLanIp(String ip) {
        String regex = "^(10\\.(\\d+)\\.|172\\.(1[6-9]|2[0-9]|3[0-1])\\.|192\\.168\\.)\\d+\\.\\d+$";
        return Pattern.matches(regex, ip);
    }

}
