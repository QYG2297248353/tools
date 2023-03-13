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
import core.base.basic.Strings;
import core.base.enums.regular.RegexpEnum;
import core.base.info.enums.EmailManufacturerEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱验证工具类
 *
 * @author 萌森 Ms
 */
public class EmailUtils {

    /**
     * 正则表达式：验证邮箱
     *
     * @param str 待验证的邮箱
     * @return 是否为邮箱 boolean
     */
    public static boolean isEmail(String str) {
        str = StringUtils.replaceBlank(str);
        // 判断字符串是否存在字符@
        if (!str.contains(Strings.AT)) {
            return false;
        }
        Pattern r = Pattern.compile(RegexpEnum.REGEX_EMAIL.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 根据邮箱获取邮箱信息
     *
     * @param email 邮箱
     * @return 邮箱信息
     */
    public static EmailManufacturerEnum getEmailManufacturer(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        }
        String[] split = email.split(Strings.AT);
        if (split.length != 2) {
            return null;
        }
        String emailSuffix = split[1];
        return EmailManufacturerEnum.getEmailManufacturer(emailSuffix);
    }
}
