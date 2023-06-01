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

package com.ms.core.base.copy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拷贝工具类
 *
 * @author ms2297248353
 */
public class BeanCopyUtils {

    /**
     * 拷贝对象
     * <p>
     * 默认忽略空值 和 空字符串 和 空列表 的属性
     * 默认忽略 serialVersionUID 属性
     * <p>
     *
     * @param source 源对象
     * @param target 目标对象
     * @param ignore 忽略的属性
     * @param <T>    目标对象类型
     */
    public static <T> void copyProperties(Object source, T target, String[] ignore) {
        List<String> ignoreList = new ArrayList<>();
        if (ignore != null) {
            ignoreList.addAll(Arrays.asList(ignore));
        }
        ignoreList.add("serialVersionUID");

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            String fieldName = sourceField.getName();
            if (ignoreList.contains(fieldName)) {
                continue;
            }

            for (Field targetField : targetFields) {
                targetField.setAccessible(true);
                if (fieldName.equals(targetField.getName()) && sourceField.getType().equals(targetField.getType())) {
                    try {
                        Object fieldValue = sourceField.get(source);
                        if (fieldValue != null && !isNullOrEmpty(fieldValue)) {
                            targetField.set(target, fieldValue);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private static boolean isNullOrEmpty(Object fieldValue) {
        if (fieldValue instanceof String) {
            return ((String) fieldValue).isEmpty();
        } else if (fieldValue instanceof List) {
            return ((List<?>) fieldValue).isEmpty();
        }
        return false;
    }


    /**
     * 拷贝对象
     * <p>
     * 默认忽略 空字符串 和 空列表 的属性 和 serialVersionUID 属性
     * 允许拷贝空值 null
     * </p>
     *
     * @param source 源对象
     * @param target 目标对象
     * @param ignore 忽略的属性
     * @param <T>    目标对象类型
     */
    public static <T> void copyPropertiesNonNull(Object source, T target, String[] ignore) {
        List<String> ignoreList = new ArrayList<>();
        if (ignore != null) {
            ignoreList.addAll(Arrays.asList(ignore));
        }
        ignoreList.add("serialVersionUID");

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            String fieldName = sourceField.getName();
            if (ignoreList.contains(fieldName)) {
                continue;
            }

            for (Field targetField : targetFields) {
                targetField.setAccessible(true);
                if (fieldName.equals(targetField.getName()) && sourceField.getType().equals(targetField.getType())) {
                    try {
                        Object fieldValue = sourceField.get(source);
                        if (fieldValue != null) {
                            if (!isNullOrEmpty(fieldValue)) {
                                targetField.set(target, fieldValue);
                            }
                        } else {
                            targetField.set(target, fieldValue);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    public static <T> void copyPropertiesNonNullAndEmpty(Object source, T target, String[] ignore) {
        List<String> ignoreList = new ArrayList<>();
        if (ignore != null) {
            ignoreList.addAll(Arrays.asList(ignore));
        }
        ignoreList.add("serialVersionUID");

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            String fieldName = sourceField.getName();
            if (ignoreList.contains(fieldName)) {
                continue;
            }

            for (Field targetField : targetFields) {
                targetField.setAccessible(true);
                if (fieldName.equals(targetField.getName()) && sourceField.getType().equals(targetField.getType())) {
                    try {
                        Object fieldValue = sourceField.get(source);
                        targetField.set(target, fieldValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}