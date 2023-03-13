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
import com.ms.core.base.basic.Strings;
import com.ms.core.base.enums.regular.RegexpEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电话号码效验工具类
 *
 * @author 萌森 Ms
 */
public class PhoneUtils {

    public static final Integer PHONE_NUMBER_UNIT_SIZE = 11;

    /**
     * 正则表达式：验证手机号
     *
     * @param str 待验证的手机号
     * @return 是否为手机号 boolean
     */
    public static boolean isChinaPhone(String str) {
        str = StringUtils.replaceBlank(str);
        if (str.startsWith(Strings.PLUS)) {
            str = str.substring(str.length() - PHONE_NUMBER_UNIT_SIZE);
        }
        if (str.length() < PHONE_NUMBER_UNIT_SIZE) {
            return false;
        }

        // 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
        // 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
        // 电信号段: 133,149,153,170,173,177,180,181,189
        Pattern r = Pattern.compile(RegexpEnum.REGEX_PHONE.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
