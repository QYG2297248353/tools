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

package com.ms.tools.core.id.factory.uuid.factory.rfc4122;

import com.ms.tools.core.id.factory.uuid.enums.UuidVersion;
import com.ms.tools.core.id.factory.uuid.factory.AbstTimeBasedFactory;

/**
 * Concrete factory for creating time-ordered unique identifiers (UUIDv6).
 * <p>
 * UUIDv6 is a new UUID version proposed by Peabody and Davis.
 * <p>
 * <b>Warning:</b> this can change in the future.
 *
 * @see AbstTimeBasedFactory
 * @see <a href=
 * "https://tools.ietf.org/html/draft-peabody-dispatch-new-uuid-format">New
 * UUID formats</a>
 * @see <a href="https://datatracker.ietf.org/wg/uuidrev/documents/">Revise
 * Universally Unique Identifier Definitions (uuidrev)</a>
 */
public final class TimeOrderedFactory extends AbstTimeBasedFactory {

    public TimeOrderedFactory() {
        this(builder());
    }

    private TimeOrderedFactory(Builder builder) {
        super(UuidVersion.VERSION_TIME_ORDERED, builder);
    }

    /**
     * Returns a builder of random-ordered factory.
     *
     * @return a builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns the most significant bits of the UUID.
     * <p>
     * It implements the algorithm for generating UUIDv6.
     *
     * @param timestamp the number of 100-nanoseconds since 1970-01-01 (Unix epoch)
     * @return the MSB
     */
    @Override
    protected long formatMostSignificantBits(final long timestamp) {
        return ((timestamp & 0x0ffffffffffff000L) << 4) //
                | (timestamp & 0x0000000000000fffL) //
                | 0x0000000000006000L; // apply version 6
    }

    /**
     * Concrete builder for creating a time-ordered factory.
     *
     * @see AbstTimeBasedFactory.Builder
     */
    public static class Builder extends AbstTimeBasedFactory.Builder<TimeOrderedFactory, Builder> {
        @Override
        public TimeOrderedFactory build() {
            return new TimeOrderedFactory(this);
        }
    }
}
