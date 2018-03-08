package com.changhong.xbrl.sysmanage.dao;

import com.changhong.xbrl.sysmanage.domain.XbrlSmRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XbrlSmRoleResourceMapper {

    /**
     * 通过XbrlSmRoleResource的id获得XbrlSmRoleResource对象
     *
     * @param roleId
     * @return
     */
    List<XbrlSmRoleResource> selectRoleResourceByRoleId(long roleId);

    /**
     * 插入XbrlSmRoleResource到数据库,包括null值
     * @param value
     * @return
     */

    /**
     * 批量保存资源集合
     * @param xbrlSmRoleResourcesList
     *           资源集合
     * @return
     *
     */
      int insertXbrlSmRoleResourceList(List<XbrlSmRoleResource> xbrlSmRoleResourcesList);


    /**
     * 通过XbrlSmRoleResource的id删除XbrlSmRoleResource
     * @param roleId
     *          角色ID
     * @param ids
     *          资源ids集合
     * @return
     */
    int deleteXbrlSmRoleResourceList(@Param("roleId") Integer roleId, @Param("ids") String[] ids);


    /**
     * 通过XbrlSmRoleResource的id删除XbrlSmRoleResource
     * @param roleId  角色ID
     * @return
     */
    int deleteSmRoleResourceByRoleId(@Param("roleId") Integer roleId);

    /**
     *
     * @param roleId
     * @param resourceIdlist
     * @return
     */
    int insertXbrlSmResourceByRoleId(@Param("roleId") Integer roleId, @Param("resourceIdlist") List<Integer> resourceIdlist);

}