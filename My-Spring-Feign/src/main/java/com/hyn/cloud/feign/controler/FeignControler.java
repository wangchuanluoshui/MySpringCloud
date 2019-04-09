package com.hyn.cloud.feign.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyn.cloud.feign.service.FeignService;

@RestController
@RequestMapping("/feign")
public class FeignControler {

	@Autowired
	FeignService feignService;
	
    @RequestMapping(value = "/name",method=RequestMethod.GET)
    public String feign(@RequestParam String name){
		return feignService.getName(name);
    }
}
