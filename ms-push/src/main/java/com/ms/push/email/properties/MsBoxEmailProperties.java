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
@ConfigurationProperties(prefix = "ms.push.mail.receive")
public class MsBoxEmailProperties {
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

    private MsBoxEmailProperties() {
        debug = false;
        auth = true;
        port = 993;
        ssl = true;
        proxy = false;
        transport = "imap";
    }


    public MsBoxEmailProperties(String username, String password, String host, Integer port) {
        this();
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public MsBoxEmailProperties(String username, String password, String host) {
        this(username, password, host, 993);
    }

    public static MsBoxEmailProperties build() {
        return new MsBoxEmailProperties();
    }

    public String getUsername() {
        if (Strings.isBlank(username)) {
            throw new MsToolsRuntimeException("ms.push.mail.send.username 请完善配置选项");
        }
        return username;
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
}