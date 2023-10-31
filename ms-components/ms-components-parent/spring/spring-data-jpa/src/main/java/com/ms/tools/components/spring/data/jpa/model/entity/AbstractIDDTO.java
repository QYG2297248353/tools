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

package com.ms.tools.components.spring.data.jpa.model.entity;

/**
 * 主键Id
 * 类型String 防止精度丢失
 *
 * @author qyg2297248353
 */
public abstract class AbstractIDDTO implements IBaseEntity {

    private String id;

    /**
     * 获取主键id
     * <p>
     * 因为如果是Long类型,前端js能处理的长度低于Java，防止精度丢失;
     * java的Long类型是18位,而 js的Long类型(虽然没有明确定义的Long类型)是16位,所以会造成丢失精度，
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }
}
