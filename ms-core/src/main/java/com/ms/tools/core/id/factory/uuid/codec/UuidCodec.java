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

package com.ms.tools.core.id.factory.uuid.codec;

import com.ms.tools.core.id.factory.uuid.exception.InvalidUuidException;

import java.util.UUID;

/**
 * Interface to be implemented by all codecs of this package.
 * <p>
 * All implementations of this interface throw {@link InvalidUuidException} if
 * an invalid argument argument is given.
 * <p>
 * The {@link RuntimeException} cases that can be detected beforehand are
 * translated into an {@link InvalidUuidException}.
 *
 * @param <T> the type encoded to and decoded from.
 * @see InvalidUuidException
 */
public interface UuidCodec<T> {

    /**
     * Get a generic type from a UUID.
     *
     * @param uuid a UUID
     * @return a generic type
     * @throws InvalidUuidException if the argument is invalid
     */
    public T encode(UUID uuid);

    /**
     * Get a UUID from a generic type.
     *
     * @param type a generic type
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    public UUID decode(T type);
}
