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

package com.ms.id.factory.tsid;

/**
 * A class that generates Time Sortable Identifiers (TSID).
 */
public final class TsidCreator {

    private TsidCreator() {
    }

    /**
     * Returns a TSID.
     * <p>
     * It supports up to 256 nodes.
     * <p>
     * It can generate up to 16,384 TSIDs per millisecond per node.
     * <p>
     * The time component can be 1 ms or more ahead of the system time when
     * necessary to maintain monotonicity and generation speed.
     * <p>
     * The node identifier is <b>random</b>, unless there's a system property
     * `tsidcreator.node` or an environment variable `TSIDCREATOR_NODE` is defined.
     * One of them <b>should</b> be used to embed a machine ID in the generated TSID
     * in order to avoid TSID collisions.
     * <p>
     * Random component settings:
     * <ul>
     * <li>Node bits: 8
     * <li>Counter bits: 14
     * <li>Maximum node: 256 (2^8)
     * <li>Maximum counter: 16,384 (2^14)
     * </ul>
     *
     * @return a TSID
     */
    public static Tsid getTsid256() {
        return Factory256Holder.INSTANCE.create();
    }

    /**
     * Returns a TSID.
     * <p>
     * It supports up to 1,024 nodes.
     * <p>
     * It can generate up to 4,096 TSIDs per millisecond per node.
     * <p>
     * The time component can be 1 ms or more ahead of the system time when
     * necessary to maintain monotonicity and generation speed.
     * <p>
     * The node identifier is <b>random</b>, unless there's a system property
     * `tsidcreator.node` or an environment variable `TSIDCREATOR_NODE` is defined.
     * One of them <b>should</b> be used to embed a machine ID in the generated TSID
     * in order to avoid TSID collisions.
     * <p>
     * Random component settings:
     * <ul>
     * <li>Node bits: 10
     * <li>Counter bits: 12
     * <li>Maximum node: 1,024 (2^10)
     * <li>Maximum counter: 4,096 (2^12)
     * </ul>
     *
     * @return a TSID
     */
    public static Tsid getTsid1024() {
        return Factory1024Holder.INSTANCE.create();
    }

    /**
     * Returns a TSID.
     * <p>
     * It supports up to 4,096 nodes.
     * <p>
     * It can generate up to 1,024 TSIDs per millisecond per node.
     * <p>
     * The time component can be 1 ms or more ahead of the system time when
     * necessary to maintain monotonicity and generation speed.
     * <p>
     * The node identifier is <b>random</b>, unless there's a system property
     * `tsidcreator.node` or an environment variable `TSIDCREATOR_NODE` is defined.
     * One of them <b>should</b> be used to embed a machine ID in the generated TSID
     * in order to avoid TSID collisions.
     * <p>
     * Random component settings:
     * <ul>
     * <li>Node bits: 12
     * <li>Counter bits: 10
     * <li>Maximum node: 4,096 (2^12)
     * <li>Maximum counter: 1,024 (2^10)
     * </ul>
     *
     * @return a TSID number
     */
    public static Tsid getTsid4096() {
        return Factory4096Holder.INSTANCE.create();
    }

    private static class Factory256Holder {
        static final TsidFactory INSTANCE = TsidFactory.newInstance256();
    }

    private static class Factory1024Holder {
        static final TsidFactory INSTANCE = TsidFactory.newInstance1024();
    }

    private static class Factory4096Holder {
        static final TsidFactory INSTANCE = TsidFactory.newInstance4096();
    }
}
