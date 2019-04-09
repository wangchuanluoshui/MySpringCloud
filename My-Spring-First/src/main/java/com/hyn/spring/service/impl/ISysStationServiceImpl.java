package com.hyn.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyn.spring.entity.SysStation;
import com.hyn.spring.entity.SysUser;
import com.hyn.spring.repository.ISysStationRepository;
import com.hyn.spring.service.ISysStationService;
import com.hyn.spring.vo.SysStationVO;

@Service
public class ISysStationServiceImpl implements ISysStationService {
	@Autowired
	ISysStationRepository iSysStationRepository;
	
	@Override
	public String save(SysStationVO sysStationVO) {
		SysStation sysStation=new SysStation();
		sysStation.setPcCode(sysStationVO.getPcCode());
		sysStation.setPcIP(sysStationVO.getPcIP());
		//关联用户
		SysUser sysUser=new SysUser();
		sysUser.setId(sysStationVO.getUserId());
		sysStation.setSysUser(sysUser);
		iSysStationRepository.save(sysStation);
		return "success";
	}

	@Override
	public String delete(String id) {
		
		return null;
	}

	@Override
	public String update(SysStationVO sysStationVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysStationVO> getSysStation(String pcCode, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
