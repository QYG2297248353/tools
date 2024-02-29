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

package com.ms.tools.minio.model;

import com.ms.tools.core.base.size.FileSizeUtils;
import io.minio.messages.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ms
 */
@Getter
@Setter
public class MinioListObject {

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 对象路径
     */
    private String objectPath;

    /**
     * 文件夹名称
     */
    private String dirName;

    /**
     * 对象完整路径
     */
    private String objectNanePath;


    /**
     * 对象大小
     */
    private Long objectSize;

    /**
     * 对象大小 单位
     */
    private String objectSizeUnit;

    /**
     * 最后修改时间
     */
    private Date lastModified;

    /**
     * etag
     */
    private String etag;

    /**
     * 是否是目录
     */
    private boolean isDir;

    /**
     * 子目录
     */
    private List<MinioListObject> subDir;

    public MinioListObject(Item item) {
        objectNanePath = item.objectName();
        objectName = item.objectName().substring(item.objectName().lastIndexOf("/") + 1);
        objectPath = item.objectName().substring(0, item.objectName().lastIndexOf("/") + 1);
        dirName = getMinioDirName(item.objectName());
        objectSize = item.size();
        objectSizeUnit = FileSizeUtils.BTrim.convert(objectSize.floatValue());
        etag = item.etag();
        isDir = item.isDir();
        if (!isDir) {
            ZonedDateTime zonedDateTime = item.lastModified();
            lastModified = new Date(zonedDateTime.toInstant().toEpochMilli());
        }
    }

    private String getMinioDirName(String path) {
        String dirName = path.substring(0, path.lastIndexOf("/"));
        return dirName.substring(dirName.lastIndexOf("/") + 1);
    }

    public void addChild(MinioListObject subObject) {
        if (subDir == null) {
            subDir = new ArrayList<>();
        }
        subDir.add(subObject);
    }
}
