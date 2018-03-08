package com.changhong.xbrl.sysmanage.controller;


import com.alibaba.fastjson.JSONObject;
import com.changhong.xbrl.sysmanage.client.InstanceServiceClient;
import com.changhong.xbrl.sysmanage.client.TaxonomyServiceClient;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;
import com.changhong.xbrl.sysmanage.model.JsonObjResp;
import com.changhong.xbrl.sysmanage.model.ObjectPageDataResp;
import com.changhong.xbrl.sysmanage.service.XbrlSmCompanyService;
import com.changhong.xbrl.sysmanage.service.XbrlSmUserService;
import com.changhong.xbrl.sysmanage.util.AesEncryptUtil;
import com.changhong.xbrl.sysmanage.util.ServiceConstants;
import com.changhong.xbrl.sysmanage.util.Util.XbrlCompanyOrderComparator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class XbrlCompanyController {

	@Autowired
	private XbrlSmCompanyService xbrlCompanyService;
	@Autowired
	private TaxonomyServiceClient  taxonomyServiceClient;
	@Autowired
	private InstanceServiceClient insatnceServiceClient;
	@Autowired
	private XbrlSmUserService xbrlSmUserService;
	@Value("${decrypt.aes.key}")
	private String aeskey;

	@Value("${decrypt.aes.iv}")
	private String aesiv;

	/**
	 *  分页查询
	 * @param pageNumber 页码
	 * @param pageSize 每页显示条数
	 * @param name 公司名称
	 * @param state 公司状态
	 * @param request
	 * @return 返回带条件下的分页查询结果
	 */
	@ApiOperation(value="公司列表分页查询", notes="公司列表分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pagenumber", value = "页号", required = false, dataType = "int"),
			@ApiImplicitParam(name = "pagesize", value = "页大小", required = false, dataType = "int"),
			@ApiImplicitParam(name = "name", value = "公司名称", required = false, dataType = "String"),
			@ApiImplicitParam(name = "state", value = "公司状态", required = false, dataType = "String")
	})
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ObjectPageDataResp get(
			@RequestParam(value = "pagenumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "state", required = false) String state,
			ServletRequest request) {
		ObjectPageDataResp<XbrlSmCompany>  companylistResp =new ObjectPageDataResp<XbrlSmCompany>();
		HashMap<String, Object> searchParamsMap=new HashMap<String,Object>();
		searchParamsMap.put("startNumber",(pageNumber-1)*pageSize);
		searchParamsMap.put("pagzSize",pageSize);
		if(name!=null&&!name.equals("")){
			searchParamsMap.put("name",name);
		}
		if(state!=null&&!state.equals("")){
			searchParamsMap.put("state",state);
		}


//        List<XbrlSmCompany> comList = xbrlCompanyService.selectXbrlSmCompany();
		//获取数据列表
		List<XbrlSmCompany> comList=xbrlCompanyService.selectXbrlSmCompanyList(searchParamsMap);

		//获取总数据条数
		int countnum = xbrlCompanyService.getCount(searchParamsMap);
		companylistResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		companylistResp.setRspDesc("请求成功");
		companylistResp.setPageNo(pageNumber);
		companylistResp.setPageSize(pageSize);
		companylistResp.setDataSet(comList);
		companylistResp.setTotalNum(countnum);
		return companylistResp;
	}

	/**
	 *  公司下拉查询
	 * @param request
	 * @return 返回带条件下的分页查询结果
	 */
	@ApiOperation(value="公司列表分页查询", notes="公司列表分页查询")

	@RequestMapping(value = "getForRole", method = RequestMethod.GET)
	@ResponseBody
	public ObjectPageDataResp getForRole(
			@RequestParam(value = "userId", required = false) Integer userId,
			ServletRequest request) {
		ObjectPageDataResp<XbrlSmCompany>  companylistResp =new ObjectPageDataResp<XbrlSmCompany>();
		HashMap<String, Object> searchParamsMap=new HashMap<String,Object>();
		searchParamsMap.put("state","1");
		List<XbrlSmCompany> comList=xbrlCompanyService.selectXbrlSmCompanyList(searchParamsMap);
		if(comList!=null&&comList.size()>1){
			Collections.sort(comList, new XbrlCompanyOrderComparator());
		}
		if(userId!=null){
			List<XbrlSmCompany> selectlist = xbrlSmUserService.selectXbrlSmCompanyByUserId(userId);
			companylistResp.setSelectedSet(selectlist);
			if(selectlist!=null){
				if(selectlist.size()>1){
					Collections.sort(selectlist, new XbrlCompanyOrderComparator());
				}
				List<Integer> selectIdList = new ArrayList<Integer>();
				List<XbrlSmCompany> comList2 = new ArrayList<XbrlSmCompany>();
				for(int i=0;i<selectlist.size();i++){
					selectIdList.add(selectlist.get(i).getId());
					comList2.add(selectlist.get(i));
				}
				for(int i=0;i<comList.size();i++){
					if(!selectIdList.contains(comList.get(i).getId())){
						comList2.add(comList.get(i));
					}
				}
                if(comList2.size()>0){
					comList = comList2;
				}
			}
		}

		//获取总数据条数
		int countnum = xbrlCompanyService.getCount(searchParamsMap);
		companylistResp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		companylistResp.setRspDesc("请求成功");
		companylistResp.setDataSet(comList);
		companylistResp.setTotalNum(countnum);

		return companylistResp;
	}


	/**
	 * 保存/修改新增公司信息
	 * @param xbrlSmCompany
	 * @return 返回保存成功或者失败信息
	 */
	@ApiOperation(value="保存或者修改新增公司信息", notes="更新传递过来的公司资源信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "xbrlSmCompany", value = "公司对象", required = true, dataType = "XbrlSmCompany")
	})
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjResp save(XbrlSmCompany xbrlSmCompany) {
		JsonObjResp resp = new JsonObjResp();
		//保存公司信息
		try {
			if(xbrlSmCompany.getId()==null){
				//判重
				for(int i=0;i<4;i++){
					XbrlSmCompany sameCompany = new XbrlSmCompany();
					String type = null;
					if(i==0&&xbrlSmCompany.getCompanyName() != null) {
						sameCompany.setCompanyName(xbrlSmCompany.getCompanyName());
						type = "公司名称";
					}else if(i==1&&xbrlSmCompany.getCompanyCode() != null) {
						sameCompany.setCompanyCode(xbrlSmCompany.getCompanyCode());
						type = "公司编码";
					}else if(i==2&&xbrlSmCompany.getShortName() != null) {
						sameCompany.setShortName(xbrlSmCompany.getShortName());
						type = "公司简称";
					}else if(i==3&&xbrlSmCompany.getIdentifierScheme() != null&&xbrlSmCompany.getNameSpacePrefix()!=null) {
						sameCompany.setIdentifierScheme(xbrlSmCompany.getIdentifierScheme());
						sameCompany.setNameSpacePrefix(xbrlSmCompany.getNameSpacePrefix());
						type = "公司域名或公司命名空间前缀";
					}
					List<XbrlSmCompany> findbyObj = xbrlCompanyService.selectXbrlSmCompanyListByObj(sameCompany);
					if(findbyObj!=null&&findbyObj.size()>0){
						resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
						resp.setRspDesc("保存公司失败："+type+"已存在重复值");
						return resp;
					}
				}
				xbrlCompanyService.insertXbrlSmCompany(xbrlSmCompany);
			}else{
				xbrlCompanyService.updateXbrlSmCompanyById(xbrlSmCompany);
			}
		} catch (Exception ex) {
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("保存公司失败：原因" + ex.getMessage());
			return resp;
		}
		resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
		resp.setRspDesc("保存公司成功");
		return resp;
	}
	
	/**
	 * 根据ID批量删除公司
	 * @param ids
	 * @return 返回删除成功或者失败信息
	 */
	@ApiOperation(value="根据ID批量删除公司", notes="根据url的ids来批量删除公司")
	@ApiImplicitParam(name = "ids", value = "公司ID列表", required = true, dataType = "String",paramType = "query")
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjResp delete(@RequestParam(value = "ids", defaultValue = "") String ids) {
		JsonObjResp resp = new JsonObjResp();
		if (null != ids && !"".equals(ids)) {
			try {
				JsonObjResp taxonomyUseComResp = getUseCom(ids+"","taxonomy");
				if(taxonomyUseComResp.getRspCode().equals(ServiceConstants.RETURN_INFO_CODE_SUCCESS)){
					JsonObjResp instanceUseComResp = getUseCom(ids+"","instance");
					if(instanceUseComResp.getRspCode().equals(ServiceConstants.RETURN_INFO_CODE_SUCCESS)){
						String[] idlist = ids.split(",");
						for (int i = 0; i < idlist.length; i++) {
							xbrlCompanyService.deleteXbrlSmCompanyById(Double.parseDouble(idlist[i]));
						}
						resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
						resp.setRspDesc("删除公司成功");
						return resp;
					}else{
						resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
						resp.setRspDesc("删除公司失败，当前公司被实例文档引用");
						return resp;
					}
				}else{
					resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
					resp.setRspDesc("删除公司失败，当前公司被分类标准引用");
					return resp;
				}


			} catch (Exception ex) {
				resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
				resp.setRspDesc("删除公司失败：原因" + ex.getMessage());
				return resp;
			}

		} else {
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
			resp.setRspDesc("删除公司失败，未传入Id值");
			return resp;
		}

	}


	/**
	 * 根据公司名获取公司列表
	 * @param name
	 * @return 返回公司列表
	 */
