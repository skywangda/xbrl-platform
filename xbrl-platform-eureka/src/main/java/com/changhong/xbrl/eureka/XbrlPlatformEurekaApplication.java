package com.changhong.xbrl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class XbrlPlatformEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbrlPlatformEurekaApplication.class, args);
	}

}
