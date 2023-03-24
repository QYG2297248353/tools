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
import com.ms.push.mail.factory.MailFactory;
import com.ms.push.mail.properties.MsMailProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author ms2297248353
 */
@Component
public class MailUtils {

    @Resource
    private MsMailProperties msMailProperties;

    private MailUtils() {
    }

    public MailUtils(MsMailProperties msMailProperties) {
        this();
        if (msMailProperties != null) {
            this.msMailProperties = msMailProperties;
        }
        if (this.msMailProperties == null) {
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
        MailFactory.getInstance(msMailProperties).send(to, subject, content);
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
        MailFactory.getInstance(msMailProperties).sendAttachment(to, subject, content, file);
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
        MailFactory.getInstance(msMailProperties).sendHtml(to, subject, content);
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
        MailFactory.getInstance(msMailProperties).sendHtmlAttachment(to, subject, content, file);
    }

    /**
     * 发送邮件
     *
     * @param build 接口
     * @throws MsToolsException 异常
     */
    public void send(MailFactory.MailBuild build) throws MsToolsException {
        MailFactory.getInstance(msMailProperties).send(build);
    }

}
