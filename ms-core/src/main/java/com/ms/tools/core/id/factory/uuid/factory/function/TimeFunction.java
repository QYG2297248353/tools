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

import com.ms.tools.core.id.factory.uuid.util.UuidTime;

import java.time.Instant;
import java.util.function.LongSupplier;

/**
 * Function that must return a number of 100-nanoseconds since 1970-01-01 (Unix
 * epoch).
 * <p>
 * Example:
 *
 * <pre>{@code
 * // A function that returns `Instant.now()` as a number of 100ns
 * TimeFunction f = () -> TimeFunction.toUnixTimestamp(Instant.now());
 * }</pre>
 *
 * <p>
 * In JDK 8, {@link Instant#now()} has millisecond precision, in spite of
 * {@link Instant} has nanoseconds resolution. In JDK 9+, {@link Instant#now()}
 * has microsecond precision.
 *
 * @see <a href="https://stackoverflow.com/questions/1712205">Current time in
 * microseconds in java</a>
 * @see <a href="https://bugs.openjdk.java.net/browse/JDK-8068730">Increase the
 * precision of the implementation of java.time.Clock.systemUTC()</a>
 */
@FunctionalInterface
public interface TimeFunction extends LongSupplier {

    /**
     * Converts an instant to a number of 100-nanoseconds since 1970-01-01 (Unix
     * epoch).
     *
     * @param instant an instant
     * @return a number of 100-nanoseconds since 1970-01-01 (Unix epoch)
     */
    public static long toUnixTimestamp(final Instant instant) {
        return UuidTime.toUnixTimestamp(instant);
    }

    /**
     * Clears the leading bits so that the resulting number is in the range 0 to
     * 2^60-1.
     * <p>
     * The result is equivalent to {@code n % 2^60}.
     *
     * @param timestamp a number of 100-nanoseconds since 1970-01-01 (Unix epoch)
     * @return a number in the range 0 to 2^60-1.
     */
    public static long toExpectedRange(final long timestamp) {
        return timestamp & 0x0_fffffffffffffffL;
    }
}
