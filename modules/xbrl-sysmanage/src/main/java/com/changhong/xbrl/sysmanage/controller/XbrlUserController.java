package com.changhong.xbrl.sysmanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.changhong.xbrl.sysmanage.client.UserServiceClient;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompanyUser;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUser;
import com.changhong.xbrl.sysmanage.model.*;
import com.changhong.xbrl.sysmanage.service.*;
import com.changhong.xbrl.sysmanage.util.AesEncryptUtil;
import com.changhong.xbrl.sysmanage.util.ServiceConstants;
import com.changhong.xbrl.sysmanage.util.SystemConstants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value = "/api/user")
public class XbrlUserController {

	@Autowired
	private XbrlSmUserService xbrlSmUserService;
	@Autowired
	private XbrlSmRoleService xbrlSmRoleService;
	@Autowired
	private XbrlSmCompanyService xbrlSmCompanyService;
	@Autowired
	private XbrlSmCompanyUserService xbrlSmCompanyUserService;
	@Autowired
	private XbrlSmUserRoleService xbrlSmUserRoleService;

	@Autowired
	private UserServiceClient userServiceClient;

	@Value("${decrypt.aes.key}")
	private String aeskey;

	@Value("${decrypt.aes.iv}")
	private String aesiv;

	@RequestMapping("/test")
	public String usertest(){
		return  "user info";
	}

    /**
     *  分页查询
     * @param key 关键字
     * @param pageNumber 页码
     * @param pagzSize 每页显示条数
     * @param sortType 排序字段
     * @param direction 排序方式
     * @return 返回带条件下的分页查询结果
     */
	@ApiOperation(value = "用户列表分页查询", notes = "用户列表分页查询")
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ObjectPageDataResp get(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("关键字") @RequestParam(value = "key", defaultValue = "") String key,
			@ApiParam("页号") @RequestParam(value = "pageno", defaultValue = "1") Integer pageNumber,
			@ApiParam("页大小") @RequestParam(value = "pagesize", defaultValue = "10") Integer pagzSize,
			@ApiParam("排序字段") @RequestParam(value = "sort", defaultValue = "auto") String sortType,
			@ApiParam("排序方向") @RequestParam(value = "direct", defaultValue = "asc") String direction) {
		List<XbrlSmUser> userList = xbrlSmUserService.selectXbrlSmUserPage(key,pageNumber,pagzSize,sortType,direction);
		Long totalSize = xbrlSmUserService.getXbrlSmUserRowCount(key);
		ObjectPageDataResp<XbrlSmUser> collectionResp = new ObjectPageDataResp<XbrlSmUser>();
		collectionResp.setDataSet(userList);
		collectionResp.setPageNo(pageNumber);
		collectionResp.setPageSize(pagzSize);
		collectionResp.setTotalNum(totalSize.intValue());
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}


