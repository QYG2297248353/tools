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

package core.base.info;

import core.base.basic.StringUtils;
import core.base.info.enums.PasswordRandomEnum;
import core.base.info.enums.PasswordRulesEnum;

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
     */
    public static String randomPassword(PasswordRandomEnum random) {

    }
}
