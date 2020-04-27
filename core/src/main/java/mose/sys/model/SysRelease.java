/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * what:    系统升级说明表. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 * 
 *
 * @author mose created on 2017年11月3日
 */
public class SysRelease {
	
	private int id;                   //主键
	
	@NotEmpty
	@Size(max=25)
	private String name;              //系统名称
	
	@NotEmpty
	@Size(max=10)
	private String version;           //版本号
	
	@NotEmpty
	@Size(max=10)
	private String releaseDate;       //发布日期
	
	@Size(max=100)
	private String releaseContent;    //发布内容
	
	@NotEmpty
	@Size(max=10)
	private String releaseBy;         //发布人
	
	public SysRelease(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseContent() {
		return releaseContent;
	}

	public void setReleaseContent(String releaseContent) {
		this.releaseContent = releaseContent;
	}

	public String getReleaseBy() {
		return releaseBy;
	}

	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}

	@Override
	public String toString() {
		return "SysRelease [id=" + id + ", name=" + name + ", version=" + version + ", releaseDate=" + releaseDate
				+ ", releaseContent=" + releaseContent + ", releaseBy=" + releaseBy + "]";
	}
	
	
	

}
