package com.hyn.spirng.aop.aop;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSON;
/**
 * 
 * @Title:  AppControllerAspect.java   
 * @Package com.summit.hsp.app.aop   
 * @Description:    TODO(https://www.cnblogs.com/zdj-/p/8665454.html 灰常详细的AOP说明)   
 * @author: heyn     
 * @date:   2019年1月19日 下午9:21:07   
 * @version V1.0
 */
@Component
@Aspect
public class AppControllerAspect {
	
	
	@Pointcut("execution(* com.hyn.spring.controller..*.*(..))")
	public void executeService() {
	}

	/*
	 * 01 . 前置通知：方法调用前被调用
	 */
	@Before("executeService()")
	public void doBeforeAdvice(JoinPoint joinPoint) {// 通过JoinPoint 获取通知的签名信息，如目标方法名，目标方法参数信息等
		System.out.println("我是前置通知");
		Object[] obj = joinPoint.getArgs();// 获取目标方法的参数信息
		joinPoint.getThis(); // AOP代理类信息
		joinPoint.getTarget(); // 代理的目标对象
		Signature signature = joinPoint.getSignature(); // 用的最多，通知的签名
		System.out.println("代理的方法是 ： " + signature.getName()); // 打印 代理的是哪一个方法
		// AOP 代理的名字
		System.out.println("AOP 代理的名字 ： " + signature.getDeclaringTypeName());
		signature.getDeclaringType();// AOP代理类的类（class）信息

		/*
		 * 通过RequestContextHolder获取请求信息，如session 信息 ;
		 */
		// 获取RequestAttributes
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		// 从requestAttributes中获取HttpServletRequest信息
		HttpServletRequest request = (HttpServletRequest) requestAttributes
				.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		// 获取session信息
		HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

		System.out.println("请求 ： " + request + " ,  HttpSession : " + session);
		Enumeration<String> enumerations = request.getParameterNames();
		Map<String, String> parameterMaps = new HashMap<String, String>();
		while (enumerations.hasMoreElements()) {
			String parameter = enumerations.nextElement();
			parameterMaps.put(parameter, request.getParameter(parameter));
		}

		String str = JSON.toJSONString(parameterMaps);// alibaba.fastjson
		if (obj.length > 0) {
			System.out.println("请求参数信息为 ： " + str);
		}

	}
}
