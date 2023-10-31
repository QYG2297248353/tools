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
import com.ms.tools.core.id.factory.uuid.factory.AbstCombFactory;
import com.ms.tools.core.id.factory.uuid.factory.nonstandard.PrefixCombFactory;
import com.ms.tools.core.id.factory.uuid.util.internal.ByteUtil;

import java.time.Clock;
import java.util.Random;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;

/**
 * 用于创建 Unix 纪元时间顺序唯一标识符 (UUIDv7) 的具体工厂。
 * UUIDv7 是 Peabody 和 Davis 提出的一个新的 UUID 版本。它类似于 Prefix COMB GUID 和 ULID。
 * 该工厂创建 3 种类型：
 * <p>
 * 类型 1（默认） ：该类型分为 3 个部分，即时间、计数器和随机。当时间重复时，计数器分量增加 1。随机分量总是随机的。
 * 类型 2（加 1） ：该类型分为 2 个分量，即时间和单调随机。当时间重复时，单调随机分量增加 1。这种类型的 UUID 就像一个单调的 ULID。它可以比其他类型快得多。
 * 类型 3（加 n） ：这种类型也分为 2 个分量，即时间和单调随机。当时间重复时，单调随机分量增加一个介于 1 和 MAX 之间的随机正整数。如果未指定 MAX 的值，则 MAX 为 2^32。这种类型的 UUID 也类似于 Monotonic ULID。
 * 警告：这可能会在未来发生变化
 *
 * @see PrefixCombFactory
 * @see <a href="https://github.com/ulid/spec">ULID Specification</a>
 * @see <a href=
 * "https://tools.ietf.org/html/draft-peabody-dispatch-new-uuid-format">New
 * UUID formats</a>
 * @see <a href="https://datatracker.ietf.org/wg/uuidrev/documents/">Revise
 * Universally Unique Identifier Definitions (uuidrev)</a>
 * @since 5.0.0
 */
public final class TimeOrderedEpochFactory extends AbstCombFactory {

    // Used to preserve monotonicity when the system clock is
    // adjusted by NTP after a small clock drift or when the
    // system clock jumps back by 1 second due to leap second.
    protected static final int CLOCK_DRIFT_TOLERANCE = 10_000;
    private static final int INCREMENT_TYPE_DEFAULT = 0; // add 2^48 to `rand_b`
    private static final int INCREMENT_TYPE_PLUS_1 = 1; // add 1 to `rand_b`
    private static final int INCREMENT_TYPE_PLUS_N = 2; // add n to `rand_b`, where 1 <= n <= 2^32-1
    private final int incrementType;
    private final LongSupplier incrementSupplier;
    private UUID lastUuid;

    public TimeOrderedEpochFactory() {
        this(builder());
    }

    public TimeOrderedEpochFactory(Clock clock) {
        this(builder().withClock(clock));
    }

    public TimeOrderedEpochFactory(Random random) {
        this(builder().withRandom(random));
    }

    public TimeOrderedEpochFactory(Random random, Clock clock) {
        this(builder().withRandom(random).withClock(clock));
    }

