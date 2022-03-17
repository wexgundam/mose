/**
 * Copyright 2022 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.traindiagram.modal;

import java.util.Date;

/**
 * what:    运行图对应路网IntervalLine的映射. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2022/3/2
 */
public class IntervalLineMapping {
    /**
     * 主键，唯一标识
     */
    private int id;
    /**
     * 所属Train Diagram id
     */
    private Integer trainDiagramId;
    /**
     * node ID
     */
    private Integer intervalLineId;
    /**
     * nodeA Id
     */
    private Integer nodeAId;
    /**
     * nodeA Name
     */
    private String nodeAName;
    /**
     * nodeB Id
     */
    private Integer nodeBId;
    /**
     * nodeB Name
     */
    private String nodeBName;
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
        if (!(o instanceof IntervalLineMapping)) return false;

        IntervalLineMapping that = (IntervalLineMapping) o;

        return id == that.id;
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

    public Integer getTrainDiagramId() {
        return trainDiagramId;
    }

    public void setTrainDiagramId(Integer trainDiagramId) {
        this.trainDiagramId = trainDiagramId;
    }

    public Integer getIntervalLineId() {
        return intervalLineId;
    }

    public void setIntervalLineId(Integer intervalLineId) {
        this.intervalLineId = intervalLineId;
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
