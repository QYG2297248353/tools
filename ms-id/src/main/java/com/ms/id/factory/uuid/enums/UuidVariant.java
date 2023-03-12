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

/**
 * UUID variants defined by RFC-4122.
 * <p>
 * List of variants:
 * <ul>
 * <li>{@link VARIANT_RESERVED_NCS}: 0
 * <li>{@link VARIANT_RFC_4122}: 2
 * <li>{@link VARIANT_RESERVED_MICROSOFT}: 6
 * <li>{@link VARIANT_RESERVED_FUTURE}: 7
 * </ul>
 */
public enum UuidVariant {

    /**
     * Reserved for NCS backward compatibility.
     */
    VARIANT_RESERVED_NCS(0),
    /**
     * The variant specified in RFC-4122.
     */
    VARIANT_RFC_4122(2),
    /**
     * Reserved for Microsoft Corporation backward compatibility.
     */
    VARIANT_RESERVED_MICROSOFT(6),
    /**
     * Reserved for future definition.
     */
    VARIANT_RESERVED_FUTURE(7);

    private final int value;

    UuidVariant(int value) {
        this.value = value;
    }

    public static UuidVariant getVariant(int value) {
        for (UuidVariant variant : UuidVariant.values()) {
            if (variant.getValue() == value) {
                return variant;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }
}
