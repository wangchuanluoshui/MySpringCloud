package com.hyn.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyn.spring.vo.SysRoleVO;
import com.hyn.spring.vo.SysUserVO;

public interface ISysRoleService {
	
	/**
	 * 增加
	 * @return
	 */
	String save(SysRoleVO sysUserVO);
	
	/**
	 * 删除
	 * @return
	 */
	String delete(String id);
	
	/**
	 * 更新
	 * @return
	 */
	String update(SysRoleVO sysUserVO);
	
	/**
	 * 分页查询
	 * @param userName
	 * @param pageable
	 * @return
	 */
	Page<SysRoleVO> getSysRole(String name,Pageable pageable);

}
