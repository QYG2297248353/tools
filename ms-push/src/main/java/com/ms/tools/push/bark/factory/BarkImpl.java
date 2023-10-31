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

import com.ms.tools.push.bark.enums.BarkConfig;
import com.ms.tools.push.bark.enums.BarkLevelEnum;
import com.ms.tools.push.bark.enums.BarkSoundEnum;

/**
 * @author ms2297248353
 */
public interface BarkImpl {

    /**
     * title
     * Notification title (font size would be larger than the body)
     *
     * @return 标题
     */
    String getTitle();

    /**
     * body
     * Notification content
     *
     * @return 内容
     */
    String getBody();

    /**
     * device_key
     * Bark APP device key 推送链接域名之后的值为key
     *
     * @return 推送密钥
     */
    String getKey();

    /**
     * 服务器推送地址
     * 使用自建服务器请实现方法
     *
     * @return 服务器地址
     */
    default String getPushServer() {
        return BarkConfig.PUSH_SERVER_URI;
    }

    /**
     * 分组名称
     *
     * @return 字段信息：分组名称
     */
    default String setGroup() {
        return null;
    }

    /**
     * 提示音效
     *
     * @return 字段信息：提示音效
     */
    default BarkSoundEnum setSound() {
        return null;
    }

    /**
     * 开启自动复制
     *
     * @return 字段信息：开启自动复制
     */
    default Boolean setAutomaticallyCopy() {
        return Boolean.FALSE;
    }

    /**
     * 实际复制值
     *
     * @return 字段信息：实际复制值
     */
    default String setCopy() {
        return null;
    }

    /**
     * 是否自动保存至推送历史
     *
     * @return 字段信息：是否自动保存至推送历史
     */
    default Boolean setSaveHistory() {
        return Boolean.FALSE;
    }

    /**
     * 图标
     *
     * @return 字段信息：图标
     */
    default String setIcon() {
        return null;
    }

    /**
     * 时效
     *
     * @return 字段信息：时效
     */
    default BarkLevelEnum setLevel() {
        return null;
    }

    /**
     * 徽章
     *
     * @return 字段信息：徽章
     */
    default Integer setBadge() {
        return 0;
    }

    /**
     * 跳转地址
     *
     * @return 字段信息：跳转地址
     */
    default String setUrl() {
        return null;
    }
}
