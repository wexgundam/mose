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
public class IntervalLineSearchVo {
    /**
     * id等于
     */
    private Integer idEqual;
    /**
     * 节点Id等于
     */
    private Integer nodeIdEqual;
    /**
     * 节点Id数组等于
     */
    private Integer[] nodesIdEqual;
    /**
     * 所属铁路线Id等于
     */
    private Integer railwayLineIdEqual;
    /**
     * 行别等于
     */
    private String directionEqual;

    public String getDirectionEqual() {
        return directionEqual;
    }

    public void setDirectionEqual(String directionEqual) {
        this.directionEqual = directionEqual;
    }

    /**
     * 返回nodesIdEqual的nodeAId
     *
     * @return
     */
    public Integer getNodeAIdEqual() {
        return (nodesIdEqual != null && nodesIdEqual.length > 1) ? nodesIdEqual[0] : null;
    }

    /**
     * 返回nodesIdEqual的nodeBId
     *
     * @return
     */
    public Integer getNodeBIdEqual() {
        return (nodesIdEqual != null && nodesIdEqual.length > 1) ? nodesIdEqual[1] : null;
    }

    public Integer getIdEqual() {
        return idEqual;
    }

    public void setIdEqual(Integer idEqual) {
        this.idEqual = idEqual;
    }

    public Integer getNodeIdEqual() {
        return nodeIdEqual;
    }

    public void setNodeIdEqual(Integer nodeIdEqual) {
        this.nodeIdEqual = nodeIdEqual;
    }

    public Integer[] getNodesIdEqual() {
        return nodesIdEqual;
    }

    public void setNodesIdEqual(Integer[] nodesIdEqual) {
        this.nodesIdEqual = nodesIdEqual;
    }

    public Integer getRailwayLineIdEqual() {
        return railwayLineIdEqual;
    }

    public void setRailwayLineIdEqual(Integer railwayLineIdEqual) {
        this.railwayLineIdEqual = railwayLineIdEqual;
    }
}
