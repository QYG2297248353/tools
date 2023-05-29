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
import lombok.Getter;
import lombok.Setter;

/**
 * 腾讯云短信签名效验
 *
 * @author qyg2297248353
 */
public class PullSmsSendStatusTencentVo {

    private final BasicSmsTencentVo basic;
    private SmsSendStatus smsSendStatus;

    private PullSmsSendStatusTencentVo() {
        basic = new BasicSmsTencentVo();
        basic.setVersion(TencentCloudApiConfig.Version.V20210111);
        basic.setAction(TencentCloudApiConfig.Action.PULL_SMS_SEND_STATUS);
        basic.setRegion(TencentCloudApiConfig.Region.AP_GUANGZHOU);
    }

    public static PullSmsSendStatusTencentVo build(SmsSendStatus smsSendStatus) {
        PullSmsSendStatusTencentVo vo = new PullSmsSendStatusTencentVo();
        vo.setSend(smsSendStatus);
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

    private void setSend(SmsSendStatus smsSendStatus) {
        this.smsSendStatus = smsSendStatus;
    }

    @Getter
    @Setter
    public static class SmsSendStatus {
        /**
         * 拉取最大条数
         * Max 100
         */
        @JSONField(name = "Limit")
        private Integer limit;
        /**
         * 短信 SdkAppId
         */
        @JSONField(name = "SmsSdkAppId")
        private String smsSdkAppId;

        private SmsSendStatus() {
            limit = 100;
        }

        /**
         * 基本构造
         *
         * @param smsSdkAppId 短信 SdkAppId
         */
        public SmsSendStatus(String smsSdkAppId) {
            this();
            this.smsSdkAppId = smsSdkAppId;
        }

        /**
         * 基本构造
         *
         * @param smsSdkAppId 短信 SdkAppId
         * @param limit       拉取最大条数
         */
        public SmsSendStatus(String smsSdkAppId, Integer limit) {
            this();
            this.limit = limit;
            this.smsSdkAppId = smsSdkAppId;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public String getSmsSdkAppId() {
            return smsSdkAppId;
        }

        public void setSmsSdkAppId(String smsSdkAppId) {
            this.smsSdkAppId = smsSdkAppId;
        }
    }
}