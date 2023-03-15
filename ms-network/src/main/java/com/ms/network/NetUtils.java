package com.ms.network;

import java.net.InetAddress;

public class NetUtils {
    /**
     * 判断网络是否连接
     *
     * @return true:已连接 false:未连接
     */
    public static boolean isNetworkConnected() {
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            return !address.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
