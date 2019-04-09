package com.hyn.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hyn.spring.entity.SysUser;

public interface ISysUserRepository extends JpaRepository<SysUser, String>,JpaSpecificationExecutor<SysUser>{

	/**
	 * 根据用户登录名和密码查询
	 * @param loString
	 * @param password
	 */
	Optional<SysUser> findByLoginNameAndPassword(String loginname,String password);
	
	/**
	 * 根据用户名 Distinct 查询用户
	 * @param userName
	 * @return
	 */
	Optional<List<SysUser>> findEmailDistinctByUserName(String userName);
	
	/**
	 * 根据用户名 IgnoreCase 查询用户 忽略username大小写
	 * @param userName
	 * @return
	 */
	Optional<List<SysUser>> findByUserNameIgnoreCase(String userName);
	
	/**
	 * 根据用户名 OrderBy moditime 查询用户 
	 * @param userName
	 * @return
	 */
	Optional<List<SysUser>> findByUserNameOrderByModitimeAsc(String userName);
	
	
	/**
	 * 根据用户名 or登录用户  查询用户 
	 * @param userName
	 * @param userName
	 * @return
	 */
	Optional<List<SysUser>> findByUserNameOrLoginName(String userName,String loginName);
	
	/**
	 * 分页查询
	 * @param userName
	 * @param pageable
	 * @return
	 */
	Page<SysUser> findByUserName(String userName,Pageable pageable);

	/**
	 * @Query 测试
	 * @param userName
	 * @return
	 */
	@Query(value = "select * from sys_user where username = ?1", nativeQuery = true)
	Optional<List<SysUser>> findByUserName(String userName);

	
	/**
	 * @Query 测试
	 * @param userName
	 * @return
	 */
	@Query(value = "select * from sys_user where email like :email", nativeQuery = true)
	Optional<List<SysUser>> findByEmail(@Param("email") String email);
	
	
	SysUser findByLoginName(String loginUser);
	
}
