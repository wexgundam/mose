/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.tdms.core.vo;

/**
 * what:    节点间查询条件Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class LineSearchVo {
    /**
     * id等于
     */
    private Integer idEqual;
    /**
     * station id等于
     */
    private Integer stationIdEqual;
    /**
     * stations id等于
     */
    private Integer[] stationsIdEqual;

    public Integer getIdEqual() {
        return idEqual;
    }

    public void setIdEqual(Integer idEqual) {
        this.idEqual = idEqual;
    }

    public Integer getStationIdEqual() {
        return stationIdEqual;
    }

    public void setStationIdEqual(Integer stationIdEqual) {
        this.stationIdEqual = stationIdEqual;
    }

    public Integer[] getStationsIdEqual() {
        return stationsIdEqual;
    }

    public void setStationsIdEqual(Integer[] stationsIdEqual) {
        this.stationsIdEqual = stationsIdEqual;
    }
}
