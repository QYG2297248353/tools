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

package com.ms.network.okhttp.download.multithread;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

/**
 * @author ms2297248353
 */
public class DownloadTask implements Runnable {
    private final String url;
    private final File destFile;
    private final long startPos;
    private final long endPos;
    private final MultiThreadListener multiThreadListener;
    private final OkHttpClient client;

    public DownloadTask(String url, File destFile, long startPos, long endPos, MultiThreadListener multiThreadListener, OkHttpClient client) {
        this.url = url;
        this.destFile = destFile;
        this.startPos = startPos;
        this.endPos = endPos;
        this.multiThreadListener = multiThreadListener;
        this.client = client;
    }

    @Override
    public void run() {
        Request request = new Request.Builder()
                .url(url)
                .header("Range", "bytes=" + startPos + "-" + endPos)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            try (InputStream inputStream = response.body().byteStream()) {
                long totalBytesRead = 0L;
                byte[] buffer = new byte[4096];
                int bytesRead;

                try (OutputStream outputStream = new FileOutputStream(destFile, true)) {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                        multiThreadListener.onProgress(endPos - startPos + 1, totalBytesRead);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
