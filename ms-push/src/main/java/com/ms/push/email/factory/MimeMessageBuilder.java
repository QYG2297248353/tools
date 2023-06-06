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

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author ms2297248353
 */
public class MimeMessageBuilder extends MimeMessageObj {
    private MimeMessageBuilder(Session session) throws MessagingException {
        super(new MimeMessage(session));
        message.setFrom();
    }

    /**
     * 创建一个MimeMessageBuilder
     *
     * @param session 邮件会话
     * @return MimeMessageBuilder
     */
    public static MimeMessageBuilder create(Session session) throws MessagingException {
        return new MimeMessageBuilder(session);
    }

    /**
     * 设置发件人
     *
     * @param from 发件人
     * @return 对象对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder from(String from) throws MessagingException {
        message.setFrom(from);
        return this;
    }

    /**
     * 设置发件人
     *
     * @param from 发件人
     * @return 对象对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder from(Address from) throws MessagingException {
        message.setFrom(from);
        return this;
    }

    /**
     * 收件人
     * <p>
     * 收件人可以看到“收件人”和“抄送”中的地址，但不能看到“密送”字的地址。
     *
     * @param tos 收件人
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder to(String... tos) throws MessagingException {
        for (String to : tos) {
            message.setRecipients(Message.RecipientType.TO, to);
        }
        return this;
    }

    /**
     * 收件人
     * <p>
     * 收件人可以看到“收件人”和“抄送”中的地址，但不能看到“密送”中的地址。
     *
     * @param to 收件人
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder to(Address[] to) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, to);
        return this;
    }

    /**
     * 抄送
     * <p>
     * 抄送：抄送是指将邮件的副本发送给其他人，知道其他“收件人”和“抄送”中的地址，但不能看到“密送”中的地址。
     *
     * @param ccs 抄送
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder cc(String... ccs) throws MessagingException {
        for (String cc : ccs) {
            message.setRecipients(Message.RecipientType.CC, cc);
        }
        return this;
    }

    /**
     * 抄送
     * <p>
     * 抄送：抄送是指将邮件的副本发送给其他人，知道其他“收件人”和“抄送”中的地址，但不能看到“密送”中的地址。
     *
     * @param cc 抄送
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder cc(Address[] cc) throws MessagingException {
        message.setRecipients(Message.RecipientType.CC, cc);
        return this;
    }

    /**
     * 密送
     * <p>
     * 密送：密送者知道“收件人”和“抄送”的地址，但是不知道其他“密送”中的地址。
     *
     * @param bccs 密送
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder bcc(String... bccs) throws MessagingException {
        for (String bcc : bccs) {
            message.setRecipients(Message.RecipientType.BCC, bcc);
        }
        return this;
    }

    /**
     * 密送
     * <p>
     * 密送：密送者知道“收件人”和“抄送”的地址，但是不知道其他“密送”中的地址。
     *
     * @param bcc 密送
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder bcc(Address[] bcc) throws MessagingException {
        message.setRecipients(Message.RecipientType.BCC, bcc);
        return this;
    }

    /**
     * 主题(标题)
     *
     * @param subject 主题
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder subject(String subject) throws MessagingException {
        message.setSubject(subject);
        return this;
    }

    /**
     * Text 纯文本
     * 邮件内容
     *
     * @return 对象
     */
    public TextMail sendText() {
        return new TextMail(this);
    }

    /**
     * Html 纯邮件
     * 邮件内容
     *
     * @return 对象
     */
    public HtmlMail sendHtml() {
        return new HtmlMail(this);
    }

    /**
     * File 纯附件邮件
     * 邮件内容
     *
     * @return 对象
     */
    public AttachMail sendAttach() {
        return new AttachMail(this);
    }

    /**
     * 混合邮件
     * 邮件内容
     *
     * @return 对象
     */
    public MixedMail mixed() {
        return new MixedMail(this);
    }


