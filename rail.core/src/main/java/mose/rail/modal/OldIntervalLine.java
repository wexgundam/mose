/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:Rail
 */
package mose.rail.modal;


import java.util.Date;

/**
 * what:    铁路线. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class OldIntervalLine {
    /**
     * R_S_NAME
     */
    private String rSName;
    /**
     * R_D
     */
    private String rD;
    /**
     * R_SCK
     */
    private Double rSck;
    /**
     * node id
     */
    private Integer nodeId;
    /**
     * node name
     */
    private String nodeName;
    /**
     * node epsg4326
     */
    private String nodeEpsg4326;

    @Override
    public String toString() {
        return "OldIntervalLine{" +
                ", rSName='" + rSName + '\'' +
                ", rD='" + rD + '\'' +
                ", rSck=" + rSck +
                ", nodeId=" + nodeId +
                ", nodeName='" + nodeName + '\'' +
                ", nodeEpsg4326='" + nodeEpsg4326 + '\'' +
                '}';
    }

    public String getrSName() {
        return rSName;
    }

    public void setrSName(String rSName) {
        this.rSName = rSName;
    }

    public String getrD() {
        return rD;
    }

    public void setrD(String rD) {
        this.rD = rD;
    }

    public Double getrSck() {
        return rSck;
    }

    public void setrSck(Double rSck) {
        this.rSck = rSck;
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

    public String getNodeEpsg4326() {
        return nodeEpsg4326;
    }

    public void setNodeEpsg4326(String nodeEpsg4326) {
        this.nodeEpsg4326 = nodeEpsg4326;
    }
}
