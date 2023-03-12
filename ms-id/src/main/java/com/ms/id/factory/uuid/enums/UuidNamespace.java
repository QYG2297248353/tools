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

package com.ms.id.factory.uuid.enums;

import java.util.UUID;

/**
 * Name spaces defined by RFC-4122, used to create name-based unique identifiers
 * (UUIDv3 and UUIDv5).
 * <p>
 * List of name spaces:
 * <ul>
 * <li>{@link NAMESPACE_DNS}: 6ba7b810-9dad-11d1-80b4-00c04fd430c8
 * <li>{@link NAMESPACE_URL}: 6ba7b811-9dad-11d1-80b4-00c04fd430c8
 * <li>{@link NAMESPACE_OID}: 6ba7b812-9dad-11d1-80b4-00c04fd430c8
 * <li>{@link NAMESPACE_X500}: 6ba7b814-9dad-11d1-80b4-00c04fd430c8
 * </ul>
 *
 * @see <a href= "https://tools.ietf.org/html/rfc4122#appendix-C">RFC-4122 -
 * Appendix C - Some Name Space IDs</a>
 */
public enum UuidNamespace {

    /**
     * Name space to be used when the name string is a fully-qualified domain name.
     */
    NAMESPACE_DNS(new UUID(0x6ba7b8109dad11d1L, 0x80b400c04fd430c8L)),
    /**
     * Name space to be used when the name string is a URL.
     */
    NAMESPACE_URL(new UUID(0x6ba7b8119dad11d1L, 0x80b400c04fd430c8L)),
    /**
     * Name space to be used when the name string is an ISO OID.
     */
    NAMESPACE_OID(new UUID(0x6ba7b8129dad11d1L, 0x80b400c04fd430c8L)),
    /**
     * Name space to be used when the name string is an X.500 DN (DER or text).
     */
    NAMESPACE_X500(new UUID(0x6ba7b8149dad11d1L, 0x80b400c04fd430c8L));

    private final UUID value;

    UuidNamespace(UUID value) {
        this.value = value;
    }

    public static UuidNamespace getNamespace(UUID value) {
        for (UuidNamespace namespace : UuidNamespace.values()) {
            if (namespace.getValue().equals(value)) {
                return namespace;
            }
        }
        return null;
    }

    public UUID getValue() {
        return this.value;
    }
}
