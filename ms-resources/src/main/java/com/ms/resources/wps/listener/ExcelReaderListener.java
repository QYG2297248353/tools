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

package com.ms.resources.wps.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.ms.core.base.basic.FormatUtils;

import java.util.Map;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public abstract class ExcelReaderListener extends AbstractReaderListener<Map<Integer, String>> {

    private static final Logger log = Logger.getLogger(ExcelReaderListener.class.getName());
    private boolean hasNext = true;
    private int maxLine = 0;

    /**
     * 读取失败
     *
     * @param exception 异常
     */
    protected void readFailed(Exception exception) {

    }

    /**
     * 读取头部
     *
     * @param headMap 头部
     */
    protected abstract void readHead(Map<Integer, String> headMap);

    /**
     * 读取内容
     *
     * @param data 内容
     */
    protected abstract void readData(Map<Integer, String> data);

    /**
     * 是否存在下一行
     *
     * @param next 是否存在
     */
    protected void next(boolean next) {
    }

    /**
     * 读取完成
     */
    protected abstract void finish();

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            String msg = FormatUtils.format("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
            log.warning(msg);
            readFailed(excelDataConvertException);
            return;
        }
        readFailed(exception);
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        readHead(headMap);
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
    }

    void close() {
        hasNext = false;
    }

    @Override
    public void readMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return hasNext;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        readData(data);
        if (maxLine > 0 && context.readRowHolder().getRowIndex() >= maxLine) {
            close();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        close();
        finish();
    }
}
