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

package com.ms.api.aliyun.core;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @author ms2297248353
 */
public class AliyunUrlUtil {
    private final static String CHARSET_UTF8 = "utf8";

    public static String urlEncode(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                url = URLEncoder.encode(url, "UTF-8");
            } catch (Exception e) {
                System.out.println("Url encode error:" + e.getMessage());
            }
        }
        return url;
    }

    public static String generateQueryString(Map<String, String> params, boolean isEncodeKV) {
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (isEncodeKV) {
                canonicalizedQueryString.append(percentEncode(entry.getKey())).append("=")
                        .append(percentEncode(entry.getValue())).append("&");
            } else {
                canonicalizedQueryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (canonicalizedQueryString.length() > 1) {
            canonicalizedQueryString.setLength(canonicalizedQueryString.length() - 1);
        }
        return canonicalizedQueryString.toString();
    }

    public static String percentEncode(String value) {
        try {
            // 使用URLEncoder.encode编码后，将"+","*","%7E"做替换即满足API规定的编码规范。
            return value == null ? null
                    : URLEncoder.encode(value, CHARSET_UTF8).replace("+", "%20").replace("*", "%2A").replace("%7E",
                    "~");
        } catch (Exception e) {

        }
        return "";
    }
}