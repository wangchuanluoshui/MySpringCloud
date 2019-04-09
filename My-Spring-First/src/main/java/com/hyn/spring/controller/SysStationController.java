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

import com.hyn.spring.service.ISysStationService;
import com.hyn.spring.utils.IPageRequest;
import com.hyn.spring.vo.SysStationVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "station", tags = "工位管理模块")
@Controller(value = "station")
@RequestMapping(value = "/station")
public class SysStationController {

	@Autowired
	ISysStationService iSysStationService;

	/**
	 * 
	 * @param SysStationVO
	 * @return
	 */
	@ApiOperation(value = "新增工位接口")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(@ApiParam(value = "工位信息", required = true) @RequestBody SysStationVO SysStationVO) {
		String resultCode = iSysStationService.save(SysStationVO);
		return resultCode;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除工位接口")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@ApiParam(value = "工位ID", required = true) @PathVariable(name = "id") String id) {
		String resultCode = iSysStationService.delete(id);
		return resultCode;
	}

	/**
	 * 
	 * @param sessionSysStation
	 * @return IResult
	 */
	@ApiOperation(value = "更新工位信息")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@ApiParam(value = "工位信息", required = true) @RequestBody SysStationVO SysStationVO) {
		String resultCode = iSysStationService.update(SysStationVO);
		return resultCode;
	}

	/**
	 * 
	 * 查询工位 分页查询
	 * 
	 */
	@ApiOperation(value = "查询工位信息")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Page<SysStationVO> getSysStation(
			@ApiParam(value = "工位编号", required = false) @RequestParam(name = "pcCode", required = false) String pcCode,
			@ApiParam(value = "排序方式", required = false) @RequestParam(name = "sort", required = false) String sort,
			@ApiParam(value = "排序字段", required = false) @RequestParam(name = "sidx", required = false) String sidx,
			@ApiParam(value = "页码", required = false) @RequestParam(name = "page", required = false) Integer page,
			@ApiParam(value = "页大小", required = false) @RequestParam(name = "size", required = false) Integer size) {
		IPageRequest iPageRequest = new IPageRequest(sort, sidx, page, size);
		Page<SysStationVO> SysStations = iSysStationService.getSysStation(pcCode, iPageRequest.getRequestPage());
		return SysStations;
	}

}
