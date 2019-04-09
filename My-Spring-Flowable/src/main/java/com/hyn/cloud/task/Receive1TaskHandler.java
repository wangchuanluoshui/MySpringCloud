package com.hyn.cloud.task;

/**
 * 申请设备创建
 * @author 62538
 *
 */
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
public class Receive1TaskHandler implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("分中心审批");
	}

}
