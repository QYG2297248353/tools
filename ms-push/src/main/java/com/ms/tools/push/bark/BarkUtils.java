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

package com.ms.tools.push.bark;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ms.tools.network.okhttp.OkClient;
import com.ms.tools.push.bark.factory.BarkDO;
import com.ms.tools.push.bark.factory.BarkImpl;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Bark 推送工具
 *
 * @author ms2297248353
 */
public class BarkUtils {

    /**
     * Bark 推送工具(单条)
     *
     * @param push 推送类
     * @param <T>  接口实现
     * @return 是否成功推送
     */
    public static <T extends BarkImpl> Boolean pushBark(T push) {
        ResponseBody body;
        try (Response response = OkClient.build().uri(getPushUri(push)).post().body(push).execute()) {
            assert response.body() != null;
            String sync = response.body().string();
            JSONObject json = JSON.parseObject(sync);
            return "success".equals(json.get("message"));
        } catch (Exception e) {
            return false;
        }
    }

    private static <T extends BarkImpl> String getPushUri(T push) {
        return push.getPushServer() + "/" + push.getKey();
    }

    /**
     * Bark 推送工具
     *
     * @param push 推送类
     * @return 是否成功推送
     */
    public static Boolean pushBarkDo(BarkDO push) {
        return BarkUtils.pushBark(push);
    }

}
