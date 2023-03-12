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

import com.ms.id.factory.uuid.enums.UuidVersion;
import com.ms.id.factory.uuid.factory.AbstTimeBasedFactory;

/**
 * Concrete factory for creating time-based unique identifiers (UUIDv1).
 *
 * @see AbstTimeBasedFactory
 */
public final class TimeBasedFactory extends AbstTimeBasedFactory {

    public TimeBasedFactory() {
        this(builder());
    }

    private TimeBasedFactory(Builder builder) {
        super(UuidVersion.VERSION_TIME_BASED, builder);
    }

    /**
     * Returns a builder of time-based factory.
     *
     * @return a builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Concrete builder for creating a time-based factory.
     *
     * @see AbstTimeBasedFactory.Builder
     */
    public static class Builder extends AbstTimeBasedFactory.Builder<TimeBasedFactory, Builder> {
        @Override
        public TimeBasedFactory build() {
            return new TimeBasedFactory(this);
        }
    }
}
