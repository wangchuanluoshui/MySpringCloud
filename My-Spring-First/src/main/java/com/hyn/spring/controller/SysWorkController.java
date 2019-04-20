package com.hyn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.service.ISysWorkService;
import com.hyn.spring.utils.IResult;
import com.hyn.spring.utils.IResultUtil;
import com.hyn.spring.vo.SysWorkVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "work", tags = "工作管理模块")
@Controller(value = "work")
@RequestMapping(value = "/work")
public class SysWorkController {

	@Autowired
	ISysWorkService iSysWorkService;

	/**
	 * 
	 * @param sysUserVO
	 * @return
	 */
	@ApiOperation(value = "新增工作接口")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public IResult<String> saveWork(@ApiIgnore @SessionAttribute("LOGIN_USER") SysUser sessionSysUser,@ApiParam(value = "用戶信息", required = true) @RequestBody SysWorkVO sysUserVO) {
		String resultCode = iSysWorkService.save(sessionSysUser,sysUserVO);
		return IResultUtil.responseMsg(resultCode);
	}
	
}
