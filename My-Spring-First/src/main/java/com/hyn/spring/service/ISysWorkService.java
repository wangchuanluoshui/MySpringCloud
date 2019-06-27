package com.hyn.spring.service;

<<<<<<< HEAD
=======
import org.springframework.data.domain.AbstractPageRequest;
>>>>>>> a2df2dc86fe14a41248465922630630fa2819b55
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
	 * 根据条件查询
	 * @param projectName
	 * @param type
	 * @param pageable
	 * @return
	 */
	public Page<SysWorkFindVO> findByCondition(String projectName, String type, Pageable pageable);

}
