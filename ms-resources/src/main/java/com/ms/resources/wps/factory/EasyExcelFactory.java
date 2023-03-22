package com.ms.resources.wps.factory;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.ms.core.base.basic.Strings;
import com.ms.resources.wps.listener.ExcelReaderListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ms2297248353
 */
public class EasyExcelFactory {

    private static void validationParameters(File file, ExcelReaderListener listener, Integer lineNum, Integer headLine) {
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
     * @param lineNum   每次读取行数 0为每次每条 默认值为0
     * @param headLine  头部行数 0为无头部 默认值为1
     * @param sheetName 读取sheet名称 默认值为首个sheet
     * @param listener  监听器
     */
    public static void readBatch(File file, Integer lineNum, Integer headLine, String sheetName, ExcelReaderListener listener, String password) {
        validationParameters(file, listener, lineNum, headLine);
        lineNum = lineNum == null ? 0 : lineNum;
        headLine = headLine == null ? 1 : headLine;
        sheetName = Strings.isBlank(sheetName) ? null : sheetName;
        Integer finalLineNum = lineNum;
        ReadListener readListener = new ReadListener() {
            final int maxReader = finalLineNum;
            boolean hasNext = true;

            List<Object> list = new ArrayList<>();

            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                listener.readFailed(exception);
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {
                listener.readHead(headMap);
            }

            @Override
            public void invoke(Object data, AnalysisContext context) {
                if (maxReader == 0) {
                    listener.readData(data);
                } else {
                    if (list.size() >= maxReader) {
                        listener.readData(list);
                        list.clear();
                    } else {
                        list.add(data);
                    }
                }
                listener.next(hasNext);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                hasNext = false;
                if (maxReader != 0 && !list.isEmpty()) {
                    listener.readData(list);
                    list.clear();
                }
                listener.finish();
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return hasNext;
            }
        };
        ExcelReaderBuilder read = EasyExcel.read(file, readListener);
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
     * 逐行读取Excel
     *
     * @param file      文件
     * @param lineNum   读取行数 0为全部 默认值为0
     * @param headLine  头部行数 0为无头部 默认值为1
     * @param sheetName 读取sheet名称 默认值为首个sheet
     * @param listener  监听器
     */
    public static void read(File file, Integer lineNum, Integer headLine, String sheetName, ExcelReaderListener listener, String password) {
        validationParameters(file, listener, lineNum, headLine);
        lineNum = lineNum == null ? 0 : lineNum;
        headLine = headLine == null ? 1 : headLine;
        sheetName = Strings.isBlank(sheetName) ? null : sheetName;
        Integer finalLineNum = lineNum;
        ReadListener readListener = new ReadListener() {
            final int maxReader = finalLineNum;
            boolean hasNext = true;

            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                listener.readFailed(exception);
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {
                listener.readHead(headMap);
            }

            @Override
            public void invoke(Object data, AnalysisContext context) {
                listener.readData(data);
                if (maxReader != 0 && context.readRowHolder().getRowIndex() >= maxReader) {
                    hasNext = false;
                }
                listener.next(hasNext);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                listener.finish();
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return hasNext;
            }
        };
        ExcelReaderBuilder read = EasyExcel.read(file, readListener);
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
     * @param lineNum  每次读取行数 0为每次每条 默认值为0
     * @param headLine 头部行数 0为无头部 默认值为1
     * @param sheetNum 读取sheet序号 默认值为首个sheet
     * @param listener 监听器
     */
    public static void readBatch(File file, Integer lineNum, Integer headLine, Integer sheetNum, ExcelReaderListener listener, String password) {
        validationParameters(file, listener, lineNum, headLine);
        lineNum = lineNum == null ? 0 : lineNum;
        headLine = headLine == null ? 1 : headLine;
        Integer finalLineNum = lineNum;
        ReadListener readListener = new ReadListener() {
            final int maxReader = finalLineNum;
            boolean hasNext = true;

            List<Object> list = new ArrayList<>();

            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                listener.readFailed(exception);
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {
                listener.readHead(headMap);
            }

            @Override
            public void invoke(Object data, AnalysisContext context) {
                if (maxReader == 0) {
                    listener.readData(data);
                } else {
                    if (list.size() >= maxReader) {
                        listener.readData(list);
                        list.clear();
                    } else {
                        list.add(data);
                    }
                }
                listener.next(hasNext);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                hasNext = false;
                if (maxReader != 0 && !list.isEmpty()) {
                    listener.readData(list);
                    list.clear();
                }
                listener.finish();
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return hasNext;
            }
        };
        ExcelReaderBuilder read = EasyExcel.read(file, readListener);
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
     * 逐行读取Excel
     *
     * @param file     文件
     * @param lineNum  读取行数 0为全部 默认值为0
     * @param headLine 头部行数 0为无头部 默认值为1
     * @param sheetNum 读取sheet序号 默认值为首个sheet
     * @param listener 监听器
     */
    public static void read(File file, Integer lineNum, Integer headLine, Integer sheetNum, ExcelReaderListener listener, String password) {
        validationParameters(file, listener, lineNum, headLine);
        lineNum = lineNum == null ? 0 : lineNum;
        headLine = headLine == null ? 1 : headLine;
        Integer finalLineNum = lineNum;
        ReadListener readListener = new ReadListener() {
            final int maxReader = finalLineNum;
            boolean hasNext = true;

            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                listener.readFailed(exception);
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {
                listener.readHead(headMap);
            }

            @Override
            public void invoke(Object data, AnalysisContext context) {
                listener.readData(data);
                if (maxReader != 0 && context.readRowHolder().getRowIndex() >= maxReader) {
                    hasNext = false;
                }
                listener.next(hasNext);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                listener.finish();
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return hasNext;
            }
        };
        ExcelReaderBuilder read = EasyExcel.read(file, readListener);
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

}
