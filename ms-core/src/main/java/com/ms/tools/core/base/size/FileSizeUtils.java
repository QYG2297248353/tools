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

package com.ms.tools.core.base.size;

/**
 * 大小工具
 * <p>
 * 1 byte（字节）= 8 bits（比特）
 * 1 kilobyte（KB）= 1024 bytes
 * 1 megabyte（MB）= 1024 kilobytes
 * 1 gigabyte（GB）= 1024 megabytes
 * 1 terabyte（TB）= 1024 gigabytes
 * <p>
 * 对于比特（bits）和字节（bytes）之间的换算，可以使用以下关系：
 * 1 byte = 8 bits
 * 1 bit = 1/8 byte
 * <p>
 * 对于KB、MB、GB和TB之间的换算，可以使用以下关系：
 * 1 KB = 1024 bytes
 * 1 MB = 1024 KB
 * 1 GB = 1024 MB
 * 1 TB = 1024 GB
 *
 * @author qyg2297248353
 */
public enum FileSizeUtils {
    /**
     * 转换任意单位的大小, 返回结果会包含两位小数但不包含单位.
     */
    Arbitrary {
        /**
         * 转换任意单位的大小
         *
         * @param size 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果会包含两位小数但不包含单位
         */
        @Override
        public String convert(float size) {
            while (size > FILE_CAPACITY_UNIT_SIZE) {
                size /= FILE_CAPACITY_UNIT_SIZE;
            }
            return String.format(FORMAT_F, size);
        }
    },

    /**
     * 转换单位为B的大小, 返回结果会包含两位小数以及单位.
     */
    B {
        /**
         * 转换单位为B的大小
         *
         * @param b 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果会包含两位小数以及单位
         */
        @Override
        public String convert(float b) {
            return converter(0, b);
        }
    },

    /**
     * 转换单位为KB的大小, 返回结果会包含两位小数以及单位.
     */
    KB {
        /**
         * 转换单位为KB的大小
         *
         * @param kb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果会包含两位小数以及单位
         */
        @Override
        public String convert(float kb) {
            return converter(1, kb);
        }
    },

    /**
     * 转换单位为MB的大小, 返回结果会包含两位小数以及单位.
     */
    MB {
        /**
         * 转换单位为MB的大小
         *
         * @param mb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果会包含两位小数以及单位
         */
        @Override
        public String convert(float mb) {
            return converter(2, mb);
        }
    },

    /**
     * 转换单位为GB的大小, 返回结果会包含两位小数以及单位.
     */
    GB {
        /**
         * 单位为GB的大小
         *
         * @param gb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果会包含两位小数以及单位
         */
        @Override
        public String convert(float gb) {
            return converter(3, gb);
        }
    },

    /**
     * 转换单位为TB的大小, 返回结果会包含两位小数以及单位.
     */
    TB {
        /**
         * 单位为TB的大小
         *
         * @param tb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果会包含两位小数以及单位
         */
        @Override
        public String convert(float tb) {
            return converter(4, tb);
        }
    },

    /**
     * 转换任意单位的大小, 返回结果小数部分为0时将去除两位小数, 不包含单位.
     */
    ArbitraryTrim {
        /**
         * 转换任意单位的大小
         *
         * @param size 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果小数部分为0时将去除两位小数, 不包含单位
         */
        @Override
        public String convert(float size) {
            while (size > FILE_CAPACITY_UNIT_SIZE) {
                size /= FILE_CAPACITY_UNIT_SIZE;
            }

            int sizeInt = (int) size;
            boolean isfloat = size - sizeInt > 0.0F;
            if (isfloat) {
                return String.format(FORMAT_F, size);
            }
            return String.format(FORMAT_D, sizeInt);
        }
    },

    /**
     * 转换单位为B的大小, 返回结果小数部分为0时将去除两位小数, 会包含单位.
     */
    BTrim {
        /**
         * 转换单位为B的大小
         * @param b 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果小数部分为0时将去除两位小数, 会包含单位
         */
        @Override
        public String convert(float b) {
            return trimConverter(0, b);
        }
    },
    /**
     * 转换单位为KB的大小, 返回结果小数部分为0时将去除两位小数, 会包含单位.
     */
    KBTrim {
        /**
         * 转换单位为KB的大小
         * @param kb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果小数部分为0时将去除两位小数, 会包含单位
         */
        @Override
        public String convert(float kb) {
            return trimConverter(1, kb);
        }
    },
    /**
     * 转换单位为MB的大小, 返回结果小数部分为0时将去除两位小数, 会包含单位.
     */
    MBTrim {
        /**
         * 转换单位为MB的大小
         * @param mb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果小数部分为0时将去除两位小数, 会包含单位
         */
        @Override
        public String convert(float mb) {
            return trimConverter(2, mb);
        }
    },
    /**
     * 转换单位为GB的大小, 返回结果小数部分为0时将去除两位小数, 会包含单位.
     */
    GBTrim {
        /**
         * 转换单位为GB的大小
         * @param gb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果小数部分为0时将去除两位小数, 会包含单位
         */
        @Override
        public String convert(float gb) {
            return trimConverter(3, gb);
        }
    },
    /**
     * 转换单位为TB的大小, 返回结果小数部分为0时将去除两位小数, 会包含单位.
     */
    TBTrim {
        /**
         * 转换单位为TB的大小
         * @param tb 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
         * @return 结果小数部分为0时将去除两位小数, 会包含单位
         */
        @Override
        public String convert(float tb) {
            return trimConverter(4, tb);
        }
    };

