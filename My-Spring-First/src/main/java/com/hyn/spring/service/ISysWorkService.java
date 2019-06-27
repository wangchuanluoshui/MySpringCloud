package com.hyn.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.vo.SysWorkFindVO;
import com.hyn.spring.vo.SysWorkVO;

public interface ISysWorkService {

	/**
	 * 保存当前工作
	 * 
	 * @param sysWorkVO
	 * @return
	 */
	public String save(SysUser sysUser, SysWorkVO sysWorkVO);

	/**
	 * 根据体检查询工作类型
	 * 
	 * @param userName
	 * @param projectName
	 * @param type
	 * @param abstractPageRequest 
	 * @return
	 */
	Page<SysWorkFindVO> findByCondition(String userName, String projectName, String type, Pageable pageable);
}
