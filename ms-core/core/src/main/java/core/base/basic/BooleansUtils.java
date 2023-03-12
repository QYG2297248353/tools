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
 * @author ms2297248353
 */
public class BooleansUtils {

    /**
     * 布尔值转为字节
     *
     * @param bool 布尔值
     * @return 字节
     */
    public static byte toByte(boolean bool) {
        return (byte) (bool ? 1 : 0);
    }

    /**
     * 布尔值转为字节
     *
     * @param bool       布尔值
     * @param trueValue  true值
     * @param falseValue false值
     * @return 指定字节
     */
    public static byte toByte(boolean bool, byte trueValue, byte falseValue) {
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
    public static byte toByte(Boolean bool, byte trueValue, byte falseValue, byte nullValue) {
        if (bool == null) {
            return nullValue;
        }
        return (bool ? trueValue : falseValue);
    }

}
