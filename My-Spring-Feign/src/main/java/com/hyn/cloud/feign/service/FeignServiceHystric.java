package com.hyn.cloud.feign.service;

import org.springframework.stereotype.Component;

@Component
public class FeignServiceHystric implements FeignService{

	@Override
	public String getName(String name) {
        return "ribbon server is die!";
	}

}
