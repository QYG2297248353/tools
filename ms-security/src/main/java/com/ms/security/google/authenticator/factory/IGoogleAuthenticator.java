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

package com.ms.security.google.authenticator.factory;

/**
 * Google Authenticator library interface.
 */
@SuppressWarnings("UnusedDeclaration")
public interface IGoogleAuthenticator {
    /**
     * 此方法生成一组新的凭证，包括:
     * <ol>
     * <li>密钥</li>
     * <li>验证代码</li>
     * <li>临时代码列表</li>
     * </ol>
     * <p>
     * The user must register this secret on their device.
     *
     * @return secret key
     */
    GoogleAuthenticatorKey createCredentials();

    /**
     * 此方法生成一组新的凭据，调用
     * <code>#createCredentials</code> method with no arguments. The generated
     * credentials are then saved using the configured
     * <code>#ICredentialRepository</code> service.
     * <p>
     * 用户必须在他们的设备上注册这个服务。
     *
     * @param userName the user name.
     * @return secret key
     */
    GoogleAuthenticatorKey createCredentials(String userName);

    /**
     * 此方法生成当前的TOTP密码。
     *
     * @param secret the encoded secret key.
     * @return the current TOTP password.
     * @since 1.1.0
     */
    int getTotpPassword(String secret);

    /**
     * 此方法在指定时间生成TOTP密码。
     *
     * @param secret The encoded secret key.
     * @param time   The time to use to calculate the password.
     * @return the TOTP password at the specified time.
     * @since 1.1.0
     */
    int getTotpPassword(String secret, long time);

    /**
     * 此方法生成当前的TOTP密码。
     *
     * @param userName The user whose password must be created.
     * @return the current TOTP password.
     * @since 1.1.0
     */
    int getTotpPasswordOfUser(String userName);

    /**
     * 此方法在指定时间生成TOTP密码
     *
     * @param userName The user whose password must be created.
     * @param time     The time to use to calculate the password.
     * @return the TOTP password at the specified time.
     * @since 1.1.0
     */
    int getTotpPasswordOfUser(String userName, long time);

    /**
     * 使用当前时间对照密钥检查验证码
     *
     * @param secret           the encoded secret key.
     * @param verificationCode the verification code.
     * @return <code>true</code> if the validation code is valid,
     * <code>false</code> otherwise.
     * @throws GoogleAuthenticatorException if a failure occurs during the
     *                                      calculation of the validation code.
     *                                      The only failures that should occur
     *                                      are related with the cryptographic
     *                                      functions provided by the JCE.
     * @see #authorize(String, int, long)
     */
    boolean authorize(String secret, int verificationCode);

    /**
     * 使用指定的时间对照密钥检查验证码。
     * 该算法还检查一个时间窗口，该时间窗口的大小由
     * {@code windowSize} property of this class.
     * <p>
     * 间隔大小使用RFC 6238推荐的默认值30秒。
     *
     * @param secret           The encoded secret key.
     * @param verificationCode The verification code.
     * @param time             The time to use to calculate the TOTP password.
     * @return {@code true} if the validation code is valid, {@code false}
     * otherwise.
     * @throws GoogleAuthenticatorException if a failure occurs during the
     *                                      calculation of the validation code.
     *                                      The only failures that should occur
     *                                      are related with the cryptographic
     *                                      functions provided by the JCE.
     * @since 0.6.0
     */
    boolean authorize(String secret, int verificationCode, long time);

    /**
     * 此方法验证指定用户的验证码，该用户的私钥是使用当前时间从配置的凭据存储库中检索到的。
     * 此方法将验证委托给
     * {@link #authorizeUser(String, int, long)}.
     *
     * @param userName         要验证其验证码的用户。
     * @param verificationCode The validation code.
     * @return <code>true</code> if the validation code is valid,
     * <code>false</code> otherwise.
     * @throws GoogleAuthenticatorException if an unexpected error occurs.
     * @see #authorize(String, int)
     */
    boolean authorizeUser(String userName, int verificationCode);

    /**
     * 此方法验证指定用户的验证代码，该用户的私钥是从配置的凭据存储库中检索到的。
     * 此方法将验证委托给
     * {@link #authorize(String, int, long)} method.
     *
     * @param userName         The user whose verification code is to be
     *                         validated.
     * @param verificationCode The validation code.
     * @param time             The time to use to calculate the TOTP password.
     * @return <code>true</code> if the validation code is valid,
     * <code>false</code> otherwise.
     * @throws GoogleAuthenticatorException if an unexpected error occurs.
     * @see #authorize(String, int)
     * @since 0.6.0
     */
    boolean authorizeUser(String userName, int verificationCode, long time);

    /**
     * This method returns the credential repository used by this instance, or
     * {@code null} if none is set or none can be found using the ServiceLoader
     * API.
     *
     * @return the credential repository used by this instance.
     * @since 1.0.0
     */
    ICredentialRepository getCredentialRepository();

    /**
     * This method sets the credential repository used by this instance.  If
     * {@code null} is passed to this method, no credential repository will be
     * used, nor discovered using the ServiceLoader API.
     *
     * @param repository The credential repository to use, or {@code null} to
     *                   disable this feature.
     * @since 1.0.0
     */
    void setCredentialRepository(ICredentialRepository repository);
}
