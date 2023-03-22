package com.ms.resources.csv.handler;

public interface CsvLineHandler {
    /**
     * 下一行
     *
     * @param line 行数据
     * @param <T>  类型
     */
    <T> void nextLine(T line);
}
