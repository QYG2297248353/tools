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

package com.ms.tools.core.id.factory.uuid;


import com.ms.tools.core.id.factory.uuid.codec.BinaryCodec;
import com.ms.tools.core.id.factory.uuid.codec.StringCodec;
import com.ms.tools.core.id.factory.uuid.enums.UuidLocalDomain;
import com.ms.tools.core.id.factory.uuid.enums.UuidNamespace;
import com.ms.tools.core.id.factory.uuid.exception.InvalidUuidException;
import com.ms.tools.core.id.factory.uuid.factory.nonstandard.PrefixCombFactory;
import com.ms.tools.core.id.factory.uuid.factory.nonstandard.ShortPrefixCombFactory;
import com.ms.tools.core.id.factory.uuid.factory.nonstandard.ShortSuffixCombFactory;
import com.ms.tools.core.id.factory.uuid.factory.nonstandard.SuffixCombFactory;
import com.ms.tools.core.id.factory.uuid.factory.rfc4122.*;
import com.ms.tools.core.id.factory.uuid.util.MachineId;

import java.time.Instant;
import java.util.UUID;

/**
 * UUID
 * 生成通用唯一标识符的 Java 库
 */
public final class UuidCreator {

    public static final UuidNamespace NAMESPACE_DNS = UuidNamespace.NAMESPACE_DNS;
    public static final UuidNamespace NAMESPACE_URL = UuidNamespace.NAMESPACE_URL;
    public static final UuidNamespace NAMESPACE_OID = UuidNamespace.NAMESPACE_OID;
    public static final UuidNamespace NAMESPACE_X500 = UuidNamespace.NAMESPACE_X500;

    public static final UuidLocalDomain LOCAL_DOMAIN_PERSON = UuidLocalDomain.LOCAL_DOMAIN_PERSON;
    public static final UuidLocalDomain LOCAL_DOMAIN_GROUP = UuidLocalDomain.LOCAL_DOMAIN_GROUP;
    public static final UuidLocalDomain LOCAL_DOMAIN_ORG = UuidLocalDomain.LOCAL_DOMAIN_ORG;

    private static final UUID UUID_NIL = new UUID(0x0000000000000000L, 0x0000000000000000L);
    private static final UUID UUID_MAX = new UUID(0x1111111111111111L, 0x1111111111111111L);

    private UuidCreator() {
    }

    /**
     * 从 UUID 返回一个字节数组。
     *
     * @param uuid UUID
     * @return 一个字节数组
     * @throws InvalidUuidException if the argument is invalid
     */
    public static byte[] toBytes(UUID uuid) {
        return BinaryCodec.INSTANCE.encode(uuid);
    }

    /**
     * 从字节数组返回 UUID。
     * 它还检查输入字节数组是否有效。
     *
     * @param uuid 一个字节数组
     * @return UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    public static UUID fromBytes(byte[] uuid) {
        return BinaryCodec.INSTANCE.decode(uuid);
    }

    /**
     * 从 UUID 返回一个字符串。
     * 它可以比 JDK 8 中的UUID.toString()快得多。
     *
     * @param uuid UUID
     * @return UUID string
     * @throws InvalidUuidException if the argument is invalid
     */
    public static String toString(UUID uuid) {
        return StringCodec.INSTANCE.encode(uuid);
    }

    /**
     * 从字符串返回 UUID。
     * 它接受字符串：
     * 带 URN 前缀：“urn:uuid:”；
     * 带花括号：'{' 和 '}';
     * 带大写或小写；
     * 带或不带连字符。
     * 它可以比 JDK 8 中的UUID.fromString(String)快得多。
     * 它也可以比 JDK 11 中的UUID.fromString(String)快两倍。
     *
     * @param uuid a UUID string
     * @return a UUID
     * @throws InvalidUuidException if the argument is invalid
     */
    public static UUID fromString(String uuid) {
        return StringCodec.INSTANCE.decode(uuid);
    }

    /**
     * 返回一个 Nil UUID。
     * Nil UUID 是一个特殊的 UUID，所有 128 位都设置为零。
     * Nil UUID 的规范字符串是00000000-0000-0000-0000-000000000000 。
     *
     * @return a Nil UUID
     */
    public static UUID getNil() {
        return UUID_NIL;
    }

