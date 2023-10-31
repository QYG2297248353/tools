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

package com.ms.tools.resources.yaml.factory;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.resources.file.FileFindUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * 读取配置文件
 */
public class YamlReaderFactory {

    /**
     * 配置文件数据
     */
    private static Map<String, Object> yamlMap;


    public YamlReaderFactory(String yamlPath) throws MsToolsException {
        readYaml(yamlPath);
    }


    /**
     * 读取配置文件
     *
     * @param yamlPath 配置文件路径
     */
    private void readYaml(String yamlPath) throws MsToolsException {
        // 读取依赖项目配置文件
        File file = FileFindUtils.findFile(yamlPath);
        try {
            Yaml yaml = new Yaml();
            yamlMap = yaml.loadAs(new InputStreamReader(new FileInputStream(file)), Map.class);
        } catch (FileNotFoundException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 获取配置文件数据
     *
     * @return 配置文件数据
     */
    public Map<String, Object> getYamlMap() {
        return yamlMap;
    }

    /**
     * 获取配置文件数据
     *
     * @param key 配置文件数据key
     * @return 配置文件数据
     */
    public Object getYamlMap(String key) {
        return yamlMap.get(key);
    }

    /**
     * 获取指定对象
     *
     * @param key 配置文件数据key
     * @return 对象
     */
    public String getYamlAsString(String key) {
        return JSON.toJSONString(yamlMap.get(key));
    }

    /**
     * 获取指定对象
     *
     * @param key   配置文件数据key
     * @param clazz 对象
     * @param <T>   对象
     * @return 对象
     */
    public <T> T getYamlAsObject(String key, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(yamlMap.get(key)), clazz);
    }

    /**
     * 获取指定对象
     *
     * @param key   配置文件数据key
     * @param clazz 对象
     * @param <T>   对象
     * @return 对象
     */
    public <T> List<T> getYamlAsList(String key, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(yamlMap.get(key)), new TypeReference<List<T>>() {
        });
    }

    /**
     * 获取指定对象
     *
     * @param key   配置文件数据key
     * @param clazz 对象
     * @param <T>   对象
     * @return 对象
     */
    public <T> Map<String, T> getYamlAsMap(String key, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(yamlMap.get(key)), new TypeReference<Map<String, T>>() {
        });
    }

    /**
     * 获取指定对象
     *
     * @param key           配置文件数据key
     * @param typeReference 类型
     * @param <T>           类型
     * @return 对象
     */
    public <T> T getYamlAsObject(String key, TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(yamlMap.get(key)), typeReference);
    }

    /**
     * 获取指定对象
     *
     * @param key           配置文件数据key
     * @param typeReference 类型
     * @param <T>           类型
     * @return 对象
     */
    public <T> List<T> getYamlAsList(String key, TypeReference<List<T>> typeReference) {
        return JSON.parseObject(JSON.toJSONString(yamlMap.get(key)), typeReference);
    }

    /**
     * 获取指定对象
     *
     * @param key           配置文件数据key
     * @param typeReference 类型
     * @param <T>           类型
     * @return 对象
     */
    public <T> Map<String, T> getYamlAsMap(String key, TypeReference<Map<String, T>> typeReference) {
        return JSON.parseObject(JSON.toJSONString(yamlMap.get(key)), typeReference);
    }


    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public Object getYamlMap(String key, Object defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public String getYamlMap(String key, String defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return value.toString();
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public Integer getYamlMap(String key, Integer defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value.toString());
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public Long getYamlMap(String key, Long defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return Long.parseLong(value.toString());
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public Boolean getYamlMap(String key, Boolean defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.toString());
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public Double getYamlMap(String key, Double defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return Double.parseDouble(value.toString());
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @return 配置文件数据
     */
    public Float getYamlMap(String key, Float defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return Float.parseFloat(value.toString());
    }

    /**
     * 是否存在key
     *
     * @param key 数据Key
     * @return 是否存在
     */
    public Boolean exists(String key) {
        return yamlMap.containsKey(key);
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @param clazz        类型
     * @param <T>          类型
     * @return 配置文件数据
     */
    public <T> T getYamlMap(String key, Class<T> clazz, T defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return JSON.parseObject(JSON.toJSONString(value), clazz);
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @param clazz        类型
     * @param <T>          类型
     * @return 配置文件数据
     */
    public <T> List<T> getYamlMapList(String key, Class<T> clazz, List<T> defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return JSON.parseArray(JSON.toJSONString(value), clazz);
    }

    /**
     * 获取配置文件数据
     *
     * @param key          配置文件数据key
     * @param defaultValue 默认值
     * @param <T>          类型
     * @return 配置文件数据
     */
    public <T> Map<String, T> getYamlMapMap(String key, Map<String, T> defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return JSON.parseObject(JSON.toJSONString(value), new TypeReference<Map<String, T>>() {
        });
    }

    /**
     * 获取配置文件数据
     *
     * @param key           配置文件数据key
     * @param defaultValue  默认值
     * @param typeReference 类型
     * @param <T>           类型
     * @return 配置文件数据
     */
    public <T> T getYamlMap(String key, TypeReference<T> typeReference, T defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return JSON.parseObject(JSON.toJSONString(value), typeReference);
    }

    /**
     * 获取配置文件数据
     *
     * @param key           配置文件数据key
     * @param defaultValue  默认值
     * @param typeReference 类型
     * @param <T>           类型
     * @return 配置文件数据
     */
    public <T> List<T> getYamlMapList(String key, TypeReference<List<T>> typeReference, List<T> defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return JSON.parseObject(JSON.toJSONString(value), typeReference);
    }

    /**
     * 获取配置文件数据
     *
     * @param key           配置文件数据key
     * @param defaultValue  默认值
     * @param typeReference 类型
     * @param <T>           类型
     * @return 配置文件数据
     */
    public <T> Map<String, T> getYamlMapMap(String key, TypeReference<Map<String, T>> typeReference, Map<String, T> defaultValue) {
        Object value = yamlMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return JSON.parseObject(JSON.toJSONString(value), typeReference);
    }
}
