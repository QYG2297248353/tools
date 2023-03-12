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
import com.ms.id.factory.uuid.factory.AbstRandomBasedFactory;
import com.ms.id.factory.uuid.util.internal.ByteUtil;

import java.time.Clock;
import java.util.Random;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;

/**
 * Concrete factory for creating Suffix COMB GUIDs.
 * <p>
 * A Suffix COMB GUID is a UUID that combines a creation time with random bits.
 * <p>
 * The creation millisecond is a 6 bytes SUFFIX at the LEAST significant bits.
 * <p>
 * The created UUID is a UUIDv4 for compatibility with RFC-4122.
 *
 * @see AbstCombFactory
 * @see AbstRandomBasedFactory
 * @see <a href="http://www.informit.com/articles/article.aspx?p=25862">The Cost
 * of GUIDs as Primary Keys</a>
 */
public final class SuffixCombFactory extends AbstCombFactory {

    public SuffixCombFactory() {
        this(builder());
    }

    public SuffixCombFactory(Clock clock) {
        this(builder().withClock(clock));
    }

    public SuffixCombFactory(Random random) {
        this(builder().withRandom(random));
    }

    public SuffixCombFactory(Random random, Clock clock) {
        this(builder().withRandom(random).withClock(clock));
    }

    public SuffixCombFactory(LongSupplier randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public SuffixCombFactory(IntFunction<byte[]> randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public SuffixCombFactory(LongSupplier randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    public SuffixCombFactory(IntFunction<byte[]> randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    private SuffixCombFactory(Builder builder) {
        super(UuidVersion.VERSION_RANDOM_BASED, builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a Suffix COMB GUID.
     *
     * @return a UUIDv4
     */
    @Override
    public synchronized UUID create() {

        long time = clock.millis();

        if (random instanceof ByteRandom) {
            byte[] bytes = random.nextBytes(10);
            long long1 = ByteUtil.toNumber(bytes, 0, 8);
            long long2 = ByteUtil.toNumber(bytes, 8, 10);
            return make(time, long1, long2);
        } else {
            long long1 = random.nextLong();
            long long2 = random.nextLong();
            return make(time, long1, long2);
        }
    }

    private UUID make(long time, long long1, long long2) {
        return toUuid(long1, (long2 << 48) | (time & 0x0000ffffffffffffL));
    }

    public static class Builder extends AbstCombFactory.Builder<SuffixCombFactory, Builder> {
        @Override
        public SuffixCombFactory build() {
            return new SuffixCombFactory(this);
        }
    }
}
