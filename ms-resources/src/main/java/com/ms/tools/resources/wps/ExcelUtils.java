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

package com.ms.tools.resources.wps;

import com.ms.tools.resources.wps.factory.EasyReadExcelFactory;
import com.ms.tools.resources.wps.factory.EasyWriteExcelFactory;
import com.ms.tools.resources.wps.listener.AbstractReaderBatchListener;
import com.ms.tools.resources.wps.listener.AbstractReaderListener;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author ms2297248353
 */
public class ExcelUtils {
    /**
     * 读取Excel 表头
     *
     * @param file 文件路径
     * @return 表头
     */
    public static Map<Integer, String> readExcelHead(File file) {
        return EasyReadExcelFactory.readHead(file, null);
    }


    /**
     * 读取Excel 表头
     *
     * @param file     文件路径
     * @param headLine 表头行数
     * @return 表头
     */
    public static Map<Integer, String> readExcelHead(File file, int headLine) {
        return EasyReadExcelFactory.readHead(file, headLine);
    }

    /**
     * 逐行读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     */
    public static <T extends AbstractReaderListener> void readExcel(File file, T listener) {
        EasyReadExcelFactory.read(file, null, null, "", listener, null);
    }

    /**
     * 逐行读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     * @param password 密码
     */
    public static <T extends AbstractReaderListener> void readExcelPass(File file, String password, T listener) {
        EasyReadExcelFactory.read(file, null, null, "", listener, password);
    }

    /**
     * 逐行读取Excel
     *
     * @param file     文件路径
     * @param headLine 读取行数
     * @param listener 处理器
     */
    public static <T extends AbstractReaderListener> void readExcelByHead(File file, int headLine, T listener) {
        EasyReadExcelFactory.read(file, null, headLine, "", listener, null);
    }

    /**
     * 逐行读取前多少行Excel
     *
     * @param file     文件路径
     * @param line     读取行数
     * @param listener 处理器
     */
    public static <T extends AbstractReaderListener> void readExcel(File file, int line, T listener) {
        EasyReadExcelFactory.read(file, line, null, "", listener, null);
    }

    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     */
    public static <T extends AbstractReaderBatchListener> void readExcelBatch(File file, T listener) {
        EasyReadExcelFactory.readBatch(file, null, "", listener, null);
    }

    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param listener 处理器
     * @param password 密码
     */
    public static <T extends AbstractReaderBatchListener> void readExcelBatch(File file, String password, T listener) {
        EasyReadExcelFactory.readBatch(file, null, "", listener, password);
    }


    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param head     表头占用行数量
     * @param listener 处理器
     */
    public static <T extends AbstractReaderBatchListener> void readExcelBatchByHead(File file, int head, T listener) {
        EasyReadExcelFactory.readBatch(file, head, "", listener, null);
    }

    /**
     * 批量读取Excel
     *
     * @param file     文件路径
     * @param head     表头占用行数量
     * @param listener 处理器
     * @param password 密码
     */
    public static <T extends AbstractReaderBatchListener> void readExcelBatchByHeadPass(File file, int head, String password, T listener) {
        EasyReadExcelFactory.readBatch(file, head, "", listener, password);
    }

    /**
     * 写入数据
     *
     * @param file 待写入文件路径
     * @param data 待写入数据
     */
    public static void writeExcel(File file, Collection<?> data) {
        EasyWriteExcelFactory.write(file, null, null, null, null, "", null, data);
    }

    /**
     * 写入数据
     *
     * @param file    待写入文件路径
     * @param useHead 是否使用表头
     * @param head    表头
     * @param data    待写入数据
     */
    public static void writeExcel(File file, Boolean useHead, Class head, Collection<?> data) {
        EasyWriteExcelFactory.write(file, useHead, head, null, null, "", null, data);
    }

    /**
     * 写入数据
     *
     * @param file     待写入文件路径
     * @param data     待写入数据
     * @param password 密码
     */
    public static void writeExcelPass(File file, String password, Collection<?> data) {
        EasyWriteExcelFactory.write(file, null, null, null, null, "", password, data);
    }

    /**
     * 写入数据
     *
     * @param file 待写入文件路径
     * @param data 待写入数据
     */
    public static void writeExcel(File file, Supplier<Collection<?>> data) {
        EasyWriteExcelFactory.write(file, null, null, null, null, "", null, data);
    }

    /**
     * 写入数据
     *
     * @param file     待写入文件路径
     * @param data     待写入数据
     * @param password 密码
     */
    public static void writeExcelPass(File file, String password, Supplier<Collection<?>> data) {
        EasyWriteExcelFactory.write(file, null, null, null, null, "", password, data);
    }


}
