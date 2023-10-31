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

package com.ms.tools.push.email.enums;

/**
 * 邮箱常用服务协议
 * <p>
 * 基本信息
 *
 * @author ms2297248353
 */

public enum EmailServicePortEnum {

    WY163_SMTP_SSL_465("smtp.163.com", "smtp", 465, 25, "网易163"),
    WY163_SMTP_SSL_994("smtp.163.com", "smtp", 994, 25, "网易163"),
    WY163_IMAP_SSL_993("imap.163.com", "imap", 993, 143, "网易163"),
    WY163_POP3_SSL_995("pop.163.com", "pop3", 995, 110, "网易163"),

    WY126_SMTP_SSL_465("smtp.126.com", "smtp", 465, 25, "网易126"),
    WY126_SMTP_SSL_994("smtp.126.com", "smtp", 994, 25, "网易126"),
    WY126_IMAP_SSL_993("imap.126.com", "imap", 993, 143, "网易126"),
    WY126_POP3_SSL_995("pop.126.com", "pop3", 995, 110, "网易126"),

    WY_YEAH_SMTP_SSL_465("smtp.yeah.net", "smtp", 465, 25, "网易yeah.net"),
    WY_YEAH_SMTP_SSL_994("smtp.yeah.net", "smtp", 994, 25, "网易yeah.net"),
    WY_YEAH_IMAP_SSL_993("imap.yeah.net", "imap", 993, 143, "网易yeah.net"),
    WY_YEAH_POP3_SSL_995("pop.yeah.net", "pop3", 995, 110, "网易yeah.net"),

    TX_SMTP_SSL_465("smtp.qq.com", "smtp", 465, 25, "腾讯"),
    TX_SMTP_SSL_587("smtp.qq.com", "smtp", 587, 25, "腾讯"),
    TX_IMAP_SSL_993("imap.qq.com", "imap", 993, 143, "腾讯"),
    TX_POP3_SSL_995("pop.qq.com", "pop3", 995, 110, "腾讯"),
    GG_SMTP_SSL_465("smtp.gmail.com", "smtp", 465, 25, "谷歌"),
    GG_SMTP_SSL_587("smtp.gmail.com", "smtp", 587, 25, "谷歌"),
    GG_IMAP_SSL_993("imap.gmail.com", "imap", 993, 143, "谷歌"),
    GG_POP3_SSL_995("pop.gmail.com", "pop3", 995, 110, "谷歌");

    private final String host;

    private final String protocol;

    private final int port;

    private final int defaultPort;

    private final String desc;

    EmailServicePortEnum(String host, String protocol, int port, int defaultPort, String desc) {
        this.host = host;
        this.protocol = protocol;
        this.port = port;
        this.defaultPort = defaultPort;
        this.desc = desc;
    }

    public String getHost() {
        return host;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getPort() {
        return port;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public String getDesc() {
        return desc;
    }
}
