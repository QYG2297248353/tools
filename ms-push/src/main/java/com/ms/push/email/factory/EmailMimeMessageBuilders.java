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

import com.ms.core.base.basic.Lists;
import com.ms.core.base.basic.Maps;
import com.ms.core.base.basic.Strings;
import com.ms.core.base.datetime.DateUtils;
import com.ms.core.exception.base.MsToolsException;
import com.ms.id.ID;
import com.ms.resources.file.FilesUtils;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 邮件构造器
 *
 * @author ms2297248353
 */
public class EmailMimeMessageBuilders {

    private static final Logger log = Logger.getLogger(EmailMimeMessageBuilder.class.getName());

    private EmailMimeMessageBuilders() {
    }

    public static EmailMimeMessageBuilder create() {
        return new EmailMimeMessageBuilder();
    }

    public static class EmailMimeMessageBuilder {


        /**
         * 发件人
         */
        private Address from;

        /**
         * 收件人列表
         */
        private List<Address> to;
        /**
         * 抄送人列表
         */
        private List<Address> cc;
        /**
         * 密送人列表
         */
        private List<Address> bcc;

        /**
         * 是否为回复邮件
         */
        private Boolean reply;

        /**
         * 主题
         */
        private String subject;

        /**
         * 描述
         */
        private String description;

        /**
         * 文字内容编码
         */
        private String charset;

        /**
         * 自定义内容
         */
        private Multipart content;

        /**
         * 纯文本内容
         */
        private String text;
        /**
         * 纯文本内容类型
         * <p>
         * 默认值 text/plain
         * 可选
         * text/plain
         * text/html
         * MIME类型
         */
        private String textType;


        /**
         * 附件
         */
        private Map<String, File> attachments;

        /**
         * 发送日期
         */
        private Date sentDate;

        /**
         * 头部字段
         */
        private Map<String, String> headers;

        private EmailMimeMessageBuilder() {
            to = Lists.empty();
            cc = Lists.empty();
            bcc = Lists.empty();
            charset = "UTF-8";
            sentDate = DateUtils.create();
            reply = false;
            headers = Maps.newHashMap();
            textType = "text/plain";
            attachments = Maps.newHashMap();
        }

        /**
         * 发件人-地址
         *
         * @param from 发件人地址
         */
        public EmailMimeMessageBuilder setFrom(Address from) {
            this.from = from;
            return this;
        }

        /**
         * 发件人-地址
         *
         * @param from 发件人地址
         */
        public EmailMimeMessageBuilder setFrom(String from) {
            try {
                this.from = new InternetAddress(from);
            } catch (AddressException e) {
                log.warning("邮件地址错误{" + from + "}");
            }
            return this;
        }

        /**
         * 收件人-地址
         *
         * @param to 收件人地址
         */
        public EmailMimeMessageBuilder addTo(Address... to) {
            this.to = Lists.newArrayList(to);
            return this;
        }

        /**
         * 抄送人-地址
         *
         * @param bcc 抄送人地址
         */
        public EmailMimeMessageBuilder addBcc(Address... bcc) {
            this.bcc = Lists.newArrayList(bcc);
            return this;
        }

        /**
         * 密送人-地址
         *
         * @param cc 密送人地址
         */
        public EmailMimeMessageBuilder addCc(Address... cc) {
            this.cc = Lists.newArrayList(cc);
            return this;
        }

        /**
         * 收件人-地址
         *
         * @param to 收件人地址
         */
        public EmailMimeMessageBuilder addTo(String... to) {
            for (String email : to) {
                try {
                    InternetAddress internetAddress = new InternetAddress(email);
                    this.to.add(internetAddress);
                } catch (AddressException e) {
                    log.warning("邮件地址错误{" + email + "}");
                }
            }
            return this;
        }

        /**
         * 抄送人-地址
         *
         * @param bcc 抄送人地址
         */
        public EmailMimeMessageBuilder addBcc(String... bcc) {
            for (String email : bcc) {
                try {
                    InternetAddress internetAddress = new InternetAddress(email);
                    this.bcc.add(internetAddress);
                } catch (AddressException e) {
                    log.warning("邮件地址错误{" + email + "}");
                }
            }
            return this;
        }

        /**
         * 密送人-地址
         *
         * @param cc 密送人地址
         */
        public EmailMimeMessageBuilder addCc(String... cc) {
            for (String email : cc) {
                try {
                    InternetAddress internetAddress = new InternetAddress(email);
                    this.cc.add(internetAddress);
                } catch (AddressException e) {
                    log.warning("邮件地址错误{" + email + "}");
                }
            }
            return this;
        }

        /**
         * 设置为回复邮件
         */
        public EmailMimeMessageBuilder setReply() {
            reply = true;
            return this;
        }

        /**
         * 是否为回复邮件
         *
         * @param reply 是否为回复邮件
         */
        public EmailMimeMessageBuilder setReply(Boolean reply) {
            this.reply = reply != null && reply;
            return this;
        }

        /**
         * 主题
         *
         * @param subject 主题
         */
        public EmailMimeMessageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * 主题
         *
         * @param subject 主题
         * @param charset 编码
         */
        public EmailMimeMessageBuilder setSubject(String subject, String charset) {
            this.subject = subject;
            this.charset = charset;
            return this;
        }

        /**
         * 描述
         *
         * @param description 描述
         */
        public EmailMimeMessageBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * 编码
         *
         * @param charset 编码
         */
        public EmailMimeMessageBuilder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        /**
         * 添加附件
         *
         * @param file 附件文件
         */
        public EmailMimeMessageBuilder addAttachment(File file) {
            String name = file.getName();
            attachments.put(name, file);
            return this;
        }

