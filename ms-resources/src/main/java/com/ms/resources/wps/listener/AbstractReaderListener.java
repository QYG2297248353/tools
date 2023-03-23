package com.ms.resources.wps.listener;

import com.alibaba.excel.event.AnalysisEventListener;

public abstract class AbstractReaderListener<T> extends AnalysisEventListener<T> {

    protected int maxLine = 0;

    public void readMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }

}
