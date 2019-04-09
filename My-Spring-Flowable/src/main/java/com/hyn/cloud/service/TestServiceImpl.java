package com.hyn.cloud.service;

import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

public class TestServiceImpl implements TestService{

	
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private HistoryService historyService;
	
	
	@Override
	public String creater(String creater, String equipments) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("creater", creater);
		variables.put("equipments", equipments);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("eq_equipment_receive", variables);
		//持久化流程ID
		return "success";
	}

}
