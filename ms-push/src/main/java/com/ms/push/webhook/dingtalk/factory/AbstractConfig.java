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

package com.ms.push.webhook.dingtalk.factory;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.push.webhook.dingtalk.enums.MsgTypeEnum;

/**
 * @author ms2297248353
 */
public abstract class AbstractConfig implements BaseImpl {
    /**
     * 推送配置-地址
     */
    @JSONField(serialize = false)
    private String uri;
    /**
     * 推送配置-机器人Token列表
     */
    @JSONField(serialize = false)
    private String token;
    /**
     * 推送配置-签名
     */
    @JSONField(serialize = false)
    private String signature;
    /**
     * 推送配置-消息类型
     */
    private String msgType;

    protected AbstractConfig() {
        uri = getPushUrl();
        token = getPushToken();
        signature = getSignature();
        msgType = getMsgTypeEnum().getMsgType();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType.getMsgType();
    }

    @Override
    @JSONField(serialize = false)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
