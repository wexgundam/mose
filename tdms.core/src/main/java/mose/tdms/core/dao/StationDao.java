/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.core.dao.BaseDao;
import mose.core.string.StringUtil;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.Station;
import mose.tdms.core.modal.TrainlineDepot;
import mose.tdms.core.vo.StationSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * what:    车站数据获取对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/16
 */
@Repository
public class StationDao extends BaseDao<Station, StationSearchVo> {
    /**
     * what:    根据查询条件查询一组车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Station> getMany(StationSearchVo stationSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" (select NAME from TDMS_BUREAU where id=BUREAU_ID) as BureauName,");
        sql.append(" TRAINLINE_D_ID as trainlineDepotId,");
        sql.append(" (select NAME from TDMS_TRAINLINE_DEPOT where id=TRAINLINE_D_ID) as TrainlineDepotName,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
        sql.append(" TELEGRAPH_CODE,");
        sql.append(" LATITUDE,");
        sql.append(" LONGITUDE,");
        sql.append(" CREATOR_ID,");
        sql.append(" CREATOR_REAL_NAME,");
        sql.append(" CREATED_AT,");
        sql.append(" LAST_EDITOR_ID,");
        sql.append(" LAST_EDITOR_REAL_NAME,");
        sql.append(" LAST_EDITED_AT");
        sql.append(" from");
        sql.append(" TDMS_STATION");
        sql.append(" where 1=1");

        sql.append(createSearchSql(stationSearchVo));
        return list(sql.substring(0), stationSearchVo);
    }

    /**
     * what:    根据查询条件查询一个车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Station getOne(StationSearchVo stationSearchVo) {
        List<Station> many = getMany(stationSearchVo);
        if (many.size() > 0) {
            return many.get(0);
        } else {
            return null;
        }
    }

    /**
     * what:    获取全路车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Station> getAll() {
        StationSearchVo stationSearchVo = new StationSearchVo();
        return getMany(stationSearchVo);
    }

    /**
     * what:    获取给定路局管辖的车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Station> getMany(Bureau bureau) {
        StationSearchVo stationSearchVo = new StationSearchVo();
        stationSearchVo.setBureauIdEqual(bureau.getId());
        return getMany(stationSearchVo);
    }

    /**
     * what:    获取给定行调台管辖的车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Station> getMany(TrainlineDepot trainlineDepot) {
        StationSearchVo stationSearchVo = new StationSearchVo();
        stationSearchVo.setTrainlineDepotIdEqual(trainlineDepot.getId());
        return getMany(stationSearchVo);
    }

    /**
     * what:    按条件获取数量. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/25
     */
    public int getCount(StationSearchVo stationSearchVo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" count(ID)");
        sql.append(" from");
        sql.append(" TDMS_STATION");
        sql.append(" where 1=1");

        sql.append(createSearchSql(stationSearchVo));
        return count(sql.substring(0), stationSearchVo);
    }

    /**
     * what:    设置查询条件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/20
     */
    private String createSearchSql(StationSearchVo stationSearchVo) {
        String sql = "";
        if (stationSearchVo.getIdEqual() != null) {
            sql += " and ID=:idEqual";
        }
        if (stationSearchVo.getBureauIdEqual() != null) {
            sql += " and BUREAU_ID=:bureauIdEqual";
        }
        if (stationSearchVo.getTrainlineDepotIdEqual() != null) {
            sql += " and TRAINLINE_D_ID=:trainlineDepotIdEqual";
        }
        if (!StringUtil.isNullOrEmpty(stationSearchVo.getTextLike())) {
            sql += " and (NAME like '%'||:textLike||'%' or NAME_PINYIN like '%'||:textLike||'%' or NAME_INITIAL_PINYIN like '%'||:textLike||'%')";
        }
        if (!StringUtil.isNullOrEmpty(stationSearchVo.getTelegraphCodeEqual())) {
            sql += " and TELEGRAPH_CODE=:telegraphCodeEqual";
        }

        return sql;
    }

    /**
     * what:    新增车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public int addOne(Station station) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into");
        sql.append(" TDMS_STATION");
        sql.append(" (");
        sql.append(" ID,");
        sql.append(" BUREAU_ID,");
        sql.append(" TRAINLINE_D_ID,");
        sql.append(" NAME,");
        sql.append(" NAME_PINYIN,");
        sql.append(" NAME_INITIAL_PINYIN,");
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
        sql.append(" seq_tdms_station.nextval,");
        sql.append(" :bureauId,");
        sql.append(" :trainlineDepotId,");
        sql.append(" :name,");
        sql.append(" :namePinyin,");
        sql.append(" :nameInitialPinyin,");
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

        return insertForId(sql.substring(0), station, "id");
    }

    /**
     * what:    更新车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(Station station) {
        StringBuffer sql = new StringBuffer();
        sql.append("update");
        sql.append(" TDMS_STATION");
        sql.append(" set");
        sql.append(" BUREAU_ID=:bureauId,");
        sql.append(" TRAINLINE_D_ID=:trainlineDepotId,");
        sql.append(" NAME=:name,");
        sql.append(" NAME_PINYIN=:namePinyin,");
        sql.append(" NAME_INITIAL_PINYIN=:nameInitialPinyin,");
        sql.append(" TELEGRAPH_CODE=:telegraphCode,");
        sql.append(" LATITUDE=:latitude,");
        sql.append(" LONGITUDE=:longitude,");
        sql.append(" LAST_EDITOR_ID=:lastEditorId,");
        sql.append(" LAST_EDITOR_REAL_NAME=:lastEditorRealName,");
        sql.append(" LAST_EDITED_AT=sysdate");
        sql.append(" where ID=:id");

        update(sql.substring(0), station);
    }

    /**
     * what:    删除车站. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(Station station) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete");
        sql.append(" TDMS_STATION");
        sql.append(" where ID=:id");

        delete(sql.substring(0), station.getId());
    }
}
