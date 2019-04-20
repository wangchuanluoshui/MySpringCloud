package com.hyn.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hyn.spring.entity.SysOrganization;
import com.hyn.spring.entity.SysUser;
import com.hyn.spring.repository.ISysUserDaoImpl;
import com.hyn.spring.repository.ISysUserRepository;
import com.hyn.spring.service.ISysUserService;
import com.hyn.spring.utils.BeanUtils;
import com.hyn.spring.utils.ICodes;
import com.hyn.spring.utils.IPageResponse;
import com.hyn.spring.vo.CurrentUserVO;
import com.hyn.spring.vo.SysUserVO;

@Service
public class ISysUserServiceImpl implements ISysUserService {

	@Autowired
	ISysUserRepository iSysUserRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public String save(SysUserVO sysUserVO) {
		SysUser sysUser = new SysUser();
		sysUser.setEmail(sysUserVO.getEmail());
		sysUser.setLoginName(sysUserVO.getLoginName());
		
		if(StringUtils.isEmpty(sysUserVO.getPassword()))
		{
			sysUserVO.setPassword("888888");
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		sysUser.setPassword(encoder.encode(sysUserVO.getPassword().trim()));
		sysUser.setPhone(sysUserVO.getPhone());
		sysUser.setStatus(sysUserVO.getStatus());
		sysUser.setUserName(sysUserVO.getUserName());
		iSysUserRepository.save(sysUser);
		return ICodes.CODE_0000;
	}

	@Override
	public String delete(String id) {
		iSysUserRepository.deleteById(id);
		return ICodes.CODE_0000;
	}

	@Override
	public String update(SysUserVO sysUserVO) {
		Optional<SysUser> optional = iSysUserRepository.findById(sysUserVO.getId());
		if (optional.isPresent()) {
			SysUser sysUser = optional.get();
			sysUser.setEmail(sysUserVO.getEmail());
			sysUser.setLoginName(sysUserVO.getLoginName());
			sysUser.setPhone(sysUserVO.getPhone());
			sysUser.setStatus(sysUserVO.getStatus());
			sysUser.setUserName(sysUserVO.getUserName());
			sysUser.setId(sysUserVO.getId());
			iSysUserRepository.save(sysUser);
			return ICodes.CODE_0000;
		} else {
			return ICodes.CODE_9998;
		}
	}

	@Override
	public Page<SysUserVO> getSysUser(String userName, String phone,String status,String orgName, Pageable pageable) {

		Page<SysUser> page = iSysUserRepository.findAll(this.getWhereClause(userName, phone,status,orgName), pageable);
		List<SysUserVO> voList = new LinkedList<>();
		for (SysUser sysUser : page.getContent()) {
			SysUserVO sysUserVO = new SysUserVO();
			sysUserVO.setEmail(sysUser.getEmail());
			sysUserVO.setId(sysUser.getId());
			sysUserVO.setLoginName(sysUser.getLoginName());
			sysUserVO.setPassword(sysUser.getPassword());
			sysUserVO.setPhone(sysUser.getPhone());
			sysUserVO.setStatus(sysUser.getStatus());
			sysUserVO.setUserName(sysUser.getUserName());
			SysOrganization organization = sysUser.getSysOrganization();
			if (organization != null) {
				sysUserVO.setOrgName(organization.getName());
			}
			voList.add(sysUserVO);
		}
		Page<SysUserVO> voPage = new IPageResponse<>(voList, pageable, page.getTotalElements());
		return voPage;
	}

	/**
	 * 构造断言
	 * 
	 * @param userName
	 * @return
	 */
	private Specification<SysUser> getWhereClause(String userName, String phone,String status,String orgName) {
		return new Specification<SysUser>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.conjunction();

				if (!StringUtils.isEmpty(userName)) {
					predicate.getExpressions()
							.add(criteriaBuilder.like(root.<String>get("userName"), "%" + userName + "%"));
				}

				if (!StringUtils.isEmpty(phone)) {
					predicate.getExpressions().add(criteriaBuilder.like(root.<String>get("phone"), "%" + phone + "%"));
				}
				
				if (!StringUtils.isEmpty(status)) {
					predicate.getExpressions().add(criteriaBuilder.equal(root.<String>get("status"), status));
				}
				return predicate;
			}
		};
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public String batchSave(List<SysUserVO> sysUserVOs) {

		Optional<SysUser> optional = iSysUserRepository.findById("2c987c3167a23e990167a240e0c80008");
		SysUser user = optional.get();

		List<SysUser> sysUsers = new ArrayList<>();
		Long startTime = System.currentTimeMillis();
		if (optional.isPresent()) {
			// 提交1W個
			for (int i = 0; i < 100000; i++) {
				SysUser sysUser = new SysUser();
//				sysUser.setId(String.valueOf(i));
				sysUser.setEmail(user.getEmail());
				sysUser.setLoginName(user.getLoginName());
				sysUser.setPassword(user.getPassword());
				sysUser.setPhone(user.getPhone());
				sysUser.setStatus(user.getStatus());
				sysUser.setUserName(user.getUserName());
				sysUser.setModitime(new Date());
				sysUser.setSysOrganization(user.getSysOrganization());
				sysUser.setSysRoles(sysUser.getSysRoles());
				sysUsers.add(sysUser);
			}
			for (int i = 0; i < sysUsers.size(); i++) {
				entityManager.persist(sysUsers.get(i));
				if (i % 500 == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("耗时：" + (endTime - startTime) + "ms");

		return "success";

	}

	@Override
	@Cacheable(value = "sysuser", key = "targetClass+#userName")
	public List<SysUserVO> listSysUser(String userName) {

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select id from sys_user where user_name = ?1");

		String sql = sqlBuffer.toString();
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userName);

		List<String> sysUsers = query.getResultList();
		System.out.println(sysUsers);
		return null;
	}

	@Override
	public String batchSave2(List<SysUserVO> sysUserVOs) {
		Optional<SysUser> optional = iSysUserRepository.findById("2c987c3167a23e990167a240e0c80008");
		SysUser user = optional.get();

		List<SysUser> sysUsers = new ArrayList<>();
		if (optional.isPresent()) {
			// 提交1W個
			for (int i = 0; i < 10000; i++) {
				SysUser sysUser = new SysUser();
				sysUser.setId(String.valueOf(i));
				sysUser.setEmail(user.getEmail());
				sysUser.setLoginName(user.getLoginName());
				sysUser.setPassword(user.getPassword());
				sysUser.setPhone(user.getPhone());
				sysUser.setStatus(user.getStatus());
				sysUser.setUserName(user.getUserName());
				sysUser.setModitime(new Date());
				sysUser.setSysOrganization(user.getSysOrganization());
				sysUser.setSysRoles(sysUser.getSysRoles());
				sysUsers.add(sysUser);
			}
			String sql = "insert into sys_user(id,email,login_name,moditime,password,phone,status,user_name) values (:id,:email,:loginName,:moditime,:password,:phone,:status,:userName)";
			SqlParameterSource[] sqlParameterSource = SqlParameterSourceUtils.createBatch(sysUsers);
			Long startTime = System.currentTimeMillis();
			namedParameterJdbcTemplate.batchUpdate(sql, sqlParameterSource);
			Long endTime = System.currentTimeMillis();
			System.out.println("耗时：" + (endTime - startTime) + "ms");

		}
		return null;
	}

	@Override
	public CurrentUserVO currentUser() {
		CurrentUserVO currentUserVO=new CurrentUserVO();
		SysUser sysUser= iSysUserRepository.findByLoginName("heyanan");
		try {
			currentUserVO=BeanUtils.oneCopy(CurrentUserVO.class, sysUser);
			currentUserVO.setOrgName(sysUser.getSysOrganization().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return currentUserVO;
	}

}
