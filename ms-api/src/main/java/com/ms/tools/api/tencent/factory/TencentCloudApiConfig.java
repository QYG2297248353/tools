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

package com.ms.tools.api.tencent.factory;

/**
 * @author qyg2297248353
 */
public class TencentCloudApiConfig {
    public enum Host {
        /**
         * 主域名-就近原则
         */
        MAIN_SMS("sms.tencentcloudapi.com", "主域名", "Host"),
        /**
         * 广州
         */
        GUANGZHOU_SMS("sms.ap-guangzhou.tencentcloudapi.com", "广州", "Host"),
        /**
         * 上海
         */
        SHANGHAI_SMS("sms.ap-shanghai.tencentcloudapi.com", "上海", "Host"),
        /**
         * 北京
         */
        BEIJING_SMS("sms.ap-beijing.tencentcloudapi.com", "北京", "Host"),
        /**
         * 成都
         */
        CHENGDU_SMS("sms.ap-chengdu.tencentcloudapi.com", "成都", "Host"),
        /**
         * 重庆
         */
        CHONGQING_SMS("sms.ap-chongqing.tencentcloudapi.com", "重庆", "Host");

        private final String host;
        private final String description;
        private final String header;

        Host(String host, String description, String header) {
            this.host = host;
            this.description = description;
            this.header = header;
        }

        public String getHost() {
            return host;
        }

        public String getWebsite() {
            return "https://" + host;
        }

        public String getDescription() {
            return description;
        }

        public String getHeader() {
            return header;
        }
    }


    public enum Action {
        /**
         * 拉取状态相关接口
         */
        PULL_SMS_REPLY_STATUS("PullSmsReplyStatus", "拉取短信回复状态", SmsApi.Region),
        PULL_SMS_REPLY_STATUS_BY_PHONE_NUMBER("PullSmsReplyStatusByPhoneNumber", "拉取单个号码短信回复状态", SmsApi.Region),
        PULL_SMS_SEND_STATUS("PullSmsSendStatus", "拉取短信下发状态", SmsApi.Region),
        PULL_SMS_SEND_STATUS_BY_PHONE_NUMBER("PullSmsSendStatusByPhoneNumber", "拉取单个号码短信下发状态", SmsApi.Region),
        /**
         * 短信转化率相关接口
         */
        REPORT_CONVERSION("ReportConversion", "上报转换率", SmsApi.Region),
        /**
         * 发送短信相关接口
         */
        SEND_SMS("SendSms", "发送短信", SmsApi.Region),
        /**
         * 短信签名相关接口
         */
        ADD_SMS_SIGN("AddSmsSign", "添加短信签名", SmsApi.Region),
        DELETE_SMS_SIGN("DeleteSmsSign", "删除短信签名", SmsApi.Region),
        DESCRIBE_SMS_SIGN_LIST("DescribeSmsSignList", "短信签名状态查询", SmsApi.Region),
        MODIFY_SMS_SIGN("ModifySmsSign", "修改短信签名", SmsApi.Region),
        /**
         * 短信模板相关接口
         */
        ADD_SMS_TEMPLATE("AddSmsTemplate", "添加短信模板", SmsApi.Region),
        DELETE_SMS_TEMPLATE("DeleteSmsTemplate", "删除短信模板", SmsApi.Region),
        DESCRIBE_SMS_TEMPLATE_LIST("DescribeSmsTemplateList", "短信模板状态查询", SmsApi.Region),
        MODIFY_SMS_TEMPLATE("ModifySmsTemplate", "修改短信模板", SmsApi.Region),
        /**
         * 短信统计相关接口
         */
        CALLBACK_STATUS_STATISTICS("CallbackStatusStatistics", "回执数据统计", SmsApi.Region),
        SEND_STATUS_STATISTICS("SendStatusStatistics", "发送短信数据统计", SmsApi.Region),
        SMS_PACKAGES_STATISTICS("SmsPackagesStatistics", "套餐包信息统计", SmsApi.Region),
        /**
         * 短信号码相关接口
         */
        DESCRIBE_PHONE_NUMBER_INFO("DescribePhoneNumberInfo", "号码信息查询", SmsApi.Region);

        private final String action;
        private final String description;
        private final String header;

        Action(String action, String description, String header) {
            this.action = action;
            this.description = description;
            this.header = header;
        }

        public String getAction() {
            return action;
        }

        public String getDescription() {
            return description;
        }

        public String getHeader() {
            return header;
        }
    }

    public enum Region {
        /**
         * 华北地区(北京)
         */
        AP_BEIJING("ap-beijing", "华北地区(北京)", SmsApi.Region),
        /**
         * 华南地区(广州)
         */
        AP_GUANGZHOU("ap-guangzhou", "华南地区(广州)", SmsApi.Region),
        /**
         * 华东地区(南京)
         */
        AP_NANJING("ap-nanjing", "华东地区(南京)", SmsApi.Region);
        private final String region;
        private final String description;
        private final String header;

        Region(String region, String description, String header) {
            this.region = region;
            this.description = description;
            this.header = header;
        }

        public String getRegion() {
            return region;
        }

        public String getDescription() {
            return description;
        }

        public String getHeader() {
            return header;
        }
    }

    public enum Language {
        /**
         * zh-CN 返回中文
         */
        ZH_CH("zh-CN", "zh-CN 返回中文", SmsApi.Language),
        /**
         * en-US 返回英文
         */
        EN_US("en-US", "en-US 返回英文", SmsApi.Language);
        private final String language;
        private final String description;
        private final String header;

        Language(String language, String description, String header) {
            this.language = language;
            this.description = description;
            this.header = header;
        }

        public String getLanguage() {
            return language;
        }

        public String getDescription() {
            return description;
        }

        public String getHeader() {
            return header;
        }
    }

    public enum Version {
        /**
         * 2021-01-11
         */
        V20210111("2021-01-11", "2021-01-11", SmsApi.Version);
        private final String version;
        private final String description;
        private final String header;

        Version(String version, String description, String header) {
            this.version = version;
            this.description = description;
            this.header = header;
        }

        public String getVersion() {
            return version;
        }

        public String getDescription() {
            return description;
        }

        public String getHeader() {
            return header;
        }
    }

    public static class SmsApi {
        public static String Token = "X-TC-Token";
        public static String Authorization = "Authorization";
        public static String Version = "X-TC-Version";
        public static String Timestamp = "X-TC-Timestamp";
        public static String Language = "X-TC-Language";
        public static String Region = "X-TC-Region";
        public static String Action = "X-TC-Action";
    }

}
