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

package com.ms.security.google.authenticator;

import com.ms.core.exception.base.MsToolsException;
import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.security.google.authenticator.factory.GoogleAuthenticator;
import com.ms.security.google.authenticator.factory.GoogleAuthenticatorKey;
import com.ms.security.google.authenticator.factory.GoogleAuthenticatorQRGenerator;
import com.ms.security.google.authenticator.factory.ICredentialRepository;

import java.util.Date;

/**
 * 单例维护-用户快速构建
 *
 * @author ms2297248353
 */
public class GoogleAuthenticatorSingleCase {
    private static volatile GoogleAuthenticatorSingleCase GOOGLE_AUTHENTICATOR_FACTORY_SINGLE_CASE;
    private GoogleAuthenticator GOOGLE_AUTHENTICATOR;
    private Boolean USE_INTERFACE;

    private GoogleAuthenticatorSingleCase(ICredentialRepository iCredentialRepository) {
        GOOGLE_AUTHENTICATOR = new GoogleAuthenticator();
        GOOGLE_AUTHENTICATOR.setCredentialRepository(iCredentialRepository);
        USE_INTERFACE = iCredentialRepository != null;
    }

    /**
     * 单例对象获取
     *
     * @param iCredentialRepository 接口 null禁用接口功能模块
     * @return 构建对象
     */
    public static GoogleAuthenticatorSingleCase getFactory(ICredentialRepository iCredentialRepository) {
        if (GOOGLE_AUTHENTICATOR_FACTORY_SINGLE_CASE == null) {
            synchronized (GoogleAuthenticatorSingleCase.class) {
                if (GOOGLE_AUTHENTICATOR_FACTORY_SINGLE_CASE == null) {
                    GOOGLE_AUTHENTICATOR_FACTORY_SINGLE_CASE = new GoogleAuthenticatorSingleCase(iCredentialRepository);
                }
            }
        }
        return GOOGLE_AUTHENTICATOR_FACTORY_SINGLE_CASE;
    }


    /**
     * 创建凭据
     *
     * @return 凭据
     */
    public GoogleAuthenticatorKey createCredentials() {
        return GOOGLE_AUTHENTICATOR.createCredentials();
    }

    /**
     * 创建并使用接口保存凭据
     *
     * @param credentials 用户凭据
     * @return 凭据
     * @throws MsToolsException 异常
     */
    public GoogleAuthenticatorKey createCredentials(String credentials) throws MsToolsException {
        checkImpl();
        return GOOGLE_AUTHENTICATOR.createCredentials(credentials);
    }

    private void checkImpl() throws MsToolsException {
        if (Boolean.FALSE.equals(USE_INTERFACE) || GOOGLE_AUTHENTICATOR.getCredentialRepository() == null) {
            throw new MsToolsException("Please provide an interface to access this service");
        }
    }

    /**
     * 通过密钥获取当前验证码
     *
     * @param secret 密钥
     * @return 验证码
     */
    public String getTotpCode(String secret) {
        int code = GOOGLE_AUTHENTICATOR.getTotpPassword(secret);
        return GOOGLE_AUTHENTICATOR.parseTotpCode(code);
    }

    /**
     * 通过密钥获取指定时间验证码
     *
     * @param secret 密钥
     * @param date   时间
     * @return 验证码
     */
    public String getTotpCode(String secret, Date date) {
        return getTotpCode(secret, date.getTime());
    }

    /**
     * 通过密钥获取指定时间验证码
     *
     * @param secret 密钥
     * @param time   时间
     * @return 验证码
     */
    public String getTotpCode(String secret, long time) {
        int code = GOOGLE_AUTHENTICATOR.getTotpPassword(secret, time);
        return GOOGLE_AUTHENTICATOR.parseTotpCode(code);
    }


    /**
     * 通过密钥获取当前验证码
     *
     * @param user 用户
     * @return 验证码
     * @throws MsToolsException 异常
     */
    public String getTotpCodeByUser(String user) throws MsToolsException {
        checkImpl();
        int code = GOOGLE_AUTHENTICATOR.getTotpPasswordOfUser(user);
        return GOOGLE_AUTHENTICATOR.parseTotpCode(code);
    }

    /**
     * 通过密钥获取指定时间验证码
     *
     * @param user 用户
     * @param date 时间
     * @return 验证码
     * @throws MsToolsException 异常
     */
    public String getTotpCodeByUser(String user, Date date) throws MsToolsException {
        checkImpl();
        return getTotpCodeByUser(user, date.getTime());
    }

