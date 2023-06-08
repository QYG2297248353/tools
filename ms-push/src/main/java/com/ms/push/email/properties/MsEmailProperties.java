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

package com.ms.push.email.properties;

import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsRuntimeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 邮件配置
 *
 * @author ms2297248353
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ms.push.mail.send")
public class MsEmailProperties {
    /**
     * 是否开启认证
     * 默认开启
     */
    private Boolean auth;

    /**
     * 用户名
     * 登录邮件服务器的用户名
     */
    private String username;

    /**
     * 密码
     * 登录邮件服务器的密码
     */
    private String password;

    /**
     * 发件人
     * 发送邮件的邮箱地址
     * 与username可能不一致 例如qq邮箱
     */
    private String from;

    /**
     * 邮箱服务器地址
     * 格式smtp.xxx.xxx
     */
    private String host;

    /**
     * 邮箱服务器端口
     * 默认465
     * 可选值：25、465、587
     */
    private Integer port;

    // 以下为可选配置

    /**
     * 是否开启调试模式
     * 默认关闭
     */
    private Boolean debug;

    /**
     * 发送协议
     * session的协议
     * <p>
     * 默认smtp
     * 可选值：smtp
     * 以下为收信协议
     * pop3、imap
     */
    private String transport;

    /**
     * 读取超时
     * 单位 毫秒
     * 默认 不超时
     */
    private String timeout;

    /**
     * 连接超时
     * 单位 毫秒
     * 默认 不超时
     */
    private String connectionTimeout;

    /**
     * 是否开启ssl
     * 默认开启
     */
    private Boolean ssl;

    /**
     * 是否使用代理
     */
    private Boolean proxy;

    /**
     * 代理地址
     */
    private String proxyHost;

    /**
     * 代理端口
     */
    private Integer proxyPort;

    /**
     * 代理用户名
     */
    private String proxyUsername;

    /**
     * 代理密码
     */
    private String proxyPassword;

    private MsEmailProperties() {
        debug = false;
        auth = true;
        port = 465;
        ssl = true;
        proxy = false;
        transport = "smtp";
    }


    public MsEmailProperties(String username, String password, String from, String host, Integer port) {
        this();
        this.username = username;
        this.password = password;
        this.from = from;
        this.host = host;
        this.port = port;
    }

    public MsEmailProperties(String username, String password, String from, String host) {
        this(username, password, from, host, 465);
    }

    public String getDebugStr() {
        return String.valueOf(debug);
    }

    public String getAuthStr() {
        return String.valueOf(auth);
    }

    public String getPortStr() {
        return String.valueOf(port);
    }

    public String getUsername() {
        if (Strings.isBlank(username)) {
            username = from;
        }
        if (Strings.isBlank(username) && Strings.isBlank(from)) {
            throw new MsToolsRuntimeException("ms.push.mail.send.username 或 ms.push.mail.send.username 至少配置其一");
        }
        return username;
    }

    public String getFrom() {
        if (Strings.isBlank(from)) {
            from = username;
        }
        if (Strings.isBlank(username) && Strings.isBlank(from)) {
            throw new MsToolsRuntimeException("ms.push.mail.send.username 或 ms.push.mail.send.username 至少配置其一");
        }
        return from;
    }

    public MsBoxEmailProperties getReceive() {
        MsBoxEmailProperties receive = MsBoxEmailProperties.build();
        receive.setAuth(auth);
        receive.setUsername(username);
        receive.setPassword(password);
        receive.setHost(host);
        receive.setPort(port);
        receive.setDebug(debug);
        String trans = "smtp".equals(transport) ? "imap" : transport;
        receive.setTransport(trans);
        receive.setTimeout(timeout);
        receive.setConnectionTimeout(connectionTimeout);
        receive.setSsl(ssl);
        receive.setProxy(proxy);
        receive.setProxyHost(proxyHost);
        receive.setProxyPort(proxyPort);
        receive.setProxyUsername(proxyUsername);
        receive.setProxyPassword(proxyPassword);
        return receive;
    }
}