//    @ApiOperation(value="根据公司名获取公司列表", notes="传公司名获取公司列表")
//    @ApiImplicitParam(name = "name", value = "公司名", required = true, dataType = "String")
//    @RequestMapping(value = "getCompanyByName", method = RequestMethod.GET)
//    @ResponseBody
//	public String getCompanyByName(String name) {
////		return xbrlCompanyDao.getCompanyByName(name);
//		return null;
//	}

	/**	 * 根据公司状态查询公司
	 *
	 * @param state 公司状态
	 * @return 返回符合条件的公司集合
	 */
//    @ApiOperation(value="根据公司状态查询公司", notes="传公司状态查询公司")
//    @ApiImplicitParam(name = "state", value = "公司状态", required = true, dataType = "int")
//    @RequestMapping(value = "getCompanyByState", method = RequestMethod.GET)
//    @ResponseBody
//	public String getCompanyByState(int state) {
////		return xbrlCompanyDao.getCompanyByState(state);
//		return null;
//	}

	/**
	 * 根据工商注册号查找公司
	 * @param businessLic 工商注册号
	 * @return 公司列表
	 */
//    @ApiOperation(value="根据工商注册号查找公司", notes="传工商注册号查找公司")
//    @ApiImplicitParam(name = "businessLic", value = "公司注册号", required = true, dataType = "String")
//    @RequestMapping(value = "getCompanyByBusinessLic", method = RequestMethod.GET)
//    @ResponseBody
//	public String getCompanyByBusinessLic(String businessLic){
////		return xbrlCompanyDao.getCompanyByBusinessLic(businessLic);
//		return null;
//	}


    /**
     * 禁用公司
     * @param id 公司ID
     * @return 返回禁用成功或者失败信息
     */
    @ApiOperation(value="根据id禁用公司", notes="根据id禁用公司")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "公司ID", required = true, dataType = "int"),
			@ApiImplicitParam(name = "state", value = "公司状态", required = true, dataType = "String")
	})
    @RequestMapping(value = "forbiddenCompanyById", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjResp forbiddenCompanyById(int id,String state){
		JsonObjResp resp = new JsonObjResp();
		try {
			XbrlSmCompany xsc =  xbrlCompanyService.selectXbrlSmCompanyById((double) id);
			if (null != xsc ) {
				if(state.equals("0")){
					//判断是否被分类标准引用
					JsonObjResp taxonomyUseComResp = getUseCom(id+"","taxonomy");
					if(taxonomyUseComResp.getRspCode().equals(ServiceConstants.RETURN_INFO_CODE_SUCCESS)){
						JsonObjResp instanceUseComResp = getUseCom(id+"","instance");
						//判断是否被实例文档引用
						if(instanceUseComResp.getRspCode().equals(ServiceConstants.RETURN_INFO_CODE_SUCCESS)){
							xsc.setState(state);
							xbrlCompanyService.updateXbrlSmCompanyById(xsc);
							resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
							resp.setRspDesc("禁用公司成功");
							return resp;
						}else{
							resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
							resp.setRspDesc("禁用公司失败，当前公司被实例文档引用");
							return resp;
						}
					}else{
						resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
						resp.setRspDesc("禁用公司失败，当前公司被分类标准引用");
						return resp;
					}

				}else{
					xsc.setState(state);
					xbrlCompanyService.updateXbrlSmCompanyById(xsc);
					resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
					resp.setRspDesc("解禁公司成功");
					return resp;
				}

			} else {
				resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR8);
				resp.setRspDesc("禁用公司失败，当前id无对应公司");
				return resp;
			}
		} catch (Exception ex) {
			resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_ERR9);
			resp.setRspDesc("禁用公司失败：原因" + ex.getMessage());
			return resp;
		}

    }

    public JsonObjResp getUseCom(String ids, String type) throws IOException {
		//判断是否被实例文档引用;
		JsonObjResp useComResp = new JsonObjResp();
		String useCom = null;
		try {
		if(type.equals("instance")){
//			strURL = "http://10.4.32.44:8030/xbrl-instance-es/rest/instance/json/getInsUseCompany?ids="+ids;
			 useCom = insatnceServiceClient.getInsUseCompany(ids);

		}else if(type.equals("taxonomy")){
//			strURL = "http://10.4.32.44:8030/xbrl-taxonomy-es/rest/taxonomy/json/getTaxUseCompany?ids="+ids;
			 useCom = taxonomyServiceClient.getTaxUseCompany(ids);
		}

			String useComJson = AesEncryptUtil.aesDecrypt(useCom, aeskey, aesiv);
			System.out.println(useComJson);
			useComResp=(JsonObjResp)JSONObject.parseObject(useComJson, JsonObjResp.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  useComResp;
//		URL url = new URL(strURL);
//		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
//		httpConn.setRequestMethod("POST");
//		httpConn.connect();
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
//		String line;
//		StringBuffer buffer = new StringBuffer();
//		while ((line = reader.readLine()) != null) {
//			buffer.append(line);
//		}
//		reader.close();
//		httpConn.disconnect();
//
//		String insUseCompanyData = buffer.toString();
//		JSONObject jsonobject = JSONObject.parseObject(insUseCompanyData);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("obj", JSONObject.class);
//
//		JsonObjResp useComResp = (JsonObjResp)JSONObject.toJavaObject(jsonobject,JsonObjResp.class);//,map);;
	}

}
