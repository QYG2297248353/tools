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

package com.ms.core.base.info;

import com.ms.core.base.basic.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 密码效验
 *
 * @author qyg2297248353
 */
public class PasswordRegexpUtils {

    /**
     * 正则表达式：验证用户名
     *
     * @param str 待验证的用户名
     * @return 是否为用户名 boolean
     */
    public static boolean isPassword(String str) {
        return isPassword(str, 8);
    }

    /**
     * 正则表达式：验证用户名
     *
     * @param str 待验证的用户名
     * @param min 最小长度
     * @return 是否为用户名 boolean
     */
    public static boolean isPassword(String str, Integer min) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(passwordCharm(min));
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 正则
     *
     * @param min 至少一个大写英文字母，一个小写英文字母，一个数字和一个特殊字符
     * @return 正则
     */
    private static String passwordCharm(int min) {
        return "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{" + min + ",}$";
    }
}
