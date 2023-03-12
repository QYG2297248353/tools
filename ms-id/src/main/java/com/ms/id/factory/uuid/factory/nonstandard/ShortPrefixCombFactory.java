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

package com.ms.id.factory.uuid.factory.nonstandard;

import com.ms.id.factory.uuid.enums.UuidVersion;
import com.ms.id.factory.uuid.factory.AbstCombFactory;
import com.ms.id.factory.uuid.util.internal.ByteUtil;

import java.time.Clock;
import java.util.Random;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;

/**
 * Concrete factory for creating Short Prefix COMB GUIDs.
 * <p>
 * A Short Prefix COMB GUID is a UUID that combines a creation time with random
 * bits.
 * <p>
 * The creation minute is a 2 bytes PREFIX at the MOST significant bits.
 * <p>
 * The prefix wraps around every ~45 days (2^16/60/24 = ~45).
 * <p>
 * The created UUID is a UUIDv4 for compatibility with RFC-4122.
 *
 * @see <a href=
 * "https://www.2ndquadrant.com/en/blog/sequential-uuid-generators/">Sequential
 * UUID Generators</a>
 */
public final class ShortPrefixCombFactory extends AbstCombFactory {

    protected static final int DEFAULT_INTERVAL = 60_000;
    // interval in milliseconds
    protected final int interval;

    public ShortPrefixCombFactory() {
        this(builder());
    }

    public ShortPrefixCombFactory(Clock clock) {
        this(builder().withClock(clock));
    }

    public ShortPrefixCombFactory(Random random) {
        this(builder().withRandom(random));
    }

    public ShortPrefixCombFactory(Random random, Clock clock) {
        this(builder().withRandom(random).withClock(clock));
    }

    public ShortPrefixCombFactory(LongSupplier randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public ShortPrefixCombFactory(IntFunction<byte[]> randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public ShortPrefixCombFactory(LongSupplier randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    public ShortPrefixCombFactory(IntFunction<byte[]> randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    private ShortPrefixCombFactory(Builder builder) {
        super(UuidVersion.VERSION_RANDOM_BASED, builder);
        interval = builder.getInterval();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a Short Prefix COMB GUID.
     *
     * @return a UUIDv4
     */
    @Override
    public synchronized UUID create() {

        long time = clock.millis() / interval;

        if (random instanceof ByteRandom) {
            byte[] bytes = random.nextBytes(14);
            long long1 = ByteUtil.toNumber(bytes, 0, 6);
            long long2 = ByteUtil.toNumber(bytes, 6, 14);
            return make(time, long1, long2);

        } else {
            long long1 = random.nextLong();
            long long2 = random.nextLong();
            return make(time, long1, long2);
        }
    }

    private UUID make(long time, long long1, long long2) {
        return toUuid((time << 48) | (long1 & 0x0000ffffffffffffL), long2);
    }

    public static class Builder extends AbstCombFactory.Builder<ShortPrefixCombFactory, Builder> {

        private Integer interval;

        protected int getInterval() {
            if (interval == null) {
                interval = DEFAULT_INTERVAL;
            }
            return interval;
        }

        public Builder withInterval(int interval) {
            this.interval = interval;
            return this;
        }

        @Override
        public ShortPrefixCombFactory build() {
            return new ShortPrefixCombFactory(this);
        }
    }
}
