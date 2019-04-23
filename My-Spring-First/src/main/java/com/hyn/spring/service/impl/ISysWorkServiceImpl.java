package com.hyn.spring.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.entity.SysWork;
import com.hyn.spring.repository.ISysUserRepository;
import com.hyn.spring.repository.ISysWorkRepository;
import com.hyn.spring.service.ISysWorkService;
import com.hyn.spring.utils.BeanUtils;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.IPageResponse;
import com.hyn.spring.utils.TimeUtil;
import com.hyn.spring.vo.SysWorkFindVO;
import com.hyn.spring.vo.SysWorkVO;

@Service
public class ISysWorkServiceImpl implements ISysWorkService{

	@Autowired
	ISysWorkRepository iSysWorkRepository;
	
	@Autowired
	ISysUserRepository iSysUserRepository;
	
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

	@Override
	public Page<SysWorkFindVO> findByCondition(String projectName, String type, Pageable pageable) {
		
		Page<SysWork> page=  iSysWorkRepository.findAll(pageable);
		
		List<SysWork> content=page.getContent();
		
		List<SysWorkFindVO> resultVOs=new LinkedList<SysWorkFindVO>();

		for(SysWork sysWork:content)
		{
			SysWorkFindVO sysWorkFindVO=new SysWorkFindVO();
			try {
				sysWorkFindVO=BeanUtils.oneCopy(SysWorkFindVO.class, sysWork);
				Optional<SysUser> optional= iSysUserRepository.findById(sysWork.getUserId());
				if(optional.isPresent())
				{
					sysWorkFindVO.setUserName(optional.get().getUserName());
				}
				resultVOs.add(sysWorkFindVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new IPageResponse<>(resultVOs, pageable, page.getTotalElements());
	}

}
