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

import com.ms.core.base.basic.StringUtils;
import com.ms.core.base.info.EmailRegexpUtils;
import com.ms.core.base.unit.Coding;
import com.ms.core.exception.base.MsToolsException;
import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.push.mail.enums.MailConfig;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.event.TransportListener;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * @author qyg2297248353
 */
@Component
public class MailBuild {

    private static final String TEXT_CONTENT = "text/plain;charset=utf-8";
    private static final String HTML_CONTENT = "text/html;charset=utf-8";

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    @Resource
    private MailConfig mailConfig;
    private Properties properties;
    private Session session;
    private Transport transport;

    public MailBuild() {
        init();
    }

    private static void sslClient(Properties properties, MailConfig mailConfig) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.smtp.port", mailConfig.getPort());
        properties.setProperty("mail.smtp.socketFactory.port", "465");
    }

    private static void client(Properties properties, MailConfig mailConfig) {
        properties.setProperty("mail.smtp.port", mailConfig.getPort());
    }

    public MimeMessage mailBuild(
            String to,
            String cc,
            String bcc,
            String subject,
            String content,
            boolean isHtml,
            String... attachments) throws MsToolsException {
        return mailBuild(new String[]{to}, new String[]{cc}, new String[]{bcc}, subject, content, isHtml, attachments);
    }

    public MimeMessage mailBuild(
            String[] to,
            String[] cc,
            String[] bcc,
            String subject,
            String content,
            boolean isHtml,
            String... attachments) throws MsToolsException {
        // 创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(getMailConfig().getEmail());
            if (to != null && to.length > 0) {
                addRecipientsTo(msg, to);
            } else {
                throw new MsToolsException("请提供至少1个邮箱地址");
            }
            if (cc != null && cc.length > 0) {
                addRecipientsCc(msg, cc);
            }
            if (bcc != null && bcc.length > 0) {
                addRecipientsBcc(msg, bcc);
            }
            if (StringUtils.isBlank(subject)) {
                throw new MsToolsException("邮件主题不允许为空");
            }
            setSubject(msg, subject);
            setBody(msg, content, isHtml, attachments);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
        return msg;
    }

    private void init() {
        mailConfig = getMailConfig();
        properties = getProperties(mailConfig);
        session = getSession(mailConfig);
        try {
            transport = session.getTransport();
            // 设置发件人的账户名和密码
            transport.connect(mailConfig.getAccount(), mailConfig.getPassword());
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException("Please enter your authorization code to login.", e);
        }
    }

    private Session getSession(MailConfig mailConfig) {
        session = Session.getInstance(properties);
        session.setDebug(mailConfig.getDebug());
        return session;
    }

    private Properties getProperties(MailConfig mailConfig) {
        if (properties == null) {
            properties = new Properties();
            // 设置用户的认证方式
            properties.setProperty("mail.smtp.user", mailConfig.getAccount());
            properties.setProperty("mail.smtp.auth", String.valueOf(mailConfig.getAuth()));
            // 设置传输协议
            properties.setProperty("mail.transport.protocol", mailConfig.getProtocol());
            // 设置发件人的SMTP服务器地址
            properties.setProperty("mail.smtp.host", mailConfig.getHost());
            properties.setProperty("mail.smtp.connectiontimeout", mailConfig.getSocketTimeout());
            properties.setProperty("mail.smtp.timeout", mailConfig.getTimeout());

            if (Boolean.TRUE.equals(mailConfig.getSsl())) {
                // SSL 加密传输
                sslClient(properties, mailConfig);
            } else {
                client(properties, mailConfig);
            }
        }
        return properties;
    }

    private MailConfig getMailConfig() {
        if (mailConfig == null) {
            mailConfig = new MailConfig();
        }
        return mailConfig;
    }


    /**
     * 设置收件人
     *
     * @param msg        邮件信息
     * @param recipients 收件人列表
     * @throws MessagingException 异常
     */
    private void addRecipientsTo(MimeMessage msg, String... recipients) throws MessagingException {
        for (String recipient : recipients) {
            if (!StringUtils.isBlank(recipient) && EmailRegexpUtils.isEmail(recipient)) {
                msg.addRecipients(Message.RecipientType.TO, recipient);
            }
        }
    }

    /**
     * 设置抄送人
     *
     * @param msg        邮件信息
     * @param recipients 抄送人列表
     * @throws MessagingException 异常
     */
    private void addRecipientsCc(MimeMessage msg, String... recipients) throws MessagingException {
        for (String recipient : recipients) {
            if (!StringUtils.isBlank(recipient) && EmailRegexpUtils.isEmail(recipient)) {
                msg.addRecipients(Message.RecipientType.CC, recipient);
            }
        }
    }

    /**
     * 设置密送人
     *
     * @param msg        邮件信息
     * @param recipients 密送人列表
     * @throws MessagingException 异常
     */
    private void addRecipientsBcc(MimeMessage msg, String... recipients) throws MessagingException {
        for (String recipient : recipients) {
            if (!StringUtils.isBlank(recipient) && EmailRegexpUtils.isEmail(recipient)) {
                msg.addRecipients(Message.RecipientType.BCC, recipient);
            }
        }
    }

    /**
     * 设置主题(邮件标题)
     *
     * @param msg     邮件信息
     * @param subject 主题
     * @throws MessagingException 异常
     */
    private void setSubject(MimeMessage msg, String subject) throws MessagingException {
        msg.setSubject(subject, Coding.UTF_8);
    }

    /**
     * 设置正文
     *
     * @param msg        邮件信息
     * @param content    正文内容
     * @param isHtml     是否为html
     * @param attachment 附件地址
     * @throws MessagingException
     */
    private void setBody(MimeMessage msg, String content, boolean isHtml, String... attachment) throws MessagingException {
        Multipart multipart = new MimeMultipart();
        // 正文
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, isHtml ? HTML_CONTENT : TEXT_CONTENT);
        multipart.addBodyPart(messageBodyPart);
        // 附件
        if (attachment != null) {
            for (String str : attachment) {
                if (!StringUtils.isBlank(str)) {
                    BodyPart messageBodyFilePart = new MimeBodyPart();
                    DataSource source = new FileDataSource(str);
                    messageBodyFilePart.setDataHandler(new DataHandler(source));
                    String substring;
                    try {
                        substring = str.substring(str.lastIndexOf(File.separator) + 1);
                        messageBodyFilePart.setFileName(MimeUtility.encodeText(substring));
                    } catch (Exception e) {
                        try {
                            messageBodyFilePart.setFileName(MimeUtility.encodeText(source.getName()));
                        } catch (UnsupportedEncodingException ex) {
                            messageBodyFilePart.setFileName("未知文件");
                        }
                    }
                    multipart.addBodyPart(messageBodyFilePart);
                }
            }
        }
        msg.setContent(multipart);
        buildMimeMessage(msg);
    }

    /**
     * 构建完成
     *
     * @param msg 邮件信息
     * @throws MessagingException 异常
     */
    private void buildMimeMessage(MimeMessage msg) throws MessagingException {
        msg.setSentDate(new Date());
    }

    public void send(MimeMessage msg) throws MessagingException {
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }

    public void send(MimeMessage msg, TransportListener listener) throws MessagingException {
        transport.addTransportListener(listener);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
