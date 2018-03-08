package com.changhong.xbrl.sysmanage.dao;

import com.changhong.xbrl.sysmanage.domain.XbrlSmUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmUserRoleMapper{

	/**
	 * 获得XbrlSmUserRole数据集合
	 * @return
	 */
    List<XbrlSmUserRole> selectXbrlSmUserRole();

	/**
	 * 获得一个XbrlSmUserRole对象,以参数XbrlSmUserRole对象中不为空的属性作为条件进行查询
	 * @param userId
	 * @return
	 */
	List<HashMap<String, String>> selectRoleByUserId(@Param("userId") Integer userId);

	/**
	 * 插入XbrlSmUserRole到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertXbrlSmUserRole(XbrlSmUserRole value);

	/**
	 * 批量添加角色人员关系
	 * @param userRoleList
	 * @return
	 */
	int insertXbrlSmUserRoles(List<XbrlSmUserRole> userRoleList);

	/**
	 * 通过XbrlSmUserRole的id删除XbrlSmUserRole
	 * @param roleId
	 * @return
	 */
	int deleteXbrlSmUserRoleByRoleId(int roleId);

	/**
	 * 通过XbrlSmUserRole的id删除XbrlSmUserRole
	 * @param ids
	 * @return
	 */
	int deleteXbrlSmUserRoleByUserIds(String[] ids);

	/**
	 * 通过XbrlSmUserRole的id删除XbrlSmUserRole
	 * @param userId
	 * @return
	 */
	int deleteXbrlSmUserRoleByUserId(int userId);

}