    /**
     * 返回最大 UUID。
     * Max UUID 是一个特殊的 UUID，所有 128 位都设置为 ONE。
     * Max UUID 的规范字符串是FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF 。
     *
     * @return Max UUID
     */
    public static UUID getMax() {
        return UUID_MAX;
    }

    /**
     * UUIDv4
     *
     * @return UUIDv4
     * @see RandomBasedFactory
     */
    public static UUID getRandomBased() {
        return RandomBasedHolder.INSTANCE.create();
    }

    /**
     * UUIDv1
     * 返回基于时间的唯一标识符 (UUIDv1)。
     * 默认节点标识符是在初始化时生成一次的随机数。
     * 自定义节点标识符可以由系统属性“uuidcreator.node”或环境变量“UUIDCREATOR_NODE”提供。
     *
     * @return UUIDv1
     * @see TimeBasedFactory
     */
    public static UUID getTimeBased() {
        return TimeBasedHolder.INSTANCE.create();
    }

    /**
     * UUIDv1
     * 返回基于时间的唯一标识符 (UUIDv1)。
     * 节点标识符是在初始化时获得一次的 MAC 地址。
     *
     * @return UUIDv1
     * @see TimeBasedFactory
     */
    public static UUID getTimeBasedWithMac() {
        return TimeBasedWithMacHolder.INSTANCE.create();
    }

    /**
     * UUIDv1
     * 返回基于时间的唯一标识符 (UUIDv1)。
     * 节点标识符是在初始化时计算一次的哈希值。
     * 哈希输入是一个包含主机名、MAC 和 IP 的字符串。
     *
     * @return UUIDv1
     * @see TimeBasedFactory
     * @see MachineId
     */
    public static UUID getTimeBasedWithHash() {
        return TimeBasedWithHashHolder.INSTANCE.create();
    }

    /**
     * UUIDv1
     * 返回基于时间的唯一标识符 (UUIDv1)。
     * 节点标识符是随每个方法调用生成的随机数。
     *
     * @return UUIDv1
     * @see TimeBasedFactory
     */
    public static UUID getTimeBasedWithRandom() {
        return TimeBasedWithRandomHolder.INSTANCE.create();
    }

    /**
     * UUIDv1
     * 返回基于时间的唯一标识符 (UUIDv1)。
     * Instant精度在带有 JDK 8 的 Linux 上被限制为 1 毫秒。在 Windows 上，其精度可能被限制为 15.625 毫秒（64 赫兹）。
     * 时钟序列是一个介于 0 和 16383 (2^14 - 1) 之间的数字。如果作为参数传递的值超出范围，将使用 MOD 2^14 的结果。
     * 节点标识符是一个介于 0 和 281474976710655 (2^48 - 1) 之间的数字。如果作为参数传递的值超出范围，将使用 MOD 2^48 的结果。
     * 空参数被忽略。如果所有参数都为 null，则此方法的工作原理与方法getTimeBased()一样。
     *
     * @param instant  另一个瞬间
     * @param clockseq 0 和 2^14-1 之间的备用时钟序列
     * @param nodeid   0 到 2^48-1 之间的备用节点标识符
     * @return UUIDv1
     * @see TimeBasedFactory
     */
    public static UUID getTimeBased(Instant instant, Integer clockseq, Long nodeid) {
        TimeBasedFactory.Builder builder = TimeBasedFactory.builder();
        if (instant != null) {
            builder.withInstant(instant);
        }
        if (clockseq != null) {
            builder.withClockSeq(clockseq);
        }
        if (nodeid != null) {
            builder.withNodeId(nodeid);
        }
        return builder.build().create();
    }

    /**
     * UUIDv6
     * 返回按时间排序的唯一标识符 (UUIDv6)。
     * 默认节点标识符是在初始化时生成一次的随机数。
     * 自定义节点标识符可以由系统属性“uuidcreator.node”或环境变量“UUIDCREATOR_NODE”提供。
     *
     * @return UUIDv6
     * @see TimeOrderedFactory
     */
    public static UUID getTimeOrdered() {
        return TimeOrderedHolder.INSTANCE.create();
    }

