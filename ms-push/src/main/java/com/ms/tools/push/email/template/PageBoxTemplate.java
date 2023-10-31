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

package com.ms.tools.push.email.template;

/**
 * 收件箱 列表模板
 *
 * @author ms2297248353
 */
public class PageBoxTemplate<T> {

    /**
     * 总数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 数据
     */
    private T data;

    public PageBoxTemplate() {
        total = 0;
        pages = 0;
        current = 0;
        size = 0;
    }

    public PageBoxTemplate(int total, Integer pages, Integer current, Integer size, T data) {
        this.total = total;
        this.pages = pages;
        this.current = current;
        this.size = size;
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
