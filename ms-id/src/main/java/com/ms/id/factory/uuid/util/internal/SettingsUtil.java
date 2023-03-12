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

package com.ms.id.factory.uuid.util.internal;

/**
 * Utility class that reads system properties and environment variables.
 * <p>
 * List of system properties:
 * <ul>
 * <li>uuidcreator.node
 * <li>uuidcreator.securerandom
 * </ul>
 * <p>
 * List of environment variables:
 * <ul>
 * <li>UUIDCREATOR_NODE
 * <li>UUIDCREATOR_SECURERANDOM
 * </ul>
 * <p>
 * System properties has prevalence over environment variables.
 */
public final class SettingsUtil {

    public static final String PROPERTY_NODE = "node";
    public static final String PROPERTY_SECURERANDOM = "securerandom";
    protected static final String PROPERTY_PREFIX = "uuidcreator";

    protected SettingsUtil() {
    }

    public static Long getNodeIdentifier() {
        String value = getProperty(PROPERTY_NODE);
        if (value == null) {
            return null;
        }
        try {
            return Long.decode(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void setNodeIdentifier(Long nodeid) {
        String value = Long.toString(nodeid);
        setProperty(PROPERTY_NODE, value);
    }

    public static String getSecureRandom() {
        return getProperty(PROPERTY_SECURERANDOM);
    }

    public static void setSecureRandom(String random) {
        setProperty(PROPERTY_SECURERANDOM, random);
    }

    public static String getProperty(String name) {

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

    public static void setProperty(String key, String value) {
        System.setProperty(getPropertyName(key), value);
    }

    public static void clearProperty(String key) {
        System.clearProperty(getPropertyName(key));
    }

    protected static String getPropertyName(String key) {
        return String.join(".", PROPERTY_PREFIX, key);
    }

    protected static String getEnvinronmentName(String key) {
        return String.join("_", PROPERTY_PREFIX, key).toUpperCase().replace(".", "_");
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
