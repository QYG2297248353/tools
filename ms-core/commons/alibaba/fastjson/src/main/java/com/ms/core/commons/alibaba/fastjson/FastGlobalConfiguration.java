package com.ms.core.commons.alibaba.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 全局配置
 *
 * @author ms
 */
public class FastGlobalConfiguration {
    private static final String DEFAULT_PREFIX = "com.ms.";

    static {
        ParserConfig.global.setAutoTypeSupport(true);
        ParserConfig.global.addAccept(DEFAULT_PREFIX);
    }

    /**
     * 获取AutoType
     */
    public static void addAutoType(String prefix) {
        ParserConfig.global.addAccept(prefix);
    }
}