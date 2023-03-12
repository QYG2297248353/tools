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

import com.ms.id.factory.uuid.codec.base.function.Base64Decoder;
import com.ms.id.factory.uuid.codec.base.function.Base64Encoder;

/**
 * Codec for base-64 as defined in RFC-4648.
 * <p>
 * It is case SENSITIVE.
 * <p>
 * The only difference between base-64 and base-64-url is that the second
 * substitutes the chars '+' and '/' with '-' and '_'.
 * <p>
 * This codec complies with RFC-4648, encoding a byte array sequentially. If you
 * need a codec that encodes integers using the remainder operator (modulus),
 * use the static factory {@link BaseNCodec#newInstance(BaseN)}.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc4648">RFC-4648</a>
 */
public final class Base64Codec extends BaseNCodec {

    // a shared immutable instance
    public static final Base64Codec INSTANCE = new Base64Codec();
    private static final BaseN BASE_N = new BaseN("A-Za-z0-9+/");

    public Base64Codec() {
        super(BASE_N, new Base64Encoder(BASE_N), new Base64Decoder(BASE_N));
    }
}
