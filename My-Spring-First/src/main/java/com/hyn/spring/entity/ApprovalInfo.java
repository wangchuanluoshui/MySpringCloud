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

@Entity
@Table(name = "approval_info")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="审批信息表")
public class ApprovalInfo implements Serializable{
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
	 *  更新时间
	 */
	@ApiModelProperty(value = "更新时间", name = "moditime", example = "2018-08-27 00:00:00")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp  not null default  current_timestamp comment '更新时间'")
	@UpdateTimestamp
	private Date moditime;
	
	@Column(columnDefinition = "VARCHAR(32)  not null default ''  COMMENT '用户'")
	String user;
	
	@Column(columnDefinition = "VARCHAR(32)  not null default ''  COMMENT '机构'")
	String organization;
	
	@Column(columnDefinition = "CHAR(32)  not null default ''  COMMENT '状态（1：同意，2：拒绝）'")
	String status;
	
	@Column(columnDefinition = "VARCHAR(32)  not null default ''  COMMENT '机构'")
	String auditor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getModitime() {
		return moditime;
	}

	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	@Override
	public String toString() {
		return "ApprovalInfo [id=" + id + ", moditime=" + moditime + ", user=" + user + ", organization=" + organization
				+ ", status=" + status + ", auditor=" + auditor + "]";
	}
}
