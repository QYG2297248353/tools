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

package com.ms.tools.core.base.basic;

import com.ms.tools.core.enums.regular.RegexpEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ms2297248353
 */

public interface Strings {
    String EMPTY = "";
    String EMPTY_JSON = "{}";
    String EMPTY_JSON_ARRAY = "[]";
    String[] EMPTY_ARRAY = new String[0];
    String SPACE = " ";
    String COMMA = ",";
    String DOT = ".";
    String COLON = ":";
    String SEMICOLON = ";";
    String UNDERLINE = "_";
    String DASHED = "-";
    String SLASH = "/";
    String BACKSLASH = "\\";
    String BRACKET_LEFT = "(";
    String BRACKET_RIGHT = ")";
    String BRACE_LEFT = "{";
    String BRACE_RIGHT = "}";
    String BRACKET_LEFT_SQUARE = "[";
    String BRACKET_RIGHT_SQUARE = "]";
    String EQUALS = "=";
    String AND = "&";
    String AT = "@";
    String STAR = "*";
    String PERCENT = "%";
    String PLUS = "+";
    String DOLLAR = "$";
    String HASH = "#";
    String EXCLAMATION_MARK = "!";
    String QUESTION_MARK = "?";
    String CARET = "^";
    String TILDE = "~";
    String SINGLE_QUOTE = "'";
    String DOUBLE_QUOTE = "\"";
    String BACKTICK = "`";
    String LEFT_ARROW = "<-";
    String RIGHT_ARROW = "->";
    String LEFT_DOUBLE_ARROW = "<=";
    String RIGHT_DOUBLE_ARROW = "=>";
    String LEFT_RIGHT_ARROW = "<->";
    String LEFT_RIGHT_DOUBLE_ARROW = "<=>";
    String VERTICAL_LINE = "|";
    String VERTICAL_DOUBLE_LINE = "||";
    String HTTP = "http";
    String HTTP_HEAD = "http://";
    String HTTPS = "https";
    String HTTPS_HEAD = "https://";
    String GBK = "GBK";
    String UTF_8 = "UTF-8";
    String UTF_16 = "UTF-16";
    String LF = "\n";
    String CR = "\r";
    String CRLF = "\r\n";


    /**
     * 失败的索引搜索
     */
    int INDEX_NOT_FOUND = -1;

    /**
     * 是否为空白字符串
     *
     * @param str 字符串
     * @return 是否为空白字符串
     */
    static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 是否不为空
     *
     * @param str 字符串
     * @return 是否不为空
     */
    static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 是否不为空
     *
     * @param str 字符串
     * @return 是否不为空
     */
    static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 数组中是否包含空字符串
     *
     * @param strs 字符串数组
     * @return 是否包含空字符串
     */
    static boolean isAnyBlank(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数组中是否全部为空字符串
     *
     * @param strs 字符串数组
     * @return 是否全部为空字符串
     */
    static boolean isNoneBlank(String... strs) {
        return !isAnyBlank(strs);
    }

    /**
     * 数组中是否包含空字符串
     *
     * @param strs 字符串数组
     * @return 是否包含空字符串
     */
    static boolean isAnyEmpty(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数组转字符串
     *
     * @param elements 数组
     * @param <T>      泛型
     * @return 字符串
     */
    static <T> String join(T... elements) {
        return join(elements, null);
    }

    /**
     * 数组转字符串
     *
     * @param elements  数组
     * @param separator 分隔符
     * @param <T>       泛型
     * @return 字符串
     */
    static <T> String join(T[] elements, String separator) {
        if (elements == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        int len = elements.length;
        if (len == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(len * 16);
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(elements[i]);
        }
        return sb.toString();
    }

    /**
     * 转为数组
     *
     * @param list 集合
     * @param <T>  泛型
     * @return 字符串数组
     */
    static <T> String[] toArray(T list) {
        if (list == null) {
            return new String[0];
        }
        if (list instanceof String[]) {
            return (String[]) list;
        }
        if (list instanceof String) {
            return new String[]{(String) list};
        }
        if (list instanceof Collection) {
            Collection<?> collection = (Collection<?>) list;
            return collection.toArray(new String[0]);
        }
        if (list instanceof Object[]) {
            Object[] objects = (Object[]) list;
            return Arrays.stream(objects).map(String::valueOf).toArray(String[]::new);
        }
        return new String[]{String.valueOf(list)};
    }

    /**
     * 字符串转为数组
     *
     * @param str 字符串
     * @return 数组
     */
    static double[] toDoubleArray(String str) {
        String[] array = str.split(",");
        double[] doubles = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubles[i] = Double.parseDouble(array[i]);
        }
        return doubles;
    }

    /**
     * 字符串转为数组
     *
     * @param str 字符串
     * @return 数组
     */
    static float[] toFloatArray(String str) {
        String[] array = str.split(",");
        float[] floats = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            floats[i] = Float.parseFloat(array[i]);
        }
        return floats;
    }

    /**
     * 字符串转为数组
     *
     * @param str 字符串
     * @return 数组
     */
    static int[] toIntArray(String str) {
        String[] array = str.split(",");
        int[] ints = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            ints[i] = Integer.parseInt(array[i]);
        }
        return ints;
    }

