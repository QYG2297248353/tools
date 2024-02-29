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

import com.alibaba.fastjson2.JSON;
import com.ms.tools.minio.exception.MsMinioException;
import com.ms.tools.minio.properties.MsMinioProperties;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Minio bucket service
 * Minio 桶服务
 *
 * @author ms
 */
@Component
public class MinioBucketService {

    private static final Logger log = Logger.getLogger(MinioBucketService.class.getName());

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
     * @throws MsMinioException MsMinioException
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
     * @throws MsMinioException MsMinioException
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
     * @throws MsMinioException MsMinioException
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
     * @throws MsMinioException MsMinioException
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
     * @throws MsMinioException MsMinioException
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
     * Remove bucket
     * 删除桶 递归删除桶内所有文件
     *
     * @param bucketName bucket name
     * @throws MsMinioException MsMinioException
     */
    public void removeBucket(String bucketName) throws MsMinioException {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> result : results) {
                Item item = result.get();
                minioClient.removeObject(RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(item.objectName())
                        .build());
            }
            removeEmptyBucket(bucketName);
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Remove All bucket
     * 删除所有桶
     * 递归删除所有桶内所有文件
     * 递归删除所有桶
     *
     * @throws MsMinioException MsMinioException
     */
    public void removeAllBucket() throws MsMinioException {
        try {
            Iterator<Bucket> bucketIterator = listBucketsIterator();
            while (bucketIterator.hasNext()) {
                Bucket bucket = bucketIterator.next();
                log.warning("remove bucket: " + bucket.name());
                removeBucket(bucket.name());
            }
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
     * @throws MsMinioException MsMinioException
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
     * @throws MsMinioException MsMinioException
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
     * @throws MsMinioException MsMinioException
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

    /**
     * Remove bucket policy
     * 删除桶策略
     *
     * @param bucketName bucket name
     * @throws MsMinioException MsMinioException
     */
    public void removeBucketPolicy(String bucketName) throws MsMinioException {
        try {
            minioClient.deleteBucketPolicy(DeleteBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Set bucket policy
     * 设置桶策略
     *
     * @param bucketName bucket name
     * @param policy     policy
     * @throws MsMinioException MsMinioException
     */
    public void setBucketPolicy(String bucketName, String policy) throws MsMinioException {
        if (!JSON.isValid(policy)) {
            throw new MsMinioException("policy is not valid json");
        }
        try {
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .config(policy)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * Get bucket policy
     *
     * @param bucketName bucket name
     * @return policy
     * @throws MsMinioException MsMinioException
     */
    public String getBucketPolicy(String bucketName) throws MsMinioException {
        try {
            return minioClient.getBucketPolicy(GetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

}
