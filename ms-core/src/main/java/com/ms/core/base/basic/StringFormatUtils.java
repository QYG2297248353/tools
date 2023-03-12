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

package com.ms.core.base.basic;


import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.text.MessageFormat;
import java.util.Map;

/**
 * 字符串工具类
 *
 * @author 萌森 Ms
 */
public class StringFormatUtils {
    /**
     * 字符串格式化
     * %s字符串 %c字符
     * %d整数 %f浮点
     * %b 布尔
     *
     * @param str  待格式化
     * @param args 插入数据
     * @return 结果数据
     */
    public static String format(String str, Object... args) {
        return String.format(str, args);
    }

    /**
     * 字符串格式化
     * {数字} 占位 可重复使用
     *
     * @param str  待格式化
     * @param data 插入数据
     * @return 结果数据
     */
    public static String messageFormat(String str, String[] data) {
        return MessageFormat.format(str, data);
    }


    /**
     * 字符串格式化
     * map模板形式
     * k为占位符v为替换值
     * #{[K]}
     *
     * @param str  待格式化
     * @param data 键值对数据
     * @return 格式化结果
     */
    public static String formatTemplate(String str, Map<String, Object> data) {
        ExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext parserContext = new TemplateParserContext();
        return parser.parseExpression(str, parserContext).getValue(data, String.class);
    }
}
