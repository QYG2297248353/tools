package com.ms.resources.wps.factory;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.ms.core.base.basic.Strings;
import com.ms.resources.wps.listener.ExcelReaderBatchListener;
import com.ms.resources.wps.listener.ExcelReaderListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ms2297248353
 */
public class EasyExcelFactory {

    private static <T extends ReadListener> void validationParameters(File file, T listener, Integer lineNum, Integer headLine) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("file is null or not exists");
        }
        if (listener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (lineNum != null && lineNum < 0) {
            throw new IllegalArgumentException("lineNum must be greater than or equal to 0");
        }
        if (headLine != null && headLine < 0) {
            throw new IllegalArgumentException("headLine must be greater than or equal to 0");
        }
    }

    /**
     * 批次读取Excel
     *
     * @param file      文件
     * @param headLine  头部行数 0为无头部 默认值为1
     * @param sheetName 读取sheet名称 默认值为首个sheet
     * @param listener  监听器
     * @param password  密码
     */
    public static void readBatch(File file, Integer headLine, String sheetName, ExcelReaderBatchListener listener, String password) {
        validationParameters(file, listener, listener.getBatchSize(), headLine);
        headLine = headLine == null ? 1 : headLine;
        build(headLine, sheetName, password, EasyExcel.read(file, listener), file, listener);
    }

    /**
     * 逐行读取Excel
     *
     * @param file      文件
     * @param lineNum   读取行数(何时停止读取) 0为全部 默认值为0
     * @param headLine  头部行数 0为无头部 默认值为1
     * @param sheetName 读取sheet名称 默认值为首个sheet
     * @param listener  监听器
     * @param password  密码
     */
    public static void read(File file, Integer lineNum, Integer headLine, String sheetName, ExcelReaderListener listener, String password) {
        validationParameters(file, listener, lineNum, headLine);
        lineNum = lineNum == null ? 0 : lineNum;
        listener.readMaxLine(lineNum);
        headLine = headLine == null ? 1 : headLine;
        build(headLine, sheetName, password, EasyExcel.read(file, listener), file, listener);
    }

    private static <T extends ReadListener> void build(Integer headLine, String sheetName, String password, ExcelReaderBuilder read, File file, T listener) {
        sheetName = Strings.isBlank(sheetName) ? null : sheetName;

        if (headLine > 1) {
            read.headRowNumber(headLine);
        }
        if (Strings.isNotBlank(password)) {
            read.password(password);
        }
        ExcelReaderSheetBuilder sheet;
        if (sheetName != null) {
            sheet = read.sheet(sheetName);
        } else {
            sheet = read.sheet();
        }
        sheet.doRead();
    }


    /**
     * 批次读取Excel
     *
     * @param file     文件
     * @param headLine 头部行数 0为无头部 默认值为1
     * @param sheetNum 读取sheet序号 默认值为首个sheet
     * @param listener 监听器
     * @param password 密码
     */
    public static void readBatch(File file, Integer headLine, Integer sheetNum, ExcelReaderBatchListener listener, String password) {
        validationParameters(file, listener, listener.getBatchSize(), headLine);
        headLine = headLine == null ? 1 : headLine;
        buildPass(headLine, sheetNum, password, EasyExcel.read(file, listener));
    }


    /**
     * 逐行读取Excel
     *
     * @param file     文件
     * @param lineNum  读取行数 0为全部 默认值为0
     * @param headLine 头部行数 0为无头部 默认值为1
     * @param sheetNum 读取sheet序号 默认值为首个sheet
     * @param listener 监听器
     * @param password 密码
     */
    public static void read(File file, Integer lineNum, Integer headLine, Integer sheetNum, ExcelReaderListener listener, String password) {
        validationParameters(file, listener, lineNum, headLine);
        lineNum = lineNum == null ? 0 : lineNum;
        listener.readMaxLine(lineNum);
        headLine = headLine == null ? 1 : headLine;
        buildPass(headLine, sheetNum, password, EasyExcel.read(file, listener));
    }

    private static <T extends ReadListener> void buildPass(Integer headLine, Integer sheetNum, String password, ExcelReaderBuilder read) {
        if (headLine > 1) {
            read.headRowNumber(headLine);
        }
        if (Strings.isNotBlank(password)) {
            read.password(password);
        }
        ExcelReaderSheetBuilder sheet;
        if (sheetNum != null) {
            sheet = read.sheet(sheetNum);
        } else {
            sheet = read.sheet();
        }
        sheet.doRead();
    }

    /**
     * 读取Excel头部
     *
     * @param file     文件
     * @param headLine 头部行数 0为无头部 默认值为1
     * @return 头部信息
     */
    public static Map<Integer, String> readHead(File file, Integer headLine) {
        headLine = headLine == null ? 1 : headLine;
        Map<Integer, String> head = new HashMap<>();
        AnalysisEventListener listener = new AnalysisEventListener() {
            @Override
            public void invoke(Object data, AnalysisContext context) {

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }

            @Override
            public void invokeHeadMap(Map headMap, AnalysisContext context) {
                head.putAll(headMap);
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return false;
            }
        };
        ExcelReaderBuilder read = EasyExcel.read(file, listener);
        if (headLine > 1) {
            read.headRowNumber(headLine);
        }
        read.sheet().doRead();
        return head;
    }
}
