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

import com.ms.core.config.SystemConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 对象拷贝工具类
 *
 * @author ms2297248353
 */
public class ObjectCopyUtils {

    private static final String DATE_FORMAT = SystemConfiguration.SYSTEM_DATE_FORMAT;

    /**
     * 拷贝对象
     * 类型不一致的属性不拷贝
     * 源对象属性为null的不拷贝
     * 不拷贝serialVersionUID
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @return 目标对象
     */
    public static <T> T copy(Object source, T target) {
        return copy(source, target, new String[0]);
    }

    /**
     * 拷贝对象
     * 类型不一致的属性不拷贝
     * 源对象属性为null的不拷贝
     * 不拷贝serialVersionUID
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @param ignore 忽略的属
     * @return 目标对象
     */
    public static <T> T copy(Object source, T target, String... ignore) {
        if (source == null || target == null) {
            return null;
        }
        BeanCopyUtils.copyProperties(source, target, ignore);
        return target;
    }

    /**
     * 拷贝对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @return 目标对象
     */
    public static <T> T copyNonNull(Object source, T target) {
        return copyNonNull(source, target, new String[0]);
    }

    /**
     * 拷贝对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @param ignore 忽略的属性
     * @return 目标对象
     */
    public static <T> T copyNonNull(Object source, T target, String... ignore) {
        if (source == null || target == null) {
            return null;
        }
        BeanCopyUtils.copyPropertiesNonNull(source, target, ignore);
        return target;
    }

    /**
     * 拷贝对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @return 目标对象
     */
    public static <T> T copyNonNullAndEmpty(Object source, T target) {
        return copyNonNullAndEmpty(source, target, null);
    }

    /**
     * 拷贝对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @param ignore 忽略的属性
     * @return 目标对象
     */
    public static <T> T copyNonNullAndEmpty(Object source, T target, String... ignore) {
        if (source == null || target == null) {
            return null;
        }
        BeanCopyUtils.copyPropertiesNonNullAndEmpty(source, target, ignore);
        return target;
    }


    /**
     * 对象拷贝工具方法
     *
     * @param source 源对象
     * @param target 目标对象 class
     * @param <T>    源对象类型
     * @param <S>    目标对象类型
     */
    public static <T, S> S copyProperties(T source, Class<S> target) {
        return copyProperties(source, target, new String[0]);
    }

    /**
     * 对象拷贝工具方法
     *
     * @param source 源对象
     * @param target 目标对象 class
     * @param ignore 忽略的属性
     * @param <T>    源对象类型
     * @param <S>    目标对象类型
     */
    public static <T, S> S copyProperties(T source, Class<S> target, String[] ignore) {
        if (source == null || target == null) {
            return null;
        }
        S s = null;
        try {
            s = target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (s == null) {
            return null;
        }
        BeanCopyUtils.copyProperties(source, s, ignore);
        return s;
    }


    /**
     * 对象拷贝工具方法
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    源对象类型
     * @param <S>    目标对象类型
     */
    public static <T, S> void copyProperties(T source, S target) {
        copyProperties(source, target, new String[0]);
    }

    /**
     * 对象拷贝工具方法
     *
     * @param source 源对象
     * @param target 目标对象
     * @param ignore 忽略的属性
     * @param <T>    源对象类型
     * @param <S>    目标对象类型
     */
    public static <T, S> void copyProperties(T source, S target, String[] ignore) {
        if (source == null || target == null) {
            return;
        }
        List<String> ignoreList = new ArrayList<>(Arrays.asList(ignore));
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
                if (fieldName.equals(targetField.getName())) {
                    try {
                        Object sourceValue = sourceField.get(source);
                        if (sourceValue != null && !isNullOrEmpty(sourceValue)) {
                            Class<?> targetType = targetField.getType();
                            Object targetValue = convertValue(sourceValue, targetType);
                            targetField.set(target, targetValue);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private static boolean isNullOrEmpty(Object value) {
        if (value instanceof String) {
            return ((String) value).isEmpty();
        } else if (value instanceof List) {
            return ((List<?>) value).isEmpty();
        }
        return false;
    }

    private static Object convertValue(Object value, Class<?> targetType) {
        if (value.getClass().equals(targetType)) {
            return value;
        }

        if (targetType.equals(String.class)) {
            if (value instanceof Date) {
                return formatDate((Date) value);
            } else {
                return value.toString();
            }
        }

        if (targetType.equals(Date.class)) {
            if (value instanceof String) {
                return parseDate((String) value);
            }
        }

        try {
            Method valueOfMethod = targetType.getMethod("valueOf", String.class);
            return valueOfMethod.invoke(null, value.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}