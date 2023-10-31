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
 * Function that encodes a UUID to a base-16 string.
 * <p>
 * It encodes in lower case only.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base16Encoder extends BaseNEncoder {

    private static final int CHAR_LENGTH = 32;

    public Base16Encoder(BaseN base) {
        super(base);
    }

    @Override
    public String apply(UUID uuid) {

        char[] chars = new char[CHAR_LENGTH];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        chars[0x00] = alphabet.get((int) (msb >>> 0x3c & 0xf));
        chars[0x01] = alphabet.get((int) (msb >>> 0x38 & 0xf));
        chars[0x02] = alphabet.get((int) (msb >>> 0x34 & 0xf));
        chars[0x03] = alphabet.get((int) (msb >>> 0x30 & 0xf));
        chars[0x04] = alphabet.get((int) (msb >>> 0x2c & 0xf));
        chars[0x05] = alphabet.get((int) (msb >>> 0x28 & 0xf));
        chars[0x06] = alphabet.get((int) (msb >>> 0x24 & 0xf));
        chars[0x07] = alphabet.get((int) (msb >>> 0x20 & 0xf));
        chars[0x08] = alphabet.get((int) (msb >>> 0x1c & 0xf));
        chars[0x09] = alphabet.get((int) (msb >>> 0x18 & 0xf));
        chars[0x0a] = alphabet.get((int) (msb >>> 0x14 & 0xf));
        chars[0x0b] = alphabet.get((int) (msb >>> 0x10 & 0xf));
        chars[0x0c] = alphabet.get((int) (msb >>> 0x0c & 0xf));
        chars[0x0d] = alphabet.get((int) (msb >>> 0x08 & 0xf));
        chars[0x0e] = alphabet.get((int) (msb >>> 0x04 & 0xf));
        chars[0x0f] = alphabet.get((int) (msb & 0xf));

        chars[0x10] = alphabet.get((int) (lsb >>> 0x3c & 0xf));
        chars[0x11] = alphabet.get((int) (lsb >>> 0x38 & 0xf));
        chars[0x12] = alphabet.get((int) (lsb >>> 0x34 & 0xf));
        chars[0x13] = alphabet.get((int) (lsb >>> 0x30 & 0xf));
        chars[0x14] = alphabet.get((int) (lsb >>> 0x2c & 0xf));
        chars[0x15] = alphabet.get((int) (lsb >>> 0x28 & 0xf));
        chars[0x16] = alphabet.get((int) (lsb >>> 0x24 & 0xf));
        chars[0x17] = alphabet.get((int) (lsb >>> 0x20 & 0xf));
        chars[0x18] = alphabet.get((int) (lsb >>> 0x1c & 0xf));
        chars[0x19] = alphabet.get((int) (lsb >>> 0x18 & 0xf));
        chars[0x1a] = alphabet.get((int) (lsb >>> 0x14 & 0xf));
        chars[0x1b] = alphabet.get((int) (lsb >>> 0x10 & 0xf));
        chars[0x1c] = alphabet.get((int) (lsb >>> 0x0c & 0xf));
        chars[0x1d] = alphabet.get((int) (lsb >>> 0x08 & 0xf));
        chars[0x1e] = alphabet.get((int) (lsb >>> 0x04 & 0xf));
        chars[0x1f] = alphabet.get((int) (lsb & 0xf));

        return new String(chars);
    }
}
