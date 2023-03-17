package com.ms.spring.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = MsBeanFactoryPostProcessor.BASE_PACKAGE,
        includeFilters = @ComponentScan.Filter({Service.class, Controller.class, Configuration.class, Bean.class, Component.class}),
        useDefaultFilters = false)
public class SpringAutoConfiguration {

    /**
     * 注册Beans
     *
     * @return MyUtilsBeanFactoryPostProcessor
     */
    @Bean
    public MsBeanFactoryPostProcessor msBeanFactoryPostProcessor() {
        return new MsBeanFactoryPostProcessor();
    }


}
