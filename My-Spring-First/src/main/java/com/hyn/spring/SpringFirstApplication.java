package com.hyn.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
//@EnableScheduling
@EnableAsync
@EnableCaching  //开启缓存
public class SpringFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFirstApplication.class, args);
	}
}
