package com.changhong.xbrl.sysmanage.dao;

import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmRoleMapper {
    /**
     * 获得XbrlSmRole数据的总行数
     *
     * @return
     */
    int getCount(HashMap<String, Object> searchParamsMap);

    /**
     * 获得XbrlSmRole数据集合
     *
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleList(HashMap<String, Object> searchParamsMap);

    /**
     * 查询所有的角色列表
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleAllList();

    /**
     * 通过XbrlSmRole的id获得XbrlSmRole对象
     *
     * @param id
     * @return
     */
    XbrlSmRole selectXbrlSmRoleById(long id);

    /**
     * 根据角色name查询角色列表
     * @param name
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleByName(String name);

    /**
     * 插入XbrlSmRole到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertXbrlSmRole(XbrlSmRole value);

    /**
     * 通过XbrlSmRole的id删除XbrlSmRole
     *
     * @param id
     * @return
     */
    int deleteXbrlSmRole(long id);

    /**
     * 通过XbrlSmRole的id更新XbrlSmRole中的数据,包括null值
     *
     * @param enti
     * @return
     */
    int updateXbrlSmRole(XbrlSmRole enti);

}