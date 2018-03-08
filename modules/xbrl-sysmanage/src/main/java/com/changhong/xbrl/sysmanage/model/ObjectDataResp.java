package com.changhong.xbrl.sysmanage.model;


public class ObjectDataResp<T> extends BaseRespEntity {
    private T data;

    public ObjectDataResp(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
