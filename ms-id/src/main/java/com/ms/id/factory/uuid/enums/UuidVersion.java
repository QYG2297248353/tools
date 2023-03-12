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

package com.ms.id.factory.uuid.enums;

/**
 * UUID versions defined by RFC-4122.
 */
public enum UuidVersion {

    /**
     * An unknown version.
     */
    VERSION_UNKNOWN(0),
    /**
     * The time-based version with gregorian epoch specified in RFC-4122.
     */
    VERSION_TIME_BASED(1),
    /**
     * The DCE Security version, with embedded POSIX UIDs.
     */
    VERSION_DCE_SECURITY(2),
    /**
     * The name-based version specified in RFC-4122 that uses MD5 hashing.
     */
    VERSION_NAME_BASED_MD5(3),
    /**
     * The randomly or pseudo-randomly generated version specified in RFC-4122.
     */
    VERSION_RANDOM_BASED(4),
    /**
     * The name-based version specified in RFC-4122 that uses SHA-1 hashing.
     */
    VERSION_NAME_BASED_SHA1(5),
    /**
     * The time-ordered version with gregorian epoch proposed by Peabody and Davis.
     */
    VERSION_TIME_ORDERED(6),
    /**
     * The time-ordered version with Unix epoch proposed by Peabody and Davis.
     */
    VERSION_TIME_ORDERED_EPOCH(7),
    /**
     * The custom or free-form version proposed by Peabody and Davis.
     */
    VERSION_CUSTOM(8);

    private final int value;

    UuidVersion(int value) {
        this.value = value;
    }

    public static UuidVersion getVersion(int value) {
        for (UuidVersion version : UuidVersion.values()) {
            if (version.getValue() == value) {
                return version;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
