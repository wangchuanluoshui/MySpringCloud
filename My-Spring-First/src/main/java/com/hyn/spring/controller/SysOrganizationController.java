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

import com.hyn.spring.service.ISysOrganizationService;
import com.hyn.spring.utils.IPageRequest;
import com.hyn.spring.vo.SysOrganizationVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "organization", tags = "机构管理模块")
@Controller(value = "organization")
@RequestMapping(value = "/organization")
public class SysOrganizationController {

	@Autowired
	ISysOrganizationService iSysOrganizationService;

	/**
	 * 
	 * @param SysOrganizationVO
	 * @return
	 */
	@ApiOperation(value = "新增机构接口")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(
			@ApiParam(value = "机构信息", required = true) @RequestBody SysOrganizationVO SysOrganizationVO) {
		String resultCode = iSysOrganizationService.save(SysOrganizationVO);
		return resultCode;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除机构接口")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@ApiParam(value = "机构ID", required = true) @PathVariable(name = "id") String id) {
		String resultCode = iSysOrganizationService.delete(id);
		return resultCode;
	}

	/**
	 * 
	 * @param sessionSysOrganization
	 * @return IResult
	 */
	@ApiOperation(value = "更新机构信息")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@ApiParam(value = "机构信息", required = true) @RequestBody SysOrganizationVO SysOrganizationVO) {
		String resultCode = iSysOrganizationService.update(SysOrganizationVO);
		return resultCode;
	}

	/**
	 * 
	 * 查询机构 分页查询
	 * 
	 */
	@ApiOperation(value = "查询机构信息")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Page<SysOrganizationVO> getSysOrganization(
			@ApiParam(value = "机构编号", required = false) @RequestParam(name = "name", required = false) String name,
			@ApiParam(value = "排序方式", required = false) @RequestParam(name = "sort", required = false) String sort,
			@ApiParam(value = "排序字段", required = false) @RequestParam(name = "sidx", required = false) String sidx,
			@ApiParam(value = "页码", required = false) @RequestParam(name = "page", required = false) Integer page,
			@ApiParam(value = "页大小", required = false) @RequestParam(name = "size", required = false) Integer size) {
		IPageRequest iPageRequest = new IPageRequest(sort, sidx, page, size);
		Page<SysOrganizationVO> SysOrganizations = iSysOrganizationService.getSysOrganization(name,
				iPageRequest.getRequestPage());
		return SysOrganizations;
	}


}
