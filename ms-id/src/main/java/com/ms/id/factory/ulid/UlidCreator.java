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

package com.ms.id.factory.ulid;

/**
 * A class that generates ULIDs.
 * <p>
 * Both types of ULID can be easily created by this generator, i.e. monotonic
 * and non-monotonic.
 */
public final class UlidCreator {

    private UlidCreator() {
    }

    /**
     * Returns a ULID.
     *
     * @return a ULID
     */
    public static Ulid getUlid() {
        return UlidFactoryHolder.INSTANCE.create();
    }

    /**
     * Returns a ULID with a given time.
     *
     * @param time a number of milliseconds since 1970-01-01 (Unix epoch).
     * @return a ULID
     */
    public static Ulid getUlid(final long time) {
        return UlidFactoryHolder.INSTANCE.create(time);
    }

    /**
     * Returns a Monotonic ULID.
     *
     * @return a ULID
     */
    public static Ulid getMonotonicUlid() {
        return MonotonicFactoryHolder.INSTANCE.create();
    }

    /**
     * Returns a Monotonic ULID with a given time.
     *
     * @param time a number of milliseconds since 1970-01-01 (Unix epoch).
     * @return a ULID
     */
    public static Ulid getMonotonicUlid(final long time) {
        return MonotonicFactoryHolder.INSTANCE.create(time);
    }

    private static class UlidFactoryHolder {
        static final UlidFactory INSTANCE = UlidFactory.newInstance();
    }

    private static class MonotonicFactoryHolder {
        static final UlidFactory INSTANCE = UlidFactory.newMonotonicInstance();
    }
}
