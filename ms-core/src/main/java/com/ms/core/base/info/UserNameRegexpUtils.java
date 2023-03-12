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
 * 用户名效验
 *
 * @author qyg2297248353
 */
public class UserNameRegexpUtils {

    /**
     * 正则表达式：验证用户名
     *
     * @param str 待验证的用户名
     * @return 是否为用户名 boolean
     */
    public static boolean isUserName(String str) {
        return isUserName(str, 8, 25);
    }

    /**
     * 正则表达式：验证用户名
     *
     * @param str 待验证的用户名
     * @param min 最小长度
     * @param max 最大长度
     * @return 是否为用户名 boolean
     */
    public static boolean isUserName(String str, Integer min, Integer max) {
        min--;
        max--;
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(usernameCharm(min, max));
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 正则表达式： 验证用户名
     *
     * @param min 最短
     * @param max 最长
     * @return 正则表达式
     */
    private static String usernameCharm(int min, int max) {
        if (min < 0) {
            min = 1;
        }
        if (min >= max) {
            return "^[a-zA-Z]\\w{" + min + "}$";
        }
        return "^[a-zA-Z]\\w{" + min + "," + max + "}$";
    }
}
