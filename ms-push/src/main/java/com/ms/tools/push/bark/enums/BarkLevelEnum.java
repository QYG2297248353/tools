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

package com.ms.tools.push.bark.enums;

/**
 * @author qyg2297248353
 */

public enum BarkLevelEnum {
    /**
     * 立即亮屏显示通知 默认值
     */
    active,
    /**
     * 时效性通知，可在专注状态下显示通知
     */
    timeSensitive,
    /**
     * 仅将通知添加到通知列表，不会亮屏提醒
     */
    passive
}
