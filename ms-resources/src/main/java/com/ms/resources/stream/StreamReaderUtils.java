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

package com.ms.resources.stream;

import com.ms.core.exception.base.MsToolsException;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * 常见流工具类
 *
 * @author qyg2297248353
 */
public class StreamReaderUtils {

    public static FileReader reader(String file) throws MsToolsException {
        try {
            return new FileReader(file);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 读取文件
     *
     * @param file 文件
     * @return InputStreamReader
     * @throws MsToolsException MsToolsException
     */
    public static InputStreamReader reader(File file) throws MsToolsException {
        try {
            return new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取文件
     *
     * @param file    文件
     * @param charset 字符集
     * @return InputStreamReader
     * @throws MsToolsException MsToolsException
     */
    public static InputStreamReader reader(File file, String charset) throws MsToolsException {
        try {
            return new InputStreamReader(Files.newInputStream(file.toPath()), charset);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 读取文件
     *
     * @param file    文件
     * @param charset 字符集
     * @return InputStreamReader
     * @throws MsToolsException MsToolsException
     */
    public static InputStreamReader reader(File file, Charset charset) throws MsToolsException {
        try {
            return new InputStreamReader(Files.newInputStream(file.toPath()), charset);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }
}
