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

package core.base.enums.request;

/**
 * @author ms2297248353
 */

public enum RequestBodyTypeEnum {
    NONE(MediaTypeEnum.NONE, "不使用"),
    FORM_DATA(MediaTypeEnum.MULTIPART_FORM_DATA, "表单数据允许文件"),
    X_WWW_FORM_URLENCODED(MediaTypeEnum.APPLICATION_X_WWW_FORM_URLENCODED, "表单数据"),
    JSON(MediaTypeEnum.APPLICATION_JSON, "JSON");

    private MediaTypeEnum type;
    private String description;

    RequestBodyTypeEnum(MediaTypeEnum type, String description) {
        this.type = type;
        this.description = description;
    }

    public MediaTypeEnum getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
