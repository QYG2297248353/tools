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

import com.ms.core.base.basic.Strings;
import com.ms.core.config.SystemConfiguration;
import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.push.email.enums.EmailBoxTypeEnum;
import com.ms.push.email.properties.MsBoxEmailProperties;
import com.ms.push.email.template.EmailBoxTemplate;
import com.ms.push.email.template.EmailFolderTemplate;
import com.ms.push.email.template.PageBoxTemplate;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.imap.SortTerm;

import javax.mail.*;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SentDateTerm;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 收件箱工厂
 *
 * @author ms2297248353
 */
public class BoxEmailFactory {
    private static final Logger log = Logger.getLogger(BoxEmailFactory.class.getName());

    /**
     * 获取收件箱列表
     *
     * @param properties 邮箱配置
     * @param pageNo     页码
     * @param pageNum    每页数量
     * @return 收件箱列表
     */
    public static PageBoxTemplate<List<EmailBoxTemplate>> getReceiveList(MsBoxEmailProperties properties, int pageNo, int pageNum) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder folder = store.getFolder(EmailBoxTypeEnum.INBOX.getCode());

            // 以只读方式打开收件箱
            folder.open(Folder.READ_ONLY);

            // 获取总邮件数
            int total = folder.getMessageCount();

            // 获取总页数
            int totalPage = total % pageNum == 0 ? total / pageNum : total / pageNum + 1;

            // 获取指定页的邮件
            int start = (pageNo - 1) * pageNum + 1;
            int end = Math.min(pageNo * pageNum, total);

