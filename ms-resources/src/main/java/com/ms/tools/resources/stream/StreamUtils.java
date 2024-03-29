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

package com.ms.tools.resources.stream;


import com.ms.tools.core.exception.base.MsToolsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {

    /**
     * 读取输入流，并返回输入流内容
     *
     * @param inputStream 输入流
     * @return 输入流内容
     * @throws MsToolsException 如果读取输入流失败，抛出该异常
     */
    public static String readInputStream(InputStream inputStream) throws MsToolsException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取输入流，并返回输入流内容
     *
     * @param inputStream 输入流
     * @param charsetName 字符集名称
     * @return 输入流内容
     * @throws MsToolsException 如果读取输入流失败，抛出该异常
     */
    public static String readInputStream(InputStream inputStream, String charsetName) throws MsToolsException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }


}
