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

package com.ms.id.factory.uuid.codec.other;

import com.ms.id.factory.uuid.codec.UuidCodec;
import com.ms.id.factory.uuid.exception.InvalidUuidException;
import com.ms.id.factory.uuid.util.UuidUtil;
import com.ms.id.factory.uuid.util.UuidValidator;

import java.util.UUID;

/**
 * Codec for time-based .Net Guids.
 */
public class DotNetGuid1Codec implements UuidCodec<UUID> {

    /**
     * A shared immutable instance.
     */
    public static final DotNetGuid1Codec INSTANCE = new DotNetGuid1Codec();

    /**
     * Convert a UUID to and from a .Net Guid.
     * <p>
     * It rearranges the most significant bytes from big-endian to little-endian,
     * and vice-versa.
     * <p>
     * The .Net Guid stores the most significant bytes as little-endian, while the
     * least significant bytes are stored as big-endian (network order).
     *
     * @param uuid a UUID
     * @return another UUID
     * @see <a href=
     * "https://blogs.msdn.microsoft.com/dbrowne/2012/07/03/how-to-generate-sequential-guids-for-sql-server-in-net/">How
     * to Generate Sequential GUIDs for SQL Server in .NET</a>
     * @see <a href=
     * "http://sqlblog.com/blogs/alberto_ferrari/archive/2007/08/31/how-are-guids-sorted-by-sql-server.aspx">How
     * are GUIDs sorted by SQL Server?</a>
     */
    protected static UUID toAndFromDotNetGuid(UUID uuid) {

        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        long newMsb = 0x0000000000000000L;
        // high bits
        newMsb |= (msb & 0xff000000_0000_0000L) >>> 24;
        newMsb |= (msb & 0x00ff0000_0000_0000L) >>> 8;
        newMsb |= (msb & 0x0000ff00_0000_0000L) << 8;
        newMsb |= (msb & 0x000000ff_0000_0000L) << 24;
        // mid bits
        newMsb |= (msb & 0x00000000_ff00_0000L) >>> 8;
        newMsb |= (msb & 0x00000000_00ff_0000L) << 8;
        // low bits
        newMsb |= (msb & 0x00000000_0000_ff00L) >>> 8;
        newMsb |= (msb & 0x00000000_0000_00ffL) << 8;

        return new UUID(newMsb, lsb);
    }

    /**
     * Get a .Ned Guid from a time-based UUID (v1).
     * <p>
     * This codec converts a time-based UUID (v1) to a .Net Guid.
     * <p>
     * It rearranges the most significant bytes from big-endian to little-endian,
     * and vice-versa.
     * <p>
     * The .Net Guid stores the most significant bytes as little-endian, while the
     * least significant bytes are stored as big-endian (network order).
     *
     * @param uuid a UUID
     * @return another UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID encode(UUID uuid) {
        UuidValidator.validate(uuid);
        if (!UuidUtil.isTimeBased(uuid)) {
            throw new InvalidUuidException(String.format("Not a time-based UUID: %s.", uuid.toString()));
        }
        return toAndFromDotNetGuid(uuid);
    }

    /**
     * Get a time-based UUID (v4) from a .Net Guid.
     * <p>
     * It rearranges the most significant bytes from big-endian to little-endian,
     * and vice-versa.
     * <p>
     * The .Net Guid stores the most significant bytes as little-endian, while the
     * least significant bytes are stored as big-endian (network order).
     *
     * @param uuid a UUID
     * @return another UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(UUID uuid) {
        UuidValidator.validate(uuid);
        UUID uuidv1 = toAndFromDotNetGuid(uuid);
        if (!UuidUtil.isTimeBased(uuidv1)) {
            throw new InvalidUuidException(String.format("Not a time-based UUID: %s.", uuidv1.toString()));
        }
        return uuidv1;
    }
}
