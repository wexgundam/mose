/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail2.vo;

import java.util.Arrays;

/**
 * what:    车站位置Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/23
 */
public class StationLocationVo {
    /**
     * 统一Id
     */
    private int id;
    /**
     * 统一车站名称
     */
    private String stationName;
    /**
     * 运输调度车站名
     */
    private String tdmsStationName;
    /**
     * 行调站名
     */
    private String trainlineDepotStationName;
    /**
     * 经纬度坐标（纬度、经度）
     */
    private double[] latLng;
    /**
     * 网格Id
     */
    private int gridId;
    /**
     * 行政信息
     */
    private String administration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationLocationVo)) return false;

        StationLocationVo that = (StationLocationVo) o;

        if (id != that.id) return false;
        if (gridId != that.gridId) return false;
        if (stationName != null ? !stationName.equals(that.stationName) : that.stationName != null) return false;
        if (tdmsStationName != null ? !tdmsStationName.equals(that.tdmsStationName) : that.tdmsStationName != null)
            return false;
        if (trainlineDepotStationName != null ? !trainlineDepotStationName.equals(that.trainlineDepotStationName) : that.trainlineDepotStationName != null)
            return false;
        if (!Arrays.equals(latLng, that.latLng)) return false;
        return administration != null ? administration.equals(that.administration) : that.administration == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        result = 31 * result + (tdmsStationName != null ? tdmsStationName.hashCode() : 0);
        result = 31 * result + (trainlineDepotStationName != null ? trainlineDepotStationName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(latLng);
        result = 31 * result + gridId;
        result = 31 * result + (administration != null ? administration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StationLocationVo{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", tdmsStationName='" + tdmsStationName + '\'' +
                ", trainlineDepotStationName='" + trainlineDepotStationName + '\'' +
                ", latLng=" + Arrays.toString(latLng) +
                ", gridId=" + gridId +
                ", administration='" + administration + '\'' +
                '}';
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTdmsStationName() {
        return tdmsStationName;
    }

    public void setTdmsStationName(String tdmsStationName) {
        this.tdmsStationName = tdmsStationName;
    }

    public String getTrainlineDepotStationName() {
        return trainlineDepotStationName;
    }

    public void setTrainlineDepotStationName(String trainlineDepotStationName) {
        this.trainlineDepotStationName = trainlineDepotStationName;
    }

    public double[] getLatLng() {
        return latLng;
    }

    public void setLatLng(double[] latLng) {
        this.latLng = latLng;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }
}
