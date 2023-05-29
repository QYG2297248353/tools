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
import com.ms.api.tencent.factory.TencentSmsFactory;
import com.ms.api.tencent.sms.response.SmsSendStatusResponse;
import com.ms.api.tencent.sms.vo.PullSmsSendStatusByPhoneNumberTencentVo;
import com.ms.api.tencent.sms.vo.PullSmsSendStatusTencentVo;
import com.ms.core.exception.base.MsToolsException;

/**
 * @author ms2297248353
 * @date 2023年05月29日 10:56
 */
public class SmsSendStatusUtils {

    /**
     * 短信发送状态查询-手机号查询
     *
     * @param secretId      密钥Id
     * @param secretKey     密钥key
     * @param smsSendStatus 推送数据
     * @return 响应
     */
    public static SmsSendStatusResponse pullSmsSendStatusByPhoneNumber(String secretId, String secretKey, PullSmsSendStatusByPhoneNumberTencentVo.SmsSendStatus smsSendStatus) {
        PullSmsSendStatusByPhoneNumberTencentVo push = PullSmsSendStatusByPhoneNumberTencentVo.build(smsSendStatus);
        String json = JSON.toJSONString(smsSendStatus);
        try {
            String res = TencentSmsFactory.push(secretId, secretKey, json, push.getBasic());
            String response = JSON.parseObject(res).getString("Response");
            SmsSendStatusResponse smsSendStatusResponse = JSONObject.parseObject(response, SmsSendStatusResponse.class);
            smsSendStatusResponse.finishing();
            return smsSendStatusResponse;
        } catch (MsToolsException e) {
            return SmsSendStatusResponse.errorResponse();
        }
    }

    /**
     * 短信发送状态查询
     *
     * @param secretId      密钥Id
     * @param secretKey     密钥key
     * @param smsSendStatus 推送数据
     * @return 响应
     */
    public static SmsSendStatusResponse pullSmsSendStatus(String secretId, String secretKey, PullSmsSendStatusTencentVo.SmsSendStatus smsSendStatus) {
        PullSmsSendStatusTencentVo push = PullSmsSendStatusTencentVo.build(smsSendStatus);
        String json = JSON.toJSONString(smsSendStatus);
        try {
            String res = TencentSmsFactory.push(secretId, secretKey, json, push.getBasic());
            String response = JSON.parseObject(res).getString("Response");
            SmsSendStatusResponse smsSendStatusResponse = JSONObject.parseObject(response, SmsSendStatusResponse.class);
            smsSendStatusResponse.finishing();
            return smsSendStatusResponse;
        } catch (MsToolsException e) {
            return SmsSendStatusResponse.errorResponse();
        }
    }
}