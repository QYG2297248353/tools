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

package com.ms.id.factory.uuid.factory;

import com.ms.id.factory.uuid.codec.BinaryCodec;
import com.ms.id.factory.uuid.codec.StringCodec;
import com.ms.id.factory.uuid.enums.UuidNamespace;
import com.ms.id.factory.uuid.enums.UuidVersion;
import com.ms.id.factory.uuid.exception.InvalidUuidException;
import com.ms.id.factory.uuid.util.internal.ByteUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Abstract factory for creating name-based unique identifiers (UUIDv3 and
 * UUIDv5).
 *
 * @see UuidNamespace
 * @see <a href= "https://www.rfc-editor.org/rfc/rfc4122#section-4.3">RFC-4122 -
 * 4.3. Algorithm for Creating a Name-Based UUID</a>
 */
public abstract class AbstNameBasedFactory extends UuidFactory {

    protected static final String ALGORITHM_MD5 = "MD5";
    protected static final String ALGORITHM_SHA1 = "SHA-1";
    protected final String algorithm; // MD5 or SHA-1
    protected byte[] namespace = null;

    /**
     * Protected constructor that receives the message digest algorithm and an
     * optional name space.
     *
     * @param version   the version number (3 or 5)
     * @param algorithm a message digest algorithm (MD5 or SHA-1)
     * @param namespace a name space byte array (null or 16 bytes)
     */
    protected AbstNameBasedFactory(UuidVersion version, String algorithm, byte[] namespace) {
        super(version);

        if (!UuidVersion.VERSION_NAME_BASED_MD5.equals(version) && !UuidVersion.VERSION_NAME_BASED_SHA1.equals(version)) {
            throw new IllegalArgumentException("Invalid UUID version");
        }

        if (ALGORITHM_MD5.equals(algorithm) || ALGORITHM_SHA1.equals(algorithm)) {
            this.algorithm = algorithm;
        } else {
            throw new IllegalArgumentException("Invalid message digest algorithm");
        }

        if (namespace == null) {
            // null is accepted
            this.namespace = null;
        } else {
            if (namespace.length == 16) {
                // must be 16 bytes length
                this.namespace = namespace;
            } else {
                throw new IllegalArgumentException("Invalid namespace length");
            }
        }
    }

    /**
     * Converts a name space enumeration into a byte array.
     *
     * @param namespace a name space enumeration
     * @return a byte array
     */
    protected static byte[] bytes(UuidNamespace namespace) {
        return bytes(namespace.getValue());
    }

    /**
     * Converts a name space UUID into a byte array.
     *
     * @param namespace a name space UUID
     * @return a byte array
     */
    protected static byte[] bytes(UUID namespace) {
        return BinaryCodec.INSTANCE.encode(namespace);
    }

    /**
     * Converts a name space string into a byte array.
     *
     * @param namespace a name space string
     * @return a byte array
     * @throws InvalidUuidException if the name space is invalid
     * @see InvalidUuidException
     */
    protected static byte[] bytes(String namespace) {
        return BinaryCodec.INSTANCE.encode(StringCodec.INSTANCE.decode(namespace));
    }

    /**
     * Returns a name-based UUID.
     *
     * @param name a byte array
     * @return a name-based UUID
     */
    public UUID create(byte[] name) {
        return create(namespace, name);
    }

    /**
     * Returns a name-based UUID.
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param name a string
     * @return a name-based UUID
     */
    public UUID create(String name) {
        byte[] n = name.getBytes(StandardCharsets.UTF_8);
        return create(namespace, n);
    }

    /**
     * Returns a name-based UUID.
     *
     * @param name a UUID
     * @return a name-based UUID
     */
    public UUID create(UUID name) {
        return create(namespace, bytes(name));
    }

    /**
     * Returns a name-based UUID.
     *
     * @param namespace a name space UUID
     * @param name      a byte array
     * @return a name-based UUID
     */
    public UUID create(UUID namespace, byte[] name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        return create(ns, name);
    }

    /**
     * Returns a name-based UUID.
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param namespace a name space UUID
     * @param name      a string
     * @return a name-based UUID
     */
    public UUID create(UUID namespace, String name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        byte[] n = name.getBytes(StandardCharsets.UTF_8);
        return create(ns, n);
    }

    /**
     * Returns a name-based UUID.
     *
     * @param namespace a name space UUID
     * @param name      a UUID
     * @return a name-based UUID
     */
    public UUID create(UUID namespace, UUID name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        byte[] n = bytes(name);
        return create(ns, n);
    }

    /**
     * Returns a name-based UUID.
     *
     * @param namespace a name space string
     * @param name      a byte array
     * @return a name-based UUID
     * @throws InvalidUuidException if the name space is invalid
     * @see InvalidUuidException
     */
    public UUID create(String namespace, byte[] name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        return create(ns, name);
    }

    /**
     * Returns a name-based UUID.
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param namespace a name space string
     * @param name      a string
     * @return a name-based UUID
     * @throws InvalidUuidException if the name space is invalid
     * @see InvalidUuidException
     */
    public UUID create(String namespace, String name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        byte[] n = name.getBytes(StandardCharsets.UTF_8);
        return create(ns, n);
    }

    /**
     * Returns a name-based UUID.
     *
     * @param namespace a name space string
     * @param name      a UUID
     * @return a name-based UUID
     * @throws InvalidUuidException if the name space is invalid
     * @see InvalidUuidException
     */
    public UUID create(String namespace, UUID name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        byte[] n = bytes(name);
        return create(ns, n);
    }

    /**
     * Returns a name-based UUID.
     *
     * @param namespace a name space enumeration
     * @param name      a byte array
     * @return a name-based UUID
     * @see UuidNamespace
     */
    public UUID create(UuidNamespace namespace, byte[] name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        return create(ns, name);
    }

    /**
     * Returns a name-based UUID.
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param namespace a name space enumeration
     * @param name      a string
     * @return a name-based UUID
     * @see UuidNamespace
     */
    public UUID create(UuidNamespace namespace, String name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        byte[] n = name.getBytes(StandardCharsets.UTF_8);
        return create(ns, n);
    }

    /**
     * Returns a name-based UUID.
     *
     * @param namespace a name space enumeration
     * @param name      a UUID
     * @return a name-based UUID
     * @see UuidNamespace
     */
    public UUID create(UuidNamespace namespace, UUID name) {
        byte[] ns = namespace == null ? null : bytes(namespace);
        byte[] n = bytes(name);
        return create(ns, n);
    }

    private UUID create(byte[] namespace, byte[] name) {

        MessageDigest hasher;

        try {
            hasher = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Message digest algorithm not available: " + algorithm, e);
        }

        if (namespace != null) {
            // Prepend the name space
            hasher.update(namespace);
        }

        // Compute the hash of the name
        byte[] hash = hasher.digest(name);

        long msb = ByteUtil.toNumber(hash, 0, 8);
        long lsb = ByteUtil.toNumber(hash, 8, 16);
        return toUuid(msb, lsb);
    }
}
