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

package com.ms.id.factory.uuid.codec;

import com.ms.id.factory.uuid.codec.base.Base16Codec;
import com.ms.id.factory.uuid.exception.InvalidUuidException;
import com.ms.id.factory.uuid.util.UuidValidator;
import com.ms.id.factory.uuid.util.immutable.CharArray;
import com.ms.id.factory.uuid.util.immutable.LongArray;

import java.util.UUID;

/**
 * Codec for UUID string representation as defined in RFC-4122.
 * <p>
 * The string representation, also referred as canonical textual representation,
 * is a string of 32 hexadecimal (base-16) digits, displayed in five groups
 * separated by hyphens, in the form 8-4-4-4-12 for a total of 36 characters (32
 * hexadecimal characters and 4 hyphens).
 * <p>
 * This codec decodes (parses) strings in these formats, with/without hyphens:
 * <ul>
 * <li>00000000-0000-V000-0000-000000000000 (canonical string)
 * <li>{00000000-0000-V000-0000-000000000000} (MS GUID string)
 * <li>urn:uuid:00000000-0000-V000-0000-000000000000 (URN UUID string)
 * </ul>
 * <p>
 * The encoding and decoding processes can be much faster (7x) than
 * {@link UUID#toString()} and {@link UUID#fromString(String)} in JDK 8.
 * <p>
 * If you prefer a string representation without hyphens, use
 * {@link Base16Codec} instead of {@link StringCodec}. {@link Base16Codec} can
 * be much faster (22x) than doing
 * <code>uuid.toString().replaceAll("-", "")</code>.
 */
public class StringCodec implements UuidCodec<String> {

    /**
     * A shared immutable instance.
     */
    public static final StringCodec INSTANCE = new StringCodec();

    private static final LongArray MAP = Base16Codec.INSTANCE.getBase().getMap();
    private static final CharArray ALPHABET = Base16Codec.INSTANCE.getBase().getAlphabet();

    private static final String URN_PREFIX = "urn:uuid:";
    private static final boolean JAVA_VERSION_GREATER_THAN_8 = getJavaVersion() > 8;

    /**
     * Returns a char array of a string.
     * <p>
     * It removes URN prefix and curly braces from the string.
     *
     * @param string a string
     * @return a substring
     */
    protected static char[] toCharArray(String string) {

        if (string == null) {
            throw new InvalidUuidException("Invalid UUID: null");
        }

        char[] chars = string.toCharArray();

        // UUID URN format: "urn:uuid:00000000-0000-0000-0000-000000000000"
        if (chars.length == 45 && string.startsWith(URN_PREFIX)) {
            // Remove the UUID URN prefix: "urn:uuid:"
            char[] substring = new char[chars.length - 9];
            System.arraycopy(chars, 9, substring, 0, substring.length);
            return substring;
        }

        // Curly braces format: "{00000000-0000-0000-0000-000000000000}"
        if (chars.length == 38 && chars[0] == '{' && chars[chars.length - 1] == '}') {
            // Remove curly braces: '{' and '}'
            char[] substring = new char[chars.length - 2];
            System.arraycopy(chars, 1, substring, 0, substring.length);
            return substring;
        }

        return chars;
    }

    /**
     * Returns the java major version number.
     *
     * @return major version number
     * @see <a href= "https://www.java.com/releases/">JDK Releases</a>
     */
    protected static int getJavaVersion() {
        try {

            String property = System.getProperty("java.version");

            if (property != null) {
                String[] version = property.split("\\.");
                if (version[0].equals("1")) {
                    return Integer.parseInt(version[1]);
                } else {
                    return Integer.parseInt(version[0]);
                }
            } else {
                return 8;
            }

        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            return 8;
        }
    }

