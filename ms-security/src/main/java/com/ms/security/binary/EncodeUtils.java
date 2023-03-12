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

package com.ms.security.binary;

import com.ms.security.binary.base32.Base32;
import com.ms.security.binary.base64.Base64;
import com.ms.security.binary.hex.Hex;
import org.apache.commons.codec.binary.BinaryCodec;

/**
 * 编码器
 *
 * @author ms2297248353
 */
public class EncodeUtils {
    /**
     * 编码-Base64
     *
     * @param str 待编码
     * @return 编码结果
     */
    public static String encodeBase64(String str) {
        return encodeBase64(str.getBytes());
    }

    /**
     * 编码-Base32
     *
     * @param str 待编码
     * @return 编码结果
     */
    public static String encodeBase32(String str) {
        return encodeBase32(str.getBytes());
    }

    /**
     * 编码-二进制
     *
     * @param str 待编码
     * @return 编码结果
     */
    public static String encodeAscii(String str) {
        return encodeAscii(str.getBytes());
    }

    /**
     * 编码-16进制
     *
     * @param str 待编码
     * @return 编码结果
     */
    public static String encodeHex(String str) {
        return encodeHex(str.getBytes());
    }

    /**
     * 编码-Base64
     *
     * @param bytes 待编码
     * @return 编码结果
     */
    public static String encodeBase64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 编码-Base32
     *
     * @param bytes 待编码
     * @return 编码结果
     */
    public static String encodeBase32(byte[] bytes) {
        return new Base32().encodeAsString(bytes);
    }

    /**
     * 编码-二进制
     *
     * @param bytes 待编码
     * @return 编码结果
     */
    public static String encodeAscii(byte[] bytes) {
        byte[] encode = new BinaryCodec().encode(bytes);
        return new String(encode);
    }

    /**
     * 编码-16进制
     *
     * @param bytes 待编码
     * @return 编码结果
     */
    public static String encodeHex(byte[] bytes) {
        byte[] encode = new Hex().encode(bytes);
        return new String(encode);
    }

}
