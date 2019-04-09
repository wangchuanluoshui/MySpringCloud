package com.hyn.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyn.spring.vo.SysUserVO;

public interface ISysUserService {
	
	/**
	 * 增加一个用户
	 * @return
	 */
	String save(SysUserVO sysUserVO);
	
	/**
	 * 批量新增
	 * @param sysUserVOs
	 * @return
	 */
	String batchSave(List<SysUserVO> sysUserVOs);
	
	/**
	 * 批量新增
	 * @param sysUserVOs
	 * @return
	 */
	String batchSave2(List<SysUserVO> sysUserVOs);
	
	/**
	 * 删除一个用户
	 * @return
	 */
	String delete(String id);
	
	/**
	 * 更新一个用户
	 * @return
	 */
	String update(SysUserVO sysUserVO);
	
	/**
	 * 分页查询用户
	 * @param userName用户名
	 * @param pageable 分页查询条件
	 * @return
	 */
	Page<SysUserVO> getSysUser(String userName, String phone,String status,String orgName, Pageable pageable);
	
	
	List<SysUserVO> listSysUser(String userName);

}
