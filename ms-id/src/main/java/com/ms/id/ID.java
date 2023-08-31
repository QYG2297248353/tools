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

package com.ms.id;


import com.ms.id.factory.ksuid.Ksuid;
import com.ms.id.factory.ksuid.KsuidCreator;
import com.ms.id.factory.snowflake.SnowFlakeCreator;
import com.ms.id.factory.tsid.Tsid;
import com.ms.id.factory.tsid.TsidCreator;
import com.ms.id.factory.ulid.Ulid;
import com.ms.id.factory.ulid.UlidCreator;
import com.ms.id.factory.uuid.UuidCreator;
import com.ms.id.factory.uuid.enums.UuidLocalDomain;
import com.ms.id.factory.uuid.enums.UuidNamespace;

import java.util.UUID;

/**
 * UUID简易版
 * 生成通用唯一标识符的 Java 库
 *
 * @author qyg2297248353
 */
public class ID {

    /**
     * 创建UUID
     * 使用UUID v4版本
     *
     * @return id 32位 连字符4位
     */
    public static String uuid() {
        return v4Uuid();
    }

    /**
     * 创建无分隔符UUID
     *
     * @return id
     */
    public static String createNotDelimiterUuid() {
        return v4Uuid().replace("-", "");
    }

    /**
     * 创建自定义分隔符UUID
     *
     * @param delimiter 分隔符
     * @return id
     */
    public static String createCustomDelimiterUuid(char delimiter) {
        return v4Uuid().replace('-', delimiter);
    }

    /**
     * 创建SnowFlake 雪花Id
     *
     * @return id 19位
     */
    public static Long snowflake() {
        return SnowFlakeCreator.snowflake();
    }

    /**
     * 创建SnowFlake 雪花Id
     *
     * @return id 19位
     */
    public static String snowflakeString() {
        return SnowFlakeCreator.snowflakeString();
    }

    /**
     * 创建ULID
     *
     * @return id 26位
     */
    public static String ulid() {
        Ulid ulid = UlidCreator.getUlid();
        return ulid.toString();
    }

    /**
     * 创建单调ULID
     *
     * @return id 26位
     */
    public static String ulidMonotonic() {
        Ulid ulid = UlidCreator.getMonotonicUlid();
        return ulid.toString();
    }

    /**
     * 创建TSID
     * 为最多 1024 个节点和 4096 ID/ms 创建一个 TSID 字符串
     *
     * @return id 13位
     */
    public static String tsid() {
        Tsid tsid = TsidCreator.getTsid1024();
        return tsid.toString();
    }

    /**
     * 创建TSID
     * 为最多 1024 个节点和 4096 ID/ms 创建一个 TSID 字符串
     *
     * @return id 18位
     */
    public static Long tsidLong() {
        Tsid tsid = TsidCreator.getTsid4096();
        return tsid.toLong();
    }

    /**
     * 创建KSUID
     *
     * @return id 27位
     */
    public static String ksuid() {
        Ksuid ksuid = KsuidCreator.getKsuid();
        return ksuid.toString();
    }

    /**
     * 创建亚秒级KSUID
     *
     * @return id 27位
     */
    public static String ksuidSubSecond() {
        Ksuid ksuid = KsuidCreator.getSubsecondKsuid();
        return ksuid.toString();
    }

    /**
     * 创建单调KSUID
     *
     * @return id 27位
     */
    public static String ksuidMonotonic() {
        Ksuid ksuid = KsuidCreator.getMonotonicKsuid();
        return ksuid.toString();
    }

    /**
     * 创建UUID
     * 使用UUID v4版本
     *
     * @return java.util.UUID
     */
    public static UUID nextUuid() {
        return UuidCreator.getRandomBased();
    }

    /**
     * 创建KSUID
     *
     * @return Ksuid
     */
    public static Ksuid nextKsuid() {
        return KsuidCreator.getKsuid();
    }

    /**
     * 创建TSID
     *
     * @return Tsid
     */
    public static Tsid nextTsid() {
        return TsidCreator.getTsid1024();
    }

