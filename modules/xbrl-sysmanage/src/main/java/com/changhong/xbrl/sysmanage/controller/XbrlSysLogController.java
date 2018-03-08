package com.changhong.xbrl.sysmanage.controller;

import com.changhong.xbrl.sysmanage.domain.XbrlSmLog;
import com.changhong.xbrl.sysmanage.model.ObjectPageDataResp;
import com.changhong.xbrl.sysmanage.service.XbrlSmSysLogService;
import com.changhong.xbrl.sysmanage.util.ServiceConstants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Liang on 2018/3/2.
 */
@Controller
@RequestMapping(value = "/api/log")
public class XbrlSysLogController {

    @Autowired
    private XbrlSmSysLogService sysLogService;

    /**
     * 系统日志读取
     */
    @ApiOperation(value = "系统日志分页查询", notes = "系统日志列表分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ObjectPageDataResp list(
            @ApiParam("请求流水号") @RequestParam(value = "id", defaultValue = "1") String id,
            @ApiParam("关键字") @RequestParam(value = "key", defaultValue = "") String key,
            @ApiParam("页号") @RequestParam(value = "pageno", defaultValue = "1") Integer pageNumber,
            @ApiParam("页大小") @RequestParam(value = "pagesize", defaultValue = "10") Integer pagzSize) {
        ObjectPageDataResp resp = new ObjectPageDataResp();
        HashMap<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("startNumber", (pageNumber - 1) * pagzSize);
        paramsMap.put("pagzSize", pagzSize);
        if (!key.isEmpty()) {
            paramsMap.put("key", key);
        }
        List<XbrlSmLog> loglist = sysLogService.selectXbrlSmLogList(paramsMap);
        resp.setDataSet(loglist);
        //获取数据总数
        int total = sysLogService.getCount(key);
        resp.setTotalNum(total);
        resp.setPageSize(pagzSize);
        resp.setPageNo(pageNumber);
        resp.setId(id);
        resp.setRspCode(ServiceConstants.RETURN_INFO_CODE_SUCCESS);
        resp.setRspDesc(ServiceConstants.RETURN_INFO_DESC_SUCCESS);
        return resp;
    }


}
