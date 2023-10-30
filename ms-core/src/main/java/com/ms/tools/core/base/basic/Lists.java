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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Lists {
    List EMPTY_LIST = new ArrayList();

    static <T> List<T> of() {
        return new ArrayList<>();
    }

    static <T> List<T> of(T... t) {
        List<T> list = of();
        Collections.addAll(list, t);
        return list;
    }

    /**
     * 数组转List
     *
     * @param array 数组
     * @param <T>   泛型
     * @return List
     */
    static <T> List<T> toList(T[] array) {
        List<T> list = of();
        Collections.addAll(list, array);
        return list;
    }


    /**
     * 数组转List
     *
     * @param array 数组
     * @param <T>   泛型
     * @return List
     */
    static <T> List<T> toList(T[][] array) {
        List<T> list = of();
        for (T[] ts : array) {
            Collections.addAll(list, ts);
        }
        return list;
    }

    /**
     * 数组转List
     *
     * @param array 数组
     * @param <T>   泛型
     * @return List
     */
    static <T> List<T> toList(T[][][] array) {
        List<T> list = of();
        for (T[][] ts : array) {
            for (T[] t : ts) {
                Collections.addAll(list, t);
            }
        }
        return list;
    }

    /**
     * 数组空判断
     *
     * @param array 数组
     * @return boolean
     */
    static boolean isEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 随机列表-不重复
     * 通过百分比返回随机列表
     *
     * @param list    列表
     * @param percent 随机数量百分比 0-100
     * @param <T>     泛型
     * @return 随机列表
     */
    static <T> List<T> random(List<T> list, int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be between 0 and 100");
        }
        if (percent == 0) {
            return Lists.of();
        }
        if (percent == 100) {
            return list;
        }
        int size = list.size();
        int randomSize = (int) Math.round(size * percent / 100.0);
        List<T> randomList = Lists.of();
        for (int i = 0; i < randomSize; i++) {
            int randomIndex = (int) (Math.random() * size);
            randomList.add(list.get(randomIndex));
            list.remove(randomIndex);
            size--;
        }
        return randomList;
    }

    static <T> List<T> newArrayList(T[] bcc) {
        return toList(bcc);
    }

    static <T> List<T> empty() {
        return EMPTY_LIST;
    }
}
