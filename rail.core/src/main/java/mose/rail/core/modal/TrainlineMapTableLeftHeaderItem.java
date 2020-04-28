/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.core.modal;

/**
 * what:    列车运行图左表头项. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/6
 */
public class TrainlineMapTableLeftHeaderItem {
    /**
     * 主键
     */
    private int id;
    /**
     * 所属运行图的ID
     */
    private int trianlineMapId;
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
     * 距前一元素的间隔运行时分，单位second
     */
    private int relativeRuntime;
    /**
     * 排序
     */
    private int order;
    /**
     * 是否可见
     */
    private boolean visible = true;
    /**
     * 详情是否可见
     */
    private boolean detailVisible = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainlineMapTableLeftHeaderItem that = (TrainlineMapTableLeftHeaderItem) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TrainlineMapTableLeftHeaderItem{" +
                "id=" + id +
                ", trianlineMapId=" + trianlineMapId +
                ", networkElementType=" + networkElementType +
                ", networkElementId=" + networkElementId +
                ", networkElementName='" + networkElementName + '\'' +
                ", networkElementBasePointString='" + networkElementBasePointString + '\'' +
                ", relativeRuntime=" + relativeRuntime +
                ", order=" + order +
                ", visible=" + visible +
                ", detailVisible=" + detailVisible +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrianlineMapId() {
        return trianlineMapId;
    }

    public void setTrianlineMapId(int trianlineMapId) {
        this.trianlineMapId = trianlineMapId;
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

    public int getRelativeRuntime() {
        return relativeRuntime;
    }

    public void setRelativeRuntime(int relativeRuntime) {
        this.relativeRuntime = relativeRuntime;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDetailVisible() {
        return detailVisible;
    }

    public void setDetailVisible(boolean detailVisible) {
        this.detailVisible = detailVisible;
    }

    public String getNetworkElementBasePointString() {
        return networkElementBasePointString;
    }

    public void setNetworkElementBasePointString(String networkElementBasePointString) {
        this.networkElementBasePointString = networkElementBasePointString;
    }
}
