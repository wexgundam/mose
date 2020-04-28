/**
 * Copyright 2017 弘远技术研发中心. All rights reserved Project 
 * Name:cdpf_v1 
 * Module Name:core
 * 
 */
package mose.sys.dao;


import mose.core.dao.BaseDao;
import mose.core.page.PageUtil;
import mose.core.string.StringUtil;
import mose.sys.model.SysUserLogin;
import mose.sys.vo.SysUserLoginSearchVO;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 
 * what:    用户登录dao
 * 
 *
 * @author mose created on 2017年6月13日
 */
@Repository
public class SysUserLoginDao extends BaseDao<SysUserLogin, SysUserLoginSearchVO> {


   /**
    * 
    * what: 插入用户登录信息
    * 
    * @param sysUserLogin 用户登录model
    *
    * @author mose created on 2017年6月13日
    */
    public void add(SysUserLogin sysUserLogin) {
        String sql = "insert into t_sys_user_login (id,user_id,login_date,login_ip,terminal,explorerType,explorerVersion)";
        sql += " values(seq_t_sys_user_login .nextval,:userId,:loginDate,:loginIp,:terminal,:explorerType,:explorerVersion)";
        insert(sql, sysUserLogin);
    }

    /**
     * 
     * what:    取得最后登录信息
     * 
     * @param userId 用户id
     * @return SysUserLogin 用户登录model
     *
     * @author mose created on 2017年11月10日
     */
    public SysUserLogin getLastLogin(int userId) {
        String sql = "select t.id,t.user_id,t.login_date,t.login_ip,t.terminal,t.explorertype,t.explorerversion from t_sys_user_login  t where user_id=? and rownum<2 order by login_date desc";
        return get(sql, userId);
    }
/**
 * 
 * what: 分页查询用户登录列表
 * 
 * @param sysUserloginSearchVO 用户登录VO
 * @return list
 *
 * @author mose created on 2017年11月10日
 */
    public List<SysUserLogin> list(SysUserLoginSearchVO sysUserloginSearchVO) {
        String sql = "select t.*,u.username username,u.real_name realName  from t_sys_user_login t,t_sys_user u where t.user_id=u.id  ";
        sql += createSearchSql(sysUserloginSearchVO);
        sql += " order by login_date desc";
		sql = PageUtil.createOraclePageSQL(sql, sysUserloginSearchVO.getPageIndex());
		return list(sql, sysUserloginSearchVO);
    }

   /**
    * 
    * what: 查询用户登录记录条数
    * 
    * @param sysUserLoginSearchVO 用户登录VO
    * @return int
    *
    * @author mose created on 2017年11月10日
    */
    public int count(SysUserLoginSearchVO sysUserLoginSearchVO) {
        String sql = "select count(*) from t_sys_user_login t,t_sys_user u where t.user_id=u.id ";
        sql += createSearchSql(sysUserLoginSearchVO);
		return count(sql, sysUserLoginSearchVO);
    }
    /**
     * 
     * what: 拼接查询条件
     * 
     * @param sysLoginLogSearchVO 用户登录VO
     * @return sql
     *
     * @author mose created on 2017年6月13日
     */
	private String createSearchSql(SysUserLoginSearchVO sysLoginLogSearchVO) {
		String sql = "";
		if (StringUtil.isNotNullOrEmpty(sysLoginLogSearchVO.getLoginIp())) {
			sql += " and login_ip like :searchIP";
		}
		if (StringUtil.isNotNullOrEmpty(sysLoginLogSearchVO.getUsername())) {
			sql += " and username like :searchusername";
		}
		if (StringUtil.isNotNullOrEmpty(sysLoginLogSearchVO.getStartDate())) {
			sql += " and to_char(login_date,'yyyy-mm-dd')>=:startDate";
		}
		if (StringUtil.isNotNullOrEmpty(sysLoginLogSearchVO.getEndDate())) {
			sql += " and to_char(login_date,'yyyy-mm-dd')<=:endDate";
		}
		if (sysLoginLogSearchVO.getUserId() != null) {
			sql += " and user_id = :userId";
		}
		return sql;
	}
}