	/**
	 * 编辑用户信息
	 * @param id
	 * @return 返回编辑用户页
	 */
	@ApiOperation(value = "编辑用户信息", notes = "编辑用户信息")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespEntity updateForm(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户信息") XbrlSmUserVo userVo) {
		BaseRespEntity resp = new BaseRespEntity();
		resp.setId(id);
		try {
			if (vlidUserInfo(userVo, resp, 1)) return resp;
			XbrlSmUser user = new XbrlSmUser();
			BeanUtils.copyProperties(userVo, user);
			Date date = new Date();
			user.setUpdateTime(date);
			//更新用户
			xbrlSmUserService.updateNonEmptyXbrlSmUserById(user);
			//更新用户公司关系及用户角色关系
			xbrlSmCompanyUserService.deleteXbrlSmCompanyUserById(userVo.getId());
			xbrlSmUserRoleService.deleteXbrlSmUserRoleById(user.getId());
			insertCompanyAndRoleInfo(userVo, user);
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc("保存成功");
		}catch (Exception e){
			e.printStackTrace();
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("保存失败");
		}
		return resp;
	}

	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "重置密码", notes = "重置密码")
	@RequestMapping(value = "resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespEntity resetPwd(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户id列表") @RequestParam(value = "userIds") String userIds) {
		BaseRespEntity resp = new BaseRespEntity();
		resp.setId(id);
		try {
			String[] arr = StringUtils.split(userIds, SystemConstants.comma);
			if(arr != null) {
				ArrayList<XbrlSmUser> list = new ArrayList<XbrlSmUser>();
				for (String a : arr) {
					XbrlSmUser user = new XbrlSmUser();
					user.setId(Integer.valueOf(a));
					user.setPassword("e10adc3949ba59abbe56e057f20f883e");
					user.setState("1");
					list.add(user);
				}
				xbrlSmUserService.resetUserPwdStatereset(list);
			}
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc("重置成功");
		}catch (Exception e){
			e.printStackTrace();
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("重置失败");
		}
		return resp;
	}


	/**
	 * 新增用户信息
	 * @param id 请求序列号
	 * @return 返回保存成功或者失败信息
	 */
	@ApiOperation(value = "新增用户信息", notes = "新增用户信息")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespEntity save(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户信息") XbrlSmUserVo userVo) {
		BaseRespEntity resp = new BaseRespEntity();
		resp.setId(id);
		try {
			if (vlidUserInfo(userVo, resp, 0)) return resp;
			XbrlSmUser user = new XbrlSmUser();
			BeanUtils.copyProperties(userVo, user);
			user.setState("1");//默认生效
			Date date = new Date();
			user.setCreateTime(date);
			user.setUpdateTime(date);
			//保存用户
			xbrlSmUserService.insertXbrlSmUser(user);
			insertCompanyAndRoleInfo(userVo, user);
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc("保存成功");
		}catch (Exception e){
			e.printStackTrace();
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("保存失败");
		}
		return resp;
	}

	/**
	 * 配置用户公司关系
	 * @param id 请求序列号
	 * @return 返回保存成功或者失败信息
	 */
	@ApiOperation(value = "配置用户公司关系", notes = "配置用户公司关系")
	@RequestMapping(value = "configCompanyUserRelation", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespEntity configCompanyUserRelation(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("请求流水号") @RequestParam(value = "companyId") Integer companyId,
			@ApiParam("请求流水号") @RequestParam(value = "userIds") String userIds) {
		BaseRespEntity resp = new BaseRespEntity();
		resp.setId(id);
		try {
			xbrlSmCompanyUserService.deleteXbrlSmCompanyUserByCompanyId(companyId);
			//更新用户公司关系
			String[] arr = StringUtils.split(userIds, SystemConstants.comma);
			ArrayList<XbrlSmCompanyUser> list = new ArrayList<XbrlSmCompanyUser>();
			for(String a : arr) {
				XbrlSmCompanyUser companyUser = new XbrlSmCompanyUser();
				companyUser.setCompanyId(companyId);
				companyUser.setUserId(Integer.valueOf(a));
				list.add(companyUser);
			}
			xbrlSmCompanyUserService.insertXbrlSmCompanyUserBatch(list);
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc("配置成功");
		}catch (Exception e){
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("配置失败");
		}
		return resp;
	}

	/**
	 * 更新用户公司关系及用户角色关系
	 * @param userVo
	 * @param user
	 * @author yubo.li@changhong.com
	 * @date 2017/12/6 0006
	 * @return
	 */
	private void insertCompanyAndRoleInfo(XbrlSmUserVo userVo, XbrlSmUser user) {
		//更新用户公司关系
		String[] arr = StringUtils.split(userVo.getCompanyIds(), SystemConstants.comma);
		if(arr != null) {
			ArrayList<XbrlSmCompanyUser> list = new ArrayList<XbrlSmCompanyUser>();
			for (String a : arr) {
				XbrlSmCompanyUser companyUser = new XbrlSmCompanyUser();
				companyUser.setCompanyId(Integer.valueOf(a));
				companyUser.setUserId(user.getId());
				list.add(companyUser);
			}
			xbrlSmCompanyUserService.insertXbrlSmCompanyUserBatch(list);
		}
		//更新用户角色关系
		String[] arr1 = StringUtils.split(userVo.getRoleIds(), SystemConstants.comma);
		if(arr1 != null) {
			int[] intArr = new int[arr1.length];
			for (int i = 0; i < arr1.length; i++) {
				String a = arr1[i];
				intArr[i] = Integer.valueOf(a);
			}
			xbrlSmUserRoleService.saveUserForRole(intArr, user.getId());
		}
	}

	/**
	 * 判断用户信息重复性
	 * @param userVo
	 * @param resp
	 * @param rightCount
	 * @author yubo.li@changhong.com
	 * @date 2017/12/6 0006
	 * @return
	 */
	private boolean vlidUserInfo(XbrlSmUserVo userVo, BaseRespEntity resp, int rightCount) {
		//判定用户名唯一
		Long count = xbrlSmUserService.getRepeatedNameCount(userVo.getName());
		if(count > rightCount){
            resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
            resp.setRspDesc("用户名已使用");
        }
		//判定用户登录名唯一
		Long count1 = xbrlSmUserService.getRepeatedCodeCount(userVo.getUserCode());
		if(count1 > rightCount){
            resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
            String tmp = "员工号已使用";
            String desc = StringUtils.isBlank(resp.getRspDesc()) ? tmp : SystemConstants.comma+tmp;
            resp.setRspDesc(resp.getRspDesc()+desc);
        }
		//判定邮箱唯一
		Long count2 = xbrlSmUserService.getRepeatedMailCount(userVo.getEmail());
		if(count1 > rightCount){
            resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
            String tmp = "邮箱已使用";
            String desc = StringUtils.isBlank(resp.getRspDesc()) ? tmp : SystemConstants.comma+tmp;
            resp.setRspDesc(resp.getRspDesc()+desc);
        }
		if(resp.getRspCode() != null){
			return true;
        }
		return false;
	}


	/**
	 * 根据ID批量删除用户
	 * @param id
	 * @param ids
	 * @return 返回删除成功或者失败信息
	 */
	@ApiOperation(value = "根据ID批量删除用户", notes = "根据ID批量删除用户")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespEntity delete(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("多用户id字符串，以逗号分隔") @RequestParam(value = "ids", defaultValue = "") String ids) {
		BaseRespEntity resp = new BaseRespEntity();
		resp.setId(id);
		try {
			if(StringUtils.isNotEmpty(ids)){
				//删除用户公司关系及用户角色关系
				xbrlSmCompanyUserService.deleteXbrlSmCompanyUserByUserIds(ids);
				xbrlSmUserRoleService.deleteXbrlSmUserRoleByUserId(ids);
				xbrlSmUserService.deleteXbrlSmUserByIds(ids);
				resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
				resp.setRspDesc("删除成功");
			}else {
				resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
				resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_ERR8);
			}
		}catch (Exception e){
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("删除失败");
		}
		return resp;
	}

	/**
	 * 获取角色信息
	 * @return 返回角色信息，符合双向选择器的格式
	 */
	@ApiOperation(value = "获取角色信息", notes = "获取角色信息")
	@RequestMapping(value = "getXbrlUserRoles", method = RequestMethod.POST)
	@ResponseBody
	public ObjectCollectionResp getXbrlRoles(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户id") @RequestParam(value="userId") Integer userId) {
		List<XbrlSmRole> list = xbrlSmUserService.selectXbrlSmRoleByUserId(userId);
		ObjectCollectionResp<XbrlSmRole> collectionResp = new ObjectCollectionResp<XbrlSmRole>(list);
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}

	/**
	 * 获取公司信息
	 * @return 返回公司信息，符合双向选择器的格式
	 */
	@ApiOperation(value = "获取公司信息", notes = "获取公司信息")
	@RequestMapping(value = "getXbrlUserCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public ObjectCollectionResp getXbrlCompanyList(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户id") @RequestParam(value="userId") Integer userId) {
		List<XbrlSmCompany> list = xbrlSmUserService.selectXbrlSmCompanyByUserId(userId);
		ObjectCollectionResp<XbrlSmCompany> collectionResp = new ObjectCollectionResp<XbrlSmCompany>(list);
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}


	/**
	 * 用户名字称重复
	 * @param id
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "用户名字称重复", notes = "用户名字称重复")
	@RequestMapping(value = "checkUniqueName", method = RequestMethod.POST)
	@ResponseBody
	public ObjectDataResp checkUniqueName(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户id") @RequestParam(value = "userId") Integer userId,
			@ApiParam("用户登录名") @RequestParam(value = "name") String name) {
		JSONObject data = new JSONObject();
		ObjectDataResp<JSONObject> resp = new ObjectDataResp<JSONObject>(data);
		resp.setId(id);
		Long count = xbrlSmUserService.getRepeatedNameCount(name);
		if(userId != null && userId != 0 && count > 1){
			data.put("isUnique",0);
		}else if((userId == null || userId == 0) && count > 0){
			data.put("isUnique",0);
		}else{
			data.put("isUnique",1);
		}
		resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return resp;
	}

	/**
	 * 用户工号重复
	 * @param id
	 * @param code
	 * @return , method = RequestMethod.POST
	 */
	@ApiOperation(value = "用户工号重复", notes = "用户工号重复")
	@RequestMapping(value = "checkUniqueCode", method = RequestMethod.POST)
	@ResponseBody
	public Object checkUniqueCode(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户id") @RequestParam(value = "userId") Integer userId,
			@ApiParam("员工号") @RequestParam(value = "code") String code) {
		JSONObject data = new JSONObject();
		ObjectDataResp<JSONObject> resp = new ObjectDataResp<JSONObject>(data);
		resp.setId(id);
		Long count = xbrlSmUserService.getRepeatedCodeCount(code);
		if(userId != null && userId != 0 && count > 1){
			data.put("isUnique",0);
		}else if((userId == null || userId == 0) && count > 0){
			data.put("isUnique",0);
		}else{
			data.put("isUnique",1);
		}
		resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return resp;
	}

	/**
	 * 用户批量启用/禁用
	 * @param id
	 * @param ids
	 * @param state 1:启用；0:禁用
	 * @return , method = RequestMethod.POST
	 */
	@ApiOperation(value = "用户批量启用/禁用", notes = "用户批量启用/禁用")
	@RequestMapping(value = "disableOrEnableUser", method = RequestMethod.POST)
	@ResponseBody
	public Object disableOrEnableUser(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户ids，以逗号分隔") @RequestParam(value = "ids") String ids,
			@ApiParam("用户状态state") @RequestParam(value = "state") Integer state
			) {
		JSONObject data = new JSONObject();
		ObjectDataResp<JSONObject> resp = new ObjectDataResp<JSONObject>(data);
		resp.setId(id);
		String[] idStrArr = StringUtils.split(ids, SystemConstants.comma);
		if(idStrArr.length > 0) {
			xbrlSmUserService.disableOrEnableXbrlSmUser(Arrays.asList(idStrArr), state);
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		}else {
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR61);
			resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_ERR61+ServiceConstants.param_ids);
		}
		return resp;
	}

	/**
	 * 获取已选角色的用户信息
	 * @return 用户信息列表
	 */
	@ApiOperation(value = "获取已选角色的用户信息", notes = "获取已选角色的用户信息")
	@RequestMapping(value = "getUserForRole", method = RequestMethod.GET)
	@ResponseBody
	public ObjectPageDataResp getUserForRole(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("角色id") @RequestParam(value = "roleId") Integer roleId,
			@ApiParam("关键字") @RequestParam(value = "key", defaultValue = "") String key,
			@ApiParam("页号") @RequestParam(value = "pageno", defaultValue = "1") Integer pageNumber,
			@ApiParam("页大小") @RequestParam(value = "pagesize", defaultValue = "10") Integer pagzSize) {
		List<Map<String, Object>> list = xbrlSmUserService.selectUserForRolePage(roleId,key,pageNumber,pagzSize);
		List<Map<String, Object>> selectedList = xbrlSmUserService.selectUserByRoleId(roleId);
		Long totalSize = xbrlSmUserService.getXbrlSmUserRowCount(key);
		ObjectPageDataResp<Map<String, Object>> collectionResp = new ObjectPageDataResp<Map<String, Object>>();
		for(Map<String, Object> l : list){
			Object tmp = l.get("roleId");
			if(roleId.equals(tmp)){
				l.remove("roleId");
				l.put("flag", 1);
			}else{
				l.remove("roleId");
				l.put("flag", 0);
			}
		}
		collectionResp.setDataSet(list);
		collectionResp.setSelectedSet(selectedList);
		collectionResp.setPageNo(pageNumber);
		collectionResp.setPageSize(pagzSize);
		collectionResp.setTotalNum(totalSize.intValue());
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}

	/**
	 * 获取已选用户的角色信息
	 * @return 用户信息列表
	 */
	@ApiOperation(value = "获取已选用户的角色信息", notes = "获取已选用户的角色信息")
	@RequestMapping(value = "getRoleForUser", method = RequestMethod.GET)
	@ResponseBody
	public ObjectCollectionResp getRoleForUser(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("角色id") @RequestParam(value = "userId") Integer userId) {
		List<Map<String, Object>> list = xbrlSmUserService.selectFlagRoleByUserId(userId);
		for(Map<String, Object> l : list){
			Object tmp = l.get("userId");
			if(userId.equals(tmp)){
				l.remove("userId");
				l.put("flag", 1);
			}else{
				l.remove("userId");
				l.put("flag", 0);
			}
		}
		ObjectCollectionResp<Map<String, Object>> collectionResp = new ObjectCollectionResp<Map<String, Object>>(list);
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}

	/**
	 * 获取已选公司的用户信息
	 * @return 用户信息列表
	 */
	@ApiOperation(value = "获取已选公司的用户信息", notes = "获取已选公司的用户信息")
	@RequestMapping(value = "getUserForCompany", method = RequestMethod.GET)
	@ResponseBody
	public ObjectPageDataResp getUserForCompany(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("公司id") @RequestParam(value = "companyId") Integer companyId,
			@ApiParam("关键字") @RequestParam(value = "key", defaultValue = "") String key,
			@ApiParam("页号") @RequestParam(value = "pageno", defaultValue = "1") Integer pageNumber,
			@ApiParam("页大小") @RequestParam(value = "pagesize", defaultValue = "10") Integer pagzSize) {
		List<Map<String, Object>> list = xbrlSmUserService.selectUserForCompanyPage(companyId,key,pageNumber,pagzSize);
		List<Map<String, Object>> selectedList = xbrlSmUserService.selectUserByCompanyId(companyId);
		Long totalSize = xbrlSmUserService.getXbrlSmUserRowCount(key);
		ObjectPageDataResp<Map<String, Object>> collectionResp = new ObjectPageDataResp<Map<String, Object>>();
		for(Map<String, Object> l : list){
			Object tmp = l.get("companyId");
			if(companyId.equals(tmp)){
				l.remove("companyId");
				l.put("flag", 1);
			}else{
				l.remove("companyId");
				l.put("flag", 0);
			}
		}
		collectionResp.setDataSet(list);
		collectionResp.setSelectedSet(selectedList);
		collectionResp.setPageNo(pageNumber);
		collectionResp.setPageSize(pagzSize);
		collectionResp.setTotalNum(totalSize.intValue());
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}

	/**
	 * 获取已选公司的用户信息
	 * @return 用户信息列表
	 */
	@ApiOperation(value = "获取已选用户的公司信息", notes = "获取已选用户的公司信息")
	@RequestMapping(value = "getCompanyForUser", method = RequestMethod.GET)
	@ResponseBody
	public ObjectCollectionResp getCompanyForUser(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("用户id") @RequestParam(value = "userId") Integer userId) {
		List<Map<String, Object>> list = xbrlSmUserService.selectFlagCompanyByUserId(userId);

		for(Map<String, Object> l : list){
			Object tmp = l.get("userId");
			if(userId.equals(tmp)){
				l.remove("userId");
				l.put("flag", 1);
			}else{
				l.remove("userId");
				l.put("flag", 0);
			}
		}
		ObjectCollectionResp<Map<String, Object>> collectionResp = new ObjectCollectionResp<Map<String, Object>>(list);
		collectionResp.setId(id);
		collectionResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		collectionResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		return collectionResp;
	}

	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return , method = RequestMethod.POST
	 */
	@ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/getUserByToken",method = RequestMethod.GET)
	@ResponseBody
	public XbrlSmUserNoResourceResp getUserByToken(@RequestParam(value = "token") String token){
		XbrlSmUserNoResourceResp resp = null;
		String userInfoResp = userServiceClient.getXbrlSmUserInfoByToken(token);
		try {
		   String userjson = AesEncryptUtil.aesDecrypt(userInfoResp, aeskey, aesiv);
		   resp=(XbrlSmUserNoResourceResp)JSONObject.parseObject(userjson, XbrlSmUserNoResourceResp.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  resp;
	}


	/**
	 * 根据token获取用户权限信息
	 * @param token
	 * @return , method = RequestMethod.POST
	 */
	@ApiOperation(value = "根据token获取用户权限信息", notes = "根据token获取用户权限信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/getXbrlSmUser",method = RequestMethod.GET)
	@ResponseBody
	public XbrlSmUserResp getXbrlSmUser(@RequestParam(value = "token") String token){
		XbrlSmUserResp resp = null;
		String authInfoResp = userServiceClient.getXbrlSmUser(new Date().getTime(),token,44);
		try {
			String userjson = AesEncryptUtil.aesDecrypt(authInfoResp, aeskey, aesiv);
			resp=(XbrlSmUserResp)JSONObject.parseObject(userjson, XbrlSmUserResp.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  resp;
	}

}
