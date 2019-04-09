package com.hyn.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyn.spring.entity.SysRole;
import com.hyn.spring.entity.SysUser;
import com.hyn.spring.repository.ISysRoleRepository;
import com.hyn.spring.service.ISysRoleService;
import com.hyn.spring.vo.SysRoleVO;

@Service
public class ISysRoleServiceImpl implements ISysRoleService {

	@Autowired
	ISysRoleRepository iSysRoleRepository;

	@Override
	public String save(SysRoleVO sysRoleVO) {
		SysRole sysRole = new SysRole();
		sysRole.setComments(sysRoleVO.getComments());
		sysRole.setName(sysRoleVO.getName());
		List<String> userIds = sysRoleVO.getUserIds();
		if (userIds != null && userIds.size() > 0) {
			List<SysUser> users = new ArrayList<>(userIds.size());
			for (String userId : userIds) {
				SysUser user = new SysUser();
				user.setId(userId);
				users.add(user);
			}
			sysRole.setSysUsers(users);
		}
		iSysRoleRepository.save(sysRole);
		return "success";
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(SysRoleVO sysUserVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysRoleVO> getSysRole(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
