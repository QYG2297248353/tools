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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ms2297248353
 */
public class AliyunSignatureUtils {
    private final static String CHARSET_UTF8 = "utf8";
    private final static String ALGORITHM = "HmacSHA1";
    private final static String SEPARATOR = "&";

    public static Map<String, String> splitQueryString(String url)
            throws URISyntaxException, UnsupportedEncodingException {
        URI uri = new URI(url);
        String query = uri.getQuery();
        String[] pairs = query.split("&");
        TreeMap<String, String> queryMap = new TreeMap<>();
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = idx > 0 ? pair.substring(0, idx) : pair;
            if (!queryMap.containsKey(key)) {
                queryMap.put(key, URLDecoder.decode(pair.substring(idx + 1), CHARSET_UTF8));
            }
        }
        return queryMap;
    }

    public static String generate(String method, Map<String, String> parameter, String accessKeySecret)
            throws Exception {
        String signString = generateSignString(method, parameter);
        System.out.println("signString---" + signString);
        byte[] signBytes = hmacSHA1Signature(accessKeySecret + "&", signString);
        String signature = newStringByBase64(signBytes);
        System.out.println("signature----" + signature);
        if ("POST".equals(method)) {
            return signature;
        }
        return URLEncoder.encode(signature, "UTF-8");
    }

    public static String generateSignString(String httpMethod, Map<String, String> parameter) throws IOException {
        TreeMap<String, String> sortParameter = new TreeMap<>();
        sortParameter.putAll(parameter);
        String canonicalizedQueryString = AliyunUrlUtil.generateQueryString(sortParameter, true);
        if (null == httpMethod) {
            throw new RuntimeException("httpMethod can not be empty");
        }
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);
        stringToSign.append(percentEncode(canonicalizedQueryString));
        return stringToSign.toString();
    }

    public static String percentEncode(String value) {
        try {
            return value == null ? null
                    : URLEncoder.encode(value, CHARSET_UTF8).replace("+", "%20").replace("*", "%2A").replace("%7E",
                    "~");
        } catch (Exception e) {
        }
        return "";
    }

    public static byte[] hmacSHA1Signature(String secret, String baseString) throws Exception {
        if (StringUtils.isEmpty(secret)) {
            throw new IOException("secret can not be empty");
        }
        if (StringUtils.isEmpty(baseString)) {
            return null;
        }
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), ALGORITHM);
        mac.init(keySpec);
        return mac.doFinal(baseString.getBytes(CHARSET_UTF8));
    }

    public static String newStringByBase64(byte[] bytes) throws UnsupportedEncodingException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return new String(Base64.encodeBase64(bytes, false), CHARSET_UTF8);
    }
}