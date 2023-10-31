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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Maps {

    /**
     * 空map
     * <p>
     * 当前对象为不可变对象 无法修改
     */
    Map EMPTY_MAP_FINAL = Collections.emptyMap();

    /**
     * 空map
     *
     * @param <K> key
     * @param <V> value
     * @return map
     */
    static <K, V> Map<K, V> emptyMap() {
        return new HashMap<>();
    }

    /**
     * 空map
     *
     * @param <K> key
     * @param <V> value
     * @return map
     */
    static <K, V> Map<K, V> of() {
        return new HashMap<>();
    }

    /**
     * 对象转map
     * key 为序号
     *
     * @param obj 对象
     * @param <V> 泛型
     * @return map
     */
    static <V> Map of(V... obj) {
        Map<Integer, V> map = of();
        for (int i = 0; i < obj.length; i++) {
            map.put(i, obj[i]);
        }
        return map;
    }

    static <K, V> Map<K, V> convert(K k1, V v1) {
        Map<K, V> map = of();
        map.put(k1, v1);
        return map;
    }

    static <K, V> Map<K, V> convert(K k1, V v1, K k2, V v2) {
        Map<K, V> map = of();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    static <K, V> Map<K, V> convert(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> map = of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    static <K, V> Map<K, V> convert(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> map = of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    static <K, V> Map<K, V> convert(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> map = of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    static <K, V> Map<K, V> convert(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map<K, V> map = of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        return map;
    }

    /**
     * 删除map中的空值
     *
     * @param map map
     * @param <K> key
     * @param <V> value
     * @return map
     */
    static <K, V> Map<K, V> removeNullValue(Map<K, V> map) {
        Map<K, V> result = new HashMap<>();
        map.forEach((k, v) -> {
            if (v != null) {
                result.put(k, v);
            }
        });
        return result;
    }

    /**
     * 删除map中的空值与空字符串
     *
     * @param map map
     * @param <K> key
     * @param <V> value
     * @return map
     */
    static <K, V> Map<K, V> removeNullAndEmptyValue(Map<K, V> map) {
        Map<K, V> result = new HashMap<>();
        map.forEach((k, v) -> {
            if (v != null && !"".equals(v)) {
                result.put(k, v);
            }
        });
        return result;
    }
}
