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

package com.ms.id.factory.uuid.factory.rfc4122;

import com.ms.id.factory.uuid.enums.UuidNamespace;
import com.ms.id.factory.uuid.enums.UuidVersion;
import com.ms.id.factory.uuid.factory.AbstNameBasedFactory;

import java.util.UUID;

/**
 * Concrete factory for creating name-based unique identifiers using SHA-1
 * hashing (UUIDv5).
 *
 * @see AbstNameBasedFactory
 */
public final class NameBasedSha1Factory extends AbstNameBasedFactory {

    public NameBasedSha1Factory() {
        this((byte[]) null);
    }

    public NameBasedSha1Factory(UUID namespace) {
        this(bytes(namespace));
    }

    public NameBasedSha1Factory(String namespace) {
        this(bytes(namespace));
    }

    public NameBasedSha1Factory(UuidNamespace namespace) {
        this(bytes(namespace));
    }

    private NameBasedSha1Factory(byte[] namespace) {
        super(UuidVersion.VERSION_NAME_BASED_SHA1, ALGORITHM_SHA1, namespace);
    }
}
