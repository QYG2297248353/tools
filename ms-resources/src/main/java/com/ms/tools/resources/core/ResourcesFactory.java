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

package com.ms.tools.resources.core;


import com.ms.tools.resources.io.IoUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author qyg2297248353
 */
public class ResourcesFactory {
    /**
     * 获取包内配置文件
     *
     * @param filePath 文件地址 最好已/开头否则容易找不到
     * @return 配置文件
     * @throws IOException 异常
     */
    public Properties getResourcesPropertiesFile(String filePath) throws IOException {
        InputStream is = getResourcesFile(filePath);
        Properties props = new Properties();
        props.load(is);
        return props;
    }

    /**
     * 获取包内文件
     *
     * @param resource 文件地址 最好已/开头否则容易找不到
     * @return 配置文件
     */
    public InputStream getResourcesFile(String resource) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(resource);
    }

    /**
     * 获取包内文件
     *
     * @param filePath 文件地址 最好已/开头否则容易找不到
     * @return 配置文件
     * @throws IOException 异常
     */
    public String getResourcesFileStr(String filePath) throws IOException {
        InputStream is = getResourcesFile(filePath);
        return IoUtils.inputStreamToString(is);
    }
}
