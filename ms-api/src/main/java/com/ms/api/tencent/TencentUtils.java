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

package com.ms.api.tencent;

import com.ms.api.tencent.sms.SendSmsUtils;
import com.ms.api.tencent.sms.SmsSendStatusUtils;
import com.ms.api.tencent.sms.SmsSignatureUtils;
import com.ms.api.tencent.sms.SmsTemplateUtils;
import com.ms.api.tencent.sms.response.SendSmsResponse;
import com.ms.api.tencent.sms.response.SignatureValidationSmsResponse;
import com.ms.api.tencent.sms.response.SmsSendStatusResponse;
import com.ms.api.tencent.sms.response.TemplateValidationSmsResponse;
import com.ms.api.tencent.sms.vo.*;

public class TencentUtils {

    /**
     * 发送短信
     *
     * @param secretId  密钥Id
     * @param secretKey 密钥key
     * @param send      推送数据
     * @return 响应
     */
    public static SendSmsResponse sendSms(String secretId, String secretKey, SendSmsTencentVo.Send send) {
        return SendSmsUtils.sendSms(secretId, secretKey, send);
    }

    /**
     * 短信发送状态查询-手机号查询
     *
     * @param secretId      密钥Id
     * @param secretKey     密钥key
     * @param smsSendStatus 查询数据
     * @return 响应
     */
    public static SmsSendStatusResponse pullSmsSendStatusByPhoneNumber(String secretId, String secretKey, PullSmsSendStatusByPhoneNumberTencentVo.SmsSendStatus smsSendStatus) {
        return SmsSendStatusUtils.pullSmsSendStatusByPhoneNumber(secretId, secretKey, smsSendStatus);
    }

    /**
     * 短信发送状态查询
     *
     * @param secretId      密钥Id
     * @param secretKey     密钥key
     * @param smsSendStatus 查询数据
     * @return 响应
     */
    public static SmsSendStatusResponse pullSmsSendStatus(String secretId, String secretKey, PullSmsSendStatusTencentVo.SmsSendStatus smsSendStatus) {
        return SmsSendStatusUtils.pullSmsSendStatus(secretId, secretKey, smsSendStatus);
    }

    /**
     * 短信签名-状态查询
     *
     * @param secretId   密钥Id
     * @param secretKey  密钥key
     * @param validation 查询数据
     * @return 响应
     */
    public static SignatureValidationSmsResponse signatureValidationSms(String secretId, String secretKey, SignatureValidationTencentVo.Validation validation) {
        return SmsSignatureUtils.signatureValidationSms(secretId, secretKey, validation);
    }

    /**
     * 短信模板-状态查询
     *
     * @param secretId   密钥Id
     * @param secretKey  密钥key
     * @param validation 查询数据
     * @return 响应
     */
    public static TemplateValidationSmsResponse templateValidationSms(String secretId, String secretKey, TemplateValidationTencentVo.Validation validation) {
        return SmsTemplateUtils.templateValidationSms(secretId, secretKey, validation);
    }
}