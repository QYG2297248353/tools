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

package com.ms.tools.core.base.info;

import com.ms.core.base.basic.StringUtils;
import com.ms.core.base.info.enums.PasswordRandomEnum;
import com.ms.core.base.info.enums.PasswordRulesEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 密码效验
 *
 * @author qyg2297248353
 */
public class PasswordUtils {

    /**
     * 正则表达式：密码强度效验
     *
     * @param str   密码
     * @param rules 验证规则
     * @return 是否符合规则
     */
    public static boolean allowPasswordRule(String str, PasswordRulesEnum rules) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(rules.getRegex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 随机密码生成
     *
     * @param random 随机类型
     * @return 随机密码
     */
    public static String randomPassword(PasswordRandomEnum random) {
        return RandomUtils.randomPassword(random, random.includeNumber(), random.includeSpecialChar());
    }

    /**
     * 随机密码生成
     *
     * @param random             随机类型
     * @param includeNumber      是否包含数字
     * @param includeSpecialChar 是否包含特殊字符
     * @return 随机密码
     */
    public static String randomPassword(PasswordRandomEnum random, boolean includeNumber, boolean includeSpecialChar) {
        return RandomUtils.randomPassword(random, includeNumber, includeSpecialChar);
    }

    /**
     * 随机密码生成
     *
     * @param character          字符集
     * @param includeNumber      是否包含数字
     * @param includeSpecialChar 是否包含特殊字符
     * @param length             密码长度
     * @return 随机密码
     */
    public static String randomPassword(String character, boolean includeNumber, boolean includeSpecialChar, int length) {
        return RandomUtils.randomPassword(character, includeNumber, includeSpecialChar, length);
    }


}