    /**
     * 创建ULID
     *
     * @return Ulid
     */
    public static Ulid nextUlid() {
        return UlidCreator.getUlid();
    }

    /**
     * UUID 版本 1：基于时间的版本，具有 RFC-4122 中指定的公历；
     *
     * @return id
     */
    public static String v1Uuid() {
        return UuidCreator.getTimeBased().toString();
    }

    /**
     * UUID 版本 2：DCE 安全版本，具有 DCE 1.1 中指定的嵌入式 POSIX UID；
     *
     * @return id
     */
    public static String v2Uuid() {
        return UuidCreator.getDceSecurity(UuidLocalDomain.LOCAL_DOMAIN_PERSON, 2001).toString();
    }

    /**
     * UUID 版本 3：使用 RFC-4122 中指定的 MD5 散列的基于名称的版本；
     *
     * @param uuidNamespace 类型
     * @param id            参数
     * @return id
     */
    public static String v3Uuid(UuidNamespace uuidNamespace, String id) {
        return UuidCreator.getNameBasedMd5(uuidNamespace, id).toString();
    }

    /**
     * UUID 第 4 版：RFC-4122 中指定的随机或伪随机生成的版本；
     *
     * @return id
     */
    public static String v4Uuid() {
        return UuidCreator.getRandomBased().toString();
    }

    /**
     * UUID 版本 5：使用 RFC-4122 中指定的 SHA-1 散列的基于名称的版本；
     *
     * @param uuidNamespace 类型
     * @param id            参数
     * @return id
     */
    public static String v5Uuid(UuidNamespace uuidNamespace, String id) {
        return UuidCreator.getNameBasedSha1(uuidNamespace, id).toString();
    }

    /**
     * UUID 第 6 版：采用公历的时间排序版本，提议为新的 UUID 格式；
     *
     * @return id
     */
    public static String v6Uuid() {
        return UuidCreator.getTimeOrdered().toString();
    }

    /**
     * UUID 版本 7：按时间排序的 Unix 纪元版本，提议为新的 UUID 格式。
     *
     * @return id
     */
    public static String v7Uuid() {
        return UuidCreator.getTimeOrderedEpoch().toString();
    }

    /**
     * Prefix COMB GUID：随机字节与前缀（毫秒）的组合；
     *
     * @return id
     */
    public static String prefixCombGuid() {
        return UuidCreator.getPrefixComb().toString();
    }

    /**
     * Suffix COMB GUID：随机字节与后缀（毫秒）的组合；
     *
     * @return id
     */
    public static String suffixCombGuid() {
        return UuidCreator.getSuffixComb().toString();
    }

    /**
     * Short Prefix COMB GUID：随机字节与小前缀（分钟）的组合；
     *
     * @return id
     */
    public static String shortPrefixCombGuid() {
        return UuidCreator.getShortPrefixComb().toString();
    }

    /**
     * Short Suffix COMB GUID：带有小后缀（分钟）的随机字节的组合。
     *
     * @return id
     */
    public static String shortSuffixCombGuid() {
        return UuidCreator.getShortSuffixComb().toString();
    }

    /**
     * 短链
     *
     * @param originalURL 原始链接
     * @return 短链
     */
    public static String shortUrl(String originalURL) {
        return RobustShortURLGenerator.generateShortURL(originalURL);
    }

    /**
     * 短链 指定长度
     *
     * @param originalURL 原始链接
     * @param length      长度
     * @return 短链
     */
    public static String shortUrl(String originalURL, int length) {
        return RobustShortURLGenerator.generateShortURL(originalURL, length);
    }

    /**
     * 压缩字符串
     *
     * @param str 字符串
     * @return 压缩后的字符串
     */
    public static String compress(String str) {
        return RobustShortURLGenerator.generateShortURL(str);
    }

    /**
     * 压缩字符串 指定长度
     *
     * @param str    字符串
     * @param length 长度
     * @return 压缩后的字符串
     */
    public static String compress(String str, int length) {
        return RobustShortURLGenerator.generateShortURL(str, length);
    }
}