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
