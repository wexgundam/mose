/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.TrainoperationDepot;
import mose.tdms.core.vo.TrainoperationDepotSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    车务段数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class TrainoperationDepotDao extends BaseDao<TrainoperationDepot, TrainoperationDepotSearchVo> {
    /**
     * what:    根据查询条件查询一组车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<TrainoperationDepot> getMany(TrainoperationDepotSearchVo trainoperationDepotSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" (select NAME from TDMS_BUREAU where id=BUREAU_ID) as BureauName,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
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
        sql.append(" TDMS_TRAINOPERATION_DEPOT");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(trainoperationDepotSearchVo));
        return list(sql.substring(0), trainoperationDepotSearchVo);
    }

    /**
     * what:    根据查询条件查询一个车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public TrainoperationDepot getOne(TrainoperationDepotSearchVo trainoperationDepotSearchVo) {
        List<TrainoperationDepot> many = getMany(trainoperationDepotSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<TrainoperationDepot> getAll() {
        TrainoperationDepotSearchVo trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        return getMany(trainoperationDepotSearchVo);
    }

    /**
     * what:    获取给定路局管辖的车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<TrainoperationDepot> getMany(Bureau bureau) {
        TrainoperationDepotSearchVo trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setBureauIdEqual(bureau.getId());
        return getMany(trainoperationDepotSearchVo);
    }

    /**
     * what:    获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(TrainoperationDepotSearchVo trainoperationDepotSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" count(ID) ");
        sql.append(" from ");
        sql.append(" TDMS_TRAINOPERATION_DEPOT");
        sql.append(" where 1=1 ");

        sql.append(createSearchSql(trainoperationDepotSearchVo));
        return count(sql.substring(0), trainoperationDepotSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(TrainoperationDepotSearchVo trainoperationDepotSearchVo) {
        String sql = "";
        if (trainoperationDepotSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (trainoperationDepotSearchVo.getBureauIdEqual() != null) {
            sql += " and BUREAU_ID=:bureauIdEqual";
        }
        if (!StringUtil.isNullOrEmpty(trainoperationDepotSearchVo.getTextLike())) {
            sql += " and (NAME like '%'||:textLike||'%' or NAME_PINYIN like '%'||:textLike||'%' or NAME_INITIAL_PINYIN like '%'||:textLike||'%')";
        }
        //已审核
        if (trainoperationDepotSearchVo.getVerified() != null && trainoperationDepotSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is not null and (LAST_VERIFIED_AT >= LAST_EDITED_AT or LAST_EDITED_AT is null))";
        }
        //未审核
        if (trainoperationDepotSearchVo.getVerified() != null && !trainoperationDepotSearchVo.getVerified()) {
            sql += " and (LAST_VERIFIED_AT is null or LAST_VERIFIED_AT < LAST_EDITED_AT)";
        }
        return sql;
    }

    /**
     * what:    新增车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(TrainoperationDepot trainoperationDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        sql.append(" TDMS_TRAINOPERATION_DEPOT");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" ) values (");
        sql.append(" seq_tdms_Trainoperation_depot.nextval,");
        sql.append(" :bureauId,");
        sql.append(" :name,");
        sql.append(" :namePinyin,");
        sql.append(" :nameInitialPinyin,");
        sql.append(" :latitude,");
        sql.append(" :longitude,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(" )");

        return insertForId(sql.substring(0), trainoperationDepot, "id");
    }

    /**
     * what:    更新车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(TrainoperationDepot trainoperationDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ");
        sql.append(" TDMS_TRAINOPERATION_DEPOT");
        sql.append(" set ");
        sql.append(" BUREAU_ID=:bureauId,");
        sql.append(" NAME=:name,");
        sql.append(" NAME_PINYIN=:namePinyin,");
        sql.append(" NAME_INITIAL_PINYIN=:nameInitialPinyin,");
        sql.append(" LATITUDE=:latitude,");
        sql.append(" LONGITUDE=:longitude,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId, ");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName, ");
        sql.append(" LAST_EDITED_AT=sysdate ");
        sql.append(" where ID=:id");

        update(sql.substring(0), trainoperationDepot);
    }

    /**
     * what:    核对. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void verifyOne(TrainoperationDepot trainoperationDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_TRAINOPERATION_DEPOT");
        sql.append(" set");
        sql.append(" LAST_VERIFIER_ID=:lastVerifierId,");
        sql.append(" LAST_VERIFIER_REAL_NAME=:lastVerifierRealName,");
        sql.append(" LAST_VERIFIED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), trainoperationDepot);
    }

    /**
     * what:    删除车务段. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(TrainoperationDepot trainoperationDepot) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete ");
        sql.append(" TDMS_TRAINOPERATION_DEPOT");
        sql.append(" where ID=:id");

        delete(sql.substring(0), trainoperationDepot.getId());
    }
}
