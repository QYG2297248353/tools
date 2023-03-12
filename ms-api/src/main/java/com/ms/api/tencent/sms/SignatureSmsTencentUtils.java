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

package com.ms.api.tencent.sms;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ms.api.tencent.sms.response.SignatureValidationSmsResponse;
import com.ms.api.tencent.sms.vo.SignatureValidationTencentVo;
import com.ms.core.exception.base.MsToolsException;

public class SignatureSmsTencentUtils {

    /**
     * 短信签名效验
     * <a href="https://console.cloud.tencent.com/cam/capi">API密匙</a>
     *
     * @param secretId   密钥Id
     * @param secretKey  密钥key
     * @param validation 推送数据
     * @return 响应
     */
    public static SignatureValidationSmsResponse signatureValidationSms(String secretId, String secretKey, SignatureValidationTencentVo.Validation validation) {
        SignatureValidationTencentVo push = SignatureValidationTencentVo.build(validation);
        String json = JSON.toJSONString(validation);
        try {
            String res = TencentSmsUtils.push(secretId, secretKey, json, push.getBasic());
            String response = JSON.parseObject(res).getString("Response");
            SignatureValidationSmsResponse validationSmsResponse = JSONObject.parseObject(response, SignatureValidationSmsResponse.class);
            validationSmsResponse.finishing();
            return validationSmsResponse;
        } catch (MsToolsException e) {
            return SignatureValidationSmsResponse.errorResponse();
        }
    }
}
