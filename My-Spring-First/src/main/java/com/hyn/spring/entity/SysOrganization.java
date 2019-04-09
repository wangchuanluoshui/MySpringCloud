package com.hyn.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * @Title:：SysOrganization.java 
 * @Package ：com.summit.homs.dto.sys 
 * @Description： TODO
 * @author： hyn   
 * @date： 2018年8月28日 下午9:10:40 
 * @version ： 1.0
 */
@Entity
@Table(name = "sys_organization")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="组织机构")
public class SysOrganization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	/**
	 *  机构ID
	 */
	@Column(length = 36, columnDefinition = "varchar(36) COMMENT '机构ID'")
	String id;

	/**
	 *  机构代码(行政区划码)
	 */
	@Column(columnDefinition = "VARCHAR(6)  not null  default '' COMMENT '机构代码(行政区划码)'")
	private String code;

	/**
	 *  机构名称
	 */
	@Column(columnDefinition = "VARCHAR(20)  unique not null  default '' COMMENT '机构名称'")
	private String name;

	/**
	 *  机构类型
	 */
	@Column(columnDefinition = "VARCHAR(10)  not null COMMENT '机构类型'")
	private String type;

	/**
	 *  机构等级
	 */
	@Column(columnDefinition = "VARCHAR(10)  not null  default '' COMMENT '机构等级'")
	private String level;

	/**
	 *  上级节点ID
	 */
	@Column(columnDefinition = "VARCHAR(36)  not null  default '' COMMENT '机构等级'")
	private String pid;

	/**
	 *  电话
	 */
	@Column(columnDefinition = "VARCHAR(11)  not null  default '' COMMENT '手机号码'")
	private String phone;

	/**
	 *  地址
	 */
	@Column(columnDefinition = "VARCHAR(100)  not null  default '' COMMENT '地址'")
	private String addr;

	/**
	 *  负责人
	 */
	@Column(columnDefinition = "VARCHAR(10)  not null default ''  COMMENT '负责人'")
	private String principal;

	/**
	 *  更新时间
	 */
	@ApiModelProperty(value = "更新时间", name = "moditime", example = "2018-08-27 00:00:00")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp  not null default  current_timestamp comment '更新时间'")
	@UpdateTimestamp
	private Date moditime;

	@OneToMany(mappedBy="sysOrganization")
	List<SysUser> sysUsers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Date getModitime() {
		return moditime;
	}

	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}

	public List<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	
	
}
