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

package com.ms.push.email.factory;

import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.push.email.properties.MsEmailProperties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author ms2297248353
 */
public class EmailCore {

    private MsEmailProperties msEmailProperties;

    private Session session;

    private Transport transport;


    protected EmailCore(MsEmailProperties msEmailProperties) {
        this.msEmailProperties = msEmailProperties;
        try {
            createSession();
        } catch (Exception e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    protected static void sendMail(EmailCore core, MimeMessage message) throws MessagingException {
        Transport transport = core.getTransport();
        transport.sendMessage(message, message.getAllRecipients());
        core.close();
    }

    private Session createSession() throws NoSuchProviderException {
        Properties prop = new Properties();
        final String smtp = "smtp";
        if (Boolean.TRUE.equals(msEmailProperties.getSsl())) {
            prop.setProperty("mail.smtp.ssl.enable", "true");
        }
        prop.setProperty("mail." + smtp + ".host", msEmailProperties.getHost());
        prop.setProperty("mail." + smtp + ".port", msEmailProperties.getPortStr());

        prop.setProperty("mail." + smtp + ".user", msEmailProperties.getUsername());
        prop.setProperty("mail.smtp.from", msEmailProperties.getFrom());

        prop.setProperty("mail." + smtp + ".auth", msEmailProperties.getAuthStr());

        if (msEmailProperties.getTimeout() != null) {
            prop.setProperty("mail." + smtp + ".timeout", msEmailProperties.getTimeout());
        }
        if (msEmailProperties.getConnectionTimeout() != null) {
            prop.setProperty("mail." + smtp + ".connectiontimeout", msEmailProperties.getConnectionTimeout());
        }

        if (Boolean.TRUE.equals(msEmailProperties.getProxy())) {
            prop.setProperty("mail." + smtp + ".proxy.host", msEmailProperties.getProxyHost());
            prop.setProperty("mail." + smtp + ".proxy.port", String.valueOf(msEmailProperties.getProxyPort()));
            if (msEmailProperties.getProxyUsername() != null) {
                prop.setProperty("mail." + smtp + ".proxy.user", msEmailProperties.getProxyUsername());
                prop.setProperty("mail." + smtp + ".proxy.password", msEmailProperties.getProxyPassword());
            }
        }

        prop.setProperty("mail." + smtp + ".socketFactory.fallback", "false");
        prop.setProperty("mail." + smtp + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail." + smtp + ".socketFactory.port", msEmailProperties.getPortStr());

        prop.setProperty("mail.debug", msEmailProperties.getDebugStr());
        session = Session.getInstance(prop);
        transport = session.getTransport(smtp);
        return session;
    }

    protected Transport connect() throws Exception {
        createSession();
        transport.connect(msEmailProperties.getHost(), msEmailProperties.getPort(), msEmailProperties.getUsername(), msEmailProperties.getPassword());
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
        try {
            connect();
        } catch (Exception e) {
            throw new MsToolsRuntimeException(e);
        }
        return transport;
    }
}