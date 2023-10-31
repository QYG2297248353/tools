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

package com.ms.tools.push.email.template;

import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.base.size.FileSizeUtils;
import lombok.ToString;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 发件箱 列表模板
 *
 * @author ms2297248353
 */
@ToString
public class EmailBoxTemplate {

    /**
     * 发件人
     */
    private String from;

    /**
     * 发件人-列表
     */
    private List<String> fromList;

    /**
     * 收件人
     */
    private String to;

    /**
     * 收件人-列表
     */
    private List<String> toList;

    /**
     * 抄送人-列表
     */
    private List<String> ccList;

    /**
     * 密送人-列表
     */
    private List<String> bccList;

    /**
     * 主题
     */
    private String subject;

    /**
     * 发送时间
     */
    private Date sendDate;

    /**
     * 收信时间
     */
    private Date receiveDate;

    /**
     * 是否已读
     */
    private boolean read;

    /**
     * 是否有附件
     */
    private boolean hasAttachment;

    /**
     * 附件数量
     */
    private int attachmentCount;

    /**
     * 邮件内容
     * <p>
     * 截取前100个字符
     */
    private String content;

    /**
     * 邮件大小
     */
    private String size;

    /**
     * 是否为新消息
     */
    private boolean recent;

    /**
     * 是否为回复状态
     */
    private boolean reply;

    /**
     * 是否为转发邮件
     */
    private boolean forwardEmail;

    /**
     * 是否为回复邮件
     */
    private boolean replyEmail;

    /**
     * 是否为草稿状态
     */
    private boolean draft;

    /**
     * 是否为删除状态
     */
    private boolean deleted;

    /**
     * 邮件内容类型
     * 纯文本 text/plain
     * html text/html
     * 附件 multipart/*
     * 未知 application/octet-stream
     */
    private String contentType;

    /**
     * 附件文件列表
     * <p>
     * k 文件名
     * v 文件
     */
    private List<String> attachments;

    /**
     * 邮件正文
     */
    private String text;


    /**
     * 邮件id
     */
    private String messageId;


