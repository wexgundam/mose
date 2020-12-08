/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.core.modal;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/3
 */
public class Line {
    /**
     * id
     */
    private int id;
    /**
     * station a
     */
    private int stationAId;
    /**
     * station a 纬度
     */
    private double stationALatitude;
    /**
     * station a 经度
     */
    private double stationALongitude;
    /**
     * station b
     */
    private int stationBId;
    /**
     * station b 纬度
     */
    private double stationBLatitude;
    /**
     * station b 经度
     */
    private double stationBLongitude;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line)) return false;

        Line station = (Line) o;

        return id == station.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationAId() {
        return stationAId;
    }

    public void setStationAId(int stationAId) {
        this.stationAId = stationAId;
    }

    public int getStationBId() {
        return stationBId;
    }

    public void setStationBId(int stationBId) {
        this.stationBId = stationBId;
    }

    public double getStationALatitude() {
        return stationALatitude;
    }

    public void setStationALatitude(double stationALatitude) {
        this.stationALatitude = stationALatitude;
    }

    public double getStationALongitude() {
        return stationALongitude;
    }

    public void setStationALongitude(double stationALongitude) {
        this.stationALongitude = stationALongitude;
    }

    public double getStationBLatitude() {
        return stationBLatitude;
    }

    public void setStationBLatitude(double stationBLatitude) {
        this.stationBLatitude = stationBLatitude;
    }

    public double getStationBLongitude() {
        return stationBLongitude;
    }

    public void setStationBLongitude(double stationBLongitude) {
        this.stationBLongitude = stationBLongitude;
    }
}
