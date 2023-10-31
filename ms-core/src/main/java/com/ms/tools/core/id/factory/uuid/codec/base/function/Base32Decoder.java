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

package com.ms.tools.core.id.factory.uuid.codec.base.function;

import com.ms.tools.core.id.factory.uuid.codec.base.BaseN;

import java.util.UUID;

/**
 * Function that decodes a base-32 string to a UUID.
 * <p>
 * It is case insensitive, so it decodes in lower case and upper case.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base32Decoder extends BaseNDecoder {

    public Base32Decoder(BaseN base) {
        super(base);
    }

    @Override
    public UUID apply(String string) {

        char[] chars = string.toCharArray();

        long msb = 0;
        long lsb = 0;

        msb |= map.get(chars[0x00]) << 59;
        msb |= map.get(chars[0x01]) << 54;
        msb |= map.get(chars[0x02]) << 49;
        msb |= map.get(chars[0x03]) << 44;
        msb |= map.get(chars[0x04]) << 39;
        msb |= map.get(chars[0x05]) << 34;
        msb |= map.get(chars[0x06]) << 29;
        msb |= map.get(chars[0x07]) << 24;
        msb |= map.get(chars[0x08]) << 19;
        msb |= map.get(chars[0x09]) << 14;
        msb |= map.get(chars[0x0a]) << 9;
        msb |= map.get(chars[0x0b]) << 4;

        msb |= map.get(chars[0x0c]) >>> 1;
        lsb |= map.get(chars[0x0c]) << 63;

        lsb |= map.get(chars[0x0d]) << 58;
        lsb |= map.get(chars[0x0e]) << 53;
        lsb |= map.get(chars[0x0f]) << 48;
        lsb |= map.get(chars[0x10]) << 43;
        lsb |= map.get(chars[0x11]) << 38;
        lsb |= map.get(chars[0x12]) << 33;
        lsb |= map.get(chars[0x13]) << 28;
        lsb |= map.get(chars[0x14]) << 23;
        lsb |= map.get(chars[0x15]) << 18;
        lsb |= map.get(chars[0x16]) << 13;
        lsb |= map.get(chars[0x17]) << 8;
        lsb |= map.get(chars[0x18]) << 3;
        lsb |= map.get(chars[0x19]) >>> 2;

        return new UUID(msb, lsb);
    }
}
