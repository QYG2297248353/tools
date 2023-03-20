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
