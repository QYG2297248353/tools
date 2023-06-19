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

import com.ms.core.base.basic.ArrayUtils;
import com.ms.core.base.datetime.DateTimeUtils;
import com.ms.core.config.SystemConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
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
        copyProperties(source, s, ignore);
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
        copyProperties(source, target, ignore, true);
    }

    /**
     * 对象拷贝工具方法
     *
     * @param source           源对象
     * @param target           目标对象
     * @param ignore           忽略的属性
     * @param serialVersionUID 是否忽略 serialVersionUID 属性
     * @param <T>              源对象类型
     * @param <S>              目标对象类型
     */
    public static <T, S> void copyProperties(T source, S target, String[] ignore, boolean serialVersionUID) {
        if (source == null || target == null) {
            return;
        }

        List<String> ignoreList = new ArrayList<>(Arrays.asList(ignore));
        if (serialVersionUID) {
            ignoreList.add("serialVersionUID");
        }
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] sourceSuperFields = source.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsL = ArrayUtils.addAll(sourceFields, sourceSuperFields);

        Field[] targetFields = target.getClass().getDeclaredFields();
        Field[] targetSuperFields = target.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsR = ArrayUtils.addAll(targetFields, targetSuperFields);

        for (Field sourceField : fieldsL) {
            sourceField.setAccessible(true);
            String fieldName = sourceField.getName();
            if (ignoreList.contains(fieldName)) {
                continue;
            }

            for (Field targetField : fieldsR) {
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
        // 如果类型相同，直接返回
        if (value.getClass().equals(targetType)) {
            return value;
        }

        // 如果目标类型是 String 类型 当前为其他类型
        if (targetType.equals(String.class)) {
            // 如果源类型是 Date 类型 ，则格式化日期 yyyy/MM/dd HH:mm:ss
            if (value instanceof Timestamp) {
                // 如果源类型是 Timestamp 类型 ，则格式化日期 yyyy/MM/dd HH:mm:ss
                return formatDate(new Date(((Timestamp) value).getTime()));
            } else if (value instanceof Date) {
                return formatDate((Date) value);
            } else if (value instanceof Integer) {
                // 如果源类型是 Integer 类型 ，则格式化日期 yyyy/MM/dd HH:mm:ss
                return formatDate(new Date((Integer) value * 1000L));
            } else if (value instanceof Long) {
                // 如果源类型是 Long 类型 ，则格式化日期 yyyy/MM/dd HH:mm:ss
                return formatDate(new Date((Long) value));
            }
            // 其他类型直接转换为 String 类型
            return value.toString();
        }

        // 如果目标类型是 Date 类型 当前为其他类型
        if (targetType.equals(Date.class)) {
            if (value instanceof String) {
                // 如果源类型是 String 类型
                return DateTimeUtils.parseTime((String) value);
            } else if (value instanceof Long) {
                // 如果类型是 13位 Long 时间戳类型
                return new Date((Long) value);
            } else if (value instanceof Integer) {
                // 如果类型是 10位 Integer 时间戳类型
                return new Date((Integer) value * 1000L);
            } else if (value instanceof Timestamp) {
                // 如果类型是 Timestamp 类型
                return new Date(((Timestamp) value).getTime());
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
}