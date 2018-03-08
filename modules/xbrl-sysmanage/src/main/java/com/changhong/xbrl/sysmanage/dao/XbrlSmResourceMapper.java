package com.changhong.xbrl.sysmanage.dao;
import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface XbrlSmResourceMapper{
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
	List<XbrlSmResource> selectXbrlSmResourceList(HashMap<String, Object> searchParamsMap);


	/**
	 * 根据父id组装树形菜单
	 * @param parentId
	 *          父类id
	 * @return
	 */
	List<XbrlSmResource> selectXbrlSmResourceAllByParentId(@Param("parentId") Integer parentId, @Param("key") String key, @Param("typed") Integer type);

	/**
	 * 根据名称和URL查询资源信息
	 * @param name
	 *           资源名称
	 * @param url
	 *           资源URL
	 * @return
	 */
	List<XbrlSmResource> selectXbrlSmResourceListByNameAndUrl(@Param("name") String name, @Param("url") String url);

	/**
	 * 保存资源信息
	 * @param xbrlSmResource
	 * @return
	 */
	Long insertXbrlSmResource(XbrlSmResource xbrlSmResource);

	/**
	 * 删除资源信息
	 * @param ids
	 *         数据集合
	 * @return
	 */
	Long deleteXbrlSmResourceByIds(String[] ids);


	/**
	 * 查询资源对象
	 * @param id
	 *         主键id对象
	 * @return
	 */
	XbrlSmResource selectXbrlSmResourceById(Integer Id);


	/**
	 * 修改资源对象
	 * @param xbrlSmResource
	 *         资源对象
	 * @return
	 */
	Long updateXbrlSmResource(XbrlSmResource xbrlSmResource);

	/**
	 * 修改资源状态
	 * @param ids
	 *         id集合
	 * @param state
	 * @return
	 */
	int disableOrEnableXbrlSmResource(@Param("ids") List<String> ids, @Param("state") Integer state);


	List<XbrlSmResource> selectXbrlSmResourceListByRoleId(@Param("roleId") Integer roleId);

}