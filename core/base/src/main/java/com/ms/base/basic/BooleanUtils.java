/*
 * @MS 2022-12-13
 *  Copyright (c) 2001-2023 萌森
 *  保留所有权利
 *  本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 *  Copyright (c) 2001-2023 Meng Sen
 *  All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.base.basic;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author ms2297248353
 */
public class BooleanUtils extends org.apache.commons.lang3.BooleanUtils {

    /**
     * <p>Converts a boolean to an int using the convention that
     * {@code true} is {@code 1} and {@code false} is {@code 0}.</p>
     *
     * <pre>
     *   BooleanUtils.toByte(true)  = 1
     *   BooleanUtils.toByte(false) = 0
     * </pre>
     *
     * @param bool the boolean to convert
     * @return one if {@code true}, zero if {@code false}
     */
    public static byte toByte(boolean bool) {
        return (byte) (bool ? 1 : 0);
    }

    /**
     * <p>Converts a boolean to an byte specifying the conversion values.</p>
     *
     * <pre>
     *   BooleanUtils.toByte(true, 1, 0)  = 1
     *   BooleanUtils.toByte(false, 1, 0) = 0
     * </pre>
     *
     * @param bool       the to convert
     * @param trueValue  the value to return if {@code true}
     * @param falseValue the value to return if {@code false}
     * @return the appropriate value
     */
    public static byte toByte(boolean bool, byte trueValue, byte falseValue) {
        return bool ? trueValue : falseValue;
    }

    /**
     * <p>Converts a Boolean to an byte specifying the conversion values.</p>
     *
     * <pre>
     *   BooleanUtils.toByte(Boolean.TRUE, 1, 0, 2)  = 1
     *   BooleanUtils.toByte(Boolean.FALSE, 1, 0, 2) = 0
     *   BooleanUtils.toByte(null, 1, 0, 2)          = 2
     * </pre>
     *
     * @param bool       the Boolean to convert
     * @param trueValue  the value to return if {@code true}
     * @param falseValue the value to return if {@code false}
     * @param nullValue  the value to return if {@code null}
     * @return the appropriate value
     */
    public static byte toByte(Boolean bool, byte trueValue, byte falseValue, byte nullValue) {
        if (bool == null) {
            return nullValue;
        }
        return (bool.booleanValue() ? trueValue : falseValue);
    }


    /**
     * <p>Converts a boolean to an Byte using the convention that
     * {@code true} is {@code 1} and {@code false} is {@code 0}.</p>
     *
     * <pre>
     *   BooleanUtils.toIntegerObject(true)  = Integer.valueOf(1)
     *   BooleanUtils.toIntegerObject(false) = Integer.valueOf(0)
     * </pre>
     *
     * @param bool the boolean to convert
     * @return one if {@code true}, zero if {@code false}
     */
    public static Byte toByteObject(boolean bool) {
        return bool ? NumberUtils.BYTE_ONE : NumberUtils.BYTE_ZERO;
    }

    /**
     * <p>Converts a boolean to an Byte specifying the conversion values.</p>
     *
     * <pre>
     *   BooleanUtils.toIntegerObject(true, Integer.valueOf(1), Integer.valueOf(0))  = Integer.valueOf(1)
     *   BooleanUtils.toIntegerObject(false, Integer.valueOf(1), Integer.valueOf(0)) = Integer.valueOf(0)
     * </pre>
     *
     * @param bool       the to convert
     * @param trueValue  the value to return if {@code true}, may be {@code null}
     * @param falseValue the value to return if {@code false}, may be {@code null}
     * @return the appropriate value
     */
    public static Byte toByteObject(boolean bool, Byte trueValue, Byte falseValue) {
        return bool ? trueValue : falseValue;
    }

    /**
     * <p>Converts a Boolean to a Byte using the convention that
     * {@code zero} is {@code false}.</p>
     *
     * <p>{@code null} will be converted to {@code null}.</p>
     *
     * <pre>
     *   BooleanUtils.toIntegerObject(Boolean.TRUE)  = Integer.valueOf(1)
     *   BooleanUtils.toIntegerObject(Boolean.FALSE) = Integer.valueOf(0)
     * </pre>
     *
     * @param bool the Boolean to convert
     * @return one if Boolean.TRUE, zero if Boolean.FALSE, {@code null} if {@code null}
     */
    public static Byte toByteObject(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? NumberUtils.BYTE_ONE : NumberUtils.BYTE_ZERO;
    }

    /**
     * <p>Converts a Boolean to an Byte specifying the conversion values.</p>
     *
     * <pre>
     *   BooleanUtils.toIntegerObject(Boolean.TRUE, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(2))  = Integer.valueOf(1)
     *   BooleanUtils.toIntegerObject(Boolean.FALSE, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(2)) = Integer.valueOf(0)
     *   BooleanUtils.toIntegerObject(null, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(2))          = Integer.valueOf(2)
     * </pre>
     *
     * @param bool       the Boolean to convert
     * @param trueValue  the value to return if {@code true}, may be {@code null}
     * @param falseValue the value to return if {@code false}, may be {@code null}
     * @param nullValue  the value to return if {@code null}, may be {@code null}
     * @return the appropriate value
     */
    public static Byte toByteObject(Boolean bool, Byte trueValue, Byte falseValue, Byte nullValue) {
        if (bool == null) {
            return nullValue;
        }
        return bool.booleanValue() ? trueValue : falseValue;
    }

}
