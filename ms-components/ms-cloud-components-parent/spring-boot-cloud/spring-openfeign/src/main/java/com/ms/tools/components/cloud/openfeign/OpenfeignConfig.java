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

package com.ms.tools.components.cloud.openfeign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Openfeign配置类
 * <p>
 * 1、使用RequestContextHolder拿到原生请求的请求头信息（下文环境保持器）
 * 2、获取老请求里的cookie信息
 * 3、同步Cookie （将老请求里的cookie信息放入新请求里（RequestTemplate））
 * 4、同步请求头
 *
 * @author ms2297248353
 */
@Configurable
public class OpenfeignConfig {
    @Bean("requestInterceptor")
    public static RequestInterceptor requestInterceptor() {
        // 创建拦截器
        return template -> {
            // 1、使用RequestContextHolder拿到原生请求的请求头信息（下文环境保持器）
            // 从ThreadLocal中获取请求头（要保证feign调用与controller请求处在同一线程环境）
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                // 获取controller请求对象
                HttpServletRequest request = requestAttributes.getRequest();
                // 如果使用线程池进行远程调用，则request是空的（因为RequestContextHolder.getRequestAttributes()是从threadlocal里拿的值）
                if (Objects.nonNull(request)) {
                    // 2、获取老请求里的cookie信息
                    String cookie = request.getHeader("Cookie");
                    // 同步Cookie （将老请求里的cookie信息放入新请求里（RequestTemplate））
                    template.header("Cookie", cookie);
                    // 3、同步请求头
                    String authorization = request.getHeader("Authorization");
                    template.header("Authorization", authorization);
                }
            }
        };
    }
}
