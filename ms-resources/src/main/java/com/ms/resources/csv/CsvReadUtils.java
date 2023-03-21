package com.ms.resources.csv;

import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.csv.factory.CsvFactory;
import com.ms.resources.csv.handler.CsvLineHandler;

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
     */
    public static List<String[]> readCsvAll(File file) throws MsToolsException {
        return CsvFactory.readCsvAll(file);
    }

    /**
     * 读取所有
     *
     * @param file  文件
     * @param clazz 类型
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
     * @return 对象列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, boolean ignoreQuotations) {
        return CsvFactory.readCsvAll(file, clazz, skipLines, separator, quoteChar, escapeChar, strictQuotes, ignoreLeadingWhiteSpace, ignoreQuotations);
    }

}
