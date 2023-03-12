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

package com.ms.push.mail;

import com.ms.core.exception.base.MsToolsException;
import com.ms.push.mail.factory.MailBuild;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ms2297248353
 */
@Service
public class MailUtils {
    @Resource
    private MailBuild mailBuild;

    /**
     * 发送邮件
     *
     * @param to          收信人
     * @param subject     主题
     * @param content     内容
     * @param isHtml      是否为Html
     * @param attachments 附件(可选)
     * @throws MsToolsException 异常
     */
    public void sendEmail(String to, String subject, String content, boolean isHtml, String... attachments) throws MsToolsException {
        sendEmail(to, null, null, subject, content, isHtml, attachments);
    }

    /**
     * 发送邮件
     *
     * @param to          收信人
     * @param subject     主题
     * @param content     内容
     * @param isHtml      是否为Html
     * @param attachments 附件(可选)
     * @throws MsToolsException 异常
     */
    public void sendEmail(String[] to, String subject, String content, boolean isHtml, String... attachments) throws MsToolsException {
        sendEmails(to, null, null, subject, content, isHtml, attachments);
    }

    /**
     * 发送邮件
     *
     * @param to          收信人
     * @param cc          抄送人
     * @param subject     主题
     * @param content     内容
     * @param isHtml      是否为Html
     * @param attachments 附件(可选)
     * @throws MsToolsException 异常
     */
    public void sendEmail(String to, String cc, String subject, String content, boolean isHtml, String... attachments) throws MsToolsException {
        sendEmail(to, cc, null, subject, content, isHtml, attachments);
    }

    /**
     * 发送邮件
     *
     * @param to          收信人 看不见密送人信息
     * @param cc          抄送人 看不见密送人信息
     * @param bcc         密送人 完整信息
     * @param subject     主题
     * @param content     内容
     * @param isHtml      是否为Html
     * @param attachments 附件(可选)
     * @throws MsToolsException 异常
     */
    public void sendEmail(String to, String cc, String bcc, String subject, String content, boolean isHtml, String... attachments) throws MsToolsException {
        MailBuild factory = getMailBuildFactory();
        MimeMessage mimeMessage = factory.mailBuild(to, cc, bcc, subject, content, isHtml, attachments);
        try {
            factory.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 发送邮件
     *
     * @param to          收信人 看不见密送人信息
     * @param cc          抄送人 看不见密送人信息
     * @param bcc         密送人 完整信息
     * @param subject     主题
     * @param content     内容
     * @param isHtml      是否为Html
     * @param attachments 附件(可选)
     * @throws MsToolsException 异常
     */
    public void sendEmails(String[] to, String[] cc, String[] bcc, String subject, String content, boolean isHtml, String... attachments) throws MsToolsException {
        MailBuild factory = getMailBuildFactory();
        MimeMessage mimeMessage = factory.mailBuild(to, cc, bcc, subject, content, isHtml, attachments);
        try {
            factory.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MsToolsException(e);
        }
    }


    private MailBuild getMailBuildFactory() {
        if (mailBuild == null) {
            mailBuild = new MailBuild();
        }
        return mailBuild;
    }
}
