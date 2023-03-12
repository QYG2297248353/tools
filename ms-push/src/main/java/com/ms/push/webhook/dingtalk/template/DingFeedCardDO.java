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
import com.ms.push.webhook.dingtalk.to.FeedCardImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Text 推送
 * 如需At成员请重写at方法
 *
 * @author ms2297248353
 */
@ToString
@Getter
@Setter
public class DingFeedCardDO extends FeedCardImpl {

    /**
     * 消息主体
     */
    private FeedCard feedCard;


    /**
     * 推送类型
     */
    @JSONField(name = "msgtype")
    private String msgType;

    public DingFeedCardDO() {
        feedCard = new FeedCard();
        msgType = super.getMsgType();
    }

    public DingFeedCardDO(FeedCardImpl webhook) {
        msgType = webhook.getMsgType();
        setToken(webhook.getToken());
        setSignature(webhook.getSignature());
        setUri(webhook.getUri());

        feedCard = new FeedCard(webhook);
    }

    public String getMsgType() {
        return msgType;
    }

    private void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public void addLink(Link link) {
        super.addLink(link);
        feedCard.setLinks(super.getLinks());
    }

    @Override
    public void addLinks(List<Link> link) {
        super.addLinks(link);
        feedCard.setLinks(super.getLinks());
    }

    /**
     * 覆盖消息主体
     *
     * @param links 消息
     */
    public void setLinks(List<Link> links) {
        feedCard.setLinks(links);
        super.removeAll();
        super.addLinks(links);
    }

    private class FeedCard {
        /**
         * 推送内容
         */
        private List<Link> links;

        public FeedCard() {
        }

        public FeedCard(FeedCardImpl webhook) {
            links = webhook.getLinks();
        }

        public List<Link> getLinks() {
            return links;
        }

        public void setLinks(List<Link> links) {
            this.links = links;
        }
    }
}
