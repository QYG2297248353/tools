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

package com.ms.annotation.manager;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.ms.core.base.basic.FormatUtils;
import com.ms.core.exception.base.MsLimiterRunException;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


/**
 * Guava限流实现类
 *
 * @author ms2297248353
 */
public class GuavaLimiter implements LimiterManager {
    private static final Logger log = Logger.getLogger(GuavaLimiter.class.getName());

    private final Map<String, RateLimiter> limiterMap = Maps.newConcurrentMap();

    @Override
    public boolean tryAccess(Limiter limiter) {
        RateLimiter rateLimiter = getRateLimiter(limiter);
        if (rateLimiter == null) {
            return false;
        }

        boolean access = rateLimiter.tryAcquire(1, 100, TimeUnit.MILLISECONDS);
        String format = FormatUtils.format("{} access :{}", limiter.getKey(), access);
        log.info(format);
        return access;
    }

    /**
     * 创建Guava 限流器
     *
     * @param limiter 限流配置
     * @return RateLimiter
     */
    private RateLimiter getRateLimiter(Limiter limiter) {
        if (limiter == null) {
            return null;
        }

        String key = limiter.getKey();
        if (StringUtils.isEmpty(key)) {
            throw new MsLimiterRunException("guava limiter key cannot be null");
        }

        RateLimiter rateLimiter = limiterMap.get(key);

        if (rateLimiter == null) {
            int seconds = limiter.getSeconds();
            double limitNum = limiter.getLimitNum();
            double permitsPerSecond = limitNum / seconds;
            RateLimiter newRateLimiter = RateLimiter.create(permitsPerSecond);

            rateLimiter = limiterMap.putIfAbsent(key, newRateLimiter);

            if (rateLimiter == null) {
                rateLimiter = newRateLimiter;
            }
        }
        return rateLimiter;
    }

}
