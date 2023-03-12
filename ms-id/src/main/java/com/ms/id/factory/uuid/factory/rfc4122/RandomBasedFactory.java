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
import com.ms.id.factory.uuid.factory.AbstRandomBasedFactory;
import com.ms.id.factory.uuid.util.internal.ByteUtil;

import java.util.Random;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;

/**
 * Concrete factory for creating random-based unique identifiers (UUIDv4).
 */
public final class RandomBasedFactory extends AbstRandomBasedFactory {

    public RandomBasedFactory() {
        this(builder());
    }

    public RandomBasedFactory(Random random) {
        this(builder().withRandom(random));
    }

    public RandomBasedFactory(LongSupplier randomSupplier) {
        this(builder().withRandomFunction(randomSupplier));
    }

    public RandomBasedFactory(IntFunction<byte[]> randomFunction) {
        this(builder().withRandomFunction(randomFunction));
    }

    private RandomBasedFactory(Builder builder) {
        super(UuidVersion.VERSION_RANDOM_BASED, builder);
    }

    /**
     * Returns a builder of random-based factory.
     *
     * @return a builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a random-based UUID.
     * <p>
     * ### RFC-4122 - 4.4. Algorithms for Creating a UUID from Truly Random or
     * Pseudo-Random Numbers
     * <p>
     * (1) Set the two most significant bits (bits 6 and 7) of the
     * clock_seq_hi_and_reserved to zero and one, respectively.
     * <p>
     * (2) Set the four most significant bits (bits 12 through 15) of the
     * time_hi_and_version field to the 4-bit version number from Section 4.1.3.
     * <p>
     * (3) Set all the other bits to randomly (or pseudo-randomly) chosen values.
     *
     * @return a random-based UUID
     */
    @Override
    public synchronized UUID create() {
        if (random instanceof ByteRandom) {
            byte[] bytes = random.nextBytes(16);
            long msb = ByteUtil.toNumber(bytes, 0, 8);
            long lsb = ByteUtil.toNumber(bytes, 8, 16);
            return toUuid(msb, lsb);
        } else {
            long msb = random.nextLong();
            long lsb = random.nextLong();
            return toUuid(msb, lsb);
        }
    }

    /**
     * Concrete builder for creating a random-based factory.
     *
     * @see AbstRandomBasedFactory.Builder
     */
    public static class Builder extends AbstRandomBasedFactory.Builder<RandomBasedFactory, Builder> {
        @Override
        public RandomBasedFactory build() {
            return new RandomBasedFactory(this);
        }
    }
}
