package com.changhong.xbrl.sysmanage.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ma on 2017/12/8.
 */
@FeignClient(name = "xbrl-taxonomy-es", url = "${url.xbrl.user}")
public interface TaxonomyServiceClient {
    @RequestMapping(value = "/xbrl-taxonomy-es/rest/taxonomy/json/getTaxUseCompany", method = RequestMethod.GET)
    String getTaxUseCompany(@RequestParam(value = "ids") String ids);
}


