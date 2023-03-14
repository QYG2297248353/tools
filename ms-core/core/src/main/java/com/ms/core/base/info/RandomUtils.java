package com.ms.core.base.info;

import com.ms.core.base.info.enums.PasswordRandomEnum;
import com.sun.istack.internal.NotNull;

import java.security.SecureRandom;

/**
 * @author ms
 */
public class RandomUtils {

    /**
     * 随机生成验证码
     * 0-9
     *
     * @param length 长度
     * @return 随机验证码
     */
    public static String randomCode(int length) {
        String character = PasswordRandomEnum.Grade.CHARACTER_NUMBER;
        return getSecureRandom(length, character);
    }

    /**
     * 随机生验证码
     * 字母
     *
     * @param length 长度
     * @return 随机验证码
     */
    public static String randomCodeLetter(int length) {
        String character = PasswordRandomEnum.Grade.CHARACTER_LOW;
        return getSecureRandom(length, character);
    }

    /**
     * 随机生验证码
     * 大写字母
     *
     * @param length 长度
     * @return 随机验证码
     */
    public static String randomCodeLetterUpper(int length) {
        String character = PasswordRandomEnum.Grade.CHARACTER_HIGH;
        return getSecureRandom(length, character);
    }


    @NotNull
    private static String getSecureRandom(int length, String character) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(character.length());
            sb.append(character.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 随机生成
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机
     */
    public static int randomInt(int min, int max) {
        return min + new SecureRandom().nextInt(max - min + 1);
    }

    /**
     * 随机生成
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机
     */
    public static long randomLong(long min, long max) {
        return min + (long) (new SecureRandom().nextDouble() * (max - min + 1));
    }

    /**
     * 随机生成
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机
     */
    public static double randomDouble(double min, double max) {
        return min + new SecureRandom().nextDouble() * (max - min + 1);
    }

    /**
     * 随机生成
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机
     */
    public static float randomFloat(float min, float max) {
        return min + new SecureRandom().nextFloat() * (max - min + 1);
    }

    /**
     * 随机生成
     *
     * @param length 长度
     * @return 随机
     */
    public static String randomString(int length) {
        String character = PasswordRandomEnum.Grade.HIGH_CHARACTER;
        return getSecureRandom(length, character);
    }

    /**
     * 随机生成
     *
     * @param length    长度
     * @param character 字符集
     * @return 随机
     */
    public static String randomString(int length, String character) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(character.length());
            sb.append(character.charAt(index));
        }
        randomStr(sb);
        return sb.toString();
    }

    public static void randomStr(StringBuilder str) {
        SecureRandom random = new SecureRandom();
        // Shuffle the password characters
        for (int i = str.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = str.charAt(i);
            str.setCharAt(i, str.charAt(index));
            str.setCharAt(index, temp);
        }
    }


    /**
     * 生成随机密码
     *
     * @param passwordRandomEnum 密码生成规则
     * @param includeNumber      是否包含数字
     * @param includeSpecialChar 是否包含特殊字符
     * @return 随机密码
     */
    public static String randomPassword(PasswordRandomEnum passwordRandomEnum, boolean includeNumber, boolean includeSpecialChar) {
        Integer lengthMin = passwordRandomEnum.getLengthMin();
        Integer lengthMax = passwordRandomEnum.getLengthMax();
        // 长度范围在min和max之间
        int length = lengthMin + new SecureRandom().nextInt(lengthMax - lengthMin + 1);

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        // Add at least one number if required
        if (includeNumber) {
            String number = PasswordRandomEnum.Grade.CHARACTER_NUMBER;
            int numberIndex = random.nextInt(number.length());
            sb.append(number.charAt(numberIndex));
            length--;
        }

        // Add at least one special character if required
        if (includeSpecialChar) {
            String special = PasswordRandomEnum.Grade.CHARACTER_SPECIAL;
            int specialCharIndex = random.nextInt(special.length());
            sb.append(special.charAt(specialCharIndex));
            length--;
        }

        // Add remaining characters
        for (int i = 0; i < length; i++) {
            String character = passwordRandomEnum.getCharacter();
            int index = random.nextInt(character.length());
            sb.append(character.charAt(index));
        }

        // Shuffle the password characters
        for (int i = sb.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(index));
            sb.setCharAt(index, temp);
        }

        return sb.toString();
    }

    /**
     * 生成随机密码
     *
     * @param character          密码生成字符集
     * @param includeNumber      是否包含数字
     * @param includeSpecialChar 是否包含特殊字符
     * @param length             密码长度
     * @return 随机密码
     */
    public static String randomPassword(String character, boolean includeNumber, boolean includeSpecialChar, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        // Add at least one number if required
        if (includeNumber) {
            String number = PasswordRandomEnum.Grade.CHARACTER_NUMBER;
            int numberIndex = random.nextInt(number.length());
            sb.append(number.charAt(numberIndex));
            length--;
        }

        // Add at least one special character if required
        if (includeSpecialChar) {
            String special = PasswordRandomEnum.Grade.CHARACTER_SPECIAL;
            int specialCharIndex = random.nextInt(special.length());
            sb.append(special.charAt(specialCharIndex));
            length--;
        }

        // Add remaining characters
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(character.length());
            sb.append(character.charAt(index));
        }

        // Shuffle the password characters
        for (int i = sb.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(index));
            sb.setCharAt(index, temp);
        }

        return sb.toString();
    }
}
