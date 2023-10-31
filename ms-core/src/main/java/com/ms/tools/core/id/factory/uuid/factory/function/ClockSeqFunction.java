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

package com.ms.tools.core.id.factory.uuid.factory.function;

import com.ms.tools.core.id.factory.uuid.util.internal.RandomUtil;

import java.util.function.LongUnaryOperator;

/**
 * Function that must return a number between 0 and 16383 (2^14-1).
 * <p>
 * It receives as argument a number of 100-nanoseconds since 1970-01-01 (Unix
 * epoch).
 * <p>
 * Example:
 *
 * <pre>{@code
 * // A function that returns new random clock sequences
 * ClockSeqFunction f = t -> ClockSeqFunction.getRandom();
 * }</pre>
 */
@FunctionalInterface
public interface ClockSeqFunction extends LongUnaryOperator {

    /**
     * Returns a new random clock sequence in the range 0 to 16383 (2^14-1).
     *
     * @return a number in the range 0 to 16383 (2^14-1)
     */
    public static long getRandom() {
        return toExpectedRange(RandomUtil.nextLong());
    }

    /**
     * Clears the leading bits so that the resulting number is within the range 0 to
     * 16383 (2^14-1).
     * <p>
     * The result is equivalent to {@code n % 2^14}.
     *
     * @param clockseq a clock sequence
     * @return a number in the range 0 to 16383 (2^14-1).
     */
    public static long toExpectedRange(long clockseq) {
        return clockseq & 0x0000000000003fffL;
    }

    /**
     * Nested class that manages a pool of 16384 clock sequence values.
     * <p>
     * The pool is implemented as an array of 2048 bytes (16384 bits). Each bit of
     * the array corresponds to a clock sequence value.
     * <p>
     * It is used to avoid that two time-based factories use the same clock sequence
     * at same time in a class loader.
     */
    public static final class ClockSeqPool {

        public static final int POOL_MIN = 0x00000000;
        public static final int POOL_MAX = 0x00003fff; // 2^14-1 = 16383
        private static final int POOL_SIZE = 16384; // 2^14 = 16384
        private final byte[] pool = new byte[2048];

        /**
         * Take a value from the pool.
         * <p>
         * If the value to be taken is already in use, it is incremented until a free
         * value is found and returned.
         * <p>
         * In the case that all pool values are in use, the pool is cleared and the last
         * incremented value is returned.
         * <p>
         * It does nothing to negative arguments.
         *
         * @param take value to be taken from the pool
         * @return the value to be borrowed if not used
         */
        public synchronized int take(int take) {
            int value = take;
            for (int i = 0; i < POOL_SIZE; i++) {
                if (setBit(value)) {
                    return value;
                }
                value = ++value % POOL_SIZE;
            }
            clearPool();
            setBit(value);
            return value;
        }

        /**
         * Take a random value from the pool.
         *
         * @return the random value to be borrowed if not used
         */
        public synchronized int random() {
            // Choose a random number between 0 and 16383
            int random = (RandomUtil.nextInt() & 0x7fffffff) % POOL_SIZE;
            return take(random);
        }

        /**
         * Set a bit from the byte array that represents the pool.
         * <p>
         * This operation corresponds to setting a value as used.
         * <p>
         * It returns false if the value is not free.
         * <p>
         * It does nothing to negative arguments.
         *
         * @param value the value to be taken from the pool
         * @return true if success
         */
        private synchronized boolean setBit(int value) {

            if (value < 0) {
                return false;
            }

            int byteIndex = value / 8;
            int bitIndex = value % 8;

            int mask = (0x00000001 << bitIndex);
            boolean clear = (pool[byteIndex] & mask) == 0;

            if (clear) {
                pool[byteIndex] = (byte) (pool[byteIndex] | mask);
                return true;
            }

            return false;
        }

        /**
         * Check if a value is used out of the pool.
         *
         * @param value a value to be checked in the pool
         * @return true if the value is used
         */
        public synchronized boolean isUsed(int value) {

            int byteIndex = value / 8;
            int bitIndex = value % 8;

            int mask = (0x00000001 << bitIndex);
            boolean clear = (pool[byteIndex] & mask) == 0;

            return !clear;
        }

        /**
         * Check if a value is free in the pool.
         *
         * @param value a value to be checked in the pool
         * @return true if the value is free
         */
        public synchronized boolean isFree(int value) {
            return !isUsed(value);
        }

        /**
         * Count the used values out of the pool
         *
         * @return the count of used values
         */
        public synchronized int countUsed() {
            int counter = 0;
            for (int i = 0; i < POOL_SIZE; i++) {
                if (isUsed(i)) {
                    counter++;
                }
            }
            return counter;
        }

        /**
         * Count the free values in the pool.
         *
         * @return the count of free values
         */
        public synchronized int countFree() {
            return POOL_SIZE - countUsed();
        }

        /**
         * Clear all bits of the byte array that represents the pool.
         * <p>
         * This corresponds to marking all pool values as free
         */
        public synchronized void clearPool() {
            for (int i = 0; i < pool.length; i++) {
                pool[i] = 0;
            }
        }
    }
}
