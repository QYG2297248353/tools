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

package com.ms.core.base.datetime.enums;

import java.time.ZoneId;
import java.util.TimeZone;

public enum ZoneIdEnum {
    /**
     * 中国时区
     * <p>
     * 亚洲/上海
     */
    CHINA("Asia/Shanghai"),
    /**
     * 日本时区
     * <p>
     * 亚洲/东京
     */
    JAPAN("Asia/Tokyo"),
    /**
     * 韩国时区
     * <p>
     * 亚洲/首尔
     */
    KOREA("Asia/Seoul"),
    /**
     * 美国时区
     * <p>
     * 美国/纽约
     */
    USA("America/New_York"),
    /**
     * 美国洛杉矶时区
     * <p>
     * 美国/洛杉矶
     */
    USA_LA("America/Los_Angeles"),
    /**
     * 英国时区
     * <p>
     * 英国/伦敦
     */
    UK("Europe/London"),
    /**
     * 澳大利亚时区
     * <p>
     * 澳大利亚/悉尼
     */
    AUSTRALIA("Australia/Sydney"),
    /**
     * 加拿大时区
     * <p>
     * 加拿大/温哥华
     */
    CANADA("America/Vancouver"),
    /**
     * 德国时区
     * <p>
     * 德国/柏林
     */
    GERMANY("Europe/Berlin"),
    /**
     * 法国时区
     * <p>
     * 法国/巴黎
     */
    FRANCE("Europe/Paris"),
    /**
     * 西班牙时区
     * <p>
     * 西班牙/马德里
     */
    SPAIN("Europe/Madrid"),
    /**
     * 意大利时区
     * <p>
     * 意大利/罗马
     */
    ITALY("Europe/Rome"),
    /**
     * 荷兰时区
     * <p>
     * 荷兰/阿姆斯特丹
     */
    NETHERLANDS("Europe/Amsterdam"),
    /**
     * 瑞士时区
     * <p>
     * 瑞士/苏黎世
     */
    SWITZERLAND("Europe/Zurich"),
    /**
     * 瑞典时区
     * <p>
     * 瑞典/斯德哥尔摩
     */
    SWEDEN("Europe/Stockholm"),
    /**
     * 挪威时区
     * <p>
     * 挪威/奥斯陆
     */
    NORWAY("Europe/Oslo"),
    /**
     * 丹麦时区
     * <p>
     * 丹麦/哥本哈根
     */
    DENMARK("Europe/Copenhagen"),
    /**
     * 奥地利时区
     * <p>
     * 奥地利/维也纳
     */
    AUSTRIA("Europe/Vienna"),
    /**
     * 比利时时区
     * <p>
     * 比利时/布鲁塞尔
     */
    BELGIUM("Europe/Brussels"),
    /**
     * 爱尔兰时区
     * <p>
     * 爱尔兰/都柏林
     */
    IRELAND("Europe/Dublin"),
    /**
     * 希腊时区
     * <p>
     * 希腊/雅典
     */
    GREECE("Europe/Athens"),
    /**
     * 葡萄牙时区
     * <p>
     * 葡萄牙/里斯本
     */
    PORTUGAL("Europe/Lisbon"),
    /**
     * 匈牙利时区
     * <p>
     * 匈牙利/布达佩斯
     */
    HUNGARY("Europe/Budapest"),
    /**
     * 捷克时区
     * <p>
     * 捷克/布拉格
     */
    CZECH("Europe/Prague"),
    /**
     * 波兰时区
     * <p>
     * 波兰/华沙
     */
    POLAND("Europe/Warsaw"),
    /**
     * 罗马尼亚时区
     * <p>
     * 罗马尼亚/布加勒斯特
     */
    ROMANIA("Europe/Bucharest"),
    /**
     * 瑞典时区
     * <p>
     * 瑞典/斯德哥尔摩
     */
    SWEDEN2("Europe/Stockholm"),
    /**
     * 协调世界时 UTC
     */
    UTC("UTC+0"),
    /**
     * 东八区时区
     * <p>
     * 北京时间
     */
    UTC8("UTC+8"),
    /**
     * 格林威治时区
     */
    GMT("GMT"),
    /**
     * 东八区时区
     * <p>
     * 北京时间
     */
    GMT8("GMT+8"),

    /**
     * 默认时区
     * <p>
     * 默认时区为 GMT+8 北京时间
     */
    DEFAULT("GMT+8");

    private String zoneId;

    ZoneIdEnum(String zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * 获取时区枚举
     *
     * @param zone 时区
     * @return 时区枚举 {@link ZoneIdEnum} 查无此项时返回 {@link ZoneIdEnum#DEFAULT}
     */
    public static ZoneIdEnum getZoneEnum(String zone) {
        for (ZoneIdEnum zoneIdEnum : ZoneIdEnum.values()) {
            if (zoneIdEnum.getZoneId().equals(zone)) {
                return zoneIdEnum;
            }
        }
        return DEFAULT;
    }

    /**
     * 获取时区
     *
     * @return 时区
     */
    public String getZoneId() {
        return zoneId;
    }

    void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * 转为时区
     *
     * @return 时区
     */
    public ZoneId toZoneId() {
        return ZoneId.of(zoneId);
    }

    /**
     * 转为时区
     *
     * @return 时区
     */
    public TimeZone toTimeZone() {
        return TimeZone.getTimeZone(toZoneId());
    }
}
