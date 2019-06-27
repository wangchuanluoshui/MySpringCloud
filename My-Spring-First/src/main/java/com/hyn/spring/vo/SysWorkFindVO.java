package com.hyn.spring.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="工作量查询")
@Data
public class SysWorkFindVO {
	
	@ApiModelProperty(value = "项目名称", name = "projectName")
	String projectName;
	
	@ApiModelProperty(value = "工作描述", name = "jobDescription")
	String jobDescription;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	String type;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	Date startTime;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	Date endTime;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	String userName;
	
	@ApiModelProperty(value = "获赞数", name = "like")
	Integer like;
}
