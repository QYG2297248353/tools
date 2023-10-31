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

package com.ms.tools.core.id.factory.uuid.util;

import java.time.Instant;

/**
 * Utility for UUID time stamps.
 * <p>
 * The UUID time stamp is a 60-bit number.
 * <p>
 * The UUID time stamp resolution is 100ns, i.e., the UUID clock ticks every
 * 100-nanosecond interval.
 * <p>
 * In JDK 8, {@link Instant#now()} has millisecond precision, in spite of
 * {@link Instant} has nanoseconds resolution. In JDK 9+,{@link Instant#now()}
 * has microsecond precision.
 *
 * @see <a href="https://stackoverflow.com/questions/1712205">Current time in
 * microseconds in java</a>
 * @see <a href="https://bugs.openjdk.java.net/browse/JDK-8068730">Increase the
 * precision of the implementation of java.time.Clock.systemUTC()</a>
 */
public final class UuidTime {

    public static final Instant EPOCH_UNIX = Instant.parse("1970-01-01T00:00:00.000Z"); // 0s
    public static final Instant EPOCH_GREG = Instant.parse("1582-10-15T00:00:00.000Z"); // -12219292800s

    public static final long EPOCH_UNIX_SECONDS = EPOCH_UNIX.getEpochSecond();
    public static final long EPOCH_GREG_SECONDS = EPOCH_GREG.getEpochSecond();

    public static final long NANOS_PER_TICK = 100; // 1 tick = 100ns
    public static final long TICKS_PER_MILLI = 10_000; // 1ms = 10,000 ticks
    public static final long TICKS_PER_SECOND = 10_000_000; // 1s = 10,000,000 ticks

    private UuidTime() {
    }

    /**
     * Returns the number of 100ns since 1970-01-01 (Unix epoch).
     * <p>
     * It uses {@link Instant#now()} to get the the current time.
     *
     * @return a number of 100ns since 1970-01-01 (Unix epoch).
     */
    public static long getUnixTimestamp() {
        return toUnixTimestamp(Instant.now());
    }

    /**
     * Returns the number of 100ns since 1582-10-15 (Gregorian epoch).
     * <p>
     * It uses {@link Instant#now()} to get the the current time.
     *
     * @return a number of 100ns since 1582-10-15 (Gregorian epoch).
     */
    public static long getGregTimestamp() {
        return toGregTimestamp(Instant.now());
    }

    /**
     * Converts a number of 100ns since 1582-10-15 (Gregorian epoch) into a number
     * of 100ns since 1970-01-01 (Unix epoch).
     *
     * @param gregTimestamp a number of 100ns since 1582-10-15 (Gregorian epoch)
     * @return a number of 100ns since 1970-01-01 (Unix epoch)
     */
    public static long toUnixTimestamp(final long gregTimestamp) {
        return gregTimestamp + (EPOCH_GREG_SECONDS * TICKS_PER_SECOND);
    }

    /**
     * Converts a number of 100ns since 1970-01-01 (Unix epoch) into a number of
     * 100ns since 1582-10-15 (Gregorian epoch).
     *
     * @param unixTimestamp a number of 100ns since 1970-01-01 (Unix epoch)
     * @return a number of 100ns since 1582-10-15 (Gregorian epoch).
     */
    public static long toGregTimestamp(final long unixTimestamp) {
        return unixTimestamp - (EPOCH_GREG_SECONDS * TICKS_PER_SECOND);
    }

    /**
     * Converts an {@link Instant} into a number of 100ns since 1970-01-01 (Unix
     * epoch).
     *
     * @param instant an instant
     * @return a number of 100ns since 1970-01-01 (Unix epoch).
     */
    public static long toUnixTimestamp(final Instant instant) {
        final long seconds = instant.getEpochSecond() * TICKS_PER_SECOND;
        final long nanos = instant.getNano() / NANOS_PER_TICK;
        return seconds + nanos;
    }

    /**
     * Converts an {@link Instant} into a number of 100ns since 1582-10-15
     * (Gregorian epoch).
     *
     * @param instant an instant
     * @return a number of 100ns since 1582-10-15 (Gregorian epoch).
     */
    public static long toGregTimestamp(final Instant instant) {
        final long seconds = (instant.getEpochSecond() - EPOCH_GREG_SECONDS) * TICKS_PER_SECOND;
        final long nanos = instant.getNano() / NANOS_PER_TICK;
        return seconds + nanos;
    }

    /**
     * Converts a number of 100ns since 1970-01-01 (Unix epoch) into an
     * {@link Instant}.
     *
     * @param unixTimestamp a number of 100ns since 1970-01-01 (Unix epoch)
     * @return an instant
     */
    public static Instant fromUnixTimestamp(final long unixTimestamp) {
        final long seconds = unixTimestamp / TICKS_PER_SECOND;
        final long nanos = (unixTimestamp % TICKS_PER_SECOND) * NANOS_PER_TICK;
        return Instant.ofEpochSecond(seconds, nanos);
    }

    /**
     * Converts a number of 100ns since 1582-10-15 (Gregorian epoch) into an
     * {@link Instant}.
     *
     * @param gregTimestamp a number of 100ns since 1582-10-15 (Gregorian epoch)
     * @return an instant
     */
    public static Instant fromGregTimestamp(final long gregTimestamp) {
        final long seconds = (gregTimestamp / TICKS_PER_SECOND) + EPOCH_GREG_SECONDS;
        final long nanos = (gregTimestamp % TICKS_PER_SECOND) * NANOS_PER_TICK;
        return Instant.ofEpochSecond(seconds, nanos);
    }
}
