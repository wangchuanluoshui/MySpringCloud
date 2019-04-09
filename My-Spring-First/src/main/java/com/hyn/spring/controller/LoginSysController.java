package com.hyn.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @Title:：LoginSysController.java 
 * @Package ：com.summit.homs.controller.sys 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年9月9日 上午9:42:50 
 * @version ： 1.0
 */
@Api(value = "/login",tags="登录模块")
@Controller
public class LoginSysController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginSysController.class);


	@ApiOperation(value = "登录功能，以获取session")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(
			@ApiParam(value = "用戶信息,example:{\"username\":\"hyn\",\"password\":\"123456\"}", required = true) @RequestBody String jsonstr) {
	}

	@ApiOperation(value = "退出用户功能")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "用户退出系统成功！";

	}
}
