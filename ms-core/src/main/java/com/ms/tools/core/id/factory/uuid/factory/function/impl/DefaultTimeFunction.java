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

package com.ms.tools.core.id.factory.uuid.factory.function.impl;

import com.ms.tools.core.id.factory.uuid.factory.function.TimeFunction;
import com.ms.tools.core.id.factory.uuid.util.internal.RandomUtil;

import java.time.Clock;

import static com.ms.tools.core.id.factory.uuid.util.UuidTime.TICKS_PER_MILLI;

/**
 * Function that returns a number of 100-nanoseconds since 1970-01-01 (Unix
 * epoch).
 * <p>
 * It can advance up to 1ms ahead of system time.
 *
 * @see TimeFunction
 */
public final class DefaultTimeFunction implements TimeFunction {

    private final Clock clock;

    private long lastTime = -1;

    // start the counter with a random number between 0 and 9,999
    private long counter = Math.abs(RandomUtil.nextLong() % TICKS_PER_MILLI);
    // start the counter limit with a number between 10,000 and 19,999
    private long counterMax = counter + TICKS_PER_MILLI;

    public DefaultTimeFunction() {
        this.clock = Clock.systemUTC();
    }

    public DefaultTimeFunction(Clock clock) {
        this.clock = clock;
    }

    @Override
    public long getAsLong() {

        counter++; // always increment

        // get the current time
        long time = clock.millis();

        // check time change
        if (time == lastTime) {
            // if the time repeats,
            // check the counter limit
            if (counter >= counterMax) {
                // if the counter goes beyond the limit,
                while (time == lastTime) {
                    // wait the time to advance
                    time = clock.millis();
                }
                // reset to a number between 0 and 9,999
                counter = counter % TICKS_PER_MILLI;
                // reset to a number between 10,000 and 19,999
                counterMax = counter + TICKS_PER_MILLI;
            }
        } else {
            // reset to a number between 0 and 9,999
            counter = counter % TICKS_PER_MILLI;
            // reset to a number between 10,000 and 19,999
            counterMax = counter + TICKS_PER_MILLI;
        }

        // save time for the next call
        lastTime = time;

        // RFC-4122 - 4.2.1.2 (P4):
        // simulate a high resolution clock
        return (time * TICKS_PER_MILLI) + counter;
    }
}
