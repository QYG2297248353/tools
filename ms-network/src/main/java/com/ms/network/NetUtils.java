package com.ms.network;

import com.ms.network.okhttp.download.multithread.MultiThreadDownloadUtils;

import java.net.InetAddress;

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

    public static void main(String[] args) {
        final String uri = "https://releases.ubuntu.com/22.04.2/ubuntu-22.04.2-desktop-amd64.iso";
        final String path = "D:\\download\\ubuntu-22.04.2-desktop-amd64.iso";

        new MultiThreadDownloadUtils(uri, path, 10)
                .download();
    }
}