    /**
     * 描述
     *
     * @param description 描述
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder description(String description) throws MessagingException {
        message.setDescription(description);
        return this;
    }

    /**
     * 描述
     *
     * @param description 描述
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder description(String description, Charset charset) throws MessagingException {
        message.setDescription(description, charset.name());
        return this;
    }

    /**
     * 发送时间
     *
     * @param sentDate 发送时间
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder sentDate(Date sentDate) throws MessagingException {
        message.setSentDate(sentDate);
        return this;
    }

    /**
     * 发送时间
     *
     * @param sentDate 发送时间
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder sentDate(Calendar sentDate) throws MessagingException {
        message.setSentDate(sentDate.getTime());
        return this;
    }

    /**
     * 发送时间
     *
     * @param sentDate 发送时间
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder sentDate(long sentDate) throws MessagingException {
        message.setSentDate(new Date(sentDate));
        return this;
    }

    /**
     * 添加contentID
     *
     * @param contentID contentID null 时移除字段
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder contentID(String contentID) throws MessagingException {
        message.setContentID(contentID);
        return this;
    }

    /**
     * 添加contentMD5
     *
     * @param contentMD5 contentMD5
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder contentMD5(String contentMD5) throws MessagingException {
        message.setContentMD5(contentMD5);
        return this;
    }

    /**
     * 替换请求头
     *
     * @param name  k值
     * @param value v值
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder header(String name, String value) throws MessagingException {
        message.setHeader(name, value);
        return this;
    }

    /**
     * 添加请求头
     *
     * @param name  k值
     * @param value v值
     * @return 对象
     * @throws MessagingException 异常
     */
    public MimeMessageBuilder addHeader(String name, String value) throws MessagingException {
        message.addHeader(name, value);
        return this;
    }


    public class TextMail extends MimeMessageObj {
        public TextMail(MimeMessageBuilder builder) {
            super(builder.message);
        }

        public TextMail text(String text) throws MessagingException {
            message.setText(text);
            return this;
        }

        public TextMail text(String text, Charset charset) throws MessagingException {
            message.setText(text, charset.name());
            return this;
        }

        public TextMail text(String text, String charset) throws MessagingException {
            message.setText(text, charset);
            return this;
        }

        public TextMail text(String text, String charset, String subtype) throws MessagingException {
            message.setText(text, charset, subtype);
            return this;
        }

        public MimeMessage build() throws MessagingException {
            message.saveChanges();
            return message;
        }
    }

    public class HtmlMail extends MimeMessageObj {
        public HtmlMail(MimeMessageBuilder builder) {
            super(builder.message);
        }

        public HtmlMail html(String html) throws MessagingException {
            message.setContent(html, "text/html;charset=UTF-8");
            return this;
        }

        public HtmlMail html(String html, Charset charset) throws MessagingException {
            message.setContent(html, "text/html;charset=" + charset.name());
            return this;
        }

        public HtmlMail html(String html, String charset) throws MessagingException {
            message.setContent(html, "text/html;charset=" + charset);
            return this;
        }

        public MimeMessage build() throws MessagingException {
            message.saveChanges();
            return message;
        }
    }

    public class AttachMail extends MimeMessageObj {
        public AttachMail(MimeMessageBuilder builder) {
            super(builder.message);
        }

