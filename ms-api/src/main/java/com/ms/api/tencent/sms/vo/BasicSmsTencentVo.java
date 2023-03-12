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

package com.ms.api.tencent.sms.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ms.api.tencent.factory.TencentCloudApiConfig;

/**
 * @author qyg2297248353
 */
public class BasicSmsTencentVo {
    /**
     * 默认参数
     */
    @JSONField(name = "Action")
    private String action;
    @JSONField(name = "Version")
    private String version;
    @JSONField(name = "Region")
    private String region;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    protected void setAction(TencentCloudApiConfig.Action action) {
        this.action = action.getAction();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(TencentCloudApiConfig.Region region) {
        this.region = region.getRegion();
    }
}
