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

import com.ms.id.factory.uuid.factory.function.TimeFunction;
import com.ms.id.factory.uuid.util.internal.RandomUtil;

import java.time.Clock;

import static com.ms.id.factory.uuid.util.UuidTime.TICKS_PER_MILLI;

/**
 * Function that returns a number of 100-nanoseconds since 1970-01-01 (Unix
 * epoch).
 * <p>
 * This function is for WINDOWS systems.
 * <p>
 * On WINDOWS, the typical system time granularity is 15.625ms due to a default
 * 64Hz timer frequency.
 * <p>
 * It can advance be up to 48ms ahead of system time.
 *
 * @see TimeFunction
 */
public final class WindowsTimeFunction implements TimeFunction {

    // arbitrary granularity greater than 15ms
    private static final long GRANULARITY = 16;
    private static final long TICKS_PER_GRANULARITY = TICKS_PER_MILLI * GRANULARITY;
    private final Clock clock;
    private long lastTime = -1;
    // start the counter with a random number between 0 and 159,999
    private long counter = Math.abs(RandomUtil.nextLong() % TICKS_PER_GRANULARITY);
    // start the counter limit with a number between 160,000 and 319,999
    private long counterMax = counter + TICKS_PER_GRANULARITY;

    public WindowsTimeFunction() {
        this.clock = Clock.systemUTC();
    }

    public WindowsTimeFunction(Clock clock) {
        this.clock = clock;
    }

    @Override
    public long getAsLong() {

        counter++; // always increment

        // get the calculated time
        long time = calculatedMillis();

        // check time change
        if (time == lastTime) {
            // if the time repeats,
            // check the counter limit
            if (counter >= counterMax) {
                // if the counter goes beyond the limit,
                while (time == lastTime) {
                    // wait the time to advance
                    time = calculatedMillis();
                }
                // reset to a number between 0 and 159,999
                counter = counter % TICKS_PER_GRANULARITY;
                // reset to a number between 160,000 and 319,999
                counterMax = counter + TICKS_PER_GRANULARITY;
            }
        } else {
            // reset to a number between 0 and 159,999
            counter = counter % TICKS_PER_GRANULARITY;
            // reset to a number between 160,000 and 319,999
            counterMax = counter + TICKS_PER_GRANULARITY;
        }

        // save time for the next call
        lastTime = time;

        // RFC-4122 - 4.2.1.2 (P4):
        // simulate a high resolution clock
        return (time * TICKS_PER_MILLI) + counter;
    }

    /**
     * Returns the calculated time in milliseconds.
     * <p>
     * It can be 16ms ahead of system time due to time granularity.
     *
     * @return the calculated time
     */
    private long calculatedMillis() {
        final long time = clock.millis();
        return time + GRANULARITY - (time % GRANULARITY);
    }
}
