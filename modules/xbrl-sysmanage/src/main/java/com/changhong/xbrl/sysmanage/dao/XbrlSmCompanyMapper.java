package com.changhong.xbrl.sysmanage.dao;

import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmCompanyMapper{
	/**
	 * 获得XbrlSmCompany数据的总行数
	 * @return
	 */
    long getXbrlSmCompanyRowCount();
	/**
	 * 获得XbrlSmCompany数据集合
	 * @return
	 */
    List<XbrlSmCompany> selectXbrlSmCompany();
	/**
	 * 获得一个XbrlSmCompany对象,以参数XbrlSmCompany对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    XbrlSmCompany selectXbrlSmCompanyByObj(XbrlSmCompany obj);
	/**
	 * 获得一组XbrlSmCompany对象,以参数XbrlSmCompany对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
	List<XbrlSmCompany> selectXbrlSmCompanyListByObj(XbrlSmCompany obj);
	/**
	 * 通过XbrlSmCompany的id获得XbrlSmCompany对象
	 * @param id
	 * @return
	 */
    XbrlSmCompany selectXbrlSmCompanyById(Double id);
	/**
	 * 插入XbrlSmCompany到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertXbrlSmCompany(XbrlSmCompany value);
	/**
	 * 插入XbrlSmCompany中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyXbrlSmCompany(XbrlSmCompany value);
	/**
	 * 通过XbrlSmCompany的id删除XbrlSmCompany
	 * @param id
	 * @return
	 */
    int deleteXbrlSmCompanyById(Double id);

	/**
	 * 通过XbrlSmCompany的id更新XbrlSmCompany中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateXbrlSmCompanyById(XbrlSmCompany enti);
	/**
	 * 通过XbrlSmCompany的id更新XbrlSmCompany中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyXbrlSmCompanyById(XbrlSmCompany enti);
	/**
	 * 获得XbrlSmCompany数据的总行数
	 *
	 * @return
	 */
	int getCount(HashMap<String, Object> searchParamsMap);
	/**
	 * 获得XbrlSmCompany数据集合
	 *
	 * @return
	 */
	List<XbrlSmCompany> selectXbrlSmCompanyList(HashMap<String, Object> searchParamsMap);
}