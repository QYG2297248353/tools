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

package com.ms.spring;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.HashSet;
import java.util.Set;

public class ClassUtils {

    /**
     * 扫描指定包下的所有类
     *
     * @param basePackage 包名
     * @param annotations 注解
     * @return Set 指定集合
     */
    public static Set<Class<?>> scanClassesAnnotatedWith(String basePackage, Class... annotations) {
        Set<Class<?>> classes = new HashSet<>();
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        for (Class annotation : annotations) {
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotation));
        }
        for (org.springframework.beans.factory.config.BeanDefinition beanDefinition : scanner.findCandidateComponents(basePackage)) {
            try {
                classes.add(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                // handle exception
            }
        }
        return classes;
    }

}
