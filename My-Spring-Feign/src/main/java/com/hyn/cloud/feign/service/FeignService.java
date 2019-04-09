package com.hyn.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SPRING-BOOT-RIBBON",fallback=FeignServiceHystric.class,configuration=FeignDisableHystrixConfiguration.class )
public interface FeignService {
	
    @RequestMapping(value = "/ribbon/name2",method = RequestMethod.GET)
	String getName(@RequestParam("name")String name);

}
