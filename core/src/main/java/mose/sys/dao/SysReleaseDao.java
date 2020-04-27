/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:cdpf_core
 */
package mose.sys.dao;

import java.util.List;

import mose.core.dao.BaseDao;
import mose.core.page.PageUtil;
import mose.core.string.StringUtil;
import mose.sys.model.SysRelease;
import mose.sys.vo.SysReleaseSearchVO;
import org.springframework.stereotype.Repository;

/**
 * 
 * what: 系统升级信息 Dao. <br/>
 * 
 *
 * @author mose created on 2017年10月31日
 */
@Repository
public class SysReleaseDao extends BaseDao<SysRelease, SysReleaseSearchVO> {

	/**
	 * 
	 * what: 添加系统升级信息. <br/>
	 * 
	 * @param sysRelease 添加的sysRelase 对象
	 * @return 返回结果，0表示失败，1表示成功
	 *
	 * @author mose created on 2017年10月31日
	 */
	public int add(SysRelease sysRelease) {
		String sql = "insert into t_sys_release(id,name,version,release_date,release_content,release_by)"
				+ " values(seq_t_sys_release.nextval,:name,:version,:releaseDate,:releaseContent,:releaseBy)";
		return insert(sql, sysRelease);
	}

	/**
	 * 
	 * what: 查询系统说明总数. <br/>
	 * 
	 * @param sysReleaseSearchVO sysRelaseSearchVO
	 * @return 返回结果
	 *
	 * @author mose created on 2017年10月31日
	 */
	public int count(SysReleaseSearchVO sysReleaseSearchVO) {
		String sql = "select count(*) from t_sys_release where 1=1 ";
		sql += createSearchSql(sysReleaseSearchVO);
		return count(sql, sysReleaseSearchVO);
	}

	/**
	 * 
	 * what: 查询系统升级信息. <br/>
	 * 
	 * @param sysReleaseSearchVO sysReleaseSearchVO
	 * @return 返回结果
	 *
	 * @author mose created on 2017年10月31日
	 */
	public List<SysRelease> list(SysReleaseSearchVO sysReleaseSearchVO) {
		String sql = "select t.id,t.name,t.version,t.release_date,t.release_content,t.release_by from t_sys_release t  where 1=1";
		sql += createSearchSql(sysReleaseSearchVO);
		sql += " order by id asc";
		sql = PageUtil.createOraclePageSQL(sql, sysReleaseSearchVO.getPageIndex());
		return list(sql, sysReleaseSearchVO);
	}

	/**
	 * 
	 * what: 查询时使用拼接url. <br/>
	 * when: 点击查询时使用.<br/>
	 * 
	 * @param sysReleaseSearchVO sysReleaseSearchVO
	 * @return 返回拼接的查询sql
	 *
	 * @author mose created on 2017年10月31日
	 */
	private String createSearchSql(SysReleaseSearchVO sysReleaseSearchVO) {
		String sql = "";
		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getName())) {
			sql += " and name like:nameStr";
		}
		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getVersion())) {
			sql += " and version like :versionStr";
		}
		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getReleaseBy())) {
			sql += " and release_by like :releaseByStr";
		}
		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getReleaseDate())) {
			sql += " and release_date like :releaseDateStr";
		}
		return sql;
	}

	/**
	 * 
	 * what: 修改系统说明信息. <br/>
	 * when: 修改页面点击保存.<br/>
	 * 
	 * @param sysRelease sysRelease
	 * @return 返回结果，0表示失败，1表示成功
	 *
	 * @author mose created on 2017年10月31日
	 */
	public int update(SysRelease sysRelease) {
		String sql = "update t_sys_release set name=:name,version=:version,release_date=:releaseDate,release_content=:releaseContent,release_by=:releaseBy where id=:id";
		return update(sql, sysRelease);
	}

	/**
	 * 
	 * what: 修改系统说明信息. <br/>
	 * when: 点击删除.<br/>
	 * 
	 * @param id 删除的id
	 * @return 返回结果，0表示失败，1表示成功
	 *
	 * @author mose created on 2017年10月31日
	 */
	public int delete(int id) {
		String sql = "delete from t_sys_release where id=? ";
		return delete(sql, id);
	}

	/**
	 * 
	 * what: 根据id获取系统说明信息. <br/>
	 * when: 修改系统升级信息.<br/>
	 * 
	 * @param id 系统升级说明的id
	 * @return 返回结果
	 *
	 * @author mose created on 2017年10月31日
	 */
	public SysRelease get(int id) {
		String sql = "select t.id,t.name,t.version,t.release_date,t.release_content,t.release_by from t_sys_release t where id=? ";
		return get(sql, id);
	}

	/**
	 * 
	 * what: 根据系统名称和版本号查询. <br/>
	 * when: 添加时使用.<br/>
	 * 
	 * @param name 系统名称
	 * @param version 系统版本号
	 * @return 返回结果
	 *
	 * @author mose created on 2017年11月16日
	 */
	public List<SysRelease> getByNameAndVesion(String name, String version) {
		String sql = "select t.id,t.name,t.version,t.release_date,t.release_content,t.release_by from t_sys_release t where name=? and version=? ";
		return list(sql, name, version);
	}

}
