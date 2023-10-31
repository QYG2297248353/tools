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

package com.ms.tools.push.bark.factory;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.core.base.info.UriRegexpUtils;
import com.ms.tools.push.bark.enums.BarkLevelEnum;
import com.ms.tools.push.bark.enums.BarkSoundEnum;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ms2297248353
 */
@ToString
@Getter
public class BarkDO implements BarkImpl {
    /**
     * 标题
     * Notification title (font size would be larger than the body)
     */
    private String title;
    /**
     * 内容
     * Notification content
     */
    private String body;
    /**
     * 分组
     * The group of the notification
     */
    private String group;
    /**
     * 密钥信息
     */
    @JSONField(serialize = false)
    private String key;
    /**
     * 占用字段
     * 忽略
     */
    @JSONField(serialize = false)
    private String category;
    /**
     * 音效
     */
    private BarkSoundEnum sound;
    /**
     * 自动复制
     * defaultValue = "1"
     * Must be 1
     */
    private Integer automaticallyCopy;
    /**
     * 复制文本
     * 实际复制文本
     * The value to be copied
     */
    private String copy;

    /**
     * 是否保存推送历史
     * Value must be 1.
     * Whether or not should be archived by the app
     */
    @JSONField(name = "isArchive")
    private Integer saveHistory;

    /**
     * 推送图标
     * url链接式图片
     * An url to the icon, available only on iOS 15 or later
     */
    private String icon;

    /**
     * 时效性
     */
    private BarkLevelEnum level;

    /**
     * 徽章 图标右上角数字
     * The number displayed next to App icon
     * <a href="https://developer.apple.com/documentation/usernotifications/unnotificationcontent/1649864-badge" >Apple Developer</a>
     */
    private Integer badge;

    /**
     * 跳转地址(点击后)
     * Url that will jump when click notification
     */
    private String url;

    private BarkDO(String key) {
        setKey(key);
        setGroup(setGroup());
        setSound(setSound());
        setAutomaticallyCopy(setAutomaticallyCopy());
        setCopy(setCopy());
        setSaveHistory(setSaveHistory());
        setIcon(setIcon());
        setLevel(setLevel());
        setBadge(setBadge());
        setUrl(setUrl());
    }

    public BarkDO(String key, String title) {
        this(key);
        setTitle(title);
    }

    public BarkDO(String key, String title, String body) {
        this(key);
        setTitle(title);
        setBody(body);
    }

    public BarkDO(String key, String title, String body, Integer badge) {
        this(key);
        setTitle(title);
        setBody(body);
        setBadge(badge);
    }

    public BarkDO(String key, String title, String body, String url) {
        this(key);
        setTitle(title);
        setBody(body);
        setUrl(url);
    }

    public BarkDO(String key, String title, String body, BarkLevelEnum level) {
        this(key);
        setTitle(title);
        setBody(body);
        setLevel(level);
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSound(BarkSoundEnum sound) {
        this.sound = sound;
    }

    public void setAutomaticallyCopy(boolean automaticallyCopy) {
        if (automaticallyCopy) {
            this.automaticallyCopy = 1;
        }
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public void setSaveHistory(boolean saveHistory) {
        if (saveHistory) {
            this.saveHistory = 1;
        }
    }

    public void setIcon(String icon) {
        if (UriRegexpUtils.isUrlFtp(url)) {
            this.icon = icon;
        }
    }

    public void setLevel(BarkLevelEnum level) {
        this.level = level;
    }

    public void setBadge(int badge) {
        if (badge > 0) {
            this.badge = badge;
        }
    }

    public void setUrl(String url) {
        if (UriRegexpUtils.isUrlFtp(url)) {
            this.url = url;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String setUrl() {
        return "https://bark.qyg2297248353.top";
    }
}
