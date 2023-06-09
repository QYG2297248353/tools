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

package com.ms.api.aliyun.sms.factory;

/**
 * @author ms2297248353
 */
public enum ApiSmsEnum {

    /**
     * 短信发送
     */
    SEND_SMS("SendSms", "发送短信", "发送前请申请短信签名和短信模板，并确保签名和模板已审核通过"),
    SEND_BATCH_SMS("SendBatchSms", "批量发送短信", "批量发送短信，支持多个号码，多个签名，注意模板只能用一个"),
    /**
     * 发送查询
     */
    QUERY_SEND_STATISTICS("QuerySendStatistics", "查询短信发送统计信息", "查询短信发送量详情"),
    QUERY_SEND_DETAILS("QuerySendDetails", "查询短信发送详情", "查询短信发送记录和发送状态等信息"),
    /**
     * 签名管理
     */
    ADD_SMS_SIGN("AddSmsSign", "申请短信签名", "申请短信签名"),
    DELETE_SMS_SIGN("DeleteSmsSign", "删除短信签名", "删除签名，删除后您将不能使用它继续发短信"),
    MODIFY_SMS_SIGN("ModifySmsSign", "修改短信签名", "修改短信签名，并重新提交审核，审核中的签名不支持修改"),
    QUERY_SMS_SIGN_LIST("QuerySmsSignList", "查询短信签名列表", "查询短信签名列表详情，分页获取短信签名列表"),
    QUERY_SMS_SIGN("QuerySmsSign", "查询短信签名申请状态", "查询短信签名申请状态"),
    /**
     * 模板管理
     */
    ADD_SMS_TEMPLATE("AddSmsTemplate", "申请短信模板", "创建短信模板"),
    DELETE_SMS_TEMPLATE("DeleteSmsTemplate", "删除短信模板", "删除短信模板"),
    MODIFY_SMS_TEMPLATE("ModifySmsTemplate", "修改审核未通过的短信模板", "修改未审核通过的短信模板信息，并重新提交审核"),
    QUERY_SMS_TEMPLATE_LIST("QuerySmsTemplateList", "查询短信模板列表", "查询短信模板列表详情"),
    QUERY_SMS_TEMPLATE("QuerySmsTemplate", "查询短信模板的审核状态", "查询短信模板审核状态"),
    /**
     * 短链管理
     */
    ADD_SHORT_URL("AddShortUrl", "创建短链", "创建短链"),
    DELETE_SHORT_URL("DeleteShortUrl", "删除短链", "删除短链，删除后短链将无法使用，无法还原为原链"),
    QUERY_SHORT_URL("QueryShortUrl", "短链状态查询", "查询短链状态，可判断短链是否可用"),
    /**
     * 标签管理
     */
    LIST_TAG_RESOURCES("ListTagResources", "查询模板标签", "查询模板标签"),
    TAG_RESOURCES("TagResources", "添加模板标签", "给模板添加标签"),
    UNTAG_RESOURCES("UntagResources", "删除模板标签", "删除模板标签"),
    /**
     * 卡片短信
     */
    GET_OSS_INFO_FOR_CARD_TEMPLATE("GetOSSInfoForCardTemplate", "获取OSS上传信息", "获取卡片短信所属OSS资源配置信息，此配置信息将用于后续OSS文件上传操作"),
    GET_MEDIA_RESOURCEID("GetMediaResourceId", "获取媒体资源ID", "将用户上传到卡片短信OSS存储的图片、视频转换成（生成）资源数据统一管理，并返回资源ID，用户可以对返回的资源ID自行管理"),
    CREATE_CARD_SMS_TEMPLATE("CreateCardSmsTemplate", "创建卡片短信模板", "创建卡片短信模板"),
    QUERY_CARD_SMS_TEMPLATE("QueryCardSmsTemplate", "查询卡片短信模板状态", "查询模板审核状态"),
    CHECK_MOBILES_CARD_SUPPORT("CheckMobilesCardSupport", "卡片短信能力校验", "校验手机号码是否能够接收卡片短信"),
    QUERY_MOBILES_CARD_SUPPORT("QueryMobilesCardSupport", "查询手机是否支持卡片短信", "卡片短信能力查询"),
    GET_CARD_SMS_LINK("GetCardSmsLink", "获取卡片短信短链", "获取卡片短信短链"),
    QUERY_CARD_SMS_TEMPLATE_REPORT("QueryCardSmsTemplateReport", "查询卡片短信发送详情", "查询卡片短信发送详情"),
    SEND_CARD_SMS("SendCardSms", "发送卡片短信", "发送卡片短信"),
    SEND_BATCH_CARD_SMS("SendBatchCardSms", "批量发送卡片短信", "批量发送卡片短信"),
    /**
     * 国际短信
     */
    SMS_CONVERSION_INTL("SmsConversionIntl", "国内发国际短信转化反馈", "将每一条消息ID(MessageId) 对应短信的接收情况反馈给阿里云国际短信平台"),
    CONVERSION_DATA_INTL("ConversionDataIntl", "国内发国际转化率数据接入API", "将短信转化率统计数据反馈给阿里云短信平台");

    private String apiName;

    private String apiDesc;

    private String apiDescDetail;

    ApiSmsEnum(String apiName, String apiDesc, String apiDescDetail) {
        this.apiName = apiName;
        this.apiDesc = apiDesc;
        this.apiDescDetail = apiDescDetail;
    }

    public String getApiName() {
        return apiName;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public String getApiDescDetail() {
        return apiDescDetail;
    }

    /**
     * 接口版本
     *
     * @return 接口版本
     */
    public String getVersion() {
        return "2017-05-25";
    }


}