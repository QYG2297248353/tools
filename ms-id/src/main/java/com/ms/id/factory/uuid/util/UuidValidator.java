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

package com.ms.id.factory.uuid.util;

import com.ms.id.factory.uuid.codec.base.Base16Codec;
import com.ms.id.factory.uuid.exception.InvalidUuidException;
import com.ms.id.factory.uuid.util.immutable.LongArray;

import java.util.UUID;

/**
 * Utility for UUID validation.
 * <p>
 * Using it is much faster than using on regular expression.
 * <p>
 * Examples of valid string formats:
 * <ul>
 * <li><code>12345678-abcd-abcd-abcd-123456789abcd</code> (36 hexadecimal chars,
 * lower case and with hyphen)
 * <li><code>12345678-ABCD-ABCD-ABCD-123456789ABCD</code> (36 hexadecimal chars,
 * UPPER CASE and with hyphen)
 * <li><code>12345678abcdabcdabcd123456789abcd</code> (32 hexadecimal chars,
 * lower case and WITHOUT hyphen)
 * <li><code>12345678ABCDABCDABCD123456789ABCD</code> (32 hexadecimal chars,
 * UPPER CASE and WITHOUT hyphen)
 * </ul>
 */
public final class UuidValidator {

    private static final LongArray MAP = Base16Codec.INSTANCE.getBase().getMap();

    private UuidValidator() {
    }

    /**
     * Checks if the UUID is valid.
     *
     * @param uuid a UUID
     * @return true if valid, false if invalid
     */
    public static boolean isValid(UUID uuid) {
        return uuid != null;
    }

    /**
     * Checks if the UUID is valid.
     *
     * @param uuid    a UUID
     * @param version a version number
     * @return true if valid, false if invalid
     */
    public static boolean isValid(UUID uuid, int version) {
        return uuid != null && isVersion(uuid, version);
    }

    /**
     * Checks if the UUID byte array is valid.
     *
     * @param uuid a UUID byte array
     * @return true if valid, false if invalid
     */
    public static boolean isValid(byte[] uuid) {
        return uuid != null && uuid.length == 16;
    }

    /**
     * Checks if the UUID byte array is valid.
     *
     * @param uuid    a UUID byte array
     * @param version a version number
     * @return true if valid, false if invalid
     */
    public static boolean isValid(byte[] uuid, int version) {
        return uuid != null && uuid.length == 16 && isVersion(uuid, version);
    }

    /**
     * Checks if the UUID string is valid.
     *
     * @param uuid a UUID string
     * @return true if valid, false if invalid
     */
    public static boolean isValid(String uuid) {
        return uuid != null && uuid.length() != 0 && isParseable(uuid.toCharArray());
    }

    /**
     * Checks if the UUID string is valid.
     *
     * @param uuid    a UUID string
     * @param version a version number
     * @return true if valid, false if invalid
     */
    public static boolean isValid(String uuid, int version) {
        return uuid != null && uuid.length() != 0 && isParseable(uuid.toCharArray(), version);
    }

    /**
     * Checks if the UUID char array is valid.
     *
     * @param uuid a UUID char array
     * @return true if valid, false if invalid
     */
    public static boolean isValid(char[] uuid) {
        return uuid != null && uuid.length != 0 && isParseable(uuid);
    }

    /**
     * Checks if the UUID char array is valid.
     *
     * @param uuid    a UUID char array
     * @param version a version number
     * @return true if valid, false if invalid
     */
    public static boolean isValid(char[] uuid, int version) {
        return uuid != null && uuid.length != 0 && isParseable(uuid, version);
    }

