/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.OldIntervalLine;
import mose.rail.vo.IntervalLineSearchVo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    区间线路数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class IntervalLineDao extends BaseDao<IntervalLine, IntervalLineSearchVo> {
    /**
     * what:    根据查询条件查询一组区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<OldIntervalLine> getOldIntervalLines(String railwayLineName) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" r.station_name as r_s_name,trim(r.railwayline_direction) as r_d,r.station_center_mileage as r_sck,");
        sql.append(" s.id as NODE_ID, s.epsg4326 as NODE_EPSG4326, s.name as NODE_NAME");
        sql.append(" from aa_lkj r ");
        sql.append(" left outer join t_node s on ( s.bureau_id= r.bureau_code and r.station_name in ");
        sql.append(" (select regexp_substr(s.alias,'[^,]+',1,level) ");
        sql.append(" from dual ");
        sql.append(" CONNECT BY  ");
        sql.append(" LEVEL <= LENGTH(s.alias) - LENGTH(REGEXP_REPLACE(s.alias, ',', '')) + 1))  ");
        sql.append(" where trim(r.railwayline_name)=?");
        sql.append(" order by r.station_center_mileage");

        List<OldIntervalLine> list = jdbcTemplate.query(sql.substring(0), new Object[]{railwayLineName}, BeanPropertyRowMapper.newInstance(OldIntervalLine.class));
        return list;
    }

    /**
     * what:    根据查询条件查询一组区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<IntervalLine> getMany(IntervalLineSearchVo intervalLineSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" ID,");
        sql.append(" RAILWAY_LINE_ID,");
        sql.append(" (select BUREAU_ID from T_NODE where ID=NODE_A_ID) as NODE_A_BUREAU_ID,");
        sql.append(" (select SHORT_NAME from T_BUREAU where ID=(select BUREAU_ID from T_NODE where ID=NODE_A_ID)) as NODE_A_BUREAU_SHORT_NAME,");
        sql.append(" NODE_A_ID,");
        sql.append(" NODE_A_MILEAGE,");
        sql.append(" (select BUREAU_ID from T_NODE where ID=NODE_B_ID) as NODE_B_BUREAU_ID,");
        sql.append(" (select SHORT_NAME from T_BUREAU where ID=(select BUREAU_ID from T_NODE where ID=NODE_B_ID)) as NODE_B_BUREAU_SHORT_NAME,");
        sql.append(" NODE_B_ID,");
        sql.append(" NODE_B_MILEAGE,");
        sql.append(" DIRECTION,");
        sql.append(" ALIAS,");
        sql.append(" GEO_SPATIAL,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" from ");
        sql.append("T_INTERVAL_LINE");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(intervalLineSearchVo));
        return list(sql.substring(0), intervalLineSearchVo);
    }

    /**
     * what:    根据查询条件查询一个区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public IntervalLine getOne(IntervalLineSearchVo intervalLineSearchVo) {
        List<IntervalLine> many = getMany(intervalLineSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<IntervalLine> getAll() {
        IntervalLineSearchVo intervalLineSearchVo = new IntervalLineSearchVo();
        return getMany(intervalLineSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(IntervalLineSearchVo intervalLineSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" count(ID) ");
        sql.append(" from ");
        sql.append("T_INTERVAL_LINE");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(intervalLineSearchVo));
        return count(sql.substring(0), intervalLineSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(IntervalLineSearchVo intervalLineSearchVo) {
        String sql = "";
        if (intervalLineSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (intervalLineSearchVo.getRailwayLineIdEqual() != null) {
            sql += " and RAILWAY_LINE_ID=:railwayLineIdEqual";
        }
        if (intervalLineSearchVo.getDirectionEqual() != null) {
            /**
             * “上”：双上、上、单
             * “下”：双下、下、单
             * “双”：双上、双下
             */
            String directionEqual = intervalLineSearchVo.getDirectionEqual();
            if ("上".equals(directionEqual)) {
                sql += " and (DIRECTION='双上' or DIRECTION='上' or DIRECTION='单')";
            } else if ("下".equals(directionEqual)) {
                sql += " and (DIRECTION='双下' or DIRECTION='下' or DIRECTION='单')";
            } else if ("双".equals(directionEqual)) {
                sql += " and (DIRECTION='双上' or DIRECTION='双下')";
            } else {
                sql += " and DIRECTION=:directionEqual";
            }
        }
        if (intervalLineSearchVo.getNodeIdEqual() != null) {
            sql += " and (NODE_A_ID=:nodeIdEqual or NODE_B_ID=:nodeIdEqual)";
        }
        if (intervalLineSearchVo.getNodesIdEqual() != null && intervalLineSearchVo.getNodesIdEqual().length > 1) {
            sql += " and ((NODE_A_ID=:nodeAIdEqual and NODE_B_ID=:nodeBIdEqual) or (NODE_A_ID=:nodeBIdEqual and NODE_B_ID=:nodeAIdEqual))";
        }
        sql += " order by RAILWAY_LINE_ID, NODE_A_MILEAGE, DIRECTION, NODE_A_ID";

        return sql;
    }

    /**
     * what:    新增区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(IntervalLine intervalLine) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        sql.append("T_INTERVAL_LINE");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" RAILWAY_LINE_ID,");
        sql.append(" NODE_A_ID,");
        sql.append(" NODE_A_MILEAGE,");
        sql.append(" NODE_B_ID,");
        sql.append(" NODE_B_MILEAGE,");
        sql.append(" DIRECTION,");
        sql.append(" ALIAS,");
        sql.append(" GEO_SPATIAL,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(") values (");
        sql.append(" seq_t_interval_Line.nextval,");
        sql.append(" :railwayLineId,");
        sql.append(" :nodeAId,");
        sql.append(" :nodeAMileage,");
        sql.append(" :nodeBId,");
        sql.append(" :nodeBMileage,");
        sql.append(" :direction,");
        sql.append(" :alias,");
        sql.append(" :geoSpatial,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(")");

        return insertForId(sql.substring(0), intervalLine, "id");
    }

    /**
     * what:    更新区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(IntervalLine intervalLine) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ");
        sql.append("T_INTERVAL_LINE");
        sql.append(" set ");
        sql.append(" RAILWAY_LINE_ID=:railwayLineId,");
        sql.append(" NODE_A_ID=:nodeAId,");
        sql.append(" NODE_A_MILEAGE=:nodeAMileage,");
        sql.append(" NODE_B_ID=:nodeBId,");
        sql.append(" NODE_B_MILEAGE=:nodeBMileage,");
        sql.append(" DIRECTION=:direction,");
        sql.append(" ALIAS=:alias,");
        sql.append(" GEO_SPATIAL=:geoSpatial,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId, ");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName, ");
        sql.append(" LAST_EDITED_AT=sysdate ");
        sql.append(" where ID=:id");

        update(sql.substring(0), intervalLine);
    }

    /**
     * what:    删除区间线路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(IntervalLine intervalLine) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete ");
        sql.append("T_INTERVAL_LINE");
        sql.append(" where ID=:id");

        delete(sql.substring(0), intervalLine.getId());
    }
}
