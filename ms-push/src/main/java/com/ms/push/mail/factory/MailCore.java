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

package com.ms.push.mail.factory;

import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.push.mail.properties.MsMailProperties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author ms2297248353
 */
public class MailCore {

    private MsMailProperties msMailProperties;

    private Session session;

    private Transport transport;


    protected MailCore(MsMailProperties msMailProperties) {
        this.msMailProperties = msMailProperties;
        try {
            createTransport();
        } catch (Exception e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    public static void sendMail(MailCore core, MimeMessage message) throws MessagingException {
        Transport transport = core.getTransport();
        transport.sendMessage(message, message.getAllRecipients());
        core.close();
    }

    private Session createSession() throws NoSuchProviderException {
        Properties prop = new Properties();
        String smtp = "smtp";
        if (Boolean.TRUE.equals(msMailProperties.getSsl())) {
            prop.setProperty("mail.smtp.ssl.enable", "true");
            smtp = "smtps";
        }
        prop.setProperty("mail." + smtp + ".host", msMailProperties.getHost());
        prop.setProperty("mail." + smtp + ".port", msMailProperties.getPortStr());

        prop.setProperty("mail." + smtp + ".user", msMailProperties.getUsername());
        prop.setProperty("mail.smtp.from", msMailProperties.getFrom());

        prop.setProperty("mail." + smtp + ".auth", msMailProperties.getAuthStr());

        if (msMailProperties.getTimeout() != null) {
            prop.setProperty("mail." + smtp + ".timeout", msMailProperties.getTimeout());
        }
        if (msMailProperties.getConnectionTimeout() != null) {
            prop.setProperty("mail." + smtp + ".connectiontimeout", msMailProperties.getConnectionTimeout());
        }

        if (Boolean.TRUE.equals(msMailProperties.getProxy())) {
            prop.setProperty("mail." + smtp + ".proxy.host", msMailProperties.getProxyHost());
            prop.setProperty("mail." + smtp + ".proxy.port", msMailProperties.getProxyPort());
            if (msMailProperties.getProxyUsername() != null) {
                prop.setProperty("mail." + smtp + ".proxy.user", msMailProperties.getProxyUsername());
                prop.setProperty("mail." + smtp + ".proxy.password", msMailProperties.getProxyPassword());
            }
        }

        prop.setProperty("mail." + smtp + ".socketFactory.fallback", "false");
        prop.setProperty("mail." + smtp + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail." + smtp + ".socketFactory.port", msMailProperties.getPortStr());

        prop.setProperty("mail.debug", msMailProperties.getDebugStr());
        session = Session.getInstance(prop);
        transport = session.getTransport(smtp);
        return session;
    }

    protected Transport createTransport() throws Exception {
        createSession();
        transport.connect(msMailProperties.getHost(), msMailProperties.getPort(), msMailProperties.getUsername(), msMailProperties.getPassword());
        return transport;
    }

    protected void close() throws MessagingException {
        if (transport != null) {
            transport.close();
        }
    }

    protected Session getSession() {
        return session;
    }

    protected Transport getTransport() {
        return transport;
    }
}