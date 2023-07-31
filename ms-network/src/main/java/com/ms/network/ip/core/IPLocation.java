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

package com.ms.network.ip.core;

/**
 * 解析实体
 *
 * @author ms2297248353
 */
public class IPLocation {
    /**
     * 国家
     */
    private String country;
    /**
     * 区域 - 省份 + 城市
     */
    private String area;

    public IPLocation() {
        country = area = "";
    }

    public synchronized IPLocation getCopy() {
        IPLocation ret = new IPLocation();
        ret.country = country;
        ret.area = area;
        return ret;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        String city = "";
        if (country != null) {
            String[] array = country.split("省");
            if (array != null && array.length > 1) {
                city = array[1];
            } else {
                city = country;
            }
            if (city.length() > 3) {
                city.replace("内蒙古", "");
            }
        }
        return city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        // 如果为局域网，纯真IP地址库的地区会显示CZ88.NET,这里把它去掉
        if ("CZ88.NET".equals(area.trim())) {
            this.area = "本机或本网络";
        } else {
            this.area = area;
        }
    }

    @Override
    public String toString() {
        return "IPLocation{" +
                "country='" + country + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}