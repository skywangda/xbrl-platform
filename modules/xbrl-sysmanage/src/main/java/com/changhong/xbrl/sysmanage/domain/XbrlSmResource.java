package com.changhong.xbrl.sysmanage.domain;

import java.util.List;

public class XbrlSmResource {
    private Integer id;
    private String name;
    private String url;
    private String menuid;
    private Integer ordering;
    private Integer type;
    private String state;//1：有效  0：无效
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String updateUser;
    private java.util.Date updateTime;
    private Long parentId;
    private List<XbrlSmResource> children;
    private Integer roleId;
    public XbrlSmResource() {
        super();
    }
    public XbrlSmResource(Integer id, String name, String url, String menuid, Integer ordering, Integer type, String state, String remark, String createUser, java.util.Date createTime, String updateUser, java.util.Date updateTime, Long parentId) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.menuid = menuid;
        this.ordering = ordering;
        this.type = type;
        this.state = state;
        this.remark = remark;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.parentId = parentId;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuid() {
        return this.menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public Integer getOrdering() {
        return this.ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<XbrlSmResource> getChildren() {
        return children;
    }

    public void setChildren(List<XbrlSmResource> children) {
        this.children = children;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
