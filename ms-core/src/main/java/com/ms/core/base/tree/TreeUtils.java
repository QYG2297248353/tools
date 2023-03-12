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

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 树状结构
 *
 * @param <T> 泛型带转换数据
 * @param <R> ID,Parent数据类型,使用相同数据类型 String,Integer,Long
 * @author qyg2297248353
 */
public class TreeUtils<T extends TreeMixin, R> {
    /**
     * id获取方法
     */
    private Function<T, R> idGetter;
    /**
     * 父节点id获取方法
     */
    private Function<T, R> parentIdGetter;

    /**
     * 构造方法
     *
     * @param idGetter       当前
     * @param parentIdGetter 目标
     */
    public TreeUtils(Function<T, R> idGetter, Function<T, R> parentIdGetter) {
        this.idGetter = idGetter;
        this.parentIdGetter = parentIdGetter;
    }

    /**
     * 转化为树形结构
     *
     * @param list 数据
     * @param root 节点
     * @return 结果
     */
    public T buildTree(List<T> list, T root) {
        R apply = idGetter.apply(root);
        List<T> children = buildTreeList(list, apply);
        root.setChildren(children);
        return root;
    }

    /**
     * 转化为树形结构
     *
     * @param list   数据
     * @param rootId 节点
     * @return 结果
     */
    public List<T> buildTree(List<T> list, R rootId) {
        List<T> rootList = new ArrayList<>();
        list.forEach(node -> {
            R applyParent = parentIdGetter.apply(node);
            if (Objects.equals(rootId, applyParent)) {
                R apply = idGetter.apply(node);
                List<T> children = buildTreeList(list, apply);
                node.setChildren(children);
                rootList.add(node);
            }
        });
        return rootList;
    }

    /**
     * 构建树
     *
     * @param list   待转化的数据列表
     * @param rootId 节点
     * @return 结果
     */
    public List<T> buildTreeList(List<T> list, R rootId) {
        ArrayList<T> dest = new ArrayList<>(Collections.nCopies(list.size(), null));
        Collections.copy(dest, list);
        Map<R, T> idItemsMap = dest.stream().collect(Collectors.toMap(item -> idGetter.apply(item), item -> item));
        dest.forEach(item -> {
            R parentId = parentIdGetter.apply(item);
            T value = idItemsMap.get(parentId);
            Optional.ofNullable(value).ifPresent(parent -> parent.addChildren(item));
        });
        return dest.stream().peek(d -> {
            if (CollectionUtils.isEmpty(d.getChildren())) {
                d.setChildren(null);
            }
        }).filter(matchParentId(rootId)).collect(Collectors.toList());
    }

    private Predicate<T> matchParentId(R parentId) {
        return item -> Objects.equals(parentIdGetter.apply(item), parentId);
    }
}
