/**
 * Copyright 2022 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.traindiagram.modal;

import java.util.Date;

/**
 * what:    运行图对应路网Node的映射. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2022/3/2
 */
public class NodeMapping {
    /**
     * 主键，唯一标识
     */
    private int id;
    /**
     * 所属Train Diagram id
     */
    private Integer trainDiagramId;
    /**
     * 序号
     */
    private Integer order;
    /**
     * node ID
     */
    private Integer nodeId;
    /**
     * node Name
     */
    private String nodeName;
    /**
     * 显示text
     */
    private String text;
    /**
     * 运行图node mapping从上到下排列，本node mapping与紧上方的node mapping的纵向间距
     * 一般通过运行时分计算获得
     */
    private Double interval;
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
        if (!(o instanceof NodeMapping)) return false;

        NodeMapping interval = (NodeMapping) o;

        return id == interval.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "NodeMapping{" +
                "id=" + id +
                ", trainDiagramId=" + trainDiagramId +
                ", order=" + order +
                ", nodeId=" + nodeId +
                ", nodeName='" + nodeName + '\'' +
                ", text='" + text + '\'' +
                ", interval=" + interval +
                ", creatorId=" + creatorId +
                ", creatorRealName='" + creatorRealName + '\'' +
                ", createdAt=" + createdAt +
                ", lastEditorId=" + lastEditorId +
                ", lastEditorRealName='" + lastEditorRealName + '\'' +
                ", lastEditedAt=" + lastEditedAt +
                '}';
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Double getInterval() {
        return interval;
    }

    public void setInterval(Double interval) {
        this.interval = interval;
    }
}
