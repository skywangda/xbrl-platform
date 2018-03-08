package com.changhong.xbrl.sysmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.changhong.xbrl.sysmanage.dao")
public class XbrlSysmanageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbrlSysmanageServiceApplication.class, args);
	}
}
