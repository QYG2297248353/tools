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

package com.ms.tools.core.id.factory.uuid.enums;

/**
 * Local domains defined by DCE 1.1 specification, used to create DCE Security
 * unique identifiers (UUIDv2).
 * <p>
 * List of local domains:
 * <ul>
 * <li>{@link LOCAL_DOMAIN_PERSON}: 0
 * <li>{@link LOCAL_DOMAIN_GROUP}: 1
 * <li>{@link LOCAL_DOMAIN_ORG}: 2
 * </ul>
 * <p>
 * On POSIX systems, local-domain numbers 0 and 1 are for user identifiers
 * (UIDs) and group identifiers (GIDs) respectively, and other local-domain
 * numbers are site-defined.
 * <p>
 * On non-POSIX systems, all local domain numbers are site-defined.
 *
 * @see <a href=
 * "https://pubs.opengroup.org/onlinepubs/9696989899/chap11.htm#tagcjh_14_05_01_01">DCE
 * Domain names</a>
 * @see <a href="https://en.wikipedia.org/wiki/User_identifier">User
 * identifier</a>
 * @see <a href="https://en.wikipedia.org/wiki/Group_identifier">Group
 * identifier</a>
 */
public enum UuidLocalDomain {

    /**
     * The principal domain, interpreted as POSIX UID domain on POSIX systems.
     */
    LOCAL_DOMAIN_PERSON((byte) 0),
    /**
     * The group domain, interpreted as POSIX GID domain on POSIX systems.
     */
    LOCAL_DOMAIN_GROUP((byte) 1),
    /**
     * The organization domain, site-defined.
     */
    LOCAL_DOMAIN_ORG((byte) 2);

    private final byte value;

    UuidLocalDomain(byte value) {
        this.value = value;
    }

    public static UuidLocalDomain getLocalDomain(byte value) {
        for (UuidLocalDomain domain : UuidLocalDomain.values()) {
            if (domain.getValue() == value) {
                return domain;
            }
        }
        return null;
    }

    public byte getValue() {
        return this.value;
    }
}
