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

package com.ms.push.webhook.dingtalk.template;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.push.webhook.dingtalk.enums.BtnOrientationEnum;
import com.ms.push.webhook.dingtalk.to.ActionCardBtnImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Text 推送
 * 如需At成员请重写at方法
 *
 * @author ms2297248353
 */
@ToString
@Getter
@Setter
public class DingActionCardBtnDO extends ActionCardBtnImpl {
    /**
     * 推送内容
     */
    private ActionCard actionCard;

    /**
     * 推送类型
     */
    @JSONField(name = "msgtype")
    private String msgType;

    public DingActionCardBtnDO() {
        actionCard = new ActionCard();
        msgType = super.getMsgType();
    }

    public DingActionCardBtnDO(ActionCardBtnImpl webhook) {
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
    public void setBtnOrientationEnum(BtnOrientationEnum btnOrientationEnum) {
        super.setBtnOrientationEnum(btnOrientationEnum);
        actionCard.setBtnOrientation(btnOrientationEnum);
    }

    @Override
    public void addBtn(Btn btn) {
        super.addBtn(btn);
        actionCard.addBtn(btn);
    }

    @Override
    public void addBtnList(List<Btn> btn) {
        super.addBtnList(btn);
        actionCard.addBtnList(btn);
    }

    @Override
    public void removeBtn(Btn btn) {
        super.removeBtn(btn);
        actionCard.removeBtn(btn);
    }

    @Override
    public void removeAllBtn() {
        super.removeAllBtn();
        actionCard.removeAllBtn();
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
        @JSONField(name = "btns")
        private List<Btn> btnList;

        public ActionCard() {
            btnList = new ArrayList<>();
            btnOrientation = BtnOrientationEnum.EMPTY.getDirection();
        }

        public ActionCard(ActionCardBtnImpl webhook) {
            title = webhook.getTitle();
            text = webhook.getText();
            btnOrientation = webhook.getBtnOrientation().getDirection();
            btnList = webhook.getBtnList();
        }

        public void addBtn(Btn btn) {
            btnList.add(btn);
        }

        public void addBtnList(List<Btn> btn) {
            btnList.addAll(btn);
        }

        public void removeBtn(Btn btn) {
            btnList.remove(btn);
        }

        public void removeAllBtn() {
            btnList.clear();
        }

        public List<Btn> getBtnList() {
            return btnList;
        }

        public void setBtnList(List<Btn> btnList) {
            this.btnList = btnList;
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
    }
}
