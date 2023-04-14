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

package com.ms.annotation.aspect;

import com.ms.annotation.MethodRunTime;
import com.ms.core.base.basic.FormatUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Aspect
@Component
public class MethodRunTimeAspect {
    private static final Logger log = Logger.getLogger(MethodRunTimeAspect.class.getName());

    @Around("@annotation(com.ms.annotation.MethodRunTime)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MethodRunTime timeLogAnnotation = method.getAnnotation(MethodRunTime.class);
        long startTime = System.nanoTime();

        Object result = joinPoint.proceed();

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double elapsedMillis = TimeUnit.NANOSECONDS.toMillis(elapsedTime);
        double elapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(elapsedTime);
        double elapsedMinutes = TimeUnit.NANOSECONDS.toMinutes(elapsedTime);
        double elapsedHours = TimeUnit.NANOSECONDS.toHours(elapsedTime);
        String timeUnit;
        double elapsedTimeValue;
        if (elapsedHours >= 1.0) {
            timeUnit = "hours";
            elapsedTimeValue = elapsedHours;
        } else if (elapsedMinutes >= 1.0) {
            timeUnit = "minutes";
            elapsedTimeValue = elapsedMinutes;
        } else if (elapsedSeconds >= 1.0) {
            timeUnit = "seconds";
            elapsedTimeValue = elapsedSeconds;
        } else {
            timeUnit = "milliseconds";
            elapsedTimeValue = elapsedMillis;
        }
        String format = FormatUtils.format("Thread {} Method {} run time: {} {}", Thread.currentThread().getName(), method.getName(), elapsedTimeValue, timeUnit);
        log.info(format);
        return result;
    }
}
