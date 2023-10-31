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

package com.ms.tools.components.cloud.qiniu;

import com.ms.tools.components.cloud.qiniu.config.QiNiuClient;
import com.ms.tools.components.cloud.qiniu.config.QiNiuCloudConfig;
import com.ms.tools.components.cloud.qiniu.enums.FileTypeEnum;
import com.ms.tools.components.cloud.qiniu.utils.UploadFileUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author ms
 */
@Service
public class QiNiuUploadService {

    @Resource
    private QiNiuCloudConfig qiNiuCloudConfig;


    /**
     * 上传文件
     *
     * @param file 文件
     * @param type 文件类型
     * @return 返回文件的url
     */
    public String uploadQiNiu(File file, FileTypeEnum type) {
        UploadManager uploadManager = QiNiuClient.getInstance().getUploadManager();
        String bucket = type.getBucket();
        String token = buildToken(bucket);
        try {
            Response response = uploadManager.put(file, null, token);
            DefaultPutRet defaultPutRet = response.jsonToObject(DefaultPutRet.class);
            return defaultPutRet.key;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private String buildToken(String bucket) {
        Auth auth = Auth.create(qiNiuCloudConfig.getAccessKey(), qiNiuCloudConfig.getSecretKey());
        return auth.uploadToken(bucket);
    }

    /**
     * 上传文件
     *
     * @param file         文件
     * @param fileTypeEnum 文件类型
     * @return 返回文件的url
     */
    public String uploadQiNiu(MultipartFile file, FileTypeEnum fileTypeEnum) {
        UploadManager uploadManager = QiNiuClient.getInstance().getUploadManager();
        String bucket = fileTypeEnum.getBucket();
        String token = buildToken(bucket);
        try {
            // file hash key 计算
            String hash = UploadFileUtils.getSha256(file);
            String key = fileTypeEnum.getPath() + hash;
            Response response = uploadManager.put(file.getBytes(), key, token);
            DefaultPutRet defaultPutRet = response.jsonToObject(DefaultPutRet.class);
            return qiNiuCloudConfig.getDomain() + defaultPutRet.key;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
