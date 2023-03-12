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

package com.ms.security.encryption.rsa;


import com.ms.core.exception.base.MsToolsException;
import com.ms.security.encryption.key.GenerateKeyPair;
import com.ms.security.encryption.key.RsaKey;
import com.ms.security.encryption.rsa.factory.RsaFactory;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSA
 *
 * @author qyg2297248353
 */
public class RSA {

    /**
     * RSA公钥加密
     * 默认密钥
     *
     * @param encodeStr 需要加密的字符串
     * @return 密文
     */
    public static String encodePub(String encodeStr) throws MsToolsException {
        return encodePub(encodeStr.getBytes());
    }

    /**
     * RSA公钥加密
     * 默认密钥
     *
     * @param encodeStr 需要加密的字节数组
     * @return 密文
     */
    public static String encodePub(byte[] encodeStr) throws MsToolsException {
        return encodePub(encodeStr, RsaKey.getRsaKey().getPublicKeyStr());
    }

    /**
     * RSA公钥加密
     *
     * @param encodeStr 需要加密的字符串
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encodePub(String encodeStr, String publicKey) throws MsToolsException {
        return encodePub(encodeStr.getBytes(), publicKey);
    }

    /**
     * RSA公钥加密
     *
     * @param encodeStr 需要加密的字节数组
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encodePub(byte[] encodeStr, String publicKey) throws MsToolsException {
        return RsaFactory.encodePub(encodeStr, publicKey);
    }


    /**
     * RSA私钥加密
     * 默认密钥
     *
     * @param decodeStr 密文
     * @return 明文
     */
    public static String encodePri(String decodeStr) throws MsToolsException {
        return encodePri(decodeStr.getBytes());
    }

    /**
     * RSA私钥加密
     * 默认密钥
     *
     * @param decodeStr 密文字节数组
     * @return 明文
     */
    public static String encodePri(byte[] decodeStr) throws MsToolsException {
        // 密文base64解密
        return encodePri(decodeStr, RsaKey.getRsaKey().getPrivateStr());
    }

    /**
     * RSA私钥加密
     *
     * @param decodeStr  密文
     * @param privateKey 私钥
     * @return 明文
     */
    public static String encodePri(String decodeStr, String privateKey) throws MsToolsException {
        return encodePri(decodeStr.getBytes(), privateKey);
    }

    /**
     * RSA私钥加密
     *
     * @param decodeStr  密文字节数组
     * @param privateKey 私钥
     * @return 明文
     */
    public static String encodePri(byte[] decodeStr, String privateKey) throws MsToolsException {
        return RsaFactory.encodePri(decodeStr, privateKey);
    }


    /**
     * RSA私钥解密
     * 默认密钥
     *
     * @param decodeStr 密文
     * @return 明文
     */
    public static String decodePri(String decodeStr) throws MsToolsException {
        return decodePri(decodeStr.getBytes());
    }

    /**
     * RSA私钥解密
     * 默认密钥
     *
     * @param decodeStr 密文字节数组
     * @return 明文
     */
    public static String decodePri(byte[] decodeStr) throws MsToolsException {
        // 密文base64解密
        return decodePri(decodeStr, RsaKey.getRsaKey().getPrivateStr());
    }

    /**
     * RSA私钥解密
     *
     * @param decodeStr  密文
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decodePri(String decodeStr, String privateKey) throws MsToolsException {
        return decodePri(decodeStr.getBytes(), privateKey);
    }

    /**
     * RSA私钥解密
     *
     * @param decodeStr  密文字节数组
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decodePri(byte[] decodeStr, String privateKey) throws MsToolsException {
        return RsaFactory.decodePri(decodeStr, privateKey);
    }


    /**
     * RSA公钥解密
     * 默认密钥
     *
     * @param encodeStr 需要加密的字符串
     * @return 密文
     */
    public static String decodePub(String encodeStr) throws MsToolsException {
        return decodePub(encodeStr.getBytes());
    }

    /**
     * RSA公钥解密
     * 默认密钥
     *
     * @param encodeStr 需要加密的字节数组
     * @return 密文
     * @throws MsToolsException 异常
     */
    public static String decodePub(byte[] encodeStr) throws MsToolsException {
        return decodePub(encodeStr, RsaKey.getRsaKey().getPublicKeyStr());
    }

    /**
     * RSA公钥解密
     *
     * @param encodeStr 需要加密的字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws MsToolsException 异常
     */
    public static String decodePub(String encodeStr, String publicKey) throws MsToolsException {
        return decodePub(encodeStr.getBytes(), publicKey);
    }

