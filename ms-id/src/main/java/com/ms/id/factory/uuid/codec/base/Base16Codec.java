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

package com.ms.id.factory.uuid.codec.base;

import com.ms.id.factory.uuid.codec.base.function.Base16Decoder;
import com.ms.id.factory.uuid.codec.base.function.Base16Encoder;

/**
 * Codec for base-16 as defined in RFC-4648.
 * <p>
 * It is case insensitive, so it decodes from lower and upper case, but encodes
 * to lower case only.
 * <p>
 * It can be up to 22x faster than doing
 * <code>uuid.toString().replaceAll("-", "")`</code>.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base16Codec extends BaseNCodec {

    // a shared immutable instance
    public static final Base16Codec INSTANCE = new Base16Codec();
    private static final BaseN BASE_N = new BaseN("0-9a-f");

    public Base16Codec() {
        super(BASE_N, new Base16Encoder(BASE_N), new Base16Decoder(BASE_N));
    }
}
