package com.changhong.xbrl.sysmanage.model;


import java.util.Collection;

public class ObjectPageDataResp<T> extends BaseRespEntity {
    private int pageNo;
    private int pageSize;
    private int totalNum;
    private Collection<T> dataSet;
    private Collection<T> selectedSet;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public Collection<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(Collection<T> dataSet) {
        this.dataSet = dataSet;
    }

    public Collection<T> getSelectedSet() {
        return selectedSet;
    }

    public void setSelectedSet(Collection<T> selectedSet) {
        this.selectedSet = selectedSet;
    }
}