    /**
     * RSA公钥解密
     *
     * @param encodeStr 需要加密的字节数组
     * @param publicKey 公钥
     * @return 密文
     * @throws MsToolsException 异常
     */
    public static String decodePub(byte[] encodeStr, String publicKey) throws MsToolsException {
        return RsaFactory.decodePub(encodeStr, publicKey);
    }


    /**
     * 生成密钥对
     * 2048默认密钥位数
     *
     * @return 密钥对
     */
    public static GenerateKeyPair generateKey() {
        return RsaFactory.generateKey();
    }

    /**
     * 生成密钥对
     *
     * @return 密钥对
     * @throws MsToolsException 异常
     */
    public static GenerateKeyPair generateKey1024() throws MsToolsException {
        return RsaFactory.generateKey(1024);
    }

    /**
     * RSA私钥签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     * @throws MsToolsException 签名异常
     */
    public static String sign(String data, String privateKey) throws MsToolsException {
        return RsaFactory.sign(data, privateKey);
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
        return RsaFactory.verify(source, publicKey, sign);
    }

    /**
     * 获取密钥
     * 密钥单例维护，每次部署全局唯一 重启服务更换密钥
     *
     * @return 密钥
     */
    public static String getPublicKey() {
        return RsaKey.getRsaKey().getPublicKeyStr();
    }

    /**
     * 获取密钥
     * 密钥单例维护，每次部署全局唯一 重启服务更换密钥
     *
     * @return 密钥
     */
    public static String getPrivateKey() {
        return RsaKey.getRsaKey().getPrivateStr();
    }

    /**
     * 获取密钥信息
     *
     * @return 密钥信息
     */
    public static GenerateKeyPair getKeyPair() {
        return RsaKey.getRsaKey().getGenerateKeyPair();
    }

    /**
     * 加载密钥信息
     *
     * @param privateKey 私钥
     * @return 密钥-私钥
     * @throws MsToolsException 读取异常
     */
    public PrivateKey loadPrivateKey(String privateKey) throws MsToolsException {
        return RsaFactory.loadPrivateKeyFromString(privateKey);
    }

    /**
     * 加载密钥信息
     *
     * @param publicKey 公钥
     * @return 密钥-公钥
     * @throws MsToolsException 读取异常
     */
    public PublicKey loadPublicKey(String publicKey) throws MsToolsException {
        return RsaFactory.loadPublicKeyFromString(publicKey);
    }

    /**
     * 加载密钥信息
     *
     * @param privateKey 私钥文件
     * @return 密钥-私钥
     * @throws MsToolsException 读取异常
     */
    public PrivateKey loadPrivateKey(File privateKey) throws MsToolsException {
        return RsaFactory.loadPrivateKeyFromFile(privateKey);
    }

    /**
     * 加载密钥信息
     *
     * @param publicKey 公钥文件
     * @return 密钥-公钥
     * @throws MsToolsException 读取异常
     */
    public PublicKey loadPublicKey(File publicKey) throws MsToolsException {
        return RsaFactory.loadPublicKeyFromFile(publicKey);
    }

    /**
     * 加载密钥信息
     *
     * @param privateKey 私钥
     * @param publicKey  公钥
     * @return 密钥对
     * @throws MsToolsException 工具
     */
    public GenerateKeyPair loadKeyPair(String privateKey, String publicKey) throws MsToolsException {
        return RsaFactory.loadKeyPairFromString(privateKey, publicKey);
    }

    /**
     * 获取密钥加密长度
     * 默认加密长度2048
     * -1表示读取密钥失败
     *
     * @param publicKey 密钥
     * @return 密钥长度
     */
    public Integer getPublicKeyLength(String publicKey) {
        return RsaFactory.publicKeyLength(publicKey);
    }

    /**
     * 获取密钥加密长度
     * 默认加密长度2048
     * -1表示读取密钥失败
     *
     * @param publicKey 密钥
     * @return 密钥长度
     */
    public Integer getKeyLength(PublicKey publicKey) {
        return RsaFactory.publicKeyLength(publicKey);
    }

    /**
     * 获取密钥加密长度
     * 默认加密长度2048
     * -1表示读取密钥失败
     *
     * @param privateKey 密钥
     * @return 密钥长度
     */
    public Integer getPrivateKeyLength(String privateKey) {
        return RsaFactory.privateKeyLength(privateKey);
    }

    /**
     * 获取密钥加密长度
     * 默认加密长度2048
     * -1表示读取密钥失败
     *
     * @param privateKey 密钥
     * @return 密钥长度
     */
    public Integer getKeyLength(PrivateKey privateKey) {
        return RsaFactory.privateKeyLength(privateKey);
    }

}
