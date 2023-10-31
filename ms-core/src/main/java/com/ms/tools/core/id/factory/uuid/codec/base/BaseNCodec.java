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

package com.ms.tools.core.id.factory.uuid.codec.base;

import com.ms.tools.core.id.factory.uuid.codec.UuidCodec;
import com.ms.tools.core.id.factory.uuid.codec.base.function.BaseNDecoder;
import com.ms.tools.core.id.factory.uuid.codec.base.function.BaseNEncoder;
import com.ms.tools.core.id.factory.uuid.codec.base.function.BaseNRemainderDecoder;
import com.ms.tools.core.id.factory.uuid.codec.base.function.BaseNRemainderEncoder;
import com.ms.tools.core.id.factory.uuid.exception.InvalidUuidException;
import com.ms.tools.core.id.factory.uuid.util.UuidValidator;

import java.util.UUID;
import java.util.function.Function;

/**
 * Abstract class that contains the basic functionality for base-n codecs of
 * this package.
 */
public abstract class BaseNCodec implements UuidCodec<String> {

    protected final BaseN base;

    protected final Function<UUID, String> encoder;
    protected final Function<String, UUID> decoder;

    /**
     * @param base an object that represents the base-n encoding
     */
    protected BaseNCodec(BaseN base) {
        this(base, null);
    }

    /**
     * @param base    an object that represents the base-n encoding
     * @param divider a division function that returns quotient and remainder
     */
    protected BaseNCodec(BaseN base, CustomDivider divider) {
        this(base, new BaseNRemainderEncoder(base, divider), new BaseNRemainderDecoder(base));
    }

