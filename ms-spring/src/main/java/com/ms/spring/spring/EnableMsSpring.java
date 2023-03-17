package com.ms.spring.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用-网络客户端
 * 开启SpringBoot 自动注入
 *
 * @author ms
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SpringAutoConfiguration.class)
public @interface EnableMsSpring {

}
