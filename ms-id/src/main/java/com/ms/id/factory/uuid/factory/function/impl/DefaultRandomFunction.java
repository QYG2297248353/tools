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

package com.ms.id.factory.uuid.factory.function.impl;

import com.ms.id.factory.uuid.factory.function.RandomFunction;
import com.ms.id.factory.uuid.util.internal.RandomUtil;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Function that returns an array of bytes with the given length.
 * <p>
 * The current implementation uses a pool {@link SecureRandom}.
 * <p>
 * The pool size is equal to the number of processors available, up to a maximum
 * of 16.
 * <p>
 * The PRNG algorithm can be specified by system property or environment
 * variable. See {@link RandomUtil#newSecureRandom()}.
 *
 * @see RandomFunction
 * @see RandomUtil#newSecureRandom()
 */
public final class DefaultRandomFunction implements RandomFunction {

    private static final int POOL_SIZE = processors();
    private static final Random[] POOL = new Random[POOL_SIZE];

    private static Random current() {

        // calculate the pool index given the current thread ID
        final int index = (int) Thread.currentThread().getId() % POOL_SIZE;

        // lazy loading instance
        if (POOL[index] == null) {
            POOL[index] = RandomUtil.newSecureRandom();
        }

        return POOL[index];
    }

    private static int processors() {

        final int min = 1;
        final int max = 16;

        // get the number of processors from the runtime
        final int processors = Runtime.getRuntime().availableProcessors();

        if (processors < min) {
            return min;
        } else if (processors > max) {
            return max;
        }

        return processors;
    }

    @Override
    public byte[] apply(final int length) {
        final byte[] bytes = new byte[length];
        current().nextBytes(bytes);
        return bytes;
    }
}
