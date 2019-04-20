package com.hyn.spring.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.alibaba.fastjson.JSONObject;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.IResultUtil;
/**
 * 
 * @Title:：LoginFailHandler.java 
 * @Package ：com.summit.homs.tool.security 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年8月21日 下午2:22:50 
 * @version ： 1.0
 */
public class LoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		//返回JSON字符串
		PrintWriter writer =response.getWriter();
		writer.write(JSONObject.toJSONString(IResultUtil.responseMsg(ICodes.CODE_9984)));
	}

	

}
