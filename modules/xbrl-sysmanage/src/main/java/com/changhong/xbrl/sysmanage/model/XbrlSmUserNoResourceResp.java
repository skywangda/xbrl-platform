package com.changhong.xbrl.sysmanage.model;

import com.changhong.xbrl.sysmanage.domain.XbrlSmUser;

/**
 * Created by Liang on 2017/12/4.
 */
public class XbrlSmUserNoResourceResp  extends BaseRespEntity {
    private XbrlSmUser user;		//用户信息
    private String token;			//令牌

    public XbrlSmUser getUser() {
        return user;
    }
    public void setUser(XbrlSmUser user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
