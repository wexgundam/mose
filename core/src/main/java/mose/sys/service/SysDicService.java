/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:cdpf_core
 */
package mose.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mose.core.model.ComboboxVO;
import mose.core.string.StringUtil;
import mose.sys.dao.SysDicDao;
import mose.sys.model.SysDic;
import mose.sys.vo.SysDicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * what: 字典管理 Service. <br/>
 * 
 *
 * @author mose created on 2017年11月1日
 */
@Service
public class SysDicService {

	/**
	 * sysDicDao
	 */
	@Autowired
	private SysDicDao sysDicDao;

	/**
	 * 
	 * what: 新增字典或类别信息. <br/>
	 * 
	 * @param sysDic 要添加的字典
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int add(SysDic sysDic) {
		return sysDicDao.add(sysDic);
	}

	/**
	 * 
	 * what: 批量添加字典或类别信息. <br/>
	 * 
	 * @param sysDics sysDics
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int batchAdd(List<SysDic> sysDics) {
		int result = 0;
		if (sysDics != null && !sysDics.isEmpty()) {
			for (SysDic sysDic : sysDics) {
				result += sysDicDao.add(sysDic);
			}

		}

		if (result != sysDics.size()) {
			return 0;
		}

		return 1;
	}

	/**
	 * 
	 * what: 字典维护,其中包括新增，修改，删除. <br/>
	 * when: 新增，修改，删除.<br/>
	 * warning: 只要其中的一项操作不成功就回滚.<br/>
	 * 
	 * @param sysDicList sysDicList
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int maintenanceSysDic(List<List<SysDic>> sysDicList) {
		int addResult = 0;
		int updateResult = 0;
		int delResult = 0;
		if (sysDicList != null && !sysDicList.isEmpty()) {
			addResult = batchAdd(sysDicList.get(0));
			updateResult = batchUpdate(sysDicList.get(1));
			delResult = batchDelte(sysDicList.get(2));
		}

		if (addResult != 1 || updateResult != 1 || delResult != 1) {
			return 0;
		}

		return 1;

	}

	/**
	 * 
	 * what: 批量更新. <br/>
	 * when: 字典维护时使用.<br/>
	 * 
	 * @param updateList 更新的字典明细列表
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int batchUpdate(List<SysDic> updateList) {
		int result = 0;
		if (updateList != null && !updateList.isEmpty()) {
			for (SysDic sysDic : updateList) {
				result += sysDicDao.update(sysDic);
			}
		}

		if (result != updateList.size()) {
			return 0;
		}

		return 1;
	}

	/**
	 * 
	 * what: 更新字典信息 . <br/>
	 * 
	 * @param sysDic sysDic
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int update(SysDic sysDic) {
		return sysDicDao.update(sysDic);
	}

	/**
	 * 
	 * what: 删除字典类别. <br/>
	 * 
	 * @param category 字典类别
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int deleteCategory(String category) {
		return sysDicDao.deleteCategory(category);
	}

	/**
	 * 
	 * what: 根据id删除字典信息. <br/>
	 * 
	 * @param id 字典id
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int delete(int id) {
		return sysDicDao.delete(id);
	}

	/**
	 * 
	 * what: 批量删除字典信息. <br/>
	 * 
	 * @param delList 删除的字典明细集合
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int batchDelte(List<SysDic> delList) {
		int result = 0;
		if (delList != null && !delList.isEmpty()) {
			for (SysDic sysDic : delList) {
				result += sysDicDao.delete(sysDic.getId());
			}
		}

		if (result != delList.size()) {
			return 0;
		}

		return 1;
	}

	/**
	 * 
	 * what: 所有字典信息列表. <br/>
	 * 
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public List<SysDicVO> listAll() {
		List<SysDic> list = sysDicDao.listAll();
		return createTree(list);
	}

	/**
	 * 
	 * what: 构建TreeTable数据. <br/>
	 * when: (这里描述这个方法适用时机 – 可选).<br/>
	 * how: (这里描述这个方法的执行流程或使用方法 – 可选).<br/>
	 * warning: (这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @param sysDicList sysDicList
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public List<SysDicVO> createTree(List<SysDic> sysDicList) {
		SysDicVO sysDicVO = null;

		if (sysDicList == null || sysDicList.isEmpty()) {
			return null;
		}

		// 摘出类别信息
		List<SysDic> categoryList = sysDicList.stream().filter((sysDic) -> {
			return StringUtil.isNullOrEmpty(sysDic.getCode());
		}).collect(Collectors.toList());

		List<SysDicVO> list = new ArrayList<>();
		for (SysDic category : categoryList) {
			sysDicVO = new SysDicVO();
			category.toSysDicVO(sysDicVO);
			sysDicVO.setPid(0);
			list.add(sysDicVO);
			for (SysDic sysDic : sysDicList) {
				if (category.getCategory().equals(sysDic.getCategory()) && sysDic.getCode() != null) {
					sysDicVO = new SysDicVO();
					sysDic.toSysDicVO(sysDicVO);
					sysDicVO.setPid(category.getId());
					list.add(sysDicVO);
				}

			}

		}

		return list;

	}

	/**
	 * 
	 * what: 根据id查询字典信息. <br/>
	 * 
	 * @param id 字典id
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public SysDic get(int id) {
		return sysDicDao.get(id);
	}

	/**
	 * 
	 * what: 根据类别获取字典信息. <br/>
	 * 
	 * @param category 字典类别
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public List<SysDic> getByCategory(String category) {
		return sysDicDao.getByCategory(category);
	}

	/**
	 * 
	 * what: 根据类别获取字典信息包含字典类别本身. <br/>
	 * 
	 * @param category 字典类别
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public List<SysDic> getAllCategory(String category) {
		return sysDicDao.getAllCategory(category);
	}

	/**
	 * 
	 * what: 根据类别名称获取字典信息. <br/>
	 * 
	 * @param categoryName 字典类别名称
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public List<SysDic> getByCategoryName(String categoryName) {
		return sysDicDao.getByCategoryName(categoryName);
	}

	/**
	 * 
	 * what: 添加时判断类别,分类名称,字典代码,字典名称是否存在,存在返回true. <br/>
	 * when: 添加字典信息时使用.<br/>
	 * 
	 * @param param 分别为category,categoryName,code,name
	 * @param prev 之前的值
	 * @param value 修改后的值
	 * @param category 字典类别
	 * @return 0表示与修改前的相同,1表示数据库中不存在,2表示数据库中存在
	 *
	 * @author mose created on 2017年11月1日
	 */
	public int checkCategoryExist(String param, String prev, String value, String category) {
		List<SysDic> sysDicList = null;

		// 如果param或value为空，直接退出
		if (StringUtil.isNullOrEmpty(param) || StringUtil.isNullOrEmpty(value)) {
			return 1;
		}

		// 判断与修改前的是否相同
		if (StringUtil.isNotNullOrEmpty(prev)) {
			if (prev.equals(value)) {
				return 0;
			}
		}

		// 在数据库中查找是否存在
		if (param.equals("category")) {
			sysDicList = sysDicDao.getByCategory(value);
		} else if (param.equals("categoryName")) {
			sysDicList = sysDicDao.getByCategoryName(value);
		} else if (param.equals("code")) {
			if (StringUtil.isNotNullOrEmpty(category)) {
				sysDicList = sysDicDao.getByCodeAndCategory(category, value);
			}
		} else if (param.equals("name")) {
			sysDicList = sysDicDao.getByName(value);
		}

		if (sysDicList != null && !sysDicList.isEmpty()) {
			return 2;
		}

		return 1;
	}

	/**
	 * 
	 * what: 字典下拉框. <br/>
	 * 
	 * @return 返回结果集
	 *
	 * @author mose created on 2017年11月1日
	 */
	public List<ComboboxVO> listType(String type) {
		return sysDicDao.listType(type);
	}

	/**
	 * 
	 * what: 检查类别下是否存在字典. <br/>
	 * 
	 * @param category 字典类别
	 * @return true 表示存在，false表示不存在
	 *
	 * @author mose created on 2017年11月1日
	 */
	public boolean checkCateoryExistDic(String category) {
		List<SysDic> sysDicList = sysDicDao.getByCategory(category);
		if (sysDicList != null && !sysDicList.isEmpty()) {
			return true;
		}

		return false;
	}

}
