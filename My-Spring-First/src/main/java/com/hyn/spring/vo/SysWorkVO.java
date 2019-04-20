package com.hyn.spring.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="工作量")
@Data
public class SysWorkVO {
	
	@ApiModelProperty(value = "项目名称", name = "projectName")
	String projectName;
	
	@ApiModelProperty(value = "持续时长", name = "duration")
	Integer duration;

	@ApiModelProperty(value = "工作描述", name = "jobDescription")
	String jobDescription;
	
	@ApiModelProperty(value = "工作类型", name = "type")
	String type;
}
