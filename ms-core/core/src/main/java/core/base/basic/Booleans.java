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

package core.base.basic;

/**
 * 布尔工具类
 *
 * @author ms2297248353
 */
public interface Booleans {
    byte TRUE_BYTE = 1;
    byte FALSE_BYTE = 0;

    boolean TRUE = true;
    boolean FALSE = false;


    /**
     * 是否为true，当val为null，返回false
     *
     * @param val 值
     * @return 返回true/false
     */
    static boolean isTrue(Byte val) {
        return isTrue(val, false);
    }

    /**
     * 是否为true
     *
     * @param val      值
     * @param whenNull 当val为null时，返回whenNull指定的值
     * @return 返回true/false
     */
    static boolean isTrue(Byte val, boolean whenNull) {
        if (val == null) {
            return whenNull;
        }
        return val == TRUE_BYTE;
    }

    /**
     * 是否为true
     *
     * @param b 字节
     * @return 布尔值
     */
    static boolean isTrue(Long b) {
        return b != null && b == TRUE_BYTE;
    }

    /**
     * 是否为true
     *
     * @param b 字节
     * @return 布尔值
     */
    static boolean isTrue(Integer b) {
        return b != null && b == TRUE_BYTE;
    }

    /**
     * 布尔转字节
     *
     * @param b 布尔值
     * @return 返回字节信息
     */
    static byte toValue(Boolean b) {
        if (b == null) {
            return FALSE_BYTE;
        }
        return b ? TRUE_BYTE : FALSE_BYTE;
    }

    /**
     * 布尔值转为字节
     *
     * @param bool 布尔值
     * @return 字节
     */
    static byte toByte(boolean bool) {
        return (bool ? TRUE_BYTE : FALSE_BYTE);
    }

    /**
     * 布尔值转为字节
     *
     * @param bool       布尔值
     * @param trueValue  true值
     * @param falseValue false值
     * @return 指定字节
     */
    static byte toByte(boolean bool, byte trueValue, byte falseValue) {
        return bool ? trueValue : falseValue;
    }

    /**
     * 布尔值转为字节
     *
     * @param bool       布尔值
     * @param trueValue  true值
     * @param falseValue false值
     * @param nullValue  null值
     * @return 指定字节
     */
    static byte toByte(Boolean bool, byte trueValue, byte falseValue, byte nullValue) {
        if (bool == null) {
            return nullValue;
        }
        return (bool ? trueValue : falseValue);
    }
}
