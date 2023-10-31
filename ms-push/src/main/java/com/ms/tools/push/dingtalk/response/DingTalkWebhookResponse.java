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

package com.ms.tools.push.dingtalk.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.ToString;

/**
 * @author qyg2297248353
 */
@ToString
public class DingTalkWebhookResponse {
    /**
     * // 消息内容中不包含任何关键词
     * {
     * "errcode":310000,
     * "errmsg":"keywords not in content"
     * }
     * <p>
     * // timestamp 无效
     * {
     * "errcode":310000,
     * "errmsg":"invalid timestamp"
     * }
     * <p>
     * // 签名不匹配
     * {
     * "errcode":310000,
     * "errmsg":"sign not match"
     * }
     * <p>
     * // IP地址不在白名单
     * {
     * "errcode":310000,
     * "errmsg":"ip X.X.X.X not in whitelist"
     * }
     * <a href="https://open.dingtalk.com/document/group/custom-robot-access">错误码一览表</a>
     */
    @JSONField(name = "errcode")
    private Integer code;

    @JSONField(name = "errmsg")
    private String message;

    private Boolean success;

    public DingTalkWebhookResponse() {
        success = false;
    }

    public static DingTalkWebhookResponse errorNull() {
        DingTalkWebhookResponse response = new DingTalkWebhookResponse();
        response.setCode(404);
        response.setMessage("推送类型不存在,请继承并实现基本类型接口");
        return response;
    }

    public static DingTalkWebhookResponse errorSignature() {
        DingTalkWebhookResponse response = new DingTalkWebhookResponse();
        response.setCode(500);
        response.setMessage("签名异常");
        return response;
    }

    public static DingTalkWebhookResponse errorRequest() {
        DingTalkWebhookResponse response = new DingTalkWebhookResponse();
        response.setCode(400);
        response.setMessage("请求失败");
        return response;
    }

    public boolean hasSuccess() {
        if (success != null) {
            return success;
        }
        return false;
    }

    public DingTalkWebhookResponse parse() {
        if (code != null && code == 0) {
            success = true;
        }
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
