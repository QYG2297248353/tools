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

package com.ms.security.encryption.rsa.factory;

import com.ms.core.exception.base.MsToolsException;
import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.security.encryption.key.GenerateKeyPair;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

public class RsaFactory {
    /**
     * 加密算法
     */
    private static final String RSA = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA 密钥位数
     */
    private static final Integer RSA_SIZE = 2048;
    private static final Integer RSA_SIZE_BASE = 64;
    private static final Integer RSA_MIN_SIZE = 512;
    private static final Integer RSA_MAX_SIZE = 65536;

    /**
     * 最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = RSA_SIZE / 8;

    /**
     * 最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = MAX_DECRYPT_BLOCK - 11;


    /**
     * RSA公钥加密
     *
     * @param encodeStr 需要加密的字符串
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encodePub(byte[] encodeStr, String publicKey) throws MsToolsException {
        try {
            // 公钥base64解密
            byte[] decode = Base64.decodeBase64(publicKey);
            // key工厂获取RSA对象,根据X509EncodedKeySpec生成公钥对象
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decode));
            // 获取Cipher RSA对象
            Cipher cipher = Cipher.getInstance(RSA);
            // 用于将密码初始化为加密模式的常量。
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            // base64 二次编码
            return Base64.encodeBase64String(cipher.doFinal(encodeStr));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * RSA私钥加密
     *
     * @param encodeStr  需要加密的字符串
     * @param privateKey 公钥
     * @return 密文
     */
    public static String encodePri(byte[] encodeStr, String privateKey) throws MsToolsException {
        try {
            // 公钥base64解密
            byte[] decode = Base64.decodeBase64(privateKey);
            // key工厂获取RSA对象,根据PKCS8EncodedKeySpec生成私钥对象
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(decode));
            // 获取Cipher RSA对象
            Cipher cipher = Cipher.getInstance(RSA);
            // 用于将密码初始化为加密模式的常量。
            cipher.init(Cipher.ENCRYPT_MODE, priKey);
            // base64 二次编码
            return Base64.encodeBase64String(cipher.doFinal(encodeStr));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * RSA私钥解密
     *
     * @param decodeStr  密文字节数组
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decodePri(byte[] decodeStr, String privateKey) throws MsToolsException {
        byte[] decode = Base64.decodeBase64(decodeStr);
        try {
            // 私钥base64解密
            byte[] decodePri = Base64.decodeBase64(privateKey);
            // key工厂获取RSA对象,根据PKCS8EncodedKeySpec生成私钥对象
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(decodePri));
            // 获取Cipher RSA对象
            Cipher cipher = Cipher.getInstance(RSA);
            // 用于将密码初始化为解密模式的常量。
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            // 解密or加密
            return new String(cipher.doFinal(decode));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * RSA公钥解密
     *
     * @param decodeStr 密文字节数组
     * @param publicKey 私钥
     * @return 明文
     */
    public static String decodePub(byte[] decodeStr, String publicKey) throws MsToolsException {
        byte[] decode = Base64.decodeBase64(decodeStr);
        try {
            // 私钥base64解密
            byte[] decodePub = Base64.decodeBase64(publicKey);
            // key工厂获取RSA对象,根据X509EncodedKeySpec生成公钥对象
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decodePub));
            // 获取Cipher RSA对象
            Cipher cipher = Cipher.getInstance(RSA);
            // 用于将密码初始化为解密模式的常量。
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            // 解密or加密
            return new String(cipher.doFinal(decode));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 生成密钥对
     *
     * @return 密钥对
     */
    public static GenerateKeyPair generateKey() {
        try {
            return generateKey(RSA_SIZE);
        } catch (MsToolsException e) {
            throw new MsToolsRuntimeException(e);
        }
    }

    /**
     * 生成密钥对
     *
     * @return 密钥对
     */
    public static GenerateKeyPair generateKey(int keySize) throws MsToolsException {
        if (keySize % RSA_SIZE_BASE == 0 && keySize >= RSA_MIN_SIZE && keySize <= RSA_MAX_SIZE) {
            try {
                // 获取密钥对生成器
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
                keyPairGenerator.initialize(keySize);
                // 获取密钥对
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                return new GenerateKeyPair(keyPair);
            } catch (NoSuchAlgorithmException e) {
                throw new MsToolsException(e);
            }
        }
        throw new MsToolsException("Invalid key size " + keySize + " for generating key pair");
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
            // 进行Base64解码
            byte[] decode = Base64.decodeBase64(keyString);
            // 获取密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            // 构建密钥规范
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
            // 获取公钥
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 从字符串中加载私钥
     *
     * @param keyString : 私钥字符串
     * @return : 私钥
     * @throws MsToolsException 读取异常
     */
    public static PrivateKey loadPrivateKeyFromString(String keyString) throws MsToolsException {
        try {
            // 进行Base64解码
            byte[] decode = Base64.decodeBase64(keyString);
            // 获取密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            // 构建密钥规范
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
            // 生成私钥
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 从字符串中加载密钥对
     *
     * @param privateKey 私钥字符串
     * @param publicKey  私钥字符串
     * @return : 密钥对
     * @throws MsToolsException 读取异常
     */
    public static GenerateKeyPair loadKeyPairFromString(String privateKey, String publicKey) throws MsToolsException {
        PrivateKey priKey = loadPrivateKeyFromString(privateKey);
        PublicKey pubKey = loadPublicKeyFromString(publicKey);
        KeyPair keyPair = new KeyPair(pubKey, priKey);
        return new GenerateKeyPair(keyPair);
    }


    /**
     * 获取加密长度
     *
     * @param privateKey 密钥
     * @return 长度 -1 解密失败
     */
    public static Integer privateKeyLength(String privateKey) {
        try {
            PrivateKey key = loadPrivateKeyFromString(privateKey);
            return privateKeyLength(key);
        } catch (MsToolsException e) {
            return -1;
        }
    }

    /**
     * 获取加密长度
     *
     * @param publicKey 密钥
     * @return 长度 -1 解密失败
     */
    public static Integer publicKeyLength(String publicKey) {
        try {
            PublicKey key = loadPublicKeyFromString(publicKey);
            return publicKeyLength(key);
        } catch (MsToolsException e) {
            return -1;
        }
    }

    /**
     * 获取加密长度
     *
     * @param privateKey 密钥
     * @return 长度 -1 解密失败
     */
    public static Integer privateKeyLength(PrivateKey privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            RSAPrivateKeySpec keySpec1 = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
            BigInteger modulus = keySpec1.getModulus();
            return modulus.toString(2).length();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return -1;
        }
    }

    /**
     * 获取加密长度
     *
     * @param publicKey 密钥
     * @return 长度 -1 解密失败
     */
    public static Integer publicKeyLength(PublicKey publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            RSAPublicKeySpec keySpec1 = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            BigInteger modulus = keySpec1.getModulus();
            return modulus.toString(2).length();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return -1;
        }
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
        PrivateKey key = loadPrivateKeyFromString(privateKey);
        return sign(data, key);
    }

    /**
     * RSA公钥验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名字符串
     * @return 是否验签通过
     * @throws MsToolsException 验证异常
     */
    public static boolean verify(String srcData, String publicKey, String sign) throws MsToolsException {
        PublicKey key = loadPublicKeyFromString(publicKey);
        return verify(srcData, key, sign);
    }


    /**
     * RSA私钥签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     * @throws MsToolsException 签名异常
     */
    public static String sign(String data, PrivateKey privateKey) throws MsToolsException {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            return new String(Base64.encodeBase64(signature.sign()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * RSA公钥验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名字符串
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws MsToolsException {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(srcData.getBytes());
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 私钥解密
     *
     * @param encryptedData 已加密数据
     * @param privateKey    私钥(BASE64编码)
     * @return 明文
     */
    public static byte[] decryptPri(byte[] encryptedData, String privateKey) throws MsToolsException {
        try {
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateK);
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            int maxDecryptBlock = privateKeyLength(privateKey) / 8;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxDecryptBlock) {
                    cache = cipher.doFinal(encryptedData, offSet, maxDecryptBlock);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxDecryptBlock;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 公钥解密
     *
     * @param encryptedData 已加密数据
     * @param publicKey     公钥(BASE64编码)
     * @return 明文
     */
    public static byte[] decryptPub(byte[] encryptedData, String publicKey) throws MsToolsException {
        try {
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicK);
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            int maxDecryptBlock = publicKeyLength(publicKey) / 8;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxDecryptBlock) {
                    cache = cipher.doFinal(encryptedData, offSet, maxDecryptBlock);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxDecryptBlock;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 公钥加密
     *
     * @param data      源数据
     * @param publicKey 公钥(BASE64编码)
     * @return 密文
     */
    public static byte[] encryptPub(byte[] data, String publicKey) throws MsToolsException {
        try {
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicK);
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            int maxDecryptBlock = publicKeyLength(publicKey) / 8;
            int maxEncryptBlock = maxDecryptBlock - 11;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxEncryptBlock) {
                    cache = cipher.doFinal(data, offSet, maxEncryptBlock);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxEncryptBlock;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 私钥加密
     *
     * @param data       源数据
     * @param privateKey 私钥(BASE64编码)
     * @return 密文
     */
    public static byte[] encryptPri(byte[] data, String privateKey) throws MsToolsException {
        try {
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateK);
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            int maxDecryptBlock = privateKeyLength(privateKey) / 8;
            int maxEncryptBlock = maxDecryptBlock - 11;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxEncryptBlock) {
                    cache = cipher.doFinal(data, offSet, maxEncryptBlock);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxEncryptBlock;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new MsToolsException(e);
        }
    }
}
