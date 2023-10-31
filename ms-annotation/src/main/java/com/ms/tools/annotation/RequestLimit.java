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

package com.ms.tools.annotation;

import java.lang.annotation.*;

/**
 * 请求限流
 * <p>
 * 默认每秒最多访问10次
 *
 * @author ms2297248353
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestLimit {
    /**
     * 资源的key
     * 唯一
     *
     * @return key
     */
    String key() default "";

    /**
     * 最多的访问限制次数
     *
     * @return limitNum
     */
    int limitNum() default 10;

    /**
     * 多少秒
     *
     * @return seconds
     */
    int seconds() default 1;
}
