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
public class TrainlineDepot {
    /**
     * id
     */
    private Integer id;
    /**
     * 局id
     */
    private Integer bureauId;
    /**
     * 路局名称
     */
    private String bureauName;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称全拼音码
     */
    private String namePinyin;
    /**
     * 名称首字母拼音码
     */
    private String nameInitialPinyin;
    /**
     * ddt id
     */
    private Integer ddtId;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
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
        if (!(o instanceof TrainlineDepot)) return false;

        TrainlineDepot that = (TrainlineDepot) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TrainlineDepot{" +
                "id=" + id +
                ", bureauId=" + bureauId +
                ", bureauName='" + bureauName + '\'' +
                ", name='" + name + '\'' +
                ", namePinyin='" + namePinyin + '\'' +
                ", nameInitialPinyin='" + nameInitialPinyin + '\'' +
                ", ddtId=" + ddtId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBureauId() {
        return bureauId;
    }

    public void setBureauId(Integer bureauId) {
        this.bureauId = bureauId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBureauName() {
        return bureauName;
    }

    public void setBureauName(String bureauName) {
        this.bureauName = bureauName;
    }

    public Integer getDdtId() {
        return ddtId;
    }

    public void setDdtId(Integer ddtId) {
        this.ddtId = ddtId;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getNameInitialPinyin() {
        return nameInitialPinyin;
    }

    public void setNameInitialPinyin(String nameInitialPinyin) {
        this.nameInitialPinyin = nameInitialPinyin;
    }
}
