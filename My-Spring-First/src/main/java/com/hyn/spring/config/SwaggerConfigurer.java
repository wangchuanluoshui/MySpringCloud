package com.hyn.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
* @Title: SwaggerConfigurer.java
* @Package com.hyn.spring.config
* @Description: TODO
* @author hyn  
* @date 2018年12月9日 下午3:00:05
* @version V1.0  
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurer {
	@Bean
	public Docket createRestApi() {
		//错误路径不监控
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build()
				.apiInfo(buildApiInf());
	}


	private ApiInfo buildApiInf() {
		return new ApiInfoBuilder().title("spring-first接口文档").description("api文档")
				.version("0.0.1").build();

	}
}
