package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmUserRoleMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUserRole;
import com.changhong.xbrl.sysmanage.service.XbrlSmUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class XbrlSmUserRoleServiceImpl implements XbrlSmUserRoleService{

    @Autowired
    private XbrlSmUserRoleMapper xbrlSmUserRoleMapper;

    @Override
    public List<XbrlSmUserRole> selectXbrlSmUserRole(){
        return xbrlSmUserRoleMapper.selectXbrlSmUserRole();
    }

    @Override
    public List<HashMap<String, String>> selectRoleByUserId(Integer userId) {
        return xbrlSmUserRoleMapper.selectRoleByUserId(userId);
    }

    @Override
    public int insertXbrlSmUserRole(XbrlSmUserRole value){
        return xbrlSmUserRoleMapper.insertXbrlSmUserRole(value);
    }

    @Override
    public int deleteXbrlSmUserRoleById(int id) {
        return xbrlSmUserRoleMapper.deleteXbrlSmUserRoleByRoleId(id);
    }

    @Override
    public int deleteXbrlSmUserRoleByUserId(String ids) {
        String[] idArr = ids.split(",");
        return xbrlSmUserRoleMapper.deleteXbrlSmUserRoleByUserIds(idArr);
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void saveUserForRole(int roleId, int[] userIds) {
        xbrlSmUserRoleMapper.deleteXbrlSmUserRoleByRoleId(roleId);
        List<XbrlSmUserRole> userRoleList =new ArrayList<XbrlSmUserRole>();
        for(int item : userIds){
            XbrlSmUserRole userRole =new XbrlSmUserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(item);
            userRoleList.add(userRole);
        }
        xbrlSmUserRoleMapper.insertXbrlSmUserRoles(userRoleList);
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void saveUserForRole(int[] roleIds, int userId) {
        xbrlSmUserRoleMapper.deleteXbrlSmUserRoleByUserId(userId);
        List<XbrlSmUserRole> userRoleList =new ArrayList<XbrlSmUserRole>();
        for(int item : roleIds){
            XbrlSmUserRole userRole =new XbrlSmUserRole();
            userRole.setRoleId(item);
            userRole.setUserId(userId);
            userRoleList.add(userRole);
        }
        xbrlSmUserRoleMapper.insertXbrlSmUserRoles(userRoleList);
    }

}