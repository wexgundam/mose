/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.tdms.modal.Bureau;
import mose.tdms.modal.TrainlineDepot;
import mose.tdms.vo.TrainlineDepotSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    行车调度台数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class TrainlineDepotDao extends BaseDao<TrainlineDepot, TrainlineDepotSearchVo> {
    /**
     * what:    根据查询条件查询一组行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<TrainlineDepot> getMany(TrainlineDepotSearchVo trainlineDepotSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" (select NAME from TDMS_BUREAU where id=BUREAU_ID) as BureauName,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
        sql.append(" DDT_ID,");
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
        sql.append(" LAST_EDITED_AT,");
        sql.append(" LAST_VERIFIER_ID,");
        sql.append(" LAST_VERIFIER_REAL_NAME,");
        sql.append(" LAST_VERIFIED_AT");
        sql.append(" from ");
        sql.append(" TDMS_TRAINLINE_DEPOT");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(trainlineDepotSearchVo));
        return list(sql.substring(0), trainlineDepotSearchVo);
    }

    /**
     * what:    根据查询条件查询一个行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public TrainlineDepot getOne(TrainlineDepotSearchVo trainlineDepotSearchVo) {
        List<TrainlineDepot> many = getMany(trainlineDepotSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<TrainlineDepot> getAll() {
        TrainlineDepotSearchVo trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        return getMany(trainlineDepotSearchVo);
    }

    /**
     * what:    获取给定路局管辖的行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<TrainlineDepot> getMany(Bureau bureau) {
        TrainlineDepotSearchVo trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setBureauIdEqual(bureau.getId());
        return getMany(trainlineDepotSearchVo);
    }

    /**
     * what:    获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(TrainlineDepotSearchVo trainlineDepotSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" count(ID) ");
        sql.append(" from ");
        sql.append(" TDMS_TRAINLINE_DEPOT");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(trainlineDepotSearchVo));
        return count(sql.substring(0), trainlineDepotSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(TrainlineDepotSearchVo trainlineDepotSearchVo) {
        String sql = "";
        if (trainlineDepotSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (trainlineDepotSearchVo.getBureauIdEqual() != null) {
            sql += " and BUREAU_ID=:bureauIdEqual";
        }
        if (!StringUtil.isNullOrEmpty(trainlineDepotSearchVo.getTextLike())) {
            sql += " and (NAME like '%'||:textLike||'%' or NAME_PINYIN like '%'||:textLike||'%' or NAME_INITIAL_PINYIN like '%'||:textLike||'%')";
        }
        if (trainlineDepotSearchVo.getDdtIdEqual() != null) {
            sql += " and DDT_ID=:ddtIdEqual";
        }
        //已审核
        if (trainlineDepotSearchVo.getVerified() != null && trainlineDepotSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is not null and (LAST_VERIFIED_AT >= LAST_EDITED_AT or LAST_EDITED_AT is null))";
        }
        //未审核
        if (trainlineDepotSearchVo.getVerified() != null && !trainlineDepotSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is null or LAST_VERIFIED_AT < LAST_EDITED_AT)";
        }
        return sql;
    }

    /**
     * what:    新增行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(TrainlineDepot trainlineDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        sql.append(" TDMS_TRAINLINE_DEPOT");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
        sql.append(" DDT_ID,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" ) values (");
        sql.append(" seq_tdms_trainline_depot.nextval,");
        sql.append(" :bureauId,");
        sql.append(" :name,");
        sql.append(" :namePinyin,");
        sql.append(" :nameInitialPinyin,");
        sql.append(" :ddtId,");
        sql.append(" :latitude,");
        sql.append(" :longitude,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(" )");

        return insertForId(sql.substring(0), trainlineDepot, "id");
    }

    /**
     * what:    更新行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(TrainlineDepot trainlineDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ");
        sql.append(" TDMS_TRAINLINE_DEPOT");
        sql.append(" set ");
        sql.append(" BUREAU_ID=:bureauId,");
        sql.append(" NAME=:name,");
        sql.append(" NAME_PINYIN=:namePinyin,");
        sql.append(" NAME_INITIAL_PINYIN=:nameInitialPinyin,");
        sql.append(" DDT_ID=:ddtId,");
        sql.append(" LATITUDE=:latitude,");
        sql.append(" LONGITUDE=:longitude,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId, ");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName, ");
        sql.append(" LAST_EDITED_AT=sysdate ");
        sql.append(" where ID=:id");

        update(sql.substring(0), trainlineDepot);
    }

    /**
     * what:    核对. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void verifyOne(TrainlineDepot trainlineDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_TRAINLINE_DEPOT");
        sql.append(" set");
        sql.append(" LAST_VERIFIER_ID=:lastVerifierId,");
        sql.append(" LAST_VERIFIER_REAL_NAME=:lastVerifierRealName,");
        sql.append(" LAST_VERIFIED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), trainlineDepot);
    }

    /**
     * what:    删除行车调度台. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(TrainlineDepot trainlineDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete ");
        sql.append(" TDMS_TRAINLINE_DEPOT");
        sql.append(" where ID=:id");

        delete(sql.substring(0), trainlineDepot.getId());
    }
}
