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

package com.ms.security.jwt.jjwt;

import com.ms.security.encryption.key.GenerateKeyPair;
import com.ms.security.jwt.jjwt.factory.JJWTFactory;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 密钥工具
 */
public class JJWT {
    /**
     * 构建器
     *
     * @return 构建工厂
     */
    public static JJWTFactory.Builder builder() {
        return JJWTFactory.builder();
    }

    /**
     * 解析器
     *
     * @return 解析工厂
     */
    public static JJWTFactory.Parser parser() {
        return JJWTFactory.parser();
    }

    /**
     * 获取密钥对
     *
     * @return 密钥信息
     */
    public static GenerateKeyPair keyPairGenerator() {
        return JJWTFactory.getKeyPair(SignatureAlgorithm.RS256);
    }
}
