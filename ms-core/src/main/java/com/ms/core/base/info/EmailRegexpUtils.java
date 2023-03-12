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
import com.ms.core.base.enums.regular.RegexpEnum;
import com.ms.core.base.unit.Symbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱验证工具类
 *
 * @author 萌森 Ms
 */
public class EmailRegexpUtils {
    /**
     * 正则表达式：验证邮箱
     *
     * @param str 待验证的邮箱
     * @return 是否为邮箱 boolean
     */
    public static boolean isEmail(String str) {
        str = StringUtils.replaceBlank(str);
        // 判断字符串是否存在字符@
        if (!str.contains(Symbol.RATIO)) {
            return false;
        }
        Pattern r = Pattern.compile(RegexpEnum.REGEX_EMAIL.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
