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
import com.ms.tools.minio.exception.MsMinioException;
import com.ms.tools.minio.properties.MsMinioProperties;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.ms.tools.resources.file.FileTypeUtils.getFileType;

/**
 * Minio bucket service
 * Minio 桶服务
 *
 * @author ms
 */
@Component
public class MinioUpDownService {

    private static final Logger log = Logger.getLogger(MinioUpDownService.class.getName());
    private final MinioClient minioClient;
    @Resource
    private MsMinioProperties msMinioProperties;

    public MinioUpDownService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param filePath   文件路径 (文件本地路径)
     * @throws MsMinioException Minio异常
     */
    public void uploadFile(String bucketName, String objectName, String filePath) throws MsMinioException {
        uploadFile(bucketName, objectName, filePath, null);
    }


    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称 (文件存储名称)
     * @param filePath    文件路径 (文件本地路径)
     * @param contentType 文件类型
     * @throws MsMinioException Minio异常
     */
    public void uploadFile(String bucketName, String objectName, String filePath, String contentType) throws MsMinioException {
        try {
            UploadObjectArgs.Builder builder = UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .filename(filePath);
            if (Strings.isBlank(contentType)) {
                contentType = getFileType(new File(filePath));
            }
            builder.contentType(contentType);
            minioClient.uploadObject(builder.build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * 分片上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param filePath   文件路径 (文件本地路径)
     * @param partSize   分片大小 (字节 10485760 = 10MB)
     * @throws MsMinioException Minio异常
     */
    public void uploadFileByPart(String bucketName, String objectName, String filePath, long partSize) throws MsMinioException {
        uploadFileByPart(bucketName, objectName, filePath, partSize, null);
    }

    /**
     * 分片上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param filePath   文件路径 (文件本地路径)
     * @param partSize   分片大小 (字节 10485760 = 10MB)
     * @throws MsMinioException Minio异常
     */
    public void uploadFileByPart(String bucketName, String objectName, String filePath, long partSize, String contentType) throws MsMinioException {
        try {
            UploadObjectArgs.Builder builder = UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .filename(filePath, partSize);
            if (Strings.isBlank(contentType)) {
                contentType = getFileType(new File(filePath));
            }
            builder.contentType(contentType);
            minioClient.uploadObject(builder.build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }


    /**
     * 上传文件 (已知大小)
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称 (文件存储名称)
     * @param inputStream 文件流
     * @param size        文件大小
     * @param contentType 文件类型
     */
    public void uploadFileStream(String bucketName, String objectName, InputStream inputStream, long size, String contentType) throws MsMinioException {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, size, -1)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * 上传文件（未知大小/类型）
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称 (文件存储名称)
     * @param inputStream 文件流
     */
    public void uploadFileStream(String bucketName, String objectName, InputStream inputStream) throws MsMinioException {
        uploadFileStream(bucketName, objectName, inputStream, "application/octet-stream");
    }


    /**
     * 上传文件（未知大小）
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称 (文件存储名称)
     * @param inputStream 文件流
     * @param contentType 文件类型
     */
    public void uploadFileStream(String bucketName, String objectName, InputStream inputStream, String contentType) throws MsMinioException {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, -1, 10485760)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称 (文件存储名称)
     * @param bytes       字节流
     * @param contentType 文件类型
     * @throws MsMinioException Minio异常
     */
    public void uploadFileStream(String bucketName, String objectName, byte[] bytes, String contentType) throws MsMinioException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        uploadFileStream(bucketName, objectName, byteArrayInputStream, bytes.length, contentType);
    }


    /**
     * 上传文件
     *
     * @param bucketName    桶名称
     * @param objectName    对象名称 (文件存储名称)
     * @param multipartFile 文件
     * @throws MsMinioException Minio异常
     */
    public void uploadMultipartFile(String bucketName, String objectName, MultipartFile multipartFile) throws MsMinioException {
        uploadMultipartFile(bucketName, objectName, null, multipartFile);
    }

    /**
     * 上传文件
     *
     * @param bucketName    桶名称
     * @param objectName    对象名称 (文件存储名称)
     * @param objectPath    对象路径 (文件存储路径)
     * @param multipartFile 文件
     * @throws MsMinioException Minio异常
     */
    public void uploadMultipartFile(String bucketName, String objectName, String objectPath, MultipartFile multipartFile) throws MsMinioException {
        try {
            String objectNamePath = getObjectNamePath(objectPath, objectName);
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectNamePath).stream(
                                    multipartFile.getInputStream(), multipartFile.getSize(), -1)
                            .contentType(multipartFile.getContentType())
                            .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }


    /**
     * 批量上传文件
     *
     * @param bucketName     桶名称
     * @param multipartFiles 文件数组
     * @throws MsMinioException Minio异常
     */
    public void uploadMultipartFiles(String bucketName, MultipartFile[] multipartFiles) throws MsMinioException {
        uploadMultipartFiles(bucketName, null, multipartFiles);
    }

    /**
     * 批量上传文件
     *
     * @param bucketName     桶名称
     * @param objectPath     对象路径 (文件存储路径)
     * @param multipartFiles 文件数组
     * @throws MsMinioException Minio异常
     */
    public void uploadMultipartFiles(String bucketName, String objectPath, MultipartFile[] multipartFiles) throws MsMinioException {
        Map<String, MultipartFile> multipartFileMap = new HashMap<>();
        for (MultipartFile multipartFile : multipartFiles) {
            multipartFileMap.put(multipartFile.getOriginalFilename(), multipartFile);
        }
        uploadMultipartFileMap(bucketName, objectPath, multipartFileMap);
    }

    /**
     * 批量上传文件
     *
     * @param bucketName     桶名称
     * @param multipartFiles 文件Map
     *                       key: 对象名称 (文件存储名称)
     *                       value: 文件
     * @throws MsMinioException Minio异常
     */
    public void uploadMultipartFileMap(String bucketName, String objectPath, Map<String, MultipartFile> multipartFiles) throws MsMinioException {
        List<Throwable> exceptions = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entry : multipartFiles.entrySet()) {
            try {
                uploadMultipartFile(bucketName, entry.getKey(), objectPath, entry.getValue());
            } catch (MsMinioException e) {
                log.warning("上传文件失败：" + entry.getKey());
                exceptions.add(e);
            }
        }
        if (!exceptions.isEmpty()) {
            throw new MsMinioException(exceptions);
        }
    }


    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param file       文件
     * @throws MsMinioException Minio异常
     */
    public void uploadFileStream(String bucketName, String objectName, File file) throws MsMinioException {
        uploadFileStream(bucketName, objectName, null, file);
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param objectPath 对象路径 (文件存储路径)
     * @param file       文件
     * @throws MsMinioException Minio异常
     */
    public void uploadFileStream(String bucketName, String objectName, String objectPath, File file) throws MsMinioException {
        uploadFile(bucketName, objectName, objectPath, file, null);
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param objectPath 对象路径 (文件存储路径)
     * @param file       文件
     * @throws MsMinioException Minio异常
     */
    public void uploadFile(String bucketName, String objectName, String objectPath, File file, String contentType) throws MsMinioException {
        try {
            if (file.isFile()) {
                if (Strings.isBlank(contentType)) {
                    contentType = getFileType(file);
                }
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    String objectNamePath = getObjectNamePath(objectPath, objectName);
                    minioClient.putObject(
                            PutObjectArgs.builder().bucket(bucketName).object(objectNamePath).stream(
                                            fileInputStream, file.length(), -1)
                                    .contentType(contentType)
                                    .build());
                } catch (Exception e) {
                    throw new MsMinioException(e);
                }
            } else {
                throw new MsMinioException("文件不存在");
            }
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param file       文件
     * @throws MsMinioException Minio异常
     */
    public void uploadFileStream(String bucketName, File file) throws MsMinioException {
        uploadFileStream(bucketName, file.getName(), file);
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param file       文件
     * @param objectPath 对象路径 (文件存储路径)
     * @throws MsMinioException Minio异常
     */
    public void uploadFileStream(String bucketName, File file, String objectPath) throws MsMinioException {
        uploadFileStream(bucketName, file.getName(), objectPath, file);
    }

    /**
     * 上传文件夹 (保留文件夹结构)
     *
     * @param bucketName 桶名称
     * @param folderPath 文件夹路径
     * @throws MsMinioException Minio异常
     */
    public void uploadFolderStream(String bucketName, String folderPath) throws MsMinioException {
        uploadFolderStream(bucketName, folderPath, null);
    }

    /**
     * 上传文件夹 (保留文件夹结构)
     *
     * @param bucketName 桶名称
     * @param folderPath 文件夹路径
     * @param objectPath 对象路径 (文件存储路径)
     * @throws MsMinioException Minio异常
     */
    public void uploadFolderStream(String bucketName, String folderPath, String objectPath) throws MsMinioException {
        uploadFolderStream(bucketName, folderPath, objectPath, false);
    }

    /**
     * 上传文件夹
     *
     * @param bucketName 桶名称
     * @param folderPath 文件夹路径
     * @param objectPath 对象路径 (文件存储路径)
     * @param recursion  是否递归 (true: 递归上传文件夹下所有文件, 不保留文件夹结构)
     * @throws MsMinioException Minio异常
     */
    public void uploadFolderStream(String bucketName, String folderPath, String objectPath, boolean recursion) throws MsMinioException {
        uploadFolderStream(bucketName, folderPath, objectPath, recursion, true);
    }


    /**
     * 上传文件夹
     *
     * @param bucketName 桶名称
     * @param folderPath 文件夹路径
     * @param objectPath 对象路径 (文件存储路径)
     * @param recursion  是否递归 (true: 递归上传文件夹下所有文件, 不保留文件夹结构)
     * @param directory  是否上传文件夹
     * @throws MsMinioException Minio异常
     */
    public void uploadFolderStream(String bucketName, String folderPath, String objectPath, boolean recursion, boolean directory) throws MsMinioException {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        uploadFileStream(bucketName, file, objectPath);
                    }
                    if (recursion && file.isDirectory() && directory) {
                        uploadFolderStream(bucketName, file.getPath(), objectPath, recursion, directory);
                        continue;
                    }
                    if (file.isDirectory() && directory) {
                        String folderName = file.getName();
                        String objectNamePath = getObjectNamePath(objectPath, folderName);
                        uploadFolderStream(bucketName, file.getPath(), objectNamePath, recursion, directory);
                    }
                }
            }
        } else {
            throw new MsMinioException("文件夹不存在");
        }
    }

    /**
     * 获取分享链接
     *
     * @param bucketName 桶名称
     * @param objectName 对象路径名称 (文件存储完整名称)
     * @return 分享链接 (7天)
     * @throws MsMinioException Minio异常
     */
    public String getShareUrl(String bucketName, String objectName) throws MsMinioException {
        return getShareUrl(bucketName, objectName, null, 7, TimeUnit.DAYS);
    }

    /**
     * 获取分享链接
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param objectPath 对象路径 (文件存储路径)
     * @return 分享链接 (7天)
     * @throws MsMinioException Minio异常
     */
    public String getShareUrl(String bucketName, String objectName, String objectPath) throws MsMinioException {
        return getShareUrl(bucketName, objectName, objectPath, 7, TimeUnit.DAYS);
    }


    /**
     * 获取分享链接
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param objectPath 对象路径 (文件存储路径)
     * @param expiry     过期时间 (分钟)
     * @return 分享链接
     * @throws MsMinioException Minio异常
     */
    public String getShareUrl(String bucketName, String objectName, String objectPath, int expiry) throws MsMinioException {
        return getShareUrl(bucketName, objectName, objectPath, Method.GET, expiry, TimeUnit.MINUTES);
    }


    /**
     * 获取分享链接
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param objectPath 对象路径 (文件存储路径)
     * @param expiry     过期时间
     * @param unit       时间单位
     * @return 分享链接
     * @throws MsMinioException Minio异常
     */
    public String getShareUrl(String bucketName, String objectName, String objectPath, int expiry, TimeUnit unit) throws MsMinioException {
        return getShareUrl(bucketName, objectName, objectPath, Method.GET, expiry, unit);
    }

    /**
     * 获取分享链接
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称 (文件存储名称)
     * @param objectPath 对象路径 (文件存储路径)
     * @param method     请求方法
     * @param expiry     过期时间
     * @param unit       时间单位
     * @return 分享链接
     * @throws MsMinioException Minio异常
     */
    public String getShareUrl(String bucketName, String objectName, String objectPath, Method method, int expiry, TimeUnit unit) throws MsMinioException {
        try {
            String objectNamePath = getObjectNamePath(objectPath, objectName);
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(objectNamePath)
                    .method(method)
                    .expiry(expiry, unit)
                    .build());
        } catch (Exception e) {
            throw new MsMinioException(e);
        }
    }


    /**
     * 获取对象名称路径
     *
     * @param objectPath 对象路径
     * @param objectName 对象名称
     * @return 对象名称路径
     */
    public String getObjectNamePath(String objectPath, String objectName) {
        if (Strings.isBlank(objectPath)) {
            return objectName;
        }
        if (objectPath.endsWith("/")) {
            return objectPath + objectName;
        } else {
            return objectPath + "/" + objectName;
        }
    }

}
