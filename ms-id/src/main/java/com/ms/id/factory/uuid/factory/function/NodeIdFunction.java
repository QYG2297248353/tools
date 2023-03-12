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

package com.ms.id.factory.uuid.factory.function;

import com.ms.id.factory.uuid.util.internal.RandomUtil;

import java.util.function.LongSupplier;

/**
 * Function that must return a number between 0 and 2^48-1.
 * <p>
 * Example:
 *
 * <pre>{@code
 * // A function that returns new random multicast node identifiers
 * NodeIdFunction f = () -> NodeIdFunction.getMulticastRandom();
 * }</pre>
 */
@FunctionalInterface
public interface NodeIdFunction extends LongSupplier {

    /**
     * Returns a new random node identifier.
     *
     * @return a number in the range 0 to 2^48-1.
     */
    public static long getRandom() {
        return toExpectedRange(RandomUtil.nextLong());
    }

    /**
     * Return a new random multicast node identifier.
     *
     * @return a number in the range 0 to 2^48-1.
     */
    public static long getMulticastRandom() {
        return toMulticast(RandomUtil.nextLong());
    }

    /**
     * Clears the leading bits so that the resulting number is in the range 0 to
     * 2^48-1.
     * <p>
     * The result is equivalent to {@code n % 2^48}.
     *
     * @param nodeid the node identifier
     * @return a number in the range 0 to 2^48-1.
     */
    public static long toExpectedRange(final long nodeid) {
        return nodeid & 0x0000_ffffffffffffL;
    }

    /**
     * Sets the multicast bit of a node identifier.
     * <p>
     * It also clears leading bits so that the resulting number is within the range
     * 0 to 2^48-1.
     *
     * @param nodeid the node identifier
     * @return a node identifier with the multicast bit set
     */
    public static long toMulticast(long nodeid) {
        return (nodeid & 0x0000_ffffffffffffL) | 0x0000_010000000000L;
    }

    /**
     * Checks if the multicast bit of a node identifier is set.
     *
     * @param nodeid a node identifier
     * @return true if the node identifier is multicast
     */
    public static boolean isMulticast(long nodeid) {
        return (nodeid & 0x0000_010000000000L) == 0x0000_010000000000L;
    }
}
