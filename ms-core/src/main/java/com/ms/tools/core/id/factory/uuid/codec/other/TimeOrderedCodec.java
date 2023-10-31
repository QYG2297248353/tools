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

package com.ms.tools.core.id.factory.uuid.codec.other;

import com.ms.tools.core.id.factory.uuid.codec.UuidCodec;
import com.ms.tools.core.id.factory.uuid.exception.InvalidUuidException;
import com.ms.tools.core.id.factory.uuid.util.UuidUtil;
import com.ms.tools.core.id.factory.uuid.util.UuidValidator;

import java.util.UUID;

/**
 * Codec for time-ordered UUIDs
 * <p>
 * This codec converts time-based UUIDs (UUIDv1) to time-ordered UUIDs (UUIDv6).
 */
public class TimeOrderedCodec implements UuidCodec<UUID> {

    /**
     * A shared immutable instance.
     */
    public static final TimeOrderedCodec INSTANCE = new TimeOrderedCodec();

    /**
     * Get a time-ordered UUID from a time-based UUID.
     *
     * @param uuid a time-based UUID
     * @return a time-ordered UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID encode(UUID uuid) {

        UuidValidator.validate(uuid);

        if (!UuidUtil.isTimeBased(uuid)) {
            throw new InvalidUuidException("Not a time-based UUID: " + uuid);
        }

        long timestamp = UuidUtil.getTimestamp(uuid);

        long msb = ((timestamp & 0x0ffffffffffff000L) << 4) //
                | (timestamp & 0x0000000000000fffL) //
                | 0x0000000000006000L; // set version 6

        long lsb = uuid.getLeastSignificantBits();

        return new UUID(msb, lsb);
    }

    /**
     * Get a time-based UUID from a time-ordered UUID.
     *
     * @param uuid a time-ordered UUID
     * @return a time-based UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(UUID uuid) {

        UuidValidator.validate(uuid);

        if (!UuidUtil.isTimeOrdered(uuid)) {
            throw new InvalidUuidException("Not a time-ordered UUID: " + uuid);
        }

        long timestamp = UuidUtil.getTimestamp(uuid);

        long msb = ((timestamp & 0x0fff_0000_00000000L) >>> 48) //
                | ((timestamp & 0x0000_ffff_00000000L) >>> 16) //
                | ((timestamp & 0x0000_0000_ffffffffL) << 32) //
                | 0x0000000000001000L; // set version 1

        long lsb = uuid.getLeastSignificantBits();

        return new UUID(msb, lsb);
    }
}
