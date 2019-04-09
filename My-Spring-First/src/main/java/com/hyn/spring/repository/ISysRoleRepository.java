package com.hyn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hyn.spring.entity.SysRole;
import com.hyn.spring.entity.SysUser;

import java.util.List;

public interface ISysRoleRepository extends JpaRepository<SysRole, String>,JpaSpecificationExecutor<SysRole>{

	List<SysRole> findBySysUsers(List<SysUser> sysusers);
}
