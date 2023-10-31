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

package com.ms.tools.resources.file.enums;

/**
 * 卷大小枚举
 *
 * @author ms2297248353
 */
public enum VolumeSizeEnum {
    /**
     * 不分卷
     */
    VOLUME_SIZE_0(0L, "不分卷"),
    /**
     * 卷大小64K
     */
    VOLUME_SIZE_64K(65536L, "64K"),
    /**
     * 卷大小128K
     */
    VOLUME_SIZE_128K(131072L, "128K"),
    /**
     * 卷大小256K
     */
    VOLUME_SIZE_256K(262144L, "256K"),
    /**
     * 卷大小512K
     */
    VOLUME_SIZE_512K(524288L, "512K"),
    /**
     * 卷大小1M
     */
    VOLUME_SIZE_1M(1048576L, "1M"),
    /**
     * 卷大小2M
     */
    VOLUME_SIZE_2M(2097152L, "2M"),
    /**
     * 卷大小5M
     */
    VOLUME_SIZE_5M(5242880L, "5M"),
    /**
     * 卷大小10M
     */
    VOLUME_SIZE_10M(10485760L, "10M"),
    /**
     * 卷大小20M
     */
    VOLUME_SIZE_20M(20971520L, "20M"),
    /**
     * 卷大小50M
     */
    VOLUME_SIZE_50M(52428800L, "50M"),
    /**
     * 卷大小100M
     */
    VOLUME_SIZE_100M(104857600L, "100M"),
    /**
     * 卷大小200M
     */
    VOLUME_SIZE_200M(209715200L, "200M"),
    /**
     * 卷大小500M
     */
    VOLUME_SIZE_500M(524288000L, "500M"),
    /**
     * 卷大小1G
     */
    VOLUME_SIZE_1G(1073741824L, "1G"),
    /**
     * 卷大小2G
     */
    VOLUME_SIZE_2G(2147483648L, "2G"),
    /**
     * 卷大小3G
     */
    VOLUME_SIZE_3G(3221225472L, "3G"),
    /**
     * 卷大小4G
     */
    VOLUME_SIZE_4G(4294967296L, "4G"),
    /**
     * 卷大小5G
     */
    VOLUME_SIZE_5G(5368709120L, "5G"),
    /**
     * 卷大小10G
     */
    VOLUME_SIZE_10G(10737418240L, "10G"),
    /**
     * 卷大小20G
     */
    VOLUME_SIZE_20G(21474836480L, "20G"),
    /**
     * 卷大小50G
     */
    VOLUME_SIZE_50G(53687091200L, "50G"),
    /**
     * 卷大小100G
     */
    VOLUME_SIZE_100G(107374182400L, "100G"),
    /**
     * 卷大小200G
     */
    VOLUME_SIZE_200G(214748364800L, "200G"),
    /**
     * 卷大小500G
     */
    VOLUME_SIZE_500G(536870912000L, "500G"),
    /**
     * 卷大小1T
     */
    VOLUME_SIZE_1T(1099511627776L, "1T"),
    /**
     * 卷大小2T
     */
    VOLUME_SIZE_2T(2199023255552L, "2T"),
    /**
     * 卷大小5T
     */
    VOLUME_SIZE_5T(5497558138880L, "5T"),
    /**
     * 卷大小10T
     * T 1024 * 1024 * 1024 * 1024
     */
    VOLUME_SIZE_10T(10995116277760L, "10T");

    private Long size;

    private String desc;

    VolumeSizeEnum(Long size, String desc) {
        this.size = size;
        this.desc = desc;
    }

    /**
     * 获取对象
     *
     * @param size 大小
     * @return 对象
     */
    public static VolumeSizeEnum getEnum(Long size) {
        for (VolumeSizeEnum volumeSizeEnum : VolumeSizeEnum.values()) {
            if (volumeSizeEnum.getSize().equals(size)) {
                return volumeSizeEnum;
            }
        }
        return VOLUME_SIZE_0;
    }

    /**
     * 获取对象
     *
     * @param desc 描述
     * @return 对象
     */
    public static VolumeSizeEnum getEnum(String desc) {
        for (VolumeSizeEnum volumeSizeEnum : VolumeSizeEnum.values()) {
            if (volumeSizeEnum.getDesc().equals(desc)) {
                return volumeSizeEnum;
            }
        }
        return VOLUME_SIZE_0;
    }

    /**
     * 获取大小
     * 单位 B
     *
     * @return 大小
     */
    public Long getSize() {
        return size;
    }

    /**
     * 获取大小
     * 单位 KB
     *
     * @return 大小
     */
    public Long getSizeKB() {
        return size / 1024;
    }

    /**
     * 获取描述
     *
     * @return 描述
     */
    public String getDesc() {
        return desc;
    }

}
