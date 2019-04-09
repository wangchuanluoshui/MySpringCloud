package com.hyn.spring.config.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hyn.spring.entity.SysOrganization;
import com.hyn.spring.entity.SysRole;
import com.hyn.spring.entity.SysStation;


/**
 * 
 * @Title:：SysUser.java 
 * @Package ：com.summit.homs.dto 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年8月16日 下午4:05:38 
 * @version ： 1.0
 */


public class LoginSysUser  implements UserDetails,Serializable {

	String id;

	/**
	 * 
	 * 用户状态
	 */
	String status;

	/**
	 *  邮箱
	 */
	private String email;

	/**
	 *  登录用户名
	 */
	private String loginName;

	/**
	 *  用户名名称
	 */
	private String userName;

	/**
	 *  密码策略
	 */
	private String password;

	/**
	 *  电话
	 */
	private String phone;
	
	SysStation sysStation;
	
	List<SysRole> sysRoles;
	
	SysOrganization sysOrganization;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = getSysRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }


	@Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SysStation getSysStation() {
		return sysStation;
	}

	public void setSysStation(SysStation sysStation) {
		this.sysStation = sysStation;
	}

	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}
