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

package com.ms.push.webhook.dingtalk.enums;

/**
 * @author ms2297248353
 */

public enum MsgTypeEnum {
    /**
     * text类型
     * text
     */
    TEXT("text"),
    /**
     * link类型
     * link
     */
    LINK("link"),
    /**
     * markdown类型
     * markdown
     */
    MARKDOWN("markdown"),
    /**
     * 整体跳转ActionCard类型
     * action_card
     */
    ACTION_CARD("actionCard"),
    /**
     * 独立跳转ActionCard类型(按钮操作)
     * action_card_btn
     */
    ACTION_CARD_BTN("actionCard"),
    /**
     * FeedCard类型
     * feed_card
     */
    FEED_CARD("feedCard");

    private String msgType;

    MsgTypeEnum(String type) {
        msgType = type;
    }

    public String getMsgType() {
        return msgType;
    }
}
