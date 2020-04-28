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
 * what:    列车运行图. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/4
 */
public class TrainlineMap {
    /**
     * 2分格运行图类型
     */
    public static final int TIME_AXIS_TYPE_2_MINUTE = 0;
    /**
     * 10分格运行图类型
     */
    public static final int TIME_AXIS_TYPE_10_MINUTE = 1;
    /**
     * 30分格运行图类型
     */
    public static final int TIME_AXIS_TYPE_30_MINUTE = 2;
    /**
     * 60分格运行图类型
     */
    public static final int TIME_AXIS_TYPE_60_MINUTE = 3;

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
     * 全程运行时分（单位秒）
     */
    private int runtimeCount;
    /**
     * 运行时分与长度换算标准，默认1
     */
    private double runtimesSpace = 2;
    /**
     * 时间轴startTime
     */
    private Date timeAxisStartTime;
    /**
     * 时间轴总小时数
     */
    private int timeAxisHourCount;
    /**
     * 时间轴每毫秒与长度换算标准，默认0.12，即1分钟间隔2
     */
    private double timeAxisMillisecondsSpace = 0.12;
    /**
     * 类型
     */
    private int timeAxisType = TIME_AXIS_TYPE_10_MINUTE;
    /**
     * 时间轴起始坐标X
     */
    private double timeAxisStartX = 150;
    /**
     * 时间轴起始坐标Y
     */
    private double timeAxisStartY = 50;
    /**
     * 时间轴分钟线高
     */
    private double timeAxisMinuteLineStringHeight = -5;
    /**
     * 时间轴10分线高
     */
    private double timeAxis10MinuteLineStringHeight = -7;
    /**
     * 时间轴30分线高
     */
    private double timeAxis30MinuteLineStringHeight = -10;
    /**
     * 时间轴60分线高
     */
    private double timeAxis60MinuteLineStringHeight = -15;
    /**
     * 时间轴60分文本高
     */
    private double timeAxis60MinuteTextHeight = -20;
    /**
     * 时间轴日期文本高
     */
    private double timeAxisDayTextHeight = 20;
    /**
     * 左表头起始坐标Y
     */
    private double tableLeftHeaderStartX = 50;
    /**
     * 左表头起始坐标Y
     */
    private double tableLeftHeaderStartY = 180;
    /**
     * 列车运行图左表头项
     */
    private List<TrainlineMapTableLeftHeaderItem> tableLeftHeaderItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainlineMap that = (TrainlineMap) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TrainlineMap{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", routeAnchorPointsString='" + routeAnchorPointsString + '\'' +
                ", runtimeCount=" + runtimeCount +
                ", runtimesSpace=" + runtimesSpace +
                ", timeAxisStartTime=" + timeAxisStartTime +
                ", timeAxisHourCount=" + timeAxisHourCount +
                ", timeAxisMinutesSpace=" + timeAxisMillisecondsSpace +
                ", timeAxisType=" + timeAxisType +
                ", timeAxisStartX=" + timeAxisStartX +
                ", timeAxisStartY=" + timeAxisStartY +
                ", timeAxisMinuteLineStringHeight=" + timeAxisMinuteLineStringHeight +
                ", timeAxis10MinuteLineStringHeight=" + timeAxis10MinuteLineStringHeight +
                ", timeAxis30MinuteLineStringHeight=" + timeAxis30MinuteLineStringHeight +
                ", timeAxis60MinuteLineStringHeight=" + timeAxis60MinuteLineStringHeight +
                ", timeAxis60MinuteTextHeight=" + timeAxis60MinuteTextHeight +
                ", timeAxisDayTextHeight=" + timeAxisDayTextHeight +
                ", tableLeftHeaderStartX=" + tableLeftHeaderStartX +
                ", tableLeftHeaderStartY=" + tableLeftHeaderStartY +
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

    public int getRuntimeCount() {
        return runtimeCount;
    }

    public void setRuntimeCount(int runtimeCount) {
        this.runtimeCount = runtimeCount;
    }

    public double getRuntimesSpace() {
        return runtimesSpace;
    }

    public void setRuntimesSpace(double runtimesSpace) {
        this.runtimesSpace = runtimesSpace;
    }

    public Date getTimeAxisStartTime() {
        return timeAxisStartTime;
    }

    public void setTimeAxisStartTime(Date timeAxisStartTime) {
        this.timeAxisStartTime = timeAxisStartTime;
    }

    public int getTimeAxisHourCount() {
        return timeAxisHourCount;
    }

    public void setTimeAxisHourCount(int timeAxisHourCount) {
        this.timeAxisHourCount = timeAxisHourCount;
    }

    public double getTimeAxisMillisecondsSpace() {
        return timeAxisMillisecondsSpace;
    }

    public void setTimeAxisMillisecondsSpace(double timeAxisMillisecondsSpace) {
        this.timeAxisMillisecondsSpace = timeAxisMillisecondsSpace;
    }

    public int getTimeAxisType() {
        return timeAxisType;
    }

    public void setTimeAxisType(int timeAxisType) {
        this.timeAxisType = timeAxisType;
    }

    public double getTimeAxisStartX() {
        return timeAxisStartX;
    }

    public void setTimeAxisStartX(double timeAxisStartX) {
        this.timeAxisStartX = timeAxisStartX;
    }

    public double getTimeAxisStartY() {
        return timeAxisStartY;
    }

    public void setTimeAxisStartY(double timeAxisStartY) {
        this.timeAxisStartY = timeAxisStartY;
    }

    public double getTimeAxisMinuteLineStringHeight() {
        return timeAxisMinuteLineStringHeight;
    }

    public void setTimeAxisMinuteLineStringHeight(double timeAxisMinuteLineStringHeight) {
        this.timeAxisMinuteLineStringHeight = timeAxisMinuteLineStringHeight;
    }

    public double getTimeAxis10MinuteLineStringHeight() {
        return timeAxis10MinuteLineStringHeight;
    }

    public void setTimeAxis10MinuteLineStringHeight(double timeAxis10MinuteLineStringHeight) {
        this.timeAxis10MinuteLineStringHeight = timeAxis10MinuteLineStringHeight;
    }

    public double getTimeAxis30MinuteLineStringHeight() {
        return timeAxis30MinuteLineStringHeight;
    }

    public void setTimeAxis30MinuteLineStringHeight(double timeAxis30MinuteLineStringHeight) {
        this.timeAxis30MinuteLineStringHeight = timeAxis30MinuteLineStringHeight;
    }

    public double getTimeAxis60MinuteLineStringHeight() {
        return timeAxis60MinuteLineStringHeight;
    }

    public void setTimeAxis60MinuteLineStringHeight(double timeAxis60MinuteLineStringHeight) {
        this.timeAxis60MinuteLineStringHeight = timeAxis60MinuteLineStringHeight;
    }

    public double getTimeAxis60MinuteTextHeight() {
        return timeAxis60MinuteTextHeight;
    }

    public void setTimeAxis60MinuteTextHeight(double timeAxis60MinuteTextHeight) {
        this.timeAxis60MinuteTextHeight = timeAxis60MinuteTextHeight;
    }

    public double getTimeAxisDayTextHeight() {
        return timeAxisDayTextHeight;
    }

    public void setTimeAxisDayTextHeight(double timeAxisDayTextHeight) {
        this.timeAxisDayTextHeight = timeAxisDayTextHeight;
    }

    public double getTableLeftHeaderStartX() {
        return tableLeftHeaderStartX;
    }

    public void setTableLeftHeaderStartX(double tableLeftHeaderStartX) {
        this.tableLeftHeaderStartX = tableLeftHeaderStartX;
    }

    public double getTableLeftHeaderStartY() {
        return tableLeftHeaderStartY;
    }

    public void setTableLeftHeaderStartY(double tableLeftHeaderStartY) {
        this.tableLeftHeaderStartY = tableLeftHeaderStartY;
    }

    public List<TrainlineMapTableLeftHeaderItem> getTableLeftHeaderItems() {
        tableLeftHeaderItems = tableLeftHeaderItems == null ? new ArrayList<>() : tableLeftHeaderItems;
        return tableLeftHeaderItems;
    }

    public void setTableLeftHeaderItems(List<TrainlineMapTableLeftHeaderItem> tableLeftHeaderItems) {
        this.tableLeftHeaderItems = tableLeftHeaderItems;
    }
}
