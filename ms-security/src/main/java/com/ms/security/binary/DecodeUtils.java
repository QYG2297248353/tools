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

import com.ms.core.exception.base.MsToolsException;
import com.ms.security.binary.base32.Base32;
import com.ms.security.binary.base64.Base64;
import com.ms.security.binary.hex.Hex;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.BinaryCodec;

/**
 * 解码器
 *
 * @author ms2297248353
 */
public class DecodeUtils {
    /**
     * 解码-Base64
     *
     * @param str 待解码
     * @return 解码结果
     */
    public static byte[] decodeBase64(String str) {
        return Base64.decodeBase64(str);
    }

    /**
     * 解码-Base32
     *
     * @param str 待解码
     * @return 解码结果
     */
    public static byte[] decodeBase32(String str) {
        return new Base32().decode(str);
    }

    /**
     * 解码-而二进制
     *
     * @param str 待解码
     * @return 解码结果
     */
    public static byte[] decodeAscii(String str) {
        return new BinaryCodec().decode(str.getBytes());
    }

    /**
     * 解码-16进制
     *
     * @param str 待解码
     * @return 解码结果
     * @throws MsToolsException 异常
     */
    public static byte[] decodeHex(String str) throws MsToolsException {
        try {
            return new Hex().decode(str.getBytes());
        } catch (DecoderException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 解码-Base64
     *
     * @param str 待解码
     * @return 解码结果
     */
    public static String decodeBase64AsString(String str) {
        return new String(decodeBase64(str));
    }

    /**
     * 解码-Base32
     *
     * @param str 待解码
     * @return 解码结果
     */
    public static String decodeBase32AsString(String str) {
        return new String(decodeBase32(str));
    }

    /**
     * 解码-二进制
     *
     * @param str 待解码
     * @return 解码结果
     */
    public static String decodeAsciiAsString(String str) {
        return new String(decodeAscii(str));
    }

    /**
     * 解码-16进制
     *
     * @param str 待解码
     * @return 解码结果
     * @throws MsToolsException 异常
     */
    public static String decodeHexAsString(String str) throws MsToolsException {
        return new String(decodeHex(str));
    }

}
