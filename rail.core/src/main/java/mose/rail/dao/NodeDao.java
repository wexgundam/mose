/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.rail.modal.Bureau;
import mose.rail.modal.Node;
import mose.rail.vo.NodeSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    节点数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class NodeDao extends BaseDao<Node, NodeSearchVo> {
    /**
     * what:    根据查询条件查询一组节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Node> getMany(NodeSearchVo nodeSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" ID,");
        sql.append(" NAME,");
        sql.append(" BUREAU_ID,");
        sql.append(" (select NAME from T_BUREAU where ID=BUREAU_ID) as BureauName,");
        sql.append(" LABELS,");
        sql.append(" PINYIN,");
        sql.append(" INITIAL_PINYIN,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" EPSG4326,");
        sql.append(" RADIUS,");
        sql.append(" ALIAS,");
        sql.append(" GEO_SPATIAL,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" from ");
        sql.append("T_NODE");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(nodeSearchVo));
        return list(sql.substring(0), nodeSearchVo);
    }

    /**
     * what:    根据查询条件查询一个节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Node getOne(NodeSearchVo nodeSearchVo) {
        List<Node> many = getMany(nodeSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Node> getAll() {
        NodeSearchVo nodeSearchVo = new NodeSearchVo();
        return getMany(nodeSearchVo);
    }

    /**
     * what:    获取给定路局管辖的节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Node> getMany(Bureau bureau) {
        NodeSearchVo nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setBureauIdEqual(bureau.getId());
        return getMany(nodeSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(NodeSearchVo nodeSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" count(ID) ");
        sql.append(" from ");
        sql.append("T_NODE");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(nodeSearchVo));
        return count(sql.substring(0), nodeSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(NodeSearchVo nodeSearchVo) {
        String sql = "";
        if (nodeSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (nodeSearchVo.getBureauIdEqual() != null) {
            sql += " and BUREAU_ID=:bureauIdEqual";
        }
        if (nodeSearchVo.getXingDiaoIdEqual() != null) {
            sql += " and Xing_Diao_ID=:xingDiaoIdEqual";
        }
        if (!StringUtil.isNullOrEmpty(nodeSearchVo.getNameEqual())) {
            sql += " and NAME=:nameEqual";
        }
        if (!StringUtil.isNullOrEmpty(nodeSearchVo.getNameLike())) {
            sql += " and NAME like :nameLike";
        }
        if (!StringUtil.isNullOrEmpty(nodeSearchVo.getPinyinLike())) {
            sql += " and (PINYIN like :pinyinLike or INITIAL_PINYIN like :pinyinLike)";
        }
        if (!StringUtil.isNullOrEmpty(nodeSearchVo.getTelegraphCodeEqual())) {
            sql += " and TELEGRAPH_CODE=:telegraphCodeEqual";
        }
        if (!StringUtil.isNullOrEmpty(nodeSearchVo.getTelegraphCodeLike())) {
            sql += " and TELEGRAPH_CODE like :telegraphCodeLike";
        }

        return sql;
    }

    /**
     * what:    新增节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(Node node) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        sql.append("T_NODE");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" NAME,");
        sql.append(" BUREAU_ID,");
        sql.append(" XING_DIAO_ID,");
        sql.append(" LABELS,");
        sql.append(" PINYIN,");
        sql.append(" INITIAL_PINYIN,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" EPSG4326,");
        sql.append(" RADIUS,");
        sql.append(" ALIAS,");
        sql.append(" GEO_SPATIAL,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(") values (");
        sql.append(" seq_t_node.nextval,");
        sql.append(" :name,");
        sql.append(" :bureauId,");
        sql.append(" :xingDiaoId,");
        sql.append(" :labels,");
        sql.append(" :pinyin,");
        sql.append(" :initialPinyin,");
        sql.append(" :telegraphCode,");
        sql.append(" :epsg4326,");
        sql.append(" :radius,");
        sql.append(" :alias,");
        sql.append(" :geoSpatial,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(")");

        return insertForId(sql.substring(0), node, "id");
    }

    /**
     * what:    更新节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(Node node) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ");
        sql.append("T_NODE");
        sql.append(" set ");
        sql.append(" NAME=:name, ");
        sql.append(" BUREAU_ID=:bureauId, ");
        sql.append(" XING_DIAO_ID=:xingDiaoId, ");
        sql.append(" labels=:labels, ");
        sql.append(" PINYIN=:pinyin, ");
        sql.append(" INITIAL_PINYIN=:initialPinyin, ");
        sql.append(" TELEGRAPH_CODE=:telegraphCode, ");
        sql.append(" EPSG4326=:epsg4326,");
        sql.append(" RADIUS=:radius,");
        sql.append(" ALIAS=:alias,");
        sql.append(" GEO_SPATIAL=:geoSpatial,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId, ");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName, ");
        sql.append(" LAST_EDITED_AT=sysdate ");
        sql.append(" where ID=:id");

        update(sql.substring(0), node);
    }

    /**
     * what:    删除节点. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(Node node) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete ");
        sql.append("T_NODE");
        sql.append(" where ID=:id");

        delete(sql.substring(0), node.getId());
    }
}
