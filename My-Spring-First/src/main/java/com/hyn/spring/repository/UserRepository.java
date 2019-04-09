package com.hyn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyn.spring.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{

}
