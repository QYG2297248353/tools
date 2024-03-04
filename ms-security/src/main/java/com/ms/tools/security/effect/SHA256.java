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
public class SHA256 {

    private final String sha256;

    public SHA256(String sha256) {
        this.sha256 = sha256;
    }

    /**
     * 计算 SHA256
     *
     * @param bytes 字节数组
     * @return SHA256
     */
    public static byte[] SHA256(byte[] bytes) {
        return DigestUtils.sha256(bytes);
    }

    /**
     * 计算 SHA256
     *
     * @param str 字符串
     * @return SHA256
     */
    public static byte[] SHA256(String str) {
        return DigestUtils.sha256(str);
    }

    /**
     * 计算 SHA256
     *
     * @param stream 输入流
     * @return SHA256
     * @throws MsToolsException 异常
     */
    public static byte[] SHA256(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.sha256(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 计算 SHA256
     *
     * @param bytes 字节数组
     * @return SHA256
     */
    public static String SHA256AsString(byte[] bytes) {
        return DigestUtils.sha256Hex(bytes);
    }

    /**
     * 计算 SHA256
     *
     * @param str 字符串
     * @return SHA256
     */
    public static String SHA256AsString(String str) {
        return DigestUtils.sha256Hex(str);
    }

    /**
     * 计算 SHA256
     *
     * @param stream 输入流
     * @return SHA256
     * @throws MsToolsException 异常
     */
    public static String SHA256AsString(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.sha256Hex(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 计算 SHA256
     *
     * @param file 文件
     * @return SHA256
     * @throws MsToolsException 异常
     */
    public static String SHA256AsFilePath(String file) throws MsToolsException {
        return SHA256AsFile(new File(file));
    }

    /**
     * 计算 SHA256
     *
     * @param file 文件
     * @return SHA256
     * @throws MsToolsException 异常
     */
    public static String SHA256AsFile(File file) throws MsToolsException {
        try {
            return DigestUtils.sha256Hex(Files.newInputStream(file.toPath()));
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 计算 SHA256
     *
     * @return SHA256
     */
    public String calculate() {
        return SHA256AsString(sha256);
    }

}