    public static final Integer FILE_CAPACITY_UNIT_SIZE = 1024;
    /**
     * 大小格式
     */
    private static final String[] UNITS = new String[]{
            "B", "KB", "MB", "GB", "TB", "PB", "**"
    };
    private static final int LAST_IDX = UNITS.length - 1;
    private static final String FORMAT_F = "%1$-1.2f";
    private static final String FORMAT_F_UNIT = "%1$-1.2f%2$s";
    private static final String FORMAT_D = "%1$-1d";
    private static final String FORMAT_D_UNIT = "%1$-1d%2$s";

    /**
     * 转换
     *
     * @param unit 节点
     * @param size 大小
     * @return 转换结果
     */
    private static String converter(int unit, float size) {
        int unitIdx = unit;
        while (size > FILE_CAPACITY_UNIT_SIZE) {
            unitIdx++;
            size /= FILE_CAPACITY_UNIT_SIZE;
        }
        int idx = Math.min(unitIdx, LAST_IDX);
        return String.format(FORMAT_F_UNIT, size, UNITS[idx]);
    }

    /**
     * 转换
     *
     * @param unit 节点
     * @param size 数量
     * @return 转换结果
     */
    private static String trimConverter(int unit, float size) {
        int unitIdx = unit;
        while (size > FILE_CAPACITY_UNIT_SIZE) {
            unitIdx++;
            size /= FILE_CAPACITY_UNIT_SIZE;
        }

        int sizeInt = (int) size;
        boolean isfloat = size - sizeInt > 0.0F;
        int idx = Math.min(unitIdx, LAST_IDX);
        if (isfloat) {
            return String.format(FORMAT_F_UNIT, size, UNITS[idx]);
        }
        return String.format(FORMAT_D_UNIT, sizeInt, UNITS[idx]);
    }

    /**
     * 转换单位为B的大小, 返回结果会包含两位小数以及转换后单位.
     *
     * @param b    待转数值
     * @param trim 方式
     * @return 转换大小
     */
    public static String convertBytes(float b, boolean trim) {
        return trim ? trimConvert(0, b, true) : convert(0, b, true);
    }

    /**
     * 转换单位为KB的大小, 返回结果会包含两位小数以及转换后单位.
     *
     * @param kb   待转数值
     * @param trim 方式
     * @return 转换大小
     */
    public static String convertKb(float kb, boolean trim) {
        return trim ? trimConvert(1, kb, true) : convert(1, kb, true);
    }

    /**
     * 转换单位为KB的大小, 返回结果会包含两位小数以及单位.
     *
     * @param mb   待转数值
     * @param trim 方式
     * @return 转换大小
     */
    public static String convertMb(float mb, boolean trim) {
        return trim ? trimConvert(2, mb, true) : convert(2, mb, true);
    }

    /***
     * 存储大小单位间的转换.
     * 注意该方法的最大单位为PB, 最小单位为B,
     * 任何超出该范围的单位最终会显示为**.
     *
     * @param unit     从哪个单位开始
     * @param size     存储大小, 注意是float, 不要以整形的形式传入, 否则会溢出
     * @param withUnit 返回的结果字符串是否带有对应的单位
     * @return 结果
     */
    private static String convert(int unit, float size, boolean withUnit) {
        int unitIdx = unit;
        while (size > FILE_CAPACITY_UNIT_SIZE) {
            unitIdx++;
            size /= FILE_CAPACITY_UNIT_SIZE;
        }
        if (withUnit) {
            int idx = Math.min(unitIdx, LAST_IDX);
            return String.format(FORMAT_F_UNIT, size, UNITS[idx]);
        }
        return String.format(FORMAT_F, size);
    }

    /***
     * 存储大小单位间的转换, 如果转换后小数部分为0, 则去除小数部分.
     * 注意该方法的最大单位为PB, 最小单位为B, 任何超出该范围的单位最终会显示为**.
     *
     * @param unit     从哪个单位开始
     * @param size     存储大小, 注意是float, 不要以整形的形式传入, 否则会溢出
     * @param withUnit 返回的结果字符串是否带有对应的单位
     * @return 结果
     */
    private static String trimConvert(int unit, float size, boolean withUnit) {
        int unitIdx = unit;
        while (size > FILE_CAPACITY_UNIT_SIZE) {
            unitIdx++;
            size /= FILE_CAPACITY_UNIT_SIZE;
        }

        int sizeInt = (int) size;
        boolean isfloat = size - sizeInt > 0.0F;
        if (withUnit) {
            int idx = Math.min(unitIdx, LAST_IDX);
            if (isfloat) {
                return String.format(FORMAT_F_UNIT, size, UNITS[idx]);
            }
            return String.format(FORMAT_D_UNIT, sizeInt, UNITS[idx]);
        }

        if (isfloat) {
            return String.format(FORMAT_F, size);
        }
        return String.format(FORMAT_D, sizeInt);
    }

    /***
     * 将指定的大小转换到1024范围内的大小. 注意该方法的最大单位为PB, 最小单位为B,
     * 任何超出该范围的单位最终会显示为**.
     *
     * @param size 要转换的大小, 注意是浮点数, 不要以整形的方式传入, 容易造成溢出.
     * @return 结果
     */
    public abstract String convert(float size);
}
