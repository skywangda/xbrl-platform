package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmResourceMapper;
import com.changhong.xbrl.sysmanage.dao.XbrlSmRoleMapper;
import com.changhong.xbrl.sysmanage.dao.XbrlSmRoleResourceMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRoleResource;
import com.changhong.xbrl.sysmanage.service.XbrlSmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class XbrlSmRoleServiceImpl implements XbrlSmRoleService {

    @Autowired
    private XbrlSmRoleMapper roleMapper;
    @Autowired
    private XbrlSmRoleResourceMapper roleResourceMapper;
    @Autowired
    private XbrlSmResourceMapper resourceMapper;

    @Override
    public List<XbrlSmRole> selectXbrlSmRoleList(HashMap<String,Object> searchParamsMap) {
        return roleMapper.selectXbrlSmRoleList(searchParamsMap);
    }

    @Override
    public List<XbrlSmRole> selectXbrlSmRoleList() {
        return roleMapper.selectXbrlSmRoleAllList();
    }

    @Override
    public int getCount(HashMap<String,Object> searchParamsMap) {
        return roleMapper.getCount(searchParamsMap);
    }

    @Override
    public List<XbrlSmRole> selectXbrlSmRoleByName(String name) {
        return roleMapper.selectXbrlSmRoleByName(name);
    }

    @Override
    public List<XbrlSmResource> selectResourceForRole(long roleId) {
       return null;
    }


    @Override
    public XbrlSmRole selectXbrlSmRoleById(long id) {
        return roleMapper.selectXbrlSmRoleById(id);
    }

    @Override
    public int insertXbrlSmRole(XbrlSmRole value) {
        int roleid=roleMapper.insertXbrlSmRole(value);
        return roleid;
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertRoleResource(int id ,List<Integer> resourceIdlist) {
        int count=0;
        if (resourceIdlist.size() > 0) {
            List<XbrlSmRoleResource> resourceslist =new ArrayList<XbrlSmRoleResource>();
            for (Integer resourceId : resourceIdlist) {
                XbrlSmRoleResource roleResource = new XbrlSmRoleResource();
                roleResource.setRoleId(id);
                roleResource.setResourceId(resourceId);
                resourceslist.add(roleResource);
            }
            roleResourceMapper.insertXbrlSmRoleResourceList(resourceslist);
        }
        return count;
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void deleteXbrlSmRoleById(int id) {
        //删除该角色的资源信息
        List<String> idlist = new ArrayList<String>();
        List<XbrlSmRoleResource> roleResourceslist=roleResourceMapper.selectRoleResourceByRoleId(id);
        for(int i=0;i<roleResourceslist.size();i++){
            idlist.add(roleResourceslist.get(i).getResourceId().toString());
        }

        String[] idarray =new String[]{};
        idarray=idlist.toArray(idarray);
        roleResourceMapper.deleteXbrlSmRoleResourceList(id,idarray);
        //删除角色信息
        roleMapper.deleteXbrlSmRole(id);
    }

    @Override
    public int updateRole(XbrlSmRole enti) {
        return roleMapper.updateXbrlSmRole(enti);
    }

}