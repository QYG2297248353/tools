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

package com.ms.tools.push.email;

import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.push.email.factory.EmailFactory;
import com.ms.tools.push.email.factory.EmailMimeMessageBuilders;
import com.ms.tools.push.email.properties.MsEmailProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author ms2297248353
 */
@Component
public class EmailPushUtils {

    @Resource
    private MsEmailProperties msEmailProperties;

    private EmailPushUtils() {
    }

    public EmailPushUtils(MsEmailProperties msEmailProperties) {
        this();
        if (msEmailProperties != null) {
            this.msEmailProperties = msEmailProperties;
        }
        if (this.msEmailProperties == null) {
            throw new IllegalArgumentException("MailUtils初始化失败，配置文件未加载");
        }
    }

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱地址
     * @param subject 标题
     * @param content 内容 纯文本
     * @throws MsToolsException 异常
     */
    public void send(String to, String subject, String content) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).send(to, subject, content);
    }

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱地址
     * @param subject 标题
     * @param content 内容 纯文本
     * @param file    附件 文件夹将会取出文件列表
     * @throws MsToolsException 异常
     */
    public void send(String to, String subject, String content, File... file) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).sendAttachment(to, subject, content, file);
    }

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱地址
     * @param subject 标题
     * @param content 内容 Html语言
     * @throws MsToolsException 异常
     */
    public void sendHtml(String to, String subject, String content) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).sendHtml(to, subject, content);
    }

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱地址
     * @param subject 标题
     * @param content 内容 Html语言
     * @param file    附件 文件夹将会取出文件列表
     * @throws MsToolsException 异常
     */
    public void sendHtml(String to, String subject, String content, File... file) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).sendHtmlAttachment(to, subject, content, file);
    }

    /**
     * 发送邮件
     *
     * @param build 接口
     * @throws MsToolsException 异常
     */
    public void send(EmailFactory.MailBuild build) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).send(build);
    }

    /**
     * 发送邮件
     *
     * @param message 接口
     * @throws MsToolsException 异常
     */
    public void send(MimeMessage message) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).send(message);
    }

    /**
     * 发送邮件
     *
     * @param builders 接口
     * @throws MsToolsException 异常
     */
    public void send(EmailMimeMessageBuilders.EmailMimeMessageBuilder builders) throws MsToolsException {
        EmailFactory.getInstance(msEmailProperties).send(builders);
    }

}
