package com.ms.core.base.datetime.factory;


import com.ms.core.base.datetime.enums.ZoneIdEnum;

import java.time.ZoneId;

public class ZoneIdFactory {

    /**
     * 创建时区
     *
     * @return 时区
     */
    public ZoneId createChina() {
        return create(ZoneIdEnum.CHINA);
    }


    /**
     * 世界协调时间
     *
     * @return UTC时区 UTC
     */
    public ZoneId createUTC() {
        return create(ZoneIdEnum.UTC);
    }

    /**
     * 创建东八区时区
     * <p>
     * 北京时间
     *
     * @return 默认时区 UTC+8
     */
    public ZoneId createUTC8() {
        return create(ZoneIdEnum.UTC8);
    }

    /**
     * 格林威治时区
     *
     * @return 格林威治时区 GMT
     */
    public ZoneId createGMT() {
        return create(ZoneIdEnum.GMT);
    }

    /**
     * 创建东八区时区
     * <p>
     * 北京时间
     *
     * @return 默认时区 GMT+8
     */
    public ZoneId createGMT8() {
        return create(ZoneIdEnum.GMT8);
    }


    /**
     * 创建默认时区
     * <p>
     * 默认时区为 GMT+8 北京时间
     *
     * @return 默认时区 GMT+8
     */
    public ZoneId createDefault() {
        return create(ZoneIdEnum.DEFAULT);
    }


    /**
     * 创建指定时区
     *
     * @param zoneId 时区
     * @return 指定时区
     */
    public ZoneId create(String zoneId) {
        return ZoneId.of(zoneId);
    }

    /**
     * 创建指定时区
     *
     * @param zoneId 时区
     * @return 指定时区
     */
    public ZoneId create(ZoneIdEnum zoneId) {
        return ZoneId.of(zoneId.getZoneId());
    }

}
