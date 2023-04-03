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

import java.util.Arrays;
import java.util.Collection;

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

    static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

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

    static boolean isNoneBlank(String... strs) {
        return !isAnyBlank(strs);
    }

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

    static boolean isNoneEmpty(String... strs) {
        return !isAnyEmpty(strs);
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
     * 字符串分割
     *
     * @param str       字符串
     * @param separator 分隔符
     * @param <T>       泛型
     * @return 字符串数组
     */
    static <T> String[] split(String str, String separator) {
        if (str == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        return str.split(separator);
    }

    /**
     * 字符串分割
     *
     * @param str       字符串
     * @param separator 分隔符
     * @param <T>       泛型
     * @return 字符串数组
     */
    static <T> String[] split(String str, char separator) {
        if (str == null) {
            return null;
        }
        return str.split(String.valueOf(separator));
    }

    /**
     * 字符串分割
     *
     * @param str       字符串
     * @param separator 分隔符
     * @param limit     限制
     * @param <T>       泛型
     * @return 字符串数组
     */
    static <T> String[] split(String str, String separator, int limit) {
        if (str == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        return str.split(separator, limit);
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
            return null;
        }
        if (list instanceof String[]) {
            return (String[]) list;
        }
        if (list instanceof String) {
            return new String[]{(String) list};
        }
        if (list instanceof Collection) {
            Collection<?> collection = (Collection<?>) list;
            return collection.toArray(new String[collection.size()]);
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
}
