package com.changhong.xbrl.sysmanage.service;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUserRole;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmUserRoleService{

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
	List<HashMap<String, String>> selectRoleByUserId(Integer userId);

	/**
	  * 插入XbrlSmUserRole到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertXbrlSmUserRole(XbrlSmUserRole value);

	/**
	 * 通过XbrlSmUserRole的id删除XbrlSmUserRole
	 * @param id
	 * @return
	 */
    int deleteXbrlSmUserRoleById(int id);

	/**
	 * 通过XbrlSmUserRole的id删除XbrlSmUserRole
	 * @param ids
	 * @return
	 */
	int deleteXbrlSmUserRoleByUserId(String ids);

	/**
	 * 保存用户角色信息
	 * @param roleId
	 * @param userIds
	 */
	void saveUserForRole(int roleId, int[] userIds);

	/**
	 * 保存用户角色信息
	 * @param roleIds
	 * @param userId
	 */
	void saveUserForRole(int[] roleIds, int userId);

}