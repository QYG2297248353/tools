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

import static com.ms.tools.core.id.factory.uuid.codec.other.DotNetGuid1Codec.toAndFromDotNetGuid;

/**
 * Codec for random-based .Net Guids.
 */
public class DotNetGuid4Codec implements UuidCodec<UUID> {

    /**
     * A shared immutable instance.
     */
    public static final DotNetGuid4Codec INSTANCE = new DotNetGuid4Codec();

    /**
     * Get a .Ned Guid from a random-based UUID (v4).
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
        if (!UuidUtil.isRandomBased(uuid)) {
            throw new InvalidUuidException(String.format("Not a random-based UUID: %s.", uuid.toString()));
        }
        return toAndFromDotNetGuid(uuid);
    }

    /**
     * Get a random-based UUID (v4) from a .Net Guid.
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
        UUID uuidv4 = toAndFromDotNetGuid(uuid);
        if (!UuidUtil.isRandomBased(uuidv4)) {
            throw new InvalidUuidException(String.format("Not a random-based UUID: %s.", uuidv4.toString()));
        }
        return uuidv4;
    }
}
