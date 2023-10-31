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

import com.ms.tools.core.id.factory.uuid.factory.function.ClockSeqFunction;

import java.util.concurrent.atomic.AtomicInteger;

import static com.ms.tools.core.id.factory.uuid.factory.function.ClockSeqFunction.ClockSeqPool.POOL_MAX;
import static com.ms.tools.core.id.factory.uuid.factory.function.ClockSeqFunction.ClockSeqPool.POOL_MIN;

/**
 * Function that returns a clock sequence.
 *
 * @see ClockSeqFunction
 * @see ClockSeqPool
 */
public final class DefaultClockSeqFunction implements ClockSeqFunction {

    protected static final ClockSeqPool POOL = new ClockSeqPool();
    private AtomicInteger sequence;
    private long lastTimestamp = -1;

    public DefaultClockSeqFunction() {
        int initial = POOL.random();
        sequence = new AtomicInteger(initial);
    }

    @Override
    public long applyAsLong(long timestamp) {
        if (timestamp > lastTimestamp) {
            lastTimestamp = timestamp;
            return sequence.get();
        }
        lastTimestamp = timestamp;
        return next();
    }

    public int next() {
        if (sequence.incrementAndGet() > POOL_MAX) {
            sequence.set(POOL_MIN);
        }
        return sequence.updateAndGet(POOL::take);
    }
}
