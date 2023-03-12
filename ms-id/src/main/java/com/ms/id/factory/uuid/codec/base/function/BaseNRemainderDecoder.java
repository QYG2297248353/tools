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

package com.ms.id.factory.uuid.codec.base.function;

import com.ms.id.factory.uuid.codec.base.BaseN;
import com.ms.id.factory.uuid.exception.InvalidUuidException;

import java.util.UUID;

/**
 * Function that decodes a base-n string to a UUID.
 * <p>
 * It decodes strings created by encoders that use remainder operator (modulus),
 * a common approach to encode integers.
 * <p>
 * The decoding process is performed using integer arithmetic.
 */
public final class BaseNRemainderDecoder extends BaseNDecoder {

    private static final long MASK = 0x00000000ffffffffL;
    private final int multiplier;

    public BaseNRemainderDecoder(BaseN base) {
        super(base);
        multiplier = base.getRadix();
    }

    // multiply a long as unsigned 64 bit integer
    protected static long[] multiply(final long x, final long multiplier, final long rem) {

        long mul;
        long overflow;
        final long product1;
        final long product2;

        // multiply the last 32 bits
        mul = ((x & MASK) * multiplier) + rem;
        product1 = mul & MASK;
        overflow = mul >>> 32;

        // multiply the first 32 bits
        mul = ((x >>> 32) * multiplier) + overflow;
        product2 = mul & MASK;
        overflow = mul >>> 32;

        // prepare the answer
        final long[] answer = new long[2];
        answer[0] = (product2 << 32) | (product1 & MASK);
        answer[1] = overflow;

        return answer;
    }

    public UUID apply(String string) {

        final char[] chars = string.toCharArray();

        long msb = 0;
        long lsb = 0;

        long rem = 0; // remainder
        long[] ans; // [product, overflow]

        for (int i = 0; i < chars.length; i++) {
            rem = (int) map.get(chars[i]);
            ans = multiply(lsb, multiplier, rem);
            lsb = ans[0];
            rem = ans[1];
            ans = multiply(msb, multiplier, rem);
            msb = ans[0];
            rem = ans[1];
        }

        if (rem != 0) {
            throw new InvalidUuidException("Invalid encoded string (overflow): \"" + string + "\"");
        }

        return new UUID(msb, lsb);
    }
}
