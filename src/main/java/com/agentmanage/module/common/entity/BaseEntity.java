package com.agentmanage.module.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基础类
 */
public class BaseEntity implements Serializable{

    protected Integer id;
    protected Date gmtCreated;
    protected Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
