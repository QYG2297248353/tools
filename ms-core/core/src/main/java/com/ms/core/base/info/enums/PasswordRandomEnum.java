package com.ms.core.base.info.enums;


import com.ms.core.base.info.RandomUtils;

/**
 * 随机密码生成枚举
 * 1. 密码生成规则
 * 2. 密码生成长度
 * 3. 密码生成正则表达式
 * 4. 密码生成错误提示
 *
 * @author ms2297248353
 */
public enum PasswordRandomEnum {
    RANDOM_LEVEL_LOW(Grade.LOW, Grade.LOW_LENGTH_MIN, Grade.LOW_LENGTH_MAX, Grade.LOW_CHARACTER, "数字和字母，长度8-16位"),
    RANDOM_LEVEL_MEDIUM(Grade.MEDIUM, Grade.MEDIUM_LENGTH_MIN, Grade.MEDIUM_LENGTH_MAX, Grade.MEDIUM_CHARACTER, "数字、大小写字母，长度8-22位"),
    RANDOM_LEVEL_HIGH(Grade.HIGH, Grade.HIGH_LENGTH_MIN, Grade.HIGH_LENGTH_MAX, Grade.HIGH_CHARACTER, "数字、大小写字母、特殊字符，长度8-32位"),
    RANDOM_LEVEL_CUSTOM(Grade.CUSTOM, Grade.CUSTOM_LENGTH_MIN, Grade.CUSTOM_LENGTH_MAX, Grade.CUSTOM_CHARACTER, "自定义");

    private Grade grade;
    private Integer lengthMin;
    private Integer lengthMax;
    private String character;
    private String mess;

    PasswordRandomEnum(Grade grade, Integer lowLengthMin, Integer lowLengthMax, String character, String mess) {
        this.grade = grade;
        lengthMin = lowLengthMin;
        lengthMax = lowLengthMax;
        this.character = character;
        this.mess = mess;
    }

    public static PasswordRandomEnum getEnumByLevel(Integer level) {
        for (PasswordRandomEnum randomEnum : PasswordRandomEnum.values()) {
            if (randomEnum.getGrade().getLevel().equals(level)) {
                return randomEnum;
            }
        }
        return null;
    }

    /**
     * 设置自定义密码生成规则
     *
     * @param character 字符集
     * @param lengthMin 最小长度
     * @param lengthMax 最大长度
     */
    PasswordRandomEnum setCustom(String character, Integer lengthMin, Integer lengthMax) {
        if (this != RANDOM_LEVEL_CUSTOM) {
            throw new RuntimeException("只有自定义密码生成规则才能设置");
        }
        if (lengthMin > lengthMax) {
            throw new RuntimeException("最小长度不能大于最大长度");
        }
        this.character = character;
        this.lengthMin = lengthMin;
        this.lengthMax = lengthMax;
        return this;
    }

    /**
     * 设置密码生成长度
     *
     * @param lengthMin 最小长度
     * @param lengthMax 最大长度
     */
    PasswordRandomEnum setLength(Integer lengthMin, Integer lengthMax) {
        if (this == RANDOM_LEVEL_CUSTOM) {
            throw new RuntimeException("自定义密码生成规则不允许设置长度");
        }
        if (lengthMin > lengthMax) {
            throw new RuntimeException("最小长度不能大于最大长度");
        }
        this.lengthMin = lengthMin;
        this.lengthMax = lengthMax;
        return this;
    }

    /**
     * 生成随机密码
     *
     * @return 随机密码
     */
    public String generate() {
        if (this == RANDOM_LEVEL_CUSTOM && (lengthMin == null || lengthMax == null || character == null)) {
            throw new RuntimeException("自定义密码生成规则未设置");
        }
        return RandomUtils.randomPassword(this, includeNumber(), includeSpecialChar());
    }

    public Grade getGrade() {
        return grade;
    }

    public Integer getLengthMin() {
        return lengthMin;
    }

    public Integer getLengthMax() {
        return lengthMax;
    }

    public String getCharacter() {
        return character;
    }

    public String getMess() {
        return mess;
    }

    public boolean includeNumber() {
        switch (this) {
            case RANDOM_LEVEL_LOW:
            case RANDOM_LEVEL_MEDIUM:
            case RANDOM_LEVEL_HIGH:
                return true;
            case RANDOM_LEVEL_CUSTOM:
                return character.contains(Grade.CHARACTER_NUMBER);
            default:
                return false;
        }
    }

    public boolean includeSpecialChar() {
        switch (this) {
            case RANDOM_LEVEL_HIGH:
                return true;
            case RANDOM_LEVEL_CUSTOM:
                return character.contains(Grade.CHARACTER_SPECIAL);
            case RANDOM_LEVEL_LOW:
            case RANDOM_LEVEL_MEDIUM:
            default:
                return false;
        }
    }

    public enum Grade {
        LOW(1),
        MEDIUM(2),
        HIGH(3),
        CUSTOM(0);

        public static final Integer CUSTOM_LENGTH_MIN = null;
        public static final Integer CUSTOM_LENGTH_MAX = null;
        public static final String CUSTOM_CHARACTER = null;
        public static Integer LOW_LENGTH_MIN = 8;
        public static Integer LOW_LENGTH_MAX = 16;

        public static Integer MEDIUM_LENGTH_MIN = 8;
        public static Integer MEDIUM_LENGTH_MAX = 22;

        public static Integer HIGH_LENGTH_MIN = 8;
        public static Integer HIGH_LENGTH_MAX = 32;
        public static String CHARACTER_NUMBER = "0123456789";
        public static String CHARACTER_LOW = "abcdefghijklmnopqrstuvwxyz";
        public static String CHARACTER_HIGH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static String CHARACTER_SPECIAL = "!@#$%^&*()_+|~-=\\`{}[]:\";'<>?,./";
        public static String LOW_CHARACTER = CHARACTER_NUMBER + CHARACTER_LOW;
        public static String MEDIUM_CHARACTER = CHARACTER_NUMBER + CHARACTER_LOW + CHARACTER_HIGH;
        public static String HIGH_CHARACTER = CHARACTER_NUMBER + CHARACTER_LOW + CHARACTER_HIGH + CHARACTER_SPECIAL;
        Integer level;

        Grade(Integer level) {
            this.level = level;
        }

        public Integer getLevel() {
            return level;
        }
    }
}
