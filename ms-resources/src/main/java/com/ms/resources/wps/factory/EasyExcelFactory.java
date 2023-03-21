package com.ms.resources.wps.factory;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.ms.resources.wps.listener.ExcelReaderListener;

import java.io.File;
import java.util.Map;

/**
 * @author ms2297248353
 */
public class EasyExcelFactory {


    /**
     * 逐行读取Excel
     *
     * @param file     文件
     * @param lineNum  读取行数 0为全部
     * @param listener 监听器
     */
    public static void readBySax(File file, Integer lineNum, ExcelReaderListener listener) {
        ReadListener readListener = new ReadListener() {
            int maxReader = lineNum == null ? 0 : lineNum;
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
        EasyExcel.read(file, readListener).sheet().doRead();
    }

}
