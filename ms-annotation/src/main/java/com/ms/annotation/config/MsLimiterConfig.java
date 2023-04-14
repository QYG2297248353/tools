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

package com.ms.annotation.config;

import com.ms.annotation.manager.GuavaLimiter;
import com.ms.annotation.manager.LimiterManager;
import com.ms.annotation.manager.RedisLimiter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 限流模式
 *
 * @author ms2297248353
 */
@Configuration
public class MsLimiterConfig {

    /**
     * 本地限流
     *
     * @return 限流器
     */
    @Bean
    @ConditionalOnProperty(name = LimitAspectCondition.LIMIT_TYPE, havingValue = "local")
    public LimiterManager guavaLimiter() {
        return new GuavaLimiter();
    }


    /**
     * redis限流
     *
     * @param stringRedisTemplate redis模板
     * @return 限流器
     */
    @Bean
    @ConditionalOnProperty(name = LimitAspectCondition.LIMIT_TYPE, havingValue = "redis")
    public LimiterManager redisLimiter(StringRedisTemplate stringRedisTemplate) {
        return new RedisLimiter(stringRedisTemplate);
    }
}
