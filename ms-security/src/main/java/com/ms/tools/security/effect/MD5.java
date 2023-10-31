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

import java.io.IOException;
import java.io.InputStream;

/**
 * MD5
 *
 * @author qyg2297248353
 */
public class MD5 {

    private String md5;

    public MD5(String md5) {
        this.md5 = md5;
    }

    public static String MD5(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }

    public static String MD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String MD5(InputStream stream) throws MsToolsException {
        try {
            return DigestUtils.md5Hex(stream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public String calculate() {
        return MD5(md5);
    }

}
