package mose.sys.service;

import java.util.ArrayList;
import java.util.List;

import mose.core.string.StringUtil;
import mose.sys.dao.SysDepartmentDao;
import mose.sys.model.SysDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * what: 部门Service
 * 
 *
 * @author mose created on 2017年10月30日
 */
@Service
public class SysDepartmentService {
	/**
	 * 部门Dao
	 */
	@Autowired
	private SysDepartmentDao sysDepartmentDao;

	/**
	 * 
	 * what: 新增
	 * 
	 * @param sysDepartment sysDepartment
	 * @return flag 0、失败，1、成功，2、部门名称已经存在
	 *
	 * @author mose created on 2017年10月30日
	 */
	public int add(SysDepartment sysDepartment) {
		int flag = 0;
		int count = sysDepartmentDao.getNameNum(sysDepartment);
		if (count > 0) {
			flag = 2;
		}
		else {
			sysDepartmentDao.add(sysDepartment);
			flag = 1;
		}
		return flag;
	}

	/**
	 * 
	 * what: 修改
	 * 
	 * @param sysDepartment sysDepartment
	 * @return flag 0、失败，1、成功，2、部门名称已经存在
	 *
	 * @author mose created on 2017年10月30日
	 */
	public int update(SysDepartment sysDepartment) {
		int flag = 0;
		int count = sysDepartmentDao.getNameNum(sysDepartment);
		if (count > 0) {
			flag = 2;
		}	
		else {
			sysDepartmentDao.update(sysDepartment);
			flag = 1;
		}
		return flag;
	}

	/**
	 * 
	 * what: 删除部门，先判断是否有下级部门，有的话提示不允许删除
	 * 
	 * @param id 部门id
	 * @return 删除结果 2、还有下级部门，1、删除成功
	 *
	 * @author mose created on 2017年10月30日
	 */
	public int delete(int id) {
		if (sysDepartmentDao.getChildCount(id) > 0) {
			return 2;
		}
		else {
			return sysDepartmentDao.delete(id);
		}
	}

	/**
	 * 
	 * what: 根据id获取对象
	 * 
	 * @param id 部门id
	 * @return 根据id查询的部门对象
	 *
	 * @author mose created on 2017年10月30日
	 */
	public SysDepartment get(int id) {
		return sysDepartmentDao.get(id);
	}

	/**
	 * 
	 * what: 部门列表，递归生成，用于显示treeGrid
	 * 
	 * @return 结果集list
	 *
	 * @author mose created on 2017年10月30日
	 */
	public List<SysDepartment> list() {
		// 获取所有部门
		List<SysDepartment> list = sysDepartmentDao.list();
		List<SysDepartment> listRet = new ArrayList<>();
		listRet = createModuleList(list, listRet, 0);
		return listRet;
	}

	/**
	 * 
	 * what: 创建列表
	 * 
	 * @param list 所有部门的list
	 * @param listRet 结果集list
	 * @param parentId parentId
	 * @return 结果集list
	 *
	 * @author mose created on 2017年10月30日
	 */
	private List<SysDepartment> createModuleList(List<SysDepartment> list, List<SysDepartment> listRet, int parentId) {
		for (SysDepartment sysModule : list) {
			if (sysModule.getParentId() == parentId) {
				listRet.add(sysModule);
				if (sysModule.getCnt() > 0) {
					listRet = createModuleList(list, listRet, sysModule.getId());
				}
			}
		}
		return listRet;
	}

	/**
	 * 
	 * what: 获取下级部门总数
	 * 
	 * @param id 部门id
	 * @return 下级部门的数量
	 *
	 * @author mose created on 2017年10月30日
	 */
	public int getChildCount(int id) {
		return sysDepartmentDao.getChildCount(id);
	}

	/**
	 * 
	 * what: 生成Ztree的树节点,新增机构时使用
	 * 
	 * @return Ztree
	 *
	 * @author mose created on 2017年10月30日
	 */
	public String createZtreeByModule() {
		// 机构列表
		List<SysDepartment> listModule = sysDepartmentDao.list();
		StringBuilder sb = new StringBuilder();
		for (SysDepartment sysModule : listModule) {
			sb.append("{id : \"" + sysModule.getId() + "\",pId :\"" + sysModule.getParentId() + "\",name :\""
					+ sysModule.getName() + "\",open : false");
			sb.append("},");
		}
		return StringUtil.subTract(sb.toString());
	}
}
