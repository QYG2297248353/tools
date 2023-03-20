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

package com.ms.resources.core;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONReader;
import com.ms.core.exception.base.MsToolsRuntimeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FastJsonFactory {

    /**
     * JSON文件读取
     * 默认UTF-8
     *
     * @param file 文件
     * @return JSON文件流
     */
    public static JSONReader fileReader(File file) {
        return fileReader(file, StandardCharsets.UTF_8);
    }

    /**
     * JSON文件流读取
     *
     * @param file    文件
     * @param charset 字符集
     * @return JSON文件流
     */
    public static JSONReader fileReader(File file, Charset charset) {
        if (file.exists() && file.isFile()) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
                return JSONReader.of(inputStreamReader);
            } catch (FileNotFoundException e) {
                throw new MsToolsRuntimeException(e);
            }
        }
        throw new MsToolsRuntimeException("文件不存在或不是文件");
    }

    /**
     * 文件流逐步读取指定数量对象
     *
     * @param <T>    对象类型
     * @param reader 文件流
     * @param clazz  对象类型
     * @param count  指定数量
     * @return 对象列表
     */
    public static <T> List<T> readJsonObject(JSONReader reader, Class<T> clazz, int count) {
        JSONArray jsonArray = new JSONArray();
        if (reader.getOffset() == 1 && reader.isArray()) {
            reader.startArray();
        }
        while (!reader.isEnd() && jsonArray.size() < count) {
            if (nextEnd(reader) && reader.nextIfMatch(']')) {
                reader.endArray();
                break;
            }
            if (reader.isObject()) {
                jsonArray.add(reader.readObject());
            } else {
                jsonArray.add(reader.readArray());
            }
        }
        return new ArrayList<>(jsonArray.toJavaList(clazz));
    }

    protected static boolean nextEnd(JSONReader reader) {
        JSONReader.SavePoint mark = reader.mark();
        reader.next();
        boolean end = reader.isEnd();
        reader.reset(mark);
        return end;
    }
}