    /**
     * Get a string from a UUID.
     * <p>
     * It can be much faster than {@link UUID#toString()} in JDK 8.
     *
     * @param uuid a UUID
     * @return a UUID string
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public String encode(UUID uuid) {

        UuidValidator.validate(uuid);

        if (JAVA_VERSION_GREATER_THAN_8) {
            return uuid.toString();
        }

        char[] chars = new char[36];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        chars[0x00] = ALPHABET.get((int) (msb >>> 0x3c & 0xf));
        chars[0x01] = ALPHABET.get((int) (msb >>> 0x38 & 0xf));
        chars[0x02] = ALPHABET.get((int) (msb >>> 0x34 & 0xf));
        chars[0x03] = ALPHABET.get((int) (msb >>> 0x30 & 0xf));
        chars[0x04] = ALPHABET.get((int) (msb >>> 0x2c & 0xf));
        chars[0x05] = ALPHABET.get((int) (msb >>> 0x28 & 0xf));
        chars[0x06] = ALPHABET.get((int) (msb >>> 0x24 & 0xf));
        chars[0x07] = ALPHABET.get((int) (msb >>> 0x20 & 0xf));
        chars[0x08] = '-'; // 8
        chars[0x09] = ALPHABET.get((int) (msb >>> 0x1c & 0xf));
        chars[0x0a] = ALPHABET.get((int) (msb >>> 0x18 & 0xf));
        chars[0x0b] = ALPHABET.get((int) (msb >>> 0x14 & 0xf));
        chars[0x0c] = ALPHABET.get((int) (msb >>> 0x10 & 0xf));
        chars[0x0d] = '-'; // 13
        chars[0x0e] = ALPHABET.get((int) (msb >>> 0x0c & 0xf));
        chars[0x0f] = ALPHABET.get((int) (msb >>> 0x08 & 0xf));
        chars[0x10] = ALPHABET.get((int) (msb >>> 0x04 & 0xf));
        chars[0x11] = ALPHABET.get((int) (msb & 0xf));
        chars[0x12] = '-'; // 18
        chars[0x13] = ALPHABET.get((int) (lsb >>> 0x3c & 0xf));
        chars[0x14] = ALPHABET.get((int) (lsb >>> 0x38 & 0xf));
        chars[0x15] = ALPHABET.get((int) (lsb >>> 0x34 & 0xf));
        chars[0x16] = ALPHABET.get((int) (lsb >>> 0x30 & 0xf));
        chars[0x17] = '-'; // 23
        chars[0x18] = ALPHABET.get((int) (lsb >>> 0x2c & 0xf));
        chars[0x19] = ALPHABET.get((int) (lsb >>> 0x28 & 0xf));
        chars[0x1a] = ALPHABET.get((int) (lsb >>> 0x24 & 0xf));
        chars[0x1b] = ALPHABET.get((int) (lsb >>> 0x20 & 0xf));
        chars[0x1c] = ALPHABET.get((int) (lsb >>> 0x1c & 0xf));
        chars[0x1d] = ALPHABET.get((int) (lsb >>> 0x18 & 0xf));
        chars[0x1e] = ALPHABET.get((int) (lsb >>> 0x14 & 0xf));
        chars[0x1f] = ALPHABET.get((int) (lsb >>> 0x10 & 0xf));
        chars[0x20] = ALPHABET.get((int) (lsb >>> 0x0c & 0xf));
        chars[0x21] = ALPHABET.get((int) (lsb >>> 0x08 & 0xf));
        chars[0x22] = ALPHABET.get((int) (lsb >>> 0x04 & 0xf));
        chars[0x23] = ALPHABET.get((int) (lsb & 0xf));

        return new String(chars);
    }

    /**
     * Get a UUID from a string.
     * <p>
     * It accepts strings:
     * <ul>
     * <li>With URN prefix: "urn:uuid:";
     * <li>With curly braces: '{' and '}';
     * <li>With upper or lower case;
     * <li>With or without hyphens.
     * </ul>
     * <p>
     * It can be much faster than {@link UUID#fromString(String)} in JDK 8.
     * <p>
     * It also can be twice as fast as {@link UUID#fromString(String)} in JDK 11.
     *
     * @param string a UUID string
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    @Override
    public UUID decode(String string) {

        char[] chars = toCharArray(string);
        UuidValidator.validate(chars);

        long msb = 0;
        long lsb = 0;

        if (chars.length == 32) {
            // UUID string WITHOUT hyphen
            msb |= MAP.get(chars[0x00]) << 60;
            msb |= MAP.get(chars[0x01]) << 56;
            msb |= MAP.get(chars[0x02]) << 52;
            msb |= MAP.get(chars[0x03]) << 48;
            msb |= MAP.get(chars[0x04]) << 44;
            msb |= MAP.get(chars[0x05]) << 40;
            msb |= MAP.get(chars[0x06]) << 36;
            msb |= MAP.get(chars[0x07]) << 32;
            msb |= MAP.get(chars[0x08]) << 28;
            msb |= MAP.get(chars[0x09]) << 24;
            msb |= MAP.get(chars[0x0a]) << 20;
            msb |= MAP.get(chars[0x0b]) << 16;
            msb |= MAP.get(chars[0x0c]) << 12;
            msb |= MAP.get(chars[0x0d]) << 8;
            msb |= MAP.get(chars[0x0e]) << 4;
            msb |= MAP.get(chars[0x0f]);

            lsb |= MAP.get(chars[0x10]) << 60;
            lsb |= MAP.get(chars[0x11]) << 56;
            lsb |= MAP.get(chars[0x12]) << 52;
            lsb |= MAP.get(chars[0x13]) << 48;
            lsb |= MAP.get(chars[0x14]) << 44;
            lsb |= MAP.get(chars[0x15]) << 40;
            lsb |= MAP.get(chars[0x16]) << 36;
            lsb |= MAP.get(chars[0x17]) << 32;
            lsb |= MAP.get(chars[0x18]) << 28;
            lsb |= MAP.get(chars[0x19]) << 24;
            lsb |= MAP.get(chars[0x1a]) << 20;
            lsb |= MAP.get(chars[0x1b]) << 16;
            lsb |= MAP.get(chars[0x1c]) << 12;
            lsb |= MAP.get(chars[0x1d]) << 8;
            lsb |= MAP.get(chars[0x1e]) << 4;
            lsb |= MAP.get(chars[0x1f]);
        } else {
            // UUID string WITH hyphen
            msb |= MAP.get(chars[0x00]) << 60;
            msb |= MAP.get(chars[0x01]) << 56;
            msb |= MAP.get(chars[0x02]) << 52;
            msb |= MAP.get(chars[0x03]) << 48;
            msb |= MAP.get(chars[0x04]) << 44;
            msb |= MAP.get(chars[0x05]) << 40;
            msb |= MAP.get(chars[0x06]) << 36;
            msb |= MAP.get(chars[0x07]) << 32;
            // input[8] = '-'
            msb |= MAP.get(chars[0x09]) << 28;
            msb |= MAP.get(chars[0x0a]) << 24;
            msb |= MAP.get(chars[0x0b]) << 20;
            msb |= MAP.get(chars[0x0c]) << 16;
            // input[13] = '-'
            msb |= MAP.get(chars[0x0e]) << 12;
            msb |= MAP.get(chars[0x0f]) << 8;
            msb |= MAP.get(chars[0x10]) << 4;
            msb |= MAP.get(chars[0x11]);
            // input[18] = '-'
            lsb |= MAP.get(chars[0x13]) << 60;
            lsb |= MAP.get(chars[0x14]) << 56;
            lsb |= MAP.get(chars[0x15]) << 52;
            lsb |= MAP.get(chars[0x16]) << 48;
            // input[23] = '-'
            lsb |= MAP.get(chars[0x18]) << 44;
            lsb |= MAP.get(chars[0x19]) << 40;
            lsb |= MAP.get(chars[0x1a]) << 36;
            lsb |= MAP.get(chars[0x1b]) << 32;
            lsb |= MAP.get(chars[0x1c]) << 28;
            lsb |= MAP.get(chars[0x1d]) << 24;
            lsb |= MAP.get(chars[0x1e]) << 20;
            lsb |= MAP.get(chars[0x1f]) << 16;
            lsb |= MAP.get(chars[0x20]) << 12;
            lsb |= MAP.get(chars[0x21]) << 8;
            lsb |= MAP.get(chars[0x22]) << 4;
            lsb |= MAP.get(chars[0x23]);
        }

        return new UUID(msb, lsb);
    }
}
