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

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器中的bean
 *
 * @author ms2297248353
 */
@Component
public class BeansUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 根据bean的名称获取bean
     *
     * @param beanClass bean的名称
     * @param <T>       bean的类型
     * @return bean
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    /**
     * 根据bean的名称获取bean
     *
     * @param beanName bean的名称
     * @param <T>      bean的类型
     * @return bean
     */
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据bean的名称获取bean
     *
     * @param beanName  bean的名称
     * @param beanClass bean的类型
     * @param <T>       bean的类型
     * @return bean
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return applicationContext.getBean(beanName, beanClass);
    }

    /**
     * 根据bean的名称获取bean
     *
     * @param beanName bean的名称
     * @param args     构造函数参数
     * @param <T>      bean的类型
     * @return bean
     */
    public static <T> T getBean(String beanName, Object... args) {
        return (T) applicationContext.getBean(beanName, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        BeansUtils.applicationContext = applicationContext;
    }
}
