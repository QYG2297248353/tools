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

package com.ms.tools.components.cloud.qiniu.enums;

/**
 * @author ms
 */

public enum FileTypeEnum {

    /**
     * 头像
     */
    AVATAR_IMG(FileTypeEnum.BUCKET_NAME, "avatar/", "头像"),

    /**
     * banner图
     */
    BANNER_IMG(FileTypeEnum.BUCKET_NAME, "banner/", "背景墙"),

    /**
     * 域名 Logo
     */
    DOMAIN_LOGO(FileTypeEnum.BUCKET_NAME, "pub/", "域名 Logo");


    /**
     * bucket name : life-userinfo
     */
    private static final String BUCKET_NAME = "life-userinfo";


    /**
     * bucket
     */
    private final String bucket;

    /**
     * 目录/路径
     */
    private final String path;

    /**
     * 描述
     */
    private final String desc;


    FileTypeEnum(String bucket, String path, String desc) {
        this.bucket = bucket;
        this.path = path;
        this.desc = desc;
    }

    public String getBucket() {
        return bucket;
    }

    public String getDesc() {
        return desc;
    }

    public String getPath() {
        return path;
    }
}