    /**
     * UUIDv6
     * 返回按时间排序的唯一标识符 (UUIDv6)。
     * 节点标识符是在初始化时获得一次的 MAC 地址。
     *
     * @return UUIDv6
     * @see TimeOrderedFactory
     */
    public static UUID getTimeOrderedWithMac() {
        return TimeOrderedWithMacHolder.INSTANCE.create();
    }

    /**
     * UUIDv6
     * 返回按时间排序的唯一标识符 (UUIDv6)。
     * 节点标识符是在初始化时计算一次的哈希值。
     * 哈希输入是一个包含主机名、MAC 和 IP 的字符串。
     *
     * @return UUIDv6
     * @see TimeOrderedFactory
     * @see MachineId
     */
    public static UUID getTimeOrderedWithHash() {
        return TimeOrderedWithHashHolder.INSTANCE.create();
    }

    /**
     * UUIDv6
     * 返回按时间排序的唯一标识符 (UUIDv6)。
     * 节点标识符是随每个方法调用生成的随机数。
     *
     * @return UUIDv6
     * @see TimeOrderedFactory
     */
    public static UUID getTimeOrderedWithRandom() {
        return TimeOrderedWithRandomHolder.INSTANCE.create();
    }

    /**
     * UUIDv6
     * 返回按时间排序的唯一标识符 (UUIDv6)。
     * Instant精度在带有 JDK 8 的 Linux 上被限制为 1 毫秒。在 Windows 上，其精度可能被限制为 15.625 毫秒（64 赫兹）。
     * 时钟序列是一个介于 0 和 16383 (2^14 - 1) 之间的数字。如果作为参数传递的值超出范围，将使用 MOD 2^14 的结果。
     * 节点标识符是一个介于 0 和 281474976710655 (2^48 - 1) 之间的数字。如果作为参数传递的值超出范围，将使用 MOD 2^48 的结果。
     * 空参数被忽略。如果所有参数都为空，则此方法的工作原理与方法getTimeOrdered()一样。
     *
     * @param instant  另一个瞬间
     * @param clockseq 0 到 2^14-1 之间的备用时钟序列
     * @param nodeid   0 到 2^48-1 之间的备用节点标识符
     * @return UUIDv6
     * @see TimeOrderedFactory
     */
    public static UUID getTimeOrdered(Instant instant, Integer clockseq, Long nodeid) {
        TimeOrderedFactory.Builder builder = TimeOrderedFactory.builder();
        if (instant != null) {
            builder.withInstant(instant);
        }
        if (clockseq != null) {
            builder.withClockSeq(clockseq);
        }
        if (nodeid != null) {
            builder.withNodeId(nodeid);
        }
        return builder.build().create();
    }

    /**
     * UUIDv7
     * 返回使用 Unix Epoch (UUIDv7) 的按时间排序的唯一标识符。
     * 标识符有 3 个部分：时间、计数器和随机。
     * 当时间重复时，计数器位加 1。
     * 每次方法调用都会生成随机位
     *
     * @return UUIDv7
     * @see TimeOrderedEpochFactory
     */
    public static UUID getTimeOrderedEpoch() {
        return TimeOrderedEpochHolder.INSTANCE.create();
    }

    /**
     * UUIDv7
     * 返回使用 Unix Epoch (UUIDv7) 的按时间排序的唯一标识符。
     * 标识符有两部分：时间和单调随机。
     * 当时间重复时，单调随机位加 1。
     *
     * @return UUIDv7
     * @see TimeOrderedEpochFactory
     */
    public static UUID getTimeOrderedEpochPlus1() {
        return TimeOrderedEpochPlus1Holder.INSTANCE.create();
    }

    /**
     * UUIDv7
     * 返回使用 Unix Epoch (UUIDv7) 的按时间排序的唯一标识符。
     * 标识符有两部分：时间和单调随机。
     * 当时间重复时，单调随机位会增加 1 到 2^32 之间的随机数。
     *
     * @return UUIDv7
     * @see TimeOrderedEpochFactory
     */
    public static UUID getTimeOrderedEpochPlusN() {
        return TimeOrderedEpochPlusNHolder.INSTANCE.create();
    }

