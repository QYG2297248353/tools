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

import java.time.Clock;

/**
 * Abstract Factory for creating COMB GUIDs.
 * <p>
 * COMB GUIDs combine a creation time and random bytes.
 */
public abstract class AbstCombFactory extends AbstRandomBasedFactory {

    protected static final Clock DEFAULT_CLOCK = Clock.systemUTC();
    protected Clock clock;

    protected AbstCombFactory(UuidVersion version, Builder<?, ?> builder) {
        super(version, builder);
        clock = builder.getClock();
    }

    /**
     * Abstract builder for creating a COMB factory.
     *
     * @param <T> factory type
     * @param <B> builder type
     * @see AbstRandomBasedFactory.Builder
     */
    public abstract static class Builder<T, B extends Builder<T, B>> extends AbstRandomBasedFactory.Builder<T, B> {

        protected Clock clock;

        protected Clock getClock() {
            if (clock == null) {
                clock = DEFAULT_CLOCK;
            }
            return clock;
        }

        public B withClock(Clock clock) {
            this.clock = clock;
            return (B) this;
        }
    }
}
