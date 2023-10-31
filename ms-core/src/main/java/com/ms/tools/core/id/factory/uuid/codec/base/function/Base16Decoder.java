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
 * Function that decodes a base-16 string to a UUID.
 * <p>
 * It is case insensitive, so it decodes in lower case and upper case.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base16Decoder extends BaseNDecoder {

    public Base16Decoder(BaseN base) {
        super(base);
    }

    @Override
    public UUID apply(String string) {

        char[] chars = string.toCharArray();

        long msb = 0;
        long lsb = 0;

        msb |= map.get(chars[0x00]) << 60;
        msb |= map.get(chars[0x01]) << 56;
        msb |= map.get(chars[0x02]) << 52;
        msb |= map.get(chars[0x03]) << 48;
        msb |= map.get(chars[0x04]) << 44;
        msb |= map.get(chars[0x05]) << 40;
        msb |= map.get(chars[0x06]) << 36;
        msb |= map.get(chars[0x07]) << 32;
        msb |= map.get(chars[0x08]) << 28;
        msb |= map.get(chars[0x09]) << 24;
        msb |= map.get(chars[0x0a]) << 20;
        msb |= map.get(chars[0x0b]) << 16;
        msb |= map.get(chars[0x0c]) << 12;
        msb |= map.get(chars[0x0d]) << 8;
        msb |= map.get(chars[0x0e]) << 4;
        msb |= map.get(chars[0x0f]);

        lsb |= map.get(chars[0x10]) << 60;
        lsb |= map.get(chars[0x11]) << 56;
        lsb |= map.get(chars[0x12]) << 52;
        lsb |= map.get(chars[0x13]) << 48;
        lsb |= map.get(chars[0x14]) << 44;
        lsb |= map.get(chars[0x15]) << 40;
        lsb |= map.get(chars[0x16]) << 36;
        lsb |= map.get(chars[0x17]) << 32;
        lsb |= map.get(chars[0x18]) << 28;
        lsb |= map.get(chars[0x19]) << 24;
        lsb |= map.get(chars[0x1a]) << 20;
        lsb |= map.get(chars[0x1b]) << 16;
        lsb |= map.get(chars[0x1c]) << 12;
        lsb |= map.get(chars[0x1d]) << 8;
        lsb |= map.get(chars[0x1e]) << 4;
        lsb |= map.get(chars[0x1f]);

        return new UUID(msb, lsb);
    }
}
