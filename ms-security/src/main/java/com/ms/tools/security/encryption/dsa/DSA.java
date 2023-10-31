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

package com.ms.tools.security.encryption.dsa;

import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.security.encryption.dsa.factory.DsaFactory;
import com.ms.tools.security.encryption.key.DsaKey;
import com.ms.tools.security.encryption.key.GenerateKeyPair;

public class DSA {

    /**
     * RSA私钥签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     * @throws MsToolsException 签名异常
     */
    public static String sign(String data, String privateKey) throws MsToolsException {
        return DsaFactory.sign(data, privateKey);
    }

    /**
     * RSA公钥验签
     *
     * @param source    原始字符串
     * @param publicKey 公钥
     * @param sign      签名字符串
     * @return 是否验签通过
     * @throws MsToolsException 验证异常
     */
    public static boolean verify(String source, String publicKey, String sign) throws MsToolsException {
        return DsaFactory.verify(source, publicKey, sign);
    }

    /**
     * 获取密钥
     * 密钥单例维护，每次部署全局唯一 重启服务更换密钥
     *
     * @return 密钥
     */
    public static String getPublicKey() {
        return DsaKey.getDsaKey().getPublicKeyStr();
    }

    /**
     * 获取密钥
     * 密钥单例维护，每次部署全局唯一 重启服务更换密钥
     *
     * @return 密钥
     */
    public static String getPrivateKey() {
        return DsaKey.getDsaKey().getPrivateStr();
    }

    /**
     * 获取密钥信息
     *
     * @return 密钥信息
     */
    public static GenerateKeyPair getKeyPair() {
        return DsaKey.getDsaKey().getGenerateKeyPair();
    }

}
