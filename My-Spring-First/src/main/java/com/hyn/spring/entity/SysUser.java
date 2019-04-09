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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
* @Title: SysUser.java
* @Package com.hyn.spring.domain
* @Description: TODO
* @author hyn  
* @date 2018年12月9日 下午1:38:53
* @version V1.0  
 */
@Entity
@Table(name = "sys_user")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@ToString
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	/**
	 *  用户ID
	 */
	@Column(columnDefinition="varchar(36)  not null  comment '用户ID'  ")
	String id;

	/**
	 * 
	 * 用户状态
	 */
	@Column(columnDefinition = "enum('true','false') default  'true' not null comment '用户状态' ")
	String status;

	/**
	 *  邮箱
	 */
	@Column(columnDefinition = "varchar(20)  not null  default '' comment '用户邮箱'" )
	private String email;

	/**
	 *  登录用户名
	 */
	@Column(columnDefinition = "varchar(20)  not null unique default '' comment '登录用户名'" )
	private String loginName;

	/**
	 *  用户名名称
	 */
	@Column(columnDefinition = "varchar(10)  not null  default '' comment '用户名称'" )
	private String userName;

	/**
	 *  密码策略
	 */
	@Column(columnDefinition = "varchar(100)  not null  default '888888' comment '登录密码'" )
	private String password;

	/**
	 *  电话
	 */
	@Column(columnDefinition = "varchar(11)  not null unique default '' comment '手机号码'" )
	private String phone;

	/**
	 *  更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)    
	@Column(columnDefinition = "timestamp  default current_timestamp on update current_timestamp comment '更新时间'")
	@UpdateTimestamp
	private Date moditime;
	
	/**
	 * 用户-角色列表 （多对多）
	 * @return 
	 */
	@ManyToMany(mappedBy="sysUsers")
	private List<SysRole> sysRoles;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="org_id")
	SysOrganization sysOrganization;
	
}
