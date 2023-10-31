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


import com.ms.tools.security.encryption.dsa.factory.DsaFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DsaKey {

    private static volatile DsaKey DSA_KEY;
    private GenerateKeyPair keyPair;

    private DsaKey() {
        keyPair = DsaFactory.generateKey();
    }


    public static DsaKey getDsaKey() {
        if (DSA_KEY == null) {
            synchronized (DsaKey.class) {
                if (DSA_KEY == null) {
                    DSA_KEY = new DsaKey();
                }
            }
        }
        return DSA_KEY;
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
