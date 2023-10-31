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

package com.ms.tools.security.authenticator.factory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * This class provides helper methods to create a QR code containing the
 * provided credential.  The generated QR code can be fed to the Google
 * Authenticator application so that it can configure itself with the data
 * contained therein.
 */
public final class GoogleAuthenticatorQRGenerator {
    /**
     * The format string to generate the Google Chart HTTP API call.
     */
    private static final String TOTP_URI_FORMAT =
            "https://api.qrserver.com/v1/create-qr-code/?data=%s&size=%dx%d&ecc=M&margin=10";

    /**
     * This method wraps the invocation of <code>URLEncoder##encode</code>
     * method using the "UTF-8" encoding.  This call also wraps the
     * <code>UnsupportedEncodingException</code> thrown by
     * <code>URLEncoder##encode</code> into a <code>RuntimeException</code>.
     * Such an exception should never be thrown.
     *
     * @param s The string to URL-encode.
     * @return the URL-encoded string.
     */
    private static String internalURLEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding is not supported by URLEncoder.", e);
        }
    }

    /**
     * The label is used to identify which account a key is associated with.
     * It contains an account name, which is a URI-encoded string, optionally
     * prefixed by an issuer string identifying the provider or service managing
     * that account.  This issuer prefix can be used to prevent collisions
     * between different accounts with different providers that might be
     * identified using the same account name, e.g. the user's email address.
     * The issuer prefix and account name should be separated by a literal or
     * url-encoded colon, and optional spaces may precede the account name.
     * Neither issuer nor account name may themselves contain a colon.
     * Represented in ABNF according to RFC 5234:
     * <p>
     * label = accountname / issuer (“:” / “%3A”) *”%20” accountname
     *
     * @see <a href="https://code.google.com/p/google-authenticator/wiki/KeyUriFormat">Google Authenticator - KeyUriFormat</a>
     */
    private static String formatLabel(String issuer, String accountName) {
        if (accountName == null || accountName.trim().length() == 0) {
            throw new IllegalArgumentException("Account name must not be empty.");
        }

        StringBuilder sb = new StringBuilder();

        if (issuer != null) {
            if (issuer.contains(":")) {
                throw new IllegalArgumentException("Issuer cannot contain the \':\' character.");
            }

            sb.append(issuer);
            sb.append(":");
        }

        sb.append(accountName);

        return sb.toString();
    }

    /**
     * 返回Google Chart API调用的URL，以生成要加载到Google Authenticator应用程序中的QR条形码。
     * 用户使用智能手机上的应用程序扫描此条形码或手动输入秘密。
     * <p>
     * 当前的实现支持以下功能:
     * <ul>
     * <li>标签，由可选的发行人和帐户名称组成。</li>
     * <li>密钥参数</li>
     * <li>发行人参数</li>
     * </ul>
     *
     * @param issuer      发行人名称。此参数不能包含冒号 (:) 字符。此参数可以为null。
     * @param accountName 帐户名称。此参数不应为null。
     * @param credentials 生成的凭据。此参数不应为null。
     * @param size        图片大小。此参数在10-1000之间,默认值在250px。
     * @return Google Chart API调用URL生成包含所提供信息的QR码。
     * @see <a href="https://code.google.com/p/google-authenticator/wiki/KeyUriFormat">Google Authenticator - KeyUriFormat</a>
     */
    public static String getOtpAuthURL(String issuer,
                                       String accountName,
                                       GoogleAuthenticatorKey credentials,
                                       Integer size) {
        if (size == null) {
            size = 250;
        }
        return String.format(
                TOTP_URI_FORMAT,
                internalURLEncode(getOtpAuthTotpURL(issuer, accountName, credentials)),
                size,
                size);
    }

    /**
     * 返回基本otpauth TOTP URI。此URI可能会通过电子邮件，QR码或其他方法发送给用户。
     * 使用安全传输，因为此URI包含秘密。
     * <p>
     * 当前的实现支持以下功能:
     * <ul>
     * <li>标签，由可选的发行人和帐户名称组成。</li>
     * <li>密钥参数</li>
     * <li>发行人参数</li>
     * </ul>
     *
     * @param issuer      发行人名称。此参数不能包含冒号 (:) 字符。此参数可以为null。
     * @param accountName 帐户名称。此参数不应为null。
     * @param credentials 生成的凭据。此参数不应为null。
     * @return 用于加载到客户端应用程序中的otpauth方案URI。
     * @see <a href="https://github.com/google/google-authenticator/wiki/Key-Uri-Format">Google Authenticator - KeyUriFormat</a>
     */
    public static String getOtpAuthTotpURL(String issuer,
                                           String accountName,
                                           GoogleAuthenticatorKey credentials) {
        URI builder = URI.create("otpauth://totp/")
                .resolve(formatLabel(issuer, accountName));
        builder = builder.resolve("?secret=" + credentials.getKey());


        if (issuer != null) {
            if (issuer.contains(":")) {
                throw new IllegalArgumentException("Issuer cannot contain the \':\' character.");
            }
            builder = builder.resolve("&issuer=" + internalURLEncode(issuer));
        }

        GoogleAuthenticatorConfig config = credentials.getConfig();
        builder = builder.resolve("&algorithm=" + getAlgorithmName(config.getHmacHashFunction()));
        builder = builder.resolve("&digits=" + config.getCodeDigits());
        builder = builder.resolve("&period=" + config.getTimeStepSizeInMillis() / 1000);
        return builder.toString();
    }

    private static String getAlgorithmName(HmacHashFunction hashFunction) {
        switch (hashFunction) {
            case HmacSHA1:
                return "SHA1";

            case HmacSHA256:
                return "SHA256";

            case HmacSHA512:
                return "SHA512";

            default:
                throw new IllegalArgumentException(String.format("Unknown algorithm %s", hashFunction));
        }
    }
}
