package com.hyn.spring.service;

import java.util.List;

import com.hyn.spring.entity.UserInfo;

public interface UserInfoService {
	UserInfo save(UserInfo userInfo);

	void remove(Long id);

	UserInfo findOne(UserInfo userInfo);
	
	List<UserInfo> findAll();
}
