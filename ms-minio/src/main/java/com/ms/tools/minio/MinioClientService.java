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

import com.ms.tools.minio.properties.MsMinioProperties;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MinioClientService {

    @Resource
    private MsMinioProperties msMinioProperties;

    /**
     * 创建客户端
     *
     * @return MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        return LocalMinioClient.create(msMinioProperties);
    }


    /**
     * 创建客户端
     *
     * @param msMinioProperties minio配置
     * @return MinioClient
     */
    public MinioClient create(MsMinioProperties msMinioProperties) {
        return MinioClient.builder()
                .endpoint(msMinioProperties.getEndpoint())
                .credentials(msMinioProperties.getAccessKey(), msMinioProperties.getSecretKey())
                .build();
    }


}
