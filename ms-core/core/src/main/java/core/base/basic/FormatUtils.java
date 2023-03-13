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

package core.base.basic;


import core.base.basic.enums.DatePatternEnum;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 字符串工具类
 *
 * @author 萌森 Ms
 */
public class FormatUtils {
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
     * @param args 插入数据
     * @return 结果数据
     */
    public static String messageFormat(String str, Object... args) {
        return MessageFormat.format(str, args);
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
    public static String formatTemplate(String str, Map<String, String> data) {
        ExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext parserContext = new TemplateParserContext();
        return parser.parseExpression(str, parserContext).getValue(data, String.class);
    }

    /**
     * 字符串格式化
     * map模板形式
     * k为占位符v为替换值
     * #{[K]}
     *
     * @param str    待格式化
     * @param data   键值对数据
     * @param prefix prefix 前缀
     * @param suffix suffix 后缀
     * @return 格式化结果
     */
    public static String formatTemplate(String str, Map<String, String> data, String prefix, String suffix) {
        ExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext parserContext = new TemplateParserContext(prefix, suffix);
        return parser.parseExpression(str, parserContext).getValue(data, String.class);
    }

    /**
     * 日期格式化
     *
     * @param date 日期
     * @return 格式化结果
     */
    public static String format(Date date) {
        return new SimpleDateFormat(DatePatternEnum.DEFAULT.getPattern()).format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(long date, String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(date));
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(Date date, DatePatternEnum pattern) {
        return new SimpleDateFormat(pattern.getPattern()).format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化结果
     */
    public static String format(long date, DatePatternEnum pattern) {
        return new SimpleDateFormat(pattern.getPattern()).format(new Date(date));
    }


}
