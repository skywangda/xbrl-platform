package com.changhong.xbrl.sysmanage.model;

import com.alibaba.fastjson.JSONObject;

public class JsonObjResp extends BaseRespEntity {
    private static final long serialVersionUID = -7022719170250902258L;
    private JSONObject obj = new JSONObject();

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
}
