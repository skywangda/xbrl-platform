package com.changhong.xbrl.sysmanage.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRoleResource;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUser;
import com.changhong.xbrl.sysmanage.model.JsonObjResp;
import com.changhong.xbrl.sysmanage.model.ObjectPageDataResp;
import com.changhong.xbrl.sysmanage.service.*;
import com.changhong.xbrl.sysmanage.util.ServiceConstants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/api/role")
public class XbrlRoleController {

    @Autowired
    private XbrlSmResourceService xbrlSmResourceService;
    @Autowired
    private XbrlSmRoleService xbrlSmRoleService;
    @Autowired
    private XbrlSmUserService xbrlSmUserService;
    @Autowired
    private XbrlSmRoleResourceService xbrlSmRoleResourceService;
    @Autowired
    private XbrlSmUserRoleService xbrlSmUserRoleService;

    /**
     * 角色列表分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   每页显示条数
     * @param name   排序字段
     * @param state  排序方式
     * @return 返回带条件下的分页查询结果
     */
    @ApiOperation(value = "角色列表分页查询", notes = "角色列表分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pagenumber", value = "页号", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pagesize", value = "页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "排序字段", required =false ,dataType = "String"),
            @ApiImplicitParam(name = "state", value = "排序方向",required =false, dataType = "String")
    })
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ObjectPageDataResp list(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "state", required = false) String state) {

        ObjectPageDataResp<XbrlSmRole>  rolelistResp =new ObjectPageDataResp<XbrlSmRole>();
        HashMap<String, Object> searchParamsMap=new HashMap<String,Object>();
        searchParamsMap.put("startNumber",(pageNumber-1)*pageSize);
        searchParamsMap.put("pagzSize",pageSize);
        if(StringUtils.isNotEmpty(name)){
            searchParamsMap.put("name",name);
        }
        if(StringUtils.isNotEmpty(state)){
            searchParamsMap.put("state",state);
        }
        //获取数据列表
        List<XbrlSmRole> roleList=xbrlSmRoleService.selectXbrlSmRoleList(searchParamsMap);
        //获取总数据条数
        int countnum = xbrlSmRoleService.getCount(searchParamsMap);
        rolelistResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        rolelistResp.setRspDesc("请求成功");
        rolelistResp.setPageNo(pageNumber);
        rolelistResp.setPageSize(pageSize);
        rolelistResp.setDataSet(roleList);
        rolelistResp.setTotalNum(countnum);
        return rolelistResp;
    }

    /**
     * 角色列表分页查询
     *
     * @return 返回带条件下的分页查询结果
     */
    @ApiOperation(value = "角色列表", notes = "角色列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "人员ID", required = false, dataType = "Long", paramType ="query" )
    })
    @RequestMapping(value = "shortlist", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,JSONArray> shortlist(@RequestParam(value = "userId", required = false) Integer userId) {
        HashMap<String,JSONArray> rolelistResp=new HashMap<String,JSONArray>();
        //获取数据列表
        JSONArray roleall =new JSONArray();
        List<XbrlSmRole> roleList=xbrlSmRoleService.selectXbrlSmRoleList();
        for(XbrlSmRole item : roleList){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("key",item.getId().toString());
            jsonObj.put("value",item.getName());
            roleall.add(jsonObj);
        }
        JSONArray rolecheckIdList =new JSONArray();
        if(userId != null) {
            List<HashMap<String, String>> checkroleMap = xbrlSmUserRoleService.selectRoleByUserId(userId);
            if (checkroleMap != null && checkroleMap.size()>0) {
                for (HashMap<String, String> item : checkroleMap) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("key", item.get("key"));
                    jsonObj.put("value", item.get("value"));
                    rolecheckIdList.add(jsonObj);
                }
            }
        }
        rolelistResp.put("alllist",roleall);
        rolelistResp.put("checklist",rolecheckIdList);
        return rolelistResp;
    }

    /**
     * 保存新增角色信息
     *
     * @param xbrlSmRole
     * @return 返回保存成功或者失败信息
     */
    @ApiOperation(value = "保存新增角色信息", notes = "保存传递过来的角色资源信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "xbrlSmRole", value = "角色对象", required = true, dataType = "String", paramType = "from")})
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjResp save(XbrlSmRole xbrlSmRole) {
        JsonObjResp resp = new JsonObjResp();
        //验证角色名称唯一性
        if(xbrlSmRole.getName().isEmpty()){
            resp.setRspCode("100001");
            resp.setRspDesc("角色名称不能为空");
            return resp;
        }
        List<XbrlSmRole> roleList = xbrlSmRoleService.selectXbrlSmRoleByName(xbrlSmRole.getName());
        if (roleList != null && roleList.size() > 0) {
            resp.setRspCode("100001");
            resp.setRspDesc("验证角色名称唯一性");
            return resp;
        }
        //保存角色信息
        try {
            xbrlSmRole.setCreateUser("admin");
            xbrlSmRole.setCreateTime(new Date());
            long roleresult = xbrlSmRoleService.insertXbrlSmRole(xbrlSmRole);
        } catch (Exception ex) {
            resp.setRspCode("100001");
            resp.setRspDesc("保存角色失败：原因" + ex.getMessage());
            return resp;
        }
        resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        resp.setRspDesc("保存角色成功");
        return resp;
    }


    /**
     * 修改角色信息
     * @param xbrlSmRole
     * @return 返回保存成功或者失败信息
     */
    @ApiOperation(value = "修改新增角色信息", notes = "更新传递过来的角色资源信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "xbrlSmRole", value = "角色对象", required = true, dataType = "String", paramType ="form" )})
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjResp update(XbrlSmRole xbrlSmRole) {
        JsonObjResp resp = new JsonObjResp();
        //验证角色名称唯一性
        if(xbrlSmRole.getName().isEmpty()){
            resp.setRspCode("100001");
            resp.setRspDesc("角色名称不能为空");
            return resp;
        }
        XbrlSmRole oldRole=xbrlSmRoleService.selectXbrlSmRoleById(xbrlSmRole.getId());
        if(oldRole == null){
            resp.setRspCode("100001");
            resp.setRspDesc("[id="+xbrlSmRole.getId()+"]的角色不存在");
            return resp;
        }
        //保存角色信息
        try {
            xbrlSmRole.setCreateUser(oldRole.getCreateUser());
            xbrlSmRole.setCreateTime(oldRole.getCreateTime());
            xbrlSmRole.setUpdateUser("admin");
            xbrlSmRole.setUpdateTime(new Date());
            long roleresult = xbrlSmRoleService.updateRole(xbrlSmRole);
        } catch (Exception ex) {
            resp.setRspCode("100001");
            resp.setRspDesc("修改角色失败：原因" + ex.getMessage());
            return resp;
        }
        resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        resp.setRspDesc("修改角色成功");
        return resp;
    }


    /**
     * 保存/修改新增角色信息
     *
     * @param roleId
     * @param resourceIdsValue
     * @return 返回保存成功或者失败信息
     */
    @ApiOperation(value = "保存或者修改新增角色信息", notes = "更新传递过来的角色资源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色对象Id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "resourceIdsValue", value = "资源ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "saveResourceForRole", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjResp saveResourceForRole(Integer roleId, String resourceIdsValue) {
        JsonObjResp resp = new JsonObjResp();
        //拆分角色ID字符串
        List<XbrlSmRoleResource> roleResourceList = new ArrayList<XbrlSmRoleResource>();
        if (null != resourceIdsValue && !"".equals(resourceIdsValue)) {
            String[] ids = resourceIdsValue.split(",");
            for (int i = 0; i < ids.length; i++) {
                XbrlSmResource xbrlResource = xbrlSmResourceService.getXbrlSmResourceById(Integer.parseInt(ids[i]));
                if (null != xbrlResource) {
                    XbrlSmRoleResource roleResource = new XbrlSmRoleResource();
                    roleResource.setRoleId(roleId);
                    roleResource.setResourceId(xbrlResource.getId());
                    roleResourceList.add(roleResource);
                }
            }
            xbrlSmRoleResourceService.saveXbrlSmRoleResourceList(roleId,roleResourceList);
        }
        resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        resp.setRspDesc("保存角色成功");
        return resp;
    }


    /**
     * 根据ID批量删除角色
     *
     * @param ids
     * @return 返回删除成功或者失败信息
     */
    @ApiOperation(value = "根据ID批量删除角色", notes = "根据url的ids来批量删除角色")
    @ApiImplicitParam(name = "ids", value = "角色ID列表", required = true, dataType = "String")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjResp delete(@RequestParam(value = "ids", defaultValue = "") String ids) {
        JsonObjResp resp = new JsonObjResp();
        if (null != ids && !"".equals(ids)) {
            String[] idlist = ids.split(",");
            for (int i = 0; i < idlist.length; i++) {
                xbrlSmRoleService.deleteXbrlSmRoleById(Integer.parseInt(idlist[i]));
            }
            resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
            resp.setRspDesc("删除角色成功");
            return resp;
        } else {
            resp.setRspCode("100001");
            resp.setRspDesc("删除角色失败，未传入Id值");
            return resp;
        }
    }


    /**
     * 获取已选角色的资源
     *
     * @return 返回资源信息，符合树形结构的格式
     */
    @ApiOperation(value = "获取已选角色的资源", notes = "根据Id获取已选角色的资源")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Long")
    @RequestMapping(value = "getResourcesOfRole", method = RequestMethod.GET)
    @ResponseBody
    public List<XbrlSmResource> getResourcesForRole(@RequestParam(value = "id") Long roleId) {
        return xbrlSmRoleService.selectResourceForRole(roleId);
    }


    /**
     * 获取已选角色的用户信息
     *
     * @return 用户信息列表
     */
    @ApiOperation(value = "获取已选角色的用户信息", notes = "更加角色ID获取已选角色的用户信息")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Long")
    @RequestMapping(value = "getUserForRole", method = RequestMethod.GET)
    @ResponseBody
    public List<XbrlSmUser> getUserForRole(@RequestParam(value = "id") Integer roleId) {
        return xbrlSmUserService.selectXbrlSmUserByRoleId(roleId);
    }


    /**
     * 获取已选角色的用户信息
     *
     * @return 用户信息列表
     */
    @ApiOperation(value = "保存用户角色信息", notes = "保存用户角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userIds", value = "用户id列表", required = true, dataType = "int数组")
    })
    @RequestMapping(value = "saveUserForRole", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjResp saveUserForRole(@RequestParam(value = "roleId", required = true) Integer roleId, @RequestParam(value = "userIds", required = true) int[] userIds){
        JsonObjResp resp = new JsonObjResp();
        if(roleId == null || userIds == null ){
            resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
            resp.setRspDesc("缺少必要参数");
            return  resp;
        }
        xbrlSmUserRoleService.saveUserForRole(roleId,userIds);
        resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        resp.setRspDesc("保存成功");
        return resp;
    }


    /**
     * 修改角色状态信息
     * @return 操作结果信息
     */
    @ApiOperation(value = "修改角色状态信息", notes = "修改角色状态信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids", value ="用户ids，如果有多个以逗号分隔",dataType ="String", paramType ="query" ) ,
            @ApiImplicitParam(name="state", value ="用户状态state",dataType ="String", paramType ="query" )
    })
    @RequestMapping(value = "disableOrEnableRole", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjResp disableOrEnableRole(@RequestParam(value = "ids", required = true) String ids, @RequestParam(value = "state", required =true ) String state){
        JsonObjResp resp = new JsonObjResp();
        if(ids == null  || state == null ){
            resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
            resp.setRspDesc("缺少必要参数");
            return  resp;
        }
        String[] roleid = ids.split(",");
        for(String item : roleid){
            XbrlSmRole roleitem=xbrlSmRoleService.selectXbrlSmRoleById(Long.parseLong(item));
            if(roleitem != null){
                roleitem.setState(state);
                xbrlSmRoleService.updateRole(roleitem);
            }
        }
        resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        resp.setRspDesc("更新角色信息成功！");
        return resp;
    }

}
