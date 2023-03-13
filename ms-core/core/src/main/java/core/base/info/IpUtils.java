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
import core.base.enums.regular.RegexpEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ip效验工具类
 *
 * @author 萌森 Ms
 */
public class IpUtils {
    private IpUtils() {
    }

    /**
     * 判断是否是IP地址
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isIp(String str) {
        str = StringUtils.replaceBlank(str);
        Pattern r = Pattern.compile(RegexpEnum.REGEX_IP_ADD.regex());
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否是IPv4地址
     * 排除局域网
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isIpv4(String str) {
        str = StringUtils.replaceBlank(str);
        if (isIp(str)) {
            Pattern r = Pattern.compile(RegexpEnum.REGEX_LAN_IP_ADD.regex());
            Matcher m = r.matcher(str);
            return !m.matches();
        }
        return false;
    }
}
