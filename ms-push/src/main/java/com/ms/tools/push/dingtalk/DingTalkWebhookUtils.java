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

package com.ms.tools.push.dingtalk;


import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.core.exception.web.MsSendRequestException;
import com.ms.tools.push.dingtalk.factory.AbstractConfig;
import com.ms.tools.push.dingtalk.factory.DingTalkWebhookBuild;
import com.ms.tools.push.dingtalk.response.DingTalkWebhookResponse;
import com.ms.tools.push.dingtalk.to.*;

/**
 * @author ms2297248353
 */
public class DingTalkWebhookUtils {
    /**
     * 钉钉机器人-消息推送
     *
     * @param webhook 信息
     * @param <T>     实现类
     * @return 响应 code=0 正常推送
     * @throws MsToolsException 异常
     */
    public static <T extends AbstractConfig> DingTalkWebhookResponse sendWebhook(T webhook) throws MsToolsException {
        switch (webhook.getMsgTypeEnum()) {
            case TEXT:
                if (TextImpl.class.isAssignableFrom(webhook.getClass())) {
                    return sendTextWebhook((TextImpl) webhook);
                }
            case LINK:
                if (LinkImpl.class.isAssignableFrom(webhook.getClass())) {
                    return sendLinkWebhook((LinkImpl) webhook);
                }
            case MARKDOWN:
                if (MarkdownImpl.class.isAssignableFrom(webhook.getClass())) {
                    return sendMarkdownWebhook((MarkdownImpl) webhook);
                }
            case ACTION_CARD:
                if (ActionCardImpl.class.isAssignableFrom(webhook.getClass())) {
                    return sendActionCardWebhook((ActionCardImpl) webhook);
                }
            case ACTION_CARD_BTN:
                if (ActionCardBtnImpl.class.isAssignableFrom(webhook.getClass())) {
                    return sendActionCardBtnWebhook((ActionCardBtnImpl) webhook);
                }
            case FEED_CARD:
                if (FeedCardImpl.class.isAssignableFrom(webhook.getClass())) {
                    return sendFeedCardWebhook((FeedCardImpl) webhook);
                }
            default:
                throw new MsToolsException("未实现任何接口");
        }
    }

    /**
     * 钉钉机器人-链接消息推送
     *
     * @param <T>     实现类
     * @param webhook 接口实现类
     * @return 响应 code=0 正常推送
     */
    public static <T extends FeedCardImpl> DingTalkWebhookResponse sendFeedCardWebhook(T webhook) {
        try {
            return DingTalkWebhookBuild.sendWebhook(webhook);
        } catch (MsSendRequestException e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }

    /**
     * 钉钉机器人-链接消息推送
     *
     * @param webhook 接口实现类
     * @param <T>     实现类
     * @return 响应 code=0 正常推送
     */
    public static <T extends ActionCardImpl> DingTalkWebhookResponse sendActionCardWebhook(T webhook) {
        try {
            return DingTalkWebhookBuild.sendWebhook(webhook);
        } catch (MsSendRequestException e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }

    /**
     * 钉钉机器人-链接消息推送
     *
     * @param webhook 接口实现类
     * @param <T>     实现类
     * @return 响应 code=0 正常推送
     */
    public static <T extends ActionCardBtnImpl> DingTalkWebhookResponse sendActionCardBtnWebhook(T webhook) {
        try {
            return DingTalkWebhookBuild.sendWebhook(webhook);
        } catch (MsSendRequestException e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }

    /**
     * 钉钉机器人-链接消息推送
     *
     * @param webhook 接口实现类
     * @param <T>     实现类
     * @return 响应 code=0 正常推送
     */
    public static <T extends MarkdownImpl> DingTalkWebhookResponse sendMarkdownWebhook(T webhook) {
        try {
            return DingTalkWebhookBuild.sendWebhook(webhook);
        } catch (MsSendRequestException e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }


    /**
     * 钉钉机器人-链接消息推送
     *
     * @param webhook 接口实现类
     * @param <T>     实现类
     * @return 响应 code=0 正常推送
     */
    public static <T extends LinkImpl> DingTalkWebhookResponse sendLinkWebhook(T webhook) {
        try {
            return DingTalkWebhookBuild.sendWebhook(webhook);
        } catch (MsSendRequestException e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }

    /**
     * 钉钉机器人-纯文本消息推送
     *
     * @param webhook 接口实现类
     * @param <T>     实现类
     * @return 响应 code=0 正常推送
     */
    public static <T extends TextImpl> DingTalkWebhookResponse sendTextWebhook(T webhook) {
        try {
            return DingTalkWebhookBuild.sendWebhook(webhook);
        } catch (MsSendRequestException e) {
            return DingTalkWebhookResponse.errorRequest();
        }
    }

}