    public TimeOrderedEpochFactory(LongSupplier randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public TimeOrderedEpochFactory(IntFunction<byte[]> randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    public TimeOrderedEpochFactory(LongSupplier randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    public TimeOrderedEpochFactory(IntFunction<byte[]> randomFunction, Clock clock) {
        this(builder().withRandomFunction(randomFunction).withClock(clock));
    }

    private TimeOrderedEpochFactory(Builder builder) {
        super(UuidVersion.VERSION_TIME_ORDERED_EPOCH, builder);
        incrementType = builder.getIncrementType();
        incrementSupplier = builder.getIncrementSupplier();

        // initialize internal state
        lastUuid = make(0L, random.nextLong(), random.nextLong());
    }

    /**
     * Returns a builder of Unix epoch time-ordered factory.
     *
     * @return a builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a time-ordered unique identifier (UUIDv7).
     *
     * @return a UUIDv7
     */
    @Override
    public synchronized UUID create() {

        long time = clock.millis();
        long lastTime = lastUuid.getMostSignificantBits() >>> 16;

        // Check if the current time is the same as the previous time or has moved
        // backwards after a small system clock adjustment or after a leap second.
        // Drift tolerance = (previous_time - 10s) < current_time <= previous_time
        if ((time > lastTime - CLOCK_DRIFT_TOLERANCE) && (time <= lastTime)) {
            lastUuid = increment(lastUuid);
        } else {
            if (random instanceof ByteRandom) {
                byte[] bytes = random.nextBytes(10);
                long long1 = ByteUtil.toNumber(bytes, 0, 2);
                long long2 = ByteUtil.toNumber(bytes, 2, 10);
                lastUuid = make(time, long1, long2);
            } else {
                long long1 = random.nextLong();
                long long2 = random.nextLong();
                lastUuid = make(time, long1, long2);
            }
        }

        return copy(lastUuid);
    }

    private synchronized UUID increment(UUID uuid) {

        // Used to check if an overflow occurred.
        final long overflow = 0x0000000000000000L;

        // Used to propagate increments through bits.
        final long versionMask = 0x000000000000f000L;
        final long variantMask = 0xc000000000000000L;

        long msb = (uuid.getMostSignificantBits() | versionMask);
        long lsb = (uuid.getLeastSignificantBits() | variantMask) + incrementSupplier.getAsLong();

        if (INCREMENT_TYPE_DEFAULT == incrementType) {

            // Used to clear the random component bits.
            final long clearMask = 0xffff000000000000L;

            // If the counter's 14 bits overflow,
            if ((lsb & clearMask) == overflow) {
                msb += 1; // increment the MSB.
            }

            // And finally, randomize the lower 48 bits of the LSB.
            lsb &= clearMask; // Clear the random before randomize.
            lsb |= ByteUtil.toNumber(random.nextBytes(6));

        } else {
            // If the 62 bits of the monotonic random overflow,
            if (lsb == overflow) {
                msb += 1; // increment the MSB.
            }
        }

        return toUuid(msb, lsb);
    }

    private UUID make(long time, long long1, long long2) {
        return toUuid((time << 16) | (long1 & 0x000000000000ffffL), long2);
    }

    private synchronized UUID copy(UUID uuid) {
        return toUuid(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    /**
     * Concrete builder for creating a Unix epoch time-ordered factory.
     *
     * @see AbstCombFactory.Builder
     */
    public static class Builder extends AbstCombFactory.Builder<TimeOrderedEpochFactory, Builder> {

        private Integer incrementType;
        private Long incrementMax;

        public Builder withIncrementPlus1() {
            incrementType = INCREMENT_TYPE_PLUS_1;
            incrementMax = null;
            return this;
        }

        public Builder withIncrementPlusN() {
            incrementType = INCREMENT_TYPE_PLUS_N;
            incrementMax = null;
            return this;
        }

        public Builder withIncrementPlusN(long incrementMax) {
            incrementType = INCREMENT_TYPE_PLUS_N;
            this.incrementMax = incrementMax;
            return this;
        }

        protected int getIncrementType() {
            if (incrementType == null) {
                incrementType = INCREMENT_TYPE_DEFAULT;
            }
            return incrementType;
        }

        protected LongSupplier getIncrementSupplier() {
            switch (getIncrementType()) {
                case INCREMENT_TYPE_PLUS_1:
                    // add 1 to rand_b
                    return () -> 1L;
                case INCREMENT_TYPE_PLUS_N:
                    if (incrementMax == null) {
                        if (random instanceof ByteRandom) {
                            return () -> {
                                // add n to rand_b, where 1 <= n <= 2^32
                                byte[] bytes = random.nextBytes(4);
                                return ByteUtil.toNumber(bytes, 0, 4) + 1;
                            };
                        } else {
                            return () -> {
                                // add n to rand_b, where 1 <= n <= 2^32
                                return (random.nextLong() >>> 32) + 1;
                            };
                        }
                    } else {
                        final long positive = 0x7fffffffffffffffL;
                        if (random instanceof ByteRandom) {
                            // the minimum number of bits and bytes for incrementMax
                            int bits = (int) Math.ceil(Math.log(incrementMax) / Math.log(2));
                            int size = ((bits - 1) / Byte.SIZE) + 1;
                            return () -> {
                                // add n to rand_b, where 1 <= n <= incrementMax
                                byte[] bytes = random.nextBytes(size);
                                long random = ByteUtil.toNumber(bytes, 0, size);
                                return ((random & positive) % incrementMax) + 1;
                            };
                        } else {
                            return () -> {
                                // add n to rand_b, where 1 <= n <= incrementMax
                                return ((random.nextLong() & positive) % incrementMax) + 1;
                            };
                        }
                    }
                case INCREMENT_TYPE_DEFAULT:
                default:
                    // add 2^48 to rand_b
                    return () -> (1L << 48);
            }
        }

        @Override
        public TimeOrderedEpochFactory build() {
            return new TimeOrderedEpochFactory(this);
        }
    }
}
