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
public class PullSmsSendStatusByPhoneNumberTencentVo {

    private final BasicSmsTencentVo basic;
    private SmsSendStatus smsSendStatus;

    private PullSmsSendStatusByPhoneNumberTencentVo() {
        basic = new BasicSmsTencentVo();
        basic.setVersion(TencentCloudApiConfig.Version.V20210111);
        basic.setAction(TencentCloudApiConfig.Action.PULL_SMS_SEND_STATUS_BY_PHONE_NUMBER);
        basic.setRegion(TencentCloudApiConfig.Region.AP_GUANGZHOU);
    }

    public static PullSmsSendStatusByPhoneNumberTencentVo build(SmsSendStatus smsSendStatus) {
        PullSmsSendStatusByPhoneNumberTencentVo vo = new PullSmsSendStatusByPhoneNumberTencentVo();
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
         * 偏移量
         * 注：目前固定设置为0。
         */
        @JSONField(name = "Offset")
        private Integer offset;

        /**
         * 拉取最大条数
         * Max 100
         */
        @JSONField(name = "Limit")
        private Integer limit;

        /**
         * 下发目的手机号码，依据 E.164 标准为：+[国家（或地区）码][手机号]
         * 示例：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。
         */
        @JSONField(name = "PhoneNumber")
        private String phoneNumber;

        /**
         * 短信 SdkAppId
         */
        @JSONField(name = "SmsSdkAppId")
        private String smsSdkAppId;

        /**
         * 拉取起始时间
         * UNIX 时间戳（时间：秒）
         * 注：最大可拉取当前时期前7天的数据。
         */
        @JSONField(name = "BeginTime")
        private Long beginTime;

        /**
         * 拉取截止时间-可选
         * UNIX 时间戳（时间：秒）
         */
        @JSONField(name = "EndTime")
        private Long endTime;

        private SmsSendStatus() {
            offset = 0;
            limit = 100;
            // 默认拉取最近7天的数据
            beginTime = System.currentTimeMillis() / 1000 - 7 * 24 * 60 * 60 + 1000;
        }

        /**
         * 基本构造
         * 默认拉取最近7天的数据
         * 默认偏移量为0
         *
         * @param smsSdkAppId 短信SdkAppId
         * @param phoneNumber 手机号
         */
        public SmsSendStatus(String smsSdkAppId, String phoneNumber) {
            this();
            this.smsSdkAppId = smsSdkAppId;
            this.phoneNumber = phoneNumber;
        }

        /**
         * 基本构造-开始时间
         *
         * @param smsSdkAppId 短信SdkAppId
         * @param phoneNumber 手机号
         * @param beginTime   开始时间
         */
        public SmsSendStatus(String smsSdkAppId, String phoneNumber, Long beginTime) {
            this(smsSdkAppId, phoneNumber);
            this.beginTime = beginTime;
        }

        /**
         * 基本构造-开始时间-结束时间
         *
         * @param smsSdkAppId 短信SdkAppId
         * @param phoneNumber 手机号
         * @param beginTime   开始时间
         * @param endTime     结束时间
         */
        public SmsSendStatus(String smsSdkAppId, String phoneNumber, Long beginTime, Long endTime) {
            this(smsSdkAppId, phoneNumber, beginTime);
            this.endTime = endTime;
        }


        /**
         * 基本构造-开始时间-结束时间-偏移量
         *
         * @param smsSdkAppId 短信SdkAppId
         * @param phoneNumber 手机号
         * @param beginTime   开始时间
         * @param endTime     结束时间
         * @param offset      偏移量
         */
        public SmsSendStatus(String smsSdkAppId, String phoneNumber, Long beginTime, Long endTime, Integer offset) {
            this(smsSdkAppId, phoneNumber, beginTime, endTime);
            this.offset = offset;
        }

        /**
         * 基本构造-开始时间-结束时间-偏移量-拉取最大条数
         *
         * @param smsSdkAppId 短信SdkAppId
         * @param phoneNumber 手机号
         * @param beginTime   开始时间
         * @param endTime     结束时间
         * @param offset      偏移量
         * @param limit       拉取最大条数
         */
        public SmsSendStatus(String smsSdkAppId, String phoneNumber, Long beginTime, Long endTime, Integer offset, Integer limit) {
            this(smsSdkAppId, phoneNumber, beginTime, endTime, offset);
            this.limit = limit;
        }

        /**
         * 基本构造-拉取数量
         *
         * @param smsSdkAppId 短信SdkAppId
         * @param phoneNumber 手机号
         * @param limit       拉取最大条数
         */
        public SmsSendStatus(String smsSdkAppId, String phoneNumber, Integer limit) {
            this(smsSdkAppId, phoneNumber);
            this.limit = limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getSmsSdkAppId() {
            return smsSdkAppId;
        }

        public void setSmsSdkAppId(String smsSdkAppId) {
            this.smsSdkAppId = smsSdkAppId;
        }

        public Long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Long beginTime) {
            this.beginTime = beginTime;
        }

        public Long getEndTime() {
            return endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }
    }
}