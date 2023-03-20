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

package com.ms.jpa.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * 主键Id
 *
 * @author qyg2297248353
 * @MappedSuperclass 解决重复生成表
 */
@MappedSuperclass
public abstract class AbstractBaseEntity extends AbstractIDEntity {
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "created_time")
    private Date createdTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "updated_time")
    private Date updatedTime;
    /**
     * 乐观锁
     */
    @Version
    @Column(name = "version")
    private Long version;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
