package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmRoleResourceMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRoleResource;
import com.changhong.xbrl.sysmanage.service.XbrlSmRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XbrlSmRoleResourceServiceImpl implements XbrlSmRoleResourceService {

    @Autowired
    private XbrlSmRoleResourceMapper xbrlSmRoleResourceMapper;



    public List<XbrlSmRoleResource> getRoleResourceListByRole(long roleid) {
        return xbrlSmRoleResourceMapper.selectRoleResourceByRoleId(roleid);
    }

    public int saveXbrlSmRoleResourceList(Integer roleId, List<XbrlSmRoleResource> list) {
        //清空之前的角色资源信息
        xbrlSmRoleResourceMapper.deleteSmRoleResourceByRoleId(roleId);
        //新增角色资源信息
        return xbrlSmRoleResourceMapper.insertXbrlSmRoleResourceList(list);
    }


    public int deleteXbrlSmRoleResourceList(Integer roleId, String[] ids) {
        return xbrlSmRoleResourceMapper.deleteXbrlSmRoleResourceList(roleId,ids);
    }


}