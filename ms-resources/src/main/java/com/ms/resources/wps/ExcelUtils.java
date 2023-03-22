package com.ms.resources.wps;

import com.ms.resources.wps.factory.EasyExcelFactory;
import com.ms.resources.wps.listener.ExcelReaderBatchListener;
import com.ms.resources.wps.listener.ExcelReaderListener;

import java.io.File;
import java.util.Map;

/**
 * @author ms2297248353
 */
public class ExcelUtils {
    /**
     * 读取Excel 表头
     *
     * @param file 文件路径
     * @return 表头
     */
    public static Map<Integer, String> readExcelHead(File file) {
        return EasyExcelFactory.readHead(file, null);
    }


    /**
     * 读取Excel 表头
     *
     * @param file     文件路径
     * @param headLine 表头行数
     * @return 表头
     */
    public static Map<Integer, String> readExcelHead(File file, int headLine) {
        return EasyExcelFactory.readHead(file, headLine);
    }

    /**
     * 逐行读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     */
    public static void readExcel(File file, ExcelReaderListener listener) {
        EasyExcelFactory.read(file, null, null, "", listener, null);
    }

    /**
     * 逐行读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     * @param password 密码
     */
    public static void readExcelPass(File file, String password, ExcelReaderListener listener) {
        EasyExcelFactory.read(file, null, null, "", listener, password);
    }

    /**
     * 逐行读取Excel
     *
     * @param file     文件路径
     * @param headLine 读取行数
     * @param listener 处理器
     */
    public static void readExcelByHead(File file, int headLine, ExcelReaderListener listener) {
        EasyExcelFactory.read(file, null, headLine, "", listener, null);
    }

    /**
     * 逐行读取前多少行Excel
     *
     * @param file     文件路径
     * @param line     读取行数
     * @param listener 处理器
     */
    public static void readExcel(File file, int line, ExcelReaderListener listener) {
        EasyExcelFactory.read(file, line, null, "", listener, null);
    }

    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     */
    public static void readExcelBatch(File file, ExcelReaderBatchListener listener) {
        EasyExcelFactory.readBatch(file, null, "", listener, null);
    }

    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     * @param password 密码
     */
    public static void readExcelBatch(File file, String password, ExcelReaderBatchListener listener) {
        EasyExcelFactory.readBatch(file, null, "", listener, password);
    }


    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param head     表头占用行数量
     * @param listener 处理器
     */
    public static void readExcelBatchByHead(File file, int head, ExcelReaderBatchListener listener) {
        EasyExcelFactory.readBatch(file, head, "", listener, null);
    }

    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param head     表头占用行数量
     * @param listener 处理器
     * @param password 密码
     */
    public static void readExcelBatchByHeadPass(File file, int head, String password, ExcelReaderBatchListener listener) {
        EasyExcelFactory.readBatch(file, head, "", listener, password);
    }
}
