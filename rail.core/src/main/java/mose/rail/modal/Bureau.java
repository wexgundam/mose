/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:Rail
 */
package mose.rail.modal;


import mose.core.gis.GisUtil;

import java.util.Date;

/**
 * what:    铁路局. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class Bureau {
    /**
     * 主键
     */
    private int id;
    /**
     * 全称
     */
    private String name;
    /**
     * 简称
     */
    private String shortName;
    /**
     * 编码
     */
    private Integer code;
    /**
     * 电报码
     */
    private String telegraphCode;
    /**
     * 局办所在地地理坐标epsg4326
     */
    private String epsg4326;
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

        Bureau bureau = (Bureau) o;

        return id == bureau.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Bureau{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", code=" + code +
                ", telegraphCode='" + telegraphCode + '\'' +
                ", epsg4326='" + epsg4326 + '\'' +
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTelegraphCode() {
        return telegraphCode;
    }

    public void setTelegraphCode(String telegraphCode) {
        this.telegraphCode = telegraphCode;
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

    public String getEpsg4326() {
        return epsg4326;
    }

    public void setEpsg4326(String epsg4326) {
        this.epsg4326 = epsg4326;
    }
}
