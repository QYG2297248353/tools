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

package com.ms.network;

import java.net.InetAddress;

/**
 * 网络工具类
 *
 * @author ms2297248353
 */
public class NetUtils {

    /**
     * 判断网络是否正常
     *
     * @return true:已连接 false:未连接
     */
    public static boolean isNetworkConnected() {
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            return address != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取本机网卡IP
     *
     * @return 本机网卡IP
     */
    public static String getLocalIp() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }
}
