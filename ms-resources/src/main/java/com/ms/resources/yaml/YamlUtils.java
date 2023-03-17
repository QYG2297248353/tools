package com.ms.resources.yaml;

import com.ms.core.exception.base.MsToolsException;

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
     * @throws MsToolsException
     */
    public static YamlReaderUtils readYaml(String path) throws MsToolsException {
        return new YamlReaderUtils(path);
    }

}
