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

package com.ms.tools.security.authenticator;

import com.ms.tools.security.authenticator.factory.GoogleAuthenticator;
import com.ms.tools.security.authenticator.factory.GoogleAuthenticatorKey;

import java.util.Date;

/**
 * 快捷工具
 *
 * @author ms2297248353
 */
public class GoogleAuthenticatorUtils {

    /**
     * 创建凭据
     *
     * @return 凭据
     */
    public static GoogleAuthenticatorKey createCredentials() {
        return new GoogleAuthenticator().createCredentials();
    }


    /**
     * 通过密钥获取当前验证码
     *
     * @param secret 密钥
     * @return 验证码
     */
    public static Integer getTotpCode(String secret) {
        return new GoogleAuthenticator().getTotpPassword(secret);
    }

    /**
     * 通过密钥获取指定时间验证码
     *
     * @param secret 密钥
     * @param date   时间
     * @return 验证码
     */
    public static Integer getTotpCode(String secret, Date date) {
        return getTotpCode(secret, date.getTime());
    }

    /**
     * 通过密钥获取指定时间验证码
     *
     * @param secret 密钥
     * @param time   时间
     * @return 验证码
     */
    public static Integer getTotpCode(String secret, long time) {
        return new GoogleAuthenticator().getTotpPassword(secret, time);
    }

    /**
     * 验证码效验
     *
     * @param secret 密钥
     * @param code   验证码
     * @return 效验结果
     */
    public boolean authorize(String secret, int code) {
        return new GoogleAuthenticator().authorize(secret, code);
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
        return new GoogleAuthenticator().authorize(secret, code, time.getTime());
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
        return new GoogleAuthenticator().authorize(secret, code, time);
    }

}
