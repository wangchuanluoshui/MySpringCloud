package com.hyn.spring.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduleTask {

	@Scheduled(cron = "0/5 * * * * ? ")
	public void task1() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("task1 thread name is :"+Thread.currentThread().getName()+"-"+dateFormat.format(new Date()));
	}
	
	
	@Scheduled(cron = "0/5 * * * * ? ")
	public void task2() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("task2 thread name is :"+Thread.currentThread().getName()+"-"+dateFormat.format(new Date()));
	}

}
