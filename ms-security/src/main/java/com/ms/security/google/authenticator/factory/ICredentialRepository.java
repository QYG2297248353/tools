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

import java.util.List;

/**
 * @author ms2297248353
 */
public interface ICredentialRepository {
    /**
     * 根据用户识别码获取用户指定私钥
     *
     * @param userName 用户识别码
     * @return 指定用户的私钥
     */
    String getSecretKey(String userName);

    /**
     * 接口：保存用户凭据
     * 关于恢复密钥,实现此方法时可根据参数,环境等生成恢复密钥提供用于用户恢复使用
     *
     * @param userName       用户识别码
     * @param secretKey      密钥
     * @param validationCode 当前验证代码
     * @param scratchCodes   临时代码列表(刮刮码) 备用验证码
     */
    void saveUserCredentials(String userName,
                             String secretKey,
                             int validationCode,
                             List<Integer> scratchCodes);
}
