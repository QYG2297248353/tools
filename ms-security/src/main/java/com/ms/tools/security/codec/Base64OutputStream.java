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

import org.apache.commons.codec.CodecPolicy;

import java.io.OutputStream;

/**
 * Base64 编码解码
 *
 * @author qyg2297248353
 */
public class Base64OutputStream extends org.apache.commons.codec.binary.Base64OutputStream {
    public Base64OutputStream(OutputStream out) {
        super(out);
    }

    public Base64OutputStream(OutputStream out, boolean doEncode) {
        super(out, doEncode);
    }

    public Base64OutputStream(OutputStream out, boolean doEncode, int lineLength, byte[] lineSeparator) {
        super(out, doEncode, lineLength, lineSeparator);
    }

    public Base64OutputStream(OutputStream out, boolean doEncode, int lineLength, byte[] lineSeparator, CodecPolicy decodingPolicy) {
        super(out, doEncode, lineLength, lineSeparator, decodingPolicy);
    }
}
