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

package com.ms.api.tencent.sms.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.api.tencent.factory.TencentCloudApiConfig;

/**
 * 腾讯云发送短信
 *
 * @author qyg2297248353
 */
public class SendSmsTencentVo {

    private final BasicSmsTencentVo basic;
    private Send send;

    private SendSmsTencentVo() {
        basic = new BasicSmsTencentVo();
        basic.setVersion("2021-01-11");
        basic.setAction(TencentCloudApiConfig.Action.SEND_SMS);
        basic.setRegion(TencentCloudApiConfig.Region.AP_GUANGZHOU);
    }

    public static SendSmsTencentVo build(Send send) {
        SendSmsTencentVo vo = new SendSmsTencentVo();
        vo.setSend(send);
        return vo;
    }

    public String getAction() {
        return basic.getAction();
    }

    public String getVersion() {
        return basic.getVersion();
    }

    public String getRegion() {
        return basic.getRegion();
    }

    public BasicSmsTencentVo getBasic() {
        return basic;
    }

    private void setSend(Send send) {
        this.send = send;
    }

    public static class Send {
        /**
         * 短信码号扩展号，默认未开通
         * 非必填
         */
        @JSONField(name = "ExtendCode")
        private String extendCode;
        /**
         * 用户的 session 内容
         * 非必填
         */
        @JSONField(name = "SessionContext")
        private String sessionContext;

        // 推送参数
        /**
         * 签名
         * <a href="https://console.cloud.tencent.com/smsv2/csms-sign">https://console.cloud.tencent.com/smsv2/csms-sign</a>
         */
        @JSONField(name = "SignName")
        private String signName;
        /**
         * 模板参数
         * <a href="https://console.cloud.tencent.com/smsv2/csms-template">https://console.cloud.tencent.com/smsv2/csms-template</a>
         */
        @JSONField(name = "TemplateId")
        private String templateId;
        /**
         * 应用Id
         * <a href="https://console.cloud.tencent.com/smsv2/app-manage">https://console.cloud.tencent.com/smsv2/app-manage</a>
         */
        @JSONField(name = "SmsSdkAppId")
        private String smsSdkAppId;
        /**
         * 下发手机号码，采用 E.164 标准
         * 格式为+[国家或地区码][手机号]
         * +8613709941037
         * 注：发送国内短信格式还支持0086、86或无任何国家或地区码的11位手机号码，前缀默认为+86
         */
        @JSONField(name = "PhoneNumberSet")
        private String[] phoneNumber;
        /**
         * 模板变量参数
         * 若无模板参数，则设置为空
         */
        @JSONField(name = "TemplateParamSet")
        private String[] templateParam;

        /**
         * 国际/港澳台短信已申请独立 SenderId
         * 默认使用公共 SenderId
         * 非必填
         */
        @JSONField(name = "SenderId")
        private String senderId;

        private Send() {
        }

        /**
         * 动态版-构建
         * 可链式调用build添加变量
         *
         * @param signName    签名
         * @param templateId  模板Id
         * @param smsSdkAppId 应用Id
         */
        public Send(String signName, String templateId, String smsSdkAppId) {
            this();
            this.signName = signName;
            this.templateId = templateId;
            this.smsSdkAppId = smsSdkAppId;
        }

        /**
         * 无变量版本-构建
         *
         * @param signName    签名
         * @param templateId  模板Id
         * @param smsSdkAppId 应用Id
         * @param phoneNumber 手机号码 数组
         */
        public Send(String signName, String templateId, String smsSdkAppId, String[] phoneNumber) {
            this();
            this.signName = signName;
            this.templateId = templateId;
            this.smsSdkAppId = smsSdkAppId;
            this.phoneNumber = phoneNumber;
        }

        /**
         * 变量版-构建
         *
         * @param signName      签名
         * @param templateId    模板Id
         * @param smsSdkAppId   应用Id
         * @param phoneNumber   手机号码 数组
         * @param templateParam 变量参数 数组
         */
        public Send(String signName, String templateId, String smsSdkAppId, String[] phoneNumber, String[] templateParam) {
            this();
            this.signName = signName;
            this.templateId = templateId;
            this.smsSdkAppId = smsSdkAppId;
            this.phoneNumber = phoneNumber;
            this.templateParam = templateParam;
        }

        /**
         * 单变量版-构建
         *
         * @param signName      签名
         * @param templateId    模板Id
         * @param smsSdkAppId   应用Id
         * @param phoneNumber   手机号码
         * @param templateParam 变量参数
         */
        public Send(String signName, String templateId, String smsSdkAppId, String phoneNumber, String templateParam) {
            this();
            this.signName = signName;
            this.templateId = templateId;
            this.smsSdkAppId = smsSdkAppId;
            this.phoneNumber = new String[]{phoneNumber};
            this.templateParam = new String[]{templateParam};
        }

        /**
         * 变量版-构建
         *
         * @param signName      签名
         * @param templateId    模板Id
         * @param smsSdkAppId   应用Id
         * @param phoneNumber   手机号码
         * @param templateParam 变量参数 数组
         */
        public Send(String signName, String templateId, String smsSdkAppId, String phoneNumber, String... templateParam) {
            this();
            this.signName = signName;
            this.templateId = templateId;
            this.smsSdkAppId = smsSdkAppId;
            this.phoneNumber = new String[]{phoneNumber};
            this.templateParam = templateParam;
        }

        /**
         * 链式构建
         *
         * @param phoneNumber   手机号码 数组
         * @param templateParam 变量参数 数组
         * @return
         */
        public Send build(String[] phoneNumber, String[] templateParam) {
            this.phoneNumber = phoneNumber;
            this.templateParam = templateParam;
            return this;
        }

        /**
         * 链式构建
         *
         * @param phoneNumber   手机号码
         * @param templateParam 变量参数
         * @return
         */
        public Send build(String phoneNumber, String templateParam) {
            this.phoneNumber = new String[]{phoneNumber};
            this.templateParam = new String[]{templateParam};
            return this;
        }

        /**
         * 链式构建
         *
         * @param phoneNumber   手机号码
         * @param templateParam 变量参数 数组
         * @return
         */
        public Send build(String phoneNumber, String... templateParam) {
            this.phoneNumber = new String[]{phoneNumber};
            this.templateParam = templateParam;
            return this;
        }

        public String getExtendCode() {
            return extendCode;
        }

        public void setExtendCode(String extendCode) {
            this.extendCode = extendCode;
        }

        public String getSessionContext() {
            return sessionContext;
        }

        public void setSessionContext(String sessionContext) {
            this.sessionContext = sessionContext;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getSmsSdkAppId() {
            return smsSdkAppId;
        }

        public void setSmsSdkAppId(String smsSdkAppId) {
            this.smsSdkAppId = smsSdkAppId;
        }

        public String[] getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String[] phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String[] getTemplateParam() {
            return templateParam;
        }

        public void setTemplateParam(String[] templateParam) {
            this.templateParam = templateParam;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }
    }
}
