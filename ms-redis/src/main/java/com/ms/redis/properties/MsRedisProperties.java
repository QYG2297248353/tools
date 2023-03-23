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

package com.ms.redis.properties;

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

    /**
     * 开启 AutoType
     */
    private String[] autoType = new String[]{"com.cc"};

    /**
     * 客户端名称 clientName
     */
    private String clientName = "ms-redis";


    /**
     * 获取AutoType
     *
     * @return AutoType
     */
    public String[] getAutoType() {
        if (autoType == null || autoType.length == 0) {
            return new String[]{"com.ms"};
        } else {
            // 如果不存在指定的包名，则添加默认的包名
            for (String s : autoType) {
                if (s.equals("com.ms")) {
                    return autoType;
                }
            }
            String[] autoType = new String[this.autoType.length + 1];
            System.arraycopy(this.autoType, 0, autoType, 0, this.autoType.length);
            autoType[autoType.length - 1] = "com.ms";
            return autoType;
        }
    }
}
