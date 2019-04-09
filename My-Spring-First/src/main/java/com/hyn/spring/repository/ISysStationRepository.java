package com.hyn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hyn.spring.entity.SysStation;
import com.hyn.spring.entity.SysUser;
import java.util.List;

public interface ISysStationRepository extends JpaRepository<SysStation, String>,JpaSpecificationExecutor<SysStation>{

	SysStation findBySysUser(SysUser sysuser);
}
