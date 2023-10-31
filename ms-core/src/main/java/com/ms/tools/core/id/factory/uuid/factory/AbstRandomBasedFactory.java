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

package com.ms.tools.core.id.factory.uuid.factory;

import com.ms.tools.core.id.factory.uuid.enums.UuidVersion;
import com.ms.tools.core.id.factory.uuid.factory.function.RandomFunction;
import com.ms.tools.core.id.factory.uuid.factory.function.impl.DefaultRandomFunction;
import com.ms.tools.core.id.factory.uuid.util.internal.ByteUtil;

import java.security.SecureRandom;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.LongSupplier;

/**
 * Abstract factory for creating random-based unique identifiers (UUIDv4).
 *
 * @see RandomFunction
 */
public abstract class AbstRandomBasedFactory extends UuidFactory implements NoArgsFactory {

    protected static final int UUID_BYTES = 16;
    protected final IRandom random;

    protected AbstRandomBasedFactory(UuidVersion version, Builder<?, ?> builder) {
        super(version);
        random = builder.getRandom();
    }

    protected static interface IRandom {

        public long nextLong();

        public byte[] nextBytes(int length);
    }

    /**
     * Abstract builder for creating a random-based factory.
     *
     * @param <T> factory type
     * @param <B> builder type
     */
    protected abstract static class Builder<T, B extends Builder<T, B>> {

        protected IRandom random;

        protected IRandom getRandom() {
            if (random == null) {
                random = new ByteRandom(new DefaultRandomFunction());
            }
            return random;
        }

        public B withRandom(Random random) {
            if (random != null) {
                if (random instanceof SecureRandom) {
                    this.random = new ByteRandom(random);
                } else {
                    this.random = new LongRandom(random);
                }
            }
            return (B) this;
        }

        public B withRandomFunction(LongSupplier randomFunction) {
            random = new LongRandom(randomFunction);
            return (B) this;
        }

        public B withRandomFunction(IntFunction<byte[]> randomFunction) {
            random = new ByteRandom(randomFunction);
            return (B) this;
        }

        public abstract T build();
    }

    protected static final class LongRandom implements IRandom {

        private final LongSupplier randomFunction;

        public LongRandom() {
            this(newRandomFunction(null));
        }

        public LongRandom(Random random) {
            this(newRandomFunction(random));
        }

        public LongRandom(LongSupplier randomFunction) {
            this.randomFunction = randomFunction != null ? randomFunction : newRandomFunction(null);
        }

        protected static LongSupplier newRandomFunction(Random random) {
            Random entropy = random != null ? random : new SecureRandom();
            return entropy::nextLong;
        }

        @Override
        public long nextLong() {
            return randomFunction.getAsLong();
        }

        @Override
        public byte[] nextBytes(int length) {

            int shift = 0;
            long random = 0;
            byte[] bytes = new byte[length];

            for (int i = 0; i < length; i++) {
                if (shift < Byte.SIZE) {
                    shift = Long.SIZE;
                    random = randomFunction.getAsLong();
                }
                shift -= Byte.SIZE; // 56, 48, 42...
                bytes[i] = (byte) (random >>> shift);
            }

            return bytes;
        }
    }

    protected static final class ByteRandom implements IRandom {

        private final IntFunction<byte[]> randomFunction;

        public ByteRandom() {
            this(newRandomFunction(null));
        }

        public ByteRandom(Random random) {
            this(newRandomFunction(random));
        }

        public ByteRandom(IntFunction<byte[]> randomFunction) {
            this.randomFunction = randomFunction != null ? randomFunction : newRandomFunction(null);
        }

        protected static IntFunction<byte[]> newRandomFunction(Random random) {
            Random entropy = random != null ? random : new SecureRandom();
            return (final int length) -> {
                byte[] bytes = new byte[length];
                entropy.nextBytes(bytes);
                return bytes;
            };
        }

        @Override
        public long nextLong() {
            byte[] bytes = randomFunction.apply(Long.BYTES);
            return ByteUtil.toNumber(bytes);
        }

        @Override
        public byte[] nextBytes(int length) {
            return randomFunction.apply(length);
        }
    }
}
