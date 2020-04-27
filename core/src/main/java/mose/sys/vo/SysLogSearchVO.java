/**
 * Copyright 2017 弘远技术研发中心. All rights reserved Project Name:cdpf_v1 Module
 * Name:core
 */
package mose.sys.vo;


import mose.core.page.PageSearchVO;

/**
 * 
 * what:日志管理查询条件VO
 * 
 *
 * @author mose created on 2017年6月13日
 */
public class SysLogSearchVO extends PageSearchVO {
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 起始日期
	 */
	private String startDate;
	/**
	 * 终止日期
	 */
	private String endDate;

	public Integer getUserId() {
		return userId;
	}

	public String getSearchUsername() {
		return "%" + username + "%";
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SysLogSearchVO [userId=" + userId + ", username=" + username + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
