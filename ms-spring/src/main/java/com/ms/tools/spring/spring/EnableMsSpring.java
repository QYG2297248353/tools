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

package com.ms.tools.spring.spring;

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
