package com.hyn.spring.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.hyn.spring.entity.SysUser;
import com.hyn.spring.repository.ISysRoleRepository;
import com.hyn.spring.repository.ISysStationRepository;
import com.hyn.spring.repository.ISysUserRepository;

/**
 * 
 * @Title:：UserDetailsServiceImpl.java 
 * @Package ：com.summit.homs.service.imp 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年8月21日 下午2:22:36 
 * @version ： 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	ISysUserRepository userRepository;

	@Autowired
	ISysRoleRepository iSysRoleRepository;
	
	@Autowired
	ISysStationRepository iSysStationRepository;
	@Override
	public UserDetails loadUserByUsername(String loginName) {
		LoginSysUser loginSysUser = new LoginSysUser();

		SysUser user = userRepository.findByLoginName(loginName);
		loginSysUser.setEmail(user.getEmail());
		loginSysUser.setId(user.getId());
		loginSysUser.setLoginName(user.getLoginName());
		loginSysUser.setPassword(user.getPassword());
		loginSysUser.setPhone(user.getPhone());
		loginSysUser.setStatus(user.getStatus());
		loginSysUser.setSysOrganization(user.getSysOrganization());
		//查詢角色
		List<SysUser> sysUsers=new ArrayList<>(1);
		loginSysUser.setSysRoles(iSysRoleRepository.findBySysUsers(sysUsers));
		loginSysUser.setSysStation(iSysStationRepository.findBySysUser(user));
		loginSysUser.setUserName(user.getUserName());
		return loginSysUser;
	}
}
