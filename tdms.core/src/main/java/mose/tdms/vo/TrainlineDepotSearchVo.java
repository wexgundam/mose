/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.tdms.vo;

/**
 * what:    行车调度台查询条件Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class TrainlineDepotSearchVo {
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
     * ddtId等于
     */
    private Integer ddtIdEqual;
    /**
     * 核对状态等于true or false
     */
    private Boolean verified;


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

    public Integer getDdtIdEqual() {
        return ddtIdEqual;
    }

    public void setDdtIdEqual(Integer ddtIdEqual) {
        this.ddtIdEqual = ddtIdEqual;
    }

    public String getTextLike() {
        return textLike;
    }

    public void setTextLike(String textLike) {
        this.textLike = textLike == null ? textLike : textLike.toUpperCase();
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
