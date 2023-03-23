package com.ms.resources.wps.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.ms.core.base.basic.FormatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public abstract class ExcelReaderBatchListener extends AbstractReaderBatchListener<Map<Integer, String>> {

    private static final Logger log = Logger.getLogger(ExcelReaderBatchListener.class.getName());
    private final List<Map<Integer, String>> dataList = new ArrayList<>();
    private boolean hasNext = true;

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
    protected abstract void readData(List<Map<Integer, String>> data);

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
    public boolean hasNext(AnalysisContext context) {
        return hasNext;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        dataList.add(data);
        if (dataList.size() >= getBatchSize()) {
            readData(dataList);
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!dataList.isEmpty()) {
            readData(dataList);
            dataList.clear();
        }
        close();
        finish();
    }

}
