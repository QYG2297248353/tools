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

package com.ms.tools.annotation.aspect;

import com.ms.tools.annotation.RequestLimit;
import com.ms.tools.annotation.config.LimitAspectCondition;
import com.ms.tools.annotation.manager.Limiter;
import com.ms.tools.annotation.manager.LimiterManager;
import com.ms.tools.core.exception.base.MsToolsRuntimeException;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;


/**
 * @author ms2297248353
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Conditional(LimitAspectCondition.class)
public class RequestLimitAspect {

    @Setter(onMethod_ = @Autowired)
    private LimiterManager limiterManager;

    @Pointcut("@annotation(com.ms.annotation.RequestLimit)")
    private void check() {

    }

    @Before("check()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RequestLimit limit = method.getAnnotation(RequestLimit.class);
        if (limit != null) {

            Limiter limiter = Limiter.builder().limitNum(limit.limitNum())
                    .seconds(limit.seconds())
                    .key(limit.key()).build();

            if (!limiterManager.tryAccess(limiter)) {
                throw new MsToolsRuntimeException("There are currently many people , please try again later!");
            }
        }
    }
}
