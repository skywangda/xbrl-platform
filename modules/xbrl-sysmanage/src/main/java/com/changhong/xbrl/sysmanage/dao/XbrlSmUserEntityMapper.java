package com.changhong.xbrl.sysmanage.dao;

import com.changhong.xbrl.sysmanage.domain.XbrlSmUserEntity;

import java.util.List;

public interface XbrlSmUserEntityMapper{
	/**
	 * 获得XbrlSmUserEntity数据的总行数
	 * @return
	 */
    long getXbrlSmUserEntityRowCount();
	/**
	 * 获得XbrlSmUserEntity数据集合
	 * @return
	 */
    List<XbrlSmUserEntity> selectXbrlSmUserEntity();
	/**
	 * 获得一个XbrlSmUserEntity对象,以参数XbrlSmUserEntity对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    XbrlSmUserEntity selectXbrlSmUserEntityByObj(XbrlSmUserEntity obj);
	/**
	 * 通过XbrlSmUserEntity的id获得XbrlSmUserEntity对象
	 * @param id
	 * @return
	 */
    XbrlSmUserEntity selectXbrlSmUserEntityById(Object id);
	/**
	 * 插入XbrlSmUserEntity到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertXbrlSmUserEntity(XbrlSmUserEntity value);
	/**
	 * 插入XbrlSmUserEntity中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyXbrlSmUserEntity(XbrlSmUserEntity value);
	/**
	 * 通过XbrlSmUserEntity的id删除XbrlSmUserEntity
	 * @param id
	 * @return
	 */
    int deleteXbrlSmUserEntityById(Object id);
	/**
	 * 通过XbrlSmUserEntity的id更新XbrlSmUserEntity中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateXbrlSmUserEntityById(XbrlSmUserEntity enti);
	/**
	 * 通过XbrlSmUserEntity的id更新XbrlSmUserEntity中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyXbrlSmUserEntityById(XbrlSmUserEntity enti);
}