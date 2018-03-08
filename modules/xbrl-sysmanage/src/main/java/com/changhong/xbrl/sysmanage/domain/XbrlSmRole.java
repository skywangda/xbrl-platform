package com.changhong.xbrl.sysmanage.domain;

import java.io.Serializable;
import java.util.Date;

public class XbrlSmRole implements Serializable {

    private Integer id;
    private String name;
    private String state;//1：有效  0：无效
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String updateUser;
    private java.util.Date updateTime;
    public XbrlSmRole() {
        super();
    }
    public XbrlSmRole(Integer id, String name, String state, String remark, String createUser, java.util.Date createTime, String updateUser, java.util.Date updateTime) {
        super();
        this.id = id;
        this.name = name;
        this.state = state;
        this.remark = remark;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
