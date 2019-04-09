package com.hyn.spring.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsynConfigurer implements AsyncConfigurer {

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// 返回值为void的异步方法不会传递异常，当方法中出现异常的时候只会打印日志，重写此方法来自定义异常处理方法
		return null;
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor asyncTaskThreadPool = new ThreadPoolTaskExecutor();
		asyncTaskThreadPool.setCorePoolSize(100);
		asyncTaskThreadPool.setMaxPoolSize(200);
		asyncTaskThreadPool.setQueueCapacity(20);
		asyncTaskThreadPool.setThreadFactory(new ThreadFactory() {

			private final AtomicLong index = new AtomicLong(1);

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "Async-override-task-pool-thread-" + index.getAndIncrement());
			}
		});
		asyncTaskThreadPool.initialize();
		return asyncTaskThreadPool;
	}

}
