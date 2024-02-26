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

package com.ms.tools.minio;

import com.ms.tools.minio.exception.MsMinioException;
import com.ms.tools.minio.properties.MsMinioProperties;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Minio bucket service
 * Minio 桶服务
 *
 * @author ms
 */
@Component
public class MinioObjectService {

    private final MinioClient minioClient;
    @Resource
    private MsMinioProperties msMinioProperties;

    public MinioObjectService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 获取桶的对象列表
     *
     * @param bucketName 桶名称
     * @param prefix     前缀
     * @param recursive  是否递归
     * @return 对象列表
     */
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive) throws MsMinioException {
        try {
            return minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(prefix)
                    .recursive(recursive)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }
}
