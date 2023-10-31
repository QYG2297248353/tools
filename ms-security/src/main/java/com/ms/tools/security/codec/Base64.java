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

package com.ms.tools.security.codec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Base64 extends org.apache.commons.codec.binary.Base64 {

    /**
     * 编码
     *
     * @param str 字符串
     * @return 编码后的字符串
     */
    public static String encodeStr(String str) {
        return encodeBase64String(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解码
     *
     * @param str 字符串
     * @return 解码后的字符串
     */
    public static String decodeStr(String str) {
        return new String(decodeBase64(str), StandardCharsets.UTF_8);
    }


    /**
     * 编码
     *
     * @param str 字符串
     * @return 编码后的字符串
     */
    public static String encodeStr(String str, Charset charset) {
        return encodeBase64String(str.getBytes(charset));
    }

    /**
     * 解码
     *
     * @param str 字符串
     * @return 解码后的字符串
     */
    public static String decodeStr(String str, Charset charset) {
        return new String(decodeBase64(str), charset);
    }
}
