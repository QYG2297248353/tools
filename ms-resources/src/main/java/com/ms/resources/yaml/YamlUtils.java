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

package com.ms.resources.yaml;

import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.yaml.factory.YamlReaderFactory;

/**
 * Yaml 文件
 *
 * @author ms2297248353
 */
public class YamlUtils {

    /**
     * 读取配置文件
     *
     * @param path 文件地址
     * @return YamlReaderUtils
     * @throws MsToolsException 异常
     */
    public static YamlReaderFactory readYaml(String path) throws MsToolsException {
        return new YamlReaderFactory(path);
    }

}
