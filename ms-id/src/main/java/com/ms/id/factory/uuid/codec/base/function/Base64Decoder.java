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

package com.ms.id.factory.uuid.codec.base.function;

import com.ms.id.factory.uuid.codec.base.BaseN;

import java.util.UUID;

/**
 * Function that decodes a base-64 string to a UUID.
 * <p>
 * It is case SENSITIVE.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base64Decoder extends BaseNDecoder {

    public Base64Decoder(BaseN base) {
        super(base);
    }

    @Override
    public UUID apply(String string) {

        char[] chars = string.toCharArray();

        long msb = 0;
        long lsb = 0;

        msb |= map.get(chars[0x00]) << 58;
        msb |= map.get(chars[0x01]) << 52;
        msb |= map.get(chars[0x02]) << 46;
        msb |= map.get(chars[0x03]) << 40;
        msb |= map.get(chars[0x04]) << 34;
        msb |= map.get(chars[0x05]) << 28;
        msb |= map.get(chars[0x06]) << 22;
        msb |= map.get(chars[0x07]) << 16;
        msb |= map.get(chars[0x08]) << 10;
        msb |= map.get(chars[0x09]) << 4;

        msb |= map.get(chars[0x0a]) >>> 2;
        lsb |= map.get(chars[0x0a]) << 62;

        lsb |= map.get(chars[0x0b]) << 56;
        lsb |= map.get(chars[0x0c]) << 50;
        lsb |= map.get(chars[0x0d]) << 44;
        lsb |= map.get(chars[0x0e]) << 38;
        lsb |= map.get(chars[0x0f]) << 32;
        lsb |= map.get(chars[0x10]) << 26;
        lsb |= map.get(chars[0x11]) << 20;
        lsb |= map.get(chars[0x12]) << 14;
        lsb |= map.get(chars[0x13]) << 8;
        lsb |= map.get(chars[0x14]) << 2;
        lsb |= map.get(chars[0x15]) >>> 4;

        return new UUID(msb, lsb);
    }
}
