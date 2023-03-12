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
 * Function that encodes a UUID to a base-64 string.
 * <p>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base64Encoder extends BaseNEncoder {

    private static final int CHAR_LENGTH = 22;

    public Base64Encoder(BaseN base) {
        super(base);
    }

    @Override
    public String apply(UUID uuid) {

        char[] chars = new char[CHAR_LENGTH];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        chars[0x00] = alphabet.get((int) ((msb >>> 58) & 0b111111));
        chars[0x01] = alphabet.get((int) ((msb >>> 52) & 0b111111));
        chars[0x02] = alphabet.get((int) ((msb >>> 46) & 0b111111));
        chars[0x03] = alphabet.get((int) ((msb >>> 40) & 0b111111));
        chars[0x04] = alphabet.get((int) ((msb >>> 34) & 0b111111));
        chars[0x05] = alphabet.get((int) ((msb >>> 28) & 0b111111));
        chars[0x06] = alphabet.get((int) ((msb >>> 22) & 0b111111));
        chars[0x07] = alphabet.get((int) ((msb >>> 16) & 0b111111));
        chars[0x08] = alphabet.get((int) ((msb >>> 10) & 0b111111));
        chars[0x09] = alphabet.get((int) ((msb >>> 4) & 0b111111));

        chars[0x0a] = alphabet.get((int) (msb << 2 & 0b111111) | (int) ((lsb >>> 62) & 0b111111));

        chars[0x0b] = alphabet.get((int) ((lsb >>> 56) & 0b111111));
        chars[0x0c] = alphabet.get((int) ((lsb >>> 50) & 0b111111));
        chars[0x0d] = alphabet.get((int) ((lsb >>> 44) & 0b111111));
        chars[0x0e] = alphabet.get((int) ((lsb >>> 38) & 0b111111));
        chars[0x0f] = alphabet.get((int) ((lsb >>> 32) & 0b111111));
        chars[0x10] = alphabet.get((int) ((lsb >>> 26) & 0b111111));
        chars[0x11] = alphabet.get((int) ((lsb >>> 20) & 0b111111));
        chars[0x12] = alphabet.get((int) ((lsb >>> 14) & 0b111111));
        chars[0x13] = alphabet.get((int) ((lsb >>> 8) & 0b111111));
        chars[0x14] = alphabet.get((int) ((lsb >>> 2) & 0b111111));
        chars[0x15] = alphabet.get((int) ((lsb << 4) & 0b111111));

        return new String(chars);
    }
}
