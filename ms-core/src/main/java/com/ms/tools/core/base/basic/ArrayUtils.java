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

import java.util.Arrays;

/**
 * 数组工具类
 *
 * @author ms
 */
public interface ArrayUtils {

    /**
     * 空数组
     */
    Object[] EMPTY_ARRAY = new Object[0];

    /**
     * 空对象数组
     *
     * @return 空对象数组
     */
    static Object[] empty() {
        return EMPTY_ARRAY;
    }

    public static void main(String[] args) {
        Object[] empty = empty();

        System.out.println(Arrays.toString(empty));
        System.out.println(Arrays.toString(EMPTY_ARRAY));

    }

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
     * 删除数组中指定的元素
     *
     * @param array    原始数组
     * @param delArray 要删除的数组对象
     * @return 处理后的数组
     */
    static <T> T[] remove(T[] array, T... delArray) {
        if (isEmpty(array) || isEmpty(delArray)) {
            return array;
        }
        // 复制对象
        T[] arrayCopy = Arrays.copyOf(array, array.length);
        for (T t : delArray) {
            int index = indexOf(arrayCopy, t);
            if (index != -1) {
                arrayCopy = remove(arrayCopy, index);
            }
        }
        return arrayCopy;
    }

    /**
     * 获取数组中指定元素的索引
     *
     * @param array 数组
     * @param t     元素
     * @param <T>   数组类型
     * @return 索引
     */
    static <T> int indexOf(T[] array, T t) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (t.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
}
