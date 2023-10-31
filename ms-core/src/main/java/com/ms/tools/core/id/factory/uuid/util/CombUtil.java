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

package com.ms.tools.core.id.factory.uuid.util;

import java.time.Instant;
import java.util.UUID;

/**
 * Utility for extracting time from COMB GUIDs.
 */
public final class CombUtil {

    private CombUtil() {
    }

    /**
     * Returns the prefix from a Prefix COMB.
     * <p>
     * The value returned is equivalent to the number of milliseconds since
     * 1970-01-01 (Unix epoch).
     *
     * @param comb a Prefix COMB
     * @return the prefix (the Unix milliseconds)
     */
    public static long getPrefix(UUID comb) {
        return (comb.getMostSignificantBits() >>> 16);
    }

    /**
     * Returns the suffix from a Suffix COMB.
     * <p>
     * The value returned is equivalent to the number of milliseconds since
     * 1970-01-01 (Unix epoch).
     *
     * @param comb a Suffix COMB
     * @return the suffix (the Unix milliseconds)
     */
    public static long getSuffix(UUID comb) {
        return (comb.getLeastSignificantBits() & 0x0000ffffffffffffL);
    }

    /**
     * Returns the instant from a Prefix COMB.
     *
     * @param comb a Prefix COMB
     * @return {@link Instant}
     */
    public static Instant getPrefixInstant(UUID comb) {
        long milliseconds = getPrefix(comb);
        return Instant.ofEpochMilli(milliseconds);
    }

    /**
     * Returns the instant from a Suffix COMB.
     *
     * @param comb a Suffix COMB
     * @return {@link Instant}
     */
    public static Instant getSuffixInstant(UUID comb) {
        long milliseconds = getSuffix(comb);
        return Instant.ofEpochMilli(milliseconds);
    }
}
