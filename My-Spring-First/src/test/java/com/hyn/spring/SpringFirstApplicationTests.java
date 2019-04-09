package com.hyn.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.repository.ISysUserRepository;
import com.hyn.spring.schedule.MyAsynTask;
import com.hyn.spring.schedule.MyScheduleTask;
import com.hyn.spring.service.ISysUserService;
import com.hyn.spring.utils.IPageRequest;
import com.hyn.spring.vo.SysUserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringFirstApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	ISysUserRepository iSysUserRepository;

	@Test
	public void test1() {
		Optional<List<SysUser>> optional = iSysUserRepository.findEmailDistinctByUserName("hyn");
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}

	@Test
	public void test2() {
		Optional<List<SysUser>> optional = iSysUserRepository.findByUserNameIgnoreCase("hyn");
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}

	@Test
	public void test3() {
		Optional<List<SysUser>> optional = iSysUserRepository.findByUserNameOrderByModitimeAsc("hyn");
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}

	@Test
	public void test4() {
		Optional<List<SysUser>> optional = iSysUserRepository.findByUserNameOrLoginName("hyn", "hyn");
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}

	@Test
	public void test5() {
		IPageRequest pageRequest = new IPageRequest(null, null, null, null);
		Page<SysUser> page = iSysUserRepository.findByUserName("hyn", pageRequest.getRequestPage());
		System.out.println(page);
	}

	@Test
	public void test6() {
		Optional<List<SysUser>> optional = iSysUserRepository.findByUserName("hyn");
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}

	@Autowired
	ISysUserService iSysUserService;

	@Test
	public void test7() {
		List<SysUserVO> sysUserVOs = iSysUserService.listSysUser("hyn");
		System.out.println(sysUserVOs);
	}

	@Test
	public void test8() {

		Optional<SysUser> optional = iSysUserRepository.findById("2c987c3167a23e990167a240e0c80008");

		Long startTime=System.currentTimeMillis();
		if (optional.isPresent()) {
			SysUser sysUser = optional.get();
			List<SysUser> sysUsers = new ArrayList<>(10000);
			// 提交1W個
			for (int i = 0; i < 10000; i++) {
				sysUser.setId(String.valueOf(i));
				sysUsers.add(sysUser);
			}
			iSysUserRepository.saveAll(sysUsers);
		}
		Long endTime=System.currentTimeMillis();

		System.out.println("耗时："+(endTime-startTime)+"ms");

	}
	
	
	@Test
	public void test9() {

		Optional<SysUser> optional = iSysUserRepository.findById("2c987c3167a23e990167a240e0c80008");

		Long startTime=System.currentTimeMillis();
		if (optional.isPresent()) {
			List<SysUser> sysUsers = new ArrayList<>(10000);
			// 提交1W個
			for (int i = 0; i < 10000; i++) {
				SysUser sysUser = new SysUser();
				sysUser=optional.get();
				sysUser.setId(String.valueOf(i));
				sysUsers.add(sysUser);
			}
			iSysUserRepository.saveAll(sysUsers);
		}
		Long endTime=System.currentTimeMillis();

		System.out.println("耗时："+(endTime-startTime)+"ms");

	}
	@Test
	public void test10() {

		iSysUserService.batchSave(null);

	}
	
	@Test
	public void test11() {

		iSysUserService.batchSave2(null);

	}
	
	@Autowired
	MyAsynTask myAsynTask;
	@Test
	public void test12() {
		Future<String> future =null;
		for(int i=0;i<10;i++)
		{
			future= myAsynTask.asyncTaskWithResult("Spring Boot-"+i+"-");
		}

        while (!future.isDone()) {
            System.out.println("Wait asyncTaskWithResult.");
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        try {
			System.out.println("asyncTaskWithResult result is:" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        System.out.println("asyncTask finished.");
	}

}
