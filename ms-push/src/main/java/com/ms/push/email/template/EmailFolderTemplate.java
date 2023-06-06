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

package com.ms.push.email.template;

import com.ms.core.base.basic.Strings;
import lombok.ToString;

import javax.mail.Folder;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹列表
 *
 * @author ms2297248353
 */
@ToString
public class EmailFolderTemplate {
    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 父文件夹
     */
    private EmailFolderTemplate parent;

    /**
     * 子文件夹
     */
    private List<EmailFolderTemplate> children;

    /**
     * 邮件数量
     */
    private int messageCount;

    /**
     * 文件夹类型
     */
    private int type;


    public static List<EmailFolderTemplate> coverToList(Folder[] folders) {
        List<EmailFolderTemplate> folderList = new ArrayList<>();
        for (Folder folder : folders) {
            try {
                if (folder.exists()) {
                    EmailFolderTemplate template = EmailFolderTemplate.coverTo(folder);
                    if (template != null) {
                        folderList.add(template);
                    }
                }
            } catch (MessagingException ignored) {
            }
        }
        return folderList;
    }

    private static EmailFolderTemplate coverTo(Folder folder) {
        try {
            if (folder.exists()) {
                EmailFolderTemplate emailFolderTemplate = new EmailFolderTemplate();
                emailFolderTemplate.setName(folder.getName());
                emailFolderTemplate.setPath(folder.getFullName());
                try {
                    emailFolderTemplate.setType(folder.getType());
                } catch (MessagingException e) {
                    emailFolderTemplate.setType(-1);
                }
                try {
                    emailFolderTemplate.setMessageCount(folder.getMessageCount());
                } catch (MessagingException e) {
                    emailFolderTemplate.setMessageCount(0);
                }
                Folder folderParent = folder.getParent();
                if (folderParent != null && Strings.isNotBlank(folderParent.getFullName())) {
                    emailFolderTemplate.setParent(EmailFolderTemplate.coverTo(folderParent));
                }
                Folder[] children = folder.list();
                if (children != null && children.length > 0) {
                    emailFolderTemplate.setChildren(EmailFolderTemplate.coverToList(children));
                }
                return emailFolderTemplate;
            }
        } catch (MessagingException ignored) {
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public EmailFolderTemplate getParent() {
        return parent;
    }

    public void setParent(EmailFolderTemplate parent) {
        this.parent = parent;
    }

    public List<EmailFolderTemplate> getChildren() {
        return children;
    }

    public void setChildren(List<EmailFolderTemplate> children) {
        this.children = children;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
}