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
