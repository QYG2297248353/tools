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
import com.ms.tools.core.id.factory.uuid.util.UuidValidator;

import java.util.UUID;

/**
 * Codec for UUID URNs.
 * <p>
 * {@link UrnCodec} encodes UUID to and from an URN.
 * <p>
 * The URN representation adds the prefix 'urn:uuid:' to a UUID canonical
 * representation.
 * <p>
 *
 * @see InvalidUuidException
 * @see <a href= "https://github.com/f4b6a3/uuid-creator/issues/32">UUID
 * URIs</a>
 * @see <a href=
 * "https://github.com/f4b6a3/uuid-creator/issues/66">UriCodec.isUuidUrn(java.net.URI
 * uri)</a>
 * @see <a href=
 * "https://stackoverflow.com/questions/4913343/what-is-the-difference-between-uri-url-and-urn">What
 * is the difference between URI, URL and URN?</a>
 */
public class UrnCodec implements UuidCodec<String> {

    /**
     * A shared immutable instance.
     */
    public static final UrnCodec INSTANCE = new UrnCodec();

    private static final String URN_PREFIX = "urn:uuid:";

    /**
     * Check if a URN string is a UUID URN.
     *
     * @param urn a string
     * @return true if the it's a URN
     */
    public static boolean isUuidUrn(String urn) {
        final int stringLength = 45; // URN string length
        final int prefixLength = 9; // URN prefix length
        if (urn != null && urn.length() == stringLength) {
            String uuid = urn.substring(prefixLength);
            return UuidValidator.isValid(uuid);
        }
        return false;
    }

    /**
     * Get a URN string from a UUID.
     *
     * @param uuid a UUID
     * @return a URN string
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public String encode(UUID uuid) {
        UuidValidator.validate(uuid);
        return URN_PREFIX + StringCodec.INSTANCE.encode(uuid);
    }

    /**
     * Get a UUID from a URN string.
     *
     * @param urn a URN string
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(String urn) {
        if (!isUuidUrn(urn)) {
            throw InvalidUuidException.newInstance(urn);
        }
        return StringCodec.INSTANCE.decode(urn);
    }
}
