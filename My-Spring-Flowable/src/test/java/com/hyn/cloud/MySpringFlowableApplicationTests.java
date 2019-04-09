package com.hyn.cloud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricDetail;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringFlowableApplicationTests {

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

	@Test
	public void test1() {
		Deployment deployment = repositoryService.createDeployment().name("MyProcess1")
				.addClasspathResource("processes/MyProcess1.bpmn").deploy();
		System.out.println(
				"Deploy successfullly, deployId:" + deployment.getId() + "; deployName:" + deployment.getName());
		// Deploy successfullly, deployId:097f8cff-34c0-11e9-880d-00155d8d7014;
		// deployName:MyProcess1
	}

	/**
	 * 查询流程定义
	 */
	@Test
	public void test2() {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId("097f8cff-34c0-11e9-880d-00155d8d7014") // 用上一步中的结果
				.singleResult();
		System.out.println("Found process definition : " + processDefinition.getName() + "; key:"
				+ processDefinition.getKey() + ";id:" + processDefinition.getId());
	}

	/**
	 * 启动流程
	 */
	@Test
	public void test3() {
		Map<String, Object> variables = new HashMap<String, Object>();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variables);
		System.out.println(processInstance.getActivityId());
	}

	/**
	 * 个人方式查询，teacher查询
	 * 
	 */
	@Test
	public void test4() {
		String userId = "teacher";
		List<Task> list = processEngine.getTaskService()//
				.createTaskQuery()//
				.taskAssignee(userId)// 指定个人任务查询
				.list();
		for (Task task : list) {
			System.out.println("id=" + task.getId());
			System.out.println("name=" + task.getName());
			System.out.println("assinee=" + task.getAssignee());
			System.out.println("createTime=" + task.getCreateTime());
			System.out.println("executionId=" + task.getExecutionId());
		}
	}

	/**
	 * teacher审批给rector
	 * 
	 */
	@Test
	public void test5() {
		String userId = "teacher";
		List<Task> list = processEngine.getTaskService()//
				.createTaskQuery()//
				.taskAssignee(userId)// 指定个人任务查询
				.list();
		for (Task task : list) {
			System.out.println("id=" + task.getId());
			System.out.println("name=" + task.getName());
			System.out.println("assinee=" + task.getAssignee());
			System.out.println("createTime=" + task.getCreateTime());
			System.out.println("executionId=" + task.getExecutionId());
		}
	}

	@Test
	public void test6() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println(processEngine);
	}

	/**
	 * 启动任务
	 */
	@Test
	public void test7() {
		// 启动流程
		HashMap<String, Object> map = new HashMap<>();
		map.put("managerId", "department_manager");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloworld", map);
		System.out.println("提交成功.流程Id为：" + processInstance.getId());
	}

	/**
	 * 个人查询任务
	 */
	@Test
	public void test8() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("department_manager").orderByTaskCreateTime()
				.desc().list();
		for (Task task : tasks) {
			System.out.println(task.toString());
		}
	}

	/**
	 * 部门经理处理任务
	 */
	@Test
	public void test9() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("department_manager").orderByTaskCreateTime()
				.desc().list();

		for (Task task : tasks) {
			// 通过审核
			System.out.println(task.toString());
			HashMap<String, Object> map = new HashMap<>();
			map.put("boss", "boss");
			taskService.complete(task.getId(), map);
		}

	}

	/**
	 * 老板查询
	 */
	@Test
	public void test10() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("boss").orderByTaskCreateTime().desc().list();
		for (Task task : tasks) {
			System.out.println(task.toString());
		}
	}

	/**
	 * 总经理处理任务
	 */
	@Test
	public void test11() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("boss").orderByTaskCreateTime().desc().list();
		for (Task task : tasks) {
			// 通过审核
			System.out.println(task.toString());
			taskService.complete(task.getId(), null);
		}
	}

	/**
	 * 查询历史的流程
	 */
	@Test
	public void test12() {

		List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery().list();
		for (HistoricActivityInstance activity : activities) {
			System.out.println(activity.getActivityId() + " took " + activity.getDurationInMillis() + " milliseconds");
		}
	}

	
	
	/**
	 * 申请设备
	 */
	@Test
	public void test13() {
		// 启动流程
		HashMap<String, Object> map = new HashMap<>();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("eq_equipment_receive", map);
		System.out.println("提交成功.流程Id为：" + processInstance.getId());
	}
	
	
	/**
	 * 申请设备（驳回）
	 */
	@Test
	public void test14() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("陈刚").orderByTaskCreateTime()
				.desc().list();

		for (Task task : tasks) {
			// 通过审核
			System.out.println(task.toString());
			HashMap<String, Object> map = new HashMap<>();
			map.put("outcome", "通过");
			taskService.complete(task.getId(), map);
		}
	}
	
	
	/**
	 * 申请设备（驳回）
	 */
	@Test
	public void test15() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("邱鹏").orderByTaskCreateTime()
				.desc().list();

		for (Task task : tasks) {
			// 通过审核
			System.out.println(task.toString());
			HashMap<String, Object> map = new HashMap<>();
			map.put("outcome", "通过");
			taskService.complete(task.getId(), map);
		}
	}
	
	
	@Test
	public void Test16()
	{
		List<HistoricDetail> historicDetails=	historyService.createHistoricDetailQuery().list();
		System.out.println(historicDetails);
	}
	
	/**
	 * 查询执行过得信息
	 */
	@Test
	public void Test17()
	{
		List<HistoricTaskInstance> taskInstances=	historyService.createHistoricTaskInstanceQuery().list();
		System.out.println(taskInstances);
	}
}
