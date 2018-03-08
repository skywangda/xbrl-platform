package com.changhong.xbrl.sysmanage.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ma on 2017/12/8.
 */
@FeignClient(name = "xbrl-instance-es", url = "${url.xbrl.user}")
public interface InstanceServiceClient {
    @RequestMapping(value = "/xbrl-instance-es/rest/instance/json/getInsUseCompany", method = RequestMethod.GET)
    String getInsUseCompany(@RequestParam(value = "ids") String ids);
}


