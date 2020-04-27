/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 
 * what: 用户登录VO
 *
 * @author mose created on 2017年6月13日
 */
public class SysUserLogin {
	/**
	 * 编号
	 */
	private long id;
	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 登录时间
	 */
	private Date loginDate;
	/**
	 * 登录IP
	 */
	private String loginIp;
	/**
	 * 登录终端
	 */
	private String terminal;
	/**
	 * 浏览器类型
	 */
	private String explorerType;
	/**
	 * 浏览器版本
	 */
	private String explorerVersion;
	/**
	 * 登录账号
	 */
	private String username;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * sessionId
	 */
	private String sessionid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getExplorerVersion() {
		return explorerVersion;
	}

	public void setExplorerVersion(String explorerVersion) {
		this.explorerVersion = explorerVersion;
	}

	public String getExplorerType() {
		return explorerType;
	}

	public void setExplorerType(String explorerType) {
		this.explorerType = explorerType;
	}
}
