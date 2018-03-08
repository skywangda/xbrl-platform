package com.changhong.xbrl.sysmanage.domain;
public class XbrlSmCompanyUser {
    private Integer companyId;
    private Integer userId;
    public XbrlSmCompanyUser() {
        super();
    }
    public XbrlSmCompanyUser(Integer companyId, Integer userId) {
        super();
        this.companyId = companyId;
        this.userId = userId;
    }
    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
