package com.hyn.spring.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="用户")
@Data
public class SysUserVO {
	
	@ApiModelProperty(value = "id", name = "id")
	String id;

	/**
	 * 
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", name = "status")
	String status;

	/**
	 *  邮箱
	 */
	@ApiModelProperty(value = "邮箱", name = "email")
	private String email;

	/**
	 *  登录用户名
	 */
	@ApiModelProperty(value = "登录用户名", name = "loginName")
	private String loginName;

	/**
	 *  用户名
	 */
	@ApiModelProperty(value = "用户名", name = "userName")
	private String userName;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", name = "password")
	private String password;

	/**
	 *  电话
	 */
	@ApiModelProperty(value = "电话", name = "phone")
	private String phone;

	
	@ApiModelProperty(value = "所属单位", name = "orgName")
	private String orgName;
	

}
