package com.hyn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hyn.spring.entity.SysOrganization;

public interface ISysOrganizationRepository extends JpaRepository<SysOrganization, String>,JpaSpecificationExecutor<SysOrganization>{

}
