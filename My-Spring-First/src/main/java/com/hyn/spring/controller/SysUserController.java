package com.hyn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyn.spring.service.ISysUserService;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.IPageRequest;
import com.hyn.spring.utils.IResult;
import com.hyn.spring.utils.IResultUtil;
import com.hyn.spring.vo.CurrentUserVO;
import com.hyn.spring.vo.SysUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "user", tags = "系统用户模块")
@Controller(value = "user")
@RequestMapping(value = "/user")
public class SysUserController {

	@Autowired
	ISysUserService iSysUserService;

	/**
	 * 
	 * @param sysUserVO
	 * @return
	 */
	@ApiOperation(value = "新增用户接口")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public IResult<String> saveUser(@ApiParam(value = "用戶信息", required = true) @RequestBody SysUserVO sysUserVO) {
		String resultCode = iSysUserService.save(sysUserVO);
		return IResultUtil.responseMsg(resultCode);
	}

	/**
	 * 
	 * @param sysUserVO
	 * @return
	 */
	@ApiOperation(value = "批量新增用户接口")
	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	@ResponseBody
	public String batchSaveUser(@ApiParam(value = "用戶信息列表", required = true) @RequestBody List<SysUserVO> sysUserVOs) {
		String resultCode = iSysUserService.batchSave(sysUserVOs);
		return resultCode;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除用户接口")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public IResult<String> delete(@ApiParam(value = "用户ID", required = true) @PathVariable(name = "id") String id) {
		String resultCode = iSysUserService.delete(id);
		return IResultUtil.responseMsg(resultCode);
	}

	/**
	 * 
	 * @param sessionSysUser
	 * @return IResult
	 */
	@ApiOperation(value = "更新用户信息")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public IResult<String> update(@ApiParam(value = "用戶信息", required = true) @RequestBody SysUserVO sysUserVO) {
		String resultCode = iSysUserService.update(sysUserVO);
		return IResultUtil.responseMsg(resultCode);
	}

	/**
	 * 
	 * 查询用户 分页查询
	 * 
	 */
	@ApiOperation(value = "查询用户信息")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public IResult<Page<SysUserVO>> getSysUser(
			@ApiParam(value = "用户名称", required = false) @RequestParam(name = "userName", required = false) String userName,
			@ApiParam(value = "电话", required = false) @RequestParam(name = "phone", required = false) String phone,
			@ApiParam(value = "状态", required = false) @RequestParam(name = "status", required = false) String status,
			@ApiParam(value = "机构名", required = false) @RequestParam(name = "orgName", required = false) String orgName,

			@ApiParam(value = "排序方式", required = false) @RequestParam(name = "sort", required = false) String sort,
			@ApiParam(value = "排序字段", required = false) @RequestParam(name = "sidx", required = false) String sidx,
			@ApiParam(value = "页码", required = false) @RequestParam(name = "page", required = false) Integer page,
			@ApiParam(value = "页大小", required = false) @RequestParam(name = "size", required = false) Integer size) {
		IPageRequest iPageRequest = new IPageRequest(sort, sidx, page, size);
		Page<SysUserVO> sysUsers = iSysUserService.getSysUser(userName, phone, status, orgName,
				iPageRequest.getRequestPage());
		return IResultUtil.responseMsg(ICodes.CODE_0000, sysUsers);
	}

	
	@ApiOperation(value = "查询当前用户")
	@RequestMapping(value = "currentUser/", method = RequestMethod.GET)
	@ResponseBody
	public IResult<CurrentUserVO> currentUser() {
		CurrentUserVO currentUserVO = iSysUserService.currentUser();
		return IResultUtil.responseMsg(ICodes.CODE_0000,currentUserVO);
	}
}
