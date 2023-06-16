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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云 API 签名
 * <p>
 * 规范化请求字符串
 *
 * @author ms2297248353
 */
public class ApiSignatureCore {

    // 1.需要修改Config.java中的AccessKey信息。
    // 2.建议使用方法二，所有参数都需要一一填写。
    // 3."最终signature"才是您需要的签名最终结果。
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        // 公共参数
        map.put("Format", "JSON");
        map.put("Version", "2018-01-20");
        map.put("AccessKeyId", "accessKey");
        map.put("SignatureMethod", "HMAC-SHA1");
        map.put("Timestamp", "2018-07-31T07:43:57Z");
        map.put("SignatureVersion", "1.0");
        map.put("SignatureNonce", "1533023037");
        map.put("RegionId", "cn-shanghai");
        // 请求参数
        map.put("Action", "RegisterDevice");
        map.put("DeviceName", "1533023037");
        map.put("ProductKey", "axxxUtgaRLB");
        try {
            String signature = AliyunSignatureUtils.generate("GET", map, "accessKeySecret");
            System.out.println("最终signature: " + signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}