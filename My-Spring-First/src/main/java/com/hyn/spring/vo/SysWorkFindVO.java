package com.hyn.spring.vo;

import java.math.BigDecimal;import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="工作量查詢")
@Data
public class SysWorkFindVO {
	
	@ApiModelProperty(value = "ID", name = "ID")
	String id;
	
	@ApiModelProperty(value = "项目名称", name = "projectName")
	String projectName;
	
	@ApiModelProperty(value = "开始时间", name = "duration")
	Date startTime;
	
	@ApiModelProperty(value = "结束时间", name = "duration")
	Date endTime;

	@ApiModelProperty(value = "工作类型", name = "type")
	Date startTime;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	Date endTime;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	String userName;
	
	@ApiModelProperty(value = "获赞数", name = "like")
	Integer like;

	@ApiModelProperty(value = "开发人员", name = "userName")
	String userName;
	
	@ApiModelProperty(value = "评分", name = "grade")
	BigDecimal grade;

}
