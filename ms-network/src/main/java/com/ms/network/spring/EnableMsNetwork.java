package com.ms.network.spring;

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
@Import(NetworkAutoConfiguration.class)
public @interface EnableMsNetwork {

}
