package com.hyn.spring.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sys_work")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@ToString
public class SysWork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@Column(columnDefinition="varchar(36)  not null  comment '用户ID'  ")
	String id;
	
	@Column(columnDefinition = "varchar(50) default  '' not null comment '项目名' ")
	String projectName;
	
	@Column(columnDefinition = "varchar(50) default  '' not null comment '用户名' ")
	String userId;
	
	@Temporal(TemporalType.TIMESTAMP)    
	@Column(columnDefinition = "timestamp  default current_timestamp on update current_timestamp comment '开始时间'")
	Date startTime;

	@Temporal(TemporalType.TIMESTAMP)    
	@Column(columnDefinition = "timestamp  default current_timestamp on update current_timestamp comment '结束时间'")
	Date endTime;
	
	@Temporal(TemporalType.TIMESTAMP)    
	@Column(columnDefinition = "timestamp  default current_timestamp on update current_timestamp comment '更新时间'")
	@UpdateTimestamp
	Date moditime;
	
	@Column(columnDefinition = "varchar(100) comment '工作说明' ")
	String jobDescription;
	
	@Column(columnDefinition = "varchar(100) comment '工作类型（会议，开发，部署，讨论，其他）' ")
	String type;

	@Column(columnDefinition = "numeric(2,1)  COMMENT '评分' ")
	BigDecimal grade;
}
