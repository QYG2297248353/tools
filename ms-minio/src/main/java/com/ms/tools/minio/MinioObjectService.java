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
import com.ms.tools.minio.model.MinioListObject;
import com.ms.tools.minio.properties.MsMinioProperties;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

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
     * @return 对象列表
     * @throws MsMinioException Minio异常
     */
    public Iterable<Result<Item>> listAllObjects(String bucketName) throws MsMinioException {
        return listObjects(bucketName, null, true);
    }

    /**
     * 获取桶的对象列表
     *
     * @param bucketName 桶名称
     * @param recursive  是否递归
     * @return 对象列表
     * @throws MsMinioException Minio异常
     */
    public Iterable<Result<Item>> listObjects(String bucketName, boolean recursive) throws MsMinioException {
        return listObjects(bucketName, null, recursive);
    }


    /**
     * 获取桶的对象列表
     */
    public List<MinioListObject> listObjects(String bucketName) throws MsMinioException {
        try {
            List<MinioListObject> dos = new ArrayList<>();
            Iterable<Result<Item>> results = listObjects(bucketName, false);
            for (Result<Item> result : results) {
                Item item = result.get();
                MinioListObject object = new MinioListObject(item);
                if (item.isDir()) {
                    updateListObject(bucketName, object, item);
                }
                dos.add(object);
            }
            return dos;
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    private MinioListObject updateListObject(String bucketName, MinioListObject object, Item item) throws MsMinioException {
        Iterable<Result<Item>> results = listObjects(bucketName, object.getObjectNanePath());
        try {
            for (Result<Item> result : results) {
                Item subItem = result.get();
                MinioListObject subObject = new MinioListObject(subItem);
                if (subItem.isDir()) {
                    updateListObject(bucketName, subObject, subItem);
                }
                object.addChild(subObject);
            }
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
        return object;
    }

    /**
     * 获取桶指定路径下的对象列表
     *
     * @param bucketName 桶名称
     * @param prefix     路径
     * @return 对象列表
     * @throws MsMinioException Minio异常
     */
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix) throws MsMinioException {
        return listObjects(bucketName, prefix, false);
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

    /**
     * 判断对象是否存在
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @return 是否存在
     * @throws MsMinioException Minio异常
     */
    public boolean objectExists(String bucketName, String objectName) throws MsMinioException {
        try {
            minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
            return true;
        } catch (ErrorResponseException e) {
            if ("NoSuchKey".equals(e.errorResponse().code())) {
                return false;
            }
            throw new MsMinioException(e);
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * 创建文件夹
     *
     * @param bucketName 桶名称
     * @param folderPath 文件夹名称 (不能以 / 开头，必须以 / 结尾)
     * @throws MsMinioException Minio异常
     */
    public void createFolder(String bucketName, String folderPath) throws MsMinioException {
        try {
            checkFolderPath(folderPath);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(folderPath)
                    .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    private void checkFolderPath(String folderPath) {
        if (folderPath.startsWith("/")) {
            throw new IllegalArgumentException("Folder path cannot start with /");
        }
        if (!folderPath.endsWith("/")) {
            throw new IllegalArgumentException("Folder path must end with /");
        }
    }
}
