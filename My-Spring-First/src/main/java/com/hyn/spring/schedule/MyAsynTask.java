package com.hyn.spring.schedule;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class MyAsynTask {

	@Async
    public Future<String> asyncTaskWithResult(String name) {
        System.out.println(name+"AsyncTaskWithResult start.");
        try {
            Thread.sleep(1000 * 10);
        } catch (Exception e) {
            return new AsyncResult<>("error" + e.getMessage());
        }
        System.out.println("AsyncTaskWithResult finished.");

        return new AsyncResult<>("success");
    }
	
}
