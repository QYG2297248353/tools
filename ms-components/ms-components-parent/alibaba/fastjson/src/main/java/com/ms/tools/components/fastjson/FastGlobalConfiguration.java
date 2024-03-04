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

package com.ms.tools.components.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 全局配置
 *
 * @author ms
 */
public class FastGlobalConfiguration {
    private static final String DEFAULT_PREFIX = "com.ms.tools";

    static {
        ParserConfig.global.setAutoTypeSupport(true);
        ParserConfig.global.addAccept(DEFAULT_PREFIX);
    }

    /**
     * 获取AutoType
     *
     * @param prefix 前缀
     */
    public static void addAutoType(String prefix) {
        ParserConfig.global.addAccept(prefix);
    }
}
