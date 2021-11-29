/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.rail.vo;

/**
 * what:    铁路线查询条件Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class RailwayLineSearchVo {
    /**
     * id等于
     */
    private Integer idEqual;
    /**
     * 全称等于
     */
    private String nameEqual;
    /**
     * 全称Like
     */
    private String nameLike;
    /**
     * 编码等于
     */
    private Integer codeEqual;

    public String getNameEqual() {
        return nameEqual;
    }

    public void setNameEqual(String nameEqual) {
        this.nameEqual = nameEqual;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public Integer getIdEqual() {
        return idEqual;
    }

    public void setIdEqual(Integer idEqual) {
        this.idEqual = idEqual;
    }

    public Integer getCodeEqual() {
        return codeEqual;
    }

    public void setCodeEqual(Integer codeEqual) {
        this.codeEqual = codeEqual;
    }
}
