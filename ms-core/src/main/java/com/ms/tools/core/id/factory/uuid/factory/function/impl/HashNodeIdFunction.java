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

package com.ms.tools.core.id.factory.uuid.factory.function.impl;

import com.ms.tools.core.id.factory.uuid.factory.function.NodeIdFunction;
import com.ms.tools.core.id.factory.uuid.util.MachineId;
import com.ms.tools.core.id.factory.uuid.util.internal.ByteUtil;

/**
 * Function that returns a hash of host name, MAC and IP.
 * <p>
 * The hash is calculated once during instantiation.
 *
 * @see NodeIdFunction
 * @see MachineId
 */
public final class HashNodeIdFunction implements NodeIdFunction {

    private final long nodeIdentifier;

    public HashNodeIdFunction() {
        final byte[] hash = MachineId.getMachineHash();
        final long number = ByteUtil.toNumber(hash, 0, 6);
        this.nodeIdentifier = NodeIdFunction.toMulticast(number);
    }

    @Override
    public long getAsLong() {
        return this.nodeIdentifier;
    }
}
