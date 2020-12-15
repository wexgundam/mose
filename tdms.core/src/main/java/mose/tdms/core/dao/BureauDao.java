/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.vo.BureauSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    路局数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class BureauDao extends BaseDao<Bureau, BureauSearchVo> {
    /**
     * what:    根据查询条件查询一组路局. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Bureau> getMany(BureauSearchVo bureauSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" ID,");
        sql.append(" NAME,");
        sql.append(" SHORT_NAME,");
        sql.append(" CODE,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT,");
        sql.append(" LAST_VERIFIER_ID,");
        sql.append(" LAST_VERIFIER_REAL_NAME,");
        sql.append(" LAST_VERIFIED_AT");
        sql.append(" from");
        sql.append(" TDMS_BUREAU");
        sql.append(" where 1=1");

        sql.append(createSearchSql(bureauSearchVo));
        return list(sql.substring(0), bureauSearchVo);
    }

    /**
     * what:    根据查询条件查询一个路局. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Bureau getOne(BureauSearchVo bureauSearchVo) {
        List<Bureau> many = getMany(bureauSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路路局. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Bureau> getAll() {
        BureauSearchVo bureauSearchVo = new BureauSearchVo();
        return getMany(bureauSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(BureauSearchVo bureauSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" count(ID)");
        sql.append(" from");
        sql.append(" TDMS_BUREAU");
        sql.append(" where 1=1");

        sql.append(createSearchSql(bureauSearchVo));
        return count(sql.substring(0), bureauSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(BureauSearchVo bureauSearchVo) {
        String sql = "";
        if (bureauSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (!StringUtil.isNullOrEmpty(bureauSearchVo.getNameEqual())) {
            sql += " and NAME=:nameEqual";
        }
        if (!StringUtil.isNullOrEmpty(bureauSearchVo.getNameLike())) {
            sql += " and NAME like :nameLike";
        }
        if (!StringUtil.isNullOrEmpty(bureauSearchVo.getShortNameEqual())) {
            sql += " and SHORT_NAME=:shortNameEqual";
        }
        if (bureauSearchVo.getCodeEqual() != null) {
            sql += " and CODE=:codeEqual";
        }
        if (!StringUtil.isNullOrEmpty(bureauSearchVo.getTelegraphCodeEqual())) {
            sql += " and TELEGRAPH_CODE=:telegraphCodeEqual";
        }
        //已审核
        if (bureauSearchVo.getVerified() != null && bureauSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is not null and (LAST_VERIFIED_AT >= LAST_EDITED_AT or LAST_EDITED_AT is null))";
        }
        //未审核
        if (bureauSearchVo.getVerified() != null && !bureauSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is null or LAST_VERIFIED_AT < LAST_EDITED_AT)";
        }

        return sql;
    }

    /**
     * what:    新增路局. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(Bureau bureau) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into");
        sql.append(" TDMS_BUREAU");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" NAME,");
        sql.append(" SHORT_NAME,");
        sql.append(" CODE,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" ) values (");
        sql.append(" seq_tdms_bureau.nextval,");
        sql.append(" :name,");
        sql.append(" :shortName,");
        sql.append(" :code,");
        sql.append(" :telegraphCode,");
        sql.append(" :latitude,");
        sql.append(" :longitude,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(" )");

        return insertForId(sql.substring(0), bureau, "id");
    }

    /**
     * what:    更新路局. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(Bureau bureau) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_BUREAU");
        sql.append(" set");
        sql.append(" NAME=:name,");
        sql.append(" SHORT_NAME=:shortName,");
        sql.append(" CODE=:code,");
        sql.append(" LATITUDE=:latitude,");
        sql.append(" LONGITUDE=:longitude,");
        sql.append(" TELEGRAPH_CODE=:telegraphCode,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId,");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName,");
        sql.append(" LAST_EDITED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), bureau);
    }

    /**
     * what:    核对. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void verifyOne(Bureau bureau) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_BUREAU");
        sql.append(" set");
        sql.append(" LAST_VERIFIER_ID=:lastVerifierId,");
        sql.append(" LAST_VERIFIER_REAL_NAME=:lastVerifierRealName,");
        sql.append(" LAST_VERIFIED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), bureau);
    }

    /**
     * what:    删除路局. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(Bureau bureau) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete");
        sql.append(" TDMS_BUREAU");
        sql.append(" where ID=:id");

        delete(sql.substring(0), bureau.getId());
    }
}
