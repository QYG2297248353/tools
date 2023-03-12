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

package com.ms.network.client;

import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * 文件下载
 * 工具类
 *
 * @author ms2297248353
 */
public class FileDownloadClient {
    Logger log = Logger.getLogger(FileDownloadClient.class.getName());
    private Client client;

    public FileDownloadClient(Client client) {
        this.client = client;
    }

    public void download(String url, File file) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.getOkClient().newCall(request).execute();

        ResponseBody body = response.body();
        long contentLength = body.contentLength();

        InputStream inputStream = body.byteStream();
        FileOutputStream outputStream = new FileOutputStream(file);

        byte[] buffer = new byte[4096];
        int bytesRead;
        long totalBytesRead = 0;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
            // 优美打印下载进度条
            String must = "\r" + (int) (totalBytesRead * 100 / contentLength) + "%";
            log.info(must);
            // System.err.print(must);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void download(String url, File file, ProgressListener listener) throws IOException {
        Request request = new Request.Builder().url(url).build();

        client.getOkClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                long contentLength = body.contentLength();

                InputStream inputStream = body.byteStream();
                FileOutputStream outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[4096];
                int bytesRead;
                long totalBytesRead = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                    listener.onProgress(totalBytesRead, contentLength);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
                listener.onComplete();
            }
        });
    }

    public interface ProgressListener {
        void onProgress(long bytesRead, long contentLength);

        void onComplete();

        void onError(IOException e);
    }


}