        public AttachMail attach(File file) throws MessagingException, IOException {
            MimeMultipart mm = new MimeMultipart();
            if (file.isFile()) {
                MimeBodyPart mbp = new MimeBodyPart();
                mbp.attachFile(file);
                mm.addBodyPart(mbp);
            } else {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        MimeBodyPart mbp = new MimeBodyPart();
                        mbp.attachFile(f);
                        mm.addBodyPart(mbp);
                    }
                }
            }
            message.setContent(mm);
            return this;
        }

        public AttachMail attach(File... files) throws MessagingException, IOException {
            MimeMultipart mm = new MimeMultipart();
            for (File file : files) {
                if (file.isFile()) {
                    MimeBodyPart mbp = new MimeBodyPart();
                    mbp.attachFile(file);
                    mm.addBodyPart(mbp);
                } else {
                    File[] fs = file.listFiles();
                    if (fs != null) {
                        for (File f : fs) {
                            MimeBodyPart mbp = new MimeBodyPart();
                            mbp.attachFile(f);
                            mm.addBodyPart(mbp);
                        }
                    }
                }
            }
            message.setContent(mm);
            return this;
        }

        public AttachMail attach(List<File> files) throws MessagingException, IOException {
            MimeMultipart mm = new MimeMultipart();
            for (File file : files) {
                if (file.isFile()) {
                    MimeBodyPart mbp = new MimeBodyPart();
                    mbp.attachFile(file);
                    mm.addBodyPart(mbp);
                } else {
                    File[] fs = file.listFiles();
                    if (fs != null) {
                        for (File f : fs) {
                            MimeBodyPart mbp = new MimeBodyPart();
                            mbp.attachFile(f);
                            mm.addBodyPart(mbp);
                        }
                    }
                }
            }
            message.setContent(mm);
            return this;
        }

        public MimeMessage build() throws MessagingException {
            message.saveChanges();
            return message;
        }
    }

    public class MixedMail extends MimeMessageObj {
        /**
         * 存储正文模块列表
         * 对象
         */
        private List<MimeBodyPart> bodyParts = new ArrayList<>();

        /**
         * 存储附件模块列表
         * 对象
         */
        private List<MimeBodyPart> attachParts = new ArrayList<>();

        /**
         * 构造方法
         *
         * @param builder 对象
         */
        public MixedMail(MimeMessageBuilder builder) {
            super(builder.message);
        }

        /**
         * 添加纯文本正文
         *
         * @param text 正文
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail text(String text) throws MessagingException {
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(text);
            bodyParts.add(mbp);
            return this;
        }

        /**
         * 添加纯文本正文
         *
         * @param text    正文
         * @param charset 编码
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail text(String text, Charset charset) throws MessagingException {
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(text, charset.name());
            bodyParts.add(mbp);
            return this;
        }

        /**
         * 添加Html 正文
         *
         * @param html 正文
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail html(String html) throws MessagingException {
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(html, "text/html;charset=UTF-8");
            bodyParts.add(mbp);
            return this;
        }

        /**
         * 添加Html 正文
         *
         * @param html    正文
         * @param charset 编码
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail html(String html, Charset charset) throws MessagingException {
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(html, "text/html;charset=" + charset.name());
            bodyParts.add(mbp);
            return this;
        }

        /**
         * 添加Html图片 正文
         *
         * @param html   正文
         * @param images 图片
         * @return 对象
         */
        public MixedMail html(String html, Map<String, File> images) throws MessagingException, IOException {
            MimeBodyPart mbp = new MimeBodyPart();
            MimeMultipart mm = new MimeMultipart("related");
            MimeBodyPart mbp2 = new MimeBodyPart();
            mbp2.setContent(html, "text/html;charset=UTF-8");
            mm.addBodyPart(mbp2);
            for (Map.Entry<String, File> entry : images.entrySet()) {
                MimeBodyPart mbp3 = new MimeBodyPart();
                mbp3.attachFile(entry.getValue());
                mbp3.setContentID(entry.getKey());
                mm.addBodyPart(mbp3);
            }
            mbp.setContent(mm);
            bodyParts.add(mbp);
            return this;
        }

        /**
         * 添加Html图片 正文
         *
         * @param html    正文
         * @param images  图片
         * @param charset 编码
         * @return 对象
         */
        public MixedMail html(String html, Map<String, File> images, Charset charset) throws MessagingException, IOException {
            MimeBodyPart mbp = new MimeBodyPart();
            MimeMultipart mm = new MimeMultipart("related");
            MimeBodyPart mbp2 = new MimeBodyPart();
            mbp2.setContent(html, "text/html;charset=" + charset.name());
            mm.addBodyPart(mbp2);
            for (Map.Entry<String, File> entry : images.entrySet()) {
                MimeBodyPart mbp3 = new MimeBodyPart();
                mbp3.attachFile(entry.getValue());
                mbp3.setContentID(entry.getKey());
                mm.addBodyPart(mbp3);
            }
            mbp.setContent(mm);
            bodyParts.add(mbp);
            return this;
        }

        /**
         * 添加附件
         *
         * @param file 附件
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail attach(File file) throws MessagingException {
            MimeBodyPart mbp = new MimeBodyPart();
            try {
                mbp.attachFile(file);
            } catch (IOException e) {
                throw new MessagingException(e.getMessage(), e);
            }
            attachParts.add(mbp);
            return this;
        }

        /**
         * 添加附件
         *
         * @param file 附件
         * @param name 名称
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail attach(File file, String name) throws MessagingException {
            MimeBodyPart mbp = new MimeBodyPart();
            try {
                mbp.attachFile(file);
            } catch (IOException e) {
                throw new MessagingException(e.getMessage(), e);
            }
            mbp.setFileName(name);
            attachParts.add(mbp);
            return this;
        }

        /**
         * 添加附件
         *
         * @param files 附件
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail attach(File... files) throws MessagingException {
            for (File file : files) {
                attach(file);
            }
            return this;
        }

        /**
         * 添加附件
         *
         * @param files 附件
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail attach(List<File> files) throws MessagingException {
            for (File file : files) {
                attach(file);
            }
            return this;
        }

        /**
         * 添加附件
         *
         * @param files 附件
         * @return 对象
         * @throws MessagingException 异常
         */
        public MixedMail attach(Map<String, File> files) throws MessagingException {
            for (Map.Entry<String, File> entry : files.entrySet()) {
                attach(entry.getValue(), entry.getKey());
            }
            return this;
        }

        /**
         * 构建
         *
         * @return 对象
         */
        public MimeMessage build() throws MessagingException {
            MimeMultipart mm = new MimeMultipart("mixed");
            for (MimeBodyPart bodyPart : bodyParts) {
                mm.addBodyPart(bodyPart);
            }
            for (MimeBodyPart attachPart : attachParts) {
                mm.addBodyPart(attachPart);
            }
            message.setContent(mm);
            message.saveChanges();
            return message;
        }
    }
}