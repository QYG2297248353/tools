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

package com.ms.id.factory.uuid.util.internal;

/**
 * Utility class that contains many static methods for byte handling.
 */
public final class ByteUtil {

    private ByteUtil() {
    }

    /**
     * Get a number from a given array of bytes.
     *
     * @param bytes a byte array
     * @return a long
     */
    public static long toNumber(final byte[] bytes) {
        return toNumber(bytes, 0, bytes.length);
    }

    /**
     * Get a number from a given array of bytes.
     *
     * @param bytes a byte array
     * @param start first byte of the array
     * @param end   last byte of the array (exclusive)
     * @return a long
     */
    public static long toNumber(final byte[] bytes, final int start, final int end) {
        long result = 0;
        for (int i = start; i < end; i++) {
            result = (result << 8) | (bytes[i] & 0xffL);
        }
        return result;
    }

    /**
     * Get a hexadecimal string from given array of bytes.
     *
     * @param bytes byte array
     * @return a string
     */
    public static String toHexadecimal(final byte[] bytes) {

        final char[] chars = new char[bytes.length * 2];
        for (int i = 0, j = 0; i < bytes.length; i++, j += 2) {
            final int v = bytes[i] & 0xff;
            chars[j] = toHexChar(v >>> 4);
            chars[j + 1] = toHexChar(v & 0x0f);
        }
        return new String(chars);
    }

    /**
     * Get a hexadecimal from a number value.
     *
     * @param number a number
     * @return a char
     */
    private static char toHexChar(final int number) {
        if (number >= 0x00 && number <= 0x09) {
            // ASCII codes from 0 to 9
            return (char) (0x30 + number);
        } else if (number >= 0x0a && number <= 0x0f) {
            // ASCII codes from 'a' to 'f'
            return (char) (0x57 + number);
        }
        return 0;
    }
}
