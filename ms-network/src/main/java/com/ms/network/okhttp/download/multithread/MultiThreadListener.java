package com.ms.network.okhttp.download.multithread;

/**
 * @author ms2297248353
 */
public interface MultiThreadListener {

    /**
     * 下载开始
     *
     * @param totalBytes 总大小
     */
    void onStart(long totalBytes);

    /**
     * 下载进度更新
     *
     * @param totalBytes      总大小
     * @param downloadedBytes 已下载大小
     */
    void onProgress(long totalBytes, long downloadedBytes);

    /**
     * 下载完成
     */
    void onFinish();

    /**
     * 下载失败
     */
    <T extends Exception> void onFailure(T e);
}
