package com.ms.tools.components.cloud.qiniu.config;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;

/**
 * 七牛云客户端
 * <p>
 * 单例对象
 *
 * @author ms
 */
public class QiNiuClient {

    private static volatile QiNiuClient instance = null;

    private UploadManager uploadManager;


    private QiNiuClient() {
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        uploadManager = new UploadManager(cfg);
    }

    public static QiNiuClient getInstance() {
        if (instance == null) {
            synchronized (QiNiuClient.class) {
                if (instance == null) {
                    instance = new QiNiuClient();
                }
            }
        }
        return instance;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }


}
