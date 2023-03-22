package com.ms.resources.wps.listener;

import com.alibaba.excel.metadata.data.ReadCellData;

import java.util.Map;

/**
 * @author ms2297248353
 */
public interface ExcelReaderListener {

    /**
     * 读取失败
     *
     * @param exception 异常
     */
    void readFailed(Exception exception);

    /**
     * 读取头部
     *
     * @param headMap 头部
     */
    void readHead(Map<Integer, ReadCellData<?>> headMap);

    /**
     * 读取内容
     *
     * @param data 内容
     */
    void readData(Object data);

    /**
     * 是否存在下一行
     *
     * @param next 是否存在
     */
    default void next(boolean next) {
    }

    /**
     * 读取完成
     */
    void finish();
}
