/**
 * Copyright 2017 弘远技术研发中心. All rights reserved Project
 * Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.dao;

import mose.core.dao.BaseDao;
import mose.core.page.PageUtil;
import mose.core.string.StringUtil;
import mose.sys.model.SysLog;
import mose.sys.vo.SysLogSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * what: 系统日志dao
 *
 *
 * @author mose created on 2017年6月13日
 */
@Repository
public class SysLogDao extends BaseDao<SysLog, SysLogSearchVO> {

    public int add(SysLog sysLog) {
        String sql = "insert into t_sys_log(id,user_id,opera_date,opera_ip,module_name,opera_name,opera_url,opera_params)"
                + " values(seq_t_sys_log.nextval,:userId,sysdate,:operaIp,:moduleName,:operaName,:operaUrl,:operaParams)";
        return insert(sql, sysLog);
    }

    /**
     *
     * what: 分页查询日志列表
     *
     * @param sysLogSearchVO 日志搜索vo
     * @return list
     *
     * @author 杨超凡 created on 2017年11月10日
     */
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        String sql = "select t.*,u.username username,u.real_name realName  from t_sys_log t,t_sys_user u where t.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        sql = PageUtil.createOraclePageSQL(sql, sysLogSearchVO.getPageIndex());
        return list(sql, sysLogSearchVO);
    }

    /**
     *
     * what: 不分页查询日志列表
     *
     * @param sysLogSearchVO 日志搜索vo
     * @return list
     *
     * @author 杨超凡 created on 2017年11月10日
     */
    public List<SysLog> listAll(SysLogSearchVO sysLogSearchVO) {
        String sql = "select t.id,t.user_id,t.opera_date,t.operea_ip,t.module_name,t.opera_date,t.opera_url"
                + ",u.code user_code,u.real_name realName  from t_sys_log t,t_sys_user u where t.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        List<SysLog> list = list(sql, sysLogSearchVO);
        return list;
    }

    /**
     *
     * what: 查询数据条数
     *
     * @param sysLogSearchVO 日志搜索vo
     * @return
     *
     * @author 杨超凡 created on 2017年11月10日
     */
    public int count(SysLogSearchVO sysLogSearchVO) {
        String sql = "select count(*) from t_sys_log t,t_sys_user u where t.user_id=u.id ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        return count(sql, sysLogSearchVO);
    }

    /**
     *
     * what: 拼接查询条件
     *
     * @param sysLogSearchVO 日志搜索vo
     * @return sql
     *
     * @author 杨超凡 created on 2017年11月10日
     */
    private String createSearchSql(SysLogSearchVO sysLogSearchVO) {
        String sql = "";
        if (sysLogSearchVO.getUserId() != null) {
            sql += " and user_id=:userId";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getUsername())) {
            sql += " and u.username like :searchUsername";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getStartDate())) {
            sql += " and to_char(t.opera_date,'yyyy-mm-dd')>=:startDate";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getEndDate())) {
            sql += " and to_char(t.opera_date,'yyyy-mm-dd')<=:endDate";
        }
        return sql;
    }

    /**
     *
     * what: 根据id查找一条日志
     *
     * @param id 日志id
     * @return syslog
     *
     * @author 杨超凡 created on 2017年11月10日
     */
    public SysLog getById(String id) {
        String sql = "select * from t_sys_log where id=? ";
        return get(sql, id);
    }
}
