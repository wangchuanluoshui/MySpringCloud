package com.hyn.spring.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="工位")
public class SysStationVO {

	@ApiModelProperty(value = "id", name = "id")
	String id;

	/**
	 * 
	 * 个人电脑编号
	 */
	@ApiModelProperty(value = "个人电脑编号", name = "pcCode")
	String pcCode;

	/**
	 *  个人电脑IP
	 */
	@ApiModelProperty(value = "个人电脑IP", name = "pcIP")
	private String pcIP;
	
	
	@ApiModelProperty(value = "用户ID", name = "userId")
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPcCode() {
		return pcCode;
	}

	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}

	public String getPcIP() {
		return pcIP;
	}

	public void setPcIP(String pcIP) {
		this.pcIP = pcIP;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
