/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.rail.core.modal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * what:    列车运行线. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/4
 */
public class Trainline {
    /**
     * 运行标记：始发
     */
    public static final int RUNNING_TAG_START = 0;
    /**
     * 运行标记：到达
     */
    public static final int RUNNING_TAG_ARRIVE = 1;
    /**
     * 运行标记：不停车通过
     */
    public static final int RUNNING_TAG_PASS = 2;
    /**
     * 运行标记：出发
     */
    public static final int RUNNING_TAG_DEPART = 3;
    /**
     * 运行标记：终到
     */
    public static final int RUNNING_TAG_END = 4;
    /**
     * 主键，唯一标识
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 径路（广义节点间）锚点坐标的字符串形式
     */
    private String routeAnchorPointsString;
    /**
     * 始发时刻
     */
    private Date startTime;
    /**
     * 终到时刻
     */
    private Date endTime;
    /**
     * 列车运行线时刻集合
     */
    private List<TrainlineItem> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainline that = (Trainline) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Trainline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", routeAnchorPointsString='" + routeAnchorPointsString + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteAnchorPointsString() {
        return routeAnchorPointsString;
    }

    public void setRouteAnchorPointsString(String routeAnchorPointsString) {
        this.routeAnchorPointsString = routeAnchorPointsString;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<TrainlineItem> getItems() {
        items = items == null ? new ArrayList<>() : items;
        return items;
    }

    public void setItems(List<TrainlineItem> items) {
        this.items = items;
    }
}
