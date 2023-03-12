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

package com.ms.id.factory.uuid.factory;

import com.ms.id.factory.uuid.enums.UuidVersion;

import java.util.UUID;

/**
 * Abstract factory that is base for all UUID factories.
 */
public abstract class UuidFactory {

    protected final UuidVersion version;
    protected final long versionMask;

    public UuidFactory(UuidVersion version) {
        this.version = version;
        versionMask = (long) version.getValue() << 12;
    }

    /**
     * Returns the version number for this factory.
     *
     * @return the version number
     */
    public UuidVersion getVersion() {
        return version;
    }

    /**
     * Creates a UUID from a pair of numbers.
     * <p>
     * It applies the version and variant numbers to the resulting UUID.
     *
     * @param msb the most significant bits
     * @param lsb the least significant bits
     * @return a UUID
     */
    protected UUID toUuid(long msb, long lsb) {
        long msb0 = (msb & 0xffffffffffff0fffL) | versionMask; // set version
        long lsb0 = (lsb & 0x3fffffffffffffffL) | 0x8000000000000000L; // set variant
        return new UUID(msb0, lsb0);
    }
}
