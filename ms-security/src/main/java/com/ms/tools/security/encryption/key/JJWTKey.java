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

package com.ms.tools.security.encryption.key;

import com.ms.tools.security.jjwt.factory.JJWTFactory;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author qyg2297248353
 */
public class JJWTKey {

    private static volatile JJWTKey RSA_JJWT_KEY;
    private GenerateKeyPair keyPair;

    private JJWTKey() {
        keyPair = JJWTFactory.getKeyPair(SignatureAlgorithm.RS256);
    }


    public static JJWTKey getJJWTKey() {
        if (RSA_JJWT_KEY == null) {
            synchronized (JJWTKey.class) {
                if (RSA_JJWT_KEY == null) {
                    RSA_JJWT_KEY = new JJWTKey();
                }
            }
        }
        return RSA_JJWT_KEY;
    }

    /**
     * 获取密钥
     *
     * @return 返回密钥
     */
    public PublicKey getPublicKey() {
        return keyPair.getKeyPair().getPublic();
    }

    /**
     * 获取密钥
     *
     * @return 返回密钥
     */
    public PrivateKey getPrivate() {
        return keyPair.getKeyPair().getPrivate();
    }

    /**
     * 获取密钥
     *
     * @return 返回密钥
     */
    public String getPublicKeyStr() {
        return keyPair.getPublicKey();
    }

    /**
     * 获取密钥
     *
     * @return 返回密钥
     */
    public String getPrivateStr() {
        return keyPair.getPrivateKey();
    }

    public KeyPair getKeyPair() {
        return keyPair.getKeyPair();
    }

    public GenerateKeyPair getGenerateKeyPair() {
        return keyPair;
    }

}
