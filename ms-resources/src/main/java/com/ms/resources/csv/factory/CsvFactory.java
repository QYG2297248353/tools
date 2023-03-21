package com.ms.resources.csv.factory;

import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.csv.handler.CsvLineHandler;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取为指定Bean
     *
     * @param file  文件
     * @param clazz 类型
     * @param <T>   类型
     * @return Bean列表
     */
    public static <T> List<T> readCsvAll(File file, Class<T> clazz) throws MsToolsException {
        try {
            return new CsvToBeanBuilder(new FileReader(file)).withType(clazz).build().parse();
        } catch (FileNotFoundException e) {
            throw new MsToolsException(e);
        }
    }
}
