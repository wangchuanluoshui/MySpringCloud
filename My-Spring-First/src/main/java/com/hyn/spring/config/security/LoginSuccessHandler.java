package com.hyn.spring.config.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.alibaba.fastjson.JSONObject;
import com.hyn.spring.entity.SysUser;

/**
 * 
 * 登陆成功处理类
 * 
 * @Title:：LoginSuccessHandler.java 
 * @Package ：com.summit.homs.global.cfg 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年8月16日 下午5:22:01 
 * @version ： 1.0
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 获取当前用户(domain接收)
		LoginSysUser loginSysUser = (LoginSysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		SysUser user = new SysUser();
		user.setEmail(loginSysUser.getEmail());
		user.setId(loginSysUser.getId());
		user.setLoginName(loginSysUser.getLoginName());
		user.setPassword(loginSysUser.getPassword());
		user.setPhone(loginSysUser.getPhone());
		user.setStatus(loginSysUser.getStatus());
		user.setSysOrganization(loginSysUser.getSysOrganization());
		user.setSysRoles(loginSysUser.getSysRoles());
		user.setUserName(loginSysUser.getUserName());

		//设置session
		request.getSession().setAttribute("LOGIN_USER", user);
		logger.info("登陆成功，用户：" + user.getUserName());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		// 返回JSON字符串
		PrintWriter writer = response.getWriter();
		Map<String,String> result=new HashMap<>(1);
		result.put("status", "success");
		writer.write(JSONObject.toJSONString(result));
	}

}
