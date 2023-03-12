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

import com.ms.id.factory.uuid.codec.base.function.Base32Decoder;
import com.ms.id.factory.uuid.codec.base.function.Base32Encoder;

/**
 * Codec for base-32 as defined in RFC-4648.
 * <p>
 * It is case insensitive, so it decodes from lower and upper case, but encodes
 * to lower case only.
 * <p>
 * This codec complies with RFC-4648, encoding a byte array sequentially. If you
 * need a codec that encodes integers using the remainder operator (modulus),
 * use the static factory {@link BaseNCodec#newInstance(BaseN)}.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base32Codec extends BaseNCodec {

    // a shared immutable instance
    public static final Base32Codec INSTANCE = new Base32Codec();
    private static final BaseN BASE_N = new BaseN("a-z2-7");

    public Base32Codec() {
        super(BASE_N, new Base32Encoder(BASE_N), new Base32Decoder(BASE_N));
    }
}
