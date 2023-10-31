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
import com.ms.tools.core.id.factory.uuid.util.immutable.CharArray;

import java.util.UUID;
import java.util.function.Function;

/**
 * Abstract function to be extended by all encoder functions of this package.
 * <p>
 * If the base-n is case insensitive, it encodes in lower case only.
 */
public abstract class BaseNEncoder implements Function<UUID, String> {

    protected final BaseN base;
    protected final CharArray alphabet;

    /**
     * @param base an object that represents the base-n encoding
     */
    public BaseNEncoder(BaseN base) {
        this.base = base;
        this.alphabet = base.getAlphabet();
    }
}
