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

package com.ms.id.factory.uuid.util.internal;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 主机名、MAC 和 IP 的实用程序类。
 */
public final class NetworkUtil {

    private static String hostname;
    private static String mac;
    private static String ip;

    private NetworkUtil() {
    }

    /**
     * 如果找到，则返回主机名。
     * HOSTNAME 搜索顺序：
     * 尝试在 LINUX 环境中查找 HOSTNAME 变量；
     * 尝试在WINDOWS环境下查找COMPUTERNAME变量；
     * 尝试通过调用InetAddress.getLocalHost().getHostName()找到主机名（昂贵的方式）；
     * 如果没有找到主机名，则返回null 。
     *
     * @return 主机名
     */
    public static synchronized String hostname() {

        if (hostname != null) {
            return hostname;
        }

        // try to find HOSTNAME on LINUX
        hostname = System.getenv("HOSTNAME");
        if (hostname != null && !hostname.isEmpty()) {
            return hostname;
        }

        // try to find COMPUTERNAME on WINDOWS
        hostname = System.getenv("COMPUTERNAME");
        if (hostname != null && !hostname.isEmpty()) {
            return hostname;
        }

        try {
            // try to find HOSTNAME for the local host
            hostname = InetAddress.getLocalHost().getHostName();
            if (hostname != null && !hostname.isEmpty()) {
                return hostname;
            }
        } catch (UnknownHostException | NullPointerException e) {
            // do nothing
        }

        // not found
        return null;
    }

    /**
     * 如果找到，则返回 MAC 地址。
     * 输出格式：“00-00-00-00-00-00”（大写）
     *
     * @param nic 函数
     * @return MAC 地址
     */
    public static synchronized String mac(NetworkInterface nic) {

        if (mac != null) {
            return mac;
        }

        try {
            if (nic != null) {
                byte[] ha = nic.getHardwareAddress();
                String[] hex = new String[ha.length];
                for (int i = 0; i < ha.length; i++) {
                    hex[i] = String.format("%02X", ha[i]);
                }
                mac = String.join("-", hex);
                return mac;
            }
        } catch (SocketException | NullPointerException e) {
            // do nothing
        }

        // not found
        return null;
    }


    /**
     * 查询IP地址
     *
     * @param nic 函数
     * @return IP address
     */
    public static synchronized String ip(NetworkInterface nic) {

        if (ip != null) {
            return ip;
        }

        try {
            if (nic != null) {
                Enumeration<InetAddress> ips = nic.getInetAddresses();
                if (ips.hasMoreElements()) {
                    ip = ips.nextElement().getHostAddress();
                    return ip;
                }
            }
        } catch (NullPointerException e) {
            // do nothing
        }

        // not found
        return null;
    }

    /**
     * 返回包含主机名、MAC 和 IP 的字符串。
     * 输出格式：“主机名 11-11-11-11-11-11 222.222.222.222”
     *
     * @return 主机名、MAC 和 IP 的字符串
     */
    public static synchronized String getMachineString() {

        NetworkInterface nic = nic();

        String hostname = NetworkUtil.hostname();
        String mac = NetworkUtil.mac(nic);
        String ip = NetworkUtil.ip(nic);

        return String.join(" ", hostname, mac, ip);
    }

    /**
     * 返回一个网络接口。
     * 它尝试返回与主机名关联的网络接口。
     * 如果未找到该网络接口，它会尝试返回满足以下条件的第一个网络接口：
     * 它已启动并正在运行；
     * 它不是环回；
     * 它不是虚拟的；
     * 它有一个硬件地址。
     *
     * @return 网络接口
     */
    public static synchronized NetworkInterface nic() {

        NetworkInterface nic;

        try {

            InetAddress ip = null;
            NetworkInterface ni = null;
            Enumeration<NetworkInterface> enu = null;

            // try to find the network interface for the host name
            ip = InetAddress.getByName(hostname());
            ni = NetworkInterface.getByInetAddress(ip);
            if (acceptable(ni)) {
                nic = ni;
                return nic;
            }

            // try to find the first network interface
            enu = NetworkInterface.getNetworkInterfaces();
            while (enu.hasMoreElements()) {
                ni = enu.nextElement();
                if (acceptable(ni)) {
                    nic = ni;
                    return nic;
                }
            }

        } catch (UnknownHostException | SocketException | NullPointerException e) {
            // do nothing
        }

        // NIC not found
        return null;
    }

    /**
     * 检查网络接口是否可接受
     *
     * @param ni 网络接口
     * @return 如果可以接受，则为真
     */
    private static synchronized boolean acceptable(NetworkInterface ni) {
        try {
            if (ni != null && ni.isUp() && !ni.isLoopback() && !ni.isVirtual()) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null && mac.length == 6) {
                    return true;
                }
            }
        } catch (SocketException | NullPointerException e) {
            // do nothing
        }
        return false;
    }
}
