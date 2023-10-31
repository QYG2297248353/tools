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

import java.net.URI;
import java.util.UUID;

/**
 * UUID URI（特别是 URN）的编解码器。
 * UriCodec将 UUID 编码到不透明的 URI 和从不透明的URI编码。
 * URN 表示将前缀“urn:uuid:”添加到 UUID 规范表示中。
 * 见：https://github.com/f4b6a3/uuid-creator/issues/32
 *
 * @see InvalidUuidException
 * @see <a href=
 * "https://github.com/f4b6a3/uuid-creator/issues/32">UUID URIs</a>
 * @see <a href=
 * "https://github.com/f4b6a3/uuid-creator/issues/66">UriCodec.isUuidUrn(java.net.URI
 * uri)</a>
 * @see <a href=
 * "https://stackoverflow.com/questions/4913343/what-is-the-difference-between-uri-url-and-urn">What
 * is the difference between URI, URL and URN?</a>
 */
public class UriCodec implements UuidCodec<URI> {

    /**
     * A shared immutable instance.
     */
    public static final UriCodec INSTANCE = new UriCodec();

    /**
     * Check if the URI is a UUID URN.
     *
     * @param uri a URI
     * @return true if the it's a URN
     */
    public static boolean isUuidUri(URI uri) {
        return uri != null && UrnCodec.isUuidUrn(uri.toString());
    }

    /**
     * Get a URI from a UUID.
     *
     * @param uuid a UUID
     * @return a URI
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public URI encode(UUID uuid) {
        return URI.create(UrnCodec.INSTANCE.encode(uuid));
    }

    /**
     * Get a UUID from a URI.
     *
     * @param uri a URI
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(URI uri) {
        if (!isUuidUri(uri)) {
            throw InvalidUuidException.newInstance(uri);
        }
        return StringCodec.INSTANCE.decode(uri.toString());
    }
}
