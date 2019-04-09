package com.hyn.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
* @Title: SysRole.java
* @Package com.hyn.spring.entity
* @Description: TODO
* @author hyn  
* @date 2018年12月13日 下午5:38:06
* @version V1.0  
 */
@Entity
@Table(name = "sys_role")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="系统角色")
public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	/**
	 *  角色ID
	 */
	@Column(columnDefinition="varchar(36)  not null  COMMENT '角色ID'  ")
	private String id;

	/**
	 *  角色名称
	 */
	@Column(columnDefinition="varchar(20)  not null unique COMMENT '角色名称'  default ''")
	private String name;
	
	/**
	 *  角色描述
	 */
	@Column(columnDefinition="varchar(50)  not null COMMENT '角色描述'  default ''")
	private String comments;
	

	/**
	 *  更新时间
	 */
	@ApiModelProperty(value = "更新时间", name = "moditime", example = "新增，修改，无此字段")
	@Temporal(TemporalType.TIMESTAMP)    
	@Column(columnDefinition = "TIMESTAMP  not null default  CURRENT_TIMESTAMP COMMENT '更新时间'")
	@UpdateTimestamp
	private Date moditime;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.DETACH)
    @JoinTable(name = "sys_user_role_r",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
	List<SysUser> sysUsers;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}



	public Date getModitime() {
		return moditime;
	}


	public void setModitime(Date updateTime) {
		this.moditime = updateTime;
	}
	
	


	public List<SysUser> getSysUsers() {
		return sysUsers;
	}


	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}


	@Override
	public String toString() {
		return "SysRole [id=" + id + ", name=" + name + ", comments=" + comments + ", moditime=" + moditime + "]";
	}
	
	
}
