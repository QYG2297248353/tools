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

package com.ms.tools.security.effect;

import com.ms.tools.core.exception.base.MsToolsException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * MD5
 *
 * @author qyg2297248353
 */
public class MD5 {

    private final String md5;

    public MD5(String md5) {
        this.md5 = md5;
    }

    /**
     * 计算 MD5
     *
     * @param bytes 字节数组
     * @return MD5
     */
    public static String MD5(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }

    /**
     * 计算 MD5
     *
     * @param str 字符串
     * @return MD5
     */
    public static String MD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 计算 MD5
     *
     * @param stream 输入流
     * @return MD5
     * @throws MsToolsException 异常
     */
    public static String MD5(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.md5Hex(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 计算MD5
     *
     * @param file 文件
     * @return MD5
     * @throws MsToolsException 异常
     */
    public static String MD5(File file) throws MsToolsException {
        try {
            return DigestUtils.md5Hex(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 计算 MD5
     *
     * @return MD5
     */
    public String calculate() {
        return MD5(md5);
    }

}
