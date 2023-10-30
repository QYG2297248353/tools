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

package com.ms.tools.core.enums.random;

/**
 * @author qyg2297248353
 */
public enum RandomTypeEnum {
    /**
     * 数字验证码
     */
    RANDOM_CODE(RandomTypeConstant.RANDOM_CODE),
    /**
     * 小写字母验证码
     */
    LOWERCASE_LETTERS(RandomTypeConstant.LOWERCASE_LETTERS),
    /**
     * 大写字母验证码
     */
    CAPITAL_LETTERS(RandomTypeConstant.CAPITAL_LETTERS),
    /**
     * 数字 + 小写字母 验证码
     */
    RANDOM_LETTERS(RandomTypeConstant.RANDOM_LETTERS),
    /**
     * 数字 + 大写字母 验证码
     */
    RANDOM_CAPITAL(RandomTypeConstant.RANDOM_CAPITAL),
    /**
     * 小写字母 + 大写字母 验证码
     */
    LETTERS_CAPITAL(RandomTypeConstant.LETTERS_CAPITAL),
    /**
     * 数字 + 小写字母 + 大写字母 验证码
     */
    RANDOM_LETTERS_CAPITAL(RandomTypeConstant.RANDOM_LETTERS_CAPITAL);

    private String code;

    RandomTypeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    static class RandomTypeConstant {
        /**
         * 数字验证码
         */
        public static final String RANDOM_CODE = "0123456789";
        /**
         * 小写字母验证码
         */
        public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        /**
         * 大写字母验证码
         */
        public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        /**
         * 数字 + 小写字母 验证码
         */
        public static final String RANDOM_LETTERS = RANDOM_CODE + LOWERCASE_LETTERS;
        /**
         * 数字 + 大写字母 验证码
         */
        public static final String RANDOM_CAPITAL = RANDOM_CODE + CAPITAL_LETTERS;
        /**
         * 小写字母 + 大写字母 验证码
         */
        public static final String LETTERS_CAPITAL = LOWERCASE_LETTERS + CAPITAL_LETTERS;
        /**
         * 数字 + 小写字母 + 大写字母 验证码
         */
        public static final String RANDOM_LETTERS_CAPITAL = RANDOM_CODE + LOWERCASE_LETTERS + CAPITAL_LETTERS;
    }

}
