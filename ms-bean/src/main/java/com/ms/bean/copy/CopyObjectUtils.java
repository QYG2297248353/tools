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

package com.ms.bean.copy;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author ms2297248353
 */
public class CopyObjectUtils extends BeanUtils {

    /**
     * 属性拷贝,第一个参数中的属性值拷贝到第二个参数中<br>
     * 注意:当第一个参数中的属性有null值时,不会拷贝进去
     *
     * @param from             源对象
     * @param to               目标对象
     * @param ignoreProperties 忽略对象
     * @throws BeansException 异常
     */
    public static void copyPropertiesIgnoreNull(Object from, Object to, String... ignoreProperties)
            throws BeansException {
        Assert.notNull(from, "Source must not be null");
        Assert.notNull(to, "Target must not be null");

        Class<?> actualEditable = to.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(from.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(from);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            // 这里判断value是否为空 当然这里也能进行一些特殊要求的处理
                            // 例如绑定时格式转换等等
                            if (value != null) {
                                if (!Modifier.isPublic(writeMethod
                                        .getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(to, value);
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    public static void copyProperties(Object from, Object to) {
        BeanUtils.copyProperties(from, to);
    }

    public static <T> T copyBean(Object from, Supplier<T> supplier) {
        Objects.requireNonNull(from);
        T to = supplier.get();
        BeanUtils.copyProperties(from, to);
        return to;
    }

    public static <T> T copyBeanNullable(Object from, Supplier<T> supplier) {
        if (from == null) {
            return supplier.get();
        }
        T to = supplier.get();
        BeanUtils.copyProperties(from, to);
        return to;
    }

    public static <T> T copyBean(Object from, Supplier<T> supplier, Consumer<T> after) {
        if (from == null) {
            return null;
        }
        T to = supplier.get();
        BeanUtils.copyProperties(from, to);
        after.accept(to);
        return to;
    }

    /**
     * List 复制 List
     *
     * @param fromList  原始数据
     * @param toElement 操作
     * @param <T>       fromList
     * @return 转换对象
     */
    public static <T> List<T> copyList(List<?> fromList, Supplier<T> toElement) {
        if (fromList == null || fromList.isEmpty()) {
            return new ArrayList<>();
        }
        return fromList.stream()
                .map(source -> {
                    T target = toElement.get();
                    BeanUtils.copyProperties(source, target);
                    return target;
                })
                .collect(Collectors.toList());
    }

    /**
     * List 复制 List
     *
     * @param fromList 原始数据
     * @param function 操作
     * @param <E>      fromList
     * @param <R>      copyList
     * @return 转换对象
     */
    public static <E, R> List<R> copyList(List<E> fromList, Function<E, R> function) {
        if (fromList == null) {
            return Collections.emptyList();
        }
        return fromList.stream()
                .map(source -> {
                    R target = function.apply(source);
                    BeanUtils.copyProperties(source, target);
                    return target;
                })
                .collect(Collectors.toList());
    }

    /**
     * List 复制 List
     *
     * @param fromList  原始数据
     * @param toElement 转换对象
     * @param after     后操作
     * @param <T>       对象
     * @return 转换对象
     */
    public static <T> List<T> copyList(List<?> fromList, Supplier<T> toElement, Consumer<T> after) {
        if (fromList == null) {
            return Collections.emptyList();
        }
        return fromList.stream()
                .map(source -> {
                    T target = toElement.get();
                    BeanUtils.copyProperties(source, target);
                    after.accept(target);
                    return target;
                })
                .collect(Collectors.toList());
    }

    /**
     * 深层次拷贝，通过json转换的方式实现
     *
     * @param from    待转换的类
     * @param toClass 目标类class
     * @param <T>     目标类
     * @return 返回目标类
     */
    public static <T> T deepCopy(Object from, Class<T> toClass) {
        String json = JSON.toJSONString(from);
        return JSON.parseObject(json, toClass);
    }


}
