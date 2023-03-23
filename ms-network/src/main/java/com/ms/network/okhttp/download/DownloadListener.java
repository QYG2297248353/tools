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

package com.ms.network.okhttp.download;

public interface DownloadListener {
    /**
     * 下载开始时回调
     *
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
     *
     * @param e 异常
     */
    void onFailure(Exception e);
}
