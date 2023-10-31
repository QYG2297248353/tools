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
import com.ms.tools.push.dingtalk.factory.AtImpl;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ms2297248353
 */
@Getter
@Setter
public class AtDo {
    private String[] atMobiles;
    private String[] atUserIds;
    @JSONField(name = "isAtAll")
    private boolean hasAtAll;

    public AtDo() {
        hasAtAll = false;
    }

    public <T extends AtImpl> AtDo(T at) {
        atMobiles = at.getAtMobiles();
        atUserIds = at.getAtUserIds();
        hasAtAll = at.hasAtAll();
    }

    public AtDo atMobiles(String... atMobiles) {
        this.atMobiles = atMobiles;
        return this;
    }

    public AtDo atUserIds(String... atUserIds) {
        this.atUserIds = atUserIds;
        return this;
    }

    public AtDo atAll() {
        hasAtAll = true;
        return this;
    }
}
