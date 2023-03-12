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

package com.ms.resources.json;

import com.alibaba.fastjson2.JSONReader;
import com.ms.resources.core.FastJsonFactory;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author ms2297248353
 */
public class FastJsonUtils {
    /**
     * JSON文件流读取
     * 默认UTF-8
     *
     * @param file 文件
     * @return 文件流
     */
    public static JSONReader fileReader(File file) {
        return FastJsonFactory.fileReader(file);
    }

    /**
     * JSON文件流读取
     *
     * @param file    文件
     * @param charset 读取编码
     * @return 文件流
     */
    public static JSONReader fileReader(File file, Charset charset) {
        return FastJsonFactory.fileReader(file, charset);
    }

    /**
     * JSON文件流读取
     *
     * @param <T>    对象类型
     * @param reader 文件流
     * @param clazz  对象类型
     * @param count  指定数量
     * @return 对象列表
     */
    public static <T> List<T> readJsonObject(JSONReader reader, Class<T> clazz, int count) {
        return FastJsonFactory.readJsonObject(reader, clazz, count);
    }
}
