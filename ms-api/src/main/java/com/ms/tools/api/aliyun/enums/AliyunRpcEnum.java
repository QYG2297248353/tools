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

package com.ms.tools.api.aliyun.enums;


import com.ms.tools.core.base.datetime.DateUtils;
import com.ms.tools.core.id.ID;

/**
 * 阿里云短信服务枚举
 */
public class AliyunRpcEnum {

    /**
     * 签名唯一随机数
     *
     * @return 签名唯一随机数
     */
    public static String getSignatureNonce() {
        return ID.snowflakeString();
    }

    /**
     * 时间戳
     *
     * @return 时间戳
     */
    public static String getTimestamp() {
        return DateUtils.getNowFormat("yyyy-MM-ddTHH:mm:ssZ");
    }

    /**
     * 请求协议
     */
    enum Protocol {
        HTTP("http://", "HTTP"),
        HTTPS("https://", "HTTPS");

        private final String protocol;

        private final String desc;

        Protocol(String protocol, String desc) {
            this.protocol = protocol;
            this.desc = desc;
        }

        public String getProtocol() {
            return protocol;
        }
    }

    /**
     * 请求方法
     */
    enum Method {
        GET("GET"),
        POST("POST");

        private final String method;

        Method(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }

    /**
     * 公共请求参数
     */
    enum CommonParams {
        VERSION("Version", "2017-05-25"),
        FORMAT("Format", "JSON"),
        ACCESS_KEY_ID("AccessKeyId", ""),
        SIGNATURE_NONCE("SignatureNonce", ""),
        TIMESTAMP("Timestamp", ""),
        SIGNATURE_METHOD("SignatureMethod", "HMAC-SHA1"),
        SIGNATURE_VERSION("SignatureVersion", "1.0"),
        SIGNATURE("Signature", "");

        private final String param;

        private final String defaults;

        CommonParams(String param, String defaults) {
            this.param = param;
            this.defaults = defaults;
        }

        public String getParam() {
            return param;
        }

        public String getDefaults() {
            return defaults;
        }
    }

    enum Format {
        JSON("JSON", "JSON"),
        XML("XML", "XML");

        private final String format;

        private final String desc;

        Format(String format, String desc) {
            this.format = format;
            this.desc = desc;
        }

        public String getFormat() {
            return format;
        }

        public String getDesc() {
            return desc;
        }
    }

    enum Endpoint {
        AP_CN_QINGDAO("华北1（青岛）", "cn-qingdao", "dysmsapi.aliyuncs.com", ""),
        AP_CN_BEIJING("华北2（北京）", "cn-beijing", "dysmsapi.aliyuncs.com", ""),
        AP_CN_ZHANGJIAKOU("华北3（张家口）", "cn-zhangjiakou", "dysmsapi.aliyuncs.com", ""),
        AP_CN_HUHEHAOTE("华北5（呼和浩特）", "cn-huhehaote", "dysmsapi.aliyuncs.com", ""),
        AP_CN_WULANCHABU("华北6（乌兰察布）", "cn-wulanchabu", "dysmsapi.aliyuncs.com", ""),
        AP_CN_HANGZHOU("华东1（杭州）", "cn-hangzhou", "dysmsapi.aliyuncs.com", ""),
        AP_CN_SHANGHAI("华东2（上海）", "cn-shanghai", "dysmsapi.aliyuncs.com", ""),
        AP_CN_SHENZHEN("华南1（深圳）", "cn-shenzhen", "dysmsapi.aliyuncs.com", ""),
        AP_CN_CHENGDU("西南1（成都）", "cn-chengdu", "dysmsapi.aliyuncs.com", ""),
        AP_CN_HONGKONG("香港", "cn-hongkong", "dysmsapi.aliyuncs.com", "dysmsapi-xman-vpc.cn-hongkong.aliyuncs.com"),
        AP_AP_NORTHEAST_1("日本（东京）", "ap-northeast-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        AP_AP_SOUTHEAST_1("新加坡", "ap-southeast-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        AP_AP_SOUTHEAST_2("澳大利亚（悉尼）", "ap-southeast-2", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        AP_AP_SOUTHEAST_3("马来西亚（吉隆坡）", "ap-southeast-3", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        AP_AP_SOUTHEAST_5("印度尼西亚（雅加达）", "ap-southeast-5", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),

        EA_US_EAST_1("美国（弗吉尼亚）", "us-east-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        EA_US_WEST_1("美国（硅谷）", "us-west-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        EA_EU_WEST_1("英国（伦敦）", "eu-west-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        EA_EU_CENTRAL_1("德国（法兰克福）", "eu-central-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),

        MI_AP_SOUTH_1("印度（孟买）", "ap-south-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),
        MI_ME_EAST_1("阿联酋（迪拜）", "me-east-1", "dysmsapi.ap-southeast-1.aliyuncs.com", ""),

        IC_CN_HANGZHOU_FINANCE("华东1 金融云", "cn-hangzhou-finance", "dysmsapi.aliyuncs.com", ""),
        IC_CN_SHANGHAI_FINANCE_1("华东2 金融云", "cn-shanghai-finance-1", "dysmsapi.aliyuncs.com", ""),
        IC_CN_SHENZHEN_FINANCE_1("华南1 金融云", "cn-shenzhen-finance-1", "dysmsapi.aliyuncs.com", ""),
        IC_CN_BEIJING_FINANCE_1("华北2 金融云", "cn-beijing-finance-1", "dysmsapi.aliyuncs.com", "");

        private final String regionName;

        private final String regionId;

        private final String host;

        private final String vpc;

        Endpoint(String regionName, String regionId, String host, String vpc) {
            this.regionName = regionName;
            this.regionId = regionId;
            this.host = host;
            this.vpc = vpc;
        }


        public String getRegionName() {
            return regionName;
        }

        public String getRegionId() {
            return regionId;
        }

        public String getHost() {
            return host;
        }

        public String getVpc() {
            return vpc;
        }
    }
}