    /**
     * 字符串转为数组
     *
     * @param str 字符串
     * @return 数组
     */
    static long[] toLongArray(String str) {
        String[] array = str.split(",");
        long[] longs = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            longs[i] = Long.parseLong(array[i]);
        }
        return longs;
    }

    /**
     * 字符串转为数组
     *
     * @param str 字符串
     * @return 数组
     */
    static short[] toShortArray(String str) {
        String[] array = str.split(",");
        short[] shorts = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            shorts[i] = Short.parseShort(array[i]);
        }
        return shorts;
    }

    /**
     * 数组中是否全部为空字符串
     *
     * @param strs 字符串数组
     * @return 是否全部为空字符串
     */
    static boolean isNoneEmpty(String... strs) {
        return !isAnyEmpty(strs);
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 待处理字符串
     * @return 处理后字符串
     */
    static String replaceBlank(String str) {
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
    static String stripHtml(String content, boolean style, boolean script) {
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
    static String stripHtml(String content) {
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
    static String stripScript(String content) {
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
    static String stripStyle(String content) {
        Pattern pStyle = Pattern.compile(RegexpEnum.REGEX_STYLE_LABEL.regex(), Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(content);
        String all = mStyle.replaceAll("");
        return processingLabel(all);
    }

    static String processingLabel(String content) {
        return content.replaceAll("&nbsp;", "\t");
    }

    /**
     * 判断字符串是否以指定字符串开头
     *
     * @param str    字符串
     * @param prefix 指定字符串
     * @return 是否以指定字符串开头
     */
    static boolean startsWith(String str, String prefix) {
        return str != null && str.startsWith(prefix);
    }

    /**
     * 判断字符串是否以指定字符串结尾
     *
     * @param str    字符串
     * @param suffix 指定字符串
     * @return 是否以指定字符串结尾
     */
    static boolean endsWith(String str, String suffix) {
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
    static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
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
    static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal, boolean lastIndex) {
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
    static String[] split(String str, char separatorChar) {
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
    static String[] splitWorker(String str, char separatorChar, boolean b) {
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
    static String[] split(String str, String separatorChars) {
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
    static String[] split(String str, String separatorChars, int max) {
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
    static String[] splitWorker(String str, String separatorChars, int max, boolean b) {
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

    /**
     * 去除字符串两端的空白字符
     *
     * @param value 要处理的字符串
     * @return 去除两端空白字符后的字符串
     */
    static String trim(String value) {
        if (Strings.isBlank(value)) {
            return value;
        }
        return value.trim();
    }
}