    /**
     * 通过密钥获取指定时间验证码
     *
     * @param user 用户
     * @param time 时间
     * @return 验证码
     * @throws MsToolsException 异常
     */
    public String getTotpCodeByUser(String user, long time) throws MsToolsException {
        checkImpl();
        int code = GOOGLE_AUTHENTICATOR.getTotpPasswordOfUser(user, time);
        return GOOGLE_AUTHENTICATOR.parseTotpCode(code);
    }

    /**
     * 验证码效验
     *
     * @param secret 密钥
     * @param code   验证码
     * @return 效验结果
     */
    public boolean authorize(String secret, int code) {
        return GOOGLE_AUTHENTICATOR.authorize(secret, code);
    }

    /**
     * 验证码效验(指定时间)
     *
     * @param secret 密钥
     * @param code   验证码
     * @param time   时间
     * @return 效验结果
     */
    public boolean authorize(String secret, int code, Date time) {
        return GOOGLE_AUTHENTICATOR.authorize(secret, code, time.getTime());
    }

    /**
     * 验证码效验(指定时间)
     *
     * @param secret 密钥
     * @param code   验证码
     * @param time   时间
     * @return 效验结果
     */
    public boolean authorize(String secret, int code, long time) {
        return GOOGLE_AUTHENTICATOR.authorize(secret, code, time);
    }


    /**
     * 验证码效验
     *
     * @param user 用户
     * @param code 验证码
     * @return 效验结果
     * @throws MsToolsException 异常
     */
    public boolean authorizeByUser(String user, int code) throws MsToolsException {
        checkImpl();
        return GOOGLE_AUTHENTICATOR.authorizeUser(user, code);
    }

    /**
     * 验证码效验(指定时间)
     *
     * @param user 用户
     * @param code 验证码
     * @param time 时间
     * @return 效验结果
     * @throws MsToolsException 异常
     */
    public boolean authorizeByUser(String user, int code, Date time) throws MsToolsException {
        checkImpl();
        return GOOGLE_AUTHENTICATOR.authorizeUser(user, code, time.getTime());
    }

    /**
     * 验证码效验(指定时间)
     *
     * @param user 用户
     * @param code 验证码
     * @param time 时间
     * @return 效验结果
     * @throws MsToolsException 异常
     */
    public boolean authorizeByUser(String user, int code, long time) throws MsToolsException {
        checkImpl();
        return GOOGLE_AUTHENTICATOR.authorizeUser(user, code, time);
    }

    /**
     * 更换存储器
     *
     * @param iCredentialRepository 新存储器
     * @throws MsToolsException 禁用无法开启 使用buildAuthorize() 重新构建当前对象
     */
    public void updateICredentialRepository(ICredentialRepository iCredentialRepository) throws MsToolsException {
        checkImpl();
        GOOGLE_AUTHENTICATOR.setCredentialRepository(iCredentialRepository);
    }

    /**
     * 重新构建当前对象
     *
     * @param iCredentialRepository 存储器
     */
    public void buildAuthorize(ICredentialRepository iCredentialRepository) {
        if (iCredentialRepository == null) {
            throw new MsToolsRuntimeException("iCredentialRepository cannot be null");
        }
        GOOGLE_AUTHENTICATOR = new GoogleAuthenticator();
        GOOGLE_AUTHENTICATOR.setCredentialRepository(iCredentialRepository);
    }

    /**
     * 二维码参数
     *
     * @param issuer  产品名称
     * @param account 账户名称
     * @param secret  密钥
     * @return 二维码主要参数
     */
    public String getQRCodeParameter(String issuer, String account, String secret) {
        GoogleAuthenticatorKey key = GOOGLE_AUTHENTICATOR.parseGoogleAuthenticatorKey(secret);
        return GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(issuer, account, key);
    }

    /**
     * 获取二维码链接
     * https://api.qrserver.com/v1/create-qr-code 提供服务
     *
     * @param issuer  产品名称
     * @param account 账户名称
     * @param secret  密钥
     * @return 二维码链接
     */
    public String getQRCodeUrl(String issuer, String account, String secret) {
        return getQRCodeUrl(issuer, account, secret, null);
    }

    /**
     * 获取二维码链接
     * https://api.qrserver.com/v1/create-qr-code 提供服务
     *
     * @param issuer  产品名称
     * @param account 账户名称
     * @param secret  密钥
     * @param size    生成图片大小 10 - 1000
     *                null 使用默认值 200 * 200
     * @return 二维码链接
     */
    public String getQRCodeUrl(String issuer, String account, String secret, Integer size) {
        GoogleAuthenticatorKey key = GOOGLE_AUTHENTICATOR.parseGoogleAuthenticatorKey(secret);
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(issuer, account, key, size);
    }

}
