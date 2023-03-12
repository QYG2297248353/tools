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

import com.ms.core.base.enums.regular.RegexpEnum;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.text.MessageFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author 萌森 Ms
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 待处理字符串
     * @return 处理后字符串
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            str = str.trim();
            Pattern p = Pattern.compile(RegexpEnum.REGEX_STRING.regex());
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 移除Html标签
     *
     * @param content 内容
     * @param style   是否移除样式标签以及标签内容
     * @param script  是否移除Js标签以及标签内容
     * @return 字符串
     */
    public static String stripHtml(String content, boolean style, boolean script) {
        if (style) {
            content = stripStyle(content);
        }
        if (script) {
            content = stripScript(content);
        }
        Pattern pHtml = Pattern.compile(RegexpEnum.REGEX_HTML_LABEL.regex(), Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(content);
        String all = mHtml.replaceAll("");
        return processingLabel(all);
    }

    /**
     * 移除Html标签保留内容
     *
     * @param content 内容
     * @return 字符串
     */
    public static String stripHtml(String content) {
        Pattern pHtml = Pattern.compile(RegexpEnum.REGEX_HTML_LABEL.regex(), Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(content);
        String all = mHtml.replaceAll("");
        return processingLabel(all);
    }

    /**
     * 移除script标签以及内容
     *
     * @param content 内容
     * @return 字符串
     */
    public static String stripScript(String content) {
        Pattern pScript = Pattern.compile(RegexpEnum.REGEX_SCRIPT_LABEL.regex(), Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(content);
        String all = mScript.replaceAll("");
        return processingLabel(all);
    }

    /**
     * 移除style标签以及内容
     *
     * @param content 内容
     * @return 字符串
     */
    public static String stripStyle(String content) {
        Pattern pStyle = Pattern.compile(RegexpEnum.REGEX_STYLE_LABEL.regex(), Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(content);
        String all = mStyle.replaceAll("");
        return processingLabel(all);
    }

    private static String processingLabel(String content) {
        return content.replaceAll("&nbsp;", "\t");
    }

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
