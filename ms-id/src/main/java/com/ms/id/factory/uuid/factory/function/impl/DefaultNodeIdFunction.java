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

/**
 * Function that returns a final random multicast node identifier.
 * <p>
 * The random value is generated once during instantiation.
 *
 * @see NodeIdFunction
 */
public final class DefaultNodeIdFunction implements NodeIdFunction {

    private final long nodeIdentifier;

    public DefaultNodeIdFunction() {
        nodeIdentifier = NodeIdFunction.getMulticastRandom();
    }

    @Override
    public long getAsLong() {
        return nodeIdentifier;
    }
}
