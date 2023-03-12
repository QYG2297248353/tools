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

package com.ms.id.factory.uuid.codec.base;

/**
 * Codec for base-58.
 * <p>
 * It is case SENSITIVE.
 * <p>
 * It encodes using remainder operator (modulus).
 * <p>
 * The alphabet for this codec is the same used in Bitcoin (BTC).
 *
 * @see <a href="https://tools.ietf.org/html/draft-msporny-base58-03">The Base58 Encoding Scheme</a>
 */
public final class Base58BtcCodec extends BaseNCodec {

    // a shared immutable instance
    public static final Base58BtcCodec INSTANCE = new Base58BtcCodec();
    private static final BaseN BASE_N = new BaseN("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz");

    public Base58BtcCodec() {
        super(BASE_N);
    }
}
