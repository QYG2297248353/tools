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

package com.ms.tools.core.id.factory.uuid.factory.nonstandard;

import com.ms.tools.core.id.factory.uuid.enums.UuidVersion;
import com.ms.tools.core.id.factory.uuid.factory.AbstCombFactory;
import com.ms.tools.core.id.factory.uuid.util.internal.ByteUtil;

import java.time.Clock;
import java.util.Random;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;

/**
 * Concrete factory for creating Prefix COMB GUIDs.
 * <p>
 * A Prefix COMB GUID is a UUID that combines a creation time with random bits.
 * <p>
 * The creation millisecond is a 6 bytes PREFIX at the MOST significant bits.
 * <p>
 * The created UUID is a UUIDv4 for compatibility with RFC-4122.
 *
 * @see <a href="http://www.informit.com/articles/article.aspx?p=25862">The Cost
 * of GUIDs as Primary Keys</a>
 */
public final class PrefixCombFactory extends AbstCombFactory {

    public PrefixCombFactory() {
        this(builder());
    }

    public PrefixCombFactory(Clock clock) {
        this(builder().withClock(clock));
    }

    public PrefixCombFactory(Random random) {
        this(builder().withRandom(random));
    }

    public PrefixCombFactory(Random random, Clock clock) {
        this(builder().withRandom(random).withClock(clock));
    }

    public PrefixCombFactory(LongSupplier randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public PrefixCombFactory(IntFunction<byte[]> randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public PrefixCombFactory(LongSupplier randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    public PrefixCombFactory(IntFunction<byte[]> randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    private PrefixCombFactory(Builder builder) {
        super(UuidVersion.VERSION_RANDOM_BASED, builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a Prefix COMB GUID.
     *
     * @return a UUIDv4
     */
    @Override
    public synchronized UUID create() {

        long time = clock.millis();

        if (random instanceof ByteRandom) {
            byte[] bytes = random.nextBytes(10);
            long long1 = ByteUtil.toNumber(bytes, 0, 2);
            long long2 = ByteUtil.toNumber(bytes, 2, 10);
            return make(time, long1, long2);
        } else {
            long long1 = random.nextLong();
            long long2 = random.nextLong();
            return make(time, long1, long2);
        }
    }

    private UUID make(long time, long long1, long long2) {
        return toUuid((time << 16) | (long1 & 0x000000000000ffffL), long2);
    }

    public static class Builder extends AbstCombFactory.Builder<PrefixCombFactory, Builder> {
        @Override
        public PrefixCombFactory build() {
            return new PrefixCombFactory(this);
        }
    }
}
