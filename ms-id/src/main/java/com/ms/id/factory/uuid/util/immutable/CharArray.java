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

package com.ms.id.factory.uuid.util.immutable;

import java.util.Arrays;

public final class CharArray {

    private final char[] array;

    private CharArray(char[] a) {
        array = Arrays.copyOf(a, a.length);
    }

    public static CharArray from(char[] a) {
        return new CharArray(a);
    }

    public char get(int index) {
        return array[index];
    }

    public int length() {
        return this.array.length;
    }

    public char[] array() {
        return array.clone();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CharArray other = (CharArray) obj;
        return Arrays.equals(array, other.array);
    }

    @Override
    public String toString() {
        return "CharArray [array=" + Arrays.toString(array) + "]";
    }
}
