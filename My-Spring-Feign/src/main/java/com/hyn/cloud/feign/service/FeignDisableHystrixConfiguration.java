package com.hyn.cloud.feign.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.hystrix.HystrixFeign;

@Configuration
public class FeignDisableHystrixConfiguration {

	@Configuration
	@ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })
	protected static class HystrixFeignConfiguration {
	    @Bean
	    @Scope("prototype")
	    public Feign.Builder feignHystrixBuilder() {
	        return HystrixFeign.builder();
	    }
	}
	
}
