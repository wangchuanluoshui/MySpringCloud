package com.hyn.spring.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.alibaba.fastjson.JSONObject;


public class AjaxSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	@Override
	  public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
	    HttpServletResponse response = event.getResponse();
	    HttpServletRequest request = event.getRequest();
	    JSONObject returnObj = new JSONObject();
	/*    if (RequestUtils.isAjax(request)) {
	      returnObj.put("status", "0");
	    } else {*/
	      returnObj.put("status", "-1");
	      returnObj.put("message", "非法登录");
//	    }
	    response.setContentType("application/json;charset=UTF-8");
	    response.getWriter().print(returnObj.toJSONString());
	    response.flushBuffer();
	  }
}
