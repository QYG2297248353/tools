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

package com.ms.tools.redis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ms2297248353
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ms.network.redis")
public class MsRedisProperties {

    private final static String DEFAULT_PREFIX = "com.ms.";

    /**
     * 白名单
     * AutoType 自动识别转换
     * <p>
     * 添加自己项目需要转换的包名即可
     */
    private String[] autoType = new String[]{};

    /**
     * 客户端名称 clientName
     */
    private String clientName = "ms-redis";

    /**
     * 全局过期时间
     * <p>
     * 单位 s秒
     * <p>
     * 默认7天
     */
    private Long globalExpire = (long) 60 * 60 * 24 * 7;


    /**
     * 获取AutoType
     *
     * @return AutoType
     */
    public String[] getAutoType() {
        return autoType;
    }
}
