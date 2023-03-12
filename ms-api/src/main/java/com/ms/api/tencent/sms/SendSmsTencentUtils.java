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
import com.ms.api.tencent.sms.response.SendSmsResponse;
import com.ms.api.tencent.sms.vo.SendSmsTencentVo;
import com.ms.core.exception.base.MsToolsException;

/**
 * 短信推送工具
 *
 * @author qyg2297248353
 */
public class SendSmsTencentUtils {

    /**
     * 发送短信
     * <a href="https://console.cloud.tencent.com/cam/capi">API密匙</a>
     *
     * @param secretId  密钥Id
     * @param secretKey 密钥key
     * @param send      推送数据
     * @return 响应
     */
    public static SendSmsResponse sendSms(String secretId, String secretKey, SendSmsTencentVo.Send send) {
        SendSmsTencentVo push = SendSmsTencentVo.build(send);
        String json = JSON.toJSONString(send);
        try {
            String res = TencentSmsUtils.push(secretId, secretKey, json, push.getBasic());
            String response = JSON.parseObject(res).getString("Response");
            SendSmsResponse sendSmsResponse = JSONObject.parseObject(response, SendSmsResponse.class);
            sendSmsResponse.finishing(send);
            return sendSmsResponse;
        } catch (MsToolsException e) {
            return SendSmsResponse.errorResponse();
        }
    }
}
