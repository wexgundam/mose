/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.rail.vo;

/**
 * what:    区间线路 Geo Spatial Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class IntervalLineGeoSpatialVo {
    /**
     * id等于
     */
    private int id;
    /**
     * nodeA id
     */
    private int nodeAId;
    /**
     * nodeB id
     */
    private int nodeBId;
    /**
     * coordinates
     */
    private double[][] coordinates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodeAId() {
        return nodeAId;
    }

    public void setNodeAId(int nodeAId) {
        this.nodeAId = nodeAId;
    }

    public int getNodeBId() {
        return nodeBId;
    }

    public void setNodeBId(int nodeBId) {
        this.nodeBId = nodeBId;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }
}
