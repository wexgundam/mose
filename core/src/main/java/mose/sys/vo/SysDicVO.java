/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:TODO
 * Module Name:core
 */
package mose.sys.vo;

/**
 * what:    用于页面展示树形结构. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 * 
 *
 * @author mose created on 2017年11月2日
 */
public class SysDicVO {
	private int id;                       //主键id
	private String code;                  //字典代码
	private String name;                  //字典名称
	private int pid;                      //父id
	
	
	
	public SysDicVO(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
	
	@Override
	public String toString() {
		return "SysDicVO [id=" + id + ", code=" + code + ", name=" + name + ", pid=" + pid + "]";
	}
	
	
    
	
	
	
}
