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

package com.ms.id.factory.uuid.factory.function.impl;

import com.ms.id.factory.uuid.factory.function.NodeIdFunction;
import com.ms.id.factory.uuid.util.internal.NetworkUtil;

import java.net.NetworkInterface;
import java.net.SocketException;

import static com.ms.id.factory.uuid.util.internal.ByteUtil.toNumber;

/**
 * Function that returns a MAC address.
 * <p>
 * The MAC address is obtained once during instantiation.
 * <p>
 * If no MAC address is found, it returns a random multicast node identifier.
 *
 * @see NodeIdFunction
 */
public final class MacNodeIdFunction implements NodeIdFunction {

    private final long nodeIdentifier;

    public MacNodeIdFunction() {
        this.nodeIdentifier = getHardwareAddress();
    }

    @Override
    public long getAsLong() {
        return this.nodeIdentifier;
    }

    private long getHardwareAddress() {

        try {
            NetworkInterface nic = NetworkUtil.nic();
            if (nic != null) {
                return toNumber(nic.getHardwareAddress());
            }
        } catch (SocketException e) {
            // do nothing
        }

        return NodeIdFunction.getMulticastRandom();
    }
}
