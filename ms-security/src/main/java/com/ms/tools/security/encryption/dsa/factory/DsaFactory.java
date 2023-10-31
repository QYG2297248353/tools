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

package com.ms.tools.security.encryption.dsa.factory;

import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.core.exception.base.MsToolsRuntimeException;
import com.ms.tools.security.codec.Base64;
import com.ms.tools.security.encryption.key.GenerateKeyPair;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * DSA签名
 *
 * @author qyg2297248353
 */
public class DsaFactory {
    /**
     * 加密算法
     */
    private static final String DSA = "DSA";
    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1withDSA";
    /**
     * RSA 密钥位数
     */
    private static final Integer DSA_SIZE = 512;
    private static final Integer DSA_SIZE_BASE = 64;
    private static final Integer DSA_MIN_SIZE = 512;
    private static final Integer DSA_MAX_SIZE = 1024;

    /**
     * 生成密钥对
     *
     * @return 密钥对
     */
    public static GenerateKeyPair generateKey() {
        try {
            return generateKey(DSA_SIZE);
        } catch (MsToolsException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    /**
     * 生成密钥对
     *
     * @param keySize 密钥长度 512-1024
     * @return 密钥对
     * @throws MsToolsException 构建异常
     */
    public static GenerateKeyPair generateKey(Integer keySize) throws MsToolsException {
        if (keySize % DSA_SIZE_BASE == 0 && keySize >= DSA_MIN_SIZE && keySize <= DSA_MAX_SIZE) {
            try {
                // 获取密钥对生成器
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA);
                keyPairGenerator.initialize(keySize);
                // 获取密钥对
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                return new GenerateKeyPair(keyPair);
            } catch (NoSuchAlgorithmException e) {
                throw new MsToolsRuntimeException(e);
            }
        }
        throw new MsToolsException("Invalid key size " + keySize + " for generating key pair");
    }

    /**
     * 从字符串中加载私钥
     *
     * @param keyString : 私钥字符串
     * @return : 私钥
     * @throws MsToolsException 加载异常
     */
    public static PrivateKey loadPrivateKeyFromString(String keyString) throws MsToolsException {
        try {
            byte[] decode = Base64.decodeBase64(keyString);
            return KeyFactory.getInstance(DSA)
                    .generatePrivate(new PKCS8EncodedKeySpec(decode));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param keyString : 公钥字符串
     * @return : 公钥
     * @throws MsToolsException 加载异常
     */
    public static PublicKey loadPublicKeyFromString(String keyString) throws MsToolsException {
        try {
            byte[] decode = Base64.decodeBase64(keyString);
            return KeyFactory.getInstance(DSA)
                    .generatePublic(new X509EncodedKeySpec(decode));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 数字签名【私钥签名】
     *
     * @param data       待签名字符串
     * @param privateKey 私钥
     * @return 签名字符串
     * @throws MsToolsException 异常
     */
    public static String sign(String data, String privateKey) throws MsToolsException {
        PrivateKey key = loadPrivateKeyFromString(privateKey);
        return sign(data, key);
    }

    /**
     * 验证签名【公钥验证】
     *
     * @param source    源数据
     * @param publicKey 公钥
     * @param sign      签名字符串
     * @return 是否一致
     * @throws MsToolsException 异常
     */
    public static Boolean verify(String source, String publicKey, String sign) throws MsToolsException {
        PublicKey key = loadPublicKeyFromString(publicKey);
        return verify(source, key, sign);
    }

    /**
     * 数字签名【私钥签名】
     *
     * @param data       待加密数据
     * @param privateKey 私钥
     * @return String 签名
     * @throws MsToolsException 异常
     */
    public static String sign(String data, PrivateKey privateKey) throws MsToolsException {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(signature.sign()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 验证签名【公钥验证】
     *
     * @param source    待解密数据
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否一致
     */
    public static Boolean verify(String source, PublicKey publicKey, String sign) throws MsToolsException {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(source.getBytes());
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new MsToolsException(e);
        }
    }
}
