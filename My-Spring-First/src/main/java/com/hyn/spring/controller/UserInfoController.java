package com.hyn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyn.spring.entity.UserInfo;
import com.hyn.spring.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "userinfo", tags = "系统用户模块")
@Controller
@RequestMapping(value = "/user")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	@ApiOperation(value = "新增用户接口")
    @RequestMapping(value="/put",method=RequestMethod.POST)
	@ResponseBody
    public UserInfo put(UserInfo userInfo){
        return userInfoService.save(userInfo);
    }

	@ApiOperation(value = "删除用户接口")
    @RequestMapping(value="/remove",method=RequestMethod.GET)
	@ResponseBody
    public String remove(Long id){
        userInfoService.remove(id);
        return "success";
    }
	
	@ApiOperation(value = "查询用户接口")
    @RequestMapping(value="/cache",method=RequestMethod.POST)
	@ResponseBody
    public UserInfo findone(UserInfo userInfo){
        return userInfoService.findOne(userInfo);
    }
	
	@ApiOperation(value = "查询全部接口")
    @RequestMapping(value="/all",method=RequestMethod.POST)
	@ResponseBody
    public List<UserInfo> findall(){
        return userInfoService.findAll();
    }
	
}
