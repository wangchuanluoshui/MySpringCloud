package com.hyn.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyn.spring.vo.SysOrganizationVO;

public interface ISysOrganizationService {
	
	/**
	 * 增加
	 * @return
	 */
	String save(SysOrganizationVO sysUserVO);
	
	/**
	 * 删除
	 * @return
	 */
	String delete(String id);
	
	/**
	 * 更新
	 * @return
	 */
	String update(SysOrganizationVO sysUserVO);
	
	/**
	 * 分页查询
	 * @param userName
	 * @param pageable
	 * @return
	 */
	Page<SysOrganizationVO> getSysOrganization(String name,Pageable pageable);


}
