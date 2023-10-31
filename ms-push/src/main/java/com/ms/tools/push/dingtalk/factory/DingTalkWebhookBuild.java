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

package com.ms.tools.push.dingtalk.factory;

import com.alibaba.fastjson2.JSON;
import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.core.exception.web.MsSendRequestException;
import com.ms.tools.network.okhttp.OkClient;
import com.ms.tools.push.dingtalk.response.DingTalkWebhookResponse;
import com.ms.tools.push.dingtalk.to.*;
import okhttp3.Response;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author ms2297248353
 */
public class DingTalkWebhookBuild {
    private static String secretSignature(AbstractConfig config) throws MsToolsException {
        long timeMillis = System.currentTimeMillis();
        String signature = signature(config.getSignature());
        String url = buildPushUrl(config);
        url += "&timestamp=" + timeMillis;
        url += "&sign=" + signature;
        return url;
    }

    private static String buildPushUrl(AbstractConfig config) {
        return config.getUri() + "?access_token=" + config.getToken();
    }

    private static String signature(String secret) throws MsToolsException {
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.getEncoder().encode(signData)), Strings.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            throw new MsToolsException(e);
        }
    }

    public static <T extends AbstractConfig> DingTalkWebhookResponse sendWebhook(T obj) throws MsSendRequestException {
        try {
            String pushUrl = getPushUrl(obj);
            switch (obj.getMsgTypeEnum()) {
                case TEXT:
                    if (TextImpl.class.isAssignableFrom(obj.getClass())) {
                        return send(pushUrl, obj);
                    }
                case LINK:
                    if (LinkImpl.class.isAssignableFrom(obj.getClass())) {
                        return send(pushUrl, obj);
                    }
                case MARKDOWN:
                    if (MarkdownImpl.class.isAssignableFrom(obj.getClass())) {
                        return send(pushUrl, obj);
                    }
                case ACTION_CARD:
                    if (ActionCardImpl.class.isAssignableFrom(obj.getClass())) {
                        return send(pushUrl, obj);
                    }
                case ACTION_CARD_BTN:
                    if (ActionCardBtnImpl.class.isAssignableFrom(obj.getClass())) {
                        return send(pushUrl, obj);
                    }
                case FEED_CARD:
                    if (FeedCardImpl.class.isAssignableFrom(obj.getClass())) {
                        return send(pushUrl, obj);
                    }
                default:
                    return DingTalkWebhookResponse.errorNull();
            }
        } catch (MsToolsException e) {
            return DingTalkWebhookResponse.errorSignature();
        }
    }

    private static <T extends AbstractConfig> DingTalkWebhookResponse send(String uri, T obj) {
        String json = JSON.toJSONString(obj);
        try (Response sync = OkClient.build().uri(uri).post().body(json).execute()) {
            try {
                assert sync.body() != null;
                String body = sync.body().string();
                return JSON.parseObject(body, DingTalkWebhookResponse.class).parse();
            } catch (IOException e) {
                throw new MsSendRequestException("响应解析失败");
            }
        } catch (Exception e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }

    private static String getPushUrl(AbstractConfig obj) throws MsToolsException {
        if (Strings.isBlank(obj.getSignature())) {
            return buildPushUrl(obj);
        } else {
            return secretSignature(obj);
        }
    }
}
