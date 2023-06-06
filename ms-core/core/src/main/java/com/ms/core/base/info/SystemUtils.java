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

package com.ms.core.base.info;

import com.ms.core.base.basic.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

/**
 * 系统工具类
 * <p>
 * 用于获取系统信息
 *
 * @author ms2297248353
 */
public class SystemUtils {

    /**
     * 获取系统名称
     *
     * @return 系统名称
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取系统版本
     *
     * @return 系统版本
     */
    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    /**
     * 获取系统架构
     *
     * @return 系统架构
     */
    public static String getOsArch() {
        return System.getProperty("os.arch");
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getUserName() {
        return System.getProperty("user.name");
    }

    /**
     * 获取系统用户主目录
     *
     * @return 系统用户主目录
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * 获取系统用户当前工作目录
     *
     * @return 系统用户当前工作目录
     */
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取系统文件分隔符
     *
     * @return 系统文件分隔符
     */
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * 获取系统路径分隔符
     *
     * @return 系统路径分隔符
     */
    public static String getPathSeparator() {
        return System.getProperty("path.separator");
    }

    /**
     * 获取系统行分隔符
     *
     * @return 系统行分隔符
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * 获取系统临时目录
     *
     * @return 系统临时目录
     */
    public static String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取系统默认编码
     *
     * @return 系统默认编码
     */
    public static String getFileEncoding() {
        return System.getProperty("file.encoding");
    }

    /**
     * 获取系统默认语言
     *
     * @return 系统默认语言
     */
    public static String getLanguage() {
        return System.getProperty("user.language");
    }

    /**
     * 获取系统默认国家
     *
     * @return 系统默认国家
     */
    public static String getCountry() {
        return System.getProperty("user.country");
    }

    /**
     * 获取系统默认时区
     *
     * @return 系统默认时区
     */
    public static String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

    /**
     * 获取系统默认编码
     *
     * @return 系统默认编码
     */
    public static String getDefaultCharset() {
        return System.getProperty("file.encoding");
    }

    /**
     * 获取 Java 运行时环境版本
     *
     * @return Java 运行时环境版本
     */
    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    /**
     * 获取 Java 运行时环境供应商
     *
     * @return Java 运行时环境供应商
     */
    public static String getJavaVendor() {
        return System.getProperty("java.vendor");
    }

    /**
     * 获取 Java 供应商的 URL
     *
     * @return Java 供应商的 URL
     */
    public static String getJavaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    /**
     * 获取 Java 安装目录
     *
     * @return Java 安装目录
     */
    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    /**
     * 获取 Java 虚拟机规范版本
     *
     * @return Java 虚拟机规范版本
     */
    public static String getJavaVmSpecificationVersion() {
        return System.getProperty("java.vm.specification.version");
    }

    /**
     * 获取 Java 虚拟机规范供应商
     *
     * @return Java 虚拟机规范供应商
     */
    public static String getJavaVmSpecificationVendor() {
        return System.getProperty("java.vm.specification.vendor");
    }

    /**
     * 获取 Java 虚拟机规范名称
     *
     * @return Java 虚拟机规范名称
     */
    public static String getJavaVmSpecificationName() {
        return System.getProperty("java.vm.specification.name");
    }

    /**
     * 获取 Java 虚拟机实现版本
     *
     * @return Java 虚拟机实现版本
     */
    public static String getJavaVmVersion() {
        return System.getProperty("java.vm.version");
    }

    /**
     * 获取 Java 虚拟机实现供应商
     *
     * @return Java 虚拟机实现供应商
     */
    public static String getJavaVmVendor() {
        return System.getProperty("java.vm.vendor");
    }

    /**
     * 获取 Java 虚拟机实现名称
     *
     * @return Java 虚拟机实现名称
     */
    public static String getJavaVmName() {
        return System.getProperty("java.vm.name");
    }

    /**
     * 获取 Java 运行时环境规范版本
     *
     * @return Java 运行时环境规范版本
     */
    public static String getJavaSpecificationVersion() {
        return System.getProperty("java.specification.version");
    }

    /**
     * 获取 系统环境变量
     *
     * @param name 环境变量名称
     * @return 系统环境变量
     */
    public static String getEnv(String name) {
        return System.getenv(name);
    }

    /**
     * 获取 系统环境变量
     *
     * @return 系统环境变量
     */
    public static Map<String, String> getEnv() {
        return System.getenv();
    }

    /**
     * 获取 系统属性
     *
     * @param name 属性名称
     * @return 系统属性
     */
    public static String getProperty(String name) {
        return System.getProperty(name);
    }

    /**
     * 获取 系统属性
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return 系统属性
     */
    public static String getProperty(String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }

    /**
     * 获取 系统属性
     *
     * @return 系统属性
     */
    public static Properties getProperties() {
        return System.getProperties();
    }

    /**
     * 获取 系统属性
     *
     * @param prefix 属性前缀
     * @return 系统属性
     */
    public static Map<String, String> getProperties(String prefix) {
        Properties properties = getProperties();
        Map<String, String> map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            if (name.startsWith(prefix)) {
                map.put(name, properties.getProperty(name));
            }
        }
        return map;
    }

