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

package com.ms.tools.core.id.factory.ksuid;

import java.time.Instant;

/**
 * A class that generates KSUIDs.
 * <p>
 * All 3 types of KSUID can be easily created by this generator, i.e.
 * non-monotonic (Segment's), monotonic and sub-second.
 */
public final class KsuidCreator {

    private KsuidCreator() {
    }

    /**
     * Returns a Segment's KSUID.
     *
     * @return a KSUID
     */
    public static Ksuid getKsuid() {
        return FactoryHolder.INSTANCE.create();
    }

    /**
     * Returns a KSUID with sub-second precision.
     * <p>
     * Three sub-second precisions are supported: millisecond, microsecond, and
     * nanosecond. The precision is detected at runtime.
     *
     * @return a KSUID
     */
    public static Ksuid getSubsecondKsuid() {
        return SubsecondHolder.INSTANCE.create();
    }

    /**
     * Returns a Monotonic KSUID.
     * <p>
     * If more than one KSUID is generated within the same second, the payload is
     * incremented by one.
     *
     * @return a KSUID
     */
    public static Ksuid getMonotonicKsuid() {
        return MonotonicHolder.INSTANCE.create();
    }

    /**
     * Returns a Segment's KSUID.
     *
     * @param instant an instant
     * @return a KSUID
     */
    public static Ksuid getKsuid(Instant instant) {
        return FactoryHolder.INSTANCE.create(instant);
    }

    /**
     * Returns a KSUID with sub-second precision.
     * <p>
     * Three sub-second precisions are supported: millisecond, microsecond, and
     * nanosecond. The precision is detected at runtime.
     *
     * @param instant an instant
     * @return a KSUID
     */
    public static Ksuid getSubsecondKsuid(Instant instant) {
        return SubsecondHolder.INSTANCE.create(instant);
    }

    /**
     * Returns a Monotonic KSUID.
     * <p>
     * If more than one KSUID is generated within the same second, the payload is
     * incremented by one.
     *
     * @param instant an instant
     * @return a KSUID
     */
    public static Ksuid getMonotonicKsuid(Instant instant) {
        return MonotonicHolder.INSTANCE.create(instant);
    }

    private static class FactoryHolder {
        static final KsuidFactory INSTANCE = KsuidFactory.newInstance();
    }

    private static class SubsecondHolder {
        static final KsuidFactory INSTANCE = KsuidFactory.newSubsecondInstance();
    }

    private static class MonotonicHolder {
        static final KsuidFactory INSTANCE = KsuidFactory.newMonotonicInstance();
    }
}
