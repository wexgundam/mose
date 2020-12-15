/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.dao;

import mose.core.dao.BaseDao;
import mose.tdms.modal.Line;
import mose.tdms.vo.LineSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    节点间数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class LineDao extends BaseDao<Line, LineSearchVo> {
    /**
     * what:    根据查询条件查询一组节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Line> getMany(LineSearchVo lineSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" ID,");
        sql.append(" station_a_id,");
        sql.append(" (select NAME from TDMS_STATION where id=STATION_A_ID) as STATION_A_Name,");
        sql.append(" (select LATITUDE from TDMS_STATION where id=STATION_A_ID) as STATION_A_LATITUDE,");
        sql.append(" (select LONGITUDE from TDMS_STATION where id=STATION_A_ID) as STATION_A_LONGITUDE,");
        sql.append(" station_b_id,");
        sql.append(" (select NAME from TDMS_STATION where id=STATION_B_ID) as STATION_B_Name,");
        sql.append(" (select LATITUDE from TDMS_STATION where id=STATION_B_ID) as STATION_B_LATITUDE,");
        sql.append(" (select LONGITUDE from TDMS_STATION where id=STATION_B_ID) as STATION_B_LONGITUDE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT,");
        sql.append(" LAST_VERIFIER_ID,");
        sql.append(" LAST_VERIFIER_REAL_NAME,");
        sql.append(" LAST_EDITED_AT,");
        sql.append(" LAST_VERIFIER_ID,");
        sql.append(" LAST_VERIFIER_REAL_NAME,");
        sql.append(" LAST_VERIFIED_AT");
        sql.append(" from");
        sql.append(" TDMS_LINE");
        sql.append(" where 1=1");

        sql.append(createSearchSql(lineSearchVo));
        return list(sql.substring(0), lineSearchVo);
    }

    /**
     * what:    根据查询条件查询一个节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Line getOne(LineSearchVo lineSearchVo) {
        List<Line> many = getMany(lineSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Line> getAll() {
        LineSearchVo lineSearchVo = new LineSearchVo();
        return getMany(lineSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(LineSearchVo lineSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" count(ID)");
        sql.append(" from");
        sql.append(" TDMS_LINE");
        sql.append(" where 1=1");

        sql.append(createSearchSql(lineSearchVo));
        return count(sql.substring(0), lineSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(LineSearchVo lineSearchVo) {
        String sql = "";
        if (lineSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (lineSearchVo.getStationIdEqual() != null) {
            sql += " and (STATION_A_ID=:stationIdEqual or STATION_B_ID=:stationIdEqual)";
        }
        Integer[] stationsIdEqual = lineSearchVo.getStationsIdEqual();
        if (stationsIdEqual != null && stationsIdEqual.length > 1) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(" and (");
            buffer.append(" (");
            buffer.append(" STATION_A_ID=");
            buffer.append(stationsIdEqual[0]);
            buffer.append(" and ");
            buffer.append(" STATION_B_ID= ");
            buffer.append(stationsIdEqual[1]);
            buffer.append(" )");
            buffer.append(" or ");
            buffer.append(" (");
            buffer.append(" STATION_A_ID=");
            buffer.append(stationsIdEqual[1]);
            buffer.append(" and ");
            buffer.append(" STATION_B_ID= ");
            buffer.append(stationsIdEqual[0]);
            buffer.append(" )");
            buffer.append(" )");

            sql += buffer.toString();
        }
        //已审核
        if (lineSearchVo.getVerified() != null && lineSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is not null and (LAST_VERIFIED_AT >= LAST_EDITED_AT or LAST_EDITED_AT is null))";
        }
        //未审核
        if (lineSearchVo.getVerified() != null && !lineSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is null or LAST_VERIFIED_AT < LAST_EDITED_AT)";
        }

        return sql;
    }

    /**
     * what:    新增节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(Line line) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into");
        sql.append(" TDMS_LINE");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" STATION_A_ID,");
        sql.append(" STATION_B_ID,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" ) values (");
        sql.append(" seq_tdms_line.nextval,");
        sql.append(" :stationAId,");
        sql.append(" :stationBId,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(" )");

        return insertForId(sql.substring(0), line, "id");
    }

    /**
     * what:    更新节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(Line line) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_LINE");
        sql.append(" set");
        sql.append(" STATION_A_ID=:stationAId,");
        sql.append(" STATION_B_ID=:stationBId,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId,");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName,");
        sql.append(" LAST_EDITED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), line);
    }

    /**
     * what:    核对. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void verifyOne(Line line) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_LINE");
        sql.append(" set");
        sql.append(" LAST_VERIFIER_ID=:lastVerifierId,");
        sql.append(" LAST_VERIFIER_REAL_NAME=:lastVerifierRealName,");
        sql.append(" LAST_VERIFIED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), line);
    }

    /**
     * what:    删除节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(Line line) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete");
        sql.append(" TDMS_LINE");
        sql.append(" where ID=:id");

        delete(sql.substring(0), line.getId());
    }
}
