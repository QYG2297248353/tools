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

package com.ms.tools.network.okhttp.download.multithread;

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
     *
     * @param <T> 异常类型
     * @param e   异常
     */
    <T extends Exception> void onFailure(T e);
}
