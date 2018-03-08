package com.changhong.xbrl.sysmanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;
import com.changhong.xbrl.sysmanage.model.BaseRespEntity;
import com.changhong.xbrl.sysmanage.model.JsonObjResp;
import com.changhong.xbrl.sysmanage.model.ObjectDataResp;
import com.changhong.xbrl.sysmanage.model.ObjectPageDataResp;
import com.changhong.xbrl.sysmanage.service.XbrlSmResourceService;
import com.changhong.xbrl.sysmanage.service.XbrlSmRoleResourceService;
import com.changhong.xbrl.sysmanage.util.ServiceConstants;
import com.changhong.xbrl.sysmanage.util.SystemConstants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping(value = "/api/resource")
public class XbrlResourceController {

	@Autowired
	private XbrlSmResourceService xbrlSmResourceService;
	@Autowired
	private XbrlSmRoleResourceService xbrlSmRoleResourceService;



	@ApiOperation(value = "获取当前父级下所有的子级资源列表分页查询", notes = "获取当前父级下所有的子级资源列表分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentId", value = "父级ID", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pagenumber", value = "页号", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pagesize", value = "页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "name", value = "资源名称", dataType = "String")
	})
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ObjectPageDataResp list( @RequestParam(value = "parentId", required = true ) int parentId,
									@RequestParam(value = "pagenumber", defaultValue = "1") int pageNumber,
									@RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
									@RequestParam(value = "name", required = false ) String name) {
		ObjectPageDataResp<XbrlSmResource> resourcelistResp =new ObjectPageDataResp<XbrlSmResource>();
		HashMap<String, Object> searchParamsMap=new HashMap<String,Object>();
		searchParamsMap.put("startNumber",(pageNumber-1)*pageSize);
		searchParamsMap.put("pagzSize",pageSize);
		List<XbrlSmResource> xbrlSmResourceList= xbrlSmResourceService.selectXbrlSmResourceList(searchParamsMap);
		int countnum = xbrlSmResourceService.getCount(searchParamsMap);
		resourcelistResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		resourcelistResp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		resourcelistResp.setPageNo(pageNumber);
		resourcelistResp.setPageSize(pageSize);
		resourcelistResp.setDataSet(xbrlSmResourceList);
		resourcelistResp.setTotalNum(countnum);

		return resourcelistResp;
	}


	@ApiOperation(value = "资源列表子节点获取", notes = "资源列表子节点获取")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentId", value = "父级ID", required = true, dataType = "int"),
	})
	@RequestMapping(value = "getResourceListByParentId", method = RequestMethod.GET)
	@ResponseBody
	public List<XbrlSmResource> getResourceListByParentId(
			@RequestParam(value = "parentId", defaultValue = "0") int parentId,
			@ApiParam("关键字") @RequestParam(value = "key", defaultValue = "") String key) {
		ObjectPageDataResp<XbrlSmResource> resourcelistResp =new ObjectPageDataResp<>();
			List<XbrlSmResource> resourceList=xbrlSmResourceService.getXbrlSmResourceAllByParentId(parentId,key,2);
		return resourceList;
	}


	@ApiOperation(value = "资源列表子节点获取", notes = "资源列表子节点获取")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色Id", required = true, dataType = "int")
	})
	@RequestMapping(value = "getResourceListByRoleId", method = RequestMethod.GET)
	@ResponseBody
	public List<XbrlSmResource> getResourceListByRoleId(@RequestParam(value = "roleId", defaultValue = "0") int roleId){
		List<XbrlSmResource> resourceList=xbrlSmResourceService.getXbrlSmResourceAllByRoleId(roleId);
		return resourceList;
	}



	@ApiOperation(value = "新增资源信息信息", notes = "新增资源信息信息")
	@ApiImplicitParams({@ApiImplicitParam(name = "XbrlSmResource", value = "资源对象", required = true, dataType = "XbrlSmResource")})
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjResp save(XbrlSmResource xbrlSmResource){
		JsonObjResp resp = new JsonObjResp();
		if (xbrlSmResource!=null) {
			List<XbrlSmResource> resourceList = xbrlSmResourceService.getXbrlSmResourceListByNameAndUrl(xbrlSmResource.getName(), xbrlSmResource.getUrl());
			if (resourceList.size()>0){
				resp.setRspCode("100001");
				resp.setRspDesc("资源已经存在");
				return resp;
			}
			//保存资源信息
			try {
				//暂定 角色未admin
				xbrlSmResource.setCreateUser("admin");
				long roleresult = xbrlSmResourceService.saveXbrlSmResource(xbrlSmResource);
			} catch (Exception ex) {
				resp.setRspCode("100001");
				resp.setRspDesc("保存资源失败：原因" + ex.getMessage());
				return resp;
			}
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc("保存资源成功");
		}
		return resp;
	}

	@ApiOperation(value = "资源信息信息", notes = "修改资源信息信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "XbrlSmResource", value = "资源对象", required = true, dataType = "XbrlSmResource")
	})
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjResp update(XbrlSmResource xbrlSmResource){
		JsonObjResp resp = new JsonObjResp();
		if(StringUtils.isBlank(xbrlSmResource.getName())
				|| StringUtils.isBlank(xbrlSmResource.getUrl()))
		{
			resp.setRspCode("100001");
			resp.setRspDesc("资源名称不能为空");
			return resp;
		}
		XbrlSmResource updateXbrlSm=xbrlSmResourceService.getXbrlSmResourceById(xbrlSmResource.getId());
		if(updateXbrlSm == null){
			resp.setRspCode("100001");
			resp.setRspDesc("[id="+updateXbrlSm.getId()+"]的资源不存在");
			return resp;
		}
		//保存角色信息
		try {
			xbrlSmResource.setCreateUser(updateXbrlSm.getCreateUser());
			xbrlSmResource.setCreateTime(updateXbrlSm.getCreateTime());
			xbrlSmResource.setUpdateUser("admin");
			xbrlSmResource.setUpdateTime(new Date());
			long roleresult = xbrlSmResourceService.updateXbrlSmResource(xbrlSmResource);
		} catch (Exception ex) {
			resp.setRspCode("100001");
			resp.setRspDesc("修改资源失败：原因" + ex.getMessage());
			return resp;
		}
		resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		resp.setRspDesc("修改资源成功");
		return resp;
	}


	/**
	 * 根据ID批量删除用户
	 * @param id
	 * @param ids
	 * @return 返回删除成功或者失败信息
	 */
	@ApiOperation(value = "根据ID批量删除资源", notes = "根据url的ids来批量删除资源")
	@ApiImplicitParam(name = "ids", value = "角色ID列表", required = true, dataType = "String")
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	@ResponseBody
	public BaseRespEntity delete(
			@RequestParam(value = "id", defaultValue = "1") String id,
			@RequestParam(value = "ids", defaultValue = "") String ids) {
		BaseRespEntity resp = new BaseRespEntity();
		resp.setId(id);
		try {
			if(StringUtils.isNotEmpty(ids)){
				String[] idArr = ids.split(",");
				xbrlSmResourceService.deleteXbrlSmResourceByIds(idArr);
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
	 * 用户批量启用/禁用
	 * @param id
	 * @param ids
	 * @param state 1:启用；0:禁用
	 * @return , method = RequestMethod.POST
	 */
	@ApiOperation(value = "资源批量启用/禁用", notes = "资源批量启用/禁用")
	@RequestMapping(value = "disableOrEnableResource", method = RequestMethod.POST)
	@ResponseBody
	public Object disableOrEnableUser(
			@ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
			@ApiParam("资源ids，以逗号分隔") @RequestParam(value = "ids") String ids,
			@ApiParam("资源状态state") @RequestParam(value = "state") Integer state
	) {
		JSONObject data = new JSONObject();
		ObjectDataResp<JSONObject> resp = new ObjectDataResp<JSONObject>(data);
		resp.setId(id);
		String[] idStrArr = StringUtils.split(ids, SystemConstants.comma);
		if(idStrArr.length > 0) {
			xbrlSmResourceService.disableOrEnableXbrlSmResource(Arrays.asList(idStrArr), state);
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
			resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
		}else {
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR61);
			resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_ERR61+ServiceConstants.param_ids);
		}
		return resp;
	}
	
}