    /**
     * 获取 系统属性
     *
     * @param prefix      属性前缀
     * @param stripPrefix 是否去除前缀
     * @return 系统属性
     */
    public static Map<String, String> getProperties(String prefix, boolean stripPrefix) {
        Properties properties = getProperties();
        Map<String, String> map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            if (name.startsWith(prefix)) {
                map.put(stripPrefix ? name.substring(prefix.length()) : name, properties.getProperty(name));
            }
        }
        return map;
    }

    /**
     * 获取 系统属性
     *
     * @param prefix           属性前缀
     * @param stripPrefix      是否去除前缀
     * @param ignoreEmptyValue 是否忽略空值
     * @return 系统属性
     */
    public static Map<String, String> getProperties(String prefix, boolean stripPrefix, boolean ignoreEmptyValue) {
        Properties properties = getProperties();
        Map<String, String> map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            if (name.startsWith(prefix)) {
                String value = properties.getProperty(name);
                if (!ignoreEmptyValue || StringUtils.isNotEmpty(value)) {
                    map.put(stripPrefix ? name.substring(prefix.length()) : name, value);
                }
            }
        }
        return map;
    }

    /**
     * 获取 系统属性
     *
     * @param prefix           属性前缀
     * @param stripPrefix      是否去除前缀
     * @param ignoreEmptyValue 是否忽略空值
     * @param trimValue        是否去除前后空格
     * @return 系统属性
     */
    public static Map<String, String> getProperties(String prefix, boolean stripPrefix, boolean ignoreEmptyValue, boolean trimValue) {
        Properties properties = getProperties();
        Map<String, String> map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            if (name.startsWith(prefix)) {
                String value = properties.getProperty(name);
                if (trimValue) {
                    value = StringUtils.trim(value);
                }
                if (!ignoreEmptyValue || StringUtils.isNotEmpty(value)) {
                    map.put(stripPrefix ? name.substring(prefix.length()) : name, value);
                }
            }
        }
        return map;
    }

    /**
     * 获取 系统属性
     *
     * @param prefix           属性前缀
     * @param stripPrefix      是否去除前缀
     * @param ignoreEmptyValue 是否忽略空值
     * @param trimValue        是否去除前后空格
     * @param toLowerCase      是否转换为小写
     * @return 系统属性
     */
    public static Map<String, String> getProperties(String prefix, boolean stripPrefix, boolean ignoreEmptyValue, boolean trimValue, boolean toLowerCase) {
        Properties properties = getProperties();
        Map<String, String> map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            if (name.startsWith(prefix)) {
                String value = properties.getProperty(name);
                if (trimValue) {
                    value = StringUtils.trim(value);
                }
                if (!ignoreEmptyValue || StringUtils.isNotEmpty(value)) {
                    map.put(toLowerCase ? (stripPrefix ? name.substring(prefix.length()).toLowerCase() : name.toLowerCase()) : (stripPrefix ? name.substring(prefix.length()) : name), value);
                }
            }
        }
        return map;
    }


}