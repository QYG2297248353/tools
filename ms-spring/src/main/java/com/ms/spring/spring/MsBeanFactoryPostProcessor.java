package com.ms.spring.spring;

import com.ms.spring.ClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Set;

public class MsBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    protected static final String BASE_PACKAGE = "com.ms.spring";

    protected static final Class[] ANNOTATIONS = new Class[]{Service.class, Controller.class, Configuration.class, Bean.class, Component.class};


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Set<Class<?>> classes = ClassUtils.scanClassesAnnotatedWith(BASE_PACKAGE, ANNOTATIONS);
        for (Class<?> clazz : classes) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            beanDefinition.setAutowireCandidate(true);
            beanDefinition.setPrimary(false);
            registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }


}
