package com.hyn.spring.vo;

import java.util.List;

public class SysRoleVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 *  角色名称
	 */
	private String name;
	
	/**
	 *  角色描述
	 */
	private String comments;
	
	/**
	 * 用户ID
	 * 
	 */
	public List<String> userIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
}
