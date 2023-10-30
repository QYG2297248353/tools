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

package com.ms.tools.core.base.basic;

import jdk.nashorn.internal.objects.NativeString;

import java.util.Arrays;

/**
 * 数组工具类
 *
 * @author ms
 */
public interface ArrayUtils {

    /**
     * 判断数组是否为空
     */
    static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不为空
     */
    static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 合并数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @param <T>    数组类型
     * @return 合并后的数组
     */
    static <T> T[] merge(T[] array1, T[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        T[] mergedArray = Arrays.copyOf(array1, length1 + length2);
        System.arraycopy(array2, 0, mergedArray, length1, length2);
        return mergedArray;
    }

    /**
     * 删除数组中的元素
     *
     * @param array 数组
     * @param index 索引
     * @return 删除后的数组
     */
    static <T> T[] remove(T[] array, int index) {
        int length = array.length;
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        T[] result = Arrays.copyOf(array, length - 1);
        System.arraycopy(array, index + 1, result, index, length - index - 1);
        return result;
    }

    /**
     * 删除数组中的元素
     *
     * @param array    数组
     * @param delArray 要删除的数组
     * @return 删除后的数组
     */
    static <T> T[] remove(T[] array, T... delArray) {
        int length = array.length;
        int delLength = delArray.length;
        T[] result = Arrays.copyOf(array, length - delLength);
        for (T t : delArray) {
            int index = NativeString.indexOf(array, t);
            if (index != -1) {
                System.arraycopy(array, index + 1, result, index, length - index - 1);
            }
        }
        return result;
    }

    static void main(String[] args) {
        String[] array1 = {"1", "2", "3", "4", "5"};
        String[] array2 = {"6", "7", "8", "9", "10"};
        String[] merge = merge(array1, array2);
        System.out.println(Arrays.toString(merge));

        String[] remove = remove(array1, 2);
        System.out.println(Arrays.toString(remove));

        String[] remove1 = remove(array1, "2", "3");
        System.out.println(Arrays.toString(remove1));
    }

}
