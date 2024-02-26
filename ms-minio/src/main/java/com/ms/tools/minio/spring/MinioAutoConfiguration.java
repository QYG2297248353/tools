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

package com.ms.tools.minio.spring;

import com.ms.tools.minio.properties.MsMinioProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author ms2297248353
 */
@Configuration
@EnableConfigurationProperties(MsMinioProperties.class)
@ComponentScan(basePackages = MsMinioBeanFactoryPostProcessor.BASE_PACKAGE,
        includeFilters = @ComponentScan.Filter({Service.class, Controller.class, Configuration.class, Bean.class, Component.class}),
        useDefaultFilters = false)
public class MinioAutoConfiguration {

    /**
     * 注册Beans
     *
     * @return MyUtilsBeanFactoryPostProcessor
     */
    @Bean
    public static MsMinioBeanFactoryPostProcessor msRedisBeanFactoryPostProcessor() {
        return new MsMinioBeanFactoryPostProcessor();
    }


}
