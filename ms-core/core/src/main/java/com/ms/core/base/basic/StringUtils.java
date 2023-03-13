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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author 萌森 Ms
 */
public class StringUtils {
    /**
     * 失败的索引搜索
     */
    public static final int INDEX_NOT_FOUND = -1;

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
     * 判断字符串是否为空
     * demo:
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return 是否不为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否为空
     * <p>
     * 与isBlank的区别是，isBlank会去除字符串两端的空格，而isEmpty不会
     * </p>
     * demo:
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * StringUtils.isEmpty(" ")       = false
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否以指定字符串开头
     *
     * @param str    字符串
     * @param prefix 指定字符串
     * @return 是否以指定字符串开头
     */
    public static boolean startsWith(String str, String prefix) {
        return str != null && str.startsWith(prefix);
    }

    /**
     * 判断字符串是否以指定字符串结尾
     *
     * @param str    字符串
     * @param suffix 指定字符串
     * @return 是否以指定字符串结尾
     */
    public static boolean endsWith(String str, String suffix) {
        return str != null && str.endsWith(suffix);
    }

    /**
     * 查找CharSequence中的第n个索引，处理null
     * <p>
     * demo:
     * A null CharSequence will return -1.
     * <p>
     * StringUtils.ordinalIndexOf(null, *, *)          = -1
     * StringUtils.ordinalIndexOf(*, null, *)          = -1
     * StringUtils.ordinalIndexOf("", "", *)           = 0
     * StringUtils.ordinalIndexOf("aabaabaa", "a", 1)  = 0
     * StringUtils.ordinalIndexOf("aabaabaa", "a", 2)  = 1
     * StringUtils.ordinalIndexOf("aabaabaa", "b", 1)  = 2
     * StringUtils.ordinalIndexOf("aabaabaa", "b", 2)  = 5
     * StringUtils.ordinalIndexOf("aabaabaa", "ab", 1) = 1
     * StringUtils.ordinalIndexOf("aabaabaa", "ab", 2) = 4
     * StringUtils.ordinalIndexOf("aabaabaa", "", 1)   = 0
     * StringUtils.ordinalIndexOf("aabaabaa", "", 2)   = 0
     * <p>
     * Matches may overlap:
     * <p>
     * StringUtils.ordinalIndexOf("ababab", "aba", 1)   = 0
     * StringUtils.ordinalIndexOf("ababab", "aba", 2)   = 2
     * StringUtils.ordinalIndexOf("ababab", "aba", 3)   = -1
     * <p>
     * StringUtils.ordinalIndexOf("abababab", "abab", 1) = 0
     * StringUtils.ordinalIndexOf("abababab", "abab", 2) = 2
     * StringUtils.ordinalIndexOf("abababab", "abab", 3) = 4
     * StringUtils.ordinalIndexOf("abababab", "abab", 4) = -1
     *
     * @param str       CharSequence
     * @param searchStr 要查找的字符串
     * @param ordinal   第几个
     * @return 第n个索引
     */
    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, false);
    }

    /**
     * 查找CharSequence中的第n个索引，处理null
     * <p>
     * demo:
     * A null CharSequence will return -1.
     *
     * @param str       CharSequence
     * @param searchStr 要查找的字符串
     * @param ordinal   第几个
     * @param lastIndex 是否从后向前查找
     * @return 第n个索引
     */
    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal, boolean lastIndex) {
        if (str == null || searchStr == null || ordinal <= 0) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return lastIndex ? str.length() : 0;
        }
        int found = 0;
        int index = lastIndex ? str.length() : INDEX_NOT_FOUND;
        do {
            if (lastIndex) {
                index = CharSequenceUtils.lastIndexOf(str, searchStr, index - 1);
            } else {
                index = CharSequenceUtils.indexOf(str, searchStr, index + 1);
            }
            if (index < 0) {
                return index;
            }
            found++;
        } while (found < ordinal);
        return index;
    }

    /**
     * 将提供的文本拆分为数组，指定分隔符。
     *
     * @param str           要拆分的字符串
     * @param separatorChar 分隔符
     * @return 拆分的字符串数组
     */
    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    /**
     * 将提供的文本拆分为数组，指定分隔符。
     *
     * @param str           要拆分的字符串
     * @param separatorChar 分隔符
     * @param b             是否保留空字符串
     * @return 拆分的字符串数组
     */
    private static String[] splitWorker(String str, char separatorChar, boolean b) {
        if (str == null) {
            return Strings.EMPTY_ARRAY;
        }
        int len = str.length();
        if (len == 0) {
            return Strings.EMPTY_ARRAY;
        }
        List<String> list = new ArrayList<>();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || b) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || (b && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[0]);
    }

    /**
     * 将提供的文本拆分为数组，指定分隔符。
     *
     * @param str            要拆分的字符串
     * @param separatorChars 分隔符
     * @return 拆分的字符串数组
     */
    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, -1);
    }

    /**
     * 提供的文本拆分为具有最大长度的数组，指定分隔符。
     *
     * @param str            要拆分的字符串
     * @param separatorChars 分隔符
     * @param max            最大长度
     * @return 拆分的字符串数组
     */
    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    /**
     * 提供的文本拆分为具有最大长度的数组，指定分隔符。
     *
     * @param str            要拆分的字符串
     * @param separatorChars 分隔符
     * @param max            最大长度
     * @param b              是否保留空字符串
     * @return 拆分的字符串数组
     */
    private static String[] splitWorker(String str, String separatorChars, int max, boolean b) {
        if (str == null) {
            return Strings.EMPTY_ARRAY;
        }
        int len = str.length();
        if (len == 0) {
            return Strings.EMPTY_ARRAY;
        }
        List<String> list = new ArrayList<>();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;
        if (separatorChars == null) {
            // null表示使用空白分隔符
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || b) {
                        if (sizePlus1++ == max) {
                            i = len;
                            list.add(str.substring(start));
                        } else {
                            list.add(str.substring(start, i));
                            match = false;
                        }
                    }
                    start = ++i;
                    continue;
                }
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // 优化分隔符长度为1的情况
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || b) {
                        if (sizePlus1++ == max) {
                            i = len;
                            list.add(str.substring(start));
                        } else {
                            list.add(str.substring(start, i));
                            match = false;
                        }
                    }
                    start = ++i;
                    continue;
                }
                match = true;
                i++;
            }
        } else {
            // 一般情况
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || b) {
                        if (sizePlus1++ == max) {
                            i = len;
                            list.add(str.substring(start));
                        } else {
                            list.add(str.substring(start, i));
                            match = false;
                        }
                    }
                    start = ++i;
                    continue;
                }
                match = true;
                i++;
            }
        }
        if (match || (b && start != len)) {
            list.add(str.substring(start));
        }
        return list.toArray(new String[0]);
    }


}
