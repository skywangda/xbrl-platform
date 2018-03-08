package com.changhong.xbrl.sysmanage.model;

import com.google.common.collect.ImmutableList;

import java.util.Collection;

public class ObjectCollectionResp<T> extends BaseRespEntity {
    private Collection<T> dataSet;

    public ObjectCollectionResp(Collection<T> dataSet) {
        this.dataSet = ImmutableList.copyOf(dataSet);
    }

    public Collection<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(Collection<T> dataSet) {
        this.dataSet = dataSet;
    }
}
