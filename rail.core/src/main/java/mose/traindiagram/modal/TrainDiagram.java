/**
 * Copyright 2022 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.traindiagram.modal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * what:    列车运行图. <br/>
 * 1.运行图的显示、运行图和运行线匹配算法剥离
 * 2.运行图显示信息包括显示的nodes，与nodes关联的IntervalLines
 * 3.IntervalLine包含的NodeA和NodeB均应在运行图的nodes中，缺一不可
 * 4.运行图和运行线匹配算法依据运行图显示信息，即根据运行图包含的IntervalLines查询关联的运行线
 * 5.运行图的创建需要一组维护工具，既要关联路网nodes也要关联IntervalLine，并且要核算它们的关联关系
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2022/3/2
 */
public class TrainDiagram {
    /**
     * 主键，唯一标识
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 1秒间隔与视图坐标系长度单位的比值
     */
    private Double secondRatio = 0.1;
    /**
     * 开始小时
     */
    private Integer startHour = 18;
    /**
     * 总小时数
     */
    private Integer hours = 36;
    /**
     * 运行图类型：2，10，30,60分格
     */
    private Integer minutes = 10;
    /**
     * 小时线高度
     */
    private Double hourLineHeight = 40d;
    /**
     * 分钟线高度
     */
    private Double minuteLineHeight = 20d;
    /**
     * 节点域与区间域间距
     */
    private Double nodeArea2IntervalLineArea = 100d;
    /**
     * 时间轴与区间域间距
     */
    private Double timeBar2IntervalLineArea = 200d;
    /**
     * 时间轴起始坐标x
     */
    private Double timeBarStartCoordinateX = 1000d;
    /**
     * 时间轴起始坐标y
     */
    private Double timeBarStartCoordinateY = 19000d;
    /**
     * 运行图绘图信息
     */
    private String spatial;
    /**
     * nodeMappings
     */
    private List<NodeMapping> nodeMappings;
    /**
     * intervalLineMappings
     */
    private List<IntervalLineMapping> intervalLineMappings;
    /**
     * 创建人Id，只在保存新增数据时填写，以后不可更改
     */
    private Integer creatorId;//创建人Id
    /**
     * 创建人实名，只在保存新增数据时填写，以后不可更改
     */
    private String creatorRealName;//创建人Name
    /**
     * 创建时间，只在保存新增数据时填写，以后不可更改
     */
    private Date createdAt;//创建时间
    /**
     * 最后修改人Id，在新增和更新数据时填写，修改后保存时可更改
     */
    private Integer lastEditorId;//最后修改人Id
    /**
     * 最后修改人实名，在新增和更新数据时填写，修改后保存时可更改
     */
    private String lastEditorRealName;//最后修改人实名
    /**
     * 最后修改时间，在新增和更新数据时填写，修改后保存时可更改
     */
    private Date lastEditedAt;//最后修改时间

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainDiagram)) return false;

        TrainDiagram trainDiagram = (TrainDiagram) o;

        return id == trainDiagram.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TrainDiagram{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondRatio=" + secondRatio +
                ", startHour=" + startHour +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", hourLineHeight=" + hourLineHeight +
                ", minuteLineHeight=" + minuteLineHeight +
                ", nodeArea2IntervalLineArea=" + nodeArea2IntervalLineArea +
                ", timeBar2IntervalLineArea=" + timeBar2IntervalLineArea +
                ", timeBarStartCoordinateX=" + timeBarStartCoordinateX +
                ", timeBarStartCoordinateY=" + timeBarStartCoordinateY +
                ", spatial='" + spatial + '\'' +
                ", nodeMappings=" + nodeMappings +
                ", intervalLineMappings=" + intervalLineMappings +
                ", creatorId=" + creatorId +
                ", creatorRealName='" + creatorRealName + '\'' +
                ", createdAt=" + createdAt +
                ", lastEditorId=" + lastEditorId +
                ", lastEditorRealName='" + lastEditorRealName + '\'' +
                ", lastEditedAt=" + lastEditedAt +
                '}';
    }

    /**
     * what:    获取时间线总是. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/15
     */
    public int getTimeLineCount() {
        //时间线总数
        return hours * 60 / minutes + 1;
    }

    /**
     * what:    获取时间线间距. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/15
     */
    public double getTimeLineInterval() {
        //时间线间隔
        return minutes * 60 * secondRatio;
    }

    /**
     * what:    获取运行图区间区域高度. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/15
     */
    public double getIntervlLineAreaHeight() {
        double height = 0;
        for (NodeMapping mapping : getNodeMappings()) {
            height += mapping.getInterval();
        }
        return height;
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

    public Double getSecondRatio() {
        return secondRatio;
    }

    public void setSecondRatio(Double secondRatio) {
        this.secondRatio = secondRatio;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Double getHourLineHeight() {
        return hourLineHeight;
    }

    public void setHourLineHeight(Double hourLineHeight) {
        this.hourLineHeight = hourLineHeight;
    }

    public Double getMinuteLineHeight() {
        return minuteLineHeight;
    }

    public void setMinuteLineHeight(Double minuteLineHeight) {
        this.minuteLineHeight = minuteLineHeight;
    }

    public Double getTimeBar2IntervalLineArea() {
        return timeBar2IntervalLineArea;
    }

    public void setTimeBar2IntervalLineArea(Double timeBar2IntervalLineArea) {
        this.timeBar2IntervalLineArea = timeBar2IntervalLineArea;
    }

    public String getSpatial() {
        return spatial;
    }

    public void setSpatial(String spatial) {
        this.spatial = spatial;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorRealName() {
        return creatorRealName;
    }

    public void setCreatorRealName(String creatorRealName) {
        this.creatorRealName = creatorRealName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(Integer lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    public String getLastEditorRealName() {
        return lastEditorRealName;
    }

    public void setLastEditorRealName(String lastEditorRealName) {
        this.lastEditorRealName = lastEditorRealName;
    }

    public Date getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Double getNodeArea2IntervalLineArea() {
        return nodeArea2IntervalLineArea;
    }

    public void setNodeArea2IntervalLineArea(Double nodeArea2IntervalLineArea) {
        this.nodeArea2IntervalLineArea = nodeArea2IntervalLineArea;
    }

    public List<NodeMapping> getNodeMappings() {
        if (nodeMappings == null) {
            nodeMappings = new ArrayList<>();
        }
        return nodeMappings;
    }

    public void setNodeMappings(List<NodeMapping> nodeMappings) {
        this.nodeMappings = nodeMappings;
    }

    public List<IntervalLineMapping> getIntervalLineMappings() {
        if (intervalLineMappings == null) {
            intervalLineMappings = new ArrayList<>();
        }
        return intervalLineMappings;
    }

    public void setIntervalLineMappings(List<IntervalLineMapping> intervalLineMappings) {
        this.intervalLineMappings = intervalLineMappings;
    }

    public Double getTimeBarStartCoordinateX() {
        return timeBarStartCoordinateX;
    }

    public void setTimeBarStartCoordinateX(Double timeBarStartCoordinateX) {
        this.timeBarStartCoordinateX = timeBarStartCoordinateX;
    }

    public Double getTimeBarStartCoordinateY() {
        return timeBarStartCoordinateY;
    }

    public void setTimeBarStartCoordinateY(Double timeBarStartCoordinateY) {
        this.timeBarStartCoordinateY = timeBarStartCoordinateY;
    }
}
