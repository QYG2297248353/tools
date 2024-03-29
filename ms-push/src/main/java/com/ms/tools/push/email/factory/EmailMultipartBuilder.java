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

package com.ms.tools.push.email.factory;

import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.resources.file.FileFindUtils;
import com.ms.tools.resources.file.FilesUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.IOException;


/**
 * 子节点构建器
 *
 * @author ms2297248353
 */
public class EmailMultipartBuilder {

    public EmailMultipartBuilder() {
    }

    public static EmailMultipartBuilder create() {
        return new EmailMultipartBuilder();
    }

    /**
     * 构建文本节点
     *
     * @param text 文本
     * @return 对象
     */
    public EmailMultipart builder(String text) {
        try {
            MimeBodyPart part = new MimeBodyPart();
            part.setText(text, "UTF-8", "text/plain");
            return new EmailMultipart(part);
        } catch (MessagingException ignored) {

        }
        return new EmailMultipart();
    }

    /**
     * 构建html节点
     *
     * @param html html
     * @return 对象
     */
    public EmailMultipart builderHtml(String html) {
        try {
            MimeBodyPart part = new MimeBodyPart();
            part.setText(html, "UTF-8", "text/html");
            return new EmailMultipart(part);
        } catch (MessagingException ignored) {

        }
        return new EmailMultipart();
    }

    /**
     * 构建文本节点
     *
     * @param text     文本
     * @param encoding 编码
     * @return 对象
     */
    public EmailMultipart builder(String text, String encoding) {
        try {
            MimeBodyPart part = new MimeBodyPart();
            part.setText(text, encoding, "text/plain");
            return new EmailMultipart(part);
        } catch (MessagingException ignored) {

        }
        return new EmailMultipart();
    }

    /**
     * 构建html节点
     *
     * @param html     html
     * @param encoding 编码
     * @return 对象
     */
    public EmailMultipart builderHtml(String html, String encoding) {
        try {
            MimeBodyPart part = new MimeBodyPart();
            part.setText(html, encoding, "text/html");
            return new EmailMultipart(part);
        } catch (MessagingException ignored) {

        }
        return new EmailMultipart();
    }

    /**
     * 构建自定义节点
     *
     * @param custom      结构对象
     * @param contentType 结构类型 Mime type of the object
     * @return 对象
     */
    public EmailMultipart builderCustom(Object custom, String contentType) {
        try {
            MimeBodyPart part = new MimeBodyPart();
            part.setContent(custom, contentType);
            return new EmailMultipart(part);
        } catch (MessagingException ignored) {

        }
        return new EmailMultipart();
    }

    /**
     * 构建附件节点
     *
     * @param fileBytes 文件字节
     * @return 对象
     */
    public EmailMultipart builderFile(byte[] fileBytes) {
        try {
            File file = FilesUtils.writeToTempFile(fileBytes);
            return builderFile(null, file, null);
        } catch (MsToolsException ignored) {
        }
        return new EmailMultipart();
    }

    /**
     * 构建附件节点
     *
     * @param fileBytes   文件字节
     * @param contentType 文件类型
     * @return 对象
     */
    public EmailMultipart builderFile(byte[] fileBytes, String contentType) {
        try {
            File file = FilesUtils.writeToTempFile(fileBytes);
            return builderFile(null, file, contentType);
        } catch (MsToolsException ignored) {
        }
        return new EmailMultipart();
    }

    /**
     * 构建附件节点
     *
     * @param path 文件路径
     * @return 对象
     */
    public EmailMultipart builderFile(String path) {
        return builderFile("", path);
    }

    /**
     * 构建附件节点
     *
     * @param fileName 文件名
     * @param path     文件路径
     * @return 对象
     */
    public EmailMultipart builderFile(String fileName, String path) {
        try {
            File file = FileFindUtils.findFile(path);
            return builderFile(fileName, file, null);
        } catch (MsToolsException ignored) {
        }
        return new EmailMultipart();
    }

    /**
     * 构建附件节点
     *
     * @param file 文件
     * @return 对象
     */
    public EmailMultipart builderFile(File file) {
        return builderFile(null, file, null);
    }

    /**
     * 构建附件节点
     *
     * @param fileName 文件名
     * @param file     文件
     * @return 对象
     */
    public EmailMultipart builderFile(String fileName, File file) {
        return builderFile(fileName, file, null);
    }

    /**
     * 构建附件节点
     *
     * @param fileName    文件名
     * @param file        文件
     * @param contentType 文件类型
     * @return 对象
     */
    public EmailMultipart builderFile(String fileName, File file, String contentType) {
        return builderFile(fileName, file, contentType, null);
    }

    /**
     * 构建附件节点
     *
     * @param file        文件
     * @param contentType 文件类型
     * @return 对象 编码
     */
    public EmailMultipart builderFile(File file, String contentType) {
        return builderFile(null, file, contentType, null);
    }

    /**
     * 构建附件节点
     *
     * @param file        文件
     * @param contentType 文件类型
     * @param encoding    编码
     * @return 对象
     */
    public EmailMultipart builderFile(File file, String contentType, String encoding) {
        return builderFile(null, file, contentType, encoding);
    }

    /**
     * 构建附件节点
     *
     * @param fileName    文件名
     * @param file        文件
     * @param contentType 文件类型
     * @param encoding    编码
     * @return 对象
     */
    public EmailMultipart builderFile(String fileName, File file, String contentType, String encoding) {
        try {
            MimeBodyPart part = new MimeBodyPart();
            part.setFileName(Strings.isBlank(fileName) ? file.getName() : fileName);
            part.attachFile(file, contentType, encoding);
            return new EmailMultipart(part);
        } catch (MessagingException | IOException ignored) {

        }
        return new EmailMultipart();
    }

    public class EmailMultipart {
        private MimeBodyPart part;

        private boolean status;

        public EmailMultipart() {
            status = false;
        }

        public EmailMultipart(MimeBodyPart part, boolean status) {
            this(part);
            this.status = status;
        }

        public EmailMultipart(MimeBodyPart part) {
            this();
            this.part = part;
        }

        protected MimeBodyPart build() {
            status = part != null;
            return part;
        }

        protected boolean status() {
            return status;
        }
    }


}
