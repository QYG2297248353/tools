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

package com.ms.tools.push.dingtalk.enums;


import com.ms.tools.core.base.basic.Strings;

/**
 * @author ms2297248353
 */

public enum BtnOrientationEnum {
    EMPTY(Strings.EMPTY, "自动"),
    VERTICAL_DIRECTION_BUTTONS("0", "按钮竖直排列"),
    TRANSVERSE_DIRECTION_BUTTONS("1", "按钮横向排列");

    private final String direction;
    private final String description;

    BtnOrientationEnum(String direction, String description) {
        this.direction = direction;
        this.description = description;
    }

    public String getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }
}
