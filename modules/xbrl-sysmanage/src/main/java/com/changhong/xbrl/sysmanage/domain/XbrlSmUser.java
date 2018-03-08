package com.changhong.xbrl.sysmanage.domain;
public class XbrlSmUser {
    private Integer id;
    private String userCode;
    private String name;
    private String email;
    private String password;
    private String avatarUrl;
    private java.util.Date loginTime;
    private String state;//1：有效     0：无效
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String updateUser;
    private java.util.Date updateTime;
    public XbrlSmUser() {
        super();
    }
    public XbrlSmUser(Integer id, String userCode, String name, String email, String password, String avatarUrl, java.util.Date loginTime, String state, String remark, String createUser, java.util.Date createTime, String updateUser, java.util.Date updateTime) {
        super();
        this.id = id;
        this.userCode = userCode;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.loginTime = loginTime;
        this.state = state;
        this.remark = remark;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public java.util.Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(java.util.Date loginTime) {
        this.loginTime = loginTime;
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

}
