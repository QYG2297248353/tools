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

package com.ms.tools.core.id.factory.uuid.codec.base.function;

import com.ms.tools.core.id.factory.uuid.codec.base.BaseN;
import com.ms.tools.core.id.factory.uuid.codec.base.BaseNCodec.CustomDivider;

import java.util.UUID;

/**
 * Function that encodes a UUID to a base-n string.
 * <p>
 * It encodes using remainder operator (modulus), a common approach to encode
 * integers.
 * <p>
 * The encoding process is performed using integer arithmetic.
 */
public final class BaseNRemainderEncoder extends BaseNEncoder {

    private static final long MASK = 0x00000000ffffffffL;
    protected final CustomDivider divider;
    private final int length;
    private final char padding;

    public BaseNRemainderEncoder(BaseN base) {
        this(base, null);
    }

    public BaseNRemainderEncoder(BaseN base, CustomDivider divider) {
        super(base);

        length = base.getLength();
        padding = base.getPadding();
        final long radix = base.getRadix();

        if (divider != null) {
            this.divider = divider;
        } else {
            this.divider = x -> new long[]{x / radix, x % radix};
        }
    }

    // divide a long as unsigned 64 bit integer
    protected static long[] divide(final long x, CustomDivider divider, final long rem) {

        long[] div;
        long remainder;
        final long quotient1;
        final long quotient2;

        // divide the first 32 bits
        div = divider.divide((rem << 32) | (x >>> 32));
        quotient1 = div[0];
        remainder = div[1];

        // divide the last 32 bits
        div = divider.divide((remainder << 32) | (x & MASK));
        quotient2 = div[0];
        remainder = div[1];

        // prepare the answer
        final long[] answer = new long[2];
        answer[0] = (quotient1 << 32) | (quotient2 & MASK);
        answer[1] = remainder;

        return answer;
    }

    @Override
    public String apply(UUID uuid) {

        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        int b = length; // buffer index
        char[] buffer = new char[length];

        long rem = 0; // remainder
        long[] ans; // [quotient, remainder]

        // fill in the buffer backwards
        while (msb != 0 || lsb != 0) {
            rem = 0;
            ans = divide(msb, divider, rem);
            msb = ans[0]; // quotient
            rem = ans[1]; // remainder
            ans = divide(lsb, divider, rem);
            lsb = ans[0]; // quotient
            rem = ans[1]; // remainder
            buffer[--b] = alphabet.get((int) rem);
        }

        // complete padding
        while (b > 0) {
            buffer[--b] = padding;
        }

        return new String(buffer);
    }
}
