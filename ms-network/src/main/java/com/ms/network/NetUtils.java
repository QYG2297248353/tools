package com.ms.network;

import java.net.InetAddress;

/**
 * 网络工具类
 *
 * @author ms2297248353
 */
public class NetUtils {

    /**
     * 判断网络是否连接
     *
     * @return true:已连接 false:未连接
     */
    public static boolean isNetworkConnected() {
        try {
            InetAddress address = InetAddress.getByName("8.8.8.8");
            return !address.equals("");
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
