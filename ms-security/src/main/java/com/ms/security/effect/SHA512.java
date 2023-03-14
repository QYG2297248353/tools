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
public class SHA512 {

    private String sha512;

    public SHA512(String sha512) {
        this.sha512 = sha512;
    }

    public static byte[] SHA512(byte[] bytes) {
        return DigestUtils.sha512(bytes);
    }

    public static byte[] SHA512(String str) {
        return DigestUtils.sha512(str);
    }

    public static byte[] SHA512(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.sha512(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public static String SHA512AsString(byte[] bytes) {
        return DigestUtils.sha512Hex(bytes);
    }

    public static String SHA512AsString(String str) {
        return DigestUtils.sha512Hex(str);
    }

    public static String SHA512AsString(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.sha512Hex(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public String calculate() {
        return SHA512AsString(sha512);
    }

}
