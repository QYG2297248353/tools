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

package com.ms.push.email.builder;

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

    private static class EmailMimeMessageBuilder {


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

        public EmailMimeMessageBuilder() {
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
        public void setFrom(Address from) {
            this.from = from;
        }

        /**
         * 发件人-地址
         *
         * @param from 发件人地址
         */
        public void setFrom(String from) {
            try {
                this.from = new InternetAddress(from);
            } catch (AddressException e) {
                log.warning("邮件地址错误{" + from + "}");
            }
        }

        /**
         * 收件人-地址
         *
         * @param to 收件人地址
         */
        public void addTo(Address... to) {
            this.to = Lists.newArrayList(to);
        }

        /**
         * 抄送人-地址
         *
         * @param bcc 抄送人地址
         */
        public void addBcc(Address... bcc) {
            this.bcc = Lists.newArrayList(bcc);
        }

        /**
         * 密送人-地址
         *
         * @param cc 密送人地址
         */
        public void addCc(Address... cc) {
            this.cc = Lists.newArrayList(cc);
        }

        /**
         * 收件人-地址
         *
         * @param to 收件人地址
         */
        public void addTo(String... to) {
            for (String email : to) {
                try {
                    InternetAddress internetAddress = new InternetAddress(email);
                    this.to.add(internetAddress);
                } catch (AddressException e) {
                    log.warning("邮件地址错误{" + email + "}");
                }
            }
        }

        /**
         * 抄送人-地址
         *
         * @param bcc 抄送人地址
         */
        public void addBcc(String... bcc) {
            for (String email : bcc) {
                try {
                    InternetAddress internetAddress = new InternetAddress(email);
                    this.bcc.add(internetAddress);
                } catch (AddressException e) {
                    log.warning("邮件地址错误{" + email + "}");
                }
            }
        }

        /**
         * 密送人-地址
         *
         * @param cc 密送人地址
         */
        public void addCc(String... cc) {
            for (String email : cc) {
                try {
                    InternetAddress internetAddress = new InternetAddress(email);
                    this.cc.add(internetAddress);
                } catch (AddressException e) {
                    log.warning("邮件地址错误{" + email + "}");
                }
            }
        }

        /**
         * 设置为回复邮件
         */
        public void setReply() {
            reply = true;
        }

        /**
         * 是否为回复邮件
         *
         * @param reply 是否为回复邮件
         */
        public void setReply(Boolean reply) {
            this.reply = reply != null && reply;
        }

        /**
         * 主题
         *
         * @param subject 主题
         */
        public void setSubject(String subject) {
            this.subject = subject;
        }

        /**
         * 主题
         *
         * @param subject 主题
         * @param charset 编码
         */
        public void setSubject(String subject, String charset) {
            this.subject = subject;
            this.charset = charset;
        }

        /**
         * 描述
         *
         * @param description 描述
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * 编码
         *
         * @param charset 编码
         */
        public void setCharset(String charset) {
            this.charset = charset;
        }

        /**
         * 添加附件
         *
         * @param file 附件文件
         */
        public void addAttachment(File file) {
            String name = file.getName();
            attachments.put(name, file);
        }

        /**
         * 添加附件
         *
         * @param name 附件名称
         * @param file 附件文件
         */
        public void addAttachment(String name, File file) {
            attachments.put(name, file);
        }

        /**
         * 添加附件
         *
         * @param name       附件名称
         * @param fileStream 附件文件流
         */
        public void addAttachment(String name, InputStream fileStream) {
            try {
                File file = FilesUtils.writeToTempFile(fileStream);
                attachments.put(name, file);
            } catch (MsToolsException e) {
                log.warning("附件添加失败：" + e.getMessage());
            }
        }

        /**
         * 添加附件
         *
         * @param name       附件名称
         * @param fileStream 附件文件流
         * @param suffix     附件后缀 例如：.jpg .json
         */
        public void addAttachment(String name, InputStream fileStream, String suffix) {
            try {
                File file = FilesUtils.writeToTempFile(fileStream, suffix);
                attachments.put(name, file);
            } catch (MsToolsException e) {
                log.warning("附件添加失败：" + e.getMessage());
            }
        }

        /**
         * 添加附件
         *
         * @param attachments 附件
         */
        public void addAttachments(Map<String, File> attachments) {
            this.attachments.putAll(attachments);
        }

        /**
         * 发送日期
         *
         * @param sentDate 发送日期
         */
        public void setSentDate(long sentDate) {
            this.sentDate = new Date(sentDate);
        }

        /**
         * 发送日期
         *
         * @param sentDate 发送日期
         */
        public void setSentDate(Date sentDate) {
            this.sentDate = sentDate;
        }


        /**
         * 添加头部字段
         *
         * @param headers 头部字段
         */
        public void addHeader(Map<String, String> headers) {
            this.headers.putAll(headers);
        }

        /**
         * 添加头部字段
         *
         * @param key   头部字段key
         * @param value 头部字段value
         */
        public void addHeader(String key, String value) {
            headers.put(key, value);
        }

        /**
         * 设置内容-文本
         *
         * @param text 内容
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * 设置内容-HTML
         *
         * @param html 内容
         */
        public void setHtml(String html) {
            text = html;
            textType = "text/html";
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
        public void setHtml(String text, String textType) {
            this.text = text;
            this.textType = textType;
        }

        /**
         * 设置内容
         *
         * @param content 内容
         * @param index   添加位置
         */
        public <T extends BodyPart> void setContent(T content, int index) {
            try {
                this.content.addBodyPart(content, index);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
        }

        private void build(MimeMessage msg) throws MessagingException {
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
        public <T extends BodyPart> void setContent(T content) {
            try {
                this.content = new MimeMultipart(content);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public <T extends BodyPart> void addContent(T content) {
            try {
                this.content.addBodyPart(content);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public <T extends Multipart> void addContent(T content) {
            try {
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(content);
                this.content.addBodyPart(mimeBodyPart);
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public void addContent(EmailMultipartBuilder content) {
            try {
                if (content.status()) {
                    this.content.addBodyPart(content.build());
                } else {
                    log.warning("内容添加失败：内容异常");
                }
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
        }

        /**
         * 设置内容
         *
         * @param content 内容
         */
        public void addContent(EmailMultipartBuilder... content) {
            try {
                for (EmailMultipartBuilder builder : content) {
                    if (builder.status()) {
                        this.content.addBodyPart(builder.build());
                    } else {
                        log.warning("内容添加失败：内容异常");
                    }
                }
            } catch (MessagingException e) {
                log.warning("内容添加失败：" + e.getMessage());
            }
        }

        private Address[] getReplyTo() {
            return new Address[]{from};
        }
    }
}