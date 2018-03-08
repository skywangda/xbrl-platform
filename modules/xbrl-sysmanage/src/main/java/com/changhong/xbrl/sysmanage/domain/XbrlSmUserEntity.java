package com.changhong.xbrl.sysmanage.domain;
public class XbrlSmUserEntity {
    private Integer entityId;
    private Integer userId;
    public XbrlSmUserEntity() {
        super();
    }
    public XbrlSmUserEntity(Integer entityId, Integer userId) {
        super();
        this.entityId = entityId;
        this.userId = userId;
    }
    public Integer getEntityId() {
        return this.entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
