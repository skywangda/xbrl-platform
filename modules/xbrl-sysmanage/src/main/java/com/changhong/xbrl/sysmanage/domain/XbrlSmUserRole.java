package com.changhong.xbrl.sysmanage.domain;
public class XbrlSmUserRole {
    private Integer roleId;
    private Integer userId;
    public XbrlSmUserRole() {
        super();
    }
    public XbrlSmUserRole(Integer roleId, Integer userId) {
        super();
        this.roleId = roleId;
        this.userId = userId;
    }
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
