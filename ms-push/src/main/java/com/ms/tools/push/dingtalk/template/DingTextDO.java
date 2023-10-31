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
import com.ms.tools.push.dingtalk.to.TextImpl;
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
public class DingTextDO extends TextImpl {
    /**
     * 推送内容
     */
    private Text text;

    /**
     * At信息
     */
    private AtDo at;

    /**
     * 推送类型
     */
    @JSONField(name = "msgtype")
    private String msgType;

    public DingTextDO() {
        text = new Text();
        at = new AtDo(this);
        msgType = super.getMsgType();
    }

    public DingTextDO(TextImpl webhook) {
        text = new Text(webhook);
        at = new AtDo(webhook);
        msgType = webhook.getMsgType();
        setContent(webhook.getContent());
        setToken(webhook.getToken());
        setSignature(webhook.getSignature());
        setUri(webhook.getUri());
    }

    public String getMsgType() {
        return msgType;
    }

    private void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    @JSONField(serialize = false)
    public String getContent() {
        return text.getContent();
    }

    public void setContent(String content) {
        text.setContent(content);
    }

    private class Text {
        private String content;

        private Text() {
        }

        public Text(String content) {
            this.content = content;
        }

        public Text(TextImpl webhook) {
            content = webhook.getContent();
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
