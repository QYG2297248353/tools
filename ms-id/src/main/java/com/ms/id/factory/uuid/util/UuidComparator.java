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

package com.ms.id.factory.uuid.util;

import java.util.Comparator;
import java.util.UUID;
import java.util.function.ToIntBiFunction;

/**
 * Comparator for UUIDs.
 * <p>
 * The default static method compares two time-based UUIDs by comparing the time
 * stamps first and then comparing the least significant bits as unsigned 64-bit
 * integers. If both UUIDs are not time-based then it compares them as unsigned
 * 128-bit integers.
 * <p>
 * The opaque static method compares two UUIDs as unsigned 128-bit integers.
 * It's the same as lexicographic sorting of UUID canonical strings.
 */
public final class UuidComparator implements Comparator<UUID> {

    private static final UuidComparator INSTANCE_DEFAULT = new UuidComparator(UuidComparator::defaultCompare);
    private static final UuidComparator INSTANCE_OPAQUE = new UuidComparator(UuidComparator::opaqueCompare);
    private final ToIntBiFunction<UUID, UUID> comparator;

    private UuidComparator(ToIntBiFunction<UUID, UUID> comparator) {
        this.comparator = comparator;
    }

    /**
     * Creates a default implementation of {@link UuidComparator}.
     *
     * @see UuidComparator#defaultCompare(UUID, UUID)
     */
    public UuidComparator() {
        this(UuidComparator::defaultCompare);
    }

    /**
     * Returns a default implementation of {@link UuidComparator}.
     *
     * @return a {@link UuidComparator}
     * @see UuidComparator#defaultCompare(UUID, UUID)
     */
    public static UuidComparator getDefaultInstance() {
        return INSTANCE_DEFAULT;
    }

    /**
     * Returns an opaque implementation of {@link UuidComparator}.
     *
     * @return a opaque {@link UuidComparator}
     * @see UuidComparator#opaqueCompare(UUID, UUID)
     */
    public static UuidComparator getOpaqueInstance() {
        return INSTANCE_OPAQUE;
    }

    /**
     * Compares two UUIDs.
     * <p>
     * The default static method compares two time-based UUIDs by comparing the time
     * stamps first and then comparing the least significant bits as unsigned 64-bit
     * integers. If both UUIDs are not time-based then it compares them as unsigned
     * 128-bit integers.
     * <p>
     * The first of two UUIDs is greater than the second if the time stamp is
     * greater for the first UUID. If the time stamps are equal, the first of two
     * UUIDs is greater than the second if the most significant byte in which they
     * differ is greater for the first UUID.
     * <p>
     * It can be useful for these reasons:
     * <ol>
     * <li>{@link UUID#compareTo(UUID)} doesn't work well for time-based UUIDs;
     * <li>{@link UUID#compareTo(UUID)} can lead to unexpected behavior due to
     * signed {@code long} comparison;
     * <li>{@link UUID#compareTo(UUID)} throws {@link NullPointerException} if a
     * {@code null} UUID is given.
     * </ol>
     *
     * @param uuid1 a {@code UUID}
     * @param uuid2 another {@code UUID}
     * @return -1, 0 or 1 as {@code u1} is less than, equal to, or greater than
     * {@code u2}
     */
    public static int defaultCompare(UUID uuid1, UUID uuid2) {

        UUID u1 = uuid1 != null ? uuid1 : new UUID(0L, 0L);
        UUID u2 = uuid2 != null ? uuid2 : new UUID(0L, 0L);

        // time-based comparison is done by timestamp first
        if (isTimeBased(u1) && isTimeBased(u2)) {
            UUID rearranged1 = new UUID(u1.timestamp(), u1.getLeastSignificantBits());
            UUID rearranged2 = new UUID(u2.timestamp(), u2.getLeastSignificantBits());
            return opaqueCompare(rearranged1, rearranged2);
        }

        // unsigned 128 bit integers
        return opaqueCompare(u1, u2);
    }

    /**
     * Compares two UUIDs.
     * <p>
     * The opaque static method compares two UUIDs as unsigned 128-bit integers.
     * It's the same as lexicographic sorting of UUID canonical strings.
     * <p>
     * The first of two UUIDs is greater than the second if the most significant
     * byte in which they differ is greater for the first UUID.
     * <p>
     * The opaque method is faster than the default method as it does not check the
     * UUID version.
     * <p>
     * It's referred to as "opaque" just because it works like a "blind byte-to-byte
     * comparison".
     * <p>
     * It can be useful for these reasons:
     * <ol>
     * <li>{@link UUID#compareTo(UUID)} can lead to unexpected behavior due to
     * signed {@code long} comparison;
     * <li>{@link UUID#compareTo(UUID)} throws {@link NullPointerException} if a
     * {@code null} UUID is given.
     * </ol>
     *
     * @param uuid1 a {@code UUID}
     * @param uuid2 another {@code UUID}
     * @return -1, 0 or 1 as {@code u1} is less than, equal to, or greater than
     * {@code u2}
     */
    public static int opaqueCompare(UUID uuid1, UUID uuid2) {

        UUID u1 = uuid1 != null ? uuid1 : new UUID(0L, 0L);
        UUID u2 = uuid2 != null ? uuid2 : new UUID(0L, 0L);

        // used to compare as UNSIGNED longs
        final long min = 0x8000000000000000L;

        final long a = u1.getMostSignificantBits() + min;
        final long b = u2.getMostSignificantBits() + min;

        if (a > b)
            return 1;
        else if (a < b)
            return -1;

        final long c = u1.getLeastSignificantBits() + min;
        final long d = u2.getLeastSignificantBits() + min;

        if (c > d)
            return 1;
        else if (c < d)
            return -1;

        return 0;
    }

    private static boolean isTimeBased(UUID uuid) {
        return uuid.version() == 1 && uuid.variant() == 2;
    }

    /**
     * Compares two UUIDs.
     *
     * @param uuid1 a {@code UUID}
     * @param uuid2 another {@code UUID}
     * @return -1, 0 or 1 as {@code u1} is less than, equal to, or greater than
     * {@code u2}
     * @see UuidComparator#defaultCompare(UUID, UUID)
     */
    @Override
    public int compare(UUID uuid1, UUID uuid2) {
        return this.comparator.applyAsInt(uuid1, uuid2);
    }
}
