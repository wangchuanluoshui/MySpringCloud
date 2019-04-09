package com.hyn.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class RibbonService {

    /**
     * 注入RestTemplate
     */
    @Autowired
    RestTemplate restTemplate;
	
	 @HystrixCommand(fallbackMethod = "errorSendFeignServer",
	            commandProperties = {
	            		////指定多久超时，单位毫秒。超时进fallback
	                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000"),
	                    //判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
	                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
	                    //判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
	                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
	            }
	    )
	    public String sendFeignServer(String name)
	    {
	    	String respResult="I am ribbon,My name is "+name;
	    	String url="http://SPRING-BOOT-FEIGN/feign/name?name=";
	        return restTemplate.getForObject(url+respResult, String.class);
	    }

	    
	    public String errorSendFeignServer(String name)
	    {
	        return "feign server is die!";
	    }
	    
	
}
