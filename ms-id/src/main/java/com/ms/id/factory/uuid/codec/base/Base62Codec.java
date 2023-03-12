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

/**
 * Codec for base-62.
 * <p>
 * It is case SENSITIVE.
 * <p>
 * It encodes using remainder operator (modulus).
 */
public final class Base62Codec extends BaseNCodec {

    // a shared immutable instance
    public static final Base62Codec INSTANCE = new Base62Codec();
    private static final BaseN BASE_N = new BaseN("0-9A-Za-z");

    public Base62Codec() {
        super(BASE_N);
    }
}
