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

package com.ms.core.response;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页模板
 *
 * @param <T> 元数据类型
 * @param <S> 目标数据类型
 * @author ms2297248353
 */
public class PageTemplate<T, S> {

    /**
     * 当前页
     */
    private Integer page;

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

    private PageTemplate() {
    }

    public PageTemplate(Page<T> page, DataHandler<T, S> handler) {
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
        this.pages = page.getTotalPages();
        this.total = page.getTotalElements();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
        this.data = page.getContent().stream().map(handler::handle).collect(Collectors.toList());
    }

    public PageTemplate(Integer current, Integer size, Integer pages, Long total, Boolean hasNext, Boolean hasPrevious) {
        this.page = current;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public PageTemplate(Integer current, Integer size, Integer pages, Long total, Boolean hasNext, Boolean hasPrevious, List<S> data) {
        this.page = current;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.data = data;
    }

    /**
     * 处理数据
     *
     * @param d       数据
     * @param handler 处理器
     * @return 分页模板
     */
    public PageTemplate<T, S> handle(List<T> d, DataHandler<T, S> handler) {
        if (!d.isEmpty()) {
            data = d.stream().map(handler::handle).collect(Collectors.toList());
        }
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
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

    /**
     * 数据处理接口
     */
    public interface DataHandler<T, S> {
        /**
         * 处理数据
         *
         * @param f 元数据
         * @return 处理后的数据
         */
        S handle(T f);
    }
}
