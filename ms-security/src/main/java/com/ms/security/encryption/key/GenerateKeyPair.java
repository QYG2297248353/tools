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

package com.ms.security.encryption.key;

import com.ms.security.binary.base64.Base64;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 密钥信息
 * 非对称加密
 *
 * @author qyg2297248353
 */
public class GenerateKeyPair {
    /**
     * 密钥对
     */
    private KeyPair keyPair;

    /**
     * 公钥字符串
     */
    private String publicKey;

    /**
     * 私钥字符串
     */
    private String privateKey;

    public GenerateKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
        // 获取公钥
        PublicKey pubKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey priKey = keyPair.getPrivate();
        // 获取byte数组
        byte[] publicKeyEncoded = pubKey.getEncoded();
        byte[] privateKeyEncoded = priKey.getEncoded();
        // 进行Base64编码
        publicKey = Base64.encodeBase64String(publicKeyEncoded);
        privateKey = Base64.encodeBase64String(privateKeyEncoded);
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    protected void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String getPublicKey() {
        return publicKey;
    }

    protected void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    protected void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
