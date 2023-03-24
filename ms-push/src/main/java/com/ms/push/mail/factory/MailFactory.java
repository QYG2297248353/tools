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

import com.ms.core.exception.base.MsToolsException;
import com.ms.push.mail.properties.MsMailProperties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author ms2297248353
 */
public class MailFactory {

    private MailCore core;

    private MailFactory() {
    }

    public static MailFactory getInstance(MsMailProperties msMailProperties) {
        MailFactoryHolder.INSTANCE.core = MailFactoryHolder.INSTANCE.create(msMailProperties);
        return MailFactoryHolder.INSTANCE;
    }

    private MailCore create(MsMailProperties msMailProperties) {
        return new MailCore(msMailProperties);
    }

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public void send(String to, String subject, String content) throws MsToolsException {
        Session session = core.getSession();
        try {
            MimeMessage message = MimeMessageBuilder.create(session)
                    .to(to)
                    .subject(subject)
                    .sendText()
                    .text(content)
                    .build();
            MailCore.sendMail(core, message);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 发送Html邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public void sendHtml(String to, String subject, String content) throws MsToolsException {
        Session session = core.getSession();
        try {
            MimeMessage message = MimeMessageBuilder.create(session)
                    .to(to)
                    .subject(subject)
                    .sendHtml()
                    .html(content)
                    .build();
            MailCore.sendMail(core, message);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param files   附件
     * @throws MsToolsException 异常
     */
    public void sendAttachment(String to, String subject, String content, File... files) throws MsToolsException {
        Session session = core.getSession();
        try {
            MimeMessage message = MimeMessageBuilder.create(session)
                    .to(to)
                    .subject(subject)
                    .mixed()
                    .text(content)
                    .attach(files)
                    .build();
            MailCore.sendMail(core, message);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 发送带附件的Html邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param files   附件
     * @throws MsToolsException 异常
     */
    public void sendHtmlAttachment(String to, String subject, String content, File... files) throws MsToolsException {
        Session session = core.getSession();
        try {
            MimeMessage message = MimeMessageBuilder.create(session)
                    .to(to)
                    .subject(subject)
                    .mixed()
                    .html(content)
                    .attach(files)
                    .build();
            MailCore.sendMail(core, message);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 发送自定义邮件
     *
     * @param mail 构建器
     * @throws MsToolsException 异常
     */
    public void send(MailBuild mail) throws MsToolsException {
        try {
            MimeMessageBuilder builder = MimeMessageBuilder.create(core.getSession());
            MailCore.sendMail(core, mail.build(builder));
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }

    public interface MailBuild {
        /**
         * 构建邮件
         *
         * @param builder 构建器
         * @return 邮件
         */
        MimeMessage build(MimeMessageBuilder builder);
    }


    private static class MailFactoryHolder {
        private static final MailFactory INSTANCE = new MailFactory();
    }


}
