package com.ms.resources.wps.listener;

import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @author ms2297248353
 */
public abstract class AbstractReaderBatchListener<T> extends AnalysisEventListener<T> {

    /**
     * 每次读取的数据量
     * must be greater than 0
     *
     * @return 数据量 0 全部
     */
    public abstract int getBatchSize();

}
