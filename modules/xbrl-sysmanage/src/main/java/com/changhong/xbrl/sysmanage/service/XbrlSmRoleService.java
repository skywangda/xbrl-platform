package com.changhong.xbrl.sysmanage.service;

import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmRoleService {

    /**
     * 获得XbrlSmRole数据集合
     *
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleList(HashMap<String, Object> searchParamsMap);

    /**
     * 获得XbrlSmRole数据集合
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleList();

    /**
     * 获得XbrlSmRole数据的总行数
     *
     * @return
     */
    int getCount(HashMap<String, Object> searchParamsMap);

    /**
     * 根据用户角色名称查询角色信息
     * @param name
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleByName(String name);

    /**
     * 更加角色roleId获取该角色的资源列表
     * @param roleId
     * @return
     */
    List<XbrlSmResource> selectResourceForRole(long roleId);

    /**
     * 通过XbrlSmRole的id获得XbrlSmRole对象
     * @param id
     * @return
     */
    XbrlSmRole selectXbrlSmRoleById(long id);

    /**
     * 插入XbrlSmRole到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertXbrlSmRole(XbrlSmRole value);

    /**
     * 插入XbrlSmRole到数据库,包括null值
     *
     * @param roleid
     * @param resourceIdlist
     * @return
     */
    int insertRoleResource(int roleid, List<Integer> resourceIdlist);

    /**
     * 通过XbrlSmRole的id删除XbrlSmRole
     * @param id
     * @return
     */
    void deleteXbrlSmRoleById(int id);

    /**
     * 通过XbrlSmRole的id更新XbrlSmRole中的数据
     * @param enti
     * @return
     */
    int updateRole(XbrlSmRole enti);

}