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

package com.ms.tools.core.enums.baseinfo;

/**
 * 密码规则枚举
 * 1. 密码规则类型
 * 2. 密码规则验证正则表达式
 * 3. 密码规则验证错误提示
 *
 * @author ms2297248353
 */
public enum PasswordRulesEnum {
    PASSWORD_LEVEL_LOW(Grade.LOW, Grade.LOW.REGEX_LOW, "密码必须包含数字和字母，长度8-16位"),
    PASSWORD_LEVEL_MEDIUM(Grade.MEDIUM, Grade.MEDIUM.REGEX_MEDIUM, "密码必须包含大写字母、小写字母和数字，长度8-16位"),
    PASSWORD_LEVEL_HIGH(Grade.HIGH, Grade.MEDIUM.REGEX_HIGH, "密码必须包含大写字母、小写字母、数字和特殊字符，长度8-16位"),
    PASSWORD_LEVEL_LOW_MIN(Grade.LOW, Grade.LOW.REGEX_LOW_MIN, "密码必须包含数字和字母，长度8位以上"),
    PASSWORD_LEVEL_MEDIUM_MIN(Grade.MEDIUM, Grade.MEDIUM.REGEX_MEDIUM_MIN, "密码必须包含大写字母、小写字母和数字，长度8位以上"),
    PASSWORD_LEVEL_HIGH_MIN(Grade.HIGH, Grade.MEDIUM.REGEX_HIGH_MIN, "密码必须包含大写字母、小写字母、数字和特殊字符，长度8位以上");


    Grade level;
    String regex;
    String message;

    PasswordRulesEnum(Grade level, String regex, String message) {
        this.level = level;
        this.regex = regex;
        this.message = message;
    }

    public Grade getLevel() {
        return level;
    }

    public String getRegex() {
        return regex;
    }

    public String getMessage() {
        return message;
    }

    enum Grade {
        LOW(1),
        MEDIUM(2),
        HIGH(3);

        public String REGEX_LOW = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        public String REGEX_LOW_MIN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$";
        public String REGEX_MEDIUM = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,16}$";
        public String REGEX_MEDIUM_MIN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";

        public String REGEX_HIGH = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$";
        public String REGEX_HIGH_MIN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        Integer level;

        Grade(Integer level) {
            this.level = level;
        }

        public Integer getLevel() {
            return level;
        }
    }
}
