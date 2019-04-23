package com.hyn.spring.service;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.vo.SysWorkFindVO;
import com.hyn.spring.vo.SysWorkVO;

public interface ISysWorkService {
	
	/**
	 * 保存当前工作
	 * @param sysWorkVO
	 * @return
	 */
	public String save(SysUser sysUser, SysWorkVO sysWorkVO);

	/**
	 * 根据条件查询
	 * @param projectName
	 * @param type
	 * @param pageable
	 * @return
	 */
	public Page<SysWorkFindVO> findByCondition(String projectName, String type, Pageable pageable);
	
	

}
