/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:Rail
 */
package mose.rail.modal;


import java.util.Date;

/**
 * what:    区间线路. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class IntervalLine {
    /**
     * 主键
     */
    private int id;
    /**
     * 行车方向
     */
    private Integer railwayLineId;
    /**
     * nodeA bureau id
     */
    private Integer nodeABureauId;
    /**
     * nodeA bureau 简称
     */
    private String nodeABureauShortName;
    /**
     * nodeA id
     */
    private Integer nodeAId;
    /**
     * nodeA 名称
     */
    private String nodeAName;
    /**
     * nodeA 公里标
     */
    private Double nodeAMileage;
    /**
     * nodeB bureau id
     */
    private Integer nodeBBureauId;
    /**
     * nodeB bureau 简称
     */
    private String nodeBBureauShortName;
    /**
     * nodeB id
     */
    private Integer nodeBId;
    /**
     * nodeB 名称
     */
    private String nodeBName;
    /**
     * nodeB 公里标
     */
    private Double nodeBMileage;
    /**
     * 行别：双上、双下、上、下、单、三、四、五....
     * <p>
     * “上”：双上、上、单
     * “下”：双下、下、单
     * “双”：双上、双下
     */
    private String direction;
    /**
     * 别名
     */
    private String alias;
    /**
     * 地理空间信息
     */
    private String geoSpatial;
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
        if (o == null || getClass() != o.getClass()) return false;

        IntervalLine bureau = (IntervalLine) o;

        return id == bureau.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "IntervalLine{" +
                "id=" + id +
                ", railwayLineId=" + railwayLineId +
                ", nodeABureauId=" + nodeABureauId +
                ", nodeABureauShortName='" + nodeABureauShortName + '\'' +
                ", nodeAId=" + nodeAId +
                ", nodeAName='" + nodeAName + '\'' +
                ", nodeAMileage=" + nodeAMileage +
                ", nodeBBureauId=" + nodeBBureauId +
                ", nodeBBureauShortName='" + nodeBBureauShortName + '\'' +
                ", nodeBId=" + nodeBId +
                ", nodeBName='" + nodeBName + '\'' +
                ", nodeBMileage=" + nodeBMileage +
                ", direction='" + direction + '\'' +
                ", alias='" + alias + '\'' +
                ", geoSpatial='" + geoSpatial + '\'' +
                ", creatorId=" + creatorId +
                ", creatorRealName='" + creatorRealName + '\'' +
                ", createdAt=" + createdAt +
                ", lastEditorId=" + lastEditorId +
                ", lastEditorRealName='" + lastEditorRealName + '\'' +
                ", lastEditedAt=" + lastEditedAt +
                '}';
    }

    /**
     * what:    获取关联nodesId. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public Integer[] getNodesId() {
        return new Integer[]{this.nodeAId, this.nodeBId};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRailwayLineId() {
        return railwayLineId;
    }

    public void setRailwayLineId(Integer railwayLineId) {
        this.railwayLineId = railwayLineId;
    }

    public Integer getNodeABureauId() {
        return nodeABureauId;
    }

    public void setNodeABureauId(Integer nodeABureauId) {
        this.nodeABureauId = nodeABureauId;
    }

    public String getNodeABureauShortName() {
        return nodeABureauShortName;
    }

    public void setNodeABureauShortName(String nodeABureauShortName) {
        this.nodeABureauShortName = nodeABureauShortName;
    }

    public Integer getNodeAId() {
        return nodeAId;
    }

    public void setNodeAId(Integer nodeAId) {
        this.nodeAId = nodeAId;
    }

    public String getNodeAName() {
        return nodeAName;
    }

    public void setNodeAName(String nodeAName) {
        this.nodeAName = nodeAName;
    }

    public Double getNodeAMileage() {
        return nodeAMileage;
    }

    public void setNodeAMileage(Double nodeAMileage) {
        this.nodeAMileage = nodeAMileage;
    }

    public Integer getNodeBBureauId() {
        return nodeBBureauId;
    }

    public void setNodeBBureauId(Integer nodeBBureauId) {
        this.nodeBBureauId = nodeBBureauId;
    }

    public String getNodeBBureauShortName() {
        return nodeBBureauShortName;
    }

    public void setNodeBBureauShortName(String nodeBBureauShortName) {
        this.nodeBBureauShortName = nodeBBureauShortName;
    }

    public Integer getNodeBId() {
        return nodeBId;
    }

    public void setNodeBId(Integer nodeBId) {
        this.nodeBId = nodeBId;
    }

    public String getNodeBName() {
        return nodeBName;
    }

    public void setNodeBName(String nodeBName) {
        this.nodeBName = nodeBName;
    }

    public Double getNodeBMileage() {
        return nodeBMileage;
    }

    public void setNodeBMileage(Double nodeBMileage) {
        this.nodeBMileage = nodeBMileage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGeoSpatial() {
        return geoSpatial;
    }

    public void setGeoSpatial(String geoSpatial) {
        this.geoSpatial = geoSpatial;
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
}
