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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DownloadManager {
    private final String url;
    private final File destFile;
    private final int threadCount;
    private final MultiThreadListener multiThreadListener;
    private final OkHttpClient client;
    private volatile boolean isPaused;
    private volatile boolean isStopped;

    public DownloadManager(String url, File destFile, int threadCount, MultiThreadListener multiThreadListener, OkHttpClient client) {
        this.url = url;
        this.destFile = destFile;
        this.threadCount = threadCount;
        this.multiThreadListener = multiThreadListener;
        this.client = client;
    }

    public void download() throws IOException {
        long contentLength = getContentLength(url, client);
        long blockSize = (contentLength + threadCount - 1) / threadCount;

        List<DownloadTask> taskList = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            long startPos = i * blockSize;
            long endPos = (i + 1) * blockSize - 1;
            if (i == threadCount - 1) {
                endPos = contentLength - 1;
            }
            taskList.add(new DownloadTask(url, destFile, startPos, endPos, multiThreadListener, client));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<?>> futureList = new ArrayList<>();

        for (DownloadTask task : taskList) {
            futureList.add(executorService.submit(task));
        }

        for (Future<?> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    private long getContentLength(String url, OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            long contentLength = response.body().contentLength();
            response.body().close();

            return contentLength;
        }
    }

    // 暂停下载
    public void pause() {
        isPaused = true;
    }

    // 继续下载
    public void resume() {
        isPaused = false;
    }

    // 停止下载
    public void stop() {
        isStopped = true;
    }

    // 取消下载
    public void cancel() {
        isStopped = true;
        destFile.delete();
    }
}
