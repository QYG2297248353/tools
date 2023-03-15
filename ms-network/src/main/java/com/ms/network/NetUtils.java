package com.ms.network;

import com.ms.network.okhttp.download.DownloadListener;
import com.ms.network.okhttp.download.DownloadUtils;

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
        String uri = "https://releases.ubuntu.com/22.04.2/ubuntu-22.04.2-desktop-amd64.iso";
        String path = "D:\\download\\ubuntu-22.04.2-desktop-amd64.iso";
        DownloadUtils.download(uri, path, new DownloadListener() {
            @Override
            public void onStart(long contentLength) {
                System.err.println("下载开始，文件大小：" + contentLength + "字节");
            }

            @Override
            public void onProgress(long currentLength, long contentLength) {
                System.err.println("下载进度：" + currentLength + "/" + contentLength);
            }

            @Override
            public void onFinish() {
                System.err.println("下载完成");
            }

            @Override
            public void onFailure(Exception e) {
                System.err.println("下载失败：" + e.getMessage());
            }
        });

    }
}
