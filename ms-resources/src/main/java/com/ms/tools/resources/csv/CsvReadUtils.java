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

package com.ms.tools.resources.csv;

import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.resources.csv.factory.CsvFactory;
import com.ms.tools.resources.csv.handler.CsvLineHandler;

import java.io.File;
import java.util.List;

/**
 * @author ms2297248353
 */
public class CsvReadUtils {
    /**
     * 读取首行
     *
     * @param file 文件
     * @return 字符串数组
     * @throws MsToolsException 异常
     */
    public static String[] readCsvFirstLine(File file) throws MsToolsException {
        return CsvFactory.readCsvFirstLine(file);
    }

    /**
     * 逐行读取
     * <p>
     * 迭代器方式节约内存
     *
     * @param file        文件
     * @param lineHandler 行处理器
     * @throws MsToolsException 异常
     */
    public static void readCsvLine(File file, CsvLineHandler lineHandler) throws MsToolsException {
        CsvFactory.readCsvLine(file, lineHandler);
    }

    /**
     * 逐行读取
     * <p>
     * 迭代器方式节约内存
     *
     * @param file        文件
     * @param clazz       类型
     * @param lineHandler 行处理器
     * @param <T>         类型
     */
    public static <T> void readCsvLine(File file, Class<T> clazz, CsvLineHandler lineHandler) {
        CsvFactory.readCsvLine(file, clazz, lineHandler);
    }

    /**
     * 读取所有
     *
     * @param file 文件
     * @return 字符串数组列表
     * @throws MsToolsException 异常
     */
    public static List<String[]> readCsvAll(File file) throws MsToolsException {
        return CsvFactory.readCsvAll(file);
    }

    /**
     * 读取所有
     *
     * @param file  文件
     * @param clazz 类型
     * @param <T>   类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz) {
        return CsvFactory.readCsvAll(file, clazz);
    }

    /**
     * 读取所有
     *
     * @param file      文件
     * @param clazz     类型
     * @param skipLines 跳过行数
     * @param <T>       类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines) {
        return CsvFactory.readCsvAll(file, clazz, skipLines);
    }

    /**
     * 读取所有
     *
     * @param file      文件
     * @param clazz     类型
     * @param skipLines 跳过行数
     * @param separator 分隔符
     * @param <T>       类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator);
    }

    /**
     * 读取所有
     *
     * @param file      文件
     * @param clazz     类型
     * @param skipLines 跳过行数
     * @param separator 分隔符
     * @param quoteChar 引号
     * @param <T>       类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator, quoteChar);
    }

    /**
     * 读取所有
     *
     * @param file       文件
     * @param clazz      类型
     * @param skipLines  跳过行数
     * @param separator  分隔符
     * @param quoteChar  引号
     * @param escapeChar 转义符
     * @param <T>        类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator, quoteChar, escapeChar);
    }

    /**
     * 读取所有
     *
     * @param file         文件
     * @param clazz        类型
     * @param skipLines    跳过行数
     * @param separator    分隔符
     * @param quoteChar    引号
     * @param escapeChar   转义符
     * @param strictQuotes 严格引号
     * @param <T>          类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator, quoteChar, escapeChar, strictQuotes);
    }

    /**
     * 读取所有
     *
     * @param file                    文件
     * @param clazz                   类型
     * @param skipLines               跳过行数
     * @param separator               分隔符
     * @param quoteChar               引号
     * @param escapeChar              转义符
     * @param strictQuotes            严格引号
     * @param ignoreLeadingWhiteSpace 忽略前导空白
     * @param <T>                     类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes, boolean ignoreLeadingWhiteSpace) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator, quoteChar, escapeChar, strictQuotes, ignoreLeadingWhiteSpace);
    }

    /**
     * 读取所有
     *
     * @param file                    文件
     * @param clazz                   类型
     * @param skipLines               跳过行数
     * @param separator               分隔符
     * @param quoteChar               引号
     * @param escapeChar              转义符
     * @param strictQuotes            严格引号
     * @param ignoreLeadingWhiteSpace 忽略前导空白
     * @param ignoreQuotations        忽略引号
     * @param <T>                     类型
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, boolean ignoreQuotations) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator, quoteChar, escapeChar, strictQuotes, ignoreLeadingWhiteSpace, ignoreQuotations);
    }

}
