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

public class IpUtils {

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

}
