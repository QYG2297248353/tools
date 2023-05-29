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
 * 腾讯云短信模板效验
 *
 * @author qyg2297248353
 */
public class TemplateValidationTencentVo {

    private final BasicSmsTencentVo basic;
    private Validation validation;

    private TemplateValidationTencentVo() {
        basic = new BasicSmsTencentVo();
        basic.setVersion(TencentCloudApiConfig.Version.V20210111);
        basic.setAction(TencentCloudApiConfig.Action.DESCRIBE_SMS_SIGN_LIST);
        basic.setRegion(TencentCloudApiConfig.Region.AP_GUANGZHOU);
    }

    public static TemplateValidationTencentVo build(Validation validation) {
        TemplateValidationTencentVo vo = new TemplateValidationTencentVo();
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
         * 是否国际/港澳台短信：
         * 0：表示国内短信。
         * 1：表示国际/港澳台短信。
         */
        @JSONField(name = "International")
        private Integer international;

        /**
         * 短信模板ID 数组 - 可选值
         */
        @JSONField(name = "TemplateIdSet")
        private Integer[] templateIdSet;

        /**
         * 最大上限 - 可选值
         * 最多100
         * 注：默认为0，TemplateIdSet 为空时启用
         */
        @JSONField(name = "Limit")
        private Integer limit;
        /**
         * 偏移量 - 可选值
         * 注：默认为0，TemplateIdSet 为空时启用
         */
        @JSONField(name = "Offset")
        private Integer offset;

        private Validation() {
            international = 0;
            limit = 0;
            offset = 0;
        }

        public Validation(Integer... ids) {
            this();
            templateIdSet = ids;
        }

        public Validation(Integer limit) {
            this();
            this.limit = limit;
        }

        public Validation(Integer limit, Integer offset) {
            this();
            this.limit = limit;
            this.offset = offset;
        }

        public Validation(Boolean isChina, Integer... ids) {
            this();
            templateIdSet = ids;
            if (Boolean.FALSE.equals(isChina)) {
                international = 1;
            }
        }

        public Integer getInternational() {
            return international;
        }

        public void setInternational(Integer international) {
            this.international = international;
        }

        public Integer[] getTemplateIdSet() {
            return templateIdSet;
        }

        public void setTemplateIdSet(Integer[] templateIdSet) {
            this.templateIdSet = templateIdSet;
        }

        public void setTemplateIdSet(Integer templateIdSet) {
            this.templateIdSet = new Integer[templateIdSet];
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

    }
}