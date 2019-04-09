package com.hyn.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyn.cloud.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {
	

	@Autowired
	TestService testService;
	
	/**
	 * 申请一个流程
	 */
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String createrReceiveTask()
	{
		return testService.creater("杨杰", "雨量筒|水尺");
	}

}
