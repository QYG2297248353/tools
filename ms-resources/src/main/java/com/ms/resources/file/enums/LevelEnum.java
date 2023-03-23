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

package com.ms.resources.file.enums;

import net.lingala.zip4j.model.enums.CompressionLevel;

public enum LevelEnum {
    /**
     * 压缩级别
     */
    STORE(0, "存储"),
    FASTEST(1, "最快"),
    FAST(3, "快"),
    NORMAL(5, "普通"),
    MAXIMUM(7, "最大"),
    ULTRA(9, "极限");

    private int level;
    private String desc;

    LevelEnum(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public static LevelEnum getLevelEnum(int level) {
        for (LevelEnum levelEnum : LevelEnum.values()) {
            if (levelEnum.getLevel() == level) {
                return levelEnum;
            }
        }
        return LevelEnum.NORMAL;
    }

    public int getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }

    public CompressionLevel covertCompressionLevel() {
        switch (this) {
            case STORE:
                return CompressionLevel.NO_COMPRESSION;
            case FASTEST:
                return CompressionLevel.FASTEST;
            case FAST:
                return CompressionLevel.FAST;
            case MAXIMUM:
                return CompressionLevel.MAXIMUM;
            case ULTRA:
                return CompressionLevel.ULTRA;
            case NORMAL:
            default:
                return CompressionLevel.NORMAL;
        }
    }
}
