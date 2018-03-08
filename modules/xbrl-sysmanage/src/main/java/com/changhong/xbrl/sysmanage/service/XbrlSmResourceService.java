package com.changhong.xbrl.sysmanage.service;
import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmResourceService{
	/**
	 * 获得XbrlSmResource数据集合
	 *
	 * @return
	 */
	List<XbrlSmResource> selectXbrlSmResourceList(HashMap<String, Object> searchParamsMap);

	/**
	 * 获得XbrlSmResource数据的总行数
	 *
	 * @return
	 */
	int getCount(HashMap<String, Object> searchParamsMap);

	List<XbrlSmResource> getXbrlSmResourceListByNameAndUrl(String name, String url);

	Long saveXbrlSmResource(XbrlSmResource xbrlSmResource);

	Long deleteXbrlSmResourceByIds(String[] ids);

	XbrlSmResource getXbrlSmResourceById(Integer id);

	List<XbrlSmResource> getXbrlSmResourceAllByParentId(Integer parentId, String key, Integer type);

	List<XbrlSmResource> getXbrlSmResourceAllByRoleId(Integer roleId);

	Long updateXbrlSmResource(XbrlSmResource xbrlSmResource);
	/**
	 * 批量修改资源状态
	 * @param ids
	 * @param state
	 * @return
	 */
	int disableOrEnableXbrlSmResource(List<String> ids, Integer state);

}