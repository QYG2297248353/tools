package com.ms.network.okhttp.download;

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
 *
 * @author ms
 */
public class DownloadUtils {
    private static Logger logger = Logger.getLogger("DownloadUtils");


    /**
     * 下载文件，支持断点续传
     *
     * @param url      文件的下载地址
     * @param savePath 文件的保存路径
     * @param listener 下载监听器
     */
    public static void download(final String url, final String savePath, final DownloadListener listener) {
        try {
            OkHttpClient client = new OkHttpFactory().create();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ResponseBody body = response.body();
            assert body != null;
            long contentLength = body.contentLength();
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
                logger.warning(e.getMessage());
            }
        }
    }

    /**
     * 存储文件下载进度到本地
     * <p>
     *     创建与下载文件同名的文件，以.ms为文件扩展名，文件内容为下载进度和文件总长度，单位为字节，此文件用于断点续传
     *     例如：下载文件为：/Users/ms/Downloads/1.txt
     *     则创建的文件为：/Users/ms/Downloads/1.txt.ms
     *     此方法会在下载完成后删除此文件
     * @Param file 文件
     * @Param progress 下载进度
     * @Param total 文件总长度
     */
    private void saveProgress(File cache, long progress, long total){
        try {
            if (progress == total && cache.exists()) {
                cache.delete();
            }
            cache.createNewFile();
            BufferedSink sink = Okio.buffer(Okio.sink(cache));
            sink.writeUtf8(progress + "/" + total);
            sink.flush();
            sink.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
