package com.hyn.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyn.spring.entity.SysOrganization;
import com.hyn.spring.entity.SysUser;
import com.hyn.spring.repository.ISysOrganizationRepository;
import com.hyn.spring.repository.ISysUserRepository;
import com.hyn.spring.service.ISysOrganizationService;
import com.hyn.spring.vo.SysOrganizationVO;

@Service
public class ISysOrganizationServiceImpl implements ISysOrganizationService {

	@Autowired
	ISysOrganizationRepository iSysOrganizationRepository;
	
	@Autowired
	ISysUserRepository iSysUserRepository;

	@Override
	public String save(SysOrganizationVO sysUserVO) {

		SysOrganization sysOrganization = new SysOrganization();
		sysOrganization.setAddr(sysUserVO.getAddr());
		sysOrganization.setCode(sysUserVO.getCode());
		sysOrganization.setLevel(sysUserVO.getLevel());
		sysOrganization.setName(sysUserVO.getName());
		sysOrganization.setPhone(sysUserVO.getPhone());
		sysOrganization.setPid(sysUserVO.getPid());
		sysOrganization.setPrincipal(sysUserVO.getPrincipal());
		sysOrganization.setType(sysUserVO.getType());
		SysOrganization  resultOrganization=	iSysOrganizationRepository.save(sysOrganization);
		
		//用户关联部门结构
		List<String> userIds = sysUserVO.getUserIds();
		if (userIds != null && userIds.size() > 0) {
			for (String userId : userIds) {
				Optional<SysUser> optional=	iSysUserRepository.findById(userId);
				if(optional.isPresent())
				{
					SysUser sysUser=optional.get();
					sysUser.setSysOrganization(resultOrganization);
					iSysUserRepository.saveAndFlush(sysUser);
				}
			}
		}
		
		return "success";
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(SysOrganizationVO sysUserVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysOrganizationVO> getSysOrganization(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


}