        /**
         * 添加附件
         *
         * @param name 附件名称
         * @param file 附件文件
         */
        public EmailMimeMessageBuilder addAttachment(String name, File file) {
            attachments.put(name, file);
            return this;
        }

        /**
         * 添加附件
         *
         * @param name       附件名称
         * @param fileStream 附件文件流
         */
        public EmailMimeMessageBuilder addAttachment(String name, InputStream fileStream) {
            try {
                File file = FilesUtils.writeToTempFile(fileStream);
                attachments.put(name, file);
            } catch (MsToolsException e) {
                log.warning("附件添加失败：" + e.getMessage());
            }
            return this;
        }

        /**
         * 添加附件
         *
         * @param name       附件名称
         * @param fileStream 附件文件流
         * @param suffix     附件后缀 例如：.jpg .json
         */
        public EmailMimeMessageBuilder addAttachment(String name, InputStream fileStream, String suffix) {
            try {
                File file = FilesUtils.writeToTempFile(fileStream, suffix);
                attachments.put(name, file);
            } catch (MsToolsException e) {
                log.warning("附件添加失败：" + e.getMessage());
            }
            return this;
        }

        /**
         * 添加附件
         *
         * @param attachments 附件
         */
        public EmailMimeMessageBuilder addAttachments(Map<String, File> attachments) {
            this.attachments.putAll(attachments);
            return this;
        }

        /**
         * 发送日期
         *
         * @param sentDate 发送日期
         */
        public EmailMimeMessageBuilder setSentDate(long sentDate) {
            this.sentDate = new Date(sentDate);
            return this;
        }

        /**
         * 发送日期
         *
         * @param sentDate 发送日期
         */
        public EmailMimeMessageBuilder setSentDate(Date sentDate) {
            this.sentDate = sentDate;
            return this;
        }


        /**
         * 添加头部字段
         *
         * @param headers 头部字段
         */
        public EmailMimeMessageBuilder addHeader(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        /**
         * 添加头部字段
         *
         * @param key   头部字段key
         * @param value 头部字段value
         */
        public EmailMimeMessageBuilder addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        /**
         * 设置内容-文本
         *
         * @param text 内容
         */
        public EmailMimeMessageBuilder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * 设置内容-HTML
         *
         * @param html 内容
         */
        public EmailMimeMessageBuilder setHtml(String html) {
            text = html;
            textType = "text/html";
            return this;
        }

        /**
         * 设置内容-自定义内容类型
         * <p>
         * 常用类型：
         * text/plain 纯文本
         * text/html HTML文档
         * text/xml XML文档
         * image/gif GIF图片
         * image/jpeg JPG图片
         * image/png PNG图片
         *
         * @param text     内容
         * @param textType 内容类型
         */
        public EmailMimeMessageBuilder setHtml(String text, String textType) {
            this.text = text;
            this.textType = textType;
            return this;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         * @param index   添加位置
         */
        public <T extends BodyPart> EmailMimeMessageBuilder setContent(T content, int index) {
            try {
                this.content.addBodyPart(content, index);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
            return this;
        }

        protected void build(MimeMessage msg) throws MessagingException {
            msg.addRecipients(MimeMessage.RecipientType.TO, to.toArray(new Address[0]));
            msg.addRecipients(MimeMessage.RecipientType.CC, cc.toArray(new Address[0]));
            msg.addRecipients(MimeMessage.RecipientType.BCC, bcc.toArray(new Address[0]));
            msg.setFrom(from);
            msg.setSender(from);
            msg.setSubject(subject, charset);
            msg.setDescription(description, charset);
            msg.setContentID(ID.snowflakeString());
            msg.setSentDate(sentDate);
            msg.setReplyTo(getReplyTo());
            if (Boolean.TRUE.equals(reply)) {
                if (Strings.isNotBlank(subject) && (!subject.startsWith("Re:") || !subject.startsWith("回复:"))) {
                    msg.setSubject("Re:" + subject);
                }
                addHeader("In-Reply-To", String.valueOf(reply));
            }
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                msg.setHeader(entry.getKey(), entry.getValue());
            }
            if (Strings.isNotBlank(text)) {
                msg.setText(text, charset, textType);
            }
            if (content != null) {
                msg.setContent(getContent());
            }
        }

        private Multipart getContent() {
            if (!attachments.isEmpty()) {
                attachments.forEach((k, v) -> {
                    MimeBodyPart file = new MimeBodyPart();
                    try {
                        file.setFileName(k);
                        file.attachFile(v);
                        content.addBodyPart(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                addHeader("Attachment-Count", String.valueOf(attachments.size()));
            }
            return content;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public <T extends BodyPart> EmailMimeMessageBuilder setContent(T content) {
            try {
                this.content = new MimeMultipart(content);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
            return this;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public <T extends BodyPart> EmailMimeMessageBuilder addContent(T content) {
            try {
                this.content.addBodyPart(content);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
            return this;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public <T extends Multipart> EmailMimeMessageBuilder addContent(T content) {
            try {
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(content);
                this.content.addBodyPart(mimeBodyPart);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
            return this;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public EmailMimeMessageBuilder addContent(EmailMultipartBuilder.EmailMultipart content) {
            try {
                if (content.status()) {
                    this.content.addBodyPart(content.build());
                } else {
                    log.warning("内容添加失败：内容异常");
                }
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
            return this;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public EmailMimeMessageBuilder addContent(EmailMultipartBuilder.EmailMultipart... content) {
            try {
                for (EmailMultipartBuilder.EmailMultipart builder : content) {
                    if (builder.status()) {
                        this.content.addBodyPart(builder.build());
                    } else {
                        log.warning("内容添加失败：内容异常");
                    }
                }
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
            return this;
        }

        private Address[] getReplyTo() {
            return new Address[]{from};
        }
    }
}