package com.hyn.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.entity.SysWork;
import com.hyn.spring.repository.ISysWorkRepository;
import com.hyn.spring.service.ISysWorkService;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.TimeUtil;
import com.hyn.spring.vo.SysWorkVO;

@Service
public class ISysWorkServiceImpl implements ISysWorkService{

	@Autowired
	ISysWorkRepository iSysWorkRepository;
	
	@Override
	public String save(SysUser sysUser, SysWorkVO sysWorkVO) {
		
		SysWork sysWork=new SysWork();
		sysWork.setJobDescription(sysWorkVO.getJobDescription());
		sysWork.setType(sysWorkVO.getType());
		sysWork.setProjectName(sysWorkVO.getProjectName());
		sysWork.setStartTime(new Date());
		sysWork.setEndTime(TimeUtil.addHour(new Date(), sysWorkVO.getDuration()));
		sysWork.setUserId(sysUser.getId());
		iSysWorkRepository.save(sysWork);
		return ICodes.CODE_0000;
	}

}
