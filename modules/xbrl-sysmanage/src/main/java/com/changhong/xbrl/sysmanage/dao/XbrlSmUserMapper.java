package com.changhong.xbrl.sysmanage.dao;

import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XbrlSmUserMapper{
	/**
	 * 获得XbrlSmUser数据的总行数
	 * @return
	 */
    long getXbrlSmUserRowCount(@Param("key") String key);
	/**
	 * 获得XbrlSmUser数据集合
	 * @return
	 */
    List<XbrlSmUser> selectXbrlSmUser();
	/**
	 * 获得XbrlSmUser数据集合
	 * @return
	 */
	List<XbrlSmUser> selectXbrlSmUserPage(String key, Integer start, Integer end, String sortType, String direction);
	/**
	 * 获得一个XbrlSmUser对象,以参数XbrlSmUser对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    XbrlSmUser selectXbrlSmUserByObj(XbrlSmUser obj);
	/**
	 * 通过XbrlSmUser的id获得XbrlSmUser对象
	 * @param id
	 * @return
	 */
    XbrlSmUser selectXbrlSmUserById(int id);
    /**
     * 获取用户关联的角色列表
     * @param userId 用户id
     * @author yubo.li@changhong.com
     * @date 2017/11/20 0020
     * @return
     */
    List<XbrlSmRole> selectXbrlSmRoleByUserId(int userId);
	/**
	 * 获取用户关联的角色列表
	 * @param userId 用户id
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<XbrlSmCompany> selectXbrlSmCompanyByUserId(int userId);
	/**
	 * 插入XbrlSmUser到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertXbrlSmUser(XbrlSmUser value);
	/**
	 * 插入XbrlSmUser中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyXbrlSmUser(XbrlSmUser value);
	/**
	 * 通过XbrlSmUser的id删除XbrlSmUser
	 * @param id
	 * @return
	 */
    int deleteXbrlSmUserById(int id);
	/**
	 * 通过XbrlSmUser的ids删除XbrlSmUser
	 * @param ids
	 * @return
	 */
	int deleteXbrlSmUserByIds(String[] ids);
	/**
	 * 通过XbrlSmUser的id更新XbrlSmUser中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateXbrlSmUserById(XbrlSmUser enti);
	/**
	 * 通过XbrlSmUser的id更新XbrlSmUser中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyXbrlSmUserById(XbrlSmUser enti);

    /**
     * 重置用户密码和可用状态
     * @param list
     * @author yubo.li@changhong.com
     * @date 2017/12/21 0021
     * @return
     */
    int updatePwdStateBatch(List<XbrlSmUser> list);

    /**
	 * 用户禁用
	 * @param ids 用户id列表
	 * @param state 1:启用；0:禁用
	 * @return
	 */
    int disableOrEnableXbrlSmUser(@Param("ids") List<String> ids, @Param("state") Integer state);

	/**
	 * 用户名字称重复
	 * @param name
	 * @return
	 */
    Long getRepeatedNameCount(String name);

	/**
	 * 用户工号重复
	 * @param code
	 * @return
	 */
	Long getRepeatedCodeCount(String code);

	/**
	 * 邮箱重复
	 * @param mail
	 * @return
	 */
	Long getRepeatedMailCount(String mail);

	/**
	 * 根据角色查询该角色所有人员信息
	 * @param id
	 * @return
	 */
	List<XbrlSmUser> selectXbrlSmUserByRoleId(int id);



	/**
	 * 查询用户信息和公司id(分页模糊查询)
	 * @param companyId
	 * @param key
	 * @param start
	 * @param end
	 * @author yubo.li@changhong.com
	 * @date 2017/12/8 0008
	 * @return
	 */
	List<Map<String, Object>> selectUserForCompanyPage(Integer companyId, String key, Integer start, Integer end);

	/**
	 * 根据公司查询该角色所有人员信息
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectUserByCompanyId(int id);


	/**
     * 查询用户信息和角色id
     * @author yubo.li@changhong.com
     * @date 2017/12/8 0008
     * @return
     */
	List<Map<String, Object>> selectUserForRole();

	/**
	 * 查询用户信息和角色id(分页模糊查询)
	 * @param roleId
	 * @param key
	 * @param start
	 * @param end
	 * @author yubo.li@changhong.com
	 * @date 2017/12/8 0008
	 * @return
	 */
	List<Map<String, Object>> selectUserForRolePage(Integer roleId, String key, Integer start, Integer end);
	/**
	 * 根据角色查询该角色所有人员信息
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectUserByRoleId(int id);

	/**
	 * 查询所有公司，并对用户ID关联的公司标记
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectFlagCompanyByUserId(int id);

	/**
	 * 查询所有角色，并对用户ID关联的角色标记
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectFlagRoleByUserId(int id);

}