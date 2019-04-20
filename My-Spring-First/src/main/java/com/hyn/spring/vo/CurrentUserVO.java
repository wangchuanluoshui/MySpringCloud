package com.hyn.spring.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户")
@Data
public class CurrentUserVO {
	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户名", name = "username")
	String userName;
	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", name = "status")
	String phone;
	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", name = "status")
	String email;
	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", name = "status")
	String portrait;
	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", name = "status")
	String description;

	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", name = "status")
	String orgName;

	@ApiModelProperty(value = "常住地址", name = "address")
	String address;
}
