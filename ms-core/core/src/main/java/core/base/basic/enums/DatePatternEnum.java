package core.base.basic.enums;

public enum DatePatternEnum {
    /**
     * yyyy-MM-dd
     */
    DATE_PATTERN("yyyy-MM-dd"),
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    DATE_TIME_PATTERN("yyyy-MM-dd HH:mm:ss"),
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    DATE_TIME_MS_PATTERN("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * yyyyMMdd
     */
    DATE_PATTERN_NO_SPLIT("yyyyMMdd"),
    /**
     * yyyyMMddHHmmss
     */
    DATE_TIME_PATTERN_NO_SPLIT("yyyyMMddHHmmss"),
    /**
     * yyyyMMddHHmmssSSS
     */
    DATE_TIME_MS_PATTERN_NO_SPLIT("yyyyMMddHHmmssSSS"),

    /**
     * yyyy-MM-dd HH:mm
     */
    DATE_TIME_MINUTE_PATTERN("yyyy-MM-dd HH:mm"),
    /**
     * yyyyMMddHHmm
     */
    DATE_TIME_MINUTE_PATTERN_NO_SPLIT("yyyyMMddHHmm"),

    /**
     * HH:mm:ss
     */
    TIME_PATTERN("HH:mm:ss"),
    /**
     * HHmmss
     */
    TIME_PATTERN_NO_SPLIT("HHmmss"),

    /**
     * HH:mm
     */
    TIME_MINUTE_PATTERN("HH:mm"),
    /**
     * HHmm
     */
    TIME_MINUTE_PATTERN_NO_SPLIT("HHmm"),

    /**
     * yyyy-MM
     */
    MONTH_PATTERN("yyyy-MM"),
    /**
     * yyyyMM
     */
    MONTH_PATTERN_NO_SPLIT("yyyyMM"),

    /**
     * yyyy
     */
    YEAR_PATTERN("yyyy"),

    /**
     * yyyy年MM月dd日
     */
    DATE_PATTERN_CHINESE("yyyy年MM月dd日"),
    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    DATE_TIME_PATTERN_CHINESE("yyyy年MM月dd日 HH时mm分ss秒"),
    /**
     * yyyy年MM月dd日 HH时mm分
     */
    DATE_TIME_MINUTE_PATTERN_CHINESE("yyyy年MM月dd日 HH时mm分"),
    /**
     * MM月dd日
     */
    DATE_PATTERN_CHINESE_NO_YEAR("MM月dd日"),
    /**
     * MM月dd日 HH时mm分ss秒
     */
    DATE_TIME_PATTERN_CHINESE_NO_YEAR("MM月dd日 HH时mm分ss秒"),
    /**
     * MM月dd日 HH时mm分
     */
    DATE_TIME_MINUTE_PATTERN_CHINESE_NO_YEAR("MM月dd日 HH时mm分"),
    /**
     * yyyy年MM月
     */
    MONTH_PATTERN_CHINESE("yyyy年MM月"),
    /**
     * yyyy年
     */
    YEAR_PATTERN_CHINESE("yyyy年"),
    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    DATE_TIME_PATTERN_UTC("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

    /**
     * 默认格式
     * yyyy-MM-dd HH:mm:ss
     */
    DEFAULT(DATE_TIME_PATTERN.getPattern());

    private String pattern;

    DatePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    /**
     * 根据pattern获取枚举
     *
     * @param pattern 日期格式
     * @return DatePatternEnum
     */
    public static DatePatternEnum getEnum(String pattern) {
        for (DatePatternEnum datePatternEnum : DatePatternEnum.values()) {
            if (datePatternEnum.getPattern().equals(pattern)) {
                return datePatternEnum;
            }
        }
        return DatePatternEnum.DEFAULT;
    }

    public String getPattern() {
        return pattern;
    }

    void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern;
    }
}
