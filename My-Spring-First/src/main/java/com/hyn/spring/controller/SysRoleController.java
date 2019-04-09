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

import com.hyn.spring.service.ISysRoleService;
import com.hyn.spring.utils.IPageRequest;
import com.hyn.spring.vo.SysRoleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "role", tags = "角色管理模块")
@Controller(value = "role")
@RequestMapping(value = "/role")
public class SysRoleController {

	@Autowired
	ISysRoleService iSysRoleService;

	/**
	 * 
	 * @param SysRoleVO
	 * @return
	 */
	@ApiOperation(value = "新增角色接口")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(@ApiParam(value = "角色信息", required = true) @RequestBody SysRoleVO SysRoleVO) {
		String resultCode = iSysRoleService.save(SysRoleVO);
		return resultCode;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除角色接口")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@ApiParam(value = "角色ID", required = true) @PathVariable(name = "id") String id) {
		String resultCode = iSysRoleService.delete(id);
		return resultCode;
	}

	/**
	 * 
	 * @param sessionSysRole
	 * @return IResult
	 */
	@ApiOperation(value = "更新角色信息")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@ApiParam(value = "角色信息", required = true) @RequestBody SysRoleVO SysRoleVO) {
		String resultCode = iSysRoleService.update(SysRoleVO);
		return resultCode;
	}

	/**
	 * 
	 * 查询角色 分页查询
	 * 
	 */
	@ApiOperation(value = "查询角色信息")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Page<SysRoleVO> getSysRole(
			@ApiParam(value = "角色编号", required = false) @RequestParam(name = "pcCode", required = false) String pcCode,
			@ApiParam(value = "排序方式", required = false) @RequestParam(name = "sort", required = false) String sort,
			@ApiParam(value = "排序字段", required = false) @RequestParam(name = "sidx", required = false) String sidx,
			@ApiParam(value = "页码", required = false) @RequestParam(name = "page", required = false) Integer page,
			@ApiParam(value = "页大小", required = false) @RequestParam(name = "size", required = false) Integer size) {
		IPageRequest iPageRequest = new IPageRequest(sort, sidx, page, size);
		Page<SysRoleVO> SysRoles = iSysRoleService.getSysRole(pcCode, iPageRequest.getRequestPage());
		return SysRoles;
	}
	
}
