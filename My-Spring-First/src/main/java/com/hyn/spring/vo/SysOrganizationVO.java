package com.hyn.spring.vo;

import java.util.List;


public class SysOrganizationVO {
	
	/**
	 *  机构ID
	 */
	String id;

	/**
	 *  机构代码(行政区划码)
	 */
	private String code;

	/**
	 *  机构名称
	 */
	private String name;

	/**
	 *  机构类型
	 */
	private String type;

	/**
	 *  机构等级
	 */
	private String level;

	/**
	 *  上级节点ID
	 */
	private String pid;

	/**
	 *  电话
	 */
	private String phone;

	/**
	 *  地址
	 */
	private String addr;

	/**
	 *  负责人
	 */
	private String principal;


	List<String> userIds;


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


	public List<String> getUserIds() {
		return userIds;
	}


	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

}
