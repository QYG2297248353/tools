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
 * 性别枚举
 * <p>
 * 参考标准：
 * <a href="https://std.samr.gov.cn/">全国标准信息公共服务平台</a>
 * <p>
 * 标准号：GB/T 2261.1-2003
 * <p>
 * 中文标准名称：<a href="https://openstd.samr.gov.cn/bzgk/gb/newGbInfo?hcno=0FC942D542BC6EE3C707B2647EF81CD8">个人基本信息分类与代码 第1部分:人的性别代码</a>
 * <p>
 * 英文标准名称：Classification and codesof basic personal information--Part 1: Codes for sexual distinction of human
 *
 * @author ms2297248353
 */
public enum SexEnum {
    // 标准中的性别代码
    /**
     * 未知
     */
    UNKNOWN(0, "未知", "unknown"),
    /**
     * 男性
     */
    MALE(1, "男", "male"),
    /**
     * 女性
     */
    FEMALE(2, "女", "female"),

    // 民间衍生的性别代码
    /**
     * 人妖
     * <p>
     * 指男性的外表，女性的内情，或女性的外表，男性的内情的人
     */
    HERMAPHRODITE(3, "人妖", "hermaphrodite"),
    /**
     * 双性人
     * <p>
     * 人妖的一种，指男性和女性两种性别的人，也称雌雄同体、雌雄双体、雌雄同体人、雌雄双体人、
     */
    INTERSEX(4, "双性人", "intersex"),
    /**
     * 变性人
     * <p>
     * 指男性变女性或女性变男性的人
     */
    TRANSSEXUAL(5, "变性人", "transsexual");

    private final int code;

    private final String name;

    private final String enName;

    SexEnum(int code, String name, String enName) {
        this.code = code;
        this.name = name;
        this.enName = enName;
    }

    public static SexEnum valueOf(int code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum;
            }
        }
        return SexEnum.UNKNOWN;
    }

    public static SexEnum valueOfName(String name) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getName().equals(name)) {
                return sexEnum;
            }
        }
        return SexEnum.UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEnName() {
        return enName;
    }


}
