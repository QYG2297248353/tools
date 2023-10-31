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

package com.ms.tools.push.dingtalk.factory;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.push.dingtalk.enums.MsgTypeEnum;

/**
 * @author ms2297248353
 */
public interface BaseImpl {
    /**
     * 消息类型
     *
     * @return 类型
     */
    @JSONField(serialize = false)
    MsgTypeEnum getMsgTypeEnum();

    /**
     * 推送地址
     * 默认为钉钉机器人
     * https://oapi.dingtalk.com/robot/send
     *
     * @return 机器人推送地址
     */
    @JSONField(serialize = false)
    default String getPushUrl() {
        return "https://oapi.dingtalk.com/robot/send";
    }

    /**
     * 推送机器人
     *
     * @return "access_token"
     */
    @JSONField(serialize = false)
    default String getPushToken() {
        return Strings.EMPTY;
    }

    /**
     * 签名
     * 未使用返回 null
     *
     * @return null
     */
    @JSONField(serialize = false)
    default String getSignature() {
        return null;
    }
}
