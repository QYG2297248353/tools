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

package com.ms.core.base.basic;

/**
 * 布尔(字节版本)
 *
 * @author ms2297248353
 */
public interface Booleans {
    byte TRUE = 1;
    byte FALSE = 0;

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
        return val == TRUE;
    }

    /**
     * 是否为true
     *
     * @param b 字节
     * @return 布尔值
     */
    static boolean isTrue(Long b) {
        return b != null && b == TRUE;
    }

    /**
     * 布尔转字节
     *
     * @param b 布尔值
     * @return 返回字节信息
     */
    static byte toValue(Boolean b) {
        if (b == null) {
            return FALSE;
        }
        return b ? TRUE : FALSE;
    }
}
