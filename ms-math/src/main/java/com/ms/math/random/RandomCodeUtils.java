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

package com.ms.math.random;

import com.ms.core.base.enums.random.RandomTypeEnum;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机码生成工具
 *
 * @author qyg2297248353
 */
public class RandomCodeUtils {

    /**
     * 随机码
     *
     * @return 6位非安全线程纯数字验证码
     */
    public static String randomMathCode() {
        return randomMathCode(6);
    }

    /**
     * 随机码
     *
     * @param number 数量
     * @return 非安全线程纯数字验证码
     */
    public static String randomMathCode(int number) {
        int i = 1;
        for (int n = number; n > 1; n--) {
            i *= 10;
        }
        int code = (int) ((Math.random() * 9 + 1) * i);
        return String.valueOf(code);
    }

    /**
     * 随机码
     *
     * @return 6位纯数字验证码
     */
    public static String randomCode() {
        return randomCode(6);
    }

    /**
     * 随机码
     *
     * @param number 数量
     * @return 纯数字验证码
     */
    public static String randomCode(int number) {
        return randomCode(number, null);
    }

    /**
     * 随机码
     * 当种子一致时产生随机数结果相同
     *
     * @param number 数量
     * @param seed   种子
     * @return 纯数字验证码
     */
    public static String randomCode(int number, Long seed) {
        Random random = new Random();
        if (seed != null) {
            random.setSeed(seed);
        }
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < number; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 随机码
     *
     * @return 6位纯数字验证码
     */
    public static String randomThreadLocalCode() {
        return randomThreadLocalCode(6);
    }

    /**
     * 随机码
     *
     * @param number 数量
     * @return 纯数字验证码
     */
    public static String randomThreadLocalCode(int number) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < number; i++) {
            code.append(ThreadLocalRandom.current().nextInt(10));
        }
        return code.toString();
    }

    /**
     * 随机码
     * 安全的随机数
     *
     * @return 6位纯数字验证码
     */
    public static String randomSecureCode() {
        return randomSecureCode(6);
    }

    /**
     * 随机码
     * 安全的随机数
     *
     * @param number 数量
     * @return 纯数字验证码
     */
    public static String randomSecureCode(int number) {
        return secureCode(number, RandomTypeEnum.RANDOM_CODE);
    }

    /**
     * 字母随机码
     * 安全的随机数
     *
     * @param number 数量
     * @return 纯数字验证码
     */
    public static String randomSecureLetter(int number) {
        return secureCode(number, RandomTypeEnum.LETTERS_CAPITAL);
    }

    /**
     * 数字字母随机码
     * 安全的随机数
     *
     * @param number 数量
     * @return 纯数字验证码
     */
    public static String randomSecureLetterCode(int number) {
        return secureCode(number, RandomTypeEnum.RANDOM_LETTERS_CAPITAL);
    }

    /**
     * 随机码
     * 安全的随机数
     *
     * @param number 数量
     * @param type   类型
     * @return 纯数字验证码
     */
    public static String randomSecure(int number, RandomTypeEnum type) {
        return secureCode(number, type);
    }


    /**
     * 生成指定验证码
     *
     * @param number 数量
     * @param type   类型
     * @return 验证码
     */
    protected static String secureCode(int number, RandomTypeEnum type) {
        SecureRandom sr;
        try {
            // 获取高强度安全随机数生成器
            sr = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            // 获取普通的安全随机数生成器
            sr = new SecureRandom();
        }
        char[] code = new char[number];
        for (int i = 0; i < number; i++) {
            // 获取随机索引
            int result = sr.nextInt(type.code().length());
            // 根据索引找给定字符
            code[i] = type.code().charAt(result);
        }
        return new String(code);
    }
}
