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

package com.ms.tools.push.dingtalk.factory;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.core.base.basic.Strings;

/**
 * @author ms2297248353
 */
public interface AtImpl {
    /**
     * at用户手机号码
     *
     * @return 参数 手机号码列表
     */
    @JSONField(serialize = false)
    default String[] getAtMobiles() {
        return Strings.EMPTY_ARRAY;
    }

    /**
     * At用户Id
     *
     * @return 参数
     */
    @JSONField(serialize = false)
    default String[] getAtUserIds() {
        return Strings.EMPTY_ARRAY;
    }

    /**
     * 是否At全体
     *
     * @return 参数
     */
    @JSONField(serialize = false)
    default boolean hasAtAll() {
        return false;
    }
}
