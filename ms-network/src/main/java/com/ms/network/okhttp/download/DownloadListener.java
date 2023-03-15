package com.ms.network.okhttp.download;

public interface DownloadListener {
    /**
     * 下载开始时回调
     * @param contentLength 文件总字节数
     */
    void onStart(long contentLength);

    /**
     * 下载进度更新时回调
     *
     * @param currentLength 当前已下载的字节数
     * @param contentLength 文件总字节数
     */
    void onProgress(long currentLength, long contentLength);

    /**
     * 下载完成时回调
     */
    void onFinish();

    /**
     * 下载失败时回调
     */
    void onFailure(Exception e);
}
