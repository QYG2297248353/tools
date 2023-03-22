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
