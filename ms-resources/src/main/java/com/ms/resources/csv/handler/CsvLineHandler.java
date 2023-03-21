package com.ms.resources.csv.handler;

public interface CsvLineHandler {
    /**
     * 下一行
     *
     * @param line 行数据
     */
    void nextLine(String[] line);
}
