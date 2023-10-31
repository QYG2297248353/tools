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

package com.ms.tools.push.dingtalk.template;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.push.dingtalk.enums.BtnOrientationEnum;
import com.ms.tools.push.dingtalk.to.ActionCardImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Text 推送
 * 如需At成员请重写at方法
 *
 * @author ms2297248353
 */
@ToString
@Getter
@Setter
public class DingActionCardDO extends ActionCardImpl {
    /**
     * 推送内容
     */
    private ActionCard actionCard;

    /**
     * 推送类型
     */
    @JSONField(name = "msgtype")
    private String msgType;

    public DingActionCardDO() {
        actionCard = new ActionCard();
        msgType = super.getMsgType();
    }

    public DingActionCardDO(ActionCardImpl webhook) {
        actionCard = new ActionCard(webhook);

        msgType = webhook.getMsgType();
        setToken(webhook.getToken());
        setSignature(webhook.getSignature());
        setUri(webhook.getUri());
    }

    @Override
    @JSONField(serialize = false)
    public String getTitle() {
        return actionCard.getTitle();
    }

    public void setTitle(String title) {
        actionCard.setTitle(title);
    }

    @Override
    @JSONField(serialize = false)
    public String getText() {
        return actionCard.getText();
    }

    public void setText(String text) {
        actionCard.setText(text);
    }

    @Override
    @JSONField(serialize = false)
    public String getSingleTitle() {
        return actionCard.getSingleTitle();
    }

    public void setSingleTitle(String singleTitle) {
        actionCard.setSingleTitle(singleTitle);
    }

    @Override
    @JSONField(serialize = false)
    public String getSingleUrl() {
        return actionCard.getSingleUrl();
    }

    public void setSingleUrl(String singleUrl) {
        actionCard.setSingleUrl(singleUrl);
    }

    @Override
    public void setBtnOrientationEnum(BtnOrientationEnum btnOrientationEnum) {
        super.setBtnOrientationEnum(btnOrientationEnum);
        actionCard.setBtnOrientation(btnOrientationEnum);
    }

    public String getMsgType() {
        return msgType;
    }

    private void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    private class ActionCard {
        private String title;
        private String text;
        private String btnOrientation;
        private String singleTitle;
        @JSONField(name = "singleURL")
        private String singleUrl;

        public ActionCard() {
        }

        public ActionCard(ActionCardImpl webhook) {
            title = webhook.getTitle();
            text = webhook.getText();
            btnOrientation = webhook.getBtnOrientation().getDirection();
            singleTitle = webhook.getSingleTitle();
            singleUrl = webhook.getSingleUrl();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getBtnOrientation() {
            return btnOrientation;
        }

        public void setBtnOrientation(BtnOrientationEnum btnOrientationEnum) {
            btnOrientation = btnOrientationEnum.getDirection();
        }

        public void setBtnOrientation(String btnOrientation) {
            this.btnOrientation = btnOrientation;
        }

        public String getSingleTitle() {
            return singleTitle;
        }

        public void setSingleTitle(String singleTitle) {
            this.singleTitle = singleTitle;
        }

        public String getSingleUrl() {
            return singleUrl;
        }

        public void setSingleUrl(String singleUrl) {
            this.singleUrl = singleUrl;
        }
    }
}
