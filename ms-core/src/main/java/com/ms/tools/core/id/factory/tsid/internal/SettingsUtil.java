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

package com.ms.tools.core.id.factory.tsid.internal;

/**
 * Reads system properties and environment variables.
 * <p>
 * The system properties has prevalence over environment variables.
 * <p>
 * Available properties and variables:
 * <ul>
 * <li>tsidcreator.node
 * <li>TSIDCREATOR_NODE
 * </ul>
 */
public final class SettingsUtil {

    static final String PROPERTY_PREFIX = "tsidcreator";
    static final String PROPERTY_NODE = "node";

    protected SettingsUtil() {
    }

    public static Integer getNode() {
        String value = getProperty(PROPERTY_NODE);

        if (value == null) {
            return null;
        }

        try {
            return Integer.decode(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void setNode(Integer node) {
        String value = Integer.toString(node);
        setProperty(PROPERTY_NODE, value);
    }

    static String getProperty(String name) {

        String fullName = getPropertyName(name);
        String value = System.getProperty(fullName);
        if (!isEmpty(value)) {
            return value;
        }

        fullName = getEnvinronmentName(name);
        value = System.getenv(fullName);
        if (!isEmpty(value)) {
            return value;
        }

        return null;
    }

    static void setProperty(String key, String value) {
        System.setProperty(getPropertyName(key), value);
    }

    static void clearProperty(String key) {
        System.clearProperty(getPropertyName(key));
    }

    static String getPropertyName(String key) {
        return String.join(".", PROPERTY_PREFIX, key);
    }

    static String getEnvinronmentName(String key) {
        return String.join("_", PROPERTY_PREFIX, key).toUpperCase().replace(".", "_");
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
