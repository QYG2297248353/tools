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

package com.ms.push.webhook.dingtalk.template;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.push.webhook.dingtalk.to.MarkdownImpl;
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
public class DingMarkdownDO extends MarkdownImpl {
    /**
     * 推送内容
     */
    private Markdown markdown;

    /**
     * At信息
     */
    private AtDo at;

    /**
     * 推送类型
     */
    @JSONField(name = "msgtype")
    private String msgType;

    /**
     * markdown 支持类型
     * <p>
     * 标题
     * <p>
     * 引用
     * <p>
     * 文字加粗、斜体
     * <p>
     * 链接
     * <p>
     * 图片（建议不要超过20张）
     * <p>
     * 无序列表
     * <p>
     * 有序列表
     */
    public DingMarkdownDO() {
        markdown = new Markdown();
        at = new AtDo(this);
        msgType = super.getMsgType();
    }

    public DingMarkdownDO(MarkdownImpl webhook) {
        msgType = webhook.getMsgType();
        setToken(webhook.getToken());
        setSignature(webhook.getSignature());
        setUri(webhook.getUri());
        at = new AtDo(webhook);

        markdown = new Markdown(webhook);
    }

    public String getMsgType() {
        return msgType;
    }

    private void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    @JSONField(serialize = false)
    public String getTitle() {
        return markdown.getTitle();
    }

    public void setTitle(String title) {
        markdown.setTitle(title);
    }

    @Override
    @JSONField(serialize = false)
    public String getText() {
        return markdown.getText();
    }

    public void setText(String text) {
        markdown.setText(text);
    }


    private class Markdown {
        private String title;
        private String text;

        public Markdown() {
        }

        public Markdown(MarkdownImpl webhook) {
            title = webhook.getTitle();
            text = webhook.getText();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