    /**
     * UUIDv3
     * 返回使用 MD5 散列 (UUIDv3) 的基于名称的唯一标识符。
     * 名称字符串使用 UTF-8 编码为字节序列。
     *
     * @param name string
     * @return UUIDv3
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(String name) {
        return NameBasedMd5Holder.INSTANCE.create(name);
    }

    /**
     * UUIDv3
     * 返回使用 MD5 散列 (UUIDv3) 的基于名称的唯一标识符。
     *
     * @param name byte array
     * @return UUIDv3
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(byte[] name) {
        return NameBasedMd5Holder.INSTANCE.create(name);
    }

    /**
     * UUIDv3
     * 返回使用 MD5 散列 (UUIDv3) 的基于名称的唯一标识符。
     *
     * @param name UUID
     * @return UUIDv3
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UUID name) {
        return NameBasedMd5Holder.INSTANCE.create(name);
    }

    /**
     * UUIDv3
     * 返回使用 MD5 散列 (UUIDv3) 的基于名称的唯一标识符。
     * 名称字符串使用 UTF-8 编码为字节序列。
     * 参形：
     * namespace – 自定义命名空间 UUID name - 一个字符串
     *
     * @param namespace 自定义命名空间
     * @param name      一个字符串
     * @return UUIDv3
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UUID namespace, String name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     *
     * @param namespace a custom name space UUID
     * @param name      a byte array
     * @return a UUIDv3
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UUID namespace, byte[] name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     *
     * @param namespace a custom name space UUID
     * @param name      a UUID
     * @return a UUIDv3
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UUID namespace, UUID name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param namespace a custom name space UUID in string format
     * @param name      a string
     * @return a UUIDv3
     * @throws InvalidUuidException if namespace is invalid
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(String namespace, String name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     *
     * @param namespace a custom name space UUID in string format
     * @param name      a byte array
     * @return a UUIDv3
     * @throws InvalidUuidException if namespace is invalid
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(String namespace, byte[] name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     *
     * @param namespace a custom name space UUID in string format
     * @param name      a UUID
     * @return a UUIDv3
     * @throws InvalidUuidException if namespace is invalid
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(String namespace, UUID name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     * <p>
     * Name spaces predefined by RFC-4122 (Appendix C):
     * <ul>
     * <li>NAMESPACE_DNS: Name string is a fully-qualified domain name;
     * <li>NAMESPACE_URL: Name string is a URL;
     * <li>NAMESPACE_OID: Name string is an ISO OID;
     * <li>NAMESPACE_X500: Name string is an X.500 DN (in DER or text format).
     * </ul>
     *
     * @param namespace a predefined name space enumeration
     * @param name      a string
     * @return a UUIDv3
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UuidNamespace namespace, String name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     * <p>
     * Name spaces predefined by RFC-4122 (Appendix C):
     * <ul>
     * <li>NAMESPACE_DNS: Name string is a fully-qualified domain name;
     * <li>NAMESPACE_URL: Name string is a URL;
     * <li>NAMESPACE_OID: Name string is an ISO OID;
     * <li>NAMESPACE_X500: Name string is an X.500 DN (in DER or text format).
     * </ul>
     *
     * @param namespace a predefined name space enumeration
     * @param name      a byte array
     * @return a UUIDv3
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UuidNamespace namespace, byte[] name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses MD5 hashing (UUIDv3).
     * <p>
     * Name spaces predefined by RFC-4122 (Appendix C):
     * <ul>
     * <li>NAMESPACE_DNS: Name string is a fully-qualified domain name;
     * <li>NAMESPACE_URL: Name string is a URL;
     * <li>NAMESPACE_OID: Name string is an ISO OID;
     * <li>NAMESPACE_X500: Name string is an X.500 DN (in DER or text format).
     * </ul>
     *
     * @param namespace a predefined name space enumeration
     * @param name      a UUID
     * @return a UUIDv3
     * @see UuidNamespace
     * @see NameBasedMd5Factory
     */
    public static UUID getNameBasedMd5(UuidNamespace namespace, UUID name) {
        return NameBasedMd5Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses SHA-1 hashing (UUIDv5).
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param name a string
     * @return a UUIDv5
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(String name) {
        return NameBasedSha1Holder.INSTANCE.create(name);
    }

    /**
     * Returns a name-based unique identifier that uses SHA-1 hashing (UUIDv5).
     *
     * @param name a byte array
     * @return a UUIDv5
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(byte[] name) {
        return NameBasedSha1Holder.INSTANCE.create(name);
    }

    /**
     * Returns a name-based unique identifier that uses SHA-1 hashing (UUIDv5).
     *
     * @param name a UUID
     * @return a UUIDv5
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UUID name) {
        return NameBasedSha1Holder.INSTANCE.create(name);
    }

    /**
     * Returns a name-based unique identifier that uses SHA-1 hashing (UUIDv5).
     * <p>
     * The name string is encoded into a sequence of bytes using UTF-8.
     *
     * @param namespace a custom name space UUID
     * @param name      a string
     * @return a UUIDv5
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UUID namespace, String name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses SHA-1 hashing (UUIDv5).
     *
     * @param namespace a custom name space UUID
     * @param name      a byte array
     * @return a UUIDv5
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UUID namespace, byte[] name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * Returns a name-based unique identifier that uses SHA-1 hashing (UUIDv5).
     *
     * @param namespace a custom name space UUID
     * @param name      a UUID
     * @return a UUIDv5
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UUID namespace, UUID name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * 返回使用 SHA-1 散列 (UUIDv5) 的基于名称的唯一标识符。
     * 名称字符串使用 UTF-8 编码为字节序列。
     *
     * @param namespace 预定义的本地域枚举
     * @param name      本地标识符
     * @return UUIDv5
     * @throws InvalidUuidException if namespace is invalid
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(String namespace, String name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * UUIDv5
     * 返回使用 SHA-1 散列 (UUIDv5) 的基于名称的唯一标识符。
     *
     * @param namespace 预定义的本地域枚举
     * @param name      本地标识符
     * @return UUIDv5
     * @throws InvalidUuidException if namespace is invalid
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(String namespace, byte[] name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * UUIDv5
     * 返回使用 SHA-1 散列 (UUIDv5) 的基于名称的唯一标识符
     *
     * @param namespace 预定义的本地域枚举
     * @param name      本地标识符
     * @return UUIDv5
     * @throws InvalidUuidException if namespace is invalid
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(String namespace, UUID name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * UUIDv5
     * 返回使用 SHA-1 散列 (UUIDv5) 的基于名称的唯一标识符。
     * 名称字符串使用 UTF-8 编码为字节序列。
     * RFC-4122（附录 C）预定义的名称空间：
     * NAMESPACE_DNS：名称字符串为全限定域名；
     * NAMESPACE_URL：名称字符串是一个URL；
     * NAMESPACE_OID：名称字符串是一个 ISO OID；
     * NAMESPACE_X500：名称字符串是 X.500 DN（DER 或文本格式）。
     *
     * @param namespace 预定义的本地域枚举
     * @param name      本地标识符
     * @return UUIDv5
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UuidNamespace namespace, String name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * UUIDv5
     * 返回使用 SHA-1 散列 (UUIDv5) 的基于名称的唯一标识符。
     * RFC-4122（附录 C）预定义的名称空间：
     * NAMESPACE_DNS：名称字符串为全限定域名；
     * NAMESPACE_URL：名称字符串是一个URL；
     * NAMESPACE_OID：名称字符串是一个 ISO OID；
     * NAMESPACE_X500：名称字符串是 X.500 DN（DER 或文本格式）。
     *
     * @param namespace 预定义的本地域枚举
     * @param name      本地标识符
     * @return UUIDv5
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UuidNamespace namespace, byte[] name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * UUIDv5
     * 返回使用 SHA-1 散列 (UUIDv5) 的基于名称的唯一标识符。
     * RFC-4122（附录 C）预定义的名称空间：
     * NAMESPACE_DNS：名称字符串为全限定域名；
     * NAMESPACE_URL：名称字符串是一个URL；
     * NAMESPACE_OID：名称字符串是一个 ISO OID；
     * NAMESPACE_X500：名称字符串是 X.500 DN（DER 或文本格式）。
     *
     * @param namespace 预定义的本地域枚举
     * @param name      本地标识符
     * @return UUIDv5
     * @see UuidNamespace
     * @see NameBasedSha1Factory
     */
    public static UUID getNameBasedSha1(UuidNamespace namespace, UUID name) {
        return NameBasedSha1Holder.INSTANCE.create(namespace, name);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurity(byte localDomain, int localIdentifier) {
        return DceSecurityHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurityWithMac(byte localDomain, int localIdentifier) {
        return DceSecurityWithMacHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurityWithHash(byte localDomain, int localIdentifier) {
        return DceSecurityWithHashHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurityWithRandom(byte localDomain, int localIdentifier) {
        return DceSecurityWithRandomHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     * DCE 1.1 认证和安全服务（第 11 章）预定义的本地域：
     * LOCAL_DOMAIN_PERSON：0（解释为 POSIX UID 域）；
     * LOCAL_DOMAIN_GROUP：1（解释为 POSIX GID 域）；
     * LOCAL_DOMAIN_ORG：2。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurity(UuidLocalDomain localDomain, int localIdentifier) {
        return DceSecurityHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     * DCE 1.1 认证和安全服务（第 11 章）预定义的本地域：
     * LOCAL_DOMAIN_PERSON：0（解释为 POSIX UID 域）；
     * LOCAL_DOMAIN_GROUP：1（解释为 POSIX GID 域）；
     * LOCAL_DOMAIN_ORG：2。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurityWithMac(UuidLocalDomain localDomain, int localIdentifier) {
        return DceSecurityWithMacHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     * DCE 1.1 认证和安全服务（第 11 章）预定义的本地域：
     * LOCAL_DOMAIN_PERSON：0（解释为 POSIX UID 域）；
     * LOCAL_DOMAIN_GROUP：1（解释为 POSIX GID 域）；
     * LOCAL_DOMAIN_ORG：2。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurityWithHash(UuidLocalDomain localDomain, int localIdentifier) {
        return DceSecurityWithHashHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * UUIDv2
     * 返回 DCE 安全唯一标识符 (UUIDv2)。
     * DCE 1.1 认证和安全服务（第 11 章）预定义的本地域：
     * LOCAL_DOMAIN_PERSON：0（解释为 POSIX UID 域）；
     * LOCAL_DOMAIN_GROUP：1（解释为 POSIX GID 域）；
     * LOCAL_DOMAIN_ORG：2。
     *
     * @param localDomain     预定义的本地域枚举
     * @param localIdentifier 本地标识符
     * @return UUIDv2
     * @see UuidLocalDomain
     * @see DceSecurityFactory
     */
    public static UUID getDceSecurityWithRandom(UuidLocalDomain localDomain, int localIdentifier) {
        return DceSecurityWithRandomHolder.INSTANCE.create(localDomain, localIdentifier);
    }

    /**
     * GUID
     * 返回前缀 COMB GUID。
     * 创建毫秒是一个 6 字节的 PREFIX 位于最高有效位。
     *
     * @return GUID
     * @see PrefixCombFactory
     */
    public static UUID getPrefixComb() {
        return PrefixCombHolder.INSTANCE.create();
    }

    /**
     * GUID
     * 返回后缀 COMB GUID。
     * 创建毫秒是一个 6 字节的 SUFFIX 位于最低有效位。
     *
     * @return GUID
     * @see SuffixCombFactory
     */
    public static UUID getSuffixComb() {
        return SuffixCombHolder.INSTANCE.create();
    }

    /**
     * 返回 n 短前缀 COMB GUID。
     * 创建分钟是 2 个字节的 PREFIX 位于最高有效位。
     * 前缀每 ~45 天环绕一次 (2^16/60/24 = ~45)。
     *
     * @return GUID
     * @see ShortPrefixCombFactory
     */
    public static UUID getShortPrefixComb() {
        return ShortPrefixCombHolder.INSTANCE.create();
    }

    /**
     * GUID
     * 返回一个短后缀 COMB GUID。
     * 创建分钟是 2 个字节的 SUFFIX 位于最低有效位。
     * 后缀每 ~45 天环绕一次 (2^16/60/24 = ~45)。
     *
     * @return GUID
     * @see ShortSuffixCombFactory
     */
    public static UUID getShortSuffixComb() {
        return ShortSuffixCombHolder.INSTANCE.create();
    }

    // ***************************************
    // Lazy holders
    // ***************************************

    private static class RandomBasedHolder {
        static final RandomBasedFactory INSTANCE = new RandomBasedFactory();
    }

    private static class TimeBasedHolder {
        static final TimeBasedFactory INSTANCE = new TimeBasedFactory();
    }

    private static class TimeBasedWithMacHolder {
        static final TimeBasedFactory INSTANCE = new TimeBasedFactory.Builder().withMacNodeId().build();
    }

    private static class TimeBasedWithHashHolder {
        static final TimeBasedFactory INSTANCE = new TimeBasedFactory.Builder().withHashNodeId().build();
    }

    private static class TimeBasedWithRandomHolder {
        static final TimeBasedFactory INSTANCE = new TimeBasedFactory.Builder().withRandomNodeId().build();
    }

    private static class TimeOrderedHolder {
        static final TimeOrderedFactory INSTANCE = new TimeOrderedFactory();
    }

    private static class TimeOrderedWithMacHolder {
        static final TimeOrderedFactory INSTANCE = new TimeOrderedFactory.Builder().withMacNodeId().build();
    }

    private static class TimeOrderedWithHashHolder {
        static final TimeOrderedFactory INSTANCE = new TimeOrderedFactory.Builder().withHashNodeId().build();
    }

    private static class TimeOrderedWithRandomHolder {
        static final TimeOrderedFactory INSTANCE = new TimeOrderedFactory.Builder().withRandomNodeId().build();
    }

    private static class TimeOrderedEpochHolder {
        static final TimeOrderedEpochFactory INSTANCE = new TimeOrderedEpochFactory();
    }

    private static class TimeOrderedEpochPlus1Holder {
        static final TimeOrderedEpochFactory INSTANCE = TimeOrderedEpochFactory.builder().withIncrementPlus1().build();
    }

    private static class TimeOrderedEpochPlusNHolder {
        static final TimeOrderedEpochFactory INSTANCE = TimeOrderedEpochFactory.builder().withIncrementPlusN().build();
    }

    private static class NameBasedMd5Holder {
        static final NameBasedMd5Factory INSTANCE = new NameBasedMd5Factory();
    }

    private static class NameBasedSha1Holder {
        static final NameBasedSha1Factory INSTANCE = new NameBasedSha1Factory();
    }

    private static class DceSecurityHolder {
        static final DceSecurityFactory INSTANCE = new DceSecurityFactory();
    }

    private static class DceSecurityWithMacHolder {
        static final DceSecurityFactory INSTANCE = new DceSecurityFactory.Builder().withMacNodeId().build();
    }

    private static class DceSecurityWithHashHolder {
        static final DceSecurityFactory INSTANCE = new DceSecurityFactory.Builder().withHashNodeId().build();
    }

    private static class DceSecurityWithRandomHolder {
        static final DceSecurityFactory INSTANCE = new DceSecurityFactory.Builder().withRandomNodeId().build();
    }

    private static class SuffixCombHolder {
        static final SuffixCombFactory INSTANCE = new SuffixCombFactory();
    }

    private static class PrefixCombHolder {
        static final PrefixCombFactory INSTANCE = new PrefixCombFactory();
    }

    private static class ShortPrefixCombHolder {
        static final ShortPrefixCombFactory INSTANCE = new ShortPrefixCombFactory();
    }

    private static class ShortSuffixCombHolder {
        static final ShortSuffixCombFactory INSTANCE = new ShortSuffixCombFactory();
    }
}
