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

package com.ms.tools.push.dingtalk.template;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.push.dingtalk.to.LinkImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Text 推送
 * 如需At成员请重写at方法
 *
 * @author ms2297248353
 */
@ToString
@Getter
@Setter
public class DingLinkDO extends LinkImpl {
    /**
     * 推送内容
     */
    private Link link;

    /**
     * 推送类型
     */
    @JSONField(name = "msgtype")
    private String msgType;

    public DingLinkDO() {
        link = new Link();
        msgType = super.getMsgType();
    }

    public DingLinkDO(LinkImpl link) {
        msgType = link.getMsgType();
        setToken(link.getToken());
        setSignature(link.getSignature());
        setUri(link.getUri());

        this.link = new Link(link);
    }

    public String getMsgType() {
        return msgType;
    }

    private void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    @JSONField(serialize = false)
    public String getText() {
        return link.getText();
    }

    public void setText(String text) {
        link.setText(text);
    }

    @Override
    @JSONField(serialize = false)
    public String getTitle() {
        return link.getTitle();
    }

    public void setTitle(String title) {
        link.setTitle(title);
    }

    @Override
    @JSONField(serialize = false)
    public String getPicUrl() {
        return link.getPicUrl();
    }

    public void setPicUrl(String picUrl) {
        link.setPicUrl(picUrl);
    }

    @Override
    @JSONField(serialize = false)
    public String getMessageUrl() {
        return link.getMessageUrl();
    }

    public void setMessageUrl(String messageUrl) {
        link.setMessageUrl(messageUrl);
    }

    private class Link {
        private String text;
        private String title;
        private String picUrl;
        private String messageUrl;

        public Link() {
        }

        public Link(LinkImpl webhook) {
            text = webhook.getText();
            title = webhook.getTitle();
            picUrl = webhook.getPicUrl();
            messageUrl = webhook.getMessageUrl();
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getMessageUrl() {
            return messageUrl;
        }

        public void setMessageUrl(String messageUrl) {
            this.messageUrl = messageUrl;
        }
    }
}
