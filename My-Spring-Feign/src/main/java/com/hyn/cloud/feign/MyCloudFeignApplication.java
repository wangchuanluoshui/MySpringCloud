package com.hyn.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //开启spring cloud feign的支持
public class MyCloudFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCloudFeignApplication.class, args);
	}
}