    public static EmailBoxTemplate coverTo(Message message) {
        EmailBoxTemplate template = new EmailBoxTemplate();
        try {
            Address[] fromAddress = message.getFrom();
            InternetAddress from = (InternetAddress) fromAddress[0];
            template.setFrom(from.getAddress());
            List<String> fromList = new ArrayList<>();
            for (Address address : fromAddress) {
                InternetAddress imapAddress = (InternetAddress) address;
                fromList.add(imapAddress.getAddress());
            }
            template.setFromList(fromList);
        } catch (MessagingException e) {
            template.setFrom("未知");
        }
        try {
            Address[] to = message.getRecipients(Message.RecipientType.TO);
            if (to != null && to.length > 0) {
                InternetAddress toAddress = (InternetAddress) to[0];
                template.setTo(toAddress.getAddress());
                List<String> toList = new ArrayList<>();
                for (Address address : to) {
                    InternetAddress imapAddress = (InternetAddress) address;
                    toList.add(imapAddress.getAddress());
                }
                template.setToList(toList);
            }
        } catch (MessagingException ignored) {
        }
        try {
            Address[] cc = message.getRecipients(Message.RecipientType.CC);
            if (cc != null && cc.length > 0) {
                List<String> ccList = new ArrayList<>();
                for (Address address : cc) {
                    InternetAddress imapAddress = (InternetAddress) address;
                    ccList.add(imapAddress.getAddress());
                }
                template.setCcList(ccList);
            }
        } catch (MessagingException ignored) {
        }
        try {
            Address[] bcc = message.getRecipients(Message.RecipientType.BCC);
            if (bcc != null && bcc.length > 0) {
                List<String> bccList = new ArrayList<>();
                for (Address address : bcc) {
                    InternetAddress imapAddress = (InternetAddress) address;
                    bccList.add(imapAddress.getAddress());
                }
                template.setBccList(bccList);
            }
        } catch (MessagingException ignored) {
        }
        try {
            template.setSubject(message.getSubject());
        } catch (MessagingException e) {
            template.setSubject("无主题");
        }
        try {
            template.setSendDate(message.getSentDate());
        } catch (MessagingException e) {
            template.setSendDate(new Date());
        }
        try {
            template.setReceiveDate(message.getReceivedDate());
        } catch (MessagingException e) {
            template.setReceiveDate(new Date());
        }
        try {
            template.setRead(message.isSet(Flags.Flag.SEEN));
        } catch (MessagingException e) {
            template.setRead(false);
        }
        try {
            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent();
                // 附件数量
                int attachmentCount = 0;
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                        attachmentCount++;
                    }
                }
                template.setHasAttachment(attachmentCount > 0);
                template.setAttachmentCount(attachmentCount);
            } else {
                template.setHasAttachment(false);
                template.setAttachmentCount(0);
            }
        } catch (MessagingException | IOException e) {
            template.setHasAttachment(false);
        }
        try {
            template.setContentType(message.getContentType());
        } catch (MessagingException e) {
            template.setContentType("未知");
        }
        try {
            template.setRecent(message.isSet(Flags.Flag.RECENT));
        } catch (MessagingException e) {
            template.setRecent(false);
        }
        try {
            template.setReply(message.isSet(Flags.Flag.ANSWERED));
        } catch (MessagingException e) {
            template.setReply(false);
        }
        String templateSubject = template.getSubject();
        template.setForwardEmail(templateSubject.startsWith("Fw:") || templateSubject.contains("Fw:") || templateSubject.startsWith("转发:") || templateSubject.contains("转发:"));
        template.setReplyEmail(templateSubject.startsWith("Re:") || templateSubject.contains("Re:") || templateSubject.startsWith("回复:") || templateSubject.contains("回复:"));
        try {
            template.setDraft(message.isSet(Flags.Flag.DRAFT));
        } catch (MessagingException e) {
            template.setDraft(false);
        }
        try {
            template.setDeleted(message.isSet(Flags.Flag.DELETED));
        } catch (MessagingException e) {
            template.setDeleted(false);
        }

        template.setMessageId(String.valueOf(message.getMessageNumber()));
        try {
            int messageSize = message.getSize();
            template.setSize(FileSizeUtils.convertBytes(messageSize, true));
        } catch (MessagingException e) {
            template.setSize("0KB");
        }
        return template;
    }

    public static EmailBoxTemplate coverToEmail(Message message) {
        EmailBoxTemplate template = coverTo(message);
        try {
            Object content = message.getContent();
            String emailContent = "";
            if (content instanceof Multipart) {
                // 邮件是multipart类型，可能包含多个部分
                Multipart multipart = (Multipart) content;
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    String contentType = bodyPart.getContentType();
                    if (contentType.startsWith("text/plain") || contentType.startsWith("text/html")) {
                        // 文本正文
                        emailContent = (String) bodyPart.getContent();
                    }
                }
            } else if (content instanceof String) {
                // 邮件是纯文本类型
                emailContent = (String) content;
            }
            template.setText(emailContent);

            // 获取附件列表
            List<String> attachments = new ArrayList<>();
            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent();
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                        String fileName = bodyPart.getFileName();
                        attachments.add(fileName);
                    }
                }
            }
            template.setAttachments(attachments);
        } catch (IOException | MessagingException ignored) {
        }
        return template;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public List<String> getBccList() {
        return bccList;
    }

    public void setBccList(List<String> bccList) {
        this.bccList = bccList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (Strings.isBlank(subject)) {
            subject = "无主题";
        }
        this.subject = subject;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isRecent() {
        return recent;
    }

    public void setRecent(boolean recent) {
        this.recent = recent;
    }

    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    public boolean isForwardEmail() {
        return forwardEmail;
    }

    public void setForwardEmail(boolean forwardEmail) {
        this.forwardEmail = forwardEmail;
    }

    public boolean isReplyEmail() {
        return replyEmail;
    }

    public void setReplyEmail(boolean replyEmail) {
        this.replyEmail = replyEmail;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getFromList() {
        return fromList;
    }

    public void setFromList(List<String> fromList) {
        this.fromList = fromList;
    }

    public int getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(int attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
