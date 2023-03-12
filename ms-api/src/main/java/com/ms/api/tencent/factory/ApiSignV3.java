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

package com.ms.api.tencent.factory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * 腾讯云签名
 *
 * @author qyg2297248353
 */
public class ApiSignV3 {
    private static final Logger LOGGER = Logger.getLogger(ApiSignV3.class.getName());

    private static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
    }

    private static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    public static String signV3(String secretId, String secretKey, String timestamp, String payload) {
        try {
            String service = "sms";
            String host = TencentCloudApiConfig.Host.MAIN_SMS.getHost();
            String algorithm = "TC3-HMAC-SHA256";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 注意时区，否则容易出错
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String date = sdf.format(new Date(Long.parseLong(timestamp + "000")));

            // ************* 步骤 1：拼接规范请求串 *************
            String httpRequestMethod = "POST";
            String canonicalUri = "/";
            String canonicalQueryString = "";
            String canonicalHeaders = "content-type:application/json; charset=utf-8\n" + "host:" + host + "\n";
            String signedHeaders = "content-type;host";

            String hashedRequestPayload = sha256Hex(payload);
            String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                    + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;

            // ************* 步骤 2：签名字段 *************
            String credentialScope = date + "/" + service + "/" + "tc3_request";
            String hashedCanonicalRequest = sha256Hex(canonicalRequest);
            String stringToSign = algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;

            // ************* 步骤 3：计算签名 *************
            byte[] secretDate = hmac256(("TC3" + secretKey).getBytes(StandardCharsets.UTF_8), date);
            byte[] secretService = hmac256(secretDate, service);
            byte[] secretSigning = hmac256(secretService, "tc3_request");
            String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();

            // ************* 步骤 4：拼接 Authorization *************
            return algorithm + " " + "Credential=" + secretId + "/" + credentialScope + ", "
                    + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return "";
        }
    }
}
