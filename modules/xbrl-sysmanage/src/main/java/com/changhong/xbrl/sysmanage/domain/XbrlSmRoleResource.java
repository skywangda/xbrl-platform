package com.changhong.xbrl.sysmanage.domain;
public class XbrlSmRoleResource {
    private Integer roleId;
    private Integer resourceId;
    public XbrlSmRoleResource() {
        super();
    }
    public XbrlSmRoleResource(Integer roleId, Integer resourceId) {
        super();
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

}
