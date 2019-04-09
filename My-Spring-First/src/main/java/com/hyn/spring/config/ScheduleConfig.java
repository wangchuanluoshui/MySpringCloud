package com.hyn.spring.config;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


/**
 * 配置定时任务线程池
* @Title: ScheduleConfig.java
* @Package com.summit.tas.cfg
* @Description: TODO
* @author hyn  
* @date 2019年1月5日 上午10:55:54
* @version V1.0  
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {


	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		//设定一个长度的定时任务线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
	}

}
