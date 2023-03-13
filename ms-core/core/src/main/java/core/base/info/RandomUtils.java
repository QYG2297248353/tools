package core.base.info;

import core.base.info.enums.PasswordRandomEnum;

import java.security.SecureRandom;

public class RandomUtils {
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
}
