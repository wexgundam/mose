/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.core.modal;

import java.util.Date;

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
    private Integer id;
    /**
     * station a id
     */
    private Integer stationAId;
    /**
     * station a name
     */
    private String stationAName;
    /**
     * station a 纬度
     */
    private Double stationALatitude;
    /**
     * station a 经度
     */
    private Double stationALongitude;
    /**
     * station b id
     */
    private Integer stationBId;
    /**
     * station b name
     */
    private String stationBName;
    /**
     * station b 纬度
     */
    private Double stationBLatitude;
    /**
     * station b 经度
     */
    private Double stationBLongitude;
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
        if (!(o instanceof Line)) return false;

        Line line = (Line) o;

        return id != null ? id.equals(line.id) : line.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", stationAId=" + stationAId +
                ", stationAName='" + stationAName + '\'' +
                ", stationALatitude=" + stationALatitude +
                ", stationALongitude=" + stationALongitude +
                ", stationBId=" + stationBId +
                ", stationBName='" + stationBName + '\'' +
                ", stationBLatitude=" + stationBLatitude +
                ", stationBLongitude=" + stationBLongitude +
                ", creatorId=" + creatorId +
                ", creatorRealName='" + creatorRealName + '\'' +
                ", createdAt=" + createdAt +
                ", lastEditorId=" + lastEditorId +
                ", lastEditorRealName='" + lastEditorRealName + '\'' +
                ", lastEditedAt=" + lastEditedAt +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationAId() {
        return stationAId;
    }

    public void setStationAId(Integer stationAId) {
        this.stationAId = stationAId;
    }

    public Integer getStationBId() {
        return stationBId;
    }

    public void setStationBId(Integer stationBId) {
        this.stationBId = stationBId;
    }

    public Double getStationALatitude() {
        return stationALatitude;
    }

    public void setStationALatitude(Double stationALatitude) {
        this.stationALatitude = stationALatitude;
    }

    public Double getStationALongitude() {
        return stationALongitude;
    }

    public void setStationALongitude(Double stationALongitude) {
        this.stationALongitude = stationALongitude;
    }

    public Double getStationBLatitude() {
        return stationBLatitude;
    }

    public void setStationBLatitude(Double stationBLatitude) {
        this.stationBLatitude = stationBLatitude;
    }

    public Double getStationBLongitude() {
        return stationBLongitude;
    }

    public void setStationBLongitude(Double stationBLongitude) {
        this.stationBLongitude = stationBLongitude;
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

    public String getStationAName() {
        return stationAName;
    }

    public void setStationAName(String stationAName) {
        this.stationAName = stationAName;
    }

    public String getStationBName() {
        return stationBName;
    }

    public void setStationBName(String stationBName) {
        this.stationBName = stationBName;
    }
}
