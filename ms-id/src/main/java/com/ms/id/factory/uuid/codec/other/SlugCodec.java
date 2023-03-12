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

package com.ms.id.factory.uuid.codec.other;

import com.ms.id.factory.uuid.codec.UuidCodec;
import com.ms.id.factory.uuid.codec.base.Base32Codec;
import com.ms.id.factory.uuid.codec.base.Base64UrlCodec;
import com.ms.id.factory.uuid.codec.base.BaseNCodec;
import com.ms.id.factory.uuid.exception.InvalidUuidException;
import com.ms.id.factory.uuid.util.UuidValidator;

import java.util.UUID;

/**
 * Codec for UUID Slugs.
 * <p>
 * A UUID Slug is a shorter string representation that can be safely included in
 * URLs and file names.
 * <p>
 * The {@link SlugCodec} turns a UUID into a string that does not start with
 * digits (0-9). Due to the default base-64-url alphabet, it is <b>case
 * sensitive</b> and may contain '-' and '_'.
 * <p>
 * The {@link Base32Codec} can be passed to the {@link SlugCodec} constructor to
 * generate base-32 slugs. Due to the base-32 alphabet, it is case insensitive
 * and it contains only letters (a-zA-Z) and digits (2-7). This encoding
 * substitution can be done to avoid the characters '-' and '_' of the
 * base-64-url encoding, but it makes the slug case insensitive.
 * <p>
 * To turn a UUID into a slug, the version and variant nibbles are are moved to
 * the first position of the UUID byte array. The slugs generated of the same
 * UUID version show a constant letter in the first position of the base-64-url
 * string.
 * <p>
 * This is how the UUID bits are rearranged:
 *
 * <pre>{@code
 *   aaaaaaaa-bbbb-Vccc-Rddd-eeeeeeeeeeee
 *                 |    |            ^
 *   ,-------------'    |   encode   |
 *   |,-----------------'      |   decode
 *   ||                        v
 *   VRaaaaaa-aabb-bbcc-cddd-eeeeeeeeeeee
 *               shift >>|
 *
 *   V: version nibble or character
 *   R: variant nibble or character
 * }</pre>
 *
 * <p>
 * This table shows the slug prefixes for each UUID version:
 *
 * <pre>
 * VERSON  PREFIX   EXAMPLE
 *    1       G     GxA1e7vco3Ib6_mjtptP3w
 *    2       K     KryezRARVgTHLQ3zJpAXIw
 *    3       O     O9JfSS1IqIabkEWC-uXWNA
 *    4       S     S5iPSZYDt7q2w0qiIFZVwQ
 *    5       W     WY-Uv6WAY5os7Gfv4ILnvQ
 *    6       a     aMKkEoaymw0FSQNJRDL7Gw
 * </pre>
 *
 * <p>
 * If you don't like the change in the bytes layout before the encoding to
 * base-64-url, use the {@link Base64UrlCodec} instead of {@link SlugCodec} to
 * generate slugs.
 * <p>
 * {@link SlugCodec} and {@link NcnameCodec} are very similar. The difference
 * between the two is the bit shift they do with the original UUID to transform
 * it into a string.
 * <p>
 * In the case someone is interested in implementing this type of slug in
 * another language, the change in the bytes layout don't have to be done with
 * bit shifting. Since a base-16 character corresponds to a nibble, the layout
 * change could be easily done by moving characters instead of by shifting bits.
 * See {@code SlugCodecTest#moveCharacters()}.
 *
 * @see <a href="https://github.com/f4b6a3/uuid-creator/issues/30">UUID
 * Slugs</a>
 */
public final class SlugCodec implements UuidCodec<String> {

    /**
     * A shared immutable instance using `base64url`
     */
    public static final SlugCodec INSTANCE = new SlugCodec();

    private final int length;
    private final BaseNCodec codec;

    public SlugCodec() {
        this(Base64UrlCodec.INSTANCE);
    }

    /**
     * @param codec a base-n codec to be used (the default is base-64-url)
     */
    public SlugCodec(BaseNCodec codec) {
        if (codec == null) {
            throw new IllegalArgumentException("Null codec");
        }
        this.codec = codec;
        this.length = codec.getBase().getLength();
    }

    /**
     * Get a Slug from a UUID.
     *
     * @param uuid a UUID
     * @return a Slug
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public String encode(UUID uuid) {

        UuidValidator.validate(uuid);

        long long1 = uuid.getMostSignificantBits();
        long long2 = uuid.getLeastSignificantBits();

        long msb = 0;
        long lsb = 0;

        msb |= (long1 & 0x000000000000f000L) << 48; // move version nibble to bit positions 0, 1, 2, and 3
        msb |= (long2 & 0xf000000000000000L) >>> 4; // move variant nibble to bit positions 4, 5, 6, and 7
        msb |= (long1 & 0xffffffffffff0000L) >>> 8;
        msb |= (long1 & 0x0000000000000fffL) >>> 4;

        lsb |= (long1 & 0x000000000000000fL) << 60;
        lsb |= (long2 & 0x0fffffffffffffffL);

        return this.codec.encode(new UUID(msb, lsb));
    }

    /**
     * Get a UUID from a Slug.
     *
     * @param slug a Slug
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(String slug) {

        if (slug == null || slug.length() != this.length) {
            throw new InvalidUuidException("Invalid UUID Slug: \"" + slug + "\"");
        }

        UUID uuid = this.codec.decode(slug);

        long long1 = uuid.getMostSignificantBits();
        long long2 = uuid.getLeastSignificantBits();

        long msb = 0;
        long lsb = 0;

        msb |= (long1 & 0xf000000000000000L) >>> 48; // move version nibble to its original position
        msb |= (long2 & 0xf000000000000000L) >>> 60; // move variant nibble to its original position
        msb |= (long1 & 0x00ffffffffffff00L) << 8;
        msb |= (long1 & 0x00000000000000ffL) << 4;

        lsb |= (long1 & 0x0f00000000000000L) << 4;
        lsb |= (long2 & 0x0fffffffffffffffL);

        return new UUID(msb, lsb);
    }
}
