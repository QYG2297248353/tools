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
import com.ms.core.base.enums.regular.RegexpEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统一信用代码工具类
 *
 * @author 萌森 Ms
 */
public class UccRegexpUtils {

    public static final Integer UCC_UNIT_SIZE = 18;


    /**
     * 正则表达式：验证统一信用代码
     *
     * @param str 待验证的信用代码
     * @return 是否为统一信用代码 boolean
     */
    public static boolean isUcc(String str) {
        str = StringUtils.replaceBlank(str);
        if (str.length() != UCC_UNIT_SIZE) {
            return false;
        }
        Pattern r = Pattern.compile(RegexpEnum.REGEX_UCC.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
