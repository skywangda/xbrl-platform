package com.changhong.xbrl.sysmanage.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Liang on 2017/12/5.
 */
@FeignClient(name = "xbrl-user-es", url = "${url.xbrl.user}")
public interface UserServiceClient {
    @RequestMapping(value = "/xbrl-user-es/rest/user/json/getXbrlSmUserBytoken", method = RequestMethod.GET)
    String getXbrlSmUserInfoByToken(@RequestParam(value = "token") String token);

    @RequestMapping(value = "/xbrl-user-es/rest/user/json/getXbrlSmUser", method = RequestMethod.GET)
    String getXbrlSmUser(@RequestParam(value = "id") Long id, @RequestParam(value = "token") String token, @RequestParam(value = "resourceType") int resourceType);
}
