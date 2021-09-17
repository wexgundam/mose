/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.modal;

import java.util.Date;

/**
 * what:    列车运行线时刻. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/6
 */
public class TrainlineItem {
    /**
     * 主键
     */
    private int id;
    /**
     * 所属运行线的ID
     */
    private int trianlineId;
    /**
     * 对应的铁路路网元素类别
     */
    private int networkElementType;
    /**
     * 对应的铁路路网元素Id
     */
    private int networkElementId;
    /**
     * 对应的铁路路网元素名称
     */
    private String networkElementName;
    /**
     * 对应的铁路路网元素的基点坐标的字符串形式
     */
    private String networkElementBasePointString;
    /**
     * 时刻
     */
    private Date time;
    /**
     * 排序
     */
    private int order;
    /**
     * 运行标记
     */
    private int runningTag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainlineItem that = (TrainlineItem) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TrainlineItem{" +
                "id=" + id +
                ", trianlineId=" + trianlineId +
                ", networkElementType=" + networkElementType +
                ", networkElementId=" + networkElementId +
                ", networkElementName='" + networkElementName + '\'' +
                ", networkElementBasePointString='" + networkElementBasePointString + '\'' +
                ", time=" + time +
                ", order=" + order +
                ", runningTag=" + runningTag +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrianlineId() {
        return trianlineId;
    }

    public void setTrianlineId(int trianlineId) {
        this.trianlineId = trianlineId;
    }

    public int getNetworkElementType() {
        return networkElementType;
    }

    public void setNetworkElementType(int networkElementType) {
        this.networkElementType = networkElementType;
    }

    public int getNetworkElementId() {
        return networkElementId;
    }

    public void setNetworkElementId(int networkElementId) {
        this.networkElementId = networkElementId;
    }

    public String getNetworkElementName() {
        return networkElementName;
    }

    public void setNetworkElementName(String networkElementName) {
        this.networkElementName = networkElementName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getRunningTag() {
        return runningTag;
    }

    public void setRunningTag(int runningTag) {
        this.runningTag = runningTag;
    }

    public String getNetworkElementBasePointString() {
        return networkElementBasePointString;
    }

    public void setNetworkElementBasePointString(String networkElementBasePointString) {
        this.networkElementBasePointString = networkElementBasePointString;
    }
}
