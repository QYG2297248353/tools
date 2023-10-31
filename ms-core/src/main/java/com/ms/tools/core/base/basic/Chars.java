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

import java.io.File;

/**
 * 字符工具类
 *
 * @author ms
 */
public interface Chars {
    char EMPTY = '\0';
    char SPACE = ' ';
    char COMMA = ',';
    char DOT = '.';
    char COLON = ':';
    char SEMICOLON = ';';
    char UNDERLINE = '_';
    char DASHED = '-';
    char SLASH = '/';
    char BACKSLASH = '\\';
    char BRACKET_LEFT = '(';
    char BRACKET_RIGHT = ')';
    char BRACE_LEFT = '{';
    char BRACE_RIGHT = '}';
    char BRACKET_LEFT_SQUARE = '[';
    char BRACKET_RIGHT_SQUARE = ']';
    char EQUALS = '=';
    char AND = '&';
    char AT = '@';
    char STAR = '*';
    char PERCENT = '%';
    char PLUS = '+';
    char DOLLAR = '$';
    char HASH = '#';
    char EXCLAMATION_MARK = '!';
    char QUESTION_MARK = '?';
    char CARET = '^';
    char TILDE = '~';
    char SINGLE_QUOTE = '\'';
    char DOUBLE_QUOTE = '"';
    char BACKTICK = '`';
    char VERTICAL_LINE = '|';

    /**
     * 是否为数字
     *
     * @param c 字符
     * @return 是否为数字
     */
    static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 是否为字母
     */
    static boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    /**
     * 是否为大写字母
     */
    static boolean isUpperCaseLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * 是否为小写字母
     */
    static boolean isLowerCaseLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    /**
     * 是否为16进制规范的字符
     */
    static boolean isHexChar(char c) {
        return isNumber(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    /**
     * 是否为字符或数字
     *
     * @param c 字符
     * @return 是否为字符或数字
     */
    static boolean isLetterOrNumber(char c) {
        return isLetter(c) || isNumber(c);
    }

    /**
     * 是否为字符或数字或下划线
     *
     * @param c 字符
     * @return 是否为字符或数字或下划线
     */
    static boolean isLetterOrNumberOrUnderline(char c) {
        return isLetterOrNumber(c) || c == UNDERLINE;
    }

    /**
     * 是否为空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     * see: https://unicode-table.com/cn/#control-character
     *
     * @param c 字符
     * @return 是否为空白符
     */
    static boolean isBlankChar(char c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a';
    }

    /**
     * 是否为emoji表情符<br>
     * see: https://unicode-table.com/cn/blocks/miscellaneous-symbols-and-pictographs/
     *
     * @param c 字符
     *          @return 是否为emoji表情符
     */
    static boolean isEmoji(char c) {
        return (c == 0x0 || c == 0x9 || c == 0xA || c == 0xD
                || (c >= 0x20 && c <= 0xD7FF)
                || (c >= 0xE000 && c <= 0xFFFD)
                || (c >= 0x10000 && c <= 0x10FFFF));
    }

    /**
     * 是否为Windows或者Linux（Unix）文件分隔符<br>
     * Windows平台下分隔符为\，Linux（Unix）为/
     *
     * @param c 字符
     * @return 是否为Windows或者Linux（Unix）文件分隔符
     */
    static boolean isFileSeparator(char c) {
        return SLASH == c || BACKSLASH == c;
    }

    /**
     * 给定类名是否为字符类，字符类包括：
     * <pre>
     * Character.class
     * char.class
     * </pre>
     *
     * @param clazz 被检查的类
     * @return 是否为字符类
     */
    static boolean isCharClass(Class<?> clazz) {
        return clazz == Character.class || clazz == char.class;
    }

    /**
     * 给定对象对应的类是否为字符类，字符类包括：
     * <pre>
     * Character.class
     * char.class
     * </pre>
     *
     * @param value 被检查的对象
     * @return 是否为字符类
     */
    static boolean isChar(Object value) {
        return value instanceof Character;
    }

    /**
     * 获取当前系统文件分隔符
     *
     * @return 文件分隔符
     */
    static char fileSeparator() {
        return File.separatorChar;
    }

    /**
     * 获取当前系统换行符
     *
     * @return 换行符
     */
    static String lineSeparator() {
        return System.lineSeparator();
    }
}
