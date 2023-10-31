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

package com.ms.tools.api.tencent.sms.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.api.tencent.factory.TencentCloudApiConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * 腾讯云短信签名效验
 *
 * @author qyg2297248353
 */
public class SignatureValidationTencentVo {

    private final BasicSmsTencentVo basic;
    private Validation validation;

    private SignatureValidationTencentVo() {
        basic = new BasicSmsTencentVo();
        basic.setVersion(TencentCloudApiConfig.Version.V20210111);
        basic.setAction(TencentCloudApiConfig.Action.DESCRIBE_SMS_SIGN_LIST);
        basic.setRegion(TencentCloudApiConfig.Region.AP_GUANGZHOU);
    }

    public static SignatureValidationTencentVo build(Validation validation) {
        SignatureValidationTencentVo vo = new SignatureValidationTencentVo();
        vo.setSend(validation);
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

    private void setSend(Validation validation) {
        this.validation = validation;
    }

    @Getter
    @Setter
    public static class Validation {
        // 推送参数
        /**
         * 签名 ID 数组
         */
        @JSONField(name = "SignIdSet")
        private Integer[] signIdSet;

        /**
         * 是否国际/港澳台短信：
         * 0：表示国内短信。
         * 1：表示国际/港澳台短信。
         */
        @JSONField(name = "International")
        private Integer international;


        private Validation() {
            international = 0;
        }

        /**
         * 构建
         *
         * @param smsSignId 签名ID 数组
         */
        public Validation(Integer... smsSignId) {
            this();
            signIdSet = smsSignId;
        }

        /**
         * 构建
         *
         * @param isChina   是否为国内短信 +86
         * @param smsSignId 签名ID 数组
         */
        public Validation(Boolean isChina, Integer... smsSignId) {
            this();
            signIdSet = smsSignId;
            if (Boolean.FALSE.equals(isChina)) {
                international = 1;
            }
        }

        public Integer[] getSignIdSet() {
            return signIdSet;
        }

        public void setSignIdSet(Integer[] signIdSet) {
            this.signIdSet = signIdSet;
        }

        public void setSignIdSet(Integer signIdSet) {
            this.signIdSet = new Integer[signIdSet];
        }

        public Integer getInternational() {
            return international;
        }

        public void setInternational(Integer international) {
            this.international = international;
        }
    }
}
