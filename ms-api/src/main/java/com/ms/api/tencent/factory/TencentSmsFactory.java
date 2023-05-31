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

package com.ms.api.tencent.factory;

import com.ms.api.tencent.sms.vo.BasicSmsTencentVo;
import com.ms.core.exception.base.MsToolsException;
import com.ms.network.okhttp.OkClient;
import okhttp3.Response;

public class TencentSmsFactory {
    /**
     * 请求服务
     *
     * @param secretId  密钥Id
     * @param secretKey 密钥key
     * @param bodyJson  请求数据JSON
     * @param basic     请求类型
     * @return 响应
     * @throws MsToolsException 异常
     */
    public static String push(String secretId, String secretKey, String bodyJson, BasicSmsTencentVo basic) throws MsToolsException {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String authorization = ApiSignV3.signV3(secretId, secretKey, timestamp, bodyJson);

        try (Response sync = OkClient.build()
                .addHeader(TencentCloudApiConfig.SmsApi.Authorization, authorization)
                .addHeader(TencentCloudApiConfig.SmsApi.Timestamp, timestamp)
                .addHeader(TencentCloudApiConfig.SmsApi.Action, basic.getAction())
                .addHeader(TencentCloudApiConfig.SmsApi.Version, basic.getVersion())
                .addHeader(TencentCloudApiConfig.SmsApi.Region, basic.getRegion())
                .addHeader(TencentCloudApiConfig.SmsApi.Language, TencentCloudApiConfig.Language.ZH_CH.getLanguage())
                .uri(TencentCloudApiConfig.Host.MAIN_SMS.getWebsite())
                .post().body(bodyJson).execute()) {
            assert sync.body() != null;
            return sync.body().string();
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }
}