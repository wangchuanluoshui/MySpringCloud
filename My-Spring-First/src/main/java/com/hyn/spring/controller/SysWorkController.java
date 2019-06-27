package com.hyn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.entity.SysWork;
import com.hyn.spring.service.ISysWorkService;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.IPageRequest;
import com.hyn.spring.utils.IResult;
import com.hyn.spring.utils.IResultUtil;
import com.hyn.spring.vo.SysWorkFindVO;
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
	
<<<<<<< HEAD
	/**
	 * 
	 * 查询用户 分页查询
	 * 
	 */
	@ApiOperation(value = "查询用户信息")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public IResult<Page<SysWorkFindVO>> getSysUser(
			@ApiParam(value = "用户名称", required = false) @RequestParam(name = "userName", required = false) String userName,
			@ApiParam(value = "项目名称", required = false) @RequestParam(name = "projectName", required = false) String projectName,
			@ApiParam(value = "工作类型", required = false) @RequestParam(name = "type", required = false) String type,

	@ApiOperation(value = "查询工作列表")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public IResult<Page<SysWorkFindVO>> findByCondition(
			@ApiParam(value = "项目名", required = false) @RequestParam(name = "projectName", required = false) String projectName,
			@ApiParam(value = "类型", required = false) @RequestParam(name = "type", required = false) String type,
			@ApiParam(value = "排序方式", required = false) @RequestParam(name = "sort", required = false) String sort,
			@ApiParam(value = "排序字段", required = false) @RequestParam(name = "sidx", required = false) String sidx,
			@ApiParam(value = "页码", required = false) @RequestParam(name = "page", required = false) Integer page,
			@ApiParam(value = "页大小", required = false) @RequestParam(name = "size", required = false) Integer size) {
		if(page==null)
		{
			page=0;
		}
		if(size==null)
		{
			size=15;
		}
		IPageRequest iPageRequest= new IPageRequest(sort, sidx, page, size);
		
		return IResultUtil.responseMsg(ICodes.CODE_0000,iSysWorkService.findByCondition(projectName, type, iPageRequest.getRequestPage()));	}
	
}
