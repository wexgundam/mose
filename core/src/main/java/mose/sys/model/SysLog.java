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
 * what: 操作日志
 *
 * @author mose created on 2017年6月13日
 */
public class SysLog {
    private long id;//id
    private int userId;//用户id
    private Date operaDate;//操作日期
    private String operaIp;//ip地址
    private String moduleName;//模块id
    private String operaName;//操作名称
    private String operaUrl;//操作url
    private String operaParams;//参数
    private String realName;//用户姓名
    private String username;//登录账号

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", operaDate=" + operaDate +
                ", operaIp='" + operaIp + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", operaName='" + operaName + '\'' +
                ", operaUrl='" + operaUrl + '\'' +
                ", operaParams='" + operaParams + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getOperaDate() {
        return operaDate;
    }

    public void setOperaDate(Date operaDate) {
        this.operaDate = operaDate;
    }

    public String getOperaIp() {
        return operaIp;
    }

    public void setOperaIp(String operaIp) {
        this.operaIp = operaIp;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperaName() {
        return operaName;
    }

    public void setOperaName(String operaName) {
        this.operaName = operaName;
    }

    public String getOperaUrl() {
        return operaUrl;
    }

    public void setOperaUrl(String operaUrl) {
        this.operaUrl = operaUrl;
    }

    public String getOperaParams() {
        return operaParams;
    }

    public void setOperaParams(String operaParams) {
        this.operaParams = operaParams;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
