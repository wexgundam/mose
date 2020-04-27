package mose.core.page;


import mose.core.global.GlobalConst;

/**
 * 分页工具类，用于生成分页语句
 *
 * @author mose
 * @date 2017-05-23
 */
public class PageUtil {

    /**
     * 生成mysql分页查询语句
     *
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String createMysqlPageSql(String sql, int pageIndex, int pageSize) {
        return sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
    }

    /**
     * 生成mysql分页查询语句
     *
     * @param sql
     * @param pageIndex
     * @return
     */
    public static String createMysqlPageSql(String sql, int pageIndex) {
        return sql += " limit " + (pageIndex - 1) * GlobalConst.PAGESIZE + "," + GlobalConst.PAGESIZE;
    }
    /**
     * 生成oracle分页查询语句
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String createOraclePageSQL(String sql, int pageIndex, int pageSize) {
        return "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <=" + pageIndex * pageSize
                + " ) WHERE RN > " + (pageIndex - 1) * pageSize;
    }
    /**
     * 生成oracle分页查询语句,默认页数
     * @param sql
     * @param pageIndex
     * @return
     */
    public static String createOraclePageSQL(String sql, int pageIndex) {
        return "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <=" + pageIndex * GlobalConst.PAGESIZE
                + " ) WHERE RN > " + (pageIndex - 1) * GlobalConst.PAGESIZE;
    }
}
