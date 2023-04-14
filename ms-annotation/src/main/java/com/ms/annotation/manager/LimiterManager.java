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


public interface LimiterManager {

    /**
     * 尝试访问
     *
     * @param limiter 限流器
     * @return true:允许访问，false:不允许访问
     */
    boolean tryAccess(Limiter limiter);
}
