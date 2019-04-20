package com.hyn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hyn.spring.entity.SysWork;

@Repository
public interface ISysWorkRepository extends JpaRepository<SysWork, String>,JpaSpecificationExecutor<SysWork>{

}
