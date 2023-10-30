package com.cloud.ms.component.qiniucloud.enums;

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
