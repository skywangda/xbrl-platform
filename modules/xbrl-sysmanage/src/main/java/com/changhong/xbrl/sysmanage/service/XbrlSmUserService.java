package com.changhong.xbrl.sysmanage.service;

import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUser;

import java.util.List;
import java.util.Map;

public interface XbrlSmUserService{
	/**
	 * 获得XbrlSmUser数据的总行数
	 * @return
	 */
    long getXbrlSmUserRowCount(String key);
	/**
	 * 获得XbrlSmUser数据集合
	 * @return
	 */
    List<XbrlSmUser> selectXbrlSmUser();
	/**
	 * 获得XbrlSmUser数据集合(分页)
	 * @return
	 */
	List<XbrlSmUser> selectXbrlSmUserPage(String key, Integer pageNumber, Integer pagzSize, String sortType, String direction);
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
	 * 依据用户id查询角色列表
	 * @param id
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
    List<XbrlSmRole> selectXbrlSmRoleByUserId(int id);

	/**
	 * 依据角色id查询用户列表
	 * @param id
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<XbrlSmUser> selectXbrlSmUserByRoleId(int id);

	/**
	 * 依据用户id查询公司列表
	 * @param id
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<XbrlSmCompany> selectXbrlSmCompanyByUserId(int id);
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
	 * 通过XbrlSmUser的id删除XbrlSmUser
	 * @param ids
	 * @return
	 */
	int deleteXbrlSmUserByIds(String ids);
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
	int resetUserPwdStatereset(List<XbrlSmUser> list);

	/**
	 * 用户禁用
	 * @param ids 用户id列表
	 * @param state 1:启用；0:禁用
	 * @return
	 */
	int disableOrEnableXbrlSmUser(List<String> ids, Integer state);
	/**
	 * 用户名字称重复统计数
	 * @param name
	 * @return
	 */
    Long getRepeatedNameCount(String name);
	/**
	 * 用户工号重复统计数
	 * @param code
	 * @return
	 */
	Long getRepeatedCodeCount(String code);

	/**
	 * 邮箱重复统计数
	 * @param mail
	 * @return
	 */
	Long getRepeatedMailCount(String mail);

	/**
	 * 查询用户信息和公司id(分页模糊查询)
	 * @param companyId
	 * @param key
	 * @param pageNumber
	 * @param pagzSize
	 * @author yubo.li@changhong.com
	 * @date 2017/12/8 0008
	 * @return
	 */
	List<Map<String, Object>> selectUserForCompanyPage(Integer companyId, String key, Integer pageNumber, Integer pagzSize);

	/**
	 * 依据公司id查询用户列表
	 * @param companyId
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<Map<String, Object>> selectUserByCompanyId(Integer companyId);

	/**
	 * 查询用户信息和角色id(分页模糊查询)
	 * @param roleId
	 * @param key
	 * @param pageNumber
	 * @param pagzSize
	 * @author yubo.li@changhong.com
	 * @date 2017/12/8 0008
	 * @return
	 */
	List<Map<String, Object>> selectUserForRolePage(Integer roleId, String key, Integer pageNumber, Integer pagzSize);

	/**
	 * 查询用户信息和角色id
	 * @author yubo.li@changhong.com
	 * @date 2017/12/8 0008
	 * @return
	 */
	List<Map<String, Object>> selectUserForRole();

	/**
	 * 依据角色id查询用户列表
	 * @param roleId
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<Map<String, Object>> selectUserByRoleId(Integer roleId);

	/**
	 * 查询所有公司，并对用户ID关联的公司标记
	 * @param userId
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<Map<String, Object>> selectFlagCompanyByUserId(Integer userId);

	/**
	 * 查询所有角色，并对用户ID关联的角色标记
	 * @param userId
	 * @author yubo.li@changhong.com
	 * @date 2017/11/20 0020
	 * @return
	 */
	List<Map<String, Object>> selectFlagRoleByUserId(Integer userId);


}