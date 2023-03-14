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

package com.ms.network.http;


import com.ms.core.base.enums.ip.BaseIp;
import com.ms.network.http.servlet.CacheHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * request 请求工具
 *
 * @author ms2297248353
 */
public class RequestUtils {

    /**
     * 复制请求
     *
     * @param request 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static ServletInputStream copyStream(HttpServletRequest request) throws IOException {
        CacheHttpServletRequest cacheHttpServletRequest = new CacheHttpServletRequest(request);
        return cacheHttpServletRequest.getInputStream();
    }

    /**
     * 复制请求
     *
     * @param request 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static HttpServletRequest copy(HttpServletRequest request) throws IOException {
        return new CacheHttpServletRequest(request);
    }


    /**
     * 获取客户端IP
     *
     * @param request request
     * @return 返回ip
     */
    public static String getIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || BaseIp.IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || BaseIp.IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || BaseIp.IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (BaseIp.IP_LOCAL.equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    // ignore
                }
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > BaseIp.IP_LEN) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
