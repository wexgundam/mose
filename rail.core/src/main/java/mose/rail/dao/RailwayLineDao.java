/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.rail.modal.RailwayLine;
import mose.rail.vo.RailwayLineSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    铁路线数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class RailwayLineDao extends BaseDao<RailwayLine, RailwayLineSearchVo> {
    /**
     * what:    根据查询条件查询一组铁路线. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<RailwayLine> getMany(RailwayLineSearchVo railwayLineSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" ID,");
        sql.append(" NAME,");
        sql.append(" CODE,");
        sql.append(" ALIAS,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" from ");
        sql.append("T_RAILWAY_LINE");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(railwayLineSearchVo));
        return list(sql.substring(0), railwayLineSearchVo);
    }

    /**
     * what:    根据查询条件查询一个铁路线. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public RailwayLine getOne(RailwayLineSearchVo railwayLineSearchVo) {
        List<RailwayLine> many = getMany(railwayLineSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路铁路线. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<RailwayLine> getAll() {
        RailwayLineSearchVo railwayLineSearchVo = new RailwayLineSearchVo();
        return getMany(railwayLineSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(RailwayLineSearchVo railwayLineSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" count(ID) ");
        sql.append(" from ");
        sql.append("T_RAILWAY_LINE");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(railwayLineSearchVo));
        return count(sql.substring(0), railwayLineSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(RailwayLineSearchVo railwayLineSearchVo) {
        String sql = "";
        if (railwayLineSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (!StringUtil.isNullOrEmpty(railwayLineSearchVo.getNameEqual())) {
            sql += " and NAME=:nameEqual";
        }
        if (!StringUtil.isNullOrEmpty(railwayLineSearchVo.getNameLike())) {
            sql += " and NAME like :nameLike";
        }

        return sql;
    }

    /**
     * what:    新增铁路线. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(RailwayLine railwayLine) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        sql.append("T_RAILWAY_LINE");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" NAME,");
        sql.append(" CODE,");
        sql.append(" ALIAS,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(") values (");
        sql.append(" seq_t_railway_Line.nextval,");
        sql.append(" :name,");
        sql.append(" :code,");
        sql.append(" :alias,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(")");

        return insertForId(sql.substring(0), railwayLine, "id");
    }

    /**
     * what:    更新铁路线. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(RailwayLine railwayLine) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ");
        sql.append("T_RAILWAY_LINE");
        sql.append(" set ");
        sql.append(" NAME=:name, ");
        sql.append(" CODE=:code, ");
        sql.append(" ALIAS=:alias,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId, ");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName, ");
        sql.append(" LAST_EDITED_AT=sysdate ");
        sql.append(" where ID=:id");

        update(sql.substring(0), railwayLine);
    }

    /**
     * what:    删除铁路线. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(RailwayLine railwayLine) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete ");
        sql.append("T_RAILWAY_LINE");
        sql.append(" where ID=:id");

        delete(sql.substring(0), railwayLine.getId());
    }
}
