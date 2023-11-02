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

package com.ms.tools.push.dingtalk.to;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.tools.push.dingtalk.enums.BtnOrientationEnum;
import com.ms.tools.push.dingtalk.enums.MsgTypeEnum;
import com.ms.tools.push.dingtalk.factory.AbstractConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ms2297248353
 */
public abstract class ActionCardBtnImpl extends AbstractConfig {
    @JSONField(serialize = false)
    private final List<Btn> btnList;
    @JSONField(serialize = false)
    private BtnOrientationEnum btnOrientationEnum;

    protected ActionCardBtnImpl() {
        btnOrientationEnum = BtnOrientationEnum.EMPTY;
        btnList = new ArrayList<>();
    }

    /**
     * 消息标题
     *
     * @return 推送标题
     */
    public abstract String getTitle();

    /**
     * markdown格式的消息
     *
     * @return markdown格式的消息
     */
    public abstract String getText();

    /**
     * 排列方向
     *
     * @return 排列方向
     */
    @JSONField(serialize = false)
    public BtnOrientationEnum getBtnOrientation() {
        return btnOrientationEnum;
    }

    public void setBtnOrientationEnum(BtnOrientationEnum btnOrientationEnum) {
        this.btnOrientationEnum = btnOrientationEnum;
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

    @Override
    @JSONField(serialize = false)
    public MsgTypeEnum getMsgTypeEnum() {
        return MsgTypeEnum.ACTION_CARD_BTN;
    }

    @Getter
    @Setter
    public static class Btn {
        private String title;

        @JSONField(name = "actionURL")
        private String actionUrl;

        private Btn() {

        }

        public Btn(String title, String actionUrl) {
            this.title = title;
            this.actionUrl = actionUrl;
        }
    }
}
