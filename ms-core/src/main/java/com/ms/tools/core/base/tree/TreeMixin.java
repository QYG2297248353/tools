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

package com.ms.tools.core.base.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface TreeMixin<T> extends Serializable {
    /**
     * 获取子节点
     *
     * @return List
     */
    List<T> getChildren();

    /**
     * 设置子节点
     *
     * @param children 子节点列表
     */
    void setChildren(List<T> children);

    /**
     * 添加子节点
     *
     * @param child 子节点
     */
    default void addChildren(T child) {
        List<T> children = getChildren();
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
        setChildren(children);
    }
}
