package com.hyn.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sys_station")
@EntityListeners(AuditingEntityListener.class)
public class SysStation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	/**
	 *  工位ID
	 */
	@Column(columnDefinition="varchar(36)  not null  comment '工位ID'  ")
	String id;
	
	
	/**
	 *  个人电脑编号
	 */
	@Column(columnDefinition = "varchar(10)  not null unique default '' comment '个人电脑编号'" )
	private String pcCode;
	
	
	/**
	 *  pcIP 个人电脑IP
	 */
	@Column(columnDefinition = "varchar(20)  not null unique default '' comment '个人电脑IP'" )
	private String pcIP;
	
	
	
	@OneToOne
	@JoinColumn(name="user_id")
	SysUser sysUser;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPcCode() {
		return pcCode;
	}


	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}


	public String getPcIP() {
		return pcIP;
	}


	public void setPcIP(String pcIP) {
		this.pcIP = pcIP;
	}


	public SysUser getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
	
}
