package com.ms.resources.csv.factory;

import com.ms.core.exception.base.MsToolsException;
import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.resources.csv.handler.CsvLineHandler;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author ms2297248353
 */
public class CsvFactory {

    /**
     * 读取首行
     *
     * @param file 文件
     * @return 字符串数组
     */
    public static String[] readCsvFirstLine(File file) throws MsToolsException {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file)).build()) {
            return reader.readNext();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        } catch (IOException | CsvValidationException e) {
            throw new MsToolsException(e);
        }
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
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file)).build()) {
            CSVIterator iterator = new CSVIterator(reader);
            while (iterator.hasNext()) {
                lineHandler.nextLine(iterator.next());
            }
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        } catch (IOException | CsvValidationException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取所有
     *
     * @param file 文件
     * @return 字符串数组列表
     */
    public static List<String[]> readCsvAll(File file) throws MsToolsException {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file)).build()) {
            return reader.readAll();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        } catch (IOException | CsvException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取所有为指定Bean
     *
     * @param file  文件
     * @param clazz 类型
     * @param <T>   类型
     * @return Bean列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    /**
     * 逐行读取为指定Bean
     *
     * @param file  文件
     * @param clazz 类型
     * @param <T>   类型
     * @return Bean列表
     */
    public static <T> void readCsvLine(File file, Class<T> clazz, CsvLineHandler lineHandler) {
        try {
            CsvToBean<T> csv = new CsvToBeanBuilder<T>(new FileReader(file)).withType(clazz).build();
            for (T t : csv) {
                lineHandler.nextLine(t);
            }
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).withSeparator(separator).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).withSeparator(separator).withQuoteChar(quoteChar).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).withSeparator(separator).withQuoteChar(quoteChar).withEscapeChar(escapeChar).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).withSeparator(separator).withQuoteChar(quoteChar).withEscapeChar(escapeChar).withStrictQuotes(strictQuotes).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes, boolean ignoreLeadingWhiteSpace) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).withSeparator(separator).withQuoteChar(quoteChar).withEscapeChar(escapeChar).withStrictQuotes(strictQuotes).withIgnoreLeadingWhiteSpace(ignoreLeadingWhiteSpace).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static <T> List<T> readCsvAll(File file, Class<T> clazz, int skipLines, char separator, char quoteChar, char escapeChar, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, boolean ignoreQuotations) {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).withSkipLines(skipLines).withSeparator(separator).withQuoteChar(quoteChar).withEscapeChar(escapeChar).withStrictQuotes(strictQuotes).withIgnoreLeadingWhiteSpace(ignoreLeadingWhiteSpace).withIgnoreQuotations(ignoreQuotations).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsRuntimeException(e);
        }
    }
}
