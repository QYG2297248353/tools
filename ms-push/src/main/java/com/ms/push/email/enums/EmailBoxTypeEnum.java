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

package com.ms.push.email.enums;

/**
 * 邮箱类型枚举
 *
 * @author ms2297248353
 */

public enum EmailBoxTypeEnum {
    INBOX("INBOX", "收件箱", "已收到"),
    SENT("Sent", "已发送", "已发出"),
    DRAFTS("Drafts", "草稿", "待发送"),
    TRASH("Trash", "垃圾邮件", "主动删除的邮件"),
    ARCHIVE("Archive", "归档", "归纳"),
    SPAM("Spam", "垃圾邮件", "邮件系统拦截的邮件");

    private String code;

    private String name;

    private String desc;

    EmailBoxTypeEnum(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static EmailBoxTypeEnum getByCode(String code) {
        for (EmailBoxTypeEnum emailBoxTypeEnum : EmailBoxTypeEnum.values()) {
            if (emailBoxTypeEnum.getCode().equalsIgnoreCase(code)) {
                return emailBoxTypeEnum;
            }
        }
        return INBOX;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}