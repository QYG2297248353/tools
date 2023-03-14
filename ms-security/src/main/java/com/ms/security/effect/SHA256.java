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

package com.ms.security.effect;

import com.ms.core.exception.base.MsToolsException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * MD5
 *
 * @author qyg2297248353
 */
public class SHA256 {

    private String sha256;

    public SHA256(String sha256) {
        this.sha256 = sha256;
    }

    public static byte[] SHA256(byte[] bytes) {
        return DigestUtils.sha256(bytes);
    }

    public static byte[] SHA256(String str) {
        return DigestUtils.sha256(str);
    }

    public static byte[] SHA256(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.sha256(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public static String SHA256AsString(byte[] bytes) {
        return DigestUtils.sha256Hex(bytes);
    }

    public static String SHA256AsString(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public static String SHA256AsString(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.sha256Hex(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public String calculate() {
        return SHA256AsString(sha256);
    }

}
