/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mose.core.string.StringUtil;
import mose.sys.vo.SysDicVO;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * what: 系统字典类s. <br/>
 * when: (这里描述这个类的适用时机 – 可选).<br/>
 * how: (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 * 
 *
 * @author mose created on 2017年11月3日
 */
public class SysDic {

	private int id; // 主键id

	@NotEmpty
	@Size(max = 40)
	private String category; // 分类

	@NotEmpty
	@Size(max = 20)
	private String categoryName; // 分类名称

	@Size(max = 20)
	private String code; // 字典代码

	@Size(max = 20)
	private String name; // 字典名称

	@NotNull
	@Digits(integer = 6, fraction = 0)
	private Integer displayOrder; // 排序

	public SysDic() {
	}

	/**
	 * 
	 * what: 将sysDic转换为sysDicVo. <br/>
	 * when: 构建TreeTable数据，用于index页面的显示.<br/>
	 * 
	 * @param sysDicVO
	 *
	 * @author mose created on 2017年11月3日
	 */
	public void toSysDicVO(SysDicVO sysDicVO) {
		sysDicVO.setId(this.id);
		if (StringUtil.isNullOrEmpty(code)) {
			sysDicVO.setCode(this.category);
			sysDicVO.setName(this.categoryName);
			return;
		}

		sysDicVO.setCode(this.code);
		sysDicVO.setName(this.name);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public String toString() {
		return "SysDic [id=" + id + ", category=" + category + ", categoryName=" + categoryName + ", code=" + code
				+ ", name=" + name + ", displayOrder=" + displayOrder + "]";
	}

}
