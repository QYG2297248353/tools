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

import java.util.Arrays;

/**
 * 数组工具类
 *
 * @author ms2297248353
 */
public class ArrayUtils {

    /**
     * 合并数组
     *
     * @param array1 数组1
     * @param array2  数组2
     * @return 合并后的数组
     */
    public static <T> T[] addAll(T[] array1, T[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        T[] mergedArray = Arrays.copyOf(array1, length1 + length2);
        System.arraycopy(array2, 0, mergedArray, length1, length2);
        return mergedArray;
    }
}