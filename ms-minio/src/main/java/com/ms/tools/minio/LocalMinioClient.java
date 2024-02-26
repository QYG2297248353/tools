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

import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.minio.properties.MsMinioProperties;
import io.minio.MinioClient;

/**
 * @author ms
 */
public class LocalMinioClient {


    /**
     * 创建客户端
     *
     * @param endpoint  服务地址
     * @param accessKey 访问密钥
     * @param secretKey 访问密钥
     * @return MinioClient
     */
    public static MinioClient create(String endpoint, String accessKey, String secretKey) {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }

    /**
     * 创建客户端
     *
     * @param properties 配置文件
     * @return MinioClient
     */
    public static MinioClient create(MsMinioProperties properties) {
        MinioClient.Builder builder = MinioClient.builder().endpoint(properties.getEndpoint()).credentials(properties.getAccessKey(), properties.getSecretKey());
        if (Strings.isNotBlank(properties.getRegion())) {
            builder.region(properties.getRegion());
        }
        return builder.build();
    }

}