    /**
     * Checks if the UUID is valid.
     *
     * @param uuid a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(UUID uuid) {
        if (uuid == null) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID is valid.
     *
     * @param uuid    a UUID
     * @param version a version number
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(UUID uuid, int version) {
        if (uuid == null || !isVersion(uuid, version)) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID byte array is valid.
     *
     * @param uuid a UUID byte array
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(byte[] uuid) {
        if (uuid == null || uuid.length != 16) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID byte array is valid.
     *
     * @param uuid    a UUID byte array
     * @param version a version number
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(byte[] uuid, int version) {
        if (uuid == null || uuid.length != 16 || !isVersion(uuid, version)) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID string is a valid.
     *
     * @param uuid a UUID string
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(String uuid) {
        if (uuid == null || !isParseable(uuid.toCharArray())) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID string is a valid.
     *
     * @param uuid    a UUID string
     * @param version a version number
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(String uuid, int version) {
        if (uuid == null || !isParseable(uuid.toCharArray(), version)) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID char array is valid.
     *
     * @param uuid a UUID char array
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(char[] uuid) {
        if (uuid == null || !isParseable(uuid)) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID char array is valid.
     *
     * @param uuid    a UUID char array
     * @param version a version number
     * @throws InvalidUuidException if the argument is invalid
     */
    public static void validate(char[] uuid, int version) {
        if (uuid == null || !isParseable(uuid, version)) {
            throw InvalidUuidException.newInstance(uuid);
        }
    }

    /**
     * Checks if the UUID char array can be parsed.
     *
     * @param chars a char array
     * @return true if valid, false if invalid
     */
    protected static boolean isParseable(char[] chars) {

        int hyphens = 0;
        for (int i = 0; i < chars.length; i++) {
            if (MAP.get(chars[i]) == -1) {
                if (chars[i] == '-') {
                    hyphens++;
                    continue;
                }
                return false; // invalid character!
            }
        }

        if (chars.length == 36 && hyphens == 4) {
            // check if the hyphens positions are correct
            return chars[8] == '-' && chars[13] == '-' && chars[18] == '-' && chars[23] == '-';
        }

        return chars.length == 32 && hyphens == 0;
    }

    /**
     * Checks if the UUID char array can be parsed.
     *
     * @param chars   a char array
     * @param version a version number
     * @return true if valid, false if invalid
     */
    protected static boolean isParseable(char[] chars, int version) {
        return isVersion(chars, version) && isParseable(chars);
    }

    /**
     * Checks the version number of a UUID.
     *
     * @param uuid    a UUID
     * @param version a version number
     * @return true if the UUID version is equal to the expected version number
     */
    protected static boolean isVersion(UUID uuid, int version) {
        boolean versionOk = ((version & ~0xf) == 0) && (uuid.version() == version);
        boolean variantOk = uuid.variant() == 2; // RFC-4122
        return versionOk && variantOk;
    }

    /**
     * Checks the version number of a UUID byte array.
     *
     * @param bytes   a byte array
     * @param version a version number
     * @return true if the UUID version is equal to the expected version number
     */
    protected static boolean isVersion(byte[] bytes, int version) {
        boolean versionOk = ((version & ~0xf) == 0) && (((bytes[6] & 0xff) >>> 4) == version);
        boolean variantOk = ((bytes[8] & 0xff) >>> 6) == 2; // RFC-4122
        return versionOk && variantOk;
    }

    /**
     * Checks the version number of a UUID char array.
     *
     * @param chars   a string
     * @param version a version number
     * @return true if the UUID version is equal to the expected version number
     */
    protected static boolean isVersion(char[] chars, int version) {

        // valid if between 0x0 and 0xf
        if ((version & ~0xf) != 0) {
            return false;
        }

        int ver = 0; // version index
        int var = 0; // variant index

        switch (chars.length) {
            case 32: // without hyphen
                ver = 12;
                var = 16;
                break;
            case 36: // with hyphen
                ver = 14;
                var = 19;
                break;
            default:
                return false;
        }

        char[] lower = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] upper = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        boolean versionOk = ((version & ~0xf) == 0) && (chars[ver] == lower[version] || chars[ver] == upper[version]);
        boolean variantOk = chars[var] == '8' || chars[var] == '9' //
                || chars[var] == 'a' || chars[var] == 'b' || chars[var] == 'A' || chars[var] == 'B';

        return versionOk && variantOk;
    }
}
