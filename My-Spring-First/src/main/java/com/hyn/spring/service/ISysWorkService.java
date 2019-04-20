package com.hyn.spring.service;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.vo.SysWorkVO;

public interface ISysWorkService {
	
	/**
	 * 保存当前工作
	 * @param sysWorkVO
	 * @return
	 */
	public String save(SysUser sysUser, SysWorkVO sysWorkVO);

}
