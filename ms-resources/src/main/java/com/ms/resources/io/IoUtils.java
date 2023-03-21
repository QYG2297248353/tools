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

package com.ms.resources.io;

import java.io.*;

/**
 * @author qyg2297248353
 */
public class IoUtils {
    /**
     * 输入流转字符串
     *
     * @param inputStream 输入流
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * 字符串转输入流
     *
     * @param streamStr 字符串
     * @return 输入流
     * @throws IOException IO异常
     */
    public static InputStream stringToInputStream(String streamStr) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(streamStr.getBytes());
        return stream;
    }
}
