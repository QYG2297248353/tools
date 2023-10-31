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

package com.ms.tools.core.id.factory.uuid.util;

import com.ms.tools.core.id.factory.uuid.util.internal.NetworkUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static com.ms.tools.core.id.factory.uuid.util.internal.ByteUtil.toHexadecimal;
import static com.ms.tools.core.id.factory.uuid.util.internal.ByteUtil.toNumber;

/**
 * Utility for generating machine ID.
 * <p>
 * It works in three steps:
 * <ol>
 * <li>Create a string containing HOSTNAME, MAC and IP;
 * <li>Create a hash of the string using SHA-256 algorithm;
 * <li>Create the identifier using part of the resulting hash.
 * </ol>
 */
public final class MachineId {

    private static Long id;
    private static UUID uuid;
    private static String hexa;
    private static byte[] hash;
    private static String string;

    private MachineId() {
    }

    /**
     * Returns a number generated from the machine hash.
     * <p>
     * It uses the first 8 bytes of the machine hash.
     *
     * @return a number
     */
    public static long getMachineId() {
        if (id == null) {
            byte[] bytes = getMachineHash();
            id = toNumber(bytes, 0, 8);
        }
        return id;
    }

    /**
     * Returns a UUID generated from the machine hash.
     * <p>
     * It uses the first 16 bytes of the machine hash.
     * <p>
     * The UUID version is 4.
     *
     * @return a UUID
     */
    public static UUID getMachineUuid() {
        if (uuid == null) {
            byte[] bytes = getMachineHash();
            long msb = toNumber(bytes, 0, 8);
            long lsb = toNumber(bytes, 8, 16);
            uuid = UuidUtil.setVersion(new UUID(msb, lsb), 4);
        }
        return uuid;
    }

    /**
     * Returns the machine hash in hexadecimal format.
     * <p>
     * The returning string has 64 chars.
     *
     * @return a string
     */
    public static String getMachineHexa() {
        if (hexa == null) {
            byte[] bytes = getMachineHash();
            hexa = toHexadecimal(bytes);
        }
        return hexa;
    }

    /**
     * Returns the machine hash in a byte array.
     * <p>
     * The returning array has 32 bytes (256 bits).
     *
     * @return a byte array
     */
    public static byte[] getMachineHash() {
        if (hash == null) {
            try {
                String string = getMachineString();
                hash = MessageDigest.getInstance("SHA-256").digest(string.getBytes(StandardCharsets.UTF_8));
            } catch (NoSuchAlgorithmException e) {
                throw new InternalError("Message digest algorithm not supported.", e);
            }

        }
        return hash;
    }

    /**
     * Returns a string containing host name, MAC and IP.
     * <p>
     * Output format: "hostname 11-11-11-11-11-11 222.222.222.222"
     *
     * @return a string
     */
    public static String getMachineString() {

        if (string == null) {
            string = NetworkUtil.getMachineString();
        }

        return string;
    }
}