    /**
     * @param base    an object that represents the base-n encoding
     * @param encoder a functional encoder
     * @param decoder a functional decoder
     */
    protected BaseNCodec(BaseN base, BaseNEncoder encoder, BaseNDecoder decoder) {
        this.base = base;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    /**
     * Static factory that returns a new instance of {@link BaseNCodec} using the
     * specified {@link BaseN}.
     * <p>
     * This method can be used if none of the existing concrete codecs of this
     * package class is desired.
     * <p>
     * The {@link BaseNCodec} objects provided by this method encode UUIDs using
     * remainder operation (modulus), a common approach to encode integers.
     * <p>
     * If you need a {@link BaseN} that is not available in this package, use the
     * static factories {@link BaseNCodec#newInstance(String)} or
     * {@link BaseNCodec#newInstance(int)}.
     *
     * @param base an object that represents the base-n encoding
     * @return a {@link BaseNCodec}
     */
    public static BaseNCodec newInstance(BaseN base) {
        return newInstance(base, null);
    }

    /**
     * Static factory that returns a new instance of {@link BaseNCodec} using the
     * specified radix.
     * <p>
     * This method can be used if none of the existing concrete codecs of this
     * package class is desired.
     * <p>
     * The {@link BaseNCodec} objects provided by this method encode UUIDs using
     * remainder operator (modulus), a common approach to encode integers.
     * <p>
     * The example below shows how to create a {@link BaseNCodec} for an
     * hypothetical base-40 encoding that contains only letters. You only need to
     * pass a number 40. The {@link BaseNCodec} instantiates a {@link BaseN} object
     * internally. See {@link BaseN}.
     *
     * <pre>{@code
     * String radix = 40;
     * BaseNCodec codec = BaseNCodec.newInstance(radix);
     * }</pre>
     *
     * <p>
     * If radix is greater than 36, the alphabet generated is a subset of the
     * character sequence "0-9A-Za-z-_". Otherwise it is a subset of "0-9a-z". In
     * the example above the resulting alphabet is
     * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcd" (0-9A-Za-d).
     *
     * @param radix the radix to be used
     * @return a {@link BaseNCodec}
     */
    public static BaseNCodec newInstance(int radix) {
        return newInstance(radix, null);
    }

    /**
     * Static factory that returns a new instance of {@link BaseNCodec} using the
     * specified alphabet.
     * <p>
     * This method can be used if none of the existing concrete codecs of this
     * package class is desired.
     * <p>
     * The {@link BaseNCodec} objects provided by this method encode UUIDs using
     * remainder operator (modulus), a common approach to encode integers.
     * <p>
     * The example below shows how to create a {@link BaseNCodec} for an
     * hypothetical base-26 encoding that contains only letters. You only need to
     * pass a string with 26 characters. The {@link BaseNCodec} instantiates a
     * {@link BaseN} object internally. See {@link BaseN}.
     *
     * <pre>{@code
     * String alphabet = "abcdefghijklmnopqrstuvwxyz";
     * BaseNCodec codec = BaseNCodec.newInstance(alphabet);
     * }</pre>
     *
     * <p>
     * Alphabet strings similar to "a-f0-9" are expanded to "abcdef0123456789". The
     * same example using the string "a-z" instead of "abcdefghijklmnopqrstuvwxyz":
     *
     * <pre>{@code
     * String alphabet = "a-z";
     * BaseNCodec codec = BaseNCodec.newInstance(alphabet);
     * }</pre>
     *
     * @param alphabet the alphabet to be used
     * @return a {@link BaseNCodec}
     */
    public static BaseNCodec newInstance(String alphabet) {
        return newInstance(alphabet, null);
    }

    /**
     * Static factory that returns a new instance of {@link BaseNCodec} using the
     * specified {@link BaseN} and a {@link CustomDivider}.
     *
     * @param base    an object that represents the base-n encoding
     * @param divider a division function that returns quotient and remainder
     * @return a {@link BaseNCodec}
     */
    public static BaseNCodec newInstance(BaseN base, CustomDivider divider) {
        return new BaseNCodec(base, divider) {
        };
    }

    /**
     * Static factory that returns a new instance of {@link BaseNCodec} using the
     * specified radix and a {@link CustomDivider}.
     *
     * @param radix   the radix to be used
     * @param divider a division function that returns quotient and remainder
     * @return a {@link BaseNCodec}
     */
    public static BaseNCodec newInstance(int radix, CustomDivider divider) {
        BaseN base = new BaseN(radix);
        return newInstance(base, divider);
    }

    /**
     * Static factory that returns a new instance of {@link BaseNCodec} using the
     * specified alphabet and a {@link CustomDivider}.
     *
     * @param alphabet the alphabet to be used
     * @param divider  a division function that returns quotient and remainder
     * @return a {@link BaseNCodec}
     */
    public static BaseNCodec newInstance(String alphabet, CustomDivider divider) {
        BaseN base = new BaseN(alphabet);
        return newInstance(base, divider);
    }

    /**
     * Get the base-n encoding object.
     *
     * @return a base-n encoding object
     */
    public BaseN getBase() {
        return base;
    }

    /**
     * Get an encoded string from a UUID.
     *
     * @param uuid a UUID
     * @return an encoded string
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public String encode(UUID uuid) {
        try {
            UuidValidator.validate(uuid);
            return encoder.apply(uuid);
        } catch (RuntimeException e) {
            throw new InvalidUuidException(e.getMessage(), e);
        }
    }

    /**
     * Get a UUID from an encoded string.
     *
     * @param string the encoded string
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(String string) {
        try {
            base.validate(string);
            return decoder.apply(string);
        } catch (RuntimeException e) {
            throw new InvalidUuidException(e.getMessage(), e);
        }
    }

    /**
     * A division function that returns quotient and remainder.
     * <p>
     * It MUST perform SIGNED long division.
     * <p>
     * Example:
     *
     * <pre>{@code
     *
     * CustomDivider divideBy64 = x -> new long[] { x / 64, x % 64 };
     *
     * long[] answer = divideBy64(1024);
     *
     * }</pre>
     */
    @FunctionalInterface
    public static interface CustomDivider {
        // [x / divider, x % divider]
        public long[] divide(long x);
    }
}
