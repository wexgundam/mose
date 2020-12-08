/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.tdms.core.vo;

/**
 * what:    分界口查询条件Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class FenjiekouSearchVo {
    /**
     * id等于
     */
    private Integer idEqual;
    /**
     * 文本标识Like，包括name、pinyin、initial pinyin
     */
    private String textLike;
    /**
     * 管辖局Id等于
     */
    private Integer bureauIdEqual;
    /**
     * 管辖局Id等于
     */
    private Integer trainlineDepotIdEqual;
    /**
     * 邻局Id等于
     */
    private Integer targetBureauIdEqual;
    /**
     * 邻局行调台Id等于
     */
    private Integer targetTrainlineDepotIdEqual;
    /**
     * 电报码等于
     */
    private String telegraphCodeEqual;


    public Integer getIdEqual() {
        return idEqual;
    }

    public void setIdEqual(Integer idEqual) {
        this.idEqual = idEqual;
    }

    public Integer getBureauIdEqual() {
        return bureauIdEqual;
    }

    public void setBureauIdEqual(Integer bureauIdEqual) {
        this.bureauIdEqual = bureauIdEqual;
    }

    public String getTextLike() {
        return textLike;
    }

    public void setTextLike(String textLike) {
        this.textLike = textLike == null ? textLike : textLike.toUpperCase();
    }

    public Integer getTrainlineDepotIdEqual() {
        return trainlineDepotIdEqual;
    }

    public void setTrainlineDepotIdEqual(Integer trainlineDepotIdEqual) {
        this.trainlineDepotIdEqual = trainlineDepotIdEqual;
    }

    public String getTelegraphCodeEqual() {
        return telegraphCodeEqual;
    }

    public void setTelegraphCodeEqual(String telegraphCodeEqual) {
        this.telegraphCodeEqual = telegraphCodeEqual;
    }

    public Integer getTargetBureauIdEqual() {
        return targetBureauIdEqual;
    }

    public void setTargetBureauIdEqual(Integer targetBureauIdEqual) {
        this.targetBureauIdEqual = targetBureauIdEqual;
    }

    public Integer getTargetTrainlineDepotIdEqual() {
        return targetTrainlineDepotIdEqual;
    }

    public void setTargetTrainlineDepotIdEqual(Integer targetTrainlineDepotIdEqual) {
        this.targetTrainlineDepotIdEqual = targetTrainlineDepotIdEqual;
    }
}
