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

    private final UploadManager uploadManager;


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
