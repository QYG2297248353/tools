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

import com.ms.network.okhttp.factory.OkHttpFactory;
import okhttp3.*;
import okio.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

public class MultiThreadDownloadUtils {
    private static final OkHttpClient client = new OkHttpFactory().create();
    private final String url;
    private final File file;
    private final int threadCount;
    private Logger logger = Logger.getLogger("MultiThreadDownloadUtils");

    public MultiThreadDownloadUtils(String url, String filePath, int threadCount) {
        this.url = url;
        file = new File(filePath);
        this.threadCount = threadCount;
    }

    public void download() {
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            long contentLength = response.body().contentLength();
            response.close();
            long blockSize = (contentLength + threadCount - 1) / threadCount;
            for (int threadId = 0; threadId < threadCount; threadId++) {
                long start = threadId * blockSize;
                long end = (threadId + 1) * blockSize - 1;
                if (threadId == threadCount - 1) {
                    end = contentLength - 1;
                }
                new DownloadThread(url, file, threadId, start, end).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class DownloadThread extends Thread {
        private final String url;
        private final File file;
        private final int threadId;
        private final long start;
        private final long end;

        public DownloadThread(String url, File file, int threadId, long start, long end) {
            this.url = url;
            this.file = file;
            this.threadId = threadId;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.seek(start);

                OkHttpClient threadClient = client.newBuilder()
                        .addInterceptor(new DownloadInterceptor(start))
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Range", "bytes=" + start + "-" + end)
                        .build();
                Call call = threadClient.newCall(request);
                Response response = call.execute();
                InputStream is = response.body().byteStream();

                byte[] buffer = new byte[4096];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                }

                is.close();
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class DownloadInterceptor implements Interceptor {
        private final long start;
        private long downloaded = 0;

        public DownloadInterceptor(long start) {
            this.start = start;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), new MultiThreadListener() {

                        @Override
                        public void onStart(long totalBytes) {
                            logger.info("Thread " + Thread.currentThread().getId() + ": start download, total bytes: " + totalBytes);
                        }

                        @Override
                        public void onProgress(long totalBytes, long downloadedBytes) {
                            downloaded += downloadedBytes;
                            logger.info("Thread " + Thread.currentThread().getId() + ": downloaded " + downloaded + " bytes");
                        }

                        @Override
                        public void onFinish() {
                            logger.info("Thread " + Thread.currentThread().getId() + ": finish download");
                        }

                        @Override
                        public <T extends Exception> void onFailure(T e) {
                            logger.warning("Thread " + Thread.currentThread().getId() + ": download failed, " + e.getMessage());
                        }
                    }))
                    .build();
        }
    }

    class ProgressResponseBody extends ResponseBody {
        private final ResponseBody responseBody;
        private final MultiThreadListener multiThreadListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, MultiThreadListener multiThreadListener) {
            this.responseBody = responseBody;
            this.multiThreadListener = multiThreadListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    multiThreadListener.onProgress(responseBody.contentLength(), totalBytesRead);
                    return bytesRead;
                }
            };
        }
    }

}
