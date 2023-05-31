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

package com.ms.jpa.template;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页模板
 *
 * @param <T> 元数据类型
 * @param <S> 目标数据类型
 * @author ms2297248353
 */
public class PaginationTemplate<T, S> {
    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;

    /**
     * 数据
     */
    private List<S> data;

    /**
     * 元数据
     */
    private List<T> metaData;

    public PaginationTemplate() {
        current = 0;
        size = 0;
        pages = 0;
        total = 0L;
        hasNext = false;
        hasPrevious = false;
        data = null;
        metaData = null;
    }

    public PaginationTemplate(Integer current, Integer size, Integer pages, Long total, Boolean hasNext, Boolean hasPrevious, List<S> data) {
        this();
        this.current = current;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.data = data;
    }

    public PaginationTemplate(Integer current, Integer size, Integer pages, Long total, Boolean hasNext, Boolean hasPrevious, List<S> data, List<T> metaData) {
        this(current, size, pages, total, hasNext, hasPrevious, data);
        this.metaData = metaData;
    }

    /**
     * 构造函数 用于转换元数据到目标数据
     *
     * @param page 分页数据
     * @param ii   接口
     */
    public PaginationTemplate(Page<T> page, Info<T, S> ii) {
        current = page.getNumber() + 1;
        pages = page.getTotalPages();
        total = page.getTotalElements();
        size = page.getSize();
        hasNext = page.hasNext();
        hasPrevious = page.hasPrevious();
        data = page.map(ii::getInfo).getContent();
    }

    /**
     * 构造函数 用于转换元数据到目标数据
     *
     * @param page 分页数据
     * @param meta 是否返回元数据
     * @param ii   接口
     */
    public PaginationTemplate(Page<T> page, Boolean meta, Info<T, S> ii) {
        current = page.getNumber() + 1;
        pages = page.getTotalPages();
        total = page.getTotalElements();
        size = page.getSize();
        hasNext = page.hasNext();
        hasPrevious = page.hasPrevious();
        data = page.map(ii::getInfo).getContent();
        if (Boolean.TRUE.equals(meta)) {
            metaData = page.getContent();
        }
    }

    /**
     * 构造函数 用于转换元数据到目标数据
     * 静态
     *
     * @param page 分页数据
     * @param ii   接口
     * @param <T>  元数据类型
     * @param <S>  目标数据类型
     * @return 分页模板
     */
    public static <T, S> PaginationTemplate<T, S> of(Page<T> page, Info<T, S> ii) {
        return new PaginationTemplate<>(page, ii);
    }

    /**
     * 构造函数 用于转换元数据到目标数据
     * 静态
     *
     * @param page 分页数据
     * @param meta 是否返回元数据
     * @param ii   接口
     * @param <T>  元数据类型
     * @param <S>  目标数据类型
     * @return 分页模板
     */
    public static <T, S> PaginationTemplate<T, S> of(Page<T> page, Boolean meta, Info<T, S> ii) {
        return new PaginationTemplate<>(page, meta, ii);
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public List<S> getData() {
        return data;
    }

    public void setData(List<S> data) {
        this.data = data;
    }

    public List<T> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<T> metaData) {
        this.metaData = metaData;
    }

    /**
     * 接口 用于转换元数据到目标数据
     */
    public interface Info<T, S> {
        /**
         * 返回dto目标数据
         *
         * @param f 原始数据
         * @return 目标数据
         */
        S getInfo(T f);
    }
}