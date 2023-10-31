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

package com.ms.tools.api.aliyun.sms.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信配置
 *
 * @author ms2297248353
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ms.api.aliyun.sms")
public class MsAliyunSmsProperties {

    /**
     * 访问密钥
     */
    private String accessKeyId;

}
