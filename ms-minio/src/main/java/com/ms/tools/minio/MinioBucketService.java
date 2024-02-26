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
import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Minio bucket service
 * Minio 桶服务
 *
 * @author ms
 */
@Component
public class MinioBucketService {

    private final MinioClient minioClient;
    @Resource
    private MsMinioProperties msMinioProperties;

    public MinioBucketService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * Bucket List
     * 桶列表
     *
     * @return bucket list
     */
    public List<Bucket> listBuckets() throws MsMinioException {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Bucket List
     * 桶列表
     *
     * @return bucket list
     */
    public Iterator<Bucket> listBucketsIterator() throws MsMinioException {
        try {
            return minioClient.listBuckets().iterator();
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Bucket exists
     * 桶是否存在
     *
     * @param bucketName bucket name
     * @return boolean
     */
    public boolean bucketExists(String bucketName) throws MsMinioException {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Create bucket
     * 创建桶
     *
     * @param bucketName bucket name
     */
    public void createBucket(String bucketName) throws MsMinioException {
        if (!bucketExists(bucketName)) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            } catch (Exception e) {
                throw new MsMinioException(e);
            }
        }
    }


    /**
     * Remove Empty bucket
     * 删除空桶
     *
     * @param bucketName bucket name
     */
    public void removeEmptyBucket(String bucketName) throws MsMinioException {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Set bucket tags
     * 设置桶标签
     *
     * @param bucketName bucket name
     * @param tags       tags
     */
    public void setBucketTags(String bucketName, Map<String, String> tags) throws MsMinioException {
        try {
            minioClient.setBucketTags(SetBucketTagsArgs.builder()
                    .bucket(bucketName)
                    .tags(tags)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Delete bucket tags
     * 删除桶标签
     *
     * @param bucketName bucket name
     */
    public void deleteBucketTags(String bucketName) throws MsMinioException {
        try {
            minioClient.deleteBucketTags(DeleteBucketTagsArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Get bucket tags
     * 获取桶标签
     *
     * @param bucketName bucket name
     * @return tags
     */
    public Map<String, String> getBucketTags(String bucketName) throws MsMinioException {
        try {
            return minioClient.getBucketTags(GetBucketTagsArgs.builder()
                    .bucket(bucketName)
                    .build()).get();
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }


}
