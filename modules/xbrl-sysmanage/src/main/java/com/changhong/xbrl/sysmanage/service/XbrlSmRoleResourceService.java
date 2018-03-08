package com.changhong.xbrl.sysmanage.service;

import com.changhong.xbrl.sysmanage.domain.XbrlSmRoleResource;

import java.util.List;

public interface XbrlSmRoleResourceService {

    /**
     * 通过XbrlSmRoleResource的id获得XbrlSmRoleResource对象
     * @param roleid
     * @return
     */
    List<XbrlSmRoleResource> getRoleResourceListByRole(long roleid);

    /**
     * 插入XbrlSmRoleResource到数据库
     * @param list
     *          数据集合
     * @return
     */
    int saveXbrlSmRoleResourceList(Integer roleId, List<XbrlSmRoleResource> list);


    /**
     * 通过XbrlSmRoleResource的id删除XbrlSmRoleResource
     * @param roleId
     *         角色Id
     * @param ids
     *         资源集合
     * @return
     */
    int deleteXbrlSmRoleResourceList(Integer roleId, String[] ids);



}