            // 排序
            boolean sorted = sortDefaultFolder(folder, false);
            List<Message> messageList;
            if (sorted) {
                // 获取邮件列表
                messageList = getBoxMessages(folder, start, end);
            } else {
                log.info("尝试使用检索排序");
                ReceivedDateTerm receivedDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, new Date());
                Message[] search = folder.search(receivedDateTerm);
                messageList = getBoxMessages(search, start, end);
            }
            // 转换为模板
            List<EmailBoxTemplate> collect = messageList.stream().map(EmailBoxTemplate::coverTo).collect(Collectors.toList());
            return new PageBoxTemplate(total, totalPage, pageNo, pageNum, collect);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }


    /**
     * 获取文件夹列表
     *
     * @param properties 邮箱配置
     * @param pageNo     页码
     * @param pageNum    每页数量
     * @return 收件箱列表
     */
    public static PageBoxTemplate<List<EmailBoxTemplate>> getBoxList(MsBoxEmailProperties properties, String folderName, boolean isSend, int pageNo, int pageNum) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(folderName);
            IMAPFolder folder = (IMAPFolder) inbox;
            // 以只读方式打开发件箱
            inbox.open(Folder.READ_ONLY);
            // 获取总邮件数
            int total = inbox.getMessageCount();
            // 获取总页数
            int totalPage = total % pageNum == 0 ? total / pageNum : total / pageNum + 1;

            // 获取指定页的邮件
            int start = (pageNo - 1) * pageNum + 1;
            int end = Math.min(pageNo * pageNum, total);

            // 排序
            boolean sorted = sortDefaultFolder(folder, isSend);
            List<Message> messageList;
            if (sorted) {
                // 获取邮件列表
                messageList = getBoxMessages(folder, start, end);
            } else {
                log.info("尝试使用检索排序");
                if (isSend) {
                    SentDateTerm sentDateTerm = new SentDateTerm(ComparisonTerm.LE, new Date());
                    Message[] search = folder.search(sentDateTerm);
                    messageList = getBoxMessages(search, start, end);
                } else {
                    ReceivedDateTerm receivedDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, new Date());
                    Message[] search = folder.search(receivedDateTerm);
                    messageList = getBoxMessages(search, start, end);
                }
            }

            List<EmailBoxTemplate> collect = messageList.stream().map(EmailBoxTemplate::coverTo).collect(Collectors.toList());
            return new PageBoxTemplate(total, totalPage, pageNo, pageNum, collect);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    private static boolean sortDefaultFolder(Folder folder, boolean isSend) {
        // 检查是否支持SORT扩展
        if (folder instanceof IMAPFolder) {
            IMAPFolder folderImap = (IMAPFolder) folder;
            try {
                if (isSend) {
                    SortTerm[] sortTerm = new SortTerm[]{SortTerm.DATE};
                    folderImap.getSortedMessages(sortTerm);
                } else {
                    SortTerm[] sortTerm = new SortTerm[]{SortTerm.ARRIVAL};
                    folderImap.getSortedMessages(sortTerm);
                }
                return true;
            } catch (MessagingException e) {
                log.warning(e.getMessage());
            }
        }
        log.warning("当前协议不支持排序");
        return false;
    }

    private static List<Message> getBoxMessages(Message[] search, int start, int end) {
        int length = search.length;
        if (start > length) {
            return Collections.emptyList();
        }
        if (end > length) {
            end = length;
        }
        return Arrays.stream(search).skip(start - 1).limit(end - start + 1).collect(Collectors.toList());
    }

    private static List<Message> getBoxMessages(Folder folder, int start, int end) {
        try {
            Message[] test = folder.getMessages(start, end);
            Message[] messages = folder.getMessages();
            return getBoxMessages(messages, start, end);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    private static void closeStore(Store store) {
        try {
            store.close();
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    private static Store connectStore(MsBoxEmailProperties properties) {
        Properties config = createProperties(properties);
        Session session = Session.getInstance(config);
        Store store;
        try {
            String host = properties.getHost();
            Integer port = properties.getPort();
            String username = properties.getUsername();
            String password = properties.getPassword();
            // 判断是否为 网易邮箱 163 126 yeah
            if ("imap".equals(properties.getTransport()) && (host.contains("163") || host.contains("126") || host.contains("yeah"))) {
                IMAPStore storeImap = (IMAPStore) session.getStore(properties.getTransport());
                storeImap.connect(host, port, username, password);
                storeImap.id(createIAM());
                return storeImap;
            }
            store = session.getStore(properties.getTransport());
            store.connect(host, port, username, password);
            return store;
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    protected static Map<String, String> createIAM() {
        HashMap IAM = new HashMap();
        IAM.put("name", SystemConfiguration.TOOLS_AUTHOR);
        IAM.put("version", SystemConfiguration.getSystemVersion());
        IAM.put("vendor", SystemConfiguration.TOOLS_TEAM);
        IAM.put("support-email", SystemConfiguration.TOOLS_SUPPORT_EMAIL);
        return IAM;
    }


    private static Properties createProperties(MsBoxEmailProperties properties) {
        Properties prop = new Properties();
        String protocol = Strings.isBlank(properties.getTransport()) ? "imap" : properties.getTransport();
        if (Boolean.TRUE.equals(properties.getSsl())) {
            prop.setProperty("mail.protocol.ssl.enable", "true");
        }
        prop.setProperty("mail." + protocol + ".host", properties.getHost());
        prop.setProperty("mail." + protocol + ".port", properties.getPortStr());
        prop.setProperty("mail." + protocol + ".user", properties.getUsername());
        prop.setProperty("mail." + protocol + ".auth", properties.getAuthStr());
        prop.setProperty("mail.store.protocol", protocol);
        if (properties.getTimeout() != null) {
            prop.setProperty("mail." + protocol + ".timeout", properties.getTimeout());
        }
        if (properties.getConnectionTimeout() != null) {
            prop.setProperty("mail." + protocol + ".connectiontimeout", properties.getConnectionTimeout());
        }

        if (Boolean.TRUE.equals(properties.getProxy())) {
            prop.setProperty("mail." + protocol + ".proxy.host", properties.getProxyHost());
            prop.setProperty("mail." + protocol + ".proxy.port", String.valueOf(properties.getProxyPort()));
            if (properties.getProxyUsername() != null) {
                prop.setProperty("mail." + protocol + ".proxy.user", properties.getProxyUsername());
                prop.setProperty("mail." + protocol + ".proxy.password", properties.getProxyPassword());
            }
        }

        prop.setProperty("mail." + protocol + ".socketFactory.fallback", "false");
        prop.setProperty("mail." + protocol + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail." + protocol + ".socketFactory.port", properties.getPortStr());
        prop.setProperty("mail.debug", properties.getDebugStr());
        return prop;
    }

    public static Optional<EmailBoxTemplate> getReceiveDetailEmail(MsBoxEmailProperties properties, String messageId, boolean privacy) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(EmailBoxTypeEnum.INBOX.getCode());
            // 以只读方式打开收件箱
            if (privacy) {
                inbox.open(Folder.READ_ONLY);
            } else {
                inbox.open(Folder.READ_WRITE);
            }

            Message message = getMessageByUID(inbox, messageId);
            if (message == null) {
                return Optional.empty();
            }
            // 获取邮件详情
            EmailBoxTemplate emailReceiveTemplate = EmailBoxTemplate.coverToEmail(message);
            return Optional.of(emailReceiveTemplate);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    private static Message getMessageByUID(Folder inbox, String messageId) {
        try {
            return inbox.getMessage(Integer.parseInt(messageId));
        } catch (MessagingException e) {
            return null;
        }
    }

    public static void deleteReceiveEmail(MsBoxEmailProperties properties, String[] messageIds) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(EmailBoxTypeEnum.INBOX.getCode());
            inbox.open(Folder.READ_WRITE);

            for (String messageId : messageIds) {
                Message message = getMessageByUID(inbox, messageId);
                if (message != null) {
                    deleteReceiveEmail(inbox, message);
                }
            }

        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    public static boolean deleteReceiveEmail(MsBoxEmailProperties properties, String messageId) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(EmailBoxTypeEnum.INBOX.getCode());
            inbox.open(Folder.READ_WRITE);
            Message message = getMessageByUID(inbox, messageId);
            return deleteReceiveEmail(inbox, message);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    private static boolean deleteReceiveEmail(Folder inbox, Message message) {
        try {
            message.setFlag(Flags.Flag.DELETED, true);
            inbox.expunge();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public static boolean readAll(MsBoxEmailProperties properties) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(EmailBoxTypeEnum.INBOX.getCode());
            inbox.open(Folder.READ_WRITE);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                message.setFlag(Flags.Flag.SEEN, true);
            }
            return true;
        } catch (MessagingException e) {
            log.warning("邮件读取失败：" + e.getMessage());
            return false;
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    public static List<EmailFolderTemplate> getFolderList(MsBoxEmailProperties properties) {
        // 连接服务器
        Store store = connectStore(properties);
        // 打开收件箱
        try {
            Folder defaultFolder = store.getDefaultFolder();
            Folder[] folders = defaultFolder.list();
            return EmailFolderTemplate.coverToList(folders);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    public static Optional<EmailBoxTemplate> getBoxDetail(MsBoxEmailProperties msReceiveEmailProperties, String folder, String messageId, boolean privacy) {
        // 连接服务器
        Store store = connectStore(msReceiveEmailProperties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(folder);
            // 以只读方式打开收件箱
            if (privacy) {
                inbox.open(Folder.READ_ONLY);
            } else {
                inbox.open(Folder.READ_WRITE);
            }

            Message message = getMessageByUID(inbox, messageId);
            if (message == null) {
                return Optional.empty();
            }
            // 获取邮件详情
            EmailBoxTemplate emailReceiveTemplate = EmailBoxTemplate.coverToEmail(message);
            return Optional.of(emailReceiveTemplate);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    public static boolean deleteBoxEmail(MsBoxEmailProperties msReceiveEmailProperties, String folder, String messageId) {
        // 连接服务器
        Store store = connectStore(msReceiveEmailProperties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_WRITE);
            Message message = getMessageByUID(inbox, messageId);
            return deleteReceiveEmail(inbox, message);
        } catch (MessagingException e) {
            throw new MsToolsRuntimeException(e);
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    public static boolean deleteBoxEmail(MsBoxEmailProperties msReceiveEmailProperties, String folder, String[] messageIds) {
        // 连接服务器
        Store store = connectStore(msReceiveEmailProperties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_WRITE);

            for (String messageId : messageIds) {
                Message message = getMessageByUID(inbox, messageId);
                if (message != null) {
                    deleteReceiveEmail(inbox, message);
                }
            }

            return true;
        } catch (MessagingException e) {
            return false;
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }

    public static boolean readAll(MsBoxEmailProperties msReceiveEmailProperties, String folder) {
        // 连接服务器
        Store store = connectStore(msReceiveEmailProperties);
        // 打开收件箱
        try {
            // 获取收件箱
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_WRITE);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                message.setFlag(Flags.Flag.SEEN, true);
            }
            return true;
        } catch (MessagingException e) {
            log.warning("邮件读取失败：" + e.getMessage());
            return false;
        } finally {
            // 关闭服务器
            closeStore(store);
        }
    }
}