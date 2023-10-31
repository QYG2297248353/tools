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

package com.ms.tools.core.id.factory.uuid.factory.rfc4122;

import com.ms.tools.core.id.factory.uuid.enums.UuidLocalDomain;
import com.ms.tools.core.id.factory.uuid.enums.UuidVersion;
import com.ms.tools.core.id.factory.uuid.factory.AbstTimeBasedFactory;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Concrete factory for creating DCE Security unique identifiers (UUIDv2).
 *
 * @see UuidLocalDomain
 * @see <a href=
 * "https://pubs.opengroup.org/onlinepubs/9696989899/chap5.htm#tagcjh_08_02_01_01">DCE
 * Security UUIDs</a>
 */
public final class DceSecurityFactory extends AbstTimeBasedFactory {

    private final byte localDomain;
    private final AtomicInteger counter;

    public DceSecurityFactory() {
        this(builder());
    }

    private DceSecurityFactory(Builder builder) {
        super(UuidVersion.VERSION_DCE_SECURITY, builder);
        localDomain = builder != null ? builder.localDomain : 0;
        counter = new AtomicInteger();
    }

    /**
     * Returns a builder of DCE Security factory.
     *
     * @return a builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Embeds the local identifier in into the most significant bits.
     *
     * @param msb             the MSB
     * @param localIdentifier the local identifier
     * @return the updated MSB
     */
    private static long emgedLocalIdentifier(long msb, int localIdentifier) {
        return (msb & 0x00000000ffffffffL) // clear time_low bits
                | ((localIdentifier & 0x00000000ffffffffL) << 32);
    }

    /**
     * Embeds the local domain bits in the least significant bits.
     *
     * @param lsb         the LSB
     * @param localDomain a local domain
     * @param counter     a counter value
     * @return the updated LSB
     */
    private static long embedLocalDomain(long lsb, byte localDomain, long counter) {
        return (lsb & 0x0000ffffffffffffL) // clear clock_seq bits
                | ((localDomain & 0x00000000000000ffL) << 48) //
                | ((counter & 0x00000000000000ffL) << 56);
    }

    /**
     * Returns a DCE Security unique identifier (UUIDv2).
     * <p>
     * A DCE Security UUID is a modified UUIDv1.
     * <p>
     * Steps of creation:
     * <ol>
     * <li>Create a Time-based UUIDv1;
     * <li>Replace the least significant 8 bits of the clock sequence with the local
     * domain;
     * <li>Replace the least significant 32 bits of the time stamp with the local
     * identifier.
     * </ol>
     *
     * @param localDomain     a local domain
     * @param localIdentifier a local identifier
     * @return a DCE Security UUID
     */
    public synchronized UUID create(byte localDomain, int localIdentifier) {

        // Create a UUIDv1
        UUID uuid = super.create();

        // Embed the local domain bits
        long lsb = embedLocalDomain(uuid.getLeastSignificantBits(), localDomain, counter.incrementAndGet());

        // Embed the local identifier bits
        long msb = emgedLocalIdentifier(uuid.getMostSignificantBits(), localIdentifier);

        return toUuid(msb, lsb);
    }

    /**
     * Returns a DCE Security unique identifier (UUIDv2).
     *
     * @param localDomain     a local domain
     * @param localIdentifier a local identifier
     * @return a DCE Security UUID
     */
    public synchronized UUID create(UuidLocalDomain localDomain, int localIdentifier) {
        return create(localDomain.getValue(), localIdentifier);
    }

    /**
     * Returns a DCE Security unique identifier (UUIDv2).
     * <p>
     * The local domain is local domain used by this method defined by builder:
     *
     * <pre>{@code
     * DceSecurityFactory factory = DceSecurityFactory.builder().withLocalDomain(UuidLocalDomain).build();
     * }</pre>
     *
     * @param localIdentifier a local identifier
     * @return a UUIDv2
     */
    public synchronized UUID create(int localIdentifier) {
        return create(localDomain, localIdentifier);
    }

    /**
     * Always throws an exception.
     * <p>
     * Overrides the method {@link AbstTimeBasedFactory#create()} to throw an
     * exception instead of returning a UUID.
     *
     * @throws UnsupportedOperationException 异常
     */
    @Override
    public synchronized UUID create() {
        throw new UnsupportedOperationException("Unsuported operation for DCE Security UUID factory");
    }

    /**
     * Concrete builder for creating a DCE Security factory.
     *
     * @see AbstTimeBasedFactory.Builder
     */
    public static class Builder extends AbstTimeBasedFactory.Builder<DceSecurityFactory, Builder> {

        private byte localDomain;

        public Builder withLocalDomain(UuidLocalDomain localDomain) {
            this.localDomain = localDomain.getValue();
            return this;
        }

        public Builder withLocalDomain(byte localDomain) {
            this.localDomain = localDomain;
            return this;
        }

        @Override
        public DceSecurityFactory build() {
            return new DceSecurityFactory(this);
        }
    }
}
