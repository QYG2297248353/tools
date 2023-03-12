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

package com.ms.push.webhook.dingtalk.to;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.push.webhook.dingtalk.enums.MsgTypeEnum;
import com.ms.push.webhook.dingtalk.factory.AbstractConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ms2297248353
 */
public abstract class FeedCardImpl extends AbstractConfig {

    @JSONField(serialize = false)
    private List<Link> links;

    protected FeedCardImpl() {
        links = new ArrayList<>();
    }

    /**
     * 添加消息内容
     *
     * @param link 链接信息
     */
    public void addLink(Link link) {
        links.add(link);
    }

    /**
     * 添加消息内容
     *
     * @param link 链接信息
     */
    public void addLinks(List<Link> link) {
        links.addAll(link);
    }

    /**
     * 移除消息内容
     *
     * @param link 待移除的内容
     */
    public void removeLink(Link link) {
        links.remove(link);
    }

    /**
     * 移除消息内容
     */
    public void removeAll() {
        links.clear();
    }

    public List<Link> getLinks() {
        return links;
    }

    @Override
    @JSONField(serialize = false)
    public MsgTypeEnum getMsgTypeEnum() {
        return MsgTypeEnum.FEED_CARD;
    }

    @Getter
    @Setter
    public static class Link {
        /**
         * 标题
         */
        private String title;
        /**
         * 跳转地址
         */
        private String messageURL;
        /**
         * 图片地址
         */
        private String picURL;

        private Link() {

        }

        public Link(String title, String messageURL, String picURL) {
            this.title = title;
            this.messageURL = messageURL;
            this.picURL = picURL;
        }
    }
}
