package com.changhong.xbrl.sysmanage.domain;
public class XbrlSmCompany {
    private Integer id;//主键
    private String identifierScheme;//公司网址  或  工商注册代码所在的工商网址
    private String companyCode;
    private Integer companyType;//1 ：集团公司
    private String companyName;
    private String shortName;//公司简称
    private String nameSpacePrefix;
    private String companyInfo;//公司基本信息
    private String state;//1：有效0：无效
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
    private Integer mark;
    private String consolidatedCode;
    private String separateCode;
    private Integer parentId;
    private String businessLic;
    public XbrlSmCompany() {
        super();
    }
    public XbrlSmCompany(Integer id, String identifierScheme, String companyCode, Integer companyType, String companyName, String shortName, String nameSpacePrefix, String companyInfo, String state, String createUser, String createTime, String updateUser, String updateTime, Integer mark, String consolidatedCode, String separateCode, Integer parentId, String businessLic) {
        super();
        this.id = id;
        this.identifierScheme = identifierScheme;
        this.companyCode = companyCode;
        this.companyType = companyType;
        this.companyName = companyName;
        this.shortName = shortName;
        this.nameSpacePrefix = nameSpacePrefix;
        this.companyInfo = companyInfo;
        this.state = state;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.mark = mark;
        this.consolidatedCode = consolidatedCode;
        this.separateCode = separateCode;
        this.parentId = parentId;
        this.businessLic = businessLic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifierScheme() {
        return this.identifierScheme;
    }

    public void setIdentifierScheme(String identifierScheme) {
        this.identifierScheme = identifierScheme;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNameSpacePrefix() {
        return this.nameSpacePrefix;
    }

    public void setNameSpacePrefix(String nameSpacePrefix) {
        this.nameSpacePrefix = nameSpacePrefix;
    }

    public String getCompanyInfo() {
        return this.companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMark() {
        return this.mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getConsolidatedCode() {
        return this.consolidatedCode;
    }

    public void setConsolidatedCode(String consolidatedCode) {this.consolidatedCode = consolidatedCode; }

    public String getSeparateCode() {
        return this.separateCode;
    }

    public void setSeparateCode(String separateCode) {
        this.separateCode = separateCode;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getBusinessLic() {
        return this.businessLic;
    }

    public void setBusinessLic(String businessLic) {
        this.businessLic = businessLic;
    }

}
