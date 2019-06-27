package com.hyn.spring.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.entity.SysWork;
import com.hyn.spring.repository.ISysUserRepository;
import com.hyn.spring.repository.ISysWorkRepository;
import com.hyn.spring.service.ISysWorkService;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.TimeUtil;
import com.hyn.spring.vo.SysWorkFindVO;
import com.hyn.spring.vo.SysWorkVO;

@Service
public class ISysWorkServiceImpl implements ISysWorkService {

	@Autowired
	ISysWorkRepository iSysWorkRepository;

	@Autowired
	ISysUserRepository iSysUserRepository;

	@Override
	public String save(SysUser sysUser, SysWorkVO sysWorkVO) {

		SysWork sysWork = new SysWork();
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
	public Page<SysWorkFindVO> findByCondition(String userName, String projectName, String type, Pageable pageable) {
		return null;
	}

	/**
	 * 构造断言
	 * 
	 * @param userName
	 * @return
	 */
	private Specification<SysWork> getWhereClause(String userName, String projectName, String type) {
		return new Specification<SysWork>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SysWork> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.conjunction();

				if (!StringUtils.isEmpty(userName)) {
					Optional<List<SysUser>> optional = iSysUserRepository.findByUserNameLike("%" + userName + "%");
					if (optional.isPresent()) {
						List<SysUser> users = optional.get();
						if (users != null && users.size() > 0) {
							In<String> in = criteriaBuilder.in(root.<String>get("userId"));
							for (SysUser user : users) {
								in.value(user.getId());
							}
							predicate.getExpressions().add(in);
						}
					}
				}

				if (!StringUtils.isEmpty(projectName)) {
					predicate.getExpressions().add(criteriaBuilder.equal(root.<String>get("projectName"), projectName));
				}

				if (!StringUtils.isEmpty(type)) {
					predicate.getExpressions().add(criteriaBuilder.equal(root.<String>get("type"), type));
				}
				return predicate;
			}
		};
	}
}
