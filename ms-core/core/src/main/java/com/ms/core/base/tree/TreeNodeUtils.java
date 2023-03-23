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

package com.ms.core.base.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树状结构
 *
 * @author ms2297248353
 */
public class TreeNodeUtils {
    public static <T extends TreeNode<T>> List<T> listToTree(List<T> nodeList, Object rootId) {
        List<T> treeList = new ArrayList<>();
        Map<Object, List<T>> parentIdMap = new HashMap<>();
        Map<Object, T> idMap = new HashMap<>();

        // 将所有节点放到idMap中
        for (T node : nodeList) {
            Object id = node.getId();
            idMap.put(id, node);
            parentIdMap.putIfAbsent(node.getParentId(), new ArrayList<>());
        }

        // 将子节点加到对应的父节点下
        for (T node : nodeList) {
            Object parentId = node.getParentId();
            if (parentId != null) {
                parentIdMap.get(parentId).add(node);
            }
        }

        // 找到根节点
        List<T> rootList = parentIdMap.get(rootId);
        if (rootList != null) {
            for (T root : rootList) {
                setChildren(root, idMap, parentIdMap);
                treeList.add(root);
            }
        }

        return treeList;
    }

    private static <T extends TreeNode<T>> void setChildren(T node, Map<Object, T> idMap, Map<Object, List<T>> parentIdMap) {
        Object id = node.getId();
        List<T> children = parentIdMap.get(id);
        if (children != null && !children.isEmpty()) {
            for (T child : children) {
                setChildren(child, idMap, parentIdMap);
            }
            node.setChildren(children);
        }
    }

}
