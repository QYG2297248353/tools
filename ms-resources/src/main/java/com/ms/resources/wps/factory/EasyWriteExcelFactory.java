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

package com.ms.resources.wps.factory;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsRuntimeException;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author ms2297248353
 */
public class EasyWriteExcelFactory {

    private static ExcelWriterBuilder buildWrite(File file, OutputStream stream, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, String password) {
        ExcelWriterBuilder write = buildWriter(file, stream, writeHead, head);
        if (Strings.isNotBlank(password)) {
            write.password(password);
        }
        if (excelType != null) {
            write.excelType(excelType);
            if (excelType == ExcelTypeEnum.CSV && charset != null) {
                write.charset(charset);
            }
        }
        return write;
    }

    private static ExcelWriterBuilder buildWriter(File file, OutputStream stream, Boolean writeHead, Class head) {
        ExcelWriterBuilder write = null;
        if (file == null) {
            if (head == null) {
                write = EasyExcel.write(stream);
            } else {
                write = EasyExcel.write(stream, head);
            }
        }
        if (stream == null) {
            if (head == null) {
                write = EasyExcel.write(file);
            } else {
                write = EasyExcel.write(file, head);
            }
        }
        if (write == null) {
            throw new MsToolsRuntimeException("写入文件不存在");
        }
        if (writeHead != null) {
            write.needHead(writeHead);
        }
        return write;
    }

    public static void write(File file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, Integer sheetNo, String password, Collection<?> data) {
        ExcelWriterBuilder write = buildWrite(file, null, writeHead, head, excelType, charset, password);
        ExcelWriterSheetBuilder sheet;
        if (sheetNo != null) {
            sheet = write.sheet(sheetNo);
        } else {
            sheet = write.sheet();
        }
        sheet.doWrite(data);
    }

    public static void write(File file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, String sheetName, String password, Collection<?> data) {
        ExcelWriterBuilder write = buildWrite(file, null, writeHead, head, excelType, charset, password);
        ExcelWriterSheetBuilder sheet;
        if (Strings.isNotBlank(sheetName)) {
            sheet = write.sheet(sheetName);
        } else {
            sheet = write.sheet();
        }
        sheet.doWrite(data);
    }


    public static void write(OutputStream file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, Integer sheetNo, String password, Collection<?> data) {
        ExcelWriterBuilder write = buildWrite(null, file, writeHead, head, excelType, charset, password);
        ExcelWriterSheetBuilder sheet;
        if (sheetNo != null) {
            sheet = write.sheet(sheetNo);
        } else {
            sheet = write.sheet();
        }
        sheet.doWrite(data);
    }

    public static void write(OutputStream file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, String sheetName, String password, Collection<?> data) {
        ExcelWriterBuilder write = buildWrite(null, file, writeHead, head, excelType, charset, password);
        ExcelWriterSheetBuilder sheet;
        if (Strings.isNotBlank(sheetName)) {
            sheet = write.sheet(sheetName);
        } else {
            sheet = write.sheet();
        }
        sheet.doWrite(data);
    }

    public static void write(File file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, String sheetName, String password, Supplier<Collection<?>> data) {
        write(file, writeHead, head, excelType, charset, sheetName, password, data.get());
    }

    public static void write(OutputStream file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, String sheetName, String password, Supplier<Collection<?>> data) {
        write(file, writeHead, head, excelType, charset, sheetName, password, data.get());
    }

    public static void write(OutputStream file, Boolean writeHead, Class head, ExcelTypeEnum excelType, Charset charset, Integer sheetNo, String password, Supplier<Collection<?>> data) {
        write(file, writeHead, head, excelType, charset, sheetNo, password, data.get());
    }
}
