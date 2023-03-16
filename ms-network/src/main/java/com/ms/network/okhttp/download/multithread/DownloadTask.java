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
