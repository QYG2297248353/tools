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
import com.ms.push.webhook.dingtalk.factory.AtImpl;

/**
 * @author ms2297248353
 */
public abstract class MarkdownImpl extends AbstractConfig implements AtImpl {
    /**
     * 消息标题
     *
     * @return 推送标题
     */
    @JSONField(serialize = false)
    public abstract String getTitle();

    /**
     * 消息内容
     *
     * @return 推送内容
     */
    @JSONField(serialize = false)
    public abstract String getText();

    @Override
    @JSONField(serialize = false)
    public MsgTypeEnum getMsgTypeEnum() {
        return MsgTypeEnum.MARKDOWN;
    }
}
