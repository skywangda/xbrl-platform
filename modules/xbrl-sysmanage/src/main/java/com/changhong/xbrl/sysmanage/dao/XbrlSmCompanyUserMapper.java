package com.changhong.xbrl.sysmanage.dao;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompanyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XbrlSmCompanyUserMapper{
	/**
	 * 获得XbrlSmCompanyUser数据的总行数
	 * @return
	 */
    long getXbrlSmCompanyUserRowCount();
	/**
	 * 获得XbrlSmCompanyUser数据集合
	 * @return
	 */
    List<XbrlSmCompanyUser> selectXbrlSmCompanyUser();
	/**
	 * 获得一个XbrlSmCompanyUser对象,以参数XbrlSmCompanyUser对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    XbrlSmCompanyUser selectXbrlSmCompanyUserByObj(XbrlSmCompanyUser obj);
	/**
	 * 通过XbrlSmCompanyUser的id获得XbrlSmCompanyUser对象
	 * @param id
	 * @return
	 */
    XbrlSmCompanyUser selectXbrlSmCompanyUserById(Object id);
	/**
	 * 插入XbrlSmCompanyUser到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertXbrlSmCompanyUser(XbrlSmCompanyUser value);
	/**
	 * 插入XbrlSmCompanyUser中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyXbrlSmCompanyUser(XbrlSmCompanyUser value);
	/**
	 * 批量插入XbrlSmCompanyUser的数据列表到数据库
	 * @param list
	 * @return
	 */
	int insertXbrlSmCompanyUserBatch(@Param("list") List<XbrlSmCompanyUser> list);
	/**
	 * 通过XbrlSmCompanyUser的id删除XbrlSmCompanyUser
	 * @param id
	 * @return
	 */
    int deleteXbrlSmCompanyUserById(Object id);

	/**
	 * 删除XbrlSmCompanyUser
	 * @param ids
	 * @return
	 */
	int deleteXbrlSmCompanyUserByUserIds(String[] ids);

	/**
	 * 通过XbrlSmCompanyUser的id删除XbrlSmCompanyUser
	 * @param id
	 * @return
	 */
	int deleteXbrlSmCompanyUserByCompanyId(Object id);
	/**
	 * 通过XbrlSmCompanyUser的id更新XbrlSmCompanyUser中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateXbrlSmCompanyUserById(XbrlSmCompanyUser enti);
	/**
	 * 通过XbrlSmCompanyUser的id更新XbrlSmCompanyUser中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyXbrlSmCompanyUserById(XbrlSmCompanyUser enti);
}