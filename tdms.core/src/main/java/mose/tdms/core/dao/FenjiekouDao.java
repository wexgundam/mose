/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.Fenjiekou;
import mose.tdms.core.modal.TrainlineDepot;
import mose.tdms.core.vo.FenjiekouSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    分界口数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class FenjiekouDao extends BaseDao<Fenjiekou, FenjiekouSearchVo> {
    /**
     * what:    根据查询条件查询一组分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Fenjiekou> getMany(FenjiekouSearchVo fenjiekouSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" (select NAME from TDMS_BUREAU where id=BUREAU_ID) as BureauName,");
        sql.append(" TRAINLINE_D_ID as TrainlineDepotId,");
        sql.append(" (select NAME from TDMS_TRAINLINE_DEPOT where id=TRAINLINE_D_ID) as TrainlineDepotName,");
        sql.append(" TARGET_BUREAU_ID,");
        sql.append(" (select NAME from TDMS_BUREAU where id=TARGET_BUREAU_ID) as TargetBureauName,");
        sql.append(" TARGET_TRAINLINE_D_ID as TargetTrainlineDepotId,");
        sql.append(" (select NAME from TDMS_TRAINLINE_DEPOT where id=TARGET_TRAINLINE_D_ID) as TargetTrainlineDepotName,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CODE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" from");
        sql.append(" TDMS_FENJIEKOU");
        sql.append(" where 1=1");

        sql.append(createSearchSql(fenjiekouSearchVo));
        return list(sql.substring(0), fenjiekouSearchVo);
    }

    /**
     * what:    根据查询条件查询一个分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Fenjiekou getOne(FenjiekouSearchVo fenjiekouSearchVo) {
        List<Fenjiekou> many = getMany(fenjiekouSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Fenjiekou> getAll() {
        FenjiekouSearchVo fenjiekouSearchVo = new FenjiekouSearchVo();
        return getMany(fenjiekouSearchVo);
    }

    /**
     * what:    获取给定路局管辖的分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Fenjiekou> getMany(Bureau bureau) {
        FenjiekouSearchVo fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setBureauIdEqual(bureau.getId());
        return getMany(fenjiekouSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(FenjiekouSearchVo fenjiekouSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" count(ID)");
        sql.append(" from");
        sql.append(" TDMS_FENJIEKOU");
        sql.append(" where 1=1");

        sql.append(createSearchSql(fenjiekouSearchVo));
        return count(sql.substring(0), fenjiekouSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(FenjiekouSearchVo fenjiekouSearchVo) {
        String sql = "";
        if (fenjiekouSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (fenjiekouSearchVo.getBureauIdEqual() != null) {
            sql += " and BUREAU_ID=:bureauIdEqual";
        }
        if (!StringUtil.isNullOrEmpty(fenjiekouSearchVo.getTextLike())) {
            sql += " and (NAME like '%'||:textLike||'%' or NAME_PINYIN like '%'||:textLike||'%' or NAME_INITIAL_PINYIN like '%'||:textLike||'%')";
        }
        if (!StringUtil.isNullOrEmpty(fenjiekouSearchVo.getTelegraphCodeEqual())) {
            sql += " and TELEGRAPH_CODE=:telegraphCodeEqual";
        }

        return sql;
    }

    /**
     * what:    新增分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(Fenjiekou fenjiekou) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into");
        sql.append(" TDMS_FENJIEKOU");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" TRAINLINE_D_ID,");
        sql.append(" TARGET_BUREAU_ID,");
        sql.append(" TARGET_TRAINLINE_D_ID,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CODE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" ) values (");
        sql.append(" seq_tdms_fenjiekou.nextval,");
        sql.append(" :bureauId,");
        sql.append(" :trainlineDepotId,");
        sql.append(" :targetBureauId,");
        sql.append(" :targetTrainlineDepotId,");
        sql.append(" :name,");
        sql.append(" :namePinyin,");
        sql.append(" :nameInitialPinyin,");
        sql.append(" :telegraphCode,");
        sql.append(" :latitude,");
        sql.append(" :longitude,");
        sql.append(" :code,");
        sql.append(" :creatorId,");
        sql.append(" :creatorRealName,");
        sql.append(" sysdate,");
        sql.append(" :lastEditorId,");
        sql.append(" :lastEditorRealName,");
        sql.append(" sysdate");
        sql.append(" )");

        return insertForId(sql.substring(0), fenjiekou, "id");
    }

    /**
     * what:    更新分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(Fenjiekou fenjiekou) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_FENJIEKOU");
        sql.append(" set");
        sql.append(" BUREAU_ID=:bureauId,");
        sql.append(" TRAINLINE_D_ID=:trainlineDepotId,");
        sql.append(" TARGET_BUREAU_ID=:targetBureauId,");
        sql.append(" TARGET_TRAINLINE_D_ID=:targetTrainlineDepotId,");
        sql.append(" NAME=:name,");
        sql.append(" NAME_PINYIN=:namePinyin,");
        sql.append(" NAME_INITIAL_PINYIN=:nameInitialPinyin,");
        sql.append(" TELEGRAPH_CODE=:telegraphCode,");
        sql.append(" LATITUDE=:latitude,");
        sql.append(" LONGITUDE=:longitude,");
        sql.append(" CODE=:code,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId,");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName,");
        sql.append(" LAST_EDITED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), fenjiekou);
    }

    /**
     * what:    删除分界口. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(Fenjiekou fenjiekou) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete");
        sql.append(" TDMS_FENJIEKOU");
        sql.append(" where ID=:id");

        delete(sql.substring(0), fenjiekou.getId());
    }
}
