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

package com.ms.push.mail.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ms.mail")
public class MailConfig {

    private static final String SMTP = "smtp";
    private static final String POP = "pop";
    private static final String IMAP = "imap";
    /**
     * 发送邮箱账户
     */
    @Value("${ms.mail.email:qyg2297248353@126.com}")
    private String email;
    /**
     * 发送账户(邮箱相同)
     */
    @Value("${ms.mail.account:qyg2297248353@126.com}")
    private String account;
    /**
     * 密码(授权码)
     */
    @Value("${ms.mail.password:TZRNTITPOBSLUMAH}")
    private String password;
    /**
     * 服务器地址
     */
    @Value("${ms.mail.host:smtp.126.com}")
    private String host;
    /**
     * 端口
     */
    @Value("${ms.mail.port:465}")
    private String port;
    /**
     * 是否开启验证
     */
    @Value("${ms.mail.auth:true}")
    private Boolean auth;
    /**
     * 是否启用SSL链接
     */
    @Value("${ms.mail.ssl:true}")
    private Boolean ssl;
    /**
     * SMTP超时时间
     */
    @Value("${ms.mail.timeout:3000}")
    private String timeout;
    /**
     * Socket超时时间
     */
    @Value("${ms.mail.socket-timeout:3000}")
    private String socketTimeout;
    /**
     * 是否开启debug输出
     */
    @Value("${ms.mail.debug:false}")
    private Boolean debug;

    public MailConfig() {
        account = email = "qyg2297248353@126.com";
        password = "TZRNTITPOBSLUMAH";
        host = "smtp.126.com";
        port = "465";
        auth = true;
        ssl = true;
        timeout = "3000";
        socketTimeout = "3000";
        debug = false;
    }

    public String getProtocol() {
        if (host.toLowerCase().startsWith(POP)) {
            return POP;
        }
        if (host.toLowerCase().startsWith(IMAP)) {
            return IMAP;
        }
        return SMTP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public void setSsl(Boolean ssl) {
        this.ssl = ssl;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(String socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }
}
