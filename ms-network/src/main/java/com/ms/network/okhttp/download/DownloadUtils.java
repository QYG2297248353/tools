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

import com.ms.core.base.size.FileSizeUtils;
import com.ms.network.okhttp.factory.OkHttpFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * 下载工具类
 * 单线程下载
 *
 * @author ms
 */
public class DownloadUtils {
    private static Logger log = Logger.getLogger(DownloadUtils.class.getName());

    private String uri;

    private String savePath;

    private OkHttpClient client;

    private DownloadListener listener;

    public DownloadUtils(String uri, String path) {
        this.uri = uri;
        savePath = path;
        client = new OkHttpFactory().create();
    }

    public DownloadUtils(String uri, String path, OkHttpClient client) {
        this.uri = uri;
        savePath = path;
        this.client = client;
    }

    public DownloadUtils(String uri, String path, DownloadListener listener) {
        this.uri = uri;
        savePath = path;
        this.listener = listener;
        client = new OkHttpFactory().create();
    }

    public DownloadUtils(String uri, String path, OkHttpClient client, DownloadListener listener) {
        this.uri = uri;
        savePath = path;
        this.listener = listener;
        this.client = client;
    }

    /**
     * 下载文件，支持断点续传
     *
     * @param url      文件的下载地址
     * @param savePath 文件的保存路径
     * @param listener 下载监听器
     */
    public static void download(String url, String savePath, DownloadListener listener) {
        OkHttpClient client = new OkHttpFactory().create();
        download(url, savePath, client, listener);
    }

    /**
     * 下载文件，支持断点续传
     *
     * @param url      文件的下载地址
     * @param savePath 文件的保存路径
     * @param client   下载客户端
     * @param listener 下载监听器
     */
    public static void download(String url, String savePath, OkHttpClient client, DownloadListener listener) {
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ResponseBody body = response.body();
            assert body != null;
            long contentLength = body.contentLength();
            if (contentLength == -1) {
                log.warning("无法获取文件大小，下载失败");
                return;
            }
            if (listener != null) {
                listener.onStart(contentLength);
            }
            BufferedSource source = body.source();
            String contentRange = response.header("Content-Range");
            long currentLength = 0;
            if (contentRange != null) {
                currentLength = Long.parseLong(contentRange.split("/")[0].split(" ")[1]);
            }
            File file = new File(savePath);
            if (currentLength == contentLength) {
                if (listener != null) {
                    listener.onFinish();
                }
                return;
            }
            BufferedSink sink = Okio.buffer(Okio.appendingSink(file));
            if (currentLength > 0) {
                source.skip(currentLength);
            }
            byte[] buffer = new byte[8192];
            int read;
            while ((read = source.read(buffer)) != -1) {
                sink.write(buffer, 0, read);
                currentLength += read;
                if (listener != null) {
                    listener.onProgress(currentLength, contentLength);
                    if (listener.isLog()) {
                        log.info("下载进度：" + FileSizeUtils.BTrim.convert(currentLength) + "/" + FileSizeUtils.BTrim.convert(contentLength));
                    }
                }
            }
            sink.flush();
            sink.close();
            body.close();
            if (listener != null) {
                listener.onFinish();
            }
        } catch (Exception e) {
            if (listener != null) {
                listener.onFailure(e);
                log.warning(e.getMessage());
            }
        }
    }

    /**
     * 下载文件，支持断点续传
     */
    public void download() {
        DownloadUtils.download(uri, savePath, client, listener);
    